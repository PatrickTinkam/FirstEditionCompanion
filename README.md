# First Edition Companion

A dependency-light Android companion for tracking an AD&D 1st Edition character during play.

## Current version: v0.8.1

[Download the official v0.8.1 APK](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/download/v0.8.1/FirstEditionCompanion-v0.8.1.apk)

[View the latest GitHub Release](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/latest)

### Features
- Named character profiles with save/load/new/delete
- Portable JSON **Export Saves / Import Saves** with Merge and Restore/Replace
- Character-sheet **Race / Subrace dropdown** with native PHB and supplied-UA racial reference data
- Immediate racial ability-adjustment summary plus detailed **Race Details**
- Character-sheet **Class / Subclass dropdown** with native PHB and supplied-UA-variant class reference data
- Immediate class requirements, hit die, alignment, and source summary plus detailed **Class Details**
- Native **Multi-class / Dual-class Rules** reference and preserved custom/legacy class strings
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
- Native/offline descriptions for nonmagical PHB/DMG/UA gear and treasure entries
- Expanded **Rules** tab covering PHB and DMG spellcasting, initiative, surprise, saving throws, item saves, weapons, armor, turning undead, treasure, potion/scroll/ring/device handling, charges, cursed items, artifacts, movement, experience, and more
- Optional local PHB/UA/DMG PDF linking for source-page verification only; PDFs are not required to use the reference
- Combat dashboard with HP, AC, THAC0 helper, attack/damage rolls, saves, and rules references
- Integrated dice roller
- Responsive navigation for folded and unfolded phones

## Character creation / race workflow

The Race field is a source-aware dropdown. Built-in PHB choices include Human, standard/hill and mountain Dwarves, High Elves, Surface Gnomes, Half-Elves, Half-Orcs, and Halfling variants including Hairfoot, Stout, Tallfellow, and an unspecified/mixed PHB baseline.

The supplied Unearthed Arcana variant expands the selector with Gray/Duergar Dwarves, Gray, Wood/Sylvan, Wild/Grugach, and Dark/Drow Elves, Deep/Svirfneblin Gnomes, several Half-Elf ancestry variants, and the Half-Ogre. Drow are split into male and female entries because the supplied variant gives them different starting ability adjustments.

Selecting a race updates the short **Ability adjustments** line immediately. **Race Details** opens the complete native racial summary. Existing/custom race strings are preserved rather than overwritten.

## Character creation / class workflow

v0.8.1 replaces the old free-form **Class(es)** field with a source-aware **Class / Subclass** selector while keeping a **Custom / Multi-class…** path for existing or campaign-specific combinations.

Built-in PHB class choices are:
- Cleric
- Druid
- Fighter
- Paladin
- Ranger
- Magic-User
- Illusionist
- Thief
- Assassin
- Monk
- Bard (PHB Appendix II optional class)

The supplied UA-variant source adds the five fully described classes contained in that file:
- Cloistered Cleric
- Anti-Paladin
- Duelist
- Necromancer
- Psionicist

The supplied UA variant references some additional class names such as Barbarian and Cavalier in racial-access and magic-item text, but it does not provide full standalone class descriptions for them in the supplied file. v0.8.1 therefore does **not** fabricate unsupported mechanics for those names.

Selecting a built-in class immediately shows its **requirements / prime requisite**, **hit die**, **alignment restriction**, and source. **Class Details** opens a native offline summary covering armor and weapons, spellcasting model, major class abilities, high-level abilities/restrictions, and important advancement notes.

A separate **Multi-class Rules** button summarizes the PHB distinction between nonhuman multiclassing and human dual-classing, including experience splitting, hit-point handling, equipment restrictions, and the high ability-score requirements for changing class.

Class selection deliberately does not yet rewrite stored HP, THAC0, saving throws, alignment, spell tracks, or ability scores. Existing characters therefore cannot be damaged simply by opening or changing the selector.

## Source-grounded rules

The supplied AD&D 1e *Players Handbook* remains the primary player-facing source for core races, classes, spells, and character-creation rules. PHB class material in v0.8.1 includes ability requirements, hit dice, alignment rules, armor/weapon restrictions, spellcasting, class abilities, and multiclass/dual-class procedures.

The supplied *Dungeon Masters Guide* fills the broader rules/mechanics layer for treasure, magic items, combat adjudication, saving throws, charges, curses, artifact handling, and related referee procedures.

### Unearthed Arcana source integrity

The `unearthedarcana.pdf` supplied to this project contains later/conversion-style terminology and mechanics. UA-only race, class, and catalog entries remain available but are explicitly treated as **supplied UA variant** material. The app does not silently replace verified PHB/DMG mechanics with those later mechanics.

### Optional source PDFs

PDF linking is explicitly optional. The Companion is intended to remain useful offline without linked rulebooks. Linking a legally owned PDF simply allows source pages to be opened for verification. The PDFs themselves are not committed to the repository or bundled in the APK.

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

## Backups and updates

Use **Export Saves** before major updates or moving devices. Backups include the current character, saved profiles, structured inventory/currency, spellbooks, prepared spells, gear notes, and combat values.

Official builds from v0.4.0 onward use the same stable prototype signing key, so v0.8.1 should install directly over v0.8.0 and preserve app-local data.

## Build and release policy

The repository is standalone. GitHub Actions installs Gradle 9.7.1, uses JDK 17, decodes the stable prototype signing key, and runs:

```bash
gradle :app:assembleDebug
```

Release builds are serialized. Every user-facing update increments the app version, updates the README current-version link, builds and uploads the APK, publishes the versioned APK into the repository, and creates or updates the matching official GitHub Release.

See `DEVELOPMENT.md` for the release definition of done.
