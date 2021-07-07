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
public class Level {
	// Field for the number of the level
	private int number;
	// Field for the zako enemies of the level
	private Zako[] zako;
	// Field for the goei enemies of the level
	private Goei[] goei;
	// Field for the commander enemies of the level
	private Commander[] commander;
	// Field for the number of dead enemies of the level
	private int deadEnemies;

	// No argument constructor
	public Level() {

	}

	// Constructor for the level
	public Level(int n) {
		setNumber(n);
		setZako();
		setGoei();
		setCommander();
	}
	// We create the setters and getters for the fields

	/**
	 * Method to set the number
	 * 
	 * @param n must be provided
	 */
	public void setNumber(int n) {
		this.number = n;
	}

	/**
	 * Method to get the number for this we use @return
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Method to get the array of Zako enemies for this we use @return
	 */
	public Zako[] getZako() {
		return zako;
	}

	/**
	 * Method to set the deadEnemies
	 * 
	 * @param deadEnemies must be provided
	 */
	public void setDeadEnemies(int deadEnemies) {
		this.deadEnemies = deadEnemies;
	}

	/**
	 * Method to get the Dead enemies for this we use @return
	 */
	public int getDeadEnemies() {
		return this.deadEnemies;
	}

	/**
	 * Method to set the Zako enemy array depending on the level the number and
	 * position of enemies will change
	 */
	public void setZako() {
		// We create an array of Zako depending on the number of the level
		Zako[] zako;
		switch (this.number) {
		// First level Zako
		case 1:
			// We create the 14 Zako enemies of the first level
			zako = new Zako[14];
			// We use a for loop to establish the position of the Zakos
			for (int frow = 0, x = 60, id = 1; frow < zako.length; frow++, x = x + 10, id++) {
				if (frow < 6) {
					zako[frow] = new Zako(x, 40, id, "enemy3.png", Constants.DIR_N);
				} else {
					// Here we subtract 60 so we get the first x position of the second row of
					// enemies
					if (frow == 6) {
						x = x - 70;
					}
					zako[frow] = new Zako(x, 30, id, "enemy3.png", Constants.DIR_N);
				}
			}
			break;
		// Second level Zako
		case 2:
			// We create the 20 Zako enemies of the second level
			zako = new Zako[20];
			// We use a for loop to establish the position of the Zakos
			for (int frow = 0, x = 40, id = 1; frow < zako.length; frow++, x = x + 10, id++) {
				if (frow < 10) {
					zako[frow] = new Zako(x, 40, id, "enemy3.png", Constants.DIR_N);
				} else {
					// Here we subtract 90 so we get the first x position of the second row of
					// enemies
					if (frow == 10) {
						x = x - 100;
					}
					zako[frow] = new Zako(x, 30, id, "enemy3.png", Constants.DIR_N);
				}
			}
			break;
		// Third level zako
		default:
			// We create the 16 Zako enemies of the third level
			zako = new Zako[16];
			// We use a for loop and a switch to establish the position of the Zakos
			for (int frow = 0, x = 40, id = 1; frow < zako.length; frow++, x = x + 10, id++) {
				if (frow < 10) {
					zako[frow] = new Zako(x, 40, id, "enemy3.png", Constants.DIR_N);
				} else {
					switch (frow) {
					case 10:
						zako[frow] = new Zako(40, 30, id, "enemy3.png", Constants.DIR_N);
						break;
					case 11:
						zako[frow] = new Zako(60, 30, id, "enemy3.png", Constants.DIR_N);
						break;
					case 12:
						zako[frow] = new Zako(70, 30, id, "enemy3.png", Constants.DIR_N);
						break;
					case 13:
						zako[frow] = new Zako(100, 30, id, "enemy3.png", Constants.DIR_N);
						break;
					case 14:
						zako[frow] = new Zako(110, 30, id, "enemy3.png", Constants.DIR_N);
						break;
					case 15:
						zako[frow] = new Zako(130, 30, id, "enemy3.png", Constants.DIR_N);
					}

				}
			}
		}
		this.zako = zako;
	}

	/**
	 * Method to get the Goei enemies for this we use @return
	 */
	public Goei[] getGoei() {
		return goei;
	}

