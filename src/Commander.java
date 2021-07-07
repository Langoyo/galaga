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

/*
 * We create a class called Commander that extends Enemy as it is going to be a
 * type of Enemy.
 */
public class Commander extends Enemy {
	// No argument constructor
	public Commander() {
	}

	// We create a constructor for Commander
	public Commander(int x, int y, int id, String image, int direction) {
		// We inherit the implementations of the "x", "y", "id", "image" and "direction"
		// fields
		super(x, y, id, image, direction);
		// We set the Commander Health Points to 2
		setHp(2);
		// We set the name of the Commander to "Commander"
		setName("Commander");
	}

}
