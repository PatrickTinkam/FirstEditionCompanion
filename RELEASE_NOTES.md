# First Edition Companion v0.9.7

v0.9.7 performs a full sweep of the app's current rolling mechanics and centralizes all live random rolls on one shared OS-seeded `SecureRandom` source.

## RNG hardening
- Added shared `DiceRng` service backed by one `java.security.SecureRandom` instance.
- The app no longer relies on separate live `Random` generators for the normal play screen and character creator.
- No roll action reseeds the generator from the clock or another predictable value.
- Bounded `nextInt` calls keep each dN result uniformly in the legal `1..N` range without modulo bias.

## Rolling systems reviewed
- THAC0 attack d20 rolls.
- Quick saving-throw d20 rolls.
- Weapon/damage dice expressions such as `1d8+2`.
- Quick d4, d6, d8, d10, d12, d20, and d100 buttons.
- DMG Method I ability generation: 4d6, drop one lowest die.
- DMG Method II: twelve 3d6 totals, keep the best six.
- DMG Method III: six 3d6 attempts per ability, keep that ability's best result.
- DMG Method IV: twelve complete in-order 3d6 sets, choose one whole set.
- Starting-age dice and other creator code that uses the shared die helper.

No off-by-one or range error was found in those formulas during the sweep. The main change is making their randomness source consistent and harder to accidentally break later.

## RNG Self-Check
- The Dice tab now includes **Run RNG Self-Check**.
- It samples d4, d6, d8, d10, d12, d20, and d100 using the same RNG used during play.
- The check reports whether every legal face appeared and shows the sample mean for each die.
- This is a runtime health check rather than a claim that a finite sample mathematically proves perfect randomness.

## Future rolling policy
- `DEVELOPMENT.md` now requires future attack, damage, save, HP, character-generation, treasure, random-table, and similar mechanics to route through `DiceRng`.
- New feature code should not create its own `Random`, call `Math.random()`, or reseed a generator inside a roll action.

## Existing behavior retained
- v0.9.6 **Why? • Source** warning-detail system remains intact.
- The ability-score pool, manual distribution, Auto Distribute, and Methods I–IV behavior remain intact.
- Existing saved characters and in-progress creation drafts are not intentionally rewritten.

## Version confirmation
- Main app title: **v0.9.7**
- Character Profiles card: **App Version: v0.9.7**
- Guided creator title: **v0.9.7**

## Updating
v0.9.7 uses the same stable prototype signing key and should install directly over v0.9.6 while preserving app-local data.

## Installation
Download the attached `FirstEditionCompanion-v0.9.7.apk` and install it on Android.
