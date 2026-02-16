![Android](https://img.shields.io/badge/android-native-green)
![Kotlin](https://img.shields.io/badge/kotlin-1.7-purple)
![Status](https://img.shields.io/badge/status-in%20progress-orange)

# Travel App – Mobile Travel Guide

A native Android mobile application designed as a travel guide platform, developed as part of a personal or exploratory project.  
This repository contains the mobile source code implemented primarily in **Kotlin**, with some **Java** components for compatibility.

---

## Overview

This project implements a travel guide mobile application that allows users to explore travel destinations and related information through an intuitive interface.

The current codebase includes:

- Android app module (`app/`)
- Platform configuration and Gradle build files
- UI layouts and resource assets
- Application logic in Kotlin and Java

This project serves as the foundation for a mobile travel utility application intended for future extension with backend integration and interactive features.

---

## Motivation

Mobile travel applications help users discover destinations, plan trips, and access travel information on the go.  
The goal of this project is to build a native Android travel app that demonstrates:

- Clean application structure  
- Use of modern Android frameworks  
- Modular activity and fragment design  
- Preparedness for API integration

---

## Key Features (Future Intent)

- Responsive UI for travel destination browsing  
- Scalable architecture ready for backend services  
- Support for navigation between multiple screens  
- Structured project layout for maintainability

*(Actual UI/feature implementations will be expanded in future updates — currently scaffold only)*

---

## Technologies Used

- **Android SDK** – Mobile development platform  
- **Kotlin** – Main development language  
- **Java** – Secondary support language  
- **Gradle** – Build automation  
- **Git & GitHub** – Version control  

---

## Repository Structure
```
travel_app/
├── .idea/ # IDE metadata
├── app/ # Android application module
│ ├── src/ # Source code (Kotlin & Java)
│ ├── res/ # UI layouts and assets
│ └── AndroidManifest.xml # App manifest
├── gradle/ # Gradle config
├── build.gradle.kts # Build configuration
├── settings.gradle.kts # Settings and included modules
├── gradlew # Gradle wrapper
├── gradlew.bat # Gradle wrapper (Windows)
└── README.md # Documentation
```

---

## Setup & Installation

To run this Android project locally:

1. Clone the repository:

```bash
git clone https://github.com/beyzaekrem/travel_app.git
Open the project in Android Studio

Let Gradle sync and install dependencies

Connect an Android device or start an emulator

Run the application via:

Run → app → “Play” (in Android Studio)
What This Project Demonstrates
Native Android application structure

Kotlin and Java interoperability

Modular activity/fragment readiness

Build configuration with Gradle

Mobile UX layout foundations

Future Improvements
This project currently contains the initial structure and could be extended with:

Backend API integration (REST/GraphQL)

UI for destination search & filtering

Maps integration (Google Maps, etc.)

Offline caching

User preferences and profiles

CI/CD integration (e.g., GitHub Actions)

Author
Developed by Beyza Ekrem
Computer Engineering Student
