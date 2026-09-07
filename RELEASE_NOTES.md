# First Edition Companion v0.3.0

This release cleans up spell management and makes Magic-User/Illusionist spell tracking behave more like an actual spellbook workflow.

## Highlights
- Added persistent **Spellbook** tracking for Magic-Users and Illusionists.
- Added persistent **Available Spells** tracking for Clerics and Druids.
- Class spell catalog entries are now added to the character's known/available collection first, rather than being prepared immediately.
- Prepare one or more memorized copies from the Spellbook/Available Spells list.
- Prepared copies continue to track Used/Restore state independently.
- Existing v0.2 prepared spells are migrated into the new known/available list when a casting track is opened.
- Custom and campaign-specific spells can be added to the known/available collection and prepared normally.
- Character save/load profiles remain intact and include spell tracks.

## Installation
Download the attached `FirstEditionCompanion-v0.3.0.apk` and install it on Android. This is currently a field-test prototype/debug-signed build.

The built-in PHB/Unearthed Arcana spell catalog uses spell names, source labels, and original concise summaries rather than reproducing copyrighted spell-description text.
