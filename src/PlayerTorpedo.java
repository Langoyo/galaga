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
//We create a class for the Torpedoes of the player
public class PlayerTorpedo extends Torpedo {
	// No argument constructor
	public PlayerTorpedo() {

	}

	// We create a constructor for PlayerTorpedo
	public PlayerTorpedo(int x, int y, int id) {
		/*
		 * We inherit the implementations of the "x", "y", "id" and "image" fields, as
		 * the image will always be the same we implement it
		 */
		super(x, y, id, "torpedo100.png");
	}

}
