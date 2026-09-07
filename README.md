# First Edition Companion

A dependency-light Android prototype for tracking an AD&D 1st Edition character during play.

## Current version: v0.5.0

[Download the official v0.5.0 APK](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/download/v0.5.0/FirstEditionCompanion-v0.5.0.apk)

[View the latest GitHub Release](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/latest)

### Features
- Named character profiles: save, load, start a new character, or delete saved profiles
- **Portable Save Export/Import:** back up all named profiles plus the current working character to a JSON file
- Import backups with **Restore / Replace** or non-destructive **Merge** behavior
- Structured **Gear** page with Equipped, Carried Gear, Currency, Magic Items, Valuables & Treasure, and Mounts/Tack/Transport
- Searchable core AD&D 1e equipment catalogs with PHB, DMG, and Unearthed Arcana source labels
- Large searchable DMG/UA magic-item catalog organized by the rulebook magic-item families
- Equipment slots, quantities, optional magic-item charges/uses, and personal item notes
- PHB currency tracking for cp/sp/ep/gp/pp with gold-piece-equivalent total
- Old free-form gear data preserved under **Legacy Gear Notes**
- Each saved profile retains its sheet, combat numbers, structured inventory, currency, notes, and casting tracks
- Ability scores, saving throws, HP, Armor Class, THAC0, target-AC hit calculator
- Weapon attack/damage roller
- Searchable PHB + Unearthed Arcana class spell catalog for Cleric, Druid, Magic-User, and Illusionist
- **Spell access is separate from preparation:** Magic-Users/Illusionists maintain a Spellbook; Clerics/Druids maintain an Available Spells list
- Prepared/memorized spell copies remain independently tracked and can be marked used/restored as they are cast
- Integrated dice roller with expressions such as `2d6+3`
- Responsive navigation for folded and unfolded phones

## Gear workflow
The Gear page uses one structured inventory underneath several useful views:
- **Equipped** — items currently worn or readied, with equipment-slot tracking.
- **Carried Gear** — ordinary unequipped adventuring equipment.
- **Currency** — copper, silver, electrum, gold, and platinum pieces using the PHB monetary system.
- **Magic Items** — potions/oils, scrolls, rings, rods/staves, wands, miscellaneous magic, magic armor/shields, swords/weapons, and artifacts/relics from the DMG/UA catalog.
- **Valuables & Treasure** — gems, jewelry, art objects, rare books/manuscripts, and other portable wealth.
- **Mounts, Tack & Transport** — animals, saddles/barding, carts, wagons, boats, ships, and similar property.

A magic item that is equipped can appear in both **Equipped** and **Magic Items** while remaining one underlying item record. Tap a recorded item to edit quantity, slot, equipped state, charges/uses, or notes.

The built-in catalog focuses on the core AD&D 1e hardcover sources used by this project (PHB, DMG, and Unearthed Arcana) instead of silently mixing Dragon magazine equipment into the core lists. Long copyrighted item descriptions are not reproduced; consult the source label for complete rules text.

## Backups and updating
Use **Export Saves** on the Sheet tab to create a portable `.json` backup. The backup includes the current working character and all named saved profiles, including structured inventory, currency, spellbooks, prepared spells, equipment notes, and combat values.

Use **Import Saves** to restore that file later. **Merge** preserves existing local profiles and adds imported copies under unique names; **Restore / Replace** replaces the app's character saves with the backup.

Starting with **v0.4.0**, official prototype releases use the same stable prototype signing key. v0.5.0 should therefore install directly over v0.4.0 and preserve Android app data, while exported backups remain the recommended safety net before updates.

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
The repository is standalone. GitHub Actions installs Gradle 9.7.1, uses JDK 17, decodes the stable prototype signing key, and runs:

```bash
gradle :app:assembleDebug
```

Release builds are serialized to avoid competing publish jobs. For every user-facing update, the project version is incremented and the workflow:
- builds the APK,
- uploads the Actions artifact,
- publishes the APK to the repository `releases/` folder, and
- creates or updates the matching official GitHub Release with the APK attached.

See `DEVELOPMENT.md` for the project's release definition of done.

This remains a field-test prototype rather than a complete automated rules implementation. THAC0 and saving throws are user-entered so the app can match the exact attack tables and house rules used by a campaign.
