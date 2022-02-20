package game;

import java.awt.Color;

@SuppressWarnings("serial")
public class GameButton extends javax.swing.JButton {

//to set the text, colour and disable the button
	public void setSymbol(String symbol) {

		if (symbol == "1") {
			this.setText(symbol);
			this.setBackground(new Color(0, 128, 128));
			this.setEnabled(false);
		}
		if (symbol == "2") {
			this.setText(symbol);
			this.setBackground(new Color(253, 67, 0));
			this.setEnabled(false);
		}
//does the resting
		if (symbol == null) {
			this.setText(null);
			this.setBackground(null);
			;
			this.setEnabled(true);
		}

	}

//returns symbol
	public String getSymbol() {
		if (this.getText() == "1" || this.getText() == "2") {
			return this.getText();
		} else {
			return null;
		}
	}

//puts null onto setSymbol()
	public void reset() {
		this.setSymbol(null);

	}
}
