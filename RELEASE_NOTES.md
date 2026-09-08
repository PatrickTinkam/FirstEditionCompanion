# First Edition Companion v0.9.1

v0.9.1 extends guided character creation from Race/Class/Abilities through **Age, Alignment, and Languages**, while keeping the in-progress character isolated from the active sheet.

## Age
- Added the DMG starting-age formulas for supported human and nonhuman race/class paths, with an obvious **ROLL AGE** action and manual/campaign override.
- Added DMG age categories and cumulative aging ability adjustments.
- Age effects are recalculated from stored raw scores, so going Back and changing race/class cannot stack modifiers twice.
- Where the original DMG has no exact supplied-UA race/class entry, the app either labels a transparent parent/racial-stock baseline or requires a manual campaign age instead of inventing a rule.
- Final ability scores are rechecked against class requirements after age adjustments.

## Alignment
- Added all nine alignments as visible choices.
- Class-incompatible alignments remain visible but are clearly marked unavailable, matching the learning-oriented Race/Class design.
- Added PHB restrictions for Cleric, Druid, Paladin, Ranger, Thief, Assassin, Monk, Bard, and unrestricted core classes.
- Added visibly source-separated supplied-UA restrictions for Cloistered Cleric, Anti-Paladin, Duelist, Necromancer, and Psionicist.
- Added the original 1e alignment-language rule as a default-on campaign toggle.
- Divine classes can optionally record a deity/patron in the draft.

## Languages
- Added automatic racial languages plus class/alignment languages where applicable.
- Added additional-language capacity from final Intelligence, with PHB racial limits taking priority over the general Intelligence table.
- Added a multi-select additional-language picker and validation against available slots.
- Supplied-UA races that use background-skill-style language choices keep their source-specific language lists without the app fabricating a PHB-style numeric slot count.

## Backtracking and safety
- The wizard now contains seven screens: Race, Class, Ability Scores, Age, Alignment, Languages, and Draft Review.
- Back navigation continues to preserve later choices and revalidate them rather than silently deleting them.
- The active character remains untouched; this release still saves an in-progress `CharacterDraft` until the remaining creation steps and atomic **Finish Character** commit are implemented.
- Existing saved characters, spell tracks, inventory, and normal Sheet editing behavior remain unchanged.

## Next creation slices
Next: weapon proficiencies, secondary skills, health, class-specific skills, spell setup, starting money/equipment, optional personality/background, derived combat values, and the final review/Finish Character commit.

## Updating
v0.9.1 uses the same stable prototype signing key and should install directly over v0.9.0 while preserving local data. Export Saves before major updates is still recommended.

## Installation
Download the attached `FirstEditionCompanion-v0.9.1.apk` and install it on Android.
