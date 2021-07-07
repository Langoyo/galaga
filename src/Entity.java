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
/**
 * In this class we would create a base for the enemies and the player. We
 * create it as abstract so we do not have to give implementation. It will
 * contain the fields and methods common to all player and enemies in the game
 * which will acquire them by inheritance.
 * 
 */
public abstract class Entity {

	// Field for the x coordinate of the entity
	private int x;
	// Field for the y coordinate of the entity
	private int y;
	// Field for the id of the entity
	private int id;
	// Field for the image of the entity
	protected String image;
	// Field for the current direction of the entity to show the right image
	private int direction;
	// Field for the health points of the entity
	private int hp;
	// Field for the timer for the explosion when an entity dies
	private int deathTimer = -1;
	// Now we will create the constructors

	// First a non-argument constructor

	public Entity() {
	}

	// Now a full constructor that is meant to be inherited
	public Entity(int x, int y, int id, String image, int direction) {
		setX(x);
		setY(y);
		setId(id);
		setImage(image);
		setDirection(direction);
	}
	// Now we will create our set and get methods

	/**
	 * This is the method to set the x coordinate of an entity.
	 * 
	 * @param x must be introduced
	 */
	public void setX(int x) {
		if (x >= 0) {
			this.x = x;
		}
	}

	/**
	 * Returns the value of the x coordinate for this we use @return
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * This method sets the y position of the entity
	 * 
	 * @param y must be introduced
	 */
	public void setY(int y) {
		if (y >= 0) {
			this.y = y;
		}
	}

	/**
	 * Returns the value of the y coordinate for this we use @return
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * 
	 * This method sets the id field , this field will not changed once assigned but
	 * instead of leaving it private we set is protected so the classes that extend
	 * from this one can have access to it
	 * 
	 * @param id must be introduced
	 */
	protected void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the value of the id for this we use @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets the value of the image field
	 * 
	 * @param im must be introduced
	 */
	public void setImage(String im) {
		this.image = im;
	}

	/**
	 * This method is used to get the image field for this we use @return
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 * This method is used to set the direction field but we leave it protected as
	 * we will only allow to change direction by using the move method
	 * 
	 * @param direction must be introduced
	 */
	protected void setDirection(int direction) {
		if (direction >= 0 && direction < 16) {
			this.direction = direction;
		}
	}

	/**
	 * Gets the value of the direction field for this we use @return
	 */
	public int getDirection() {
		return this.direction;
	}

	/**
	 * Method to set the value of the health points
	 * 
	 * @param hp must be provided
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * Method to get the current health of the player for this we use @return
	 */
	public int getHp() {
		return this.hp;
	}

	/**
	 * This method is used to set the deathTimer
	 * 
	 * @param timer must be introduced
	 */
	public void setDeathTimer(int timer) {
		this.deathTimer = timer;
	}

	/**
	 * This method is used to get the deathTimer for this we use @return
	 */
	public int getDeathTimer() {
		return this.deathTimer;
	}

	/**
	 * Method to move the enemy to a new position, receives the direction and the
	 * steps and calculates the new (x,y)
	 * 
	 * @param direction the direction to move
	 * @param steps     the steps to move in that direction
	 * @return true if it can move in that direction, otherwise:
	 * @return false when the direction introduced is wrong
	 */
	public boolean move(int direction, int steps) {
		// If parameters are wrong we don't move and return false.
		if (direction < 0 || direction > 16 || steps < 1) {
			return false;
		} else {
			// changing the direction
			this.direction = direction;
			// calculating the new x and y
			this.x = this.x + Constants.MOVES[direction][0] * steps;
			this.y = this.y + Constants.MOVES[direction][1] * steps;
			return true;
		}
	}

}
