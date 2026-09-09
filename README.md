# First Edition Companion

A dependency-light Android companion for tracking an AD&D 1st Edition character during play.

## Current version: v0.9.7

[Download the official v0.9.7 APK](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/download/v0.9.7/FirstEditionCompanion-v0.9.7.apk)

[View the latest GitHub Release](https://github.com/PatrickTinkam/FirstEditionCompanion/releases/latest)

### Features
- Named character profiles with save/load/new/delete
- Profile-first Sheet screen: when no real character is active, the old blank/default character sheet is hidden
- Prominent **Create New Character** button launches the separate guided creator
- **App Version** is shown directly in the Character Profiles card for quick installed-build confirmation
- Portable JSON **Export Saves / Import Saves** with Merge and Restore/Replace
- Guided character-creation draft is isolated from the active character and can be resumed, kept, or discarded safely
- Wizard **Back** navigation allows earlier choices to change while later choices are retained and revalidated
- Race/Subrace choice drives race/class compatibility while all classes remain visible for learning and details
- Class selection separates **race compatibility** from **ability-score qualification**
- Class minimum ability requirements are shown during class selection but enforced only after ability rolling/distribution
- DMG Methods I and II produce a persistent six-score pool that can be manually assigned with dropdowns
- Assigned pool values disappear from other ability selectors until returned/reassigned; duplicate rolls are tracked as separate copies
- **Auto Distribute for Class** optimizes the rolled pool for the selected class or recognized multiclass/dual-class combination, while still allowing manual changes afterward
- Method III results remain tied to their individual abilities and Method IV chosen sets remain in order
- Manual entry remains available and the live Class Qualification Check updates as scores change
- Raw scores are kept separately so racial and age modifiers cannot be applied twice
- **Why? • Source** buttons provide source-grounded explanations for race/class restrictions, class ability requirements, age checks, alignment restrictions, and language-capacity rules
- Source-detail dialogs identify the governing PHB/DMG/supplied-UA source, section, and verified page number where available
- All live random rolls now use one shared **OS-seeded SecureRandom** source with no per-roll reseeding
- Dice-tab **RNG Self-Check** samples d4, d6, d8, d10, d12, d20, and d100 and verifies that every legal face is reachable
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

## Randomness / dice audit

v0.9.7 sweeps every current random call site in the app. The live play screen and guided creator now both route through `DiceRng`, which uses one `java.security.SecureRandom` instance seeded by the Android/OS entropy source. Rolls use bounded `nextInt` calls, so dN results remain in the exact `1..N` range without modulo bias or time-based reseeding patterns.

The sweep covers:
- THAC0 attack d20 rolls
- Quick saving-throw d20 rolls
- Weapon/damage dice expressions such as `1d8+2`
- Quick d4/d6/d8/d10/d12/d20/d100 buttons
- DMG ability-generation Methods I–IV
- Starting-age dice
- Any creator helper that calls the shared die function

The underlying roll formulas were reviewed at the same time. Method I still rolls 4d6 and drops the single lowest die; Method II rolls twelve 3d6 totals and keeps the best six; Method III rolls six 3d6 attempts separately for each ability and keeps that ability's best result; Method IV generates twelve complete in-order 3d6 sets and lets the player choose one whole set.

The Dice tab now includes **Run RNG Self-Check**. It samples the standard dice used by the app and reports whether every legal face appeared. This is a runtime health check, not a mathematical proof of perfect randomness, but it provides an immediate way to verify that the installed build's RNG is functioning across the expected ranges.

## Guided character creation

**Create New Character** opens a separate full-screen creation activity instead of editing a blank sheet in place.

The current wizard contains seven screens:
1. **Race / Subrace** — choose from the native race catalog and open Race Details.
2. **Class / Subclass** — every built-in class remains visible. The screen checks only whether the chosen race can use that class; exact class ability requirements are displayed but are not treated as pass/fail yet.
3. **Ability Scores** — choose a DMG method or manual entry. Methods I and II create a six-score pool that can be assigned manually or auto-distributed for the selected class/class combination. After racial adjustments, the app performs the actual class qualification check and blocks Continue until the current requirements are met.
4. **Age** — roll from the DMG race/class starting-age table where supported, or use a manual/campaign age; age category and cumulative aging modifiers are shown and final class requirements are checked again.
5. **Alignment** — all nine alignments remain visible and are marked available/unavailable according to class.
6. **Languages** — automatic languages plus additional-language capacity based on final Intelligence and racial restrictions.
7. **Draft Review** — review race, class, age, alignment, final scores, and languages, then save the draft and return.

### Rules warnings and source details

Rules-sensitive creator screens expose a small **Why? • Source** button. It opens a native offline dialog explaining why the rule/check applies to the current character and identifies the governing book and source location. Current coverage includes race/class restrictions, class ability-score requirements, age/aging qualification checks, alignment restrictions, and additional-language capacity.

For core PHB/DMG rules, verified page references are included where available. Supplied-UA-variant material remains clearly labeled as variant. The app preserves exact mechanical facts while using concise native summaries instead of requiring the user to open a PDF.

### Class eligibility timing

The wizard deliberately separates two different 1e rules:
- **Race/class compatibility** is known as soon as race and class are selected. A race-restricted class remains visible for learning/details, but ability rolls cannot make an otherwise illegal race/class combination legal under core rules.
- **Ability-score qualification** is checked only after the player has rolled and distributed scores. The Class step shows the required minimums in advance so the player knows what scores to aim for.

Some nonhuman class level limits can also vary with ability scores. Those are advancement limits rather than a reason to reject an otherwise legal level-1 class choice during the Class step.

### Ability-score distribution

For **DMG Method I** and **DMG Method II**, the creator stores the six kept scores as a shared pool. Each ability has a dropdown showing only values that are still available. Assigning a value consumes one copy; changing or clearing the slot returns its previous value to the pool. Duplicate numerical results are counted separately.

The player can also press **Auto Distribute for Class**. The optimizer first tries to satisfy every encoded minimum after racial modifiers, then favors prime/principal abilities. If the stored selection represents multiple recognized classes, their requirements are combined so one class is not optimized at the expense of another. Auto Distribution never locks the result; every assignment can still be changed manually.

**Method III** remains ability-specific. **Method IV** remains a complete in-order set selection. **Manual Entry** allows free typing.

The creator is not a one-way questionnaire. Going Back preserves later choices where possible and revalidates them instead of silently deleting them.

v0.9.7 still deliberately does **not** commit a partially built character into the active play sheet. The active character remains untouched until the remaining wizard steps—weapon proficiencies, secondary skills, health, class skills, spell setup, money, equipment, optional personality/background, derived combat values, and final review—are complete and can be committed atomically.

## Sheet behavior

When no real character is active, the Sheet tab shows only the Character Profiles / creation area and a short empty-state message. The legacy default Human Fighter sheet is not displayed. After a saved character is loaded—or once the guided creator eventually finishes and commits a new character—the normal editable character sheet appears as the play screen.

## Source-grounded rules

The supplied AD&D 1e *Players Handbook* is the primary player-facing source for core races, classes, spells, languages, alignments, and character-operation rules. The supplied *Dungeon Masters Guide* supplies ability-generation methods, starting-age tables, aging rules, treasure, magic-item, and broader DM-facing mechanics.

The supplied `unearthedarcana.pdf` is included as part of the app's AD&D 1e content group. Where its supplied mechanics use later/conversion-style terminology or differ from verified PHB/DMG core rules, entries remain visibly source-labeled rather than silently replacing core rules.

PDF linking is optional and only for source-page verification. The Companion is intended to remain useful offline without linked rulebooks; PDFs are not bundled in the APK.

## Backups and updates

Use **Export Saves** before major updates or moving devices. Backups include current-character data, saved profiles, structured inventory/currency, spellbooks, prepared spells, gear notes, and combat values.

Official builds from v0.4.0 onward use the same stable prototype signing key, so v0.9.7 should install directly over v0.9.6 while preserving app-local data.

## Build and release policy

The repository is standalone. GitHub Actions uses JDK 17 and runs:

```bash
gradle :app:assembleDebug
```

Every user-facing update increments the app version, updates the README and release notes, builds the APK, publishes it into the repository, and creates or updates the matching GitHub Release. See `DEVELOPMENT.md` for the release definition of done.
