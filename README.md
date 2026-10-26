# Magnetic Launcher — early prototype

This is a small Android launcher scaffold intended for testing on Android devices such as a Galaxy S25.

## Current status
- Declares itself as a selectable Android Home app.
- Queries launchable apps and displays them in a basic grid.
- Tapping an app icon opens that app.
- Does **not** yet implement the Magnetic UI library, magnetic sensor-driven animation, widgets, or a complete app drawer experience.
- No APK is included; this is source code only.

## Build
Open this folder in Android Studio and let Gradle sync, then run the `app` configuration on an Android device. The GitHub Actions workflow can build a debug APK from the source repository after it is uploaded to GitHub.

This project intentionally does not assume an unverified third-party Magnetic UI library path. The earlier version referenced a speculative library module, which could cause a build failure.
