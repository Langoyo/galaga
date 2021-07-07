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
 * We create the class Constants to store values that will not change such as
 * directions
 */
public class Constants {
	// We create a constant for each of the directions
	public static final int DIR_N = 0;
	public static final int DIR_NNE = 1;
	public static final int DIR_NE = 2;
	public static final int DIR_ENE = 3;
	public static final int DIR_E = 4;
	public static final int DIR_ESE = 5;
	public static final int DIR_SE = 6;
	public static final int DIR_SSE = 7;
	public static final int DIR_S = 8;
	public static final int DIR_SSW = 9;
	public static final int DIR_SW = 10;
	public static final int DIR_WSW = 11;
	public static final int DIR_W = 12;
	public static final int DIR_WNW = 13;
	public static final int DIR_NW = 14;
	public static final int DIR_NNW = 15;

	/*
	 * The move deltas for the directions: dX,dY Moving in direction i (0<=i<=15)
	 * means adding MOVES[i][0] to the current x and MOVES[i][1] to the current y
	 */
	public static final int[][] MOVES = { { 0, -4 }, // DIR_N
			{ 1, -4 }, // DIR_NNE
			{ 3, -3 }, // DIR_NE
			{ 4, -1 }, // DIR_ENE
			{ 4, 0 }, // DIR_E
			{ 4, 1 }, // DIR_ESE
			{ 3, 3 }, // DIR_SE
			{ 1, 4 }, // DIR_SSE
			{ 0, 4 }, // DIR_S
			{ -1, 4 }, // DIR_SSW
			{ -3, 3 }, // DIR_SW
			{ -4, 1 }, // DIR_WSW
			{ -4, 0 }, // DIR_W
			{ -4, -1 }, // DIR_WNW
			{ -3, -3 }, // DIR_NW
			{ -1, -4 }, // DIR_NNW
	};
	/*
	 * We create the directions that the enemies follow for their entry animations
	 * for each level
	 */
	// Commander entry animations directions

	// Level 1
	public static final int[] COM_ENTRY1 = { DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S,
			DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_E, DIR_E, DIR_E, DIR_E, DIR_E, DIR_E,
			DIR_E, DIR_NE, DIR_NE, DIR_N, DIR_N, DIR_N, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW,
			DIR_NW, DIR_NW, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, };
	// Level 2
	public static final int[] COM_ENTRY2 = { DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S,
			DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_SSE, DIR_SE, DIR_ESE, DIR_E,
			DIR_ENE, DIR_NE, DIR_NNE, DIR_N, DIR_NNW, DIR_NW, DIR_WNW, DIR_W, DIR_WSW, DIR_SW, DIR_SSW, DIR_NW, DIR_NW,
			DIR_SW, DIR_SW, DIR_NW, DIR_NW, DIR_SW, DIR_SW, DIR_NW, DIR_NW, DIR_W, DIR_W, DIR_N, DIR_N, DIR_NE, DIR_NE,
			DIR_NE, DIR_NE, DIR_NE, DIR_NE, DIR_NE, DIR_NE, DIR_NE, DIR_NE, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N,
			DIR_N };
	// Level 3
	public static final int[] COM_ENTRY3 = { DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S,
			DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S, DIR_SSW, DIR_SW, DIR_WSW,
			DIR_W, DIR_WNW, DIR_NW, DIR_NNW, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N,
			DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N };

	// Zako entry animations directions

	// Level 1
	public static final int[] ZAK_ENTRY1 = { DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W,
			DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_WSW, DIR_SW, DIR_SSW, DIR_S, DIR_SSE, DIR_SE, DIR_ESE, DIR_E,
			DIR_ENE, DIR_NE, DIR_NNE, DIR_N, DIR_NNW, DIR_NW, DIR_WNW, DIR_W, DIR_WSW, DIR_SW, DIR_SSW, DIR_S, DIR_SSE,
			DIR_SE, DIR_ESE, DIR_E, DIR_ENE, DIR_NE, DIR_NNE, DIR_N, DIR_NNW, DIR_NW, DIR_WNW, DIR_W, DIR_WSW, DIR_SW,
			DIR_SSW, DIR_S, DIR_SSE, DIR_SE, DIR_ESE, DIR_E, DIR_ENE, DIR_NE, DIR_NNE, DIR_N, DIR_N, DIR_N, DIR_N,
			DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N };
	// Level 2
	public static final int[] ZAK_ENTRY2 = { DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N,
			DIR_N, DIR_N, DIR_N, DIR_NNW, DIR_NW, DIR_WNW, DIR_W, DIR_W, DIR_WSW, DIR_SW, DIR_SW, DIR_SW, DIR_SW,
			DIR_SSW, DIR_S, DIR_SSE, DIR_SE, DIR_SE, DIR_SE, DIR_SSE, DIR_S, DIR_SSW, DIR_SW, DIR_SW, DIR_SW, DIR_WSW,
			DIR_W, DIR_WNW, DIR_NW, DIR_NNW, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N,
			DIR_N, DIR_N, DIR_N, DIR_N, DIR_N };
	// Level 3
	public static final int[] ZAK_ENTRY3 = { DIR_W, DIR_W, DIR_W, DIR_WNW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW,
			DIR_NW, DIR_NW, DIR_NW, DIR_WNW, DIR_W, DIR_W, DIR_W, DIR_W, DIR_WSW, DIR_SW, DIR_SSW, DIR_S, DIR_S, DIR_S,
			DIR_S, DIR_S, DIR_S, DIR_S, DIR_SSE, DIR_SE, DIR_ESE, DIR_E, DIR_ENE, DIR_NE, DIR_NE, DIR_NE, DIR_NNE,
			DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N,
			DIR_N, DIR_N, DIR_N, DIR_N, DIR_N };

