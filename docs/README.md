<h1 align="center">
  LDTS_T02G07 - Soul Knight
</h1>

<p align="center">
  <img src="mockups/mockupIdle.png"/>
</p>

## Project Description
**Soul Knight** is a game inspired by platformer games like *Hollow Knight*, but with some distinct ideas. In this world, everything is trying to kill you, and you are a warrior of great prestige and renown. Your goal is to survive and traverse this underground world to escape.

## General Structure
<p align="center">
  <img src="resources/uml/class/structure.png"/>
</p>

## Features to Implement in the Game

### Player
- **The player has the following characteristics**:
    - **HP (Health Points)**: The player's life is represented by an integer. If it reaches zero, the player dies.
    - **Energy**: The player accumulates energy to perform a special attack.
    - **Movement**: Options for navigating the map, such as jumping and dashing.

### Game Mechanics
- **Random Level Generation**: Creation of varied level layouts in each playthrough.
- **Jump Mechanic**: Introduction of jumping mechanics for the player.
- **Game Physics**: Implementation of physics for moving elements and interactions.

### Combat
- **Player Attacks**:
    - **Melee Attack**: Close-range attack.
    - **Special Attack**: A powerful ability with higher damage or a unique effect.

### Collectibles and Enemies
- **Collectibles**: Items scattered throughout the levels, such as orbs that provide "Power-Ups," enhancing the player's abilities.
- **Enemy Types**: Introduction of various enemies with distinct abilities.

### Interface and Navigation
- **Main Menu**: A navigable menu for starting and managing the game.
- **Information Bars**: Displays for important stats and information (e.g., health, energy).

### Advanced Movement
- **Dash or Dodge**: Enhanced movement for crossing the map and evading enemies.

### Graphics
- **Resolution Adjustment**: Optimization and improvement of game resolution.

## Documentation

### Problems/Features and Patterns
- The implemented features, or those planned for future development, such as Dash and Jump, aim to provide advanced movement for the player while introducing a "skill gap."
- Some software design patterns applied include:
    - **Singleton**: Used in the Game class to ensure only one instance of the Game exists.
    - **Game Loop**: Implemented in the Game class within the Start method.
    - **State**: Allows an object to alter its behavior dynamically based on its current state. Useful for managing the various states a character can occupy, such as Idle, Walking, Running, Jumping, Attacking, and Dying.
    - **MVC (Model-Controller-Viewer)**: Composed of three patterns:
      - **Strategy**: Applied in the Controller, allowing dynamic changes to the game's behavior without altering its logic.
      - **Composite**: Applied in the Viewer. Organizes each Viewer into a hierarchical structure, providing a common access point for each Viewer.
      - **Observer**: Applied between the View and the Controller. The Model notifies multiple Viewers "interested" in specific objects, ensuring they remain updated. 
- **An explanation is also provided in the following diagram**:
<p align="center">
  <img src="uml/mvc.png"/>
</p>

### Consequences
- By following the MVC pattern, typically used for projects of this kind in Java, we were able to extend our project more easily and in a structured way.
- However, when creating code for new features, we must carefully consider their structure and how to divide and implement them within this pattern, which can be challenging.
    


## Mockups

### Menu

### Game

## Test
<p align="center">
  <img src="test/coverage.png"/>
</p>