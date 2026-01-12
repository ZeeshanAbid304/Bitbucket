Studio Ghibli Movie App
A modern Android application that displays Studio Ghibli films with favorites functionality, built using Clean Architecture principles and following Android best practices.
Features

✅ Movie List Screen: Browse all Studio Ghibli films
✅ Movie Details Screen: View detailed information about each film
✅ Favorites System: Mark/unmark movies as favorites with local persistence
✅ Filter Options: Toggle between "All Movies" and "Favorites Only"
✅ Error Handling: Proper loading, error, and empty states
✅ Offline Support: Room database caching for offline access
✅ Material Design: Modern UI following Material Design guidelines

Architecture
The app follows MVVM (Model-View-ViewModel) architecture with Clean Architecture principles:
app/
├── data/
│   ├── db/          # Room database
│   │   ├── AppDatabase.kt
│   │   ├── MovieDao.kt
│   │   └── MovieEntity.kt
│   ├── api/         # Retrofit API
│   │   ├── GhibliApi.kt
│   │   └── MovieDto.kt
│   ├── mapper/         # Data mappers
│   │   └── Mappers.kt
│   └── repository/     # Repository implementation
│       └── MovieRepositoryImpl.kt
├── domain/
│   ├── model/          # Domain models
│   │   └── Movie.kt
│   └── repository/     # Repository interface
│       └── MovieRepository.kt
├── presentation/
│   ├── movie_list/           # Movie list screen
│   │   ├── MovieListActivity.kt
│   │   ├── MovieListViewModel.kt
│   │   └── MovieAdapter.kt
│   ├── movie_details/        # Movie details screen
│   │   ├── MovieDetailsActivity.kt
│   │   └── MovieDetailsViewModel.kt
│   └── splash/           
│    │   └── SplashActivity.kt
│    └──ui_state/        
│       └── UiState.kt
├── di/                 # Dependency injection
│   └── AppModule.kt
└── MovieApplication.kt # Application class
Tech Stack
Core

Language: Kotlin
Min SDK: 24 (Android 7.0)
Target SDK: 36 (Android 14)

Architecture & Patterns

MVVM Architecture
Clean Architecture
Repository Pattern
UseCase Pattern (optional)

Dependency Injection

Hilt - For dependency injection

Networking

Retrofit - REST API client
OkHttp - HTTP client
Gson - JSON serialization

Database

Room - Local persistence
Flow - Reactive database queries

Asynchronous

Kotlin Coroutines - Asynchronous programming
Flow - Reactive streams
StateFlow - State management in ViewModels

UI

View Binding - Type-safe view access
Material Components - Material Design UI
RecyclerView - List display
Glide - Image loading

Lifecycle

ViewModel - Lifecycle-aware components
LiveData/StateFlow - Observable data holders

