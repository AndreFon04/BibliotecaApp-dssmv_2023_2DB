# BibliotecaApp

An Android app written in Java to browse libraries and their books, request (check out) books and review them. It talks to the course's Library REST API through Retrofit.

Developed in **2023** for the **DSSMV** course of the BSc in Telecommunications and Informatics Engineering at **ISEP** (Instituto Superior de Engenharia do Porto) by **André Fonseca** and **Diogo Martins**.

### Features
- Choose a library from the list provided
- Browse that library's books and open each book's details (title, authors, publication date, number of pages, description)
- Identify yourself with a user ID (there is no password) to unlock the actions below
- Request (check out) a book, with a confirmation step
- Extend the due date of a request
- Write a review of a book, with a "Recommend this book?" option
- See the reviews of a book and the list of your own requests

### Tech stack
- Java and the Android SDK (minimum API 21)
- Retrofit with the Gson converter for the REST calls
- Gradle (Kotlin DSL) build
- Layered structure: UI, Service, Listeners, Model and Helper

### Web service
The app consumes the **TMDB API** used in the course. The Retrofit clients, and the base URLs they use, are created in the activities. If the service is no longer available, the app will not be able to load data.

### Getting started
Prerequisites: Android Studio and an emulator or device running Android 5.0 (API 21) or later.

```bash
git clone https://github.com/AndreFon04/BibliotecaApp-dssmv_2023_2DB.git
```
Open the project in Android Studio, let Gradle sync, and run the `app` module.

### Known limitations
The app does not check library opening hours. If the library is closed, creating a review returns an error and the user is not told why.

### Documentation
Requirements, use cases, diagrams and tests (in Portuguese)
