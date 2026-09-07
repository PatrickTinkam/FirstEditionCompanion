# First Edition Companion

A dependency-light Android prototype for tracking an AD&D 1st Edition character during play.

## Current version: v0.2.0

[Download the installable v0.2.0 APK](releases/FirstEditionCompanion-v0.2.0.apk)

### Features
- Named character profiles: save, load, start a new character, or delete saved profiles
- Each saved profile retains its own sheet, combat numbers, gear, notes, and casting tracks
- Persistent full character sheet and modular multiclass casting tracks
- Ability scores, saving throws, HP, Armor Class, THAC0, target-AC hit calculator
- Weapon attack/damage roller
- Prepared/memorized spell copies and used-spell tracking
- Searchable class spell browser for Cleric, Druid, Magic-User, and Illusionist
- Catalog combines Players Handbook-era spell lists with Unearthed Arcana additions, including UA cantrips
- Tap a catalog spell for a concise original table summary and source label, then prepare it directly
- One-tap rest reset
- Custom spell entry for house rules and campaign-specific spells
- Integrated dice roller with expressions such as `2d6+3`
- Equipment, wealth, proficiencies, languages, notes, and clipboard backup
- Responsive navigation for folded and unfolded phones

## Spell text and source books
The spell browser identifies PHB and Unearthed Arcana material and provides concise original summaries. It does **not** reproduce the copyrighted spell-description text from the books. Use the cited source book for exact wording, ranges, durations, components, saving throws, and special cases.

Reference books requested for the prototype catalog:
- *Advanced Dungeons & Dragons Players Handbook*, ISBN 0-935696-01-6
- *Unearthed Arcana*, ISBN 0-88038-084-5

## Build
The repository is standalone. GitHub Actions installs Gradle 9.7.1, uses JDK 17, and runs:

```bash
gradle :app:assembleDebug
```

The workflow uploads `FirstEditionCompanion-v0.2.0.apk` as a build artifact after each push to `main`.

This remains a field-test prototype rather than a complete automated rules implementation. THAC0 and saving throws are user-entered so the app can match the exact attack tables and house rules used by a campaign.
