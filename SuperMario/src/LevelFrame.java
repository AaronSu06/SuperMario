/* Name: Aaron Su
 * Date: May 28, 2023
 * Course Code: ICS - 3U1
 * Title: SDP #2 Individual Project Video Game
 * Description: This class creates the frame for our designated level. The frame contains all sorts of information, such as Menu Bar, Key Bindings, ScoreBoard. etc.
 * Major Skills: Constructor, Scanner, Arrays, Swing and Awt GUI Components, ActionListener, Music, If Statements, For Loops, ActionMaps / InputMaps
 * Added Features: Creates a new level frame based on the .txt file and argument, ScoreBoard, Menu Bar, Added a background image that changes based on the level, 
 * Added extra objects / blocks in the map
 * Contribution to Assignment: Aaron Su - 100%
 */

// import our necessities 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

public class LevelFrame extends JFrame implements KeyListener {

	// create our global variables that can be used throughout the code
	public static JLabel[][] boardArray = new JLabel[20][25];
	private JPanel lvlPanel = new JPanel();
	public static Character mario = new Character(Icons.MARIO, new String[] { "a", "d", " " }); // creates an instance
																								// of the Character
																								// class

	ImageIcon backgroundImage;
	JLabel backgroundLabel = new JLabel();

	// LevelFrame constructor will be used to put everything together
	public LevelFrame(int level) {

		// set layout to null to manually move everything
		setLayout(null);

		// call all our methods
		loadLevel(level);
		createScoreBoard();
		createMenuBar();
		setupKeyBindings();
		createLvlPanel();

		// set the basic frame settings
		setSize(25 * boardArray[0].length, 25 * boardArray.length + 25); // size of the frame will be length of the row
																			// and length of the column
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	// loadLevel method will try and read from the .txt file that corresponds to the
	// parameter level. It will also change / set the background of the level based
	// on the parameter level
	private void loadLevel(int level) {
		// change the background image based on parameter int level
		if (level == 1) {
			backgroundImage = new ImageIcon("images/Frame1Background.png");
			Image image = backgroundImage.getImage();
			Image newImage = image.getScaledInstance(25 * boardArray[0].length, 25 * boardArray.length + 25,
					java.awt.Image.SCALE_SMOOTH);
			backgroundImage = new ImageIcon(newImage);
		}

		else if (level == 2) {
			backgroundImage = new ImageIcon("images/Frame2Background.png");
			Image image = backgroundImage.getImage();
			Image newImage = image.getScaledInstance(25 * boardArray[0].length, 25 * boardArray.length + 25,
					java.awt.Image.SCALE_SMOOTH);
			backgroundImage = new ImageIcon(newImage);
		}

		// set the Image to a JLabel, and then make it the size of the entire board
		backgroundLabel.setIcon(backgroundImage);
		backgroundLabel.setSize(25 * boardArray[0].length, 25 * boardArray.length + 25);

		// Create Scanner to get file
		try {
			Scanner inputFile = new Scanner(new File("level" + level + ".txt"));
			for (int row = 0; row < boardArray.length; row++) {
				char[] lineArray = inputFile.next().toCharArray(); // Convert Array into a Char Array, meaning that each
																	// element in the Array will hold a Char type from
																	// the file

				// once reading the file, each character / letter of the .txt file will be set
				// to an image in our Icon class
				for (int col = 0; col < boardArray[0].length; col++) {
					if (lineArray[col] == 'B') {
						boardArray[row][col] = new JLabel(Icons.WALL);
					}

					else if (lineArray[col] == 'C') {
						boardArray[row][col] = new JLabel(Icons.COIN);
					}

					else if (lineArray[col] == 'M') {
						boardArray[row][col] = new JLabel(Icons.MUSHROOM);
					}

					else if (lineArray[col] == 'P') {
						boardArray[row][col] = new JLabel(Icons.PIPE);
					}

					else {
						boardArray[row][col] = new JLabel();
					}
				}
			}
			inputFile.close();
		} catch (FileNotFoundException e) { // if the above code did not work, print out that an exception / error has
											// occurred
			e.printStackTrace();
		}

	}

	// createScoreBoard method will create and pack the score and the timer
	private void createScoreBoard() {

		JLabel scoreLabel = mario.collectCoin(); // set JLabel scoreLabel to the return of the collectCoin method in
													// Character class
		scoreLabel.setForeground(Color.WHITE); // set text color to white
		scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 15)); // set font
		scoreLabel.setBounds(230, 55, 100, 50); // set the position to which it will be seen

