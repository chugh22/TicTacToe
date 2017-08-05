package Game;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {
	public static final int BOARD_SIZE = 3;
	private boolean move = true;

	private static enum Gamestatus {
		Xwin, Zwin, Tie, Incomplete;
	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];

	public TTT() {
		super.setTitle("Tick Tack Toe");
		super.setSize(800, 800);

		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Comic Sans", 3, 150);
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				JButton button = new JButton("");
				buttons[i][j] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
				

			}
		}
		super.setResizable(false);
		super.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		this.makeMove(clickedButton);
		Gamestatus gs = getgameStatus();
		if (gs != Gamestatus.Incomplete) {
			this.declareWinner(gs);

			int choice = JOptionPane.showConfirmDialog(this, "RESTART?");

			if (choice == JOptionPane.YES_OPTION) {
				for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {

						buttons[i][j].setText("");

					}
				}
				move = true;
			} else {
				super.dispose();
			}
		}

	}

	private void makeMove(JButton clickedButton) {
		String txt = clickedButton.getText();
		if (txt.length() != 0) {
			JOptionPane.showMessageDialog(this, "Invalid Move !");
			return;
		}
		if (move) {
			clickedButton.setText("X");
		} else {
			clickedButton.setText("0");
		}
		move = !move;
	}

	private Gamestatus getgameStatus() {
		String txt1 = "", txt2 = "";
		int row = 0, col = 0;
		for (row = 0; row < BOARD_SIZE; row++) {

			for (col = 0; col < BOARD_SIZE - 1; col++) {
				txt1 = buttons[row][col].getText();
				txt2 = buttons[row][col + 1].getText();
				if (!txt1.equals(txt2) || txt1.length() == 0) {
					break;
				}
			}

			if (col == BOARD_SIZE - 1) {
				if (txt1.equals("X")) {
					return Gamestatus.Xwin;
				} else {
					return Gamestatus.Zwin;
				}
			}
		}
		for (col = 0; col < BOARD_SIZE; col++) {

			for (row = 0; row < BOARD_SIZE - 1; row++) {
				txt1 = buttons[row][col].getText();
				txt2 = buttons[row + 1][col].getText();
				if (!txt1.equals(txt2) || txt1.length() == 0) {
					break;
				}
			}

			if (row == BOARD_SIZE - 1) {
				if (txt1.equals("X")) {
					return Gamestatus.Xwin;
				} else {
					return Gamestatus.Zwin;
				}
			}
		}
		int i= 0 ;
		for( i = 0 ;i < BOARD_SIZE-1 ;i++ ){
			txt1 = buttons[i][i].getText() ;
			txt2 = buttons[i+1][i+1].getText() ;
			if(!txt1.equals(txt2) || txt1.length() == 0){
				break ;
			}
		}
		if(i == BOARD_SIZE-1){
			if(txt1.equals("X")){
				return Gamestatus.Xwin ;
			}
			else{
				return Gamestatus.Zwin ;
			}
		}
		row = BOARD_SIZE -1 ;
		col = 0 ;
		while(row > 0){
			txt1 = buttons[row][col].getText() ;
			txt2 = buttons[row-1][col+1].getText() ;
			if(!txt1.equals(txt2) || txt1.length() == 0){
				break ;
			}
			row-- ;
			col++ ;
		}
		if(row == 0){
			if(txt1.equals("X")){
				return Gamestatus.Xwin ;
			}
			else{
				return Gamestatus.Zwin ;
			}
		}
		String txt = "";
		for (i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				txt = buttons[i][j].getText();
				if (txt.length() == 0) {
					return Gamestatus.Incomplete;
				}
			}

		}

		return Gamestatus.Tie;
	}

	private void declareWinner(Gamestatus gs) {
		if (gs == Gamestatus.Xwin) {
			JOptionPane.showMessageDialog(this, "X WINS");
		} else if (gs == Gamestatus.Zwin) {
			JOptionPane.showMessageDialog(this, "O WINS");
		} else {
			JOptionPane.showMessageDialog(this, " Its a TIE");
		}
	}
}
