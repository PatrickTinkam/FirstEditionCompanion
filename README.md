# First Edition Companion

A dependency-light Android companion for tracking an AD&D 1st Edition character during play.

## Current version: v0.9.2

[Download the official v0.9.2 APK](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/download/v0.9.2/FirstEditionCompanion-v0.9.2.apk)

[View the latest GitHub Release](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/latest)

### Features
- Named character profiles with save/load/new/delete
- Prominent **Create New Character** button at the top of the Sheet launches the separate guided creator
- **App Version** is shown directly in the Character Profiles card for quick installed-build confirmation
- Portable JSON **Export Saves / Import Saves** with Merge and Restore/Replace
- Dedicated **guided character creation** launched from **Create New Character**
- Character-creation draft is separate from the active character and can be resumed, kept, or discarded safely
- Wizard **Back** navigation allows earlier choices to be changed while later choices are retained and revalidated
- Race/Subrace choice drives class availability while **all classes remain visible** for learning and details
- Unavailable classes are clearly marked but still expose full **Class Details**
- Ability-score step supports the four DMG generation methods plus manual entry, with raw scores stored separately so modifiers cannot be applied twice
- DMG-based **Age** step with source-table rolls, manual override, age categories, cumulative aging modifiers, and post-age class requirement checks
- **Alignment** step keeps all nine alignments visible and marks class-incompatible choices unavailable rather than hiding them
- Source-aware **Languages** step with automatic racial/class/alignment languages and Intelligence-based additional-language capacity
- Character-sheet **Race / Subrace dropdown** with native PHB and supplied-UA racial reference data
- Character-sheet **Class / Subclass dropdown** with native PHB and supplied-UA-variant class reference data
- Native **Multi-class / Dual-class Rules** reference and preserved custom/legacy class strings
- Structured Gear page with Equipped, Carried Gear, Currency, Magic Items, Valuables & Treasure, and Mounts/Tack/Transport
- Searchable equipment and magic-item catalogs with source labels
- Equipment slots, quantities, optional charges/uses, and personal item notes
- PHB cp/sp/ep/gp/pp currency tracking with a GP-equivalent total
- Searchable Cleric, Druid, Magic-User, and Illusionist spell catalogs
- Magic-User/Illusionist **Spellbook** workflow with learned spells separated from prepared/memorized copies
- Clerics automatically receive their full built-in Cleric spell list, grouped by spell level, and prepare directly from that list
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

v0.9.2 restores the visible **Character Profiles** controls that were accidentally hidden by the v0.9.x Sheet override. The top of the Sheet now contains **Create New Character**, **Save Character**, **Load Character**, and **Delete Saved Character** controls. **Create New Character** opens the separate guided creation activity instead of clearing or editing the normal working sheet in place.

The current wizard contains seven screens:
1. **Race / Subrace** — choose from the native race catalog and open Race Details.
2. **Class / Subclass** — every built-in class remains visible. Compatible classes are marked available; incompatible classes stay visible and still allow Class Details.
3. **Ability Scores** — roll with DMG Methods I–IV or enter scores manually. Raw scores are kept separately from adjusted values.
4. **Age** — roll from the DMG race/class starting-age table where a source formula exists, or use a manual/campaign age. The app shows age category, cumulative aging modifiers, and the resulting final scores.
5. **Alignment** — all nine alignments remain visible and are marked available/unavailable according to the chosen class. The original 1e alignment-language rule is available as a default-on toggle, and divine classes may record a deity/patron.
6. **Languages** — automatic languages are assembled from race, class, and alignment settings. Additional-language capacity uses final Intelligence plus the race's own PHB restrictions; supplied-UA background-skill language lists remain visibly source-separated.
7. **Draft Review** — review the current race, class, age, alignment, final scores, and languages, then save the draft and return to the normal app.

v0.9.2 still deliberately does **not** commit a partially built character into the active sheet. The active character remains untouched until the remaining wizard steps—weapon proficiencies, secondary skills, health, class skills, spell setup, money, equipment, optional personality/background, derived combat values, and final review—are implemented and can be committed atomically.

### Backtracking and changing decisions

The creator is not a one-way questionnaire. The user can go Back and change earlier decisions. Race changes re-evaluate class availability and racial score adjustments; class changes re-evaluate later requirements; age changes recalculate final scores; alignment and language choices are preserved and rechecked where possible. The wizard flags invalid downstream choices rather than silently deleting them.

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

The supplied AD&D 1e *Players Handbook* remains the primary player-facing source for core races, classes, spells, languages, alignments, and character-operation rules. The supplied *Dungeon Masters Guide* supplies the ability-generation methods, starting-age tables, aging rules, and broader DM-facing mechanics layer.

### Unearthed Arcana source integrity

The supplied `unearthedarcana.pdf` is included as part of the app's AD&D 1e content group. Where its supplied mechanics use later/conversion-style terminology or differ from verified PHB/DMG core rules, those entries remain visibly source-labeled rather than silently replacing the core path.

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

Official builds from v0.4.0 onward use the same stable prototype signing key, so v0.9.2 should install directly over v0.9.1 and preserve app-local data.

## Build and release policy

The repository is standalone. GitHub Actions installs Gradle 9.7.1, uses JDK 17, decodes the stable prototype signing key, and runs:

```bash
gradle :app:assembleDebug
```

Release builds are serialized. Every user-facing update increments the app version, updates the README current-version link, builds and uploads the APK, publishes the versioned APK into the repository, and creates or updates the matching official GitHub Release.

See `DEVELOPMENT.md` for the release definition of done.
