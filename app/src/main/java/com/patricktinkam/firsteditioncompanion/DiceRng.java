package com.patricktinkam.firsteditioncompanion;

import java.security.SecureRandom;
import java.util.Random;

/** Shared, OS-seeded random source for every die roll in the app. */
public final class DiceRng {
  private static final SecureRandom RNG=new SecureRandom();
  private static final Random ADAPTER=new Random(){
    @Override public int nextInt(int bound){
      if(bound<=0)throw new IllegalArgumentException("bound must be positive");
      return RNG.nextInt(bound);
    }
    @Override protected int next(int bits){
      if(bits<=0)return 0;
      return RNG.nextInt() >>> (32-bits);
    }
  };

  public static int die(int sides){
    if(sides<1||sides>1_000_000)throw new IllegalArgumentException("die sides out of range");
    return RNG.nextInt(sides)+1;
  }

  public static int roll(int count,int sides){
    if(count<1||count>10_000)throw new IllegalArgumentException("dice count out of range");
    if(sides<1||sides>1_000_000)throw new IllegalArgumentException("die sides out of range");
    long total=0;
    for(int i=0;i<count;i++)total+=die(sides);
    if(total>Integer.MAX_VALUE)throw new IllegalArgumentException("roll total too large");
    return (int)total;
  }

  /** Adapter for legacy call sites that already expect java.util.Random.nextInt(bound). */
  public static Random adapter(){return ADAPTER;}

  public static final class Audit {
    public final boolean passed;
    public final int totalSamples;
    public final String summary;
    Audit(boolean passed,int totalSamples,String summary){this.passed=passed;this.totalSamples=totalSamples;this.summary=summary;}
  }

  /**
   * Lightweight runtime sanity check. For each standard die, sample 100 rolls per face
   * and verify every legal face appears and no result ever leaves the legal range.
   * This is intentionally a health check, not a claim that a finite sample proves perfect randomness.
   */
  public static Audit auditStandardDice(){
    int[] dice={4,6,8,10,12,20,100};
    int samples=0;
    StringBuilder detail=new StringBuilder();
    boolean ok=true;
    for(int sides:dice){
      int n=sides*100;
      boolean[] seen=new boolean[sides];
      long sum=0;
      for(int i=0;i<n;i++){
        int v=die(sides);samples++;sum+=v;
        if(v<1||v>sides){ok=false;continue;}
        seen[v-1]=true;
      }
      int distinct=0;for(boolean s:seen)if(s)distinct++;
      if(distinct!=sides)ok=false;
      double mean=sum/(double)n;
      if(detail.length()>0)detail.append("\n");
      detail.append("d").append(sides).append(": ").append(distinct).append("/").append(sides)
        .append(" faces seen • mean ").append(String.format(java.util.Locale.US,"%.2f",mean));
    }
    String head=ok?"PASS — standard dice produced their full legal ranges.":"CHECK FAILED — one or more dice did not cover the expected range.";
    return new Audit(ok,samples,head+"\nOS-seeded SecureRandom • no per-roll reseeding\n\n"+detail.toString());
  }

  private DiceRng(){}
}
