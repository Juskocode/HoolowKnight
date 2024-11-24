<h1 align="center">
  LDTS_T02G07 - Soul Knight
</h1>

## Project Description
**Soul Knight** is a game inspired by platformer games like *Hollow Knight*, but with some distinct ideas. In this world, everything is trying to kill you, and you are a warrior of great prestige and renown. Your goal is to survive and traverse this underground world to escape.

## Implemented Features
- **Menu** - The game has a Menu that has
  the following options:
    - **Start** - Starts a new game.
    - **Exit** - Exits the game.
- **Player** - The player has the following characteristics:
    - **HP (Health Points)**: The player's life is represented by an integer. If it reaches zero, the player dies.
    - **Energy**: The player accumulates energy to perform a special attack.
    - **Movement**: the player can move (using the arrow keys) at variable speeds, jump and DoubleJump. The player's movement animations change according to the player's current action and speed.
- **Collectables** - Collectables are "Orbs" that will give the player a "PowerUp" and enhance his abilities. They are scattered around the map and the player can pick them up.
- **Particles** - Particles accompany a lot of actions, it is like a system within the game. They are present in the Menu giving a kind of "Visual Flair" and also in a lot of 
player animations such as Jumping and dying. There exists another system that simulates a Fluid's particles, so that they don't disappear into thin air and actually stay around the map
just like rain would. Currently, we have Death, Jump, DoubleJump and Menu Particles.
- **Game Physics** - We have applied collisions to all the elements in the game.
- **Scene Loader** - class to load levels of written text files with specific characters, allowing to generate tiles and other elements for the different scenes/levels and allows for simple layout editions.

## Planned Features
- **Combat** - The player will be able to attack the enemies using:
    - **Melee Attack**: Close-range attack.
    - **Special Attack**: A powerful ability with higher damage or a unique effect.
- **Game Mechanics** : Creation of varied level layouts in each playthrough. Potential implementation for randomization of levels to allow for 
a unique experience in each playthrough.
- **Interface** - Improve display of important player stats and information (e.g., health, energy).
- **Advanced Movement** - implement a dash or dodge so the player has an enhanced movement.
- **Enemies** - Introduce the logic and hitboxes of each enemy type.
- **Graphics** - **Resolution Adjustment**: Optimization and improvement of game resolution.
- **Damage Taken** - when the player gets hit by one of the monsters his health goes down.
- **Settings and Scoreboard** - Implement the Settings and ScoreBoard options of the Menu.
- **Tutorial** - Add a tutorial to the BackGround of the MainMenu.
- **Attack States** - Implement the attack states of the player and the respective hitboxes for each attack and their effects on the monsters;
## Documentation

### Problems/Features and Patterns
- The implemented features, or those planned for future development, such as Dash and Jump, aim to provide advanced movement for the player while introducing a "skill gap."
- Some software **Design patterns** used include:
    - **Singleton**: Used in the Game class to ensure only one instance of the Game exists.
    - **Game Loop**: Implemented in the Game class within the Start method. It ensures the game updates and renders consistently, regardless of varying frame rates or system performance.
        <p align="center">
            <img src="uml/game-loop.png"/>
        </p>
    - **State**: Allows an object to alter its behavior dynamically based on its current state. Useful for managing the various states a character can occupy, such as Idle, Walking, Running, Jumping, Attacking, and Dying.
    - **MCV (Model-Controller-Viewer)**: Composed of three patterns:
      - **Strategy**: Applied in the Controller, allowing dynamic changes to the game's behavior without altering its logic.
      - **Composite**: Applied in the Viewer. Organizes each Viewer into a hierarchical structure, providing a common access point for each Viewer.
      - **Observer**: Applied between the View and the Controller. The Model notifies multiple Viewers "interested" in specific objects, ensuring they remain updated. 
- **An explanation is also provided in the following diagram**:
<p align="center">
  <img src="uml/mvc.png"/>
</p>

### Consequences
- By following the MCV pattern, typically used for projects of this kind in Java, we were able to extend our project more easily and in a structured way.
However, when creating code for new features, we must carefully consider their structure and how to divide and implement them within this pattern, which can be challenging.
