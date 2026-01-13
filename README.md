🎬 Studio Ghibli Movie App

A modern Android application that displays Studio Ghibli films with favorites functionality, built using Clean Architecture, MVVM, and Android best practices.

The app consumes the public Studio Ghibli API, supports offline access, and follows Material Design guidelines.

✨ Features

✅ Movie List Screen – Browse all Studio Ghibli films

✅ Movie Details Screen – View detailed movie information

✅ Favorites System – Mark/unmark movies as favorites (locally persisted)

✅ Filter Options – Toggle between All Movies and Favorites Only

✅ Error Handling – Loading, error, and empty states

✅ Offline Support – Room database caching

✅ Material Design UI – Clean and modern interface

🏗 Architecture

The app follows Clean Architecture with MVVM:

app/
├── data/
│   ├── api/          # Retrofit API & DTOs
│   ├── db/           # Room database
│   ├── mapper/       # DTO ↔ Entity ↔ Domain mappers
│   └── repository/   # Repository implementation
│
├── domain/
│   ├── model/        # Domain models
│   └── repository/  # Repository interface
│
├── presentation/
│   ├── movie_list/   # Movie list screen
│   ├── movie_details/# Movie details screen
│   ├── splash/       # Splash screen
│   └── ui_state/     # UI state handling
│
├── di/               # Hilt dependency injection
└── MovieApplication.kt

Architectural Principles

MVVM (Model–View–ViewModel)

Repository Pattern

Single Source of Truth (Room)

Reactive streams with Flow & StateFlow

🧰 Tech Stack
Core

Language: Kotlin

Min SDK: 24 (Android 7.0)

Target SDK: 36 (Android 14)

Architecture & Patterns

MVVM

Clean Architecture

Repository Pattern

UseCase Pattern (optional)

Dependency Injection

Hilt

Networking

Retrofit

OkHttp

Gson

Database

Room

Flow

Asynchronous

Kotlin Coroutines

Flow / StateFlow

UI

View Binding

Material Components

RecyclerView

Glide

Lifecycle

ViewModel

StateFlow / LiveData

🌐 API

Using the Studio Ghibli API:

Base URL: https://ghibliapi.vercel.app/

GET /films   → Retrieve all films

🚀 Setup Instructions
Prerequisites

Android Studio Hedgehog | 2023.1.1+

JDK 8 or later

Android SDK (API 34+)

Installation

Clone the repository

git clone https://github.com/your-username/studio-ghibli-movie-app.git


Open in Android Studio

Sync Gradle

./gradlew clean build

📁 Project Structure
com.example.db/
│
├── data/
│   ├── api/
│   │   ├── GhibliApi.kt
│   │   └── MovieDto.kt
│   ├── db/
│   │   ├── AppDatabase.kt
│   │   ├── MovieDao.kt
│   │   └── MovieEntity.kt
│   ├── mapper/
│   │   └── Mappers.kt
│   └── repository/
│       └── MovieRepositoryImpl.kt
│
├── domain/
│   ├── model/
│   │   └── Movie.kt
│   └── repository/
│       └── MovieRepository.kt
│
├── presentation/
│   ├── movie_list/
│   │   ├── MovieListActivity.kt
│   │   ├── MovieListViewModel.kt
│   │   └── MovieAdapter.kt
│   ├── movie_details/
│   │   ├── MovieDetailsActivity.kt
│   │   └── MovieDetailsViewModel.kt
│   ├── splash/
│   │   └── SplashActivity.kt
│   └── ui_state/
│       └── UiState.kt
│
├── di/
│   └── AppModule.kt
│
└── MovieApplication.kt

🎨 Resources
Layouts (res/layout)

activity_movie_list.xml

activity_movie_details.xml

item_movie.xml

Menu (res/menu)

menu_movie_list.xml

Drawables (res/drawable)

ic_back.xml

🔄 State Management

Uses a sealed UI state:

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String?) : UiState<Nothing>()
}

⭐ Favorites Filtering
enum class FilterType {
    ALL,
    FAVORITES
}

🔁 Reactive Data Flow
Room → Flow → Repository → ViewModel → UI


Automatic UI updates

Single source of truth

Offline-first approach

📡 Offline Support

API data cached in Room

App works without internet after initial load

🧪 Testing Checklist

 Loading state shown on launch

 Movies displayed correctly

 Error state on network failure

 Retry button works

 Favorite toggle works

 Favorites filter works

 Navigation to details works

 Offline mode works

❗ Common Issues & Solutions
Hilt Error: Entry point not found

✔ Ensure:

@HiltAndroidApp on MovieApplication

@AndroidEntryPoint on all Activities

Room Database Not Working

✔ Check:

kapt / ksp applied

Room compiler dependency added

Images Not Loading

✔ Verify:

Internet permission in AndroidManifest.xml

Glide dependency included

API Not Responding

✔ Check:

Internet connection

Endpoint: https://ghibliapi.vercel.app/films
🔮 Future Enhancements

🔍 Search movies

📊 Sort by year / title

🌙 Dark theme

🔗 Share movie details

🎞 Animations & transitions

🧪 Unit & UI tests

📄 Pagination

🔄 Pull-to-refresh

📦 Dependency Versions
AGP                  = 8.13.2
Kotlin               = 2.3.0
Hilt                 = 2.57.2
Retrofit             = 3.0.0
Room                 = 2.8.4
Glide                = 5.0.5
Material             = 1.13.0
Lifecycle            = 2.10.0
Core KTX             = 1.17.0
AppCompat            = 1.7.1

📜 License

This project is for educational purposes only.

📬 Contact

For questions or issues, please open an issue in the repository.
