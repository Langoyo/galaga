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
 * We create a class called Goei that extends Enemy as it is going to be a type
 * of Enemy.
 */
public class Goei extends Enemy {

	// No argument constructor.
	public Goei() {

	}

	// We create a constructor for Goei
	public Goei(int x, int y, int id, String image, int direction) {
		// We inherit the implementations of the "x", "y", "id", "image" and "direction"
		// fields
		super(x, y, id, image, direction);
		// We set the Goei Health Points to 1
		setHp(1);
		// We set the name of the Goei to "Goei"
		setName("Goei");

	}
}
