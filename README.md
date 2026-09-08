# First Edition Companion

A dependency-light Android companion for tracking an AD&D 1st Edition character during play.

## Current version: v0.7.1

[Download the official v0.7.1 APK](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/download/v0.7.1/FirstEditionCompanion-v0.7.1.apk)

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
- Native/offline descriptions for nonmagical PHB/DMG/UA gear and treasure entries, including gems, jewelry, art objects, manuscripts, precious-metal treasure, armor, weapons, adventuring gear, transport, provisions, religious items, and spellbooks
- Expanded **Rules** tab covering PHB and DMG spellcasting, initiative, surprise, saving throws, item saves, weapons, armor, turning undead, treasure, potion/scroll/ring/device handling, charges, cursed items, artifacts, movement, experience, and more
- Optional local PHB/UA/DMG PDF linking for source-page verification only; PDFs are not required to use the reference
- Combat dashboard with HP, AC, THAC0 helper, attack/damage rolls, saves, and rules references
- Integrated dice roller
- Responsive navigation for folded and unfolded phones

## Source-grounded rules

v0.7.1 fixes a v0.7.0 routing bug that allowed nonmagical DMG/UA catalog entries to fall back to an obsolete “source PDF required” message. Every built-in gear and treasure entry now routes through a native offline description path.

The supplied AD&D 1e *Players Handbook* remains the primary player-facing spell and class source. PHB spell entries retain the book's structured fields—type/school, range, duration, area of effect, components, casting time, saving throw, reversibility, and source page—plus a native offline effect digest.

The supplied *Dungeon Masters Guide* fills the large rules/mechanics layer for treasure, magic items, combat adjudication, saving throws, charges, curses, artifact handling, and related referee procedures. DMG gems and jewelry use their value-generation concepts directly in the built-in summaries; synthetic tracking categories such as Art Object clearly state when the books do not define one universal stat block and instead require a DM-assigned/appraised value.

Magic items show native mechanics directly. Named items with dedicated entries show their special behavior; remaining catalog entries receive category-specific 1e handling instead of a generic missing-description message. Standard enchanted armor and weapons explain how their pluses apply, while potions, scrolls, rings, charged devices, cursed items, and artifacts use their appropriate sourcebook handling.

### Unearthed Arcana source integrity

The `unearthedarcana.pdf` supplied to this project contains later/conversion-style terminology and mechanics—including systems not presented as original 1985 AD&D 1e rules. UA-only catalog entries remain available but are explicitly treated as **supplied UA variant** material. The app does not silently replace verified PHB/DMG mechanics with those later mechanics.

### Optional source PDFs

PDF linking is explicitly optional. The Companion is intended to remain useful offline without linked rulebooks. Linking a legally owned PDF simply allows source pages to be opened for verification.

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

Tap a catalog or recorded item and use its **Rules** action to see its native offline description/mechanics and source note. Personal item notes remain separate from the built-in rules text.

## Backups and updates

Use **Export Saves** before major updates or moving devices. Backups include the current character, saved profiles, structured inventory/currency, spellbooks, prepared spells, gear notes, and combat values.

Official builds from v0.4.0 onward use the same stable prototype signing key, so v0.7.1 should install directly over v0.7.0 and preserve app-local data.

## Build and release policy

The repository is standalone. GitHub Actions installs Gradle 9.7.1, uses JDK 17, decodes the stable prototype signing key, and runs:

```bash
gradle :app:assembleDebug
```

Release builds are serialized. Every user-facing update increments the app version, updates the README current-version link, builds and uploads the APK, publishes the versioned APK into the repository, and creates or updates the matching official GitHub Release.

See `DEVELOPMENT.md` for the release definition of done.
