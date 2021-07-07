package finalProject;

/**
 * 
 * 
 * @author Carlos Gallego Jiménez and Andrés Langoyo Martín
 * 
 * @since December 2018
 * 
 * @version 1.0
 * 
 * 
 * 
 */
// We create a class for the Player which extends Entity

public class Player extends Entity {
	// We add the required fields for our Player class
	// Field for the points of the player
	private int points;
	// Field for the hits of the player
	private int hits;
	// Field for the shots of the player
	private int shots;

	// No argument constructor
	public Player() {

	}

	// Now we will create a constructor for the player
	public Player(int id, String image) {
		// Invoking the no argument constructor of the Entity class
		super();

		// We introduce as parameter for the x and y coordinates the initial position in
		// the board
		setY(Constants.PLAYER_Y);
		setX(85);
		// We will set the default value of the player at 3 life points
		setHp(3);
		setId(id);
		setImage(image);

	}
	// We create setters and getter for the new fields

	/**
	 * Method to set the points of the player
	 * 
	 * @param points needs to be introduced
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * We use this method to get the current points of the player for this we
	 * use @return
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * Method to set the hits of the player, as the hits can only increase we just
	 * add 1 to the current hits
	 */
	public void incrementHits() {
		this.hits++;
	}

	/**
	 * We use this method to get the current hits of the player for this we
	 * use @return
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * Method to set the shots of the player, as the shots can only increase we just
	 * add 1 to the current shots
	 */
	public void incrementShots() {
		this.shots++;
	}

	/**
	 * We use this method to get the current shots of the player for this we
	 * use @return
	 */
	public int getShots() {
		return shots;
	}

}
