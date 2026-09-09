# Development and Release Policy

## Required after every user-facing update
1. Increment `versionCode` in `app/build.gradle`.
2. Increment `versionName` in `app/build.gradle` using the next project version.
3. Update `README.md` so the **Current version** heading, direct APK download link, relevant feature text, and update/install wording all reference the new version before the change is considered complete.
4. Replace `RELEASE_NOTES.md` with notes for the new version.
5. Push the update to `main` and require the Android build to pass.
6. The GitHub Actions workflow must publish the APK to `releases/` and create or update the matching official GitHub Release with the APK attached.
7. Verify the GitHub Release exists and the APK asset is downloadable before considering the update complete.
8. In the user-facing completion message, always provide a direct download link to the newly built APK so it can be installed and tested immediately.

The release tag is derived from `versionName` (with the `-prototype` suffix removed), so `0.4.0-prototype` becomes GitHub Release `v0.4.0`.

Do not treat an update as complete merely because source code was committed. A successful APK build, synchronized README current-version information, matching GitHub Release, and direct APK test link are all part of the definition of done for this project.

## Rules warning / error UI policy
Any user-facing warning, error, restriction, or failed rules validation that comes from AD&D mechanics must provide a nearby **Why? / Source Details** affordance whenever a source-grounded explanation exists.

The source-details view must:
- Explain the character-specific reason the warning is appearing.
- Identify the governing source (PHB, DMG, supplied UA variant, or clearly labeled campaign/house rule).
- Include the relevant section and a verified page number when one is known.
- Preserve exact mechanical facts such as minimum scores, class/race restrictions, limits, percentages, dice, and level thresholds.
- Use concise native paraphrase rather than reproducing long copyrighted passages verbatim; a brief source excerpt may be included when useful.
- Remain fully usable offline and never require opening a linked PDF to understand the warning.
- Explicitly flag source ambiguity or disagreement instead of inventing or silently reconciling a rule.

This pattern applies to character creation and to future rules-aware warnings in proficiencies, spells, equipment, magic items, combat, advancement, and other app areas.

## Randomness / dice policy
All user-facing random outcomes must use the shared `DiceRng` service. Do not create a new `Random`, call `Math.random()`, or reseed a generator inside a roll action.

The shared RNG policy applies to:
- Generic dice expressions and quick-die buttons.
- Attack and saving-throw rolls.
- Damage rolls.
- Character-creation ability generation.
- Starting age and other random character-generation tables.
- Future hit-point, treasure, encounter, item-effect, or other random-table mechanics.

`DiceRng` uses one OS-seeded `SecureRandom` instance and bounded `nextInt` calls so legal die faces are selected uniformly without modulo bias or time-based repeat patterns. The Dice screen includes a runtime self-check that samples all standard dice and verifies that every legal face is reachable.
