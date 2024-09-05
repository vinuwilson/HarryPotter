# Harry Potter

## Description

Android app built with jetpack compose that displays data from the Harry Potter API. The home screen will show a list of Harry Potter characters, each featuring an image and key details. When a user selects a character, they will be navigated to a detail screen with more in-depth information about that character. The project should adhere to SOLID principles and follow a clean architecture design pattern.

## Detailed Project Description

## 1. Technology Used:

  * Kotlin
  * Jetpack Compose for the UI
  * Hilt for dependency injection
  * Coroutines and Flow - for making asynchronous calls
  * Retrofit - for networking
  * mockk - for unit testing
  * Coil - Image Loader library.
  * Room database - Offline support
  * The Harry Potter API for fetching data
  
## 2. Features:

  * Home Screen: Home screen that displays a list of Harry Potter characters retrieved from the API.
  * Character Details Scren: Detail screen that displays more information about a character, such as their name, role, house, dob, etc.
  * Search Option: Search functionality that allows the user to search for a specific character by character’s name or actor’s name.
  * Offline mode: The app should work on offline mode.

## 3. Architecture:

  * SOLID Principles: The app is designed following the SOLID principles to ensure a scalable and maintainable codebase.
  * Clean Code Architecture: The app architecture is designed to be clean and modular, separating concerns into different layers (e.g., UI, domain, data).

## 4. Implementation:

  * Shared ViewModel: A shared ViewModel is used to manage the state and share data between the screens.
  * Type-Safe Navigation: The Navigation Component is used with type-safe arguments to navigate between screens.
    
## Design Pattern

* The application is developed using Test-Driven Development (TDD) and follows the Model-View-ViewModel (MVVM) design pattern.

## Further enhancements

* Furthermore cosmetics and refactoring is an endless thought.

## Snapshots

![Screenshot-1](https://github.com/user-attachments/assets/85d6f319-f320-4152-84d7-ee29f4abfd47)

![Screenshot-2](https://github.com/user-attachments/assets/f2900bd8-c105-4a17-951f-846466a11a27)

![Screenshot-3](https://github.com/user-attachments/assets/996756fa-d2f8-486c-ba2f-c4b54f4281c8)



