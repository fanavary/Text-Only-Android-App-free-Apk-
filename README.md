LightBrowser - minimal Android WebView app (Kotlin)
==================================================

What this is
------------
A minimal Android app (Kotlin) that runs a WebView and blocks media requests (images, audio, video, data: images) at the request interception level.

How to build
------------
You can build this project locally in Android Studio, or use a CI to build it server-side.

Local (Android Studio / AIDE / Termux)
- Open project in Android Studio and build.
- Or import in AIDE on-device.

CI (GitHub Actions)
- Create a GitHub repo and push this project.
- Add the included workflow `.github/workflows/android-build.yml` (example).
- The workflow installs Android SDK and runs Gradle to assembleDebug.

Notes
-----
- This repo intentionally does NOT include Gradle wrapper files. If you want the simplest GitHub Actions path, you can either:
  - add the Gradle wrapper (recommended: run `gradle wrapper` locally and commit), or
  - use the provided workflow which installs Gradle on the runner.

- You may want to tune the filtering logic (MIME checks, response headers) to avoid false positives on dynamic CDNs.

