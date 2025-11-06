/* Name: Aaron Su
 * Date: May 28, 2023
 * Course Code: ICS - 3U1
 * Title: SDP #2 Individual Project Video Game
 * Description: This class creates the frame for the information. 
 * This will only execute when the informationButton in TitleFrame is clicked. 
 * It will display all the information on how to play the game, and the additional features added to the game
 * Major Skills: Constructor, Swing and Awt GUI Components, ActionListener, Music
 * Added Features: Everything
 * Contribution to Assignment: Aaron Su - 100%
 */

// import our necessities 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationFrame extends JFrame {

	// create our global variables, which are all from javax.swing and java.awt
	ImageIcon instructionImage;
	JLabel imageLabel = new JLabel();
	JTextArea descriptionLabel = new JTextArea();

	Image backIcon = new ImageIcon("images/BackIcon.png").getImage().getScaledInstance(300, 100,
			java.awt.Image.SCALE_SMOOTH);
	JButton backButton = new JButton();

	Image exitIcon = new ImageIcon("images/ExitIcon.png").getImage().getScaledInstance(300, 100,
			java.awt.Image.SCALE_SMOOTH);
	JButton exitButton = new JButton();

	public InformationFrame() {

		// set layout to null to manually move everything
		setLayout(null);

		// scale the image, and set global JLabel imageLabel to the corresponding image
		instructionImage = new ImageIcon("images/instructionsLogo.png");
		Image image = instructionImage.getImage();
		Image newImage = image.getScaledInstance(650, 180, java.awt.Image.SCALE_SMOOTH);
		instructionImage = new ImageIcon(newImage);
		imageLabel.setIcon(instructionImage);

		// Add some specifications to our descriptionLabel so that it isn't as messy
		descriptionLabel.setLineWrap(true); // Text goes to next line
		descriptionLabel.setWrapStyleWord(true); // Make sure every character in a word are together
		descriptionLabel.setHighlighter(null); // Disable text highlighting
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 23)); // Set the size and font of the text
		descriptionLabel.setEditable(false); // Disable editing
		descriptionLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // Create some padding between
																					// edges
																					// and text

		// set the text of the descriptionLabel
		descriptionLabel.setText(
				String.format("1. FOR MOVEMENT, USE 'A' TO MOVE LEFT, 'D' TO MOVE RIGHT, AND 'SPACEBAR' TO JUMP "
						+ "\n\n2. TO PROCEED TO A NEW LEVEL, YOU MUST FIRST COLLECT ALL THE COINS IN YOUR CURRENT LEVEL,"
						+ " AND THEN TAKE THE PIPE. POWER UPS SUCH AS MUSHROOMS ARE OPTIONAL."
						+ "\n\n3. THE 'RESET' BUTTON WILL RESET ALL PROGRESS MADE IN THE CURRENT SESSION OF THE GAME. "
						+ "THE 'EXIT' BUTTON WILL FORCE QUIT THE GAME \n\nGOOD LUCK, AND HAVE FUN!"));

		// set the exitButton to the corresponding image
		backButton.setIcon(new ImageIcon(backIcon));

		// add an actionListener to the backButton, which will dispose of this current
		// frame, stop playing the current clip, and create a new frame from the
		// TitleFrame class
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
				PlaySong.clip.stop();
				new TitleFrame();
			}
		});

		// set the exitButton to the corresponding image
		exitButton.setIcon(new ImageIcon(exitIcon));

		// add an actionListener to the exitButton, which will force quit the entire
		// program
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		// set the positions of everything to which they will be seen
		imageLabel.setBounds(180, 40, 650, 180);
		descriptionLabel.setBounds(120, 220, 760, 353);
		backButton.setBounds(120, 600, 300, 100);
		exitButton.setBounds(580, 600, 300, 100);

		backButton.setBorderPainted(false); // set the button itself to be invisible, meaning that only the image can be
											// seen and clicked
		backButton.setFocusable(false); // set the button to not be automatically focused / hovered

		exitButton.setBorderPainted(false); // set the button itself to be invisible, meaning that only the image can be
											// seen and clicked
		exitButton.setFocusable(false); // set the button to not be automatically focused / hovered

		// pack them
		add(imageLabel);
		add(descriptionLabel);
		add(backButton);
		add(exitButton);

		// set the basic frame settings
		setSize(1020, 810);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