	/**
	 * Method to set the Goei enemies number and positions depending on the level
	 */
	public void setGoei() {
		Goei[] goei;
		switch (this.number) {
		// First level Goei
		case 1:
			// We create the 6 goeis of the first level
			goei = new Goei[6];
			// We use a for and a switch to establish their positions
			for (int frow = 0, id = 15; frow < goei.length; frow++, id++) {
				switch (frow) {
				case 0:
					goei[frow] = new Goei(40, 20, id, "enemy2.png", Constants.DIR_N);
					break;
				case 1:
					goei[frow] = new Goei(60, 20, id, "enemy2.png", Constants.DIR_N);
					break;
				case 2:
					goei[frow] = new Goei(70, 20, id, "enemy2.png", Constants.DIR_N);
					break;
				case 3:
					goei[frow] = new Goei(100, 20, id, "enemy2.png", Constants.DIR_N);
					break;
				case 4:
					goei[frow] = new Goei(110, 20, id, "enemy2.png", Constants.DIR_N);
					break;
				default:
					goei[frow] = new Goei(130, 20, id, "enemy2.png", Constants.DIR_N);
				}
			}
			break;
		// Second level Goei
		case 2:
			// We create the 10 goeis of the second level
			goei = new Goei[10];
			// We use the for loop to establish the position of the goeis
			for (int frow = 0, x = 40, id = 21; frow < goei.length; frow++, x = x + 10, id++) {
				goei[frow] = new Goei(x, 20, id, "enemy2.png", Constants.DIR_N);
			}
			break;
		// Third level Goei
		default:
			// We create the 10 goeis of the third level
			goei = new Goei[10];
			// We use the for loop to establish the position of the goeis
			for (int frow = 0, x = 40, id = 17; frow < goei.length; frow++, x = x + 10, id++) {
				goei[frow] = new Goei(x, 20, id, "enemy2.png", Constants.DIR_N);
			}

		}
		this.goei = goei;

	}

	/**
	 * Method to get the array of commander enemies for this we use @return
	 */
	public Commander[] getCommander() {
		return commander;
	}

	/**
	 * Method to set the commander array of enemies position and number depending on
	 * the level
	 */
	public void setCommander() {
		Commander[] commander;
		switch (this.number) {
		// First level Commander
		case 1:
			// We create the 2 commanders of the first level
			commander = new Commander[2];
			commander[0] = new Commander(80, 10, 21, "enemy1.png", Constants.DIR_N);
			commander[1] = new Commander(90, 10, 22, "enemy1.png", Constants.DIR_N);
			break;
		// Second level Commander
		case 2:
			// We create the 4 commanders of the second level
			commander = new Commander[4];
			// We use a for to establish the position of the commanders
			for (int frow = 0, id = 31; frow < commander.length; frow++, id++) {
				switch (frow) {
				case 0:
					commander[frow] = new Commander(60, 10, id, "enemy1.png", Constants.DIR_N);
					break;
				case 1:
					commander[frow] = new Commander(70, 10, id, "enemy1.png", Constants.DIR_N);
					break;
				case 2:
					commander[frow] = new Commander(100, 10, id, "enemy1.png", Constants.DIR_N);
					break;
				default:
					commander[frow] = new Commander(110, 10, id, "enemy1.png", Constants.DIR_N);

				}
			}
			break;
		// Third level Commander
		default:
			// We create the 6 commanders of the third level
			commander = new Commander[6];
			for (int frow = 0, id = 27; frow < commander.length; frow++, id++) {
				switch (frow) {
				case 0:
					commander[frow] = new Commander(40, 10, id, "enemy1.png", Constants.DIR_N);
					break;
				case 1:
					commander[frow] = new Commander(60, 10, id, "enemy1.png", Constants.DIR_N);
					break;
				case 2:
					commander[frow] = new Commander(70, 10, id, "enemy1.png", Constants.DIR_N);
					break;
				case 3:
					commander[frow] = new Commander(100, 10, id, "enemy1.png", Constants.DIR_N);
					break;
				case 4:
					commander[frow] = new Commander(110, 10, id, "enemy1.png", Constants.DIR_N);
					break;
				default:
					commander[frow] = new Commander(130, 10, id, "enemy1.png", Constants.DIR_N);
					break;
				}
			}

		}
		this.commander = commander;
	}

	/**
	 * Method to get the trajectory of Commander depending on the level for this we
	 * use @return
	 */
	public int[] createTrajectoryCommander() {
		int[] trajectory;
		// We use a switch to get the trajectory depending on the level
		switch (this.number) {
		case 2:
			trajectory = Constants.COM_ENTRY2;
			break;
		case 3:
			trajectory = Constants.COM_ENTRY3;
			break;
		default:
			trajectory = Constants.COM_ENTRY1;
		}
		return trajectory;
	}

	// Method to get the trajectory of Zako depending on the level
	public int[] createTrajectoryZako() {
		int[] trajectory;
		// We use a switch to get the trajectory depending on the level
		switch (this.number) {
		case 2:
			trajectory = Constants.ZAK_ENTRY2;
			break;
		case 3:
			trajectory = Constants.ZAK_ENTRY3;
			break;
		default:
			trajectory = Constants.ZAK_ENTRY1;
		}
		return trajectory;
	}

	// Method to get the trajectory of Goei depending on the level
	public int[] createTrajectoryGoei() {
		int[] trajectory;
		// We use a switch to get the trajectory depending on the level
		switch (this.number) {
		case 2:
			trajectory = Constants.GOE_ENTRY2;
			break;
		case 3:
			trajectory = Constants.GOE_ENTRY3;
			break;
		default:
			trajectory = Constants.GOE_ENTRY1;

		}
		return trajectory;
	}

}
