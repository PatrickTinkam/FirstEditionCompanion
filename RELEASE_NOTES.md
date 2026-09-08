# First Edition Companion v0.9.0

v0.9.0 begins the dedicated guided character-creation workflow while keeping existing characters protected.

## Guided creation foundation
- **New Character** now opens a separate full-screen creation activity instead of immediately clearing the working sheet.
- Added a persistent `CharacterDraft` so in-progress creation is isolated from the active character.
- Added Back navigation and draft resume/keep/discard handling.
- Race selection uses the existing native race catalog and keeps **Race Details** available.
- The class screen keeps **every built-in class visible**. Classes unavailable to the selected race are clearly marked but still expose **Class Details**, so a player can learn about the class and go back to choose a compatible race.
- Changing race re-evaluates class availability instead of silently deleting the previous class choice.
- Added machine-readable racial ability modifiers and core class minimum-score validation for the wizard.
- Ability scores are stored as raw values separately from final racial-adjusted values so racial modifiers cannot be accidentally applied twice.
- Added an obvious **ROLL** workflow supporting all four DMG character ability-generation methods plus manual entry.
- DMG Method IV displays twelve complete generated sets and lets the player choose one.
- Added a foundation review screen showing race, class, and final ability scores.

## Safety / compatibility
- v0.9.0 deliberately stops before committing a partially built character. The existing active character remains untouched while the remaining creation steps are added.
- Existing v0.8.1 character profiles, spell tracks, inventory, and normal Sheet selectors remain compatible.
- The normal Sheet still permits manual editing and does not silently recalculate existing characters.

## Next creation slices
The next guided-creation updates will extend the same draft with age, alignment, languages, weapon proficiencies, secondary skills, health, class-specific skills, spell setup, starting money, equipment, derived combat values, the optional personality/background section, and the final **Finish Character** commit into the normal sheet.

## Updating
v0.9.0 uses the same stable prototype signing key as previous prototype releases and should install directly over v0.8.1 while preserving local character data. Export Saves before major updates is still recommended.

## Installation
Download the attached `FirstEditionCompanion-v0.9.0.apk` and install it on Android.
