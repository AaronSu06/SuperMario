/* Name: Aaron Su
 * Date: May 28, 2023
 * Course Code: ICS - 3U1
 * Title: SDP #2 Individual Project Video Game
 * Description: This class binds keys to actions, for example player movement with A, D, and SPACE 
 * responds to input by calling methods in Character class
 * Major Skills: Constructor, Arrays, ActionListener, If Statements
 * Added Features: None
 * Contribution to Assignment: Aaron Su - 100%
 */

// import our necessities 
import java.awt.event.ActionEvent;

import javax.swing.text.TextAction;

public class KeyAction extends TextAction {
	private String key;

	// takes string parameter, key, which represents the key that will be bound to
	// the action.
	public KeyAction(String key) {
		super(key);
		this.key = key;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Character mario = LevelFrame.mario;
		if (e.getActionCommand().equals(mario.getKey()[0])
				&& LevelFrame.boardArray[mario.getRow()][mario.getCol()].getIcon() != Icons.WALL)
			mario.moveLeft(); // move left
		else if (e.getActionCommand().equals(mario.getKey()[1])
				&& LevelFrame.boardArray[mario.getRow()][mario.getCol()].getIcon() != Icons.WALL)
			mario.moveRight(); // move right
		else if (e.getActionCommand().equals(mario.getKey()[2])
				&& LevelFrame.boardArray[mario.getRow() - 1][mario.getCol()].getIcon() != Icons.WALL) {
			if (!mario.isJumping()) { // if not jumping then jump
				mario.setJumping(true);
				mario.jump();
			}
		}

	}

}
