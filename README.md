# Molloonaire — Who Wants to Be a Millionaire? Flutter Game

> **Full Flutter quiz game with progressive difficulty, three lifeline mechanics, countdown timer, animated transitions, and Provider-based state management.**

[![Flutter](https://img.shields.io/badge/Flutter-3.x-blue.svg)](https://flutter.dev/)
[![Dart](https://img.shields.io/badge/Dart-3.x-blue.svg)](https://dart.dev/)
[![Provider](https://img.shields.io/badge/State-Provider-green.svg)](https://pub.dev/packages/provider)

---

## Overview

**Molloonaire** is a fully-featured Flutter mobile quiz game inspired by *Who Wants to Be a Millionaire?*. The game implements all classic mechanics: 15 progressive questions, three lifelines, a ticking countdown timer, animated question transitions, and a final score leaderboard.

---

## Game Features

### Question Progression
- 15 questions across 3 difficulty tiers:
  - Questions 1–5: Easy (€100 – €1,000)
  - Questions 6–10: Medium (€2,000 – €32,000)
  - Questions 11–15: Hard (€64,000 – €1,000,000)
- Safe haven checkpoints at Q5 and Q10
- Walk-away mechanic to bank guaranteed winnings

### Three Lifelines
| Lifeline | Mechanic |
|---|---|
| **50:50** | Removes two wrong answers, leaving one wrong + correct |
| **Audience Poll** | Simulates audience vote distribution (weighted toward correct answer) |
| **Phone a Friend** | Shows a "friend" confidence hint with configurable accuracy |

### Countdown Timer
- 30 seconds per question
- Animated circular progress indicator
- Timer color shifts: green → orange → red as time runs low
- Auto-submit wrong answer on timeout

### Animations
- Question slide-in transitions (left-to-right)
- Answer button highlight animation on selection
- Correct/incorrect reveal with color flash (green/red)
- Money ladder scroll animation on correct answer

---

## Architecture — Provider Pattern

```
┌──────────────────┐
│  GameProvider         │  ← ChangeNotifier
│  - currentQuestion   │
│  - score             │
│  - lifelinesUsed     │
│  - timerRemaining    │
│  - gameState         │
└────────┬─────────┘
         │ notifyListeners()
    ┌────┼────┐
    │         │
QuestionScreen  LifelineWidget
(Consumer)     (Consumer)
```

Provider cleanly separates UI from game logic — screens rebuild only when relevant state changes, avoiding unnecessary widget rebuilds.

---

## Project Structure

```
lib/
├── main.dart
├── providers/
│   └── game_provider.dart
├── screens/
│   ├── home_screen.dart
│   ├── game_screen.dart
│   └── result_screen.dart
├── widgets/
│   ├── question_card.dart
│   ├── lifeline_buttons.dart
│   └── money_ladder.dart
└── data/
    └── questions.dart
```

---

## Installation

```bash
git clone https://github.com/tamer017/molloonaire.git
cd molloonaire
flutter pub get
flutter run
```

---

## Skills & Concepts

`Flutter` `Dart` `Provider` `State Management` `Game Logic` `Animation` `Mobile UI` `Timer Implementation` `Gamification` `Quiz App Architecture`

---

## Author

**Ahmed Tamer Assy** — [GitHub](https://github.com/tamer017) | Machine Learning Researcher @ Volkswagen AG
