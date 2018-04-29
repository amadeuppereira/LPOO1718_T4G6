# LPOO1718_T4G6


## Package and class diagram (UML)

<img src="https://user-images.githubusercontent.com/25926554/39405853-23d1ed60-4ba5-11e8-9828-cb5112439049.png" width="100%"></img> 

## Design patterns (NEED TO FINISH)
  ### Model-View-Controller (MVC)
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
  
  ### Strategy
  Used in PowerUp to implement different types of power-ups.
  
  ### Singleton
  Used in GameModel.
  The game only needs one GameModel object, that must be accessible to several other classes.
  
  
  
## GUI design

  * ### Main Menu
    ![MainMenu](https://user-images.githubusercontent.com/25926554/39405743-3c58dd06-4ba2-11e8-8116-853134c7f3f9.png)

    **Functionalities:**
    * Choose to Play a game.
    * Choose to Customize the game (the player's skin).
    * Choose to Exit the game.
    
  * ### Map Select Menu
     ![MapSelectMenu](https://user-images.githubusercontent.com/25926554/39405755-a2a00684-4ba2-11e8-9073-a6f2c98d1dc1.png)
     
    **Functionalities:**
    * Select map to play.
    * Swipe down to navigate between maps.
    * Button to return to the main menu.
    
   * ### Play Screen
      ![PlayScreen](https://user-images.githubusercontent.com/25926554/39405767-19895534-4ba3-11e8-9c72-420c4c17e06e.png)
      
     **Functionalities:**
     * Play the game using the arrows on the right side to jump or get down.
     * The circle on the left side is where the power ups will appear and can be activated.

   * ### Pause Menu
      ![PauseMenu](https://user-images.githubusercontent.com/25926554/39405796-170c8fb4-4ba4-11e8-9400-0985af63ddde.png)
      
     **Functionalities:**
     * This screen appears when the cross from the Play Screen is pressed.
     * In this screen the player is able to leave a game or continue.
     
   * ### Finish Menu
      ![FinishMenu](https://user-images.githubusercontent.com/25926554/39405810-5d55dc46-4ba4-11e8-8fc8-9ee9b76d6e6b.png)
           
     **Functionalities:**
     * Game standings and time accomplished.
     * Return to main menu by pressing the arrow.

## Tests

* Test if the character jumps acorddingly to the User's input.
* Test if the character ducks acorddingly to the User's input.
* Check if the time passed increases.
* Test character changing skin.
* Check character collision with terrain.
* Check character collision with enemies.
* Test if the player reached the end of the game.
* (Pick up a power ups)
* ....
* Check character invulnerability after colliding with an enemy.
* Test if the player doesn't jump unless there is an object underneath him.
* Test if the player doesn't jump unless there is an object immediately in front of him.
* (Test menu change)
* ....
