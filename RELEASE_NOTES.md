# First Edition Companion v0.9.2

v0.9.2 fixes the missing entry point to the guided character creator and makes the installed app version easy to verify from inside the app.

## Character Profiles / creator entry
- Restored the **Character Profiles** controls that were accidentally hidden by the v0.9.x Sheet override.
- Added a prominent full-width **Create New Character** button at the top of the Sheet.
- **Create New Character** launches the separate guided character-creation activity instead of editing the normal Sheet in place.
- Restored visible **Save Character**, **Load Character**, and **Delete Saved Character** controls.
- Existing active-character data remains untouched while a creation draft is in progress.

## Visible version confirmation
- Added **App Version: v0.9.2** directly in the Character Profiles card on the Sheet.
- The main title bar and guided creator title also display v0.9.2.
- This gives testers a quick way to confirm that the newest APK is actually installed.

## Guided character creation
The current creator still contains seven steps: Race/Subrace, Class/Subclass, Ability Scores, Age, Alignment, Languages, and Draft Review. Backtracking, class availability markers, source-aware details, DMG age handling, alignment validation, and language handling from v0.9.1 are unchanged.

The wizard still deliberately stops at Draft Review and does not commit a partial character into the active sheet. The remaining creation steps and final atomic **Finish Character** commit will be added in later releases.

## Updating
v0.9.2 uses the same stable prototype signing key and should install directly over v0.9.1 while preserving local data. Export Saves before major updates is still recommended.

## Installation
Download the attached `FirstEditionCompanion-v0.9.2.apk` and install it on Android.
