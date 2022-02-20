package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Memory;

public class Keyboard extends JPanel implements ActionListener {

	private final Color orange = new Color(242, 163, 60);
	private final Color darkGrey = new Color(68, 68, 68);
	private final Color lightGrey = new Color(99, 99, 99);

	private static final long serialVersionUID = -7822700467342494570L;

	public Keyboard ()
	{
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		setLayout(layout);
		
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;

		constraints.gridwidth = 3;
		this.addButton(0, 0, "AC", darkGrey, constraints);

		constraints.gridwidth = 1;
		this.addButton(3, 0, "/", orange, constraints);
		
		this.addButton(0, 1, "9", lightGrey, constraints);
		this.addButton(1, 1, "8", lightGrey, constraints);
		this.addButton(2, 1, "7", lightGrey, constraints);
		this.addButton(3, 1, "x", orange, constraints);
		
		this.addButton(0, 2, "6", lightGrey, constraints);
		this.addButton(1, 2, "5", lightGrey, constraints);
		this.addButton(2, 2, "4", lightGrey, constraints);
		this.addButton(3, 2, "-", orange, constraints);
		
		this.addButton(0, 3, "3", lightGrey, constraints);
		this.addButton(1, 3, "2", lightGrey, constraints);
		this.addButton(2, 3, "1", lightGrey, constraints);
		this.addButton(3, 3, "+", orange, constraints);
		
		constraints.gridwidth = 2;
		this.addButton(0, 4, "0", lightGrey, constraints);
		
		constraints.gridwidth = 1;
		this.addButton(2, 4, ",", lightGrey, constraints);
		this.addButton(3, 4, "=", orange, constraints);
	}
	
	public void addButton (int line, int col, String name, Color color, GridBagConstraints constraint)
	{
		constraint.gridx = line;
		constraint.gridy = col;

		Button button = new Button(name, color);
		button.addActionListener(this);
		add(button, constraint);
	}
	
	@Override
	public void actionPerformed (ActionEvent e)
	{
		JButton button = (JButton) e.getSource();
		Memory
			.getInstance()
			.processCommand(button.getText());
	}
}
