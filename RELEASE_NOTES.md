# First Edition Companion v0.9.3

v0.9.3 fixes the remaining Sheet-screen regression visible in v0.9.2.

## Profile-first empty state
- When no real character is active, the Sheet tab now shows the **Character Profiles** area and a short **Ready to Begin** message only.
- The old default/blank Human Fighter character sheet no longer appears underneath the creation controls.
- **Create New Character** still launches the separate guided creator.
- **Load Character** brings an existing saved character into the active play sheet.
- **Save Character** is disabled while there is no active character.
- Once a meaningful active character is loaded, the normal editable character sheet appears as the play screen.

## Version confirmation
- The main title bar shows **v0.9.3**.
- The Character Profiles card shows **App Version: v0.9.3**.
- The guided creator title shows **v0.9.3**.

## Character safety
- Existing saved characters are not deleted or rewritten by this empty-state change.
- In-progress guided-character drafts remain separate from the active character.
- The creator still stops at Draft Review until the remaining creation steps and final atomic **Finish Character** commit are implemented.

## Updating
v0.9.3 uses the same stable prototype signing key and should install directly over v0.9.2 while preserving local data. Export Saves before major updates is still recommended.

## Installation
Download the attached `FirstEditionCompanion-v0.9.3.apk` and install it on Android.
