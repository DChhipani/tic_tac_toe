package game;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//replaced JButton with GameButton
@SuppressWarnings("serial")
public class GameWindow extends JFrame {

	GameButton gameButton = new GameButton();
	private GameButton[][] buttons;
	private GameButton resetButton;
	private JLabel statusLabel;
	protected String playerOne = "1";
	protected String playerTwo = "2";
	private String currentPlayer = playerOne;
	boolean gameFinished = false;
	boolean allFilled = false;
	
	public GameWindow(int size) {
		super("Let's play a game!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		statusLabel = new JLabel(" ");
		statusLabel.setText("Player One's Turn!");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(size, size));
		
		buttons = new GameButton[size][];
		for (int i = 0; i < size; i++) {
			buttons[i] = new GameButton[size];
			for (int j = 0; j < size; j++) {
				GameButton button = new GameButton();
				button.setFont(button.getFont().deriveFont(25.0f));
				button.setPreferredSize(new Dimension(100, 100));
				buttonPanel.add(button);
				buttons[i][j] = button;
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GameButton buttonPressed = (GameButton) e.getSource();
						buttonPressed.setSymbol(currentPlayer);
						//checking win condition
						boolean allEqual;
						String[] square;
						for (int i = 0; i < buttons.length-1; i++) {
							for (int j = 0; j < buttons[i].length-1; j++) {
								allEqual = true;
								square = new String[4];
								square[0] = buttons[i][j].getSymbol();
								square[1] = buttons[i+1][j].getSymbol();
								square[2] = buttons[i][j+1].getSymbol();
								square[3] = buttons[i+1][j+1].getSymbol();
								for (int k = 0; k < square.length-1; k++) {
									if (square[k] == null || square[k+1] == null) {
										allEqual = false;
										continue;
									} if (!square[k].equals(square[k+1])) {
										allEqual = false;
									}
								}
								if (allEqual == true && square[0].equals(playerOne)) {
									gameFinished = true;
									statusLabel.setText("Winner: Player " + playerOne);
								} else if (allEqual == true && square[0].equals(playerTwo)) {
									gameFinished = true;
									statusLabel.setText("Winner: Player " + playerTwo);
								}
							}
						}
						loop:
							for (int i = 0; i <  buttons.length; i++) {
								for (int j = 0; j < buttons[i].length; j++) {
									allFilled = true;
									if (buttons[i][j].getSymbol() == null) {
										allFilled = false;
										break loop;
									}
								}
							}
						if (!gameFinished ) {
							if (currentPlayer == playerOne) {
								currentPlayer = playerTwo;
								statusLabel.setText("Player Two's Turn!");
							} else if (currentPlayer == playerTwo) {
								currentPlayer = playerOne;
								statusLabel.setText("Player One's Turn!");
							}
						}
						if (gameFinished) {
							for (int i = 0; i < buttons.length; i++) {
								for (int j = 0; j < buttons[i].length; j++) {
									buttons[i][j].setEnabled(false);
								}
							}
						}
						if (allFilled) {
							statusLabel.setText("Board is full with no winner");
							for (int i = 0; i < buttons.length; i++) {
								for (int j = 0; j < buttons[i].length; j++) {
									buttons[i][j].setEnabled(false);
								}
							}
						}
					}
				});
			}
		}
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(statusLabel, BorderLayout.NORTH);
		getContentPane().add(buttonPanel, BorderLayout.CENTER);
		
		resetButton = new GameButton();
		resetButton.setText("Reset");
		resetButton.addActionListener(new ActionListener() {
			/*
			 * Method to run when reset button is clicked
	*/
@Override
public void actionPerformed(ActionEvent e) {
	for (int i = 0; i < buttons.length; i++) {
		currentPlayer = playerOne;
		gameFinished = false;
		for (int j = 0; j < buttons[i].length; j++) {
			buttons[i][j].reset();
		}
	}
	}
		});
		getContentPane().add(resetButton, BorderLayout.SOUTH);
		pack();
	}
	/**
     * Main method -- just creates and displays the window.
     */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
@Override
public void run() {
	new GameWindow(4).setVisible(true);
	}
		});
	}
}