API
Using Studio Ghibli API (https://ghibliapi.vercel.app/)
Endpoints:

GET /films - Retrieve all films

Setup Instructions
Prerequisites

Android Studio Hedgehog | 2023.1.1 or later
JDK 8 or later
Android SDK with API 34

Installation

Clone or create the project

bash   # Create new Android Studio project or clone repository

Project Structure
Create the following package structure:

   com.example.db/

├── data/
│   ├── db/          # Room database
│   │   ├── AppDatabase.kt
│   │   ├── MovieDao.kt
│   │   └── MovieEntity.kt
│   ├── api/         # Retrofit API
│   │   ├── GhibliApi.kt
│   │   └── MovieDto.kt
│   ├── mapper/         # Data mappers
│   │   └── Mappers.kt
│   └── repository/     # Repository implementation
│       └── MovieRepositoryImpl.kt
├── domain/
│   ├── model/          # Domain models
│   │   └── Movie.kt
│   └── repository/     # Repository interface
│       └── MovieRepository.kt
├── presentation/
│   ├── movie_list/           # Movie list screen
│   │   ├── MovieListActivity.kt
│   │   ├── MovieListViewModel.kt
│   │   └── MovieAdapter.kt
│   ├── movie_details/        # Movie details screen
│   │   ├── MovieDetailsActivity.kt
│   │   └── MovieDetailsViewModel.kt
│   └── splash/           
│    │   └── SplashActivity.kt
│    └──ui_state/        
│       └── UiState.kt
├── di/                 # Dependency injection
│   └── AppModule.kt
└── MovieApplication.kt # Application class

Add Dependencies

Copy the provided build.gradle.kts (app level)
Copy the provided build.gradle.kts (project level)
Sync Gradle


Add Files
Place all Kotlin files in their respective packages according to the structure above.
Add Resources

Copy XML layouts to res/layout/
Copy menu file to res/menu/
Copy drawable to res/drawable/


Update AndroidManifest.xml

Copy the provided AndroidManifest.xml
Ensure internet permission is included


Sync and Build

bash   ./gradlew clean build
File Organization
Kotlin Files
Core Application

MovieApplication.kt → Root package

Data Layer

MovieEntity.kt → data/db
MovieDao.kt → data/db
AppDatabase.kt → data/db
MovieDto.kt → data/api
GhibliApi.kt → data/api
Mappers.kt → data/mapper/
MovieRepositoryImpl.kt → data/repository/

Domain Layer

Movie.kt → domain/model/
MovieRepository.kt → domain/repository/

Presentation Layer

MovieListActivity.kt → presentation/list/
MovieListViewModel.kt → presentation/list/
MovieAdapter.kt → presentation/list/
MovieDetailsActivity.kt → presentation/details/
MovieDetailsViewModel.kt → presentation/details/
UiState.kt → presentation/(sealed class)
Extensions.kt → presentation/util/

Dependency Injection

AppModule.kt → di/

XML Resources
Layouts (res/layout/)

activity_movie_list.xml
activity_movie_details.xml
item_movie.xml

Menu (res/menu/)

menu_movie_list.xml

Drawable (res/drawable/)

ic_back.xml

Key Features Implementation
1. State Management
Uses sealed classes for clear state handling:
kotlinsealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String?) : UiState<Nothing>()
}
2. Favorites Filtering
kotlinenum class FilterType {
    ALL, FAVORITES
}
3. Reactive Data Flow

Room → Flow → Repository → ViewModel → UI
Automatic UI updates when data changes

4. Error Handling

Try-catch blocks in ViewModels
Error state propagation to UI
Retry functionality

5. Offline Support

Room database caching
API data persistence
Works without internet after initial load

Usage

Launch App: Opens movie list screen
Browse Movies: Scroll through Studio Ghibli films
Add to Favorites: Tap star icon on any movie
Filter: Use menu → "Show Favorites Only"
View Details: Tap any movie card
Toggle Favorite in Details: Use FAB button

Testing
Manual Testing Checklist

 Initial load shows loading state
 Movies display after load
 Error state on network failure
 Retry button works
 Favorite toggle works
 Filter shows correct movies
 Navigation to details works
 Details screen displays correctly
 Back navigation works
 Offline mode works after initial load

Common Issues & Solutions
Issue: Hilt error "Entry point not found"
Solution: Ensure @AndroidEntryPoint is on all activities and @HiltAndroidApp is on Application class
Issue: Room database not working
Solution: Check kapt is applied and Room compiler dependency is added
Issue: Images not loading
Solution: Verify internet permission in manifest and Glide dependency
Issue: API not responding
Solution: Check internet connection and API endpoint (https://ghibliapi.vercel.app/films)
Future Enhancements

 Search functionality
 Sort options (by year, title, etc.)
 Share movie details
 Dark theme support
 Animations and transitions
 Unit tests
 UI tests
 Pagination for large lists
 Pull to refresh

Dependencies Versions
agp = "8.13.2"
glide = "5.0.5"
hiltAndroid = "2.57.2"
kotlin = "2.3.0"
coreKtx = "1.17.0"
junit = "4.13.2"
junitVersion = "1.3.0"
espressoCore = "3.7.0"
appcompat = "1.7.1"
lifecycleLivedataKtx = "2.10.0"
material = "1.13.0"
activity = "1.12.2"
constraintlayout = "2.2.1"
retrofit = "3.0.0"
roomRuntime = "2.8.4"
License
This project is for educational purposes.
Contact
For questions or issues, please create an issue in the repository.
