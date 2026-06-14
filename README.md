# molloonaire

An engaging Android quiz game challenging general knowledge with strategic lifelines.

## Overview

`molloonaire` is an Android mobile quiz game designed to provide an engaging and challenging experience for users to test their general knowledge. Inspired by popular quiz show formats, the application presents players with a series of multiple-choice questions across varying difficulty levels.

The project addresses the need for interactive educational entertainment by offering a dynamic gameplay experience, complete with strategic "lifelines" to assist players when faced with difficult questions. This application is targeted at Android users who enjoy trivia, general knowledge challenges, and interactive mobile gaming.

## Architecture

The `molloonaire` application follows a standard multi-activity Android architecture, leveraging core Android components for its structure and functionality.

*   **Activities**:
    *   `MainActivity`: Serves as the primary entry point, facilitating navigation to the game, instructions, and an external app rating link.
    *   `Instructions`: Dedicated to displaying game rules and information to the user.
    *   `Game`: The central activity responsible for managing question display, user input, game state, and lifeline interactions.
*   **Data Model**: A `Question` Plain Old Java Object (POJO) encapsulates each quiz question, its four options, and the correct answer.
*   **Question Management**: Questions are categorized and managed within `ArrayList`s for easy, medium, and hard difficulty levels, ensuring a structured progression.
*   **UI Components**: The application leverages standard Android UI elements such as `TextView` for text display, `ImageView` for interactive elements like lifelines, and `LinearLayout` for clickable option cards.
*   **Adaptive Icons**: The project utilizes modern Android adaptive icons for a consistent launcher icon appearance across various devices and OEM customizations.

## Key Features

*   **Progressive Difficulty Quiz**: Players navigate through a series of questions that progressively increase in difficulty, starting with easy, moving to medium, and concluding with hard questions, providing a balanced challenge.
*   **Strategic Lifelines**: Three distinct lifelines are available to assist players: "Remove Two Wrong Answers" to eliminate incorrect options, "Ask a Friend" to directly reveal the correct answer, and "Ask the Audience" (visualized via a bar chart) to show popular choices. Each lifeline is a one-time use per game.
*   **Dynamic Question Presentation**: Questions are randomly selected from their respective difficulty pools, ensuring variety across game sessions. Answer options are also shuffled for each question to prevent predictable positioning of the correct answer.

## Technologies

*   **Platform**: Android SDK
*   **Language**: Java
*   **UI Framework**: AndroidX (e.g., `AppCompatActivity`, `DialogFragment`)
*   **Build System**: Gradle (version 7.0.2 via Gradle Wrapper)
*   **Testing**: JUnit (for local unit tests), AndroidX Test (for instrumented tests)
*   **External Libraries**: MPAndroidChart (for charting functionalities, specifically for the "Ask the Audience" lifeline)

## Getting Started

To get a local copy of `molloonaire` up and running, follow these steps:

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/molloonaire.git
    cd molloonaire
    ```
2.  **Open in Android Studio**:
    Launch Android Studio and open the cloned `molloonaire` project. Android Studio will automatically synchronize the Gradle project.
3.  **Build and Run**:
    Once Gradle synchronization is complete, select an Android emulator or a physical device (API Level 21+ recommended) and click the 'Run' button (green triangle icon) in Android Studio to build and deploy the application.