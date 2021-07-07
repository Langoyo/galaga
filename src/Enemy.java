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
//We create a class for the Enemies that inherits from the Entity class 

public class Enemy extends Entity {
	/**
	 * @name This field is to know which type of enemy it is. It will be specified
	 *       in the constructors of each enemy
	 */
	private String name;
	/**
	 * @attacking Field to know if an enemy is attacking
	 */
	private boolean attacking;

	/**
	 * @decideAttack Field to store the type of attack and current step of it. The
	 *               different attacks are created in the constants class as an
	 *               array. It is an integer array of two positions. The first one
	 *               will store the type of attack. The second, the position in the
	 *               array of attacks.
	 */
	private int[] decideAttack = new int[2];

	// We create the non-argument constructor
	public Enemy() {
	}

	// We create the constructor for the Enemy class
	public Enemy(int x, int y, int id, String image, int direction) {
		// We inherit the implementations of the "x", "y", "id", "image" and "direction"
		// fields
		super(x, y, id, image, direction);
	}

	// We create the setters and getters for the new fields

	/**
	 * Method to set the name of the enemy
	 * 
	 * @param name must be introduced
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the name of an enemy for this we use @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Method to set an enemy to attack or not
	 * 
	 * @param attacking must be introduced
	 */
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	/**
	 * Method to get if an enemy is attacking for this we use @return
	 */
	public boolean getAttacking() {
		return this.attacking;
	}

	/**
	 * Method to set the type of attack of an enemy
	 * 
	 * @param type must be introduced
	 */
	public void setTypeAtt(int type) {
		this.decideAttack[0] = type;
	}

	/**
	 * Method to get the type of attack an enemy is doing for this we use @return
	 */
	public int getTypeAtt() {
		return this.decideAttack[0];
	}

	/**
	 * Method to set the step of the type of attack an enemy is doing
	 * 
	 * @param order must be introduced
	 * 
	 */
	public void setOrderAtt(int order) {
		this.decideAttack[1] = order;
	}

	/**
	 * Method to get the step of the type of attack an enemy is doing for this we
	 * use @return
	 */
	public int getOrderAtt() {
		return this.decideAttack[1];
	}

	/**
	 * Method that returns the image field depending on the direction
	 */
	public String getImage() {
		String directionCode;
		// We check if direction is <=9 if so we add a 0
		if (this.getDirection() <= 9) {
			directionCode = "0" + this.getDirection();
		} else {
			directionCode = "" + this.getDirection();
		}
		// We use the split method to separate the name and the file extension
		String[] fileName = this.image.split("\\.");
		// Creating the file name using the prefix, the direction code and the
		// extension
		return fileName[0] + directionCode + "." + fileName[1];
	}

	// Method to get the image independently of the direction
	public String getSimpleImage() {
		return this.image;
	}

	// Method which is the same as the move method in Entity but it changes the x
	// component to
	// -x it is used for the entry animations of the enemies
	public boolean mirrorMove(int direction, int steps) {
		// If parameters are wrong we don't move the enemy and return false.
		if (direction < 0 || direction > 16 || steps < 1) {
			return false;
		} else {
			// changing the direction
			switch (direction) {
			// North and South remain the same
			case 0:
			case 8:
				this.setDirection(direction);
				break;
			// We get the mirrored directions by symmetry of the array
			default:
				this.setDirection((16 - direction));
				break;
			}
			// calculating the new x coordinate
			this.setX(this.getX() - Constants.MOVES[direction][0] * steps);
			// Calculating the new y coordinate
			this.setY(this.getY() + Constants.MOVES[direction][1] * steps);
			return true;
		}
	}

}
