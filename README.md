# First Edition Companion

A dependency-light Android companion for tracking an AD&D 1st Edition character during play.

## Current version: v0.9.0

[Download the official v0.9.0 APK](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/download/v0.9.0/FirstEditionCompanion-v0.9.0.apk)

[View the latest GitHub Release](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/latest)

### Features
- Named character profiles with save/load/new/delete
- Portable JSON **Export Saves / Import Saves** with Merge and Restore/Replace
- New **guided character-creation foundation** launched from **New Character**
- Character-creation draft is separate from the active character and can be resumed, kept, or discarded safely
- Wizard **Back** navigation allows race, class, and ability choices to be revisited without losing the rest of the draft
- Race/Subrace choice drives class availability while **all classes remain visible** for learning and details
- Unavailable classes are clearly marked but still expose full **Class Details**, allowing a player to go back and choose a compatible race
- Ability-score step supports the four DMG generation methods plus manual entry, with racial modifiers applied from stored raw scores exactly once
- Character-sheet **Race / Subrace dropdown** with native PHB and supplied-UA racial reference data
- Immediate racial ability-adjustment summary plus detailed **Race Details**
- Character-sheet **Class / Subclass dropdown** with native PHB and supplied-UA-variant class reference data
- Immediate class requirements, hit die, alignment, and source summary plus detailed **Class Details**
- Native **Multi-class / Dual-class Rules** reference and preserved custom/legacy class strings
- Structured Gear page with Equipped, Carried Gear, Currency, Magic Items, Valuables & Treasure, and Mounts/Tack/Transport
- Searchable equipment and magic-item catalogs with source labels
- Equipment slots, quantities, optional charges/uses, and personal item notes
- PHB cp/sp/ep/gp/pp currency tracking with a GP-equivalent total
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

## Guided character creation

v0.9.0 begins the dedicated character-creation workflow. **New Character** now opens a separate creation activity instead of immediately clearing the current working sheet.

The current foundation contains four screens:
1. **Race / Subrace** — choose from the existing native race catalog and open Race Details.
2. **Class / Subclass** — every built-in class remains visible. Compatible classes are marked available; incompatible classes are marked unavailable but still allow Class Details to be opened.
3. **Ability Scores** — roll with DMG Methods I–IV or enter scores manually. The draft stores raw scores separately from final racial-adjusted scores and checks the currently encoded class minimum requirements.
4. **Draft Review** — review the current race, class, and final scores, then save the draft and return to the normal app.

This first v0.9.0 slice deliberately does **not** commit a partially built character into the active sheet. The active character remains untouched until the later wizard steps—age, alignment, languages, proficiencies/secondary skills, HP, class skills, spell setup, money, equipment, derived combat values, optional personality/background, and final review—are implemented and can be committed atomically.

### Backtracking and changing decisions

The creator is not a one-way questionnaire. The user can go Back and change earlier decisions. Race changes re-evaluate class availability and racial score adjustments from the stored raw ability scores; modifiers are not stacked a second time. A previously chosen class can remain selected so the app can explain that it is now unavailable instead of silently deleting the player's choice.

## Character creation / race workflow

The normal Sheet Race field remains a source-aware dropdown. Built-in PHB choices include Human, standard/hill and mountain Dwarves, High Elves, Surface Gnomes, Half-Elves, Half-Orcs, and Halfling variants including Hairfoot, Stout, Tallfellow, and an unspecified/mixed PHB baseline.

The supplied Unearthed Arcana variant expands the selector with Gray/Duergar Dwarves, Gray, Wood/Sylvan, Wild/Grugach, and Dark/Drow Elves, Deep/Svirfneblin Gnomes, several Half-Elf ancestry variants, and the Half-Ogre. Drow are split into male and female entries because the supplied variant gives them different starting ability adjustments.

Selecting a race updates the short **Ability adjustments** line immediately. **Race Details** opens the complete native racial summary. Existing/custom race strings are preserved rather than overwritten.

## Character creation / class workflow

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

The supplied UA variant references some additional class names such as Barbarian and Cavalier in racial-access and magic-item text, but it does not provide full standalone class descriptions for them in the supplied file. The Companion therefore does **not** fabricate unsupported mechanics for those names.

On the normal Sheet, selecting a built-in class shows its **requirements / prime requisite**, **hit die**, **alignment restriction**, and source. **Class Details** opens a native offline summary covering armor and weapons, spellcasting model, major class abilities, high-level abilities/restrictions, and important advancement notes.

A separate **Multi-class Rules** button summarizes the PHB distinction between nonhuman multiclassing and human dual-classing, including experience splitting, hit-point handling, equipment restrictions, and the high ability-score requirements for changing class.

Normal-sheet class selection deliberately does not rewrite stored HP, THAC0, saving throws, alignment, spell tracks, or ability scores. Guided automation is confined to the separate new-character draft so existing characters cannot be damaged simply by opening or changing a selector.

## Source-grounded rules

The supplied AD&D 1e *Players Handbook* remains the primary player-facing source for core races, classes, spells, and character-creation rules. The supplied *Dungeon Masters Guide* supplies the ability-generation methods and the broader DM-facing mechanics layer.

### Unearthed Arcana source integrity

The supplied `unearthedarcana.pdf` contains later/conversion-style terminology and mechanics. UA entries are part of the app's AD&D 1e content group but remain visibly source-labeled so variant mechanics are not silently substituted for verified PHB/DMG core rules.

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

Official builds from v0.4.0 onward use the same stable prototype signing key, so v0.9.0 should install directly over v0.8.1 and preserve app-local data.

## Build and release policy

The repository is standalone. GitHub Actions installs Gradle 9.7.1, uses JDK 17, decodes the stable prototype signing key, and runs:

```bash
gradle :app:assembleDebug
```

Release builds are serialized. Every user-facing update increments the app version, updates the README current-version link, builds and uploads the APK, publishes the versioned APK into the repository, and creates or updates the matching official GitHub Release.

See `DEVELOPMENT.md` for the release definition of done.
