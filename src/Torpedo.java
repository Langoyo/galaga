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
//We create a class for the torpedoes fired by enemies and the player
public class Torpedo {
	// Field for the x position of the torpedo
	private int x;
	// Field for the y position of the torpedo
	private int y;
	// Field for the id of the torpedo
	private int id;
	// Field for the image of the torpedo
	private String image;

	// No argument constructor
	public Torpedo() {
	}

	// Full argument constructor
	public Torpedo(int x, int y, int id, String image) {
		setX(x);
		setY(y);
		setId(id);
		setImage(image);
	}
//We create the setter and getters for the fields

	/**
	 * We use this method to set the x coordinate
	 * 
	 * @param x needs to be introduced
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * We use this method to get the value of the x coordinate for this we
	 * use @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * We use this method to set the y coordinate
	 * 
	 * @param y needs to be introduced
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * We use this method to get the value of the y coordinate for this we
	 * use @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * We use this method to set the id
	 * 
	 * @param id needs to be introduced
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * We use this method to get the value of the id for this we use @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * We use this method to set the image
	 * 
	 * @param image needs to be introduced
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * We use this method to get the image for this we use @return
	 */
	public String getImage() {
		return image;
	}


}
