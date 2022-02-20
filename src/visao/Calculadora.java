package visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Calculadora extends JFrame {

	private static final long serialVersionUID = -7326214659392518140L;
	
	public Calculadora () {
		this.organizarLayout();
		setSize(232, 322);
//		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void organizarLayout () {
		setLayout(new BorderLayout());
		Display display = new Display();
		Teclado teclado = new Teclado();
		
		display.setPreferredSize(new Dimension(233, 60));
		add(display, BorderLayout.NORTH);
		add(teclado, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new Calculadora();
	}

}
