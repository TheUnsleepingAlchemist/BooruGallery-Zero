# BooruGallery-Zero

BooruGallery-Zero is a gallery application that allows you to browse and view media assets from external web resources. It connects directly to online imageboards to stream images, animated GIFs, and videos directly to your device.

## Features

- **Browse Web Media:** Explore thousands of online media assets seamlessly within a unified interface.
- **Full-Screen Previews:** View high-quality images, watch videos, and play animated GIFs smoothly*.
- **Customizable Layouts:** Switch easily between grid configurations and list views depending on how you prefer to browse.
- **Persistent Preferences:** The application remembers your viewing preferences and settings automatically.

## Building

The app uses Gradle and requires the Android SDK. We recommend using Android Studio, which includes all required dependencies, for development and building. For manual building without Android Studio make sure a compatible JDK and Android SDK are installed and in your PATH, then use the Gradle wrapper (./gradlew) to build the project with the assembleDebug Gradle task to generate an apk file:

./gradlew assembleDebug

The task will create an APK file in the /app/build/outputs/apk/debug directory. This APK file uses a different app-id from our stable builds and can be manually installed to your device.

## Testing & Previewing

This app does not currently include automated tests. Instead, it has been verified manually by running it directly on screens and virtual devices.

To run and preview the app yourself:
1. Open the project inside [Android Studio](https://developer.android.com/studio).
2. Set up a virtual device by following the official [Android Emulator guide](https://developer.android.com/studio/run/emulator).
3. Click the green **Run** button (play icon) at the top of Android Studio to launch the app inside the emulator or on a connected phone.

## Contact
E-mail: TheUnsleepingAlchemist@proton.me 