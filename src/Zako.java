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
 * We create a class called Zako that extends Enemy as it is going to be a type
 * of Enemy.
 */
public class Zako extends Enemy {

	// No argument constructor
	public Zako() {
	}

	// We create a constructor for Zako
	public Zako(int x, int y, int id, String image, int direction) {
		// We inherit the implementations of the "x", "y", "id", "image" and "direction"
		// fields
		super(x, y, id, image, direction);
		// We set the Zako Health Points to 1
		setHp(1);
		// We set the name of the Zako to "Zako"
		setName("Zako");
	}
}
