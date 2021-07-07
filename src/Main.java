package finalProject;

/**
 * 
 * 
 * @author Andrés Langoyo Martín and Carlos Gallego Jiménez 
 * 
 * @since December 2018
 * 
 * @version 1.0
 * 
 * 
 * 
 */

//Importing the Locale object to change the default configuration 
//of the computer to English
import java.util.Locale;

//Importing the GameBoardGUI library
import edu.uc3m.game.GameBoardGUI;

public class Main {
	//
	/*
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * _________________________________MAIN PROGRAM________________________________
	 */////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws InterruptedException {

		// Changing the GUI to English
		Locale.setDefault(new Locale("en"));
		// We declare a GameBoardGUI object
		GameBoardGUI board;
		// We create a 17x22 board using the constants
		board = new GameBoardGUI(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);

		// Changing the player name on the GUI
		board.gb_setTextPlayerName("");
		// Changing the name of one of the text labels
		board.gb_setTextPointsUp("Points");
		// Changing the max health of the player
		board.gb_setValueHealthMax(Constants.MAX_HP_PLAYER);
		// Changing the health of the player
		board.gb_setValueHealthCurrent(3);
		// Changing the ability1 to the number of shots of the player
		board.gb_setTextAbility1("Shots");
		board.gb_setValueAbility1(0);
		// Changing the ability2 to the number of hits of the player
		board.gb_setTextAbility2("Hits");
		// Setting the initial value of the number of hits of the player to 0
		board.gb_setValueAbility2(0);
		// Changing the points_down to the missile charge of the player and assigning it
		// a value
		board.gb_setTextPointsDown("Charge");
		// Setting the charge of the player to 1
		board.gb_setValuePointsDown(1);
		// Changing the portrait of the player
		board.gb_setPortraitPlayer("portrait.png");
		// Setting the board visible
		board.setVisible(true);
		// We create this booleans so that the commands of new game and exit work
		boolean newGame = false;
		boolean exitGame = false;
		boolean messageSent = false;
		// This boolean is to activate god mode
		boolean godMode = false;
		// Loop for the game
		do {

			// Creating the player object
			Player player = new Player(0, "player1.png");
			// Setting the sprite of the player invoking the function to
			// set the sprite of an entity
			setSprite(player, board);
			// Variable to control the level each time one level finishes we will increment
			// it by one
			int changelev = 0;
			// Creation of the level object
			Level level;

			// Creating the images that show the current life of the player
			board.gb_addSprite(-732, "player1.png", true);
			board.gb_setSpriteImage(-732, "player1.png", 30, 30);
			board.gb_moveSprite(-732, 0, 21);

			board.gb_addSprite(-733, "player1.png", true);
			board.gb_setSpriteImage(-732, "player1.png", 30, 30);
			board.gb_moveSprite(-733, 1, 21);

			board.gb_addSprite(-734, "player1.png", true);
			board.gb_setSpriteImage(-734, "player1.png", 30, 30);
			board.gb_moveSprite(-734, 2, 21);

			board.gb_setSpriteVisible(-732, true);
			board.gb_setSpriteVisible(-733, true);
			board.gb_setSpriteVisible(-734, true);

			// Setting the background using an image
			board.gb_addSprite(-735, "background.png", false);
			board.gb_setSpriteImage(-735, "background.png", 495, 637);
			board.gb_setSpriteVisible(-735, true);

			// This boolean is used in case the player presses the button "exit game". It is
			// a condition for the game to finish
			exitGame = false;
			// This integer changes the design of the player when pressing up or down
			int changePlayerImage = 1;
			// Loop for a game
			do {
				newGame = false;
				// First thing we do is increment the level we are in
				changelev++;

				// We update the level on the interface
				board.gb_setValueLevel(changelev);
				// We create a boolean to know if the entry animation for the level has been
				// done
				boolean haveIDoneEntryAnimation = false;
				// Now we initialise our level object with the changelev variable
				level = new Level(changelev);

				/*
				 * Once the level is created, the enemies are positioned in the swarm formation,
				 * to perform the entry animations we will place them in other positions. We
				 * want to save the positions of the swarm formation so, when the animations are
				 * finished, the enemies can can return. For that reason, we create a copy of
				 * the level
				 */
				Level copy = new Level(changelev);

				// This is a function to position the enemies for the entry animation
				positionForEntryAnimation(level, board);

				// One condition to finish a level is that all enemies are dead.
				// We store the total number of enemies of a level into the variable tenemies
				int tenemies = level.getGoei().length + level.getCommander().length + level.getZako().length;

				/*
				 * We now create the array of torpedoes of the player We initialise them at
				 * coordinates (-10,-10) because one condition to shoot them will be that the
				 * torpedoes have to be above the screen limit
				 */
				PlayerTorpedo[] playerTorpedoArray = new PlayerTorpedo[15];
				for (int index = 0; index < playerTorpedoArray.length; index++) {
					playerTorpedoArray[index] = new PlayerTorpedo(-10, -10, index + 1000);
				}

				// Here we initialise the enemy torpedoes. The condition to shoot them will be
				// that they have to be below the board
				EnemyTorpedo[] enemyTorpedoArray = new EnemyTorpedo[30];
				for (int index = 0; index < enemyTorpedoArray.length; index++) {
					enemyTorpedoArray[index] = new EnemyTorpedo(-10, 300, index + 2000);
				}

				// We use the function to set as invisible all the torpedoes in the array
				createTorpedoes(playerTorpedoArray, board);
				createTorpedoes(enemyTorpedoArray, board);

				// This is a variable that represents the index of the array of player torpedoes
				// Each time it shoots, it will increment its value to shoot a different torpedo
				int shots = -1;

				/*
				 * This boolean checks that the player has pressed the space bar to shoot a
				 * torpedo if so, it will be set to true and it will be set to false after the
				 * torpedo has appeared on the position of the player
				 */
				boolean haveIShot = false;

				/*
				 * These integers are used to change the images of the enemies when they are in
				 * the formation and add a delay so that they do not change so fast, we use one
				 * to establish the timer and the second one to change the images depending on
				 * that timer
				 */
				int alternateTurn = 0;
				int doubleAlternate = 0;
				/*
				 * This boolean is used to coordinate the movement of the swarm formation. If it
				 * is true, the formation will move to the right, if false, enemies will move
				 * the the left
				 */
				boolean moveRight = true;
				/*
				 * This boolean is used to limit the shooting cadence. When the user shoots, it
				 * is set to zero and until it is seven again, the user cannot shoot again
				 */
				int charge = 7;
				/*
				 * These are two auxiliary enemies. We will use them to coordinate the movement
				 * of the swarm formation. We create them on the far right and left positions of
				 * the swarm formation: 130 and 10. The idea is that one of these two enemies
				 * reaches a board limit, we change the movement of the enemy swarm formation
				 */
				Commander invisibleRight = new Commander(130, 10, -10, "enemy1.png", Constants.DIR_N);
				Commander invisibleLeft = new Commander(40, 10, -11, "enemy1.png", Constants.DIR_N);
				/**
				 * This variable is used as index of the array of directions that each enemy
				 * follows for the entry animations. It is increased by one each time one enemy
				 * performs a movement
				 */
				int tempDirection = 0;

				// Loop for the level

				do {

					/*
					 * Condition to see if the entry animation has been performed. If it is false we
					 * perform the entry animations.
					 */

					if (!haveIDoneEntryAnimation) {

						/* Here we invoke the function "animate" to do the entry animations */
						haveIDoneEntryAnimation = animate(level, copy, tempDirection, board);
						/*
						 * The function moves each enemy once, we increment tempDirection by one so that
						 * the next time the enemy obtains the following direction from the array of
						 * predefined directions
						 */
						tempDirection++;

					}

					/*
					 * This String stores the last command or action from the user. We add .trim()
					 * to remove any initial or final space
					 */

					String lastAction = board.gb_getLastAction().trim();
					/*
					 * This if is used to do specific tasks if a key is pressed by the user or a
					 * command is introduced. The length has to be larger than 0, meaning the user
					 * has introduced something
					 */
					if (lastAction.length() > 0) {
						// Printing the action on the console to check it is correct
						board.gb_println(lastAction);
						// With a switch for last action, we do different operations
						switch (lastAction) {
						case "right":
							/*
							 * If player presses right we check that it has not reached the right border and
							 * move it.
							 */
							if (player.getX() < 165) {
								player.move(Constants.DIR_E, 1);
							}
							break;
						case "left":
							/*
							 * If player presses left we check that it has not reached the left border and
							 * move it.
							 */
							if (player.getX() > 5) {
								player.move(Constants.DIR_W, 1);
							}
							break;
						case "space":
							/*
							 * We place another condition to limit the number of torpedoes on the screen.
							 * With the function "canShoot" we will allow the player to shoot only if there
							 * are less than four torpedoes on the screen
							 */
							if (canShoot(playerTorpedoArray)) {
								// We use the charge integer to determine the shooting cadence
								if (charge == 7) {
									// In case the user has shot, we will increase the number of the integer shot
									// shots represents the index of the array of torpedoesWith the if we make sure
									// that we never reach an index out of bounds
									shots++;
									if (shots > 14) {
										shots = 0;
									}
									// We set true the boolean that indicates if the user has shot
									haveIShot = true;
									/*
									 * We increment the shots counter that will display the number of shots of the
									 * player.
									 */
									player.incrementShots();
									// We reestablish the charge to zero, so that the player has to wait
									charge = 0;
								}

							}
							break;
						/*
						 * These command changes the current level to level the desired level. To do so,
						 * we set the counter of dead enemies to the total number of enemies of the
						 * level. That way, the current level will finish, it will reenter the loop
						 * again and increment the changelev by one so we set the changelev variable to
						 * one number less than the level we want.
						 * 
						 */
						case "command level1":
							changelev = 0;
							level.setDeadEnemies(tenemies);
							break;
						case "command level2":
							changelev = 1;
							level.setDeadEnemies(tenemies);
							break;
						case "command level3":
							changelev = 2;
							level.setDeadEnemies(tenemies);
							break;
						// This command restores the health of the player to its maximum
						case "command hp":
							player.setHp(3);
							break;
						/*
						 * With this command we set true exitGame which is condition for the game loop
						 * to finish.
						 */
						case "exit game":
							exitGame = true;
							break;
						/* With this command, the user will receive some tips & tricks. */
						case "tab":
							// We show in the command bar random tips generated by the function
							// giveMeTips.
							board.gb_clearCommandBar();
							board.gb_println(giveMeTips());
							break;
						/*
						 * With the up and down keys we will allow the player to customize his/her
						 * spaceship. The images of the player are named "playerX.png" with x belonging
						 * to (1,7). With the variable changePlayerImage, we change that X.
						 */
						case "up":
							// If he presses up we will increment the X of the player.
							changePlayerImage++;
							/*
							 * There are 7 images available for the player but we will limit them depending
							 * on the level. For level one, we will only allow the classic sprite. For level
							 * 2, we will allow 4. For level 3 we will allow all of them.
							 */
							switch (level.getNumber()) {
							/*
							 * If the user presses up, we increment the value of changePlayerImage. If it
							 * reaches the maximum for a level we change it back to the first one.
							 */
							case 1:
								if (changePlayerImage == 2) {
									changePlayerImage = 1;
								}

								break;
							case 2:
								if (changePlayerImage == 5) {
									changePlayerImage = 1;
								}
								break;
							default:
								if (changePlayerImage == 8) {
									changePlayerImage = 1;
								}
								break;
							}

							// Here we invoke the function to change the player image
							changePlayerImage(changePlayerImage, player, board);
							break;

						// We operate the same way if he/she presses down.
						case "down":
							changePlayerImage--;

							switch (level.getNumber()) {

							/*
							 * If he/she presses down we change the decrease changePlayerImage and if it
							 * reaches 0, we update the variable to the maximum one depending on the level
							 */
							case 1:
								if (changePlayerImage == 0) {
									changePlayerImage = 1;
								}

								break;
							case 2:
								if (changePlayerImage == 0) {
									changePlayerImage = 4;
								}
								break;
							default:
								if (changePlayerImage == 0) {
									changePlayerImage = 7;
								}
								break;
							}
							changePlayerImage(changePlayerImage, player, board);
							break;

						/*
						 * This command makes the player invincible
						 */
						case "command god":
							godMode = true;
							break;
						/*
						 * This command undoes the god mode
						 */
						case "command normal":
							godMode = false;
							break;
						// With the default we establish the new game command.
						default:
							// We first check that the command contains the words new game.
							if (lastAction.contains("new game")) {
								// We split the String introduced into different string separated by a space.
								String[] name = lastAction.split(" ");
								/*
								 * With the command the first words are always new game, and what follows is the
								 * new name of the player. We update in the interface the last substring given,
								 * assumming it will be the name.
								 */
								board.gb_setTextPlayerName(name[name.length - 1]);
								newGame = true;
							}

						}

					}
					/*
					 * With each iteration we increment the charge of the player by one except if it
					 * as reached its maximum.
					 */
					if (charge == 7) {
						charge = 7;
					} else {
						charge++;
					}
					/*
					 * Now we move the sprite of the player to its new position.
					 */
					board.gb_moveSpriteCoord(player.getId(), player.getX(), Constants.PLAYER_Y);
					/*
					 * We invoke the function to move the torpedoes to the position of the player in
					 * case he/she has shot.
					 */
					if (haveIShot) {
						positionTorpedoes(player, playerTorpedoArray, shots, board);
						/*
						 * Once we have shot the torpedo, we set the haveIshot boolean to false,so if in
						 * the next iteration the player does not press the space bar it won't move a
						 * torpedo
						 */
						haveIShot = false;
					}
					/*
					 * Now we invoke the function to move all the torpedoes that are on the screen.
					 */

					movePlayerTorpedoes(playerTorpedoArray, board);

					/*
					 * This part will only be executed if the entry animations for a level have been
					 * done.
					 */
					if (haveIDoneEntryAnimation) {

						/*
						 * We invoke here the function to make the enemies flap. As the computer
						 * iterates too fast we will only change the images if alternateTurn is 3.
						 */
						if (alternateTurn == 3) {
							changeImageArray(level.getCommander(), board, doubleAlternate);
							changeImageArray(level.getGoei(), board, doubleAlternate);
							changeImageArray(level.getZako(), board, doubleAlternate);

							// We as well set to zero the doubleAlternate in case it is 9
							if (doubleAlternate == 9) {
								doubleAlternate = 0;
							}
							// We set to zero the alternateTurn variable
							alternateTurn = 0;
							// In case altenateTurn is not three, we increment the values of both variables
						} else {
							doubleAlternate++;
							alternateTurn++;
						}

						/*
						 * In this piece of code, we will coordinate the movement of the array of
						 * enemies. To do so, we use the two auxiliary enemies:invisibleRight and
						 * invisibleLeft. We coordinate all the movements with two booleans: moveDown
						 * and moveRight. moveRight is set initially to the right, because is the first
						 * direction we want. moveDown is set every loop to false, so that the enemies
						 * only go down when they reach the board limits
						 */

						boolean moveDown = false;
						/*
						 * Here, depending on the direction, we move the invisible enemies to the right
						 * or to the left
						 */
						if (moveRight == true) {
							invisibleRight.setX(invisibleRight.getX() + 2);
							invisibleLeft.setX(invisibleLeft.getX() + 2);
						} else {
							invisibleRight.setX(invisibleRight.getX() - 2);
							invisibleLeft.setX(invisibleLeft.getX() - 2);
						}

						/*
						 * Here we compare the positions of the invisible enemies to update the control
						 * booleans. If invisibleRight has reached the limit, we set moveRight to false,
						 * the swarm will then move to the left after moving down
						 */

						if (invisibleRight.getX() >= (Constants.BOARD_WIDTH * 10 - 5)) {
							moveRight = false;
							moveDown = true;
						}
						if (invisibleLeft.getX() <= 5) {
							moveRight = true;
							moveDown = true;
						}

						/*
						 * Here we invoke the functions to move the enemies arrays using the control
						 * booleans.
						 */
						moveArray(level.getCommander(), moveRight, moveDown, board);
						moveArray(level.getZako(), moveRight, moveDown, board);
						moveArray(level.getGoei(), moveRight, moveDown, board);

						/*
						 * This function is used to move the array of the copy formations. It moves the
						 * enemies the same way as moveArray but it doesn't move the images
						 */
						justMoveArray(copy.getCommander(), moveRight, moveDown, board);
						justMoveArray(copy.getZako(), moveRight, moveDown, board);
						justMoveArray(copy.getGoei(), moveRight, moveDown, board);

						/*
						 * This function is used to select which enemy to attack
						 */
						selectEnemyToAttack(level.getZako(), level, copy.getZako(), copy);
						selectEnemyToAttack(level.getGoei(), level, copy.getGoei(), copy);
						selectEnemyToAttack(level.getCommander(), level, copy.getCommander(), copy);

						/*
						 * This function is used to move the enemies in their attack animations.
						 */
						attackAnimation(level, board);

						/*
						 * This function is used to teleport the enemies to the top of the board in case
						 * they reach the bottom.
						 * 
						 */
						teleportEnemy(level, board);

						/*
						 * This function is used to return the enemies to the formation in case they
						 * have been teleported.
						 * 
						 */
						returnToFormation(level, copy, board);

						/*
						 * This function is used to fire the torpedoes of the enemies
						 */
						fireEnemyTorpedo(level, enemyTorpedoArray, board);

						/*
						 * If the player has activated god mode, we will not check the collisions of the
						 * player with the enemies and their torpedoes
						 */
						if (!godMode) {
							/*
							 * This function is used to control the collisions of an enemy with the player
							 */
							collisionEnemiesPlayer(level, board, player);
							/*
							 * This function is used to control the collisions of an enemy torpedo with the
							 * player
							 */
							collisionPlayerEnemyTorpedoes(enemyTorpedoArray, player, board);
						}
					}

					/*
					 * With this function we control the collision of the player torpedoes and the
					 * enemies
					 */
					torpedoEnemyCollision(level, playerTorpedoArray, player, board);

					/*
					 * This function makes disappear torpedoes in case one enemy torpedo hits a
					 * player torpedo
					 */
					torpedoesColliding(enemyTorpedoArray, playerTorpedoArray, board);

					/*
					 * This function makes explode the enemies and the player when they die
					 */
					explodingEntities(level, player, board);

					// We update the health points display the current points of the player with the
					// getPoints method
					board.gb_setValuePointsUp(player.getPoints());

					// We update the current health points of the player using the getHp method
					board.gb_setValueHealthCurrent(player.getHp());
					/*
					 * With a switch we check the value of the Health points of the player to update
					 * the display of lives at the corner of the screen. As the life of the player
					 * goes down, we set invisible the corresponding sprite on the screen
					 */
					switch (player.getHp()) {
					case 0:
						board.gb_setSpriteVisible(-732, false);
					case 1:
						board.gb_setSpriteVisible(-733, false);
					case 2:
						board.gb_setSpriteVisible(-734, false);
					}

					// Here we update the charge of the player on the interface
					board.gb_setValuePointsDown(charge);
					// Here we update the number of hits the player has done using the getHits
					// method
					board.gb_setValueAbility2(player.getHits());
					// Now we update the number of shots of the player in the interface
					board.gb_setValueAbility1(player.getShots());
					// We repaint the board to clear traces of images
					board.gb_repaintBoard();
					/*
					 * Here we slow down the speed at which the computer iterates through the
					 * program. We have picked a number to make the game "fluid" but playable
					 */
					Thread.sleep(34L);

					/*
					 * This is the end of the loop for a level. One level will finish when:
					 * 
					 * 1. All the enemies are dead. The field deadenemies on a level reaches the
					 * total amount of enemies in the level, which are stored in tenemies.
					 * 
					 * 2. The health points of the player reach 0. We retrieve them using the
					 * getMethod
					 * 
					 * 3. The player has pressed the button "exit game" in the interface which sets
					 * true the exitGame boolean
					 * 
					 */
				} while (level.getDeadEnemies() < tenemies && player.getHp() > 0 && !exitGame && newGame == false);
				/*
				 * This is the end of the game loop The game will finish when:
				 * 
				 * 1.The level is 3 once the player has come out of the level loop
				 * 
				 * 2.The animation of the player has ended. It happens when the field deathTimer
				 * has reached the last possible digit. The digit represents the last character
				 * of the image of the explosion. It means then that if the death timer has
				 * reached this digit, the animation of the player explosion has finished.
				 * 
				 * 3.The player has pressed the button exit game.
				 * 
				 */
				messageSent = false;
			} while (changelev != 3 && player.getDeathTimer() < 4 && !exitGame && newGame == false);

			/*
			 * Now we establish the different conditions to show a final message depending
			 * if the player has been killed or has won
			 * 
			 */
			if (!messageSent && newGame == false && exitGame == false) {
				if (player.getHp() == 0) {
					board.gb_showMessageDialog("You lost");
				} else {
					board.gb_showMessageDialog("You have saved the world!!!");
				}
				messageSent = true;
				board.gb_showMessageDialog("Play again?");
			}

			/*
			 * If the game has ended, the player needs to choose between two options: press
			 * the new game or the exit name button
			 */
			if (newGame == false && exitGame == false) {
				String lastAction;
				do {
					lastAction = board.gb_getLastAction().trim();

					if (lastAction.contains("new game")) {
						// We split the String introduced into different string separated by a space.
						String[] name = lastAction.split(" ");
						/*
						 * With the command the first words are always new game, and what follows is the
						 * new name of the player. We update in the interface the last substring given,
						 * assumming it will be the name.
						 */
						board.gb_setTextPlayerName(name[name.length - 1]);
						newGame = true;
					} else if (lastAction.contains("exit game")) {
						exitGame = true;

					}
					// If the player has not decided to exit or start a new game we keep the game
					// running
				} while (newGame == false && exitGame == false);
			}
			board.gb_clearSprites();
			// If the player decides to exit we will end the program, if he decides to start
			// a new game we will do so
		} while (newGame == true && exitGame == false);
		// If the user decides to exit we close the window
		board.setVisible(false);

	}

	/*
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * _________________________________SPRITES____________________________________
	 */////////////////////////////////////////////////////////////////////////////

	/**
	 * This function is used to set sprites to the board
	 * 
	 * @param entity must be introduced to set its sprite
	 * @param board  is introduced to select to which board to add the sprite
	 */
	public static void setSprite(Entity entity, GameBoardGUI board) {

		/*
		 * Adding a sprite to the board, it is done in three steps : 1) adding it.
		 * Parameters are a unique id, the file containing the image and true if we want
		 * it to be on top of any other at the same position
		 */

		if (entity.getHp() > 0) {
			board.gb_addSprite(entity.getId(), entity.getImage(), true);

			/*
			 * 2) placing the sprite at a board position. Parameters of the sprite are the
			 * id,the x and the y
			 */
			board.gb_moveSpriteCoord(entity.getId(), entity.getX(), entity.getY());

			/*
			 * 3) making the sprite visible. Parameters are the id and true to show it or
			 * false to hide it
			 */

			board.gb_setSpriteVisible(entity.getId(), true);
		}
	}

	/**
	 * This function sets the sprite for an entire array
	 * 
	 * @param enemy is the array we are setting the sprite of
	 * @param board is where the sprite will be set
	 */
	public static void setSpriteArray(Enemy[] enemy, GameBoardGUI board) {
		// To add the sprite for the array we use a for loop
		for (int index = 0; index < enemy.length; index++) {
			// We check if the enemy is alive
			if (enemy[index].getHp() > 0) {
				setSprite(enemy[index], board);
			}
		}

	}

	/**
	 * This function behaves in the same way as setSprite but makes the sprite
	 * invisible
	 * 
	 * @param entity is the one we will be setting the sprite of
	 * @param board  is where we we will set the sprite
	 */
	public static void setSpriteF(Entity entity, GameBoardGUI board) {
		// Adding a sprite to the board, it is done in three steps
		// 1) adding it. Parameters are a unique id, the file containing the
		// image and true if we want it to be on top of any other at the same
		// position
		board.gb_addSprite(entity.getId(), entity.getImage(), false);

		// 2) placing the sprite at a board position. Parameters are the sprite
		// id, the x and the y0

		board.gb_moveSpriteCoord(entity.getId(), entity.getX(), entity.getY());

		// 3) making the sprite not visible. Parameters are the id and true to show
		// it or false to hide it

		board.gb_setSpriteVisible(entity.getId(), false);
	}

	/**
	 * This function sets the sprite of an array to invisible
	 * 
	 * @param enemy is the array we are setting to invisible its sprite
	 * @param board is where we are setting the sprite
	 */
	public static void setSpriteArrayF(Enemy[] enemy, GameBoardGUI board) {

		for (int index = 0; index < enemy.length; index++) {
			// We check if the enemy is alive
			if (enemy[index].getHp() > 0) {
				setSpriteF(enemy[index], board);
			}
		}

	}

	/*
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * _________________________________IMAGES_____________________________________
	 */////////////////////////////////////////////////////////////////////////////

	/**
	 * This function changes the image of an array of enemies depending on the type
	 * of enemy and the flap animation also the life for commanders is taken into
	 * account
	 * 
	 * @param enemy is the array we will be changing the image of
	 * @param image is where we store the image of the enemies to later change it
	 * @param board is where we change the images
	 */
	public static void changeImageArray(Enemy[] enemy, GameBoardGUI board, int alternate) {
		String image = "";
		int number = 0;
		/*
		 * If its a Commander it also takes into account their health because of the
		 * different images doing this with the function changeCommanderImage
		 */
		for (int index = 0; index < enemy.length; index++) {
			if (enemy[index].getHp() > 0) {
				number = index;
			}
		}
		// We use a switch to take into account the type of enemy
		switch (enemy[number].getName()) {
		case "Commander":

			changeCommanderImage(enemy, board, alternate);

			break;
		case "Goei":
			image = alternate % 2 == 0 ? "enemy2G1.png" : "enemy2G0.png";

			break;
		// Default is case Zako
		default:
			image = alternate % 2 == 0 ? "enemy3G1.png" : "enemy3G0.png";
			break;
		}
		// With a for loop we assign the new image to the entities, not to commander
		// because it has been previously assigned
		if (!enemy[0].getName().equals("Commander")) {
			for (int index = 0; index < enemy.length; index++) {
				if (enemy[index].getHp() > 0) {
					if (!enemy[index].getAttacking()) {

						enemy[index].setImage(image);

						setSprite(enemy[index], board);
					}
				}
			}
		}
	}

	/**
	 * This function is used to change the image of a commander on its Health points
	 * and its flap animation
	 * 
	 * @param commander the array we will change the image of
	 * @param board     where we will change the image
	 * @param alternate the variable that controls the flap animation
	 */
	public static void changeCommanderImage(Enemy[] commander, GameBoardGUI board, int alternate) {
		String image;
		// We iterate through the array of commander
		for (int index = 0; index < commander.length; index++) {
			// We check if the commander is not attacking
			if (!commander[index].getAttacking()) {
				// We assign an image if the Health points are 2 we take into account the
				// flapping animation
				if (commander[index].getHp() == 2) {
					image = alternate % 2 == 0 ? "enemy1G1.png" : "enemy1G0.png";
					// We assign an image if the Health points are 1 we take into account the
					// flapping animation
				} else {
					if (commander[index].getImage().equals("enemy1G000.png")
							|| commander[index].getImage().equals("enemy1G100.png")) {
						image = alternate % 2 == 0 ? "enemy9G1.png" : "enemy9G0.png";

					} else {
						image = alternate % 2 == 0 ? "enemy9G1.png" : "enemy9G0.png";

					}
				}
				// We set the image and the sprite
				commander[index].setImage(image);
				setSprite(commander[index], board);
				// If the commander is attacking and its Health points are 1 we change its image
			} else if (commander[index].getHp() == 1) {
				commander[index].setImage("enemy9.png");
			}
		}

	}

	/**
	 * Function that changes the image of the player
	 * 
	 * @param changePlayerImage It indicates the last number of the player image
	 * @param player
	 * @param board
	 */
	public static void changePlayerImage(int changePlayerImage, Player player, GameBoardGUI board) {
		// First we change the image of the player
		player.setImage("player" + changePlayerImage + ".png");
		// Then we update the sprite of the player with the
		// function setSprite
		setSprite(player, board);
	}

	/**
	 * This is a function to move the swarm formation.
	 * 
	 * @param enemy     This is the array of enemies we want to move
	 * @param moveRight This indicates what direction the enemy must move(right or
	 *                  left)
	 * @param moveDown  This indicates whether we should change the y direction of
	 *                  the enemies or not
	 * @param board     is where we move the enemies
	 */
	public static void moveArray(Enemy[] enemy, boolean moveRight, boolean moveDown, GameBoardGUI board) {
		// We need to move all the enemies at the same time, so we make a for loop
		for (int index = 0; index < enemy.length; index++) {
			if (enemy[index].getHp() > 0 && !enemy[index].getAttacking()) {
				// Depending on the direction received, we increase or decrease the x coordinate
				// of the enemies
				if (moveDown == true) {
					enemy[index].setY(enemy[index].getY() + 2);
				} else {
					if (moveRight == true) {
						enemy[index].setX(enemy[index].getX() + 2);
					} else {
						enemy[index].setX(enemy[index].getX() - 2);
					}
				}
				// In the case we need to move them down, we increase by 2 the y direction

				board.gb_moveSpriteCoord(enemy[index].getId(), enemy[index].getX(), enemy[index].getY());

			}
		}
	}

	/**
	 * This function is the same as move array but it does not change the images as
	 * we will use it for copy
	 * 
	 * @param enemy
	 * @param moveRight
	 * @param moveDown
	 * @param board
	 */
	public static void justMoveArray(Enemy[] enemy, boolean moveRight, boolean moveDown, GameBoardGUI board) {
		// We need to move all the enemies at the same time, so we write a for loop
		for (int index = 0; index < enemy.length; index++) {
			if (enemy[index].getHp() > 0 && !enemy[index].getAttacking()) {
				// Depending on the direction received, we increase or decrease the x coordinate
				// of the enemies
				if (moveDown == true) {
					enemy[index].setY(enemy[index].getY() + 2);
				} else {
					if (moveRight == true) {
						enemy[index].setX(enemy[index].getX() + 2);
					} else {
						enemy[index].setX(enemy[index].getX() - 2);
					}
				}
			}
		}
	}

	/*
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * ___________________________TORPEDOES AND COLLISIONS_________________________
	 */////////////////////////////////////////////////////////////////////////////
	/**
	 * Function to set the torpedoes on the position of the player when he presses
	 * the space bar We use the integer shots as an indicator of the next torpedo in
	 * the array
	 * 
	 * @param player             we will use it to place the torpedo on its
	 *                           coordinates
	 * @param playerTorpedoArray is the array of torpedoes we will position
	 * @param shots              is used as an indicator
	 * @param board              where we will place the torpedoes
	 */
	public static void positionTorpedoes(Player player, PlayerTorpedo[] playerTorpedoArray, int shots,
			GameBoardGUI board) {

		// We check if the torpedo is out of bounds, meaning is not on screen so it can
		// be shot, if so we place it in the position of the player
		if (playerTorpedoArray[shots].getY() < 0) {
			playerTorpedoArray[shots].setX(player.getX());
			playerTorpedoArray[shots].setY(player.getY());
			// We make it visible
			board.gb_moveSpriteCoord(playerTorpedoArray[shots].getId(), player.getX(), Constants.PLAYER_Y);
			board.gb_setSpriteVisible(playerTorpedoArray[shots].getId(), true);

		}
	}

	/**
	 * This function is used to move the torpedoes fired by the player
	 * 
	 * @param playerTorpedoArray is the array of torpedoes we will move
	 * @param board              is where we will move them
	 */
	public static void movePlayerTorpedoes(PlayerTorpedo[] playerTorpedoArray, GameBoardGUI board) {
		// We iterate through the array with a for loop
		for (int index = 0; index < playerTorpedoArray.length; index++) {
			// We check if the torpedoes are on screen
			if (playerTorpedoArray[index].getY() > -5) {
				playerTorpedoArray[index].setY(playerTorpedoArray[index].getY() - 5);
				// We move the sprite of the torpedo
				board.gb_moveSpriteCoord(playerTorpedoArray[index].getId(), playerTorpedoArray[index].getX(),
						playerTorpedoArray[index].getY());
				board.gb_setSpriteVisible(playerTorpedoArray[index].getId(), true);
			}
		}
	}

	/**
	 * We use this function to fire the enemy torpedoes
	 * 
	 * @param level             we use level for the implementation
	 * @param enemyTorpedoArray is the array we will fire from
	 * @param board             is where we will fire
	 */
	public static void fireEnemyTorpedo(Level level, EnemyTorpedo[] enemyTorpedoArray, GameBoardGUI board) {
		// We position the torpedoes
		positionEnemyTorpedo(level, enemyTorpedoArray, board);
		// We move the torpedoes
		moveEnemyTorpedo(level, enemyTorpedoArray, board);

	}

	//
	/**
	 * We use this function to destroy enemy torpedoes when impacted by player
	 * torpedoes
	 * 
	 * @param enemytorpedoes is the array of enemy torpedoes
	 * @param torpedoes      is the array of player torpedoes
	 * @param board          is where all torpedoes are
	 */
	public static void torpedoesColliding(EnemyTorpedo[] enemytorpedoes, PlayerTorpedo[] torpedoes,
			GameBoardGUI board) {
		// We iterate through both arrays
		for (int index = 0; index < enemytorpedoes.length; index++) {
			for (int index1 = 0; index < torpedoes.length; index++) {
				// We compare the position of the torpedoes
				if ((enemytorpedoes[index].getX() < (torpedoes[index1].getX() + 100)
						&& enemytorpedoes[index].getX() > (torpedoes[index1].getX() - 100))
						&& (enemytorpedoes[index].getY() < (torpedoes[index1].getY() + 5)
								&& enemytorpedoes[index].getY() > (torpedoes[index1].getY() - 5))) {
					// If the torpedoes are near each other they will both disappear
					makeInvisibletorpedo(enemytorpedoes[index], board);
					makeInvisibletorpedo(torpedoes[index1], board);
					// We will also place the torpedoes out of the screen
					torpedoes[index1].setX(-10);
					torpedoes[index1].setY(-10);
					enemytorpedoes[index].setY(300);
				}
			}
		}

	}

	/**
	 * This function moves the enemy torpedoes down in case they are on screen
	 * 
	 * @param level
	 * @param enemyTorpedoArray
	 * @param board
	 */
	public static void moveEnemyTorpedo(Level level, EnemyTorpedo[] enemyTorpedoArray, GameBoardGUI board) {
		for (int index = 0; index < enemyTorpedoArray.length; index++) {
			// We put a condition that if a torpedo is above where we create it, we move it
			// down
			if (enemyTorpedoArray[index].getY() < 230) {
				enemyTorpedoArray[index].setY(enemyTorpedoArray[index].getY() + 5);
				// We move the sprite of the enemy torpedo
				board.gb_moveSpriteCoord(enemyTorpedoArray[index].getId(), enemyTorpedoArray[index].getX(),
						enemyTorpedoArray[index].getY());
				board.gb_setSpriteVisible(enemyTorpedoArray[index].getId(), true);

			}
		}
	}

	/**
	 * This function is used to determine whether the player has been hit by an
	 * enemy torpedo and if so reduce his HP by one and make the Torpedo disappear
	 * and return true, returns false otherwise
	 * 
	 * @param torpedoes the enemy torpedoes we will be comparing
	 * @param player    the entity we will be comparing with
	 * 
	 * 
	 */
	public static boolean collisionPlayerEnemyTorpedoes(EnemyTorpedo[] torpedoes, Player player, GameBoardGUI board) {

		// We check if any of the torpedoes has hit the player
		for (int index = 0; index < torpedoes.length; index++) {
			if ((torpedoes[index].getX() <= (player.getX() + 5) && torpedoes[index].getY() <= player.getY() + 5)
					&& (torpedoes[index].getX() >= (player.getX() - 5)
							&& torpedoes[index].getY() >= player.getY() - 5)) {
				// If the player has been hit we reduce his HP by one and we make invisible the
				// torpedo
				player.setHp(player.getHp() - 1);
				if (player.getHp() == 0) {
					makeInvisibleEntity(player, board);
				}
				// We produce the damage animation
				board.gb_animateDamage();
				makeInvisibletorpedo(torpedoes[index], board);
				// We send the torpedo away
				torpedoes[index].setY(torpedoes[index].getY() + 40);
				board.gb_moveSprite(torpedoes[index].getId(), torpedoes[index].getX(), torpedoes[index].getY());
				return true;
			}
		}
		return false;
	}

	/**
	 * Function used to check if an enemy has struck the player and to eliminate
	 * both if so
	 * 
	 * @param enemy  the array we compare with the position of the player
	 * @param board  where enemies and players are placed
	 * @param player will be compared with the enemies positions
	 */
	public static boolean collideEnemy(Enemy[] enemy, GameBoardGUI board, Player player) {
		// We iterate through the enemy array
		for (int index = 0; index < enemy.length; index++) {
			// We check that the enemy we are comparing with is alive
			if (enemy[index].getHp() > 0) {
				// We compare the positions of enemies and player
				if (((enemy[index].getX() >= (player.getX() - 8)) && (enemy[index].getX() <= (player.getX() + 8)))
						&& ((enemy[index].getY() >= (player.getY() - 8))
								&& (enemy[index].getY() <= (player.getY() + 8)))) {
					// If hit both player and enemy will be invisible and the animation of damage
					// and explosions will take place
					player.setHp(0);
					makeInvisibleEntity(player, board);
					board.gb_animateDamage();
					makeInvisibleEntity(enemy[index], board);
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * This function is used to only allow to one enemy to collide with the player
	 * at the same time
	 * 
	 * @param level  taken into account for the number of enemies
	 * @param board  where all entities are placed
	 * @param player we will compare the collisions with this entity
	 */
	public static boolean collisionEnemiesPlayer(Level level, GameBoardGUI board, Player player) {
		if (collideEnemy(level.getGoei(), board, player)) {
			return true;
		} else if (collideEnemy(level.getZako(), board, player)) {
			return true;
		} else if (collideEnemy(level.getCommander(), board, player)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This function positions an enemy torpedo at the coordinate of the enemy if he
	 * meets the correct conditions depending on the enemy firing
	 * 
	 * @param level             taken into account for the array of enemies
	 * @param enemyTorpedoArray the torpedoes to be placed
	 * @param board             where all entities are
	 */
	public static void positionEnemyTorpedo(Level level, EnemyTorpedo[] enemyTorpedoArray, GameBoardGUI board) {

		// Here we invoke a function that will position the torpedoes for each array of
		// enemies
		positionEnemytorpedoespecific(level.getCommander(), enemyTorpedoArray, board);
		positionEnemytorpedoespecific(level.getGoei(), enemyTorpedoArray, board);
		positionEnemytorpedoespecific(level.getZako(), enemyTorpedoArray, board);

	}

	/**
	 * Determines whether we will place a torpedo on an enemy
	 * 
	 * @param enemy             the array of enemies
	 * @param enemyTorpedoArray the array of enemy torpedoes
	 * @param board             where all entities and torpedoes are placed
	 */
	public static void positionEnemytorpedoespecific(Enemy[] enemy, EnemyTorpedo[] enemyTorpedoArray,
			GameBoardGUI board) {
		// With two for loops we compare each enemy with each torpedo
		for (int ii = 0; ii < enemy.length; ii++) {
			for (int jj = 0; jj < enemyTorpedoArray.length; jj++) {
				// We create a random integer that goes from 0 to 499. This is to establish the
				// probability of an enemy to shoot
				int random = (int) (Math.random() * 500);
				// We check that the enemy has direction between SE(6) and SW(10), that the
				// torpedo is out of the board, that the random integer is 0,so there is a
				// probability of 1/500 and that the enemy is alive
				if (random == 0 && enemy[ii].getDirection() >= 6 && enemy[ii].getDirection() <= 10
						&& enemyTorpedoArray[jj].getY() > 250 && enemy[ii].getHp() > 0) {
					// We position the enemies in the correct coordinates
					enemyTorpedoArray[jj].setX(enemy[ii].getX());
					enemyTorpedoArray[jj].setY(enemy[ii].getY());
					// We move the sprites
					board.gb_moveSpriteCoord(enemyTorpedoArray[jj].getId(), enemyTorpedoArray[jj].getX(),
							enemyTorpedoArray[jj].getY());
					board.gb_setSpriteVisible(enemyTorpedoArray[jj].getId(), true);

				}
			}
		}
	}

	/**
	 * This function sets the sprites of an array of torpedoes
	 * 
	 * @param TorpedoArray
	 */
	public static void createTorpedoes(Torpedo[] TorpedoArray, GameBoardGUI board) {

		for (int index = 0; index < TorpedoArray.length; index++) {
			// Adding a sprite to the board, it is done in three steps
			// 1) adding it. Parameters are a unique id, the file containing the
			// image and true if we want it to be on top of any other at the same
			// position
			board.gb_addSprite(TorpedoArray[index].getId(), TorpedoArray[index].getImage(), true);

			// 2) placing the sprite at a board position. Parameters are the sprite
			// id, the x and the y

			board.gb_moveSpriteCoord(TorpedoArray[index].getId(), TorpedoArray[index].getX(),
					TorpedoArray[index].getY());

			// 3) making the sprite visible. Parameters are the id and true to show
			// it or false to hide it

			board.gb_setSpriteVisible(TorpedoArray[index].getId(), false);
		}

	}

	/**
	 * function that invokes functions to checks the collision of a torpedo with an
	 * array of enemies
	 * 
	 * @param level
	 * @param playerTorpedoArray
	 * @param player
	 * @param board
	 */
	public static void torpedoEnemyCollision(Level level, PlayerTorpedo[] playerTorpedoArray, Player player,
			GameBoardGUI board) {
		torpedoEnemyCollisionSpecific(level.getCommander(), playerTorpedoArray, level, player, board);
		torpedoEnemyCollisionSpecific(level.getZako(), playerTorpedoArray, level, player, board);
		torpedoEnemyCollisionSpecific(level.getGoei(), playerTorpedoArray, level, player, board);

	}

	/**
	 * This is a function to make the collisions of the enemies and the player's
	 * torpedoes
	 * 
	 * @param enemy
	 * @param playerTorpedoArray
	 * @param level
	 * @param player
	 * @param board
	 */
	public static void torpedoEnemyCollisionSpecific(Enemy[] enemy, PlayerTorpedo[] playerTorpedoArray, Level level,
			Player player, GameBoardGUI board) {

		// With two for loops we compare the positions of all the enemies and all the
		// torpedoes
		for (int ii = 0; ii < playerTorpedoArray.length; ii++) {
			for (int jj = 0; jj < enemy.length; jj++) {
				// Here we put as a condition that the coordinates of the torpedo coincides with
				// the range of the image of the enemy. By testing, the +5,-5 is a reasonable
				// range
				if ((playerTorpedoArray[ii].getX() >= enemy[jj].getX() - 5
						&& playerTorpedoArray[ii].getX() <= enemy[jj].getX() + 5)
						&& (playerTorpedoArray[ii].getY() >= enemy[jj].getY() - 5
								&& playerTorpedoArray[ii].getY() <= enemy[jj].getY() + 5)) {
					// We will only operate if the current life of the enemy impacted is larger than
					// 0
					if (enemy[jj].getHp() > 0) {
						if (enemy[jj].getHp() == 1) {
							// If the enemy only has one hp, invoke the function to make disappears both the
							// enemy
							// and the torpedo
							makeInvisibletorpedoEntity(enemy[jj], playerTorpedoArray[ii], board);

						} else {
							// if the enemy Health points is larger, we only make disappear the torpedo
							makeInvisibletorpedo(playerTorpedoArray[ii], board);
						}
						// In addition, we invoke the pointsUp function to update some data
						pointsUp(level, enemy[jj], player, board);
						// We move the position of the torpedo were they were created so that they can
						// be reused
						playerTorpedoArray[ii].setX(-10);
						playerTorpedoArray[ii].setY(-10);
					}
				}

			}
		}
	}

	/**
	 * Function to make the torpedo invisible. It sets the sprite false
	 * 
	 * @param torpedo
	 * @param board
	 */
	public static void makeInvisibletorpedo(Torpedo torpedo, GameBoardGUI board) {
		board.gb_setSpriteVisible(torpedo.getId(), false);
	}

	/**
	 * Function to make the torpedo and the entity invisible
	 * 
	 * @param entity
	 * @param torpedo
	 * @param board
	 */
	public static void makeInvisibletorpedoEntity(Entity entity, Torpedo torpedo, GameBoardGUI board) {
		makeInvisibleEntity(entity, board);
		makeInvisibletorpedo(torpedo, board);
	}

	/**
	 * Function to make an entity invisible sets the death timer to zero so that the
	 * entity can perform the explosion animation
	 * 
	 * @param entity
	 * @param board
	 */
	public static void makeInvisibleEntity(Entity entity, GameBoardGUI board) {
		entity.setDeathTimer(0);
		board.gb_setSpriteVisible(entity.getId(), false);
	}

	/**
	 * function that performs the exploding animation for an entity
	 * 
	 * @param level
	 * @param player
	 * @param board
	 */
	public static void explodingEntities(Level level, Player player, GameBoardGUI board) {
		// If it is an enemy we invoke another function for the exploding animations of
		// the enemies
		explodingEntitArray(level.getCommander(), board);
		explodingEntitArray(level.getGoei(), board);
		explodingEntitArray(level.getZako(), board);
		// Player only has four images. We change the extension of the image while the
		// death timer is in range
		if (player.getDeathTimer() > -1 && player.getDeathTimer() < 4) {
			board.gb_setSpriteImage(1, "explosion1" + (player.getDeathTimer() + 1) + ".png");
			board.gb_moveSpriteCoord(1, player.getX(), player.getY());
			board.gb_setSpriteVisible(1, true);
			// Each iteration we add one to the death timer
			player.setDeathTimer(player.getDeathTimer() + 1);
		} else if (player.getDeathTimer() == 4) {
			// If it has reached a limit we set it false
			board.gb_setSpriteVisible(1, false);
		}

	}

	/**
	 * function that makes an entity explode if its Health points are 0
	 * 
	 * @param entity the entity array we want to check
	 * @param board
	 */
	public static void explodingEntitArray(Entity[] entity, GameBoardGUI board) {
		// We search for an enemy whose death timer that has a correct number
		for (int index = 0; index < entity.length; index++) {
			if (entity[index].getDeathTimer() > -1 && entity[index].getDeathTimer() < 5) {
				// We add the timer to the image in this iteration and change the sprite
				board.gb_setSpriteImage(entity[index].getId(), "explosion2" + entity[index].getDeathTimer() + ".png");
				board.gb_setSpriteVisible(entity[index].getId(), true);
				// We increment the number for the next iteration
				entity[index].setDeathTimer(entity[index].getDeathTimer() + 1);
				// If the index is out of bounds we set the sprite false
			} else if (entity[index].getDeathTimer() == 5) {
				board.gb_setSpriteVisible(entity[index].getId(), false);
			}
		}
	}

	/**
	 * We use this function to restrict the number of torpedoes that can be shot. It
	 * counts the number of bullets on screen and returns true if it is lower than a
	 * maximum
	 * 
	 * @param playerTorpedoArray
	 * @return
	 */
	public static boolean canShoot(PlayerTorpedo[] playerTorpedoArray) {
		int counter = 0;
		// We count the number of torpedoes that are on the screen. Each time we find
		// one, we add one to a counter
		for (int index = 0; index < playerTorpedoArray.length; index++) {
			if (playerTorpedoArray[index].getY() > 0) {
				counter++;
			}

		}
		// We return true if there are less than 6 torpedoes on screen
		return counter < 6 ? true : false;

	}
	/*
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * __________________________ATTACK ANIMATIONS_________________________________
	 */////////////////////////////////////////////////////////////////////////////

	/**
	 * function to select which enemy will attack
	 * 
	 * @param enemy     enemy array from we will select one enemy
	 * @param level     level object
	 * @param copy      level object of the copy
	 * @param levelCopy copy of the enemy array from we will select one enemy
	 */
	public static void selectEnemyToAttack(Enemy[] enemy, Level level, Enemy[] copy, Level levelCopy) {
		// We generate a random number between 0 and 100
		int mayAttack = (int) (Math.random() * 50);
		// Variable to restrict the number of goeis that go with their commander
		int restriction = 0;
		// If the random number is equal to 0, that is, 1/50 of the times an enemy
		// will attack
		if ((mayAttack == 0)) {
			// We generate a random number to select which enemy is going to attack
			// attacker will be the index of the array
			int attacker = (int) (Math.random() * enemy.length);
			// If the enemy we have selected is alive and is not already attacking we set
			// his attacking field to true
			if (enemy[attacker].getHp() > 0 && !enemy[attacker].getAttacking()) {
				// If the enemy that attacks is a commander, we need to make some goeis to
				// attack with him
				if (enemy[attacker].getName().equals("Commander")) {

					for (int index = 0; index < level.getGoei().length; index++) {
						// We search for a goei that has health and that is not attacking
						if (level.getGoei()[index].getHp() > 0 && !level.getGoei()[index].getAttacking()) {
							// We use the restriction variable to only pick two goeis
							if (restriction < 2) {
								// We change the attacking field of the enemy
								level.getGoei()[index].setAttacking(true);
								// We "randomly" select an attack
								level.getGoei()[index].setTypeAtt((int) (Math.random() * 3));
								// We increment the restriction variable
								restriction++;
								// We copy the image from the copy enemy so that that the enemy doesn't have the
								// the flapping sprite when it enters the attack animation
								level.getGoei()[index].setImage(levelCopy.getGoei()[index].getSimpleImage());
							}
						}
					}
				}
				// If the name is other than commander or it is commander but its hp is 2
				// We set the corresponding image of the enemy from the copy
				if (enemy[attacker].getName() != "Commander"
						|| (enemy[attacker].getName() == "Commander" && enemy[attacker].getHp() == 2)) {
					enemy[attacker].setImage(copy[attacker].getSimpleImage());
				} else {
					// If the enemy is a commander has 1 hp, we set the corresponding 01image
					enemy[attacker].setImage("enemy9.png");
				}
				// We set the attacking field to true
				enemy[attacker].setAttacking(true);
				// We "randomly" select an attack
				enemy[attacker].setTypeAtt((int) (Math.random() * 3));

			}
		}

	}

	/**
	 * This animation invokes the function to perform the attack animations for each
	 * array of enemies of the level
	 * 
	 * @param level the level object that contains the arrays of enemies
	 * @param board
	 */
	public static void attackAnimation(Level level, GameBoardGUI board) {
		attackAnimationOneEnemy(level.getZako(), board);
		attackAnimationOneEnemy(level.getGoei(), board);
		attackAnimationOneEnemy(level.getCommander(), board);
	}

	/**
	 * function that performs the attack animations for one array of enemies
	 * 
	 * @param enemy the array of enemies we want to move
	 * @param board
	 */
	public static void attackAnimationOneEnemy(Enemy[] enemy, GameBoardGUI board) {
		// We iterate through every enemy in the array
		for (int index = 0; index < enemy.length; index++) {
			// We check which enemies are attacking and if they are alive
			if (enemy[index].getAttacking() && enemy[index].getHp() > 0) {
				/*
				 * The enemy will perform its attack animation while the attack order(index of
				 * the array of attacks) is lower than the dimension of the array of attacks
				 * 
				 */
				if (enemy[index].getOrderAtt() < Constants.ATT[enemy[index].getTypeAtt()].length) {
					// We use the move method with the type and order of attack of the enemy fields,
					// 1 step
					enemy[index].move(Constants.ATT[enemy[index].getTypeAtt()][enemy[index].getOrderAtt()], 1);
					// Incrementing the order so that the next time it takes the following direction
					enemy[index].setOrderAtt(enemy[index].getOrderAtt() + 1);
				} else {

					/**
					 * In case the enemy has already finished one movement, we set the order to zero
					 * and we generate randomly the next type of movement it will make
					 */
					enemy[index].setOrderAtt(0);
					enemy[index].setTypeAtt((int) (Math.random() * 3));
				}
				// Now we repaint the board and reset the sprite
				board.gb_repaintBoard();
				setSprite(enemy[index], board);
			}
		}
	}

	/**
	 * With this function we invoke the function to teleport each array of enemies
	 * when they reach the bottom of the screen
	 * 
	 * @param level
	 * @param board
	 */

	public static void teleportEnemy(Level level, GameBoardGUI board) {
		teleportEnemySpecific(level.getCommander(), board);
		teleportEnemySpecific(level.getGoei(), board);
		teleportEnemySpecific(level.getZako(), board);
	}

	/**
	 * This function moves an enemy to the top of the board in case it reaches the
	 * bottom
	 * 
	 * @param enemy
	 * @param board
	 */
	public static void teleportEnemySpecific(Enemy[] enemy, GameBoardGUI board) {
		for (int index = 0; index < enemy.length; index++) {
			// If the Y of the enemy is below the board we set the enemy Y to 5
			if (enemy[index].getY() > 239) {
				enemy[index].setY(5);
				// We update the sprite
				setSprite(enemy[index], board);
				/*
				 * Here we reestablish the order of the attack to zero so that next time it
				 * attacks, it makes the first movement from the array of attacks
				 */
				enemy[index].setOrderAtt(0);
				/*
				 * We set the boolean field of the attack to false to indicate that it has ended
				 * the attack
				 */
				enemy[index].setAttacking(false);
				// We make an extra movement with north direction to reestablish the direction
				// to north so we get the correct image

				enemy[index].move(Constants.DIR_N, 1);
			}
		}
	}

	/**
	 * This function is used to invoke the functions to return the enemies to
	 * formation
	 * 
	 * @param level
	 * @param copy
	 * @param board
	 */

	public static void returnToFormation(Level level, Level copy, GameBoardGUI board) {
		returnToFormationSpecific(level.getCommander(), copy.getCommander(), board);
		returnToFormationSpecific(level.getZako(), copy.getZako(), board);
		returnToFormationSpecific(level.getGoei(), copy.getGoei(), board);
	}

	/**
	 * Moves an enemy back to the formation
	 * 
	 * @param enemy the Array of one type of enemy
	 * 
	 * @param copy  the Array of the same type of enemy that stores the position the
	 *              enemy stopped attacking needs to go
	 * @param board
	 */
	public static void returnToFormationSpecific(Enemy[] enemy, Enemy[] copy, GameBoardGUI board) {

		// This for loop is to make the enemies move faster
		for (int index0 = 0; index0 < 4; index0++) {
			// For each enemy we check if the enemy is not attacking and is not in
			// formation.
			// Then we return it to its position
			for (int index = 0; index < enemy.length; index++) {
				// We check if the enemy is attacking by comparing the x and the y coordinates
				// with the respective enemy in copy and if the enemy is has finished its attack
				if ((enemy[index].getX() != copy[index].getX() || enemy[index].getY() != copy[index].getY())
						&& !enemy[index].getAttacking()) {

					// We compare the position of the enemy with the copy and
					// We update the x and the y coordinates until is the same
					// This way we move the enemy to formation
					if (enemy[index].getX() > copy[index].getX()) {
						enemy[index].setX(enemy[index].getX() - 1);

					} else if (enemy[index].getX() < copy[index].getX()) {
						enemy[index].setX(enemy[index].getX() + 1);
					}

					if (enemy[index].getY() > copy[index].getY()) {
						enemy[index].setY(enemy[index].getY() - 1);

					} else if (enemy[index].getY() < copy[index].getY()) {
						enemy[index].setY(enemy[index].getY() + 1);
					}

					// We make visible the new positions of the array of enemies
					board.gb_repaintBoard();
					setSprite(enemy[index], board);
				}

			}
		}

	}

	/*
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * _________________________________ENTRY ANIMATIONS___________________________
	 */////////////////////////////////////////////////////////////////////////////

	/**
	 * This function positions the enemies for the entry animation. We have decided
	 * that the enemies will start always from the same position but will perform
	 * different movements depending on the level. We divide each enemy array into
	 * two and will place the groups symmetrically
	 * 
	 * @param level
	 * @param board
	 */
	public static void positionForEntryAnimation(Level level, GameBoardGUI board) {
		// Here we want to position the Commanders
		for (int index = 0; index < level.getCommander().length; index++) {
			level.getCommander()[index].setX(85);
			level.getCommander()[index].setY(5);
		}
		// Position the goei. Two for loops, one for each half of the type of enemy
		for (int index = 0; index < level.getGoei().length / 2; index++) {
			level.getGoei()[index].setX(165);
			level.getGoei()[index].setY(215);
		}
		for (int index = level.getGoei().length / 2; index < level.getGoei().length; index++) {
			level.getGoei()[index].setX(5);
			level.getGoei()[index].setY(215);
		}

		// Position the zakos
		for (int index = 0; index < level.getZako().length / 2; index++) {
			level.getZako()[index].setX(165);
			level.getZako()[index].setY(155);
		}
		for (int index = level.getZako().length / 2; index < level.getZako().length; index++) {
			level.getZako()[index].setX(5);
			level.getZako()[index].setY(155);
		}

		// We invoke the function to set the sprites of the array of enemies
		setSpriteArray(level.getCommander(), board);
		setSpriteArray(level.getGoei(), board);
		setSpriteArray(level.getZako(), board);

	}

	/**
	 * This function is to perform the entry animation of the enemies. This function
	 * will be used to invoke the animate one array that will perform the entry
	 * animations for one array.
	 * 
	 * @param level
	 * @param copy
	 * @param control this variable represents the current direction the the
	 *                enemies. It is the index of the createTrajectoryEnemy
	 * 
	 * @param board
	 * @return It returns a boolean that is true if the entry animation has been
	 *         performed and false if it is still unfinished
	 */
	public static boolean animate(Level level, Level copy, int control, GameBoardGUI board) {
		/*
		 * We create three enemies to store the result of the function for each array of
		 * enemies. We will compare the three booleans in order to return true or false
		 */
		boolean commanderInPosition;
		boolean zakoInPosition;
		boolean goeiInPosition;

		// We invoke the function only for the commander
		commanderInPosition = animateOneArray(level.getCommander(), copy.getCommander(),
				level.createTrajectoryCommander(), control, board);

		// We invoke the funciton only for the zako
		zakoInPosition = animateOneArray(level.getZako(), copy.getZako(), level.createTrajectoryZako(), control, board);
		// We invoke the function only for the goei
		goeiInPosition = animateOneArray(level.getGoei(), copy.getGoei(), level.createTrajectoryGoei(), control, board);

		/*
		 * If the three of the functions return true, that means the entry animation has
		 * finished. Otherwise it would return false.
		 */

		if (commanderInPosition && zakoInPosition && goeiInPosition) {
			return true;
		}

		else {
			return false;
		}

	}

	/**
	 * Performs the entry animations of one array of enemies
	 * 
	 * @param enemy                 array of enemies we want to move
	 * 
	 * @param copy                  array of enemies of the same type that contain
	 *                              the position in the swarm formation
	 * 
	 * @param createTrajectoryEnemy array of directions in which the enemy must move
	 * 
	 * 
	 * @param control               this variable represents the current direction
	 *                              the the enemies. It is the index of the
	 *                              createTrajectoryEnemy
	 * 
	 * @param board                 board object
	 * @return
	 */

	public static boolean animateOneArray(Enemy[] enemy, Enemy[] copy, int[] createTrajectoryEnemy, int control,
			GameBoardGUI board) {

		/*
		 * In case the name of the enemy is commander we check if it has been hit by a
		 * player. In case the life points of a commander is 1 we change the image to
		 * the respective one
		 */
		if (enemy[0].getName().equals("Commander"))
			for (int index = 0; index < enemy.length; index++) {
				if (enemy[index].getHp() == 1) {
					enemy[index].setImage("enemy9.png");
					setSprite(enemy[index], board);
				}
			}
		/*
		 * If the number of the index (control) of the array of directions is less than
		 * the maximum possible position in the array, we move the array. Otherwise, we
		 * will move the enemies to their position in the swarm formation
		 */
		if (control < createTrajectoryEnemy.length) {

			/*
			 * We use the move method for the first enemy placed in each position. For all
			 * the others, we will copy the X and Y coordinates and the direction of the
			 * previous enemy in the array.
			 */

			/*
			 * With a for loop we copy the direction and the x and y coordinates of the
			 * previous enemy for the first half of the array. Then we set the sprite of the
			 * enemy.
			 */
			for (int index = (enemy.length / 2) - 1; index > 0; index--) {

				enemy[index].setX(enemy[index - 1].getX());
				enemy[index].setY(enemy[index - 1].getY());
				enemy[index].setDirection(enemy[index - 1].getDirection());
				setSprite(enemy[index], board);
			}

			// Here we move the first enemy with the trajectory array and the current index
			// We set the sprite as well
			enemy[0].move(createTrajectoryEnemy[control], 2);
			setSprite(enemy[0], board);

			// Here we do the same operation with the other half of the array

			for (int index = (enemy.length) - 1; index > (enemy.length / 2); index--) {

				enemy[index].setX(enemy[index - 1].getX());
				enemy[index].setY(enemy[index - 1].getY());
				enemy[index].setDirection(enemy[index - 1].getDirection());
				setSprite(enemy[index], board);
			}

			// Now we move the first enemy from the second half the array. For these enemies
			// we use the mirror move method, that makes the same as the move method but
			// inverts the direction for the X
			enemy[enemy.length / 2].mirrorMove(createTrajectoryEnemy[control], 2);
			setSprite(enemy[enemy.length / 2], board);

		} else {

			/*
			 * In case the enemies have passed through all the positions of the array of
			 * directions, they go to the formation
			 * 
			 * We use a for loop that is repeated four times to make the movement faster
			 */

			for (int index0 = 0; index0 < 4; index0++) {

				/*
				 * We iterate through the array of enemies
				 */
				for (int index = 0; index < enemy.length; index++) {

					/*
					 * We compare the position of each enemy with the position of the copy and
					 * depending on the difference we change the coordinates of the enemy until the
					 * position is the same
					 */
					if (enemy[index].getX() > copy[index].getX()) {
						enemy[index].setX(enemy[index].getX() - 1);

					} else if (enemy[index].getX() < copy[index].getX()) {
						enemy[index].setX(enemy[index].getX() + 1);
					}

					if (enemy[index].getY() > copy[index].getY()) {
						enemy[index].setY(enemy[index].getY() - 1);
					} else if (enemy[index].getY() < copy[index].getY()) {
						enemy[index].setY(enemy[index].getY() + 1);
					}
					setSprite(enemy[index], board);
				}

			}
		}

		/*
		 * If any of the enemies of the array has 0 Health points and doesn't coincide
		 * with the copy we return false
		 */
		for (int index = 0; index < enemy.length; index++) {
			if (enemy[index].getHp() > 0
					&& ((enemy[index].getX() != copy[index].getX() || enemy[index].getY() != copy[index].getY()))) {

				return false;
			}
		}

		// If it comes out of the loop and all the positions are correct, returns true
		return true;
	}

	/**
	 * Function to show a dialog message with some tips
	 * 
	 * @return It just returns a string to be shown
	 */

	/*
	 * \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * _________________________________EXTRAS_____________________________________
	 */////////////////////////////////////////////////////////////////////////////
	public static String giveMeTips() {

		/*
		 * We create an array of strings and we return one of them using a math.random
		 * for the index
		 */
		String[] tips = { "Press exit game to quit", "You need to hit a Commander twice to kill it!",
				"Be careful, Commanders will bring some allies in their attacks", "Press space bar to shoot",
				"Enjoy the entry animations!", "Press up or down to change the colour of your ship!",
				"Get to higher levels and unlock new spaceships" };
		return tips[(int) (Math.random() * tips.length)];
	}

	/**
	 * This function is used to update some of the data in each collision of enemy
	 * with player torpedoes and prints in the GUI the enemy that has been killed
	 * and the points obtained
	 * 
	 * @param level
	 * @param enemy  the enemy that has been killed
	 * @param board
	 * @param player
	 */
	public static void pointsUp(Level level, Enemy enemy, Player player, GameBoardGUI board) {
		// We increment the number of hits of the player with a method that increases
		// them by 1
		player.incrementHits();
		// We reduce the health points of the enemy
		enemy.setHp(enemy.getHp() - 1);
		if (enemy.getHp() == 0) {
			// We update the deadEnemies field independently of which kind of enemy it is
			level.setDeadEnemies(level.getDeadEnemies() + 1);
			// We update the points of the player depending of the type of enemy that has
			// been killed and we show it on the GUI
			switch (enemy.getName()) {
			case "Zako":
				player.setPoints(player.getPoints() + 100);
				board.gb_println(enemy.getName() + " " + enemy.getId() + " was killed: 100 points");
				break;
			case "Goei":
				player.setPoints(player.getPoints() + 250);
				board.gb_println(enemy.getName() + " " + enemy.getId() + " was killed: 250 points");

				break;
			case "Commander":
				player.setPoints(player.getPoints() + 400);
				board.gb_println(enemy.getName() + " " + enemy.getId() + " was killed: 400 points");

				break;
			}

		}
	}

}
