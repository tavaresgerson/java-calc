package visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Memoria;
import modelo.MemoriaObservador;

public class Display extends JPanel implements MemoriaObservador {
	
	private static final long serialVersionUID = -7822700467342494570L;

	private final JLabel label;
	
	public Display () {
		Memoria.getInstancia().adicionarObservador(this);

		setBackground(new Color(46, 49, 50));
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("calibri", Font.PLAIN, 30));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 9, 20));
		add(label);
	}
	
	@Override
	public void valorAlterado (String novoValor) {
		label.setText(novoValor);
	}
}
