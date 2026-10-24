# Pokemon KMP

A Pokémon app built with Kotlin Multiplatform and Compose Multiplatform. Runs on Android and iOS.

## Features

- Pokemon list from PokeAPI
- Pokemon details screen
- Navigation between screens
- Cross-platform UI with Compose Multiplatform
- Shared business logic

## Tech Stack

- Kotlin Multiplatform (KMP)
- Compose Multiplatform
- Kotlinx Serialization
- Ktor (HTTP client)
- Coil (image loading)
- Voyager / Navigation (Compose)

## Platforms

- Android
- iOS

## Getting Started

### Prerequisites
- Android Studio / IntelliJ IDEA
- JDK 17+
- Xcode (for iOS)
- Kotlin Multiplatform plugin

### Run on Android
`ash
./gradlew :composeApp:assembleDebug
`
Or run directly from Android Studio.

### Run on iOS
Open iosApp/iosApp.xcodeproj in Xcode and run, or use Android Studio with KMP support.

## Project Structure

`
composeApp/src/
├── commonMain/    # Shared code (UI, logic, models)
├── androidMain/   # Android-specific code
└── iosMain/       # iOS-specific code
`

## API
This app uses the [PokeAPI](https://pokeapi.co/) for Pokémon data.

## License
MIT

