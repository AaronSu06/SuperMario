/* Name: Aaron Su
 * Date: May 28, 2023
 * Course Code: ICS - 3U1
 * Title: SDP #2 Individual Project Video Game
 * Description: This class acts as a utility class to load images onto map (player, walls, coins, etc.)
 * Major Skills: Arrays, Swing GUI Components 
 * Added Features: Facing different directions, Extra Images
 * Contribution to Assignment: Aaron Su - 100%
 */

// import our necessities 
import javax.swing.*;

public class Icons {
	// create icons in the game
	public static final ImageIcon WALL = new ImageIcon("images/Wall.png"); // brick in the game map
	public static final ImageIcon COIN = new ImageIcon("images/coin.png"); // coin in the game map
	public static final ImageIcon MUSHROOM = new ImageIcon("images/Mushroom.png"); // mushroom in the game map
	public static final ImageIcon PIPE = new ImageIcon("images/Pipe.png"); // pipe in the game map
	public static final ImageIcon[] MARIO = { new ImageIcon("images/mario.gif"), new ImageIcon("images/mario1.gif") }; // character
																														// Mario
																														// in
																														// the
																														// game
																														// map,
																														// which
																														// is
																														// facing
																														// different
																														// directions
																														// to
																														// later
																														// correspond
																														// to
																														// user
																														// input

}
