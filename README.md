# MHRS Android Hospital Appointment System

This repository contains a professional Android application designed to simulate the Central Physician Appointment System (MHRS). The project demonstrates core software engineering principles, local/cloud data integration, and automated testing.

## Technical Stack
- **Language:** Java
- **Platform:** Android SDK
- **Database:** Firebase Realtime Database & SQLite
- **Build System:** Gradle (Kotlin DSL)
- **Testing:** JUnit Framework

## Core Features
- **Symptom Analysis Module:** An NLP-based logic that recommends the appropriate medical department based on user-submitted symptoms.
- **Real-time Data Sync:** Integration with Firebase for secure appointment tracking and data persistence.
- **Automated QA:** Unit tests implemented to verify the reliability of the department recommendation algorithm.
- **Modular Architecture:** Separation of UI components and business logic for scalability and maintainability.

## Application Screenshots

<p align="center">
  <img src="screenshots/screen1.png" width="19%" />
  <img src="screenshots/screen2.png" width="19%" />
  <img src="screenshots/screen3.png" width="19%" />
  <img src="screenshots/screen4.png" width="19%" />
  <img src="screenshots/screen5.png" width="19%" />
</p>
<p align="center">
  <i>User Flow: Login, Registration, Department Selection, Appointment Booking, and Management.</i>
</p>

## Engineering Quality (QA)
The project emphasizes reliability through systematic testing. The `app/src/test` directory contains JUnit test cases that validate the core decision-making logic of the system, ensuring high data integrity and functional accuracy.

---
*Developed as a Computer Engineering Project.*