		JLabel timerLabel = mario.timer(); // set JLabel timerLabel to the return of the timer method in Character class
		timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15)); // set font
		timerLabel.setForeground(Color.WHITE); // set text color to white
		timerLabel.setBounds(360, 55, 100, 50); // set the position to which it will be seen

		// pack them
		add(scoreLabel);
		add(timerLabel);
	}

	// createMenuBar method will create and pack all the menu bar options, such as
	// resetting the game and quitting the game
	private void createMenuBar() {

		// get the image for the reset button, and set that image to JButton resetButton
		Image resetImage = new ImageIcon("images/ResetButton.png").getImage().getScaledInstance(140, 40,
				java.awt.Image.SCALE_SMOOTH);
		JButton resetButton = new JButton(new ImageIcon(resetImage));

		// reset everything when button is clicked - this includes the frame, score,
		// time, speed, coin counter, and level
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				TitleFrame.level.dispose();

				mario.frame = 1;
				mario.score = 0;
				Character.scoreLabel.setText("Score: 0");
				mario.time = 0;
				Character.timerLabel.setText("Time: 0");
				mario.dir = 1;
				mario.coinCounter = 0;
				TitleFrame.level = new LevelFrame(1);
			}
		});

		// get the image for the quit button, and set that image to JButton quitButton
		Image quitImage = new ImageIcon("images/QuitButton.png").getImage().getScaledInstance(140, 55,
				java.awt.Image.SCALE_SMOOTH);
		JButton quitButton = new JButton(new ImageIcon(quitImage));

		// force quit the entire program when button is clicked
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		resetButton.setBounds(304, 30, 140, 40); // set the position to which it will be seen
		resetButton.setBorderPainted(false); // set the button itself to be invisible, meaning that only the image can
												// be seen and clicked
		resetButton.setFocusable(false); // set the button to not be automatically focused / hovered

		quitButton.setBounds(180, 32, 140, 43); // set the position to which it will be seen
		quitButton.setBorderPainted(false); // set the button itself to be invisible, meaning that only the image can be
											// seen and clicked
		quitButton.setFocusable(false); // set the button to not be automatically focused / hovered

		// pack them
		add(resetButton);
		add(quitButton);
	}

	// createLvlPanel method will be used to actually set the positions of the
	// images on the map, based off of our loadLevel data
	private void createLvlPanel() {
		lvlPanel.setLayout(null); // set the layout of the level to null, meaning that we can manually change the
									// positions

		// loop through our entire board / level
		for (int row = 0; row < boardArray.length; row++) {

			for (int col = 0; col < boardArray[0].length; col++) {

				// check if it is currently looping through a Wall (char B). If so, set the
				// position, and add it to lvlPanel
				if (boardArray[row][col].getIcon() == Icons.WALL) {
					boardArray[row][col].setBounds(col * 25, row * 25, 25, 25);
					lvlPanel.add(boardArray[row][col]);
				}

				// check if it is currently looping through a Coin (char C). If so, set the
				// position to which it will be seen, and add it to lvlPanel
				else if (boardArray[row][col].getIcon() == Icons.COIN) {
					boardArray[row][col].setBounds(col * 25, row * 25, 25, 25);
					lvlPanel.add(boardArray[row][col]);
				}

				// check if it is currently looping through a Mushroom (char M). If so, set the
				// position to which it will be seen, and add it to lvlPanel
				else if (boardArray[row][col].getIcon() == Icons.MUSHROOM) {
					boardArray[row][col].setBounds(col * 25, row * 25, 25, 25);
					lvlPanel.add(boardArray[row][col]);
				}

				// check if it is currently looping through a Pipe (char P). If so, set the
				// position to which it will be seen, and add it to lvlPanel
				else if (boardArray[row][col].getIcon() == Icons.PIPE) {
					boardArray[row][col].setBounds(col * 25, row * 25, 25, 25);
					lvlPanel.add(boardArray[row][col]);
				}
			}
		}

		mario.setBounds(25, 425, 25, 25); // set the position of the character
		lvlPanel.add(mario); // add the instance of Character to lvlPanel
		lvlPanel.add(backgroundLabel); // add backgroundLabel, which holds the background image, to lvlPanel

		lvlPanel.setBounds(0, 0, boardArray[0].length * 25, boardArray.length * 25); // set the size of the lvlPanel to
																						// the size of the frame

		// pack it
		add(lvlPanel);
	}

	// setupKeyBindings method will be used to set character movement based on the
	// inputs of the user
	private void setupKeyBindings() {

		// initialize our ActionMap and InputMap
		ActionMap actionMap;
		InputMap inputMap;

		inputMap = lvlPanel.getInputMap(); // inputMap will map the keystrokes in our lvlPanel to a string
		actionMap = lvlPanel.getActionMap(); // actionMap will map the string to an action

		// this block of code will perform the above for going left, right, and up
		inputMap.put(KeyStroke.getKeyStroke(mario.getKey()[0].toCharArray()[0]), "left");
		actionMap.put("left", new KeyAction("left"));

		inputMap.put(KeyStroke.getKeyStroke(mario.getKey()[1].toCharArray()[0]), "right");
		actionMap.put("right", new KeyAction("right"));

		inputMap.put(KeyStroke.getKeyStroke(mario.getKey()[2].toCharArray()[0]), "jump");
		actionMap.put("jump", new KeyAction("jump"));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
			mario.setdX(0);
		}

	}
}
