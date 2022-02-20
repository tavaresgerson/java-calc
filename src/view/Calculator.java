package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Calculator extends JFrame {

	private static final long serialVersionUID = -7326214659392518140L;
	
	public Calculator ()
	{
		this.setupLayout();

		setSize(232, 319);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void setupLayout ()
	{
		setLayout(new BorderLayout());
		Display display = new Display();
		Keyboard keyboard = new Keyboard();
		
		display.setPreferredSize(new Dimension(233, 60));

		add(display, BorderLayout.NORTH);
		add(keyboard, BorderLayout.CENTER);
	}
}
