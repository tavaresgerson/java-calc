package model;

import java.util.ArrayList;
import java.util.List;

public class Memory {	
	private enum KindCommand { AC, NUMBER, DIVISION, MULTI, SUB, SUM, EQUALS, COMMA }
	private KindCommand lastOperation = null;
	private boolean substituir = false;
	private String textoBuffer = "";
	private static final Memory instancia = new Memory();
	private String textoAtual = "";
	private final List<MemoryObserver> observadores = new ArrayList<>();

	private Memory () {}
	
	public static Memory getInstance ()
	{
		return instancia;
	}
	
	public void adicionarObservador (MemoryObserver o)
	{
		 observadores.add(o);
	}
	
	public String getTextoAtual ()
	{
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}
	
	public void processCommand (String valor)
	{
		KindCommand tipoComando = detectarTipoComando(valor);

		if (tipoComando == null) {
			return;
		}

		if (tipoComando == KindCommand.AC) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			lastOperation = null;
		}
		
		if (tipoComando == KindCommand.NUMBER || tipoComando == KindCommand.COMMA) {
			textoAtual = substituir ? valor : textoAtual + valor;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = this.obterResulvadoOperacao();
			textoBuffer = textoAtual;
			lastOperation = tipoComando;
		}

		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}
	
	private String obterResulvadoOperacao ()
	{
		if (lastOperation == null || lastOperation == KindCommand.EQUALS) {
			return textoAtual;
		}
		
		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
		double resultado = 0;
		
		switch (lastOperation) {
			case SUM:
				resultado = numeroBuffer + numeroAtual;
				break;
			case SUB:
				resultado = numeroBuffer - numeroAtual;
				break;
			case MULTI:
				resultado = numeroBuffer * numeroAtual;
				break;
			case DIVISION:
				resultado = numeroBuffer / numeroAtual;
				break;
			default:
				resultado = 0;
				break;
		}

		String resultadoString = Double.toString(resultado).replace(".", ",");
		boolean inteiro = resultadoString.endsWith(",0");

		return inteiro ? resultadoString.replace(",0", "") : resultadoString;
	}

	private KindCommand detectarTipoComando (String valor)
	{
		if (textoAtual.isEmpty() && valor == "0") {			
			return null;
		}
		
		try {
			Integer.parseInt(valor);
			return KindCommand.NUMBER;			
		} catch (NumberFormatException e) {
			if ("AC".equals(valor)) {
				return KindCommand.AC;		
			} else if ("/".equals(valor)) {
				return KindCommand.DIVISION;
			} else if ("x".equals(valor)) {
				return KindCommand.MULTI;
			} else if ("+".equals(valor)) {
				return KindCommand.SUM;
			} else if ("-".equals(valor)) {
				return KindCommand.SUB;
			} else if ("=".equals(valor)) {
				return KindCommand.EQUALS;
			} else if (",".equals(valor) && !textoAtual.contains(",")) {
				return KindCommand.COMMA;
			}
		}
		
		return null;
	}
}
