# LPOO1718_T4G6


## Package and class diagram (UML)

<img src="https://user-images.githubusercontent.com/25926554/39405267-2f0540b6-4b9a-11e8-86f8-7430a2c33211.PNG" width="90%"></img> 

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
