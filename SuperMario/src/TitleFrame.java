/* Name: Aaron Su
 * Date: May 28, 2023
 * Course Code: ICS - 3U1
 * Title: SDP #2 Individual Project Video Game
 * Description: This class creates the frame for the Title. 
 * This will be the first frame that shows up when our program is ran, and it will contain a logo, an information button, and a play button
 * Major Skills: Constructor, Swing and Awt GUI Components, ActionListener, Music
 * Added Features: Everything
 * Contribution to Assignment: Aaron Su - 100%
 */

// import our necessities 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class TitleFrame extends JFrame {
	// create an instance of the LevelFrame class, so that we can dispose and call it	 throughout the code
	public static LevelFrame level;

	// create our global variables, which are all from javax.swing and java.awt
	Image logoImage = new ImageIcon("images/SuperMarioLogo.png").getImage().getScaledInstance(600, 275,
			java.awt.Image.SCALE_SMOOTH);
	JLabel imageLabel = new JLabel();

	Image playImage = new ImageIcon("images/PlayLogo.png").getImage().getScaledInstance(300, 100,
			java.awt.Image.SCALE_SMOOTH);
	Image instructionImage = new ImageIcon("images/instructionsLogo.png").getImage().getScaledInstance(610, 140,
			java.awt.Image.SCALE_SMOOTH);

	JButton playButton = new JButton();
	JButton instructionButton = new JButton();

	public TitleFrame() {

		// call the PlaySong method, and add the .wav file of our music and the time
		// position as an argument
		PlaySong.playMusic("Music/Super Mario Bros BG.wav", 0);
		PlaySong.clip.loop(Clip.LOOP_CONTINUOUSLY); // set the clip of the method to loop continuously

		// set layout to null to manually move everything
		setLayout(null);

		// set imageLabel to the corresponding image initialized globally
		imageLabel.setIcon(new ImageIcon(logoImage));

		playButton.setIcon(new ImageIcon(playImage)); // set playButton to the corresponding image initialized globally
		instructionButton.setIcon(new ImageIcon(instructionImage)); // set instructionButton to the corresponding image
																	// initialized globally

		// add an actionListener to the playButton, which will dispose of this current
		// frame, and create a new level 1 frame from the LevelFrame class
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
				level = new LevelFrame(1);
			}
		});

		// add an actionListener to the playButton, which will dispose of this current
		// frame, and create a new frame from the InformationFrame
		instructionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
				new InformationFrame();
			}
		});

		// set the positions of everything to which they will be seen
		imageLabel.setBounds(210, 50, 600, 275);
		playButton.setBounds(363, 430, 300, 100);
		instructionButton.setBounds(223, 530, 620, 150);

		playButton.setBorderPainted(false); // set the button itself to be invisible, meaning that only the image can be
											// seen and clicked
		playButton.setFocusable(false); // set the button to not be automatically focused / hovered

		instructionButton.setBorderPainted(false); // set the button itself to be invisible, meaning that only the image
													// can be seen and clicked
		instructionButton.setFocusable(false); // set the button to not be automatically focused / hovered

		// pack them
		add(imageLabel);
		add(playButton);
		add(instructionButton);

		// set the basic frame settings
		setTitle("Super Mario");
		setSize(1020, 810);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

}
