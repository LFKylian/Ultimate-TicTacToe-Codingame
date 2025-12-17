# Tic Tac Toe – Minimax AI with Alpha-Beta Pruning

## Overview

This project implements an **artificial intelligence capable of playing Tic Tac Toe** on a 3x3 grid. The algorithm is based on **Minimax**, optimized using **Alpha-Beta pruning**, a common technique used to reduce the number of game states explored.

The purpose of this repository is to demonstrate a classical approach for solving turn-based games with complete information, often used as an introduction to decision-making algorithms in artificial intelligence.

---

## General Algorithm Principle

Tic Tac Toe is a **deterministic, zero-sum game with perfect information**. These properties make it well suited for the Minimax algorithm.

The AI recursively explores the game tree in order to:

* Maximize its outcome when it is its turn to play
* Minimize the opponent’s outcome when the opponent plays

Each board state is assigned a numerical value representing its strategic interest.

---

## Game Representation

* The board is represented using a `int[3][3]` array
* Values used:

  * `0` : empty cell
  * `1` : AI (PLAYER)
  * `2` : opponent (OPPONENT)

---

## State Evaluation

The `evaluate` function assigns a score to a board state:

| Situation     | Score  |
| ------------- | ------ |
| AI wins       | `+100` |
| Opponent wins | `-100` |
| No winner     | `0`    |

All winning configurations are explicitly checked:

* Rows
* Columns
* Diagonals

This evaluation function allows the algorithm to compare final and intermediate states.

---

## End of Game Detection

The `isFinished` method checks whether the board is fully occupied. A game ends when:

* A player wins
* Or no legal moves remain (draw)

---

## Move Generation

The `getValidActionCount` function scans the grid and returns the list of available cells. Each available cell corresponds to a possible action in the search tree.

---

## Minimax with Alpha-Beta Pruning

### Core Function

`minMaxAlphaBeta(int[][] grid, boolean isPlayer, int alpha, int beta)`

### Purpose

* Recursively explores possible moves
* Alternates turns between the AI and the opponent
* Returns the best achievable score for the current player

### Alpha-Beta Pruning

Alpha-Beta pruning is used to:

* Avoid exploring branches that cannot affect the final decision
* Preserve the result of standard Minimax while reducing computation

| Parameter | Meaning                                         |
| --------- | ----------------------------------------------- |
| `alpha`   | Best score currently achievable by the AI       |
| `beta`    | Best score currently achievable by the opponent |

A branch is cut when further exploration cannot improve the outcome.

---

## Move Selection

In the main loop:

1. Each legal move is simulated
2. Minimax is applied to the resulting state
3. The move with the highest resulting score is selected

This process ensures consistent decision-making based on full game tree exploration.

---

## License

This project is intended for educational and experimental use.
