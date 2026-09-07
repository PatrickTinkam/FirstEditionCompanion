# First Edition Companion

A dependency-light Android prototype for tracking an AD&D 1st Edition character during play.

## Current version: v0.3.0

[Download the official v0.3.0 APK](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/download/v0.3.0/FirstEditionCompanion-v0.3.0.apk)

[View the latest GitHub Release](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/latest)

### Features
- Named character profiles: save, load, start a new character, or delete saved profiles
- Each saved profile retains its own sheet, combat numbers, gear, notes, and casting tracks
- Persistent full character sheet and modular multiclass casting tracks
- Ability scores, saving throws, HP, Armor Class, THAC0, target-AC hit calculator
- Weapon attack/damage roller
- Searchable PHB + Unearthed Arcana class spell catalog for Cleric, Druid, Magic-User, and Illusionist
- **Spell access is separate from preparation:** Magic-Users/Illusionists maintain a Spellbook; Clerics/Druids maintain an Available Spells list
- Add learned/discovered spells from the catalog to the character's Spellbook/Available Spells list, then prepare one or more copies from there
- Existing v0.2 prepared spells are automatically migrated into the new known/available list the first time a track is opened
- Prepared/memorized spell copies remain independently tracked and can be marked used/restored as they are cast
- Custom/campaign spells can be added to the known/available list and then prepared normally
- Catalog includes PHB-era spell lists and Unearthed Arcana additions, including UA cantrips
- Tap catalog, known, or prepared spells for concise original table summaries and source labels
- One-tap rest reset for prepared spell use state
- Integrated dice roller with expressions such as `2d6+3`
- Equipment, wealth, proficiencies, languages, notes, and clipboard backup
- Responsive navigation for folded and unfolded phones

## Spell workflow
For a Magic-User or Illusionist:
1. Open the casting track and choose **Browse Class Spells**.
2. Add a spell the character has learned or discovered to the **Spellbook**.
3. Prepare one or more copies from the Spellbook.
4. Mark each prepared copy **Used** when cast.

Cleric and Druid tracks use the same workflow but call the persistent list **Available Spells** rather than Spellbook.

## Spell text and source books
The spell browser identifies PHB and Unearthed Arcana material and provides concise original summaries. It does **not** reproduce the copyrighted spell-description text from the books. Use the cited source book for exact wording, ranges, durations, components, saving throws, and special cases.

Reference books requested for the prototype catalog:
- *Advanced Dungeons & Dragons Players Handbook*, ISBN 0-935696-01-6
- *Unearthed Arcana*, ISBN 0-88038-084-5

## Build and release policy
The repository is standalone. GitHub Actions installs Gradle 9.7.1, uses JDK 17, and runs:

```bash
gradle :app:assembleDebug
```

For every user-facing update, the project version is incremented and the workflow:
- builds the APK,
- uploads the Actions artifact,
- publishes the APK to the repository `releases/` folder, and
- creates or updates the matching official GitHub Release with the APK attached.

See `DEVELOPMENT.md` for the project's release definition of done.

This remains a field-test prototype rather than a complete automated rules implementation. THAC0 and saving throws are user-entered so the app can match the exact attack tables and house rules used by a campaign.
