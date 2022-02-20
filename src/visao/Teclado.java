package visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import modelo.Memoria;

public class Teclado extends JPanel implements ActionListener {

	private final Color COR_CINZA_ESCURO = new Color(68, 68, 68);
	private final Color COR_CINZA_CLARO = new Color(99, 99, 99);
	private final Color COR_LARANJA = new Color(242, 163, 60);
	private static final long serialVersionUID = -7822700467342494570L;

	public Teclado () {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		setLayout(layout);
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;

		constraints.gridwidth = 3;
		this.addButton("AC", COR_CINZA_ESCURO, constraints, 0, 0);

		constraints.gridwidth = 1;
		this.addButton("/", COR_LARANJA, constraints, 3, 0);
		
		this.addButton("9", COR_CINZA_CLARO, constraints, 0, 1);
		this.addButton("8", COR_CINZA_CLARO, constraints, 1, 1);
		this.addButton("7", COR_CINZA_CLARO, constraints, 2, 1);
		this.addButton("x", COR_LARANJA, constraints, 3, 1);
		
		this.addButton("6", COR_CINZA_CLARO, constraints, 0, 2);
		this.addButton("5", COR_CINZA_CLARO, constraints, 1, 2);
		this.addButton("4", COR_CINZA_CLARO, constraints, 2, 2);
		this.addButton("-", COR_LARANJA, constraints, 3, 2);
		
		this.addButton("3", COR_CINZA_CLARO, constraints, 0, 3);
		this.addButton("2", COR_CINZA_CLARO, constraints, 1, 3);
		this.addButton("1", COR_CINZA_CLARO, constraints, 2, 3);
		this.addButton("+", COR_LARANJA, constraints, 3, 3);
		
		constraints.gridwidth = 2;
		this.addButton("0", COR_CINZA_CLARO, constraints, 0, 4);
		
		constraints.gridwidth = 1;
		this.addButton(",", COR_CINZA_CLARO, constraints, 2, 4);
		this.addButton("=", COR_LARANJA, constraints, 3, 4);
	}
	
	public void addButton (String name, Color color, GridBagConstraints constraint, int line, int col) {
		constraint.gridx = line;
		constraint.gridy = col;

		Botao button = new Botao(name, color);
		button.addActionListener(this);
		add(button, constraint);
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		JButton button = (JButton) e.getSource();
		Memoria.getInstancia().processarComando(button.getText());
	}
	
}
