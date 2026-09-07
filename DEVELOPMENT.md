# Development and Release Policy

## Required after every user-facing update
1. Increment `versionCode` in `app/build.gradle`.
2. Increment `versionName` in `app/build.gradle` using the next project version.
3. Update `README.md` so the current version and download information are accurate.
4. Replace `RELEASE_NOTES.md` with notes for the new version.
5. Push the update to `main` and require the Android build to pass.
6. The GitHub Actions workflow must publish the APK to `releases/` and create or update the matching official GitHub Release with the APK attached.
7. Verify the GitHub Release exists and the APK asset is downloadable before considering the update complete.

The release tag is derived from `versionName` (with the `-prototype` suffix removed), so `0.4.0-prototype` becomes GitHub Release `v0.4.0`.

Do not treat an update as complete merely because source code was committed. A successful APK build and matching GitHub Release are part of the definition of done for this project.
