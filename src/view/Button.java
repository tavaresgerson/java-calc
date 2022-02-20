package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Button extends JButton {

	private static final long serialVersionUID = -7822700467342494570L;

	public Button (String label, Color cor) {
		setText(label);
		setFont(new Font("calibri", Font.PLAIN, 20));
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(cor);
		setForeground(Color.WHITE);
		setFocusPainted(false);
	}
}