	// Goei entry animations directions

	// Level 1
	public static final int[] GOE_ENTRY1 = { DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_NW, DIR_NW,
			DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W,
			DIR_WSW, DIR_SW, DIR_SSW, DIR_S, DIR_SSE, DIR_SE, DIR_ESE, DIR_E, DIR_E, DIR_E, DIR_E, DIR_E, DIR_E, DIR_E,
			DIR_E, DIR_E, DIR_ENE, DIR_NE, DIR_NNE, DIR_NNW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW,
			DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N };
	// Level 2
	public static final int[] GOE_ENTRY2 = { DIR_N, DIR_N, DIR_N, DIR_N, DIR_NNW, DIR_NW, DIR_WNW, DIR_W, DIR_W, DIR_W,
			DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_WNW, DIR_NW, DIR_NNW, DIR_N, DIR_NNE, DIR_NE,
			DIR_ENE, DIR_E, DIR_E, DIR_E, DIR_E, DIR_E, DIR_E, DIR_E, DIR_E, DIR_ENE, DIR_NE, DIR_NNE, DIR_N, DIR_NNW,
			DIR_NW, DIR_WNW, DIR_W, DIR_WNW, DIR_NW, DIR_NNW, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N };
	// Level 3
	public static final int[] GOE_ENTRY3 = { DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N,
			DIR_N, DIR_NNW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_NW, DIR_WNW, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W,
			DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_W, DIR_WSW, DIR_SW, DIR_SSW, DIR_S, DIR_S, DIR_S, DIR_S, DIR_S,
			DIR_S, DIR_SSE, DIR_SE, DIR_ESE, DIR_E, DIR_E, DIR_E, DIR_E, DIR_ENE, DIR_NE, DIR_NNE, DIR_N, DIR_N, DIR_N,
			DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N, DIR_N };

	// We create the directions of each one of the attacks enemies can perform
	// Attack 1-ZIG-ZAG
	public static final int[] ATT1 = { DIR_S, DIR_S, DIR_S, DIR_S, DIR_SW, DIR_SW, DIR_SW, DIR_SE, DIR_SE, DIR_SE,
			DIR_S, DIR_S, DIR_S, DIR_S, DIR_S };
	// Attack 2 CIRCLE
	public static final int[] ATT2 = { DIR_S, DIR_S, DIR_S, DIR_S, DIR_SSW, DIR_SW, DIR_WSW, DIR_W, DIR_WNW, DIR_NW,
			DIR_NNW, DIR_N, DIR_NNE, DIR_NE, DIR_ENE, DIR_E, DIR_ESE, DIR_SE, DIR_SSE, DIR_S, DIR_S, DIR_S, DIR_S };
	// Attack 3 INVERSE CIRCLE
	public static final int[] ATT3 = { DIR_S, DIR_S, DIR_S, DIR_S, DIR_SSE, DIR_SE, DIR_ESE, DIR_E, DIR_ENE, DIR_NE,
			DIR_NNE, DIR_N, DIR_NNW, DIR_NW, DIR_WNW, DIR_W, DIR_WSW, DIR_SW, DIR_SSW, DIR_S, DIR_S, DIR_S, DIR_S };

	/*
	 * In this matrix we store each one of the attacks patterns so later we can use
	 * a random to select one of them
	 */
	public static final int[][] ATT = { ATT1, ATT2, ATT3 };
	// We create two Constants where we store the size of the board
	public static final int BOARD_WIDTH = 17;
	public static final int BOARD_HEIGHT = 22;
	// We create a constant for the Y position of the player as it is fixed
	public static final int PLAYER_Y = 205;
	// The maximum life of the player will always be 3 so we create a Constant
	public static final int MAX_HP_PLAYER = 3;
}
