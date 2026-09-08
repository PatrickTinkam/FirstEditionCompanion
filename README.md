# First Edition Companion

A dependency-light Android companion for tracking an AD&D 1st Edition character during play.

## Current version: v0.7.0

[Download the official v0.7.0 APK](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/download/v0.7.0/FirstEditionCompanion-v0.7.0.apk)

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
- PHB spell details with school/type, range, duration, area, components, casting time, saving throw, reversibility, source page, and native effect digests
- Native/offline spell descriptions throughout the catalog
- Native/offline DMG and supplied-UA magic-item mechanics instead of “consult the book” placeholders
- Expanded **Rules** tab covering PHB and DMG spellcasting, initiative, surprise, saving throws, item saves, weapons, armor, turning undead, treasure, potion/scroll/ring/device handling, charges, cursed items, artifacts, movement, experience, and more
- Optional local PHB/UA/DMG PDF linking for source-page verification only; PDFs are not required to use the reference
- Combat dashboard with HP, AC, THAC0 helper, attack/damage rolls, saves, and rules references
- Integrated dice roller
- Responsive navigation for folded and unfolded phones

## Source-grounded rules

v0.7.0 is the first broad **PHB + DMG + UA sourcebook mechanics pass**.

The supplied AD&D 1e *Players Handbook* remains the primary player-facing spell and class source. PHB spell entries retain the book's structured fields—type/school, range, duration, area of effect, components, casting time, saving throw, reversibility, and source page—plus a native offline effect digest.

The supplied *Dungeon Masters Guide* now fills the large rules/mechanics gap that previously existed in the app. The Rules tab includes DMG handling for cleric and magic-user spell access, casting during melee, cover, counter-affecting spells, initiative and surprise, morale, weapon speed, two-weapon fighting, saving throws, item saving throws, magic resistance, turning undead, holy/unholy water, potion miscibility, scrolls, rings, rods/staves/wands, identification, command words, charges, cursed items, artifacts/relics, character expenses, gems, experience, and training.

The Gear catalog now provides native mechanics for magic items. Named items with dedicated entries show their special behavior directly; remaining catalog entries receive category-specific 1e handling instead of a generic missing-description message. Standard enchanted armor and weapons explain how their pluses apply, while potions, scrolls, rings, charged devices, cursed items, and artifacts use their appropriate sourcebook handling.

### Unearthed Arcana source integrity

The `unearthedarcana.pdf` supplied to this project contains later/conversion-style terminology and mechanics—including systems not presented as original 1985 AD&D 1e rules. v0.7.0 therefore keeps UA-only catalog entries available but explicitly labels them as **supplied UA variant** material. The app does not silently replace verified PHB/DMG mechanics with those later mechanics.

### Optional source PDFs

PDF linking is now explicitly optional. The Companion is intended to remain useful offline without linked rulebooks. Linking a legally owned PDF simply allows the Rules or spell detail screens to open the cited printed page for verification.

The PDFs themselves are not committed to the repository or bundled in the APK.

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

The app models each memorized/prepared copy as a separate use, consistent with the 1e preparation model.

## Gear workflow

The Gear page uses one inventory record underneath several useful views:
- **Equipped**
- **Carried Gear**
- **Currency**
- **Magic Items**
- **Valuables & Treasure**
- **Mounts, Tack & Transport**

Tap a catalog or recorded magic item and use its **Rules** action to see native mechanics, source category/page range where known, and any source-integrity note. Personal item notes remain separate from the built-in rules text.

## Backups and updates

Use **Export Saves** before major updates or moving devices. Backups include the current character, saved profiles, structured inventory/currency, spellbooks, prepared spells, gear notes, and combat values.

Official builds from v0.4.0 onward use the same stable prototype signing key, so v0.7.0 should install directly over v0.6.2 and preserve app-local data.

## Build and release policy

The repository is standalone. GitHub Actions installs Gradle 9.7.1, uses JDK 17, decodes the stable prototype signing key, and runs:

```bash
gradle :app:assembleDebug
```

Release builds are serialized. Every user-facing update increments the app version, updates the README current-version link, builds and uploads the APK, publishes the versioned APK into the repository, and creates or updates the matching official GitHub Release.

See `DEVELOPMENT.md` for the release definition of done.
