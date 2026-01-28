# 🚀 Project 2026 - Habit Tracker (Base Version)

![Android](https://img.shields.io/badge/Android-100%25-green)
![Kotlin](https://img.shields.io/badge/Kotlin-100%25-blueviolet)
![Koin](https://img.shields.io/badge/Koin_DI-4.1.1-yellow)
![Compose](https://img.shields.io/badge/Jetpack_Compose-✅-orange)
![Status](https://img.shields.io/badge/Status-Working_Base-brightgreen)

Professional modular architecture Android application for habit tracking. **Base working version installed on device**.

## 📱 Current Status

### ✅ **Working Base Version**
- **App builds successfully** and installs on physical device
- **Modular architecture** with 6 feature modules ready
- **Koin 4.1.1** with BOM for dependency management
- **Compose** with Kotlin 2.0+ support
- **compileSdk 35**, targetSdk 35

### 🎯 **Next Features to Implement**
1. **📋 Rules Management** - 7 daily habits, tracking, completion
2. **📅 Calendar Tracking** - 365-day visual progress
3. **📊 Statistics & Analytics** - Charts, percentages, recommendations
4. **🔔 Notifications** - Motivational reminders, scheduler
5. **⚙️ App Settings** - Minimalist design (black/white/red)

## 🏗️ Project Structure
Project2026/
├── :app/ # Main application module
├── :core/ # Utilities, extensions
├── :core-ui/ # UI components, theme
├── :domain/ # Business logic, use cases
├── :data/ # Repositories, database (to be implemented)
└── :feature-*/ # Screen modules (5 total)
├── rules/ # Rules list and management
├── tracking/ # Daily habit tracking
├── statistics/ # Statistics and charts
├── notifications/ # Push notifications
└── settings/ # App settings

text

## 🛠️ Technology Stack

- **Language:** 100% Kotlin
- **Architecture:** Modular Clean Architecture
- **DI:** Koin 4.1.1 (with BOM)
- **UI:** Jetpack Compose
- **Async:** Kotlin Coroutines
- **Build:** Gradle with version catalog (libs.versions.toml)

## 🚀 How to Build

```bash
# Clone the repository
git clone https://github.com/perepel202020-oss/Project2026.git

# Build the app
cd Project2026
./gradlew :app:assembleDebug

# Run on device/emulator
./gradlew :app:installDebug
📦 Dependencies
Managed via gradle/libs.versions.toml:

Koin 4.1.1 (DI)

Kotlin 2.3.0

Compose BOM 2023.10.01

Android Gradle Plugin 9.0.0

📄 Recent Changes
Latest Commit: Added modular architecture with working base

Fixed Koin version from 3.6.0 → 4.1.1 (BOM approach)

Updated compileSdk to 35

Added compose-compiler plugin for Kotlin 2.0+

Created 5 feature modules ready for implementation

App successfully builds and installs on device

🏁 Next Steps
Implement Room Database in :data module

Create Rule entity and repository

Build Rules screen UI in :feature-rules

Add daily tracking calendar in :feature-tracking

📞 Contact
Project: https://github.com/perepel202020-oss/Project2026