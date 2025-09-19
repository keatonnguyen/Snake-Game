# Snake Game

This is a classic **Snake Game** implemented in **Java** using **Swing** for graphical interface. The game consists of a snake that moves around the screen, eating apples to grow longer. The game ends when the snake collides with itself or the walls. Press **ENTER** to restart the game after a game over.

## Features

- **Snake Movement**: Use the **WASD keys** to control the direction of the snake (up, down, left, right).
- **Apple Consumption**: Eating an apple increases the snake's body length and your score.
- **Collision Detection**: The game ends if the snake collides with its own body or the walls.
- **Game Over Screen**: Displays the "Game Over" message and the option to restart the game by pressing **ENTER**.
- **Scoring**: The score is displayed in the top left corner.

## Requirements

- **Java Development Kit (JDK)**
- **IDE** or **text editor** 

## How to Run

### 1. **Clone this repository:**

```bash
git clone https://github.com/yourusername/snake-game.git
cd snake-game
```

### 2. Compile and Run the Game:
Open the project in your IDE.
Compile and run the Main.java class.

```bash
Copy
javac Main.java
java SnakeGame.Main
```

## Key Files
GamePanel.java: Contains the core game logic, including the snake's movement, apple generation, collision detection, and game rendering.

GameFrame.java: The frame that holds the game panel, providing the window for the game.

Main.java: The entry point of the game that initializes the game.
