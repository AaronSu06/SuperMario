/* Name: Aaron Su
 * Date: May 28, 2023
 * Course Code: ICS - 3U1
 * Title: SDP #2 Individual Project Video Game
 * Description: This class handles player movement. it includes other actions such as collecting coins, and represents main character in game.
 * Major Skills: Constructor, Getters and Setters, Timer, Swing and Awt GUI Components, ActionListener, Music, If Statements
 * Added Features: Facing different directions, Timer, Score, Sound Effects, New Physics
 * Contribution to Assignment: Aaron Su - 100%
 */

// import our necessities 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Character extends JLabel implements ActionListener {

	// create our instance variables
	JFrame f;
	public static JLabel scoreLabel = new JLabel("Score: 0");
	public static JLabel timerLabel = new JLabel("Time: 0");

	private ImageIcon[] iconArray; // if u want character to face different directions in game
	private ImageIcon icon; // if u want character static

	private String[] key;
	private int dX, dY;
	private Timer jumpTimer = new Timer(25, this);
	private int jumpCounter;
	private boolean jumping = false;
	private Timer fallTimer = new Timer(25, this);
	private int fallCounter;
	private boolean falling = false;

	// global variables to which we can use throughout the entire project
	double dir = 1;
	int coinCounter = 0;
	int score = 0;
	int frame = 1;
	int time = 0;
	Timer effectTimer;
	Timer gameTimer;

	// constructor for the character
	public Character(ImageIcon[] iconArray, String[] key) {
		super();
		setIcon(iconArray[0]);
		this.iconArray = iconArray;
		this.key = key;
	}

	// getters/setters
	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public String[] getKey() {
		return key;
	}

	public void setKey(String[] key) {
		this.key = key;
	}

	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	public int getdY() {
		return dY;
	}

	public void setdY(int dY) {
		this.dY = dY;
	}

	public Timer getJumpTimer() {
		return jumpTimer;
	}

	public void setJumpTimer(Timer jumpTimer) {
		this.jumpTimer = jumpTimer;
	}

	public int getJumpCounter() {
		return jumpCounter;
	}

	public void setJumpCounter(int jumpCounter) {
		this.jumpCounter = jumpCounter;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public Timer getFallTimer() {
		return fallTimer;
	}

	public void setFallTimer(Timer fallTimer) {
		this.fallTimer = fallTimer;
	}

	public int getFallCounter() {
		return fallCounter;
	}

	public void setFallCounter(int fallCounter) {
		this.fallCounter = fallCounter;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	/// utility methods

	// makes char jump
	public void jump() {
		PlaySong.playMusic("Music/Mario Jump Sound.wav", 0);

		jumping = true;
		jumpCounter = 0;
		jumpTimer.start();
	}

	// makes char fall
	public void fall() {
		falling = true;
		fallCounter = 0;
		fallTimer.start();
	}

	// move left
	public void moveLeft() {
		setIcon(iconArray[1]);
		dX = (int) (-5 * dir); // declare negative for left
		System.out.println(dX); // print for debugging
		setBounds(getX() + dX, getY(), 25, 25);
	}

	// move right
	public void moveRight() {
		setIcon(iconArray[0]);
		dX = (int) (5 * dir); // declare positive for right
		System.out.println(dX); // print for debugging
		setBounds(getX() + dX, getY(), 25, 25);
	}

	public int getRow() {
		return (int) getY() / 25;
	}

	public int getCol() {
		return (int) getX() / 25;
	}

	// handles coin collisions
	public JLabel collectCoin() {
		int row = getRow();
		int col = getCol();

		if (LevelFrame.boardArray[row][col].getIcon() == Icons.COIN) {
			// calls the PlaySong method for the sound
			PlaySong.playMusic("Music/Mario Coin Sound.wav", 0);
			// delete coin from board when collected
			LevelFrame.boardArray[row][col].setIcon(null);

			// increment and set the score. Additionally, increments the coinCounter to be
			// used for future conditions
			coinCounter++;
			score += 100;
			System.out.println("Score: " + score);
			System.out.println("Coins: " + coinCounter);
			scoreLabel.setText("Score: " + score);
		}

		// return the label to be formatted in LevelFrame
		return scoreLabel;
	}

	// handles mushroom collisions
	public JLabel collectMushroom() {
		int row = getRow();
		int col = getCol();

		if (LevelFrame.boardArray[row][col].getIcon() == Icons.MUSHROOM) {
			// calls the PlaySong method for the sound
			PlaySong.playMusic("Music/Mario Mushroom Sound.wav", 0);
			// delete mushroom from board when collected
			LevelFrame.boardArray[row][col].setIcon(null);

			// increment and set the score
			score += 200;
			System.out.println("Score: " + score);
			scoreLabel.setText("Score: " + score);

			// increase speed by 1.7x
			dir = 1.7;

			// create a 5 second delay using Timer, and when the ActionListner is called,
			// reset speed to the default
			effectTimer = new Timer(3000, new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if (event.getSource() == effectTimer) {
						dir = 1;
					}
				}
			});

			effectTimer.start();
		}

		// return the label to be formatted in LevelFrame
		return scoreLabel;
	}

	// handles pipe collisions. Sourced from
	// https://stackoverflow.com/questions/557903/how-can-i-wait-for-a-java-sound-clip-to-finish-playing-back)
	public void touchedPipe() {
		int row = getRow();
		int col = getCol();

		// this will first check if the amount of coins collected in the first map is 4,
		// and then check if they have touched the pipe. You must collect all coins in a
		// map before continuing to a new level
		if (coinCounter == 4) {
			if (LevelFrame.boardArray[row][col].getIcon() == Icons.PIPE) {
				// calls the PlaySong method for the sound
				PlaySong.playMusic("Music/Mario Pipe Sound.wav", 0);

				// dispose of the current frame
				TitleFrame.level.dispose();
				frame = 2;
				// swap to a new level frame
				TitleFrame.level = new LevelFrame(2);

				// print for debugging
				System.out.println("Frame " + frame);
			}
		}
	}

	// handles the timer label. Sourced from
	// https://stackoverflow.com/questions/557903/how-can-i-wait-for-a-java-sound-clip-to-finish-playing-back)
	public JLabel timer() {
		// creates a new timer that occurs every second
		gameTimer = new Timer(1000, new ActionListener() {
			// creates an action performed method when the timer is called, where we start
			// and set the time every second, or stops the timer if coins collected is 9.
			// Additionally, it will display a JOptionPane window that congrats the user on
			// winning, and exits on close / click
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == gameTimer) {
					time++;
					timerLabel.setText("Time: " + time);
				}

				if (coinCounter == 9) {
					gameTimer.stop();
					JOptionPane.showMessageDialog(f, "Congratulations On Beating Super Mario!");
					System.exit(0);
				}
			}
		});

		gameTimer.start();

		// return the label to be formatted in LevelFrame
		return timerLabel;
	}

	// deals with physics/collisions

	@Override
	// this method is responsible for checking for collisions with the ground when
	// the character is jumping to the ceiling and falling to the down
	public void actionPerformed(ActionEvent e) {

		if (jumping && dY < 0 && LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.WALL) {
			jumping = false;
			dY = 0;
			jumpTimer.stop();
			fall();
			return;
		}

		// Considers if we are jumping off and hitting the ground (falling)
		if (falling && dY > 0) {
			int nextRow = getRow() + 1;
			if (nextRow >= LevelFrame.boardArray.length
					|| LevelFrame.boardArray[nextRow][getCol()].getIcon() == Icons.WALL) {
				falling = false;
				dY = 0;
				fallTimer.stop();
				return;
			}
		}

		// Considers when jumping should be true or false
		// checks if we are on Frame 1, and changes jump settings based on that
		if (frame == 1) {

			// checks if we are jumping
			if (jumping) {
				jumpCounter++;
				if (jumpCounter <= 10) { // going up
					dY = -5;
				}

				else if (jumpCounter <= 20) { // going down
					dY = 5;
				}

				else { // reset to stop jumping
					jumping = false;
					dY = 0;
					jumpTimer.stop();
					fall();
				}
			}

			// checks if we are falling
			else if (falling) {
				fallCounter++;
				dY = 5; // continuously move the character down 5 units
				setBounds(getX(), getY() + dY, 25, 25);

				if (LevelFrame.boardArray[getRow() + 1][getCol()].getIcon() == Icons.WALL) { // checks for collision
																								// with the ground
					falling = false;
					dY = 0;
					fallTimer.stop();
				}

				else if (fallCounter >= 20) { // stops falling once we reach the ground
					falling = false;
					dY = 0;
					fallTimer.stop();
				}

				return; // exits the method
			}
		}

		// checks if we are on Frame 2, and changes jump settings based on that
		else if (frame == 2) {

			// checks if we are jumping
			if (jumping) {
				jumpCounter++;
				if (jumpCounter <= 20) { // going up by double
					dY = -5;
				}

				else if (jumpCounter <= 40) { // going down by double
					dY = 5;
				}

				else { // reset to stop jumping
					jumping = false;
					dY = 0;
					jumpTimer.stop();
					fall();
				}
			}

			// checks if we are falling
			else if (falling) {
				fallCounter++;
				dY = 5; // continuously move the character down 5 units
				setBounds(getX(), getY() + dY, 25, 25);

				if (LevelFrame.boardArray[getRow() + 1][getCol()].getIcon() == Icons.WALL) { // checks for collision
																								// with the ground
					falling = false;
					dY = 0;
					fallTimer.stop();
				}

				else if (fallCounter >= 40) { // stops falling once we reach the ground, but is doubled since our
												// gravity has "changed"
					falling = false;
					dY = 0;
					fallTimer.stop();
				}

				return; // exits the method
			}
		}

		// this block of code checks to see if the character is jumping and touches the
		// wall. If true, change the dX and / or dY to 0
		if (LevelFrame.boardArray[getRow()][getCol() + 1].getIcon() == Icons.WALL && dX > 0) {
			dX = 0;
		}

		else if (LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.WALL && dX < 0) {
			dX = 0;
		}

		// if the character is touching a ceiling, as well as moving upwards
		else if (LevelFrame.boardArray[getRow()][getCol()].getIcon() == Icons.WALL && dY < 0) {
			dY = 0;
		}

		// if the character is touching the ground, as well as moving downwards
		else if (LevelFrame.boardArray[getRow() + 1][getCol()].getIcon() == Icons.WALL && dY > 0) {
			dY = 0;
		}

		collectCoin();
		collectMushroom();
		touchedPipe();

		setBounds(getX() + dX, getY() + dY, 25, 25);
	}
}
