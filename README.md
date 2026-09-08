# First Edition Companion

A dependency-light Android companion for tracking an AD&D 1st Edition character during play.

## Current version: v0.6.2

[Download the official v0.6.2 APK](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/download/v0.6.2/FirstEditionCompanion-v0.6.2.apk)

[View the latest GitHub Release](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/latest)

### Features
- Named character profiles with save/load/new/delete
- Portable JSON **Export Saves / Import Saves** with Merge and Restore/Replace
- Structured Gear page with Equipped, Carried Gear, Currency, Magic Items, Valuables & Treasure, and Mounts/Tack/Transport
- Searchable equipment and magic-item catalogs with source labels
- Equipment slots, quantities, optional charges/uses, and personal item notes
- PHB cp/sp/ep/gp/pp currency tracking with GP-equivalent total
- Searchable Cleric, Druid, Magic-User, and Illusionist spell catalogs
- Magic-User/Illusionist **Spellbook** workflow with learned spells separated from prepared/memorized copies
- Clerics automatically receive their full built-in class spell list, grouped by spell level, and prepare directly from that list
- PHB spell details with range, duration, area, components, casting time, saving throw, source page, and concise mechanics where matched
- Native/offline spell descriptions throughout the catalog, including fallback summaries for entries without a full structured rules digest
- **Rules** tab with source-grounded PHB table references
- Optional local PHB/UA/DMG PDF linking and in-app PHB source-page viewing
- Combat dashboard with HP, AC, THAC0 helper, attack/damage rolls, saves, and PHB combat references
- Integrated dice roller
- Responsive navigation for folded and unfolded phones

## Source-grounded rules

v0.6.2 continues the book-grounding pass using the supplied 1978 AD&D 1e *Players Handbook*. The app's PHB spell reference follows the PHB's own presentation fields: type/school, level, range, duration, area of effect, components, casting time, saving throw, and effect.

The Rules tab summarizes table-use mechanics for spell preparation, spell interruption, money, armor/shields, weapon proficiency, encumbrance, movement, light, surprise, turning undead, AC/saves, damage, healing, and experience. Each PHB reference includes a printed page number.

### Link your own rulebooks

On **Rules → Your Rulebooks**, use Android's system file picker to link your legally owned PDF copies. The Companion stores persistent read access to the selected document URI on that device. PHB references can then open the cited source page directly in the app.

The PDFs themselves are **not** committed to this repository or bundled in the APK. They are optional source-reference conveniences; the spell catalog itself is designed to remain useful offline without linked PDFs.

### Unearthed Arcana / DMG status

The supplied Unearthed Arcana PDF contains later revised/variant rules terminology and mechanics rather than matching a clean original 1985 TSR text. Existing UA catalog entries remain available and source-tagged, and v0.6.2 provides native concise descriptions without silently blending later variant mechanics into the verified PHB rules layer.

Detailed powers for many magic items require the *Dungeon Masters Guide*. Until a DMG source is supplied, DMG-only entries remain usable for tracking but are explicitly marked as awaiting source-grounded mechanics rather than receiving guessed descriptions.

## Spell workflow

For a Magic-User or Illusionist:
1. Browse the class spell catalog.
2. Add learned/discovered spells to the **Spellbook**.
3. Prepare one or more individual copies.
4. Mark each prepared copy **Used** when cast.
5. Rest/restore prepared copies as appropriate for the campaign.

For a Cleric:
1. The full built-in Cleric spell list is available automatically.
2. Browse spells by spell level.
3. Open **Details** or **Prepare** directly from the class list.
4. Prepare one or more copies.
5. Mark prepared copies **Used** when cast and restore them after rest as appropriate.

The PHB treats multiple memorized copies as separate uses, which is why the app does not model one memorized spell as an endlessly reusable modern-style slot.

## Gear workflow

The Gear page uses one inventory record underneath several useful views:
- **Equipped**
- **Carried Gear**
- **Currency**
- **Magic Items**
- **Valuables & Treasure**
- **Mounts, Tack & Transport**

An equipped magic item can appear in both Equipped and Magic Items while remaining one underlying record. The **Rules** action beside recorded items exposes current source-grounded handling without overwriting the character's personal notes.

## Backups and updates

Use **Export Saves** before major updates or moving devices. Backups include the current character, saved profiles, structured inventory/currency, spellbooks, prepared spells, gear notes, and combat values.

Official builds from v0.4.0 onward use the same stable prototype signing key, so v0.6.2 should install directly over v0.6.1 and preserve app-local data.

## Build and release policy

The repository is standalone. GitHub Actions installs Gradle 9.7.1, uses JDK 17, decodes the stable prototype signing key, and runs:

```bash
gradle :app:assembleDebug
```

Release builds are serialized. Every user-facing update increments the app version, builds and uploads the APK, publishes the versioned APK into the repository, and creates or updates the matching official GitHub Release.

See `DEVELOPMENT.md` for the release definition of done.
