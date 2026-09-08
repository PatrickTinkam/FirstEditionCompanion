# First Edition Companion

A dependency-light Android companion for tracking an AD&D 1st Edition character during play.

## Current version: v0.9.3

[Download the official v0.9.3 APK](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/download/v0.9.3/FirstEditionCompanion-v0.9.3.apk)

[View the latest GitHub Release](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/latest)

### Features
- Named character profiles with save/load/new/delete
- Profile-first Sheet screen: when no real character is active, the old blank/default character sheet is hidden
- Prominent **Create New Character** button launches the separate guided creator
- **App Version** is shown directly in the Character Profiles card for quick installed-build confirmation
- Portable JSON **Export Saves / Import Saves** with Merge and Restore/Replace
- Guided character-creation draft is isolated from the active character and can be resumed, kept, or discarded safely
- Wizard **Back** navigation allows earlier choices to change while later choices are retained and revalidated
- Race/Subrace choice drives class availability while all classes remain visible for learning and details
- Unavailable classes remain visible and still expose full **Class Details**
- Ability-score step supports DMG Methods I–IV plus manual entry, with raw scores kept separately so modifiers cannot be applied twice
- DMG-based **Age** step with source-table rolls, manual override, age categories, cumulative aging modifiers, and post-age class requirement checks
- **Alignment** step keeps all nine alignments visible and marks class-incompatible choices unavailable rather than hiding them
- Source-aware **Languages** step with automatic racial/class/alignment languages and Intelligence-based additional-language capacity
- Native PHB and supplied-UA race/class reference data
- Native **Multi-class / Dual-class Rules** reference and preserved custom/legacy class strings
- Structured Gear page with Equipped, Carried Gear, Currency, Magic Items, Valuables & Treasure, and Mounts/Tack/Transport
- Searchable equipment and magic-item catalogs with source labels
- Searchable Cleric, Druid, Magic-User, and Illusionist spell catalogs
- Magic-User/Illusionist spellbook workflow with learned spells separate from prepared copies
- Clerics automatically receive their full built-in Cleric spell list, grouped by spell level, and prepare directly from that list
- Native/offline spell, gear, treasure, and magic-item descriptions/mechanics
- Expanded Rules tab covering common PHB/DMG play references
- Combat dashboard with HP, AC, THAC0 helper, attack/damage rolls, saves, and rules references
- Integrated dice roller
- Responsive navigation for folded and unfolded phones

## Guided character creation

**Create New Character** opens a separate full-screen creation activity instead of editing a blank sheet in place.

The current wizard contains seven screens:
1. **Race / Subrace** — choose from the native race catalog and open Race Details.
2. **Class / Subclass** — every built-in class remains visible; unavailable choices are marked but still allow Class Details.
3. **Ability Scores** — roll with DMG Methods I–IV or enter scores manually; raw values are stored separately from adjusted values.
4. **Age** — roll from the DMG race/class starting-age table where supported, or use a manual/campaign age; age category and cumulative aging modifiers are shown.
5. **Alignment** — all nine alignments remain visible and are marked available/unavailable according to class.
6. **Languages** — automatic languages plus additional-language capacity based on final Intelligence and racial restrictions.
7. **Draft Review** — review race, class, age, alignment, final scores, and languages, then save the draft and return.

The creator is not a one-way questionnaire. Going Back preserves later choices where possible and revalidates them instead of silently deleting them.

v0.9.3 still deliberately does **not** commit a partially built character into the active play sheet. The active character remains untouched until the remaining wizard steps—weapon proficiencies, secondary skills, health, class skills, spell setup, money, equipment, optional personality/background, derived combat values, and final review—are complete and can be committed atomically.

## Sheet behavior

When no real character is active, the Sheet tab shows only the Character Profiles / creation area and a short empty-state message. The legacy default Human Fighter sheet is not displayed. After a saved character is loaded—or once the guided creator eventually finishes and commits a new character—the normal editable character sheet appears as the play screen.

## Source-grounded rules

The supplied AD&D 1e *Players Handbook* is the primary player-facing source for core races, classes, spells, languages, alignments, and character-operation rules. The supplied *Dungeon Masters Guide* supplies ability-generation methods, starting-age tables, aging rules, treasure, magic-item, and broader DM-facing mechanics.

The supplied `unearthedarcana.pdf` is included as part of the app's AD&D 1e content group. Where its supplied mechanics use later/conversion-style terminology or differ from verified PHB/DMG core rules, entries remain visibly source-labeled rather than silently replacing core rules.

PDF linking is optional and only for source-page verification. The Companion is intended to remain useful offline without linked rulebooks; PDFs are not bundled in the APK.

## Spell workflow

For a Magic-User or Illusionist, browse the class catalog, add learned/discovered spells to the Spellbook, prepare copies, mark copies Used when cast, and restore after rest as appropriate.

For a Cleric, the full built-in Cleric spell list is available automatically, grouped by spell level, and spells are prepared directly from that class list.

## Backups and updates

Use **Export Saves** before major updates or moving devices. Backups include current-character data, saved profiles, structured inventory/currency, spellbooks, prepared spells, gear notes, and combat values.

Official builds from v0.4.0 onward use the same stable prototype signing key, so v0.9.3 should install directly over v0.9.2 while preserving app-local data.

## Build and release policy

The repository is standalone. GitHub Actions uses JDK 17 and runs:

```bash
gradle :app:assembleDebug
```

Every user-facing update increments the app version, updates the README and release notes, builds the APK, publishes it into the repository, and creates or updates the matching GitHub Release. See `DEVELOPMENT.md` for the release definition of done.
