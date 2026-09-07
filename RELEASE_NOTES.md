# First Edition Companion v0.4.0

This release adds portable character backups and makes future prototype updates much safer.

## Highlights
- Added **Export Saves** to write a portable JSON backup file using Android's system file picker.
- Backups include the current working character plus every named saved character profile.
- Character backups preserve stats, HP/AC/THAC0, saving throws, equipment, notes, casting tracks, Spellbooks/Available Spells, prepared spells, and used/restored state.
- Added **Import Saves** with two restore modes:
  - **Restore / Replace** replaces local character saves with the selected backup.
  - **Merge** keeps existing characters and imports backup profiles under unique names.
- Added compatibility with the older clipboard JSON backup format from v0.1-v0.3.
- Added a stable prototype signing key for CI builds beginning with v0.4.0 so future APK updates can install over one another and preserve Android app data normally.

## Important v0.3 → v0.4 note
Older prototype APKs were signed with temporary CI debug keys. Android may therefore require uninstalling v0.3 before installing v0.4, which would erase local app data. If you have important v0.3 data, use the existing **Copy Character Backup to Clipboard** feature before uninstalling and save that JSON somewhere safe (for example, in a note). v0.4 can import that legacy backup text through **Import Legacy Clipboard Backup**.

Beginning with v0.4.0, official prototype releases use the same stable signing key, so normal in-place updates should preserve local data. Exported backups remain recommended before major updates or moving to another device.

## Installation
Download the attached `FirstEditionCompanion-v0.4.0.apk` from this release and install it on Android.

This remains a field-test prototype. The stable signing key is intentionally a prototype/development key and is not intended as production Play Store signing infrastructure.
