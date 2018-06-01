# Fun Run
A simple Fun Run copycat game with a few changes made using libGDX for the LPOO classes.

|Table of Contents|
|:---------------:|
|[Setup/Installation Procedure](#setupinstallation-procedure)|
|[Package and class diagram (UML)](#package-and-class-diagram-uml)|
|[Design Decision](#design-decisions)|
|[Design Patterns Used](#design-patterns-used)|
|[Major Dificulties](#major-dificulties)|
|[Lessons Learned](#lessons-learned)|
|[Overal Time Spent Developing](#overal-time-spent-developing)|
|[User Manual](#user-manual)|

## Setup/Installation Procedure

* ### To install the Android app:
    1. Download the apk [here]()
    2. Accept permissions.

* ### To install the desktop app (for Windows, Linux and MacOS)
   1. Download the jar [here]().
   2. Run the file from the terminal or by double-clicking it.
    
* ### Setup the Project on your computer:
    1. Java Development Kit 7+ (JDK);
    2. [Android Studio] (https://developer.android.com/sdk/index.html) 
       Android Studio already comes packaged with the Android SDK so contrary to Eclipse or Intellij IDEA you do not need to install this component.
    

## Package and class diagram (UML)



## Design Decisions

  * ### Model-View-Controller (MVC)
  <img src=https://upload.wikimedia.org/wikipedia/commons/f/fd/MVC-Process.png>
  
 Main packages:
  * **Model**: Is the central component of the pattern. It expresses the application's behavior in terms of the problem domain, independent of the user interface. It directly manages the data, logic and rules of the application.
  * **View**: Can be any output representation of information, such as a chart or a diagram. Multiple views of the same information are possible, such as a bar chart for management and a tabular view for accountants.
  * **Controller**: Accepts input and converts it to commands for the model or view.
  
  Advantages:
  * Simultaneous development
  * High cohesion
  * Low coupling
  * Ease of modification
  * Multiple views for a model
  
  
## Design Patterns Used

* [Singleton](https://en.wikipedia.org/wiki/Singleton_pattern) -  Restricts the instantiation of many classes (in GameModel and GameController)
* [State Pattern](https://en.wikipedia.org/wiki/State_pattern) - Used in PowerUpModel
* [Strategy Pattern](https://en.wikipedia.org/wiki/Strategy_pattern) - Controls the Menu Appeareance 
* [Observer Pattern](https://en.wikipedia.org/wiki/Observer_pattern) - Implemented by Libgdx with controls listeners (in Controllers class)
* [Update Method](http://gameprogrammingpatterns.com/update-method.html) - Used in EntityModel
* [Game Loop](http://gameprogrammingpatterns.com/game-loop.html) - Used in GameController and Controllers (handles input), GameModel (updates game) and GameScreen (renders)

  
## Major Dificulties
* Mostly trying to integrate the Networking part. We pretty much had to learn by ourselves to create a simple server with a database and connect with it during our game. The result we got it's not perfect but for what we know it's a good start.
* Some problems with making the game equally when played on computer or on Android phone. The big difference is on the pause menu when used on the smartphone it takes a really long time to show.

## Lessons Learned
With this project, we learned that creating a game is very complex and time consuming. We learned many things regarding networking and physics.

## Overal Time Spent Developing
We estimate a total of 160 hours (evenly distributed between the two developers).
  
## User Manual

  * ### Main Menu
    <img width="1075" alt="Main Menu" src="https://user-images.githubusercontent.com/25926554/40864982-04a4e2f4-65ee-11e8-8453-3b65e9484e09.png">

    **Functionalities:**
    * Choose to Play a game.
    * Choose to Select game map.
    * Choose to Exit the game.
    
  * ### Map Select Menu
     <img width="1076" alt="Map Select Menu" src="https://user-images.githubusercontent.com/25926554/40865026-42f8575c-65ee-11e8-8b62-3f07ab3b2d63.png">
     
    **Functionalities:**
    * Select map to play.
    * Button to return to the main menu.
    
   * ### Play Screen
      <img width="1073" alt="Play Screen" src="https://user-images.githubusercontent.com/25926554/40865052-5d737530-65ee-11e8-91de-5590f8ced358.png">
      
     **Functionalities:**
     * Play the game using the arrows on the right side to jump or get down.
     * The circle on the left side is where the power ups will appear and can be activated.

   * ### Pause Menu
      <img width="1075" alt="Pause Menu" src="https://user-images.githubusercontent.com/25926554/40865060-6d2bc752-65ee-11e8-912f-29eb0dfdc584.png">
      
     **Functionalities:**
     * This screen appears when the pause button from the Play Screen is pressed.
     * In this screen the User is able to leave a running game or continue.
     
   * ### Finish Menu
      <img width="1073" alt="Finish Menu" src="https://user-images.githubusercontent.com/25926554/40865075-7d35a168-65ee-11e8-8f8d-e4e38569e0c8.png">
           
     **Functionalities:**
     * Time accomplished when the player reaches the end of the game.
     * Return to main menu by pressing the button.

## Game Art:

* https://opengameart.org/content/animated-runner-character
* https://opengameart.org/content/platformer-art-complete-pack-often-updated
* https://opengameart.org/content/onscreen-controls-8-styles
