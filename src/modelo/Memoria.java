package modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {	
	private enum TipoComando { AC, NUMERO, DIVISAO, MULTI, SUB, SOMA, IGUAL, VIRGULA }
	private TipoComando ultimaOperacao = null;
	private boolean substituir = false;
	private String textoBuffer = "";
	private static final Memoria instancia = new Memoria();
	private String textoAtual = "";
	private final List<MemoriaObservador> observadores = new ArrayList<>();
	
	
	private Memoria () {}
	
	public static Memoria getInstancia () {
		return instancia;
	}
	
	public void adicionarObservador (MemoriaObservador o) {
		 observadores.add(o);
	}
	
	public String getTextoAtual () {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}
	
	public void processarComando (String valor) {
		TipoComando tipoComando = detectarTipoComando(valor);

		if (tipoComando == null) {
			return;
		} else if (tipoComando == TipoComando.AC) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		} else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
			textoAtual = substituir ? valor : textoAtual + valor;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = this.obterResulvadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = tipoComando;
		}

		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}
	
	private String obterResulvadoOperacao() {
		if (ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		
		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
		double resultado = 0;
		
		if (ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		}
		
		if (ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		}
		
		if (ultimaOperacao == TipoComando.MULTI) {
			resultado = numeroBuffer * numeroAtual;
		}

		if (ultimaOperacao == TipoComando.DIVISAO) {
			resultado = numeroBuffer / numeroAtual;
		}
		
		String resultadoString = Double.toString(resultado).replace(".", ",");
		boolean inteiro = resultadoString.endsWith(",0");

		return inteiro ? resultadoString.replace(",0", "") : resultadoString;
	}

	private TipoComando detectarTipoComando (String valor) {
		if (textoAtual.isEmpty() && valor == "0") {			
			return null;
		}
		
		try {
			Integer.parseInt(valor);
			return TipoComando.NUMERO;			
		} catch (NumberFormatException e) {
			if ("AC".equals(valor)) {
				return TipoComando.AC;		
			} else if ("/".equals(valor)) {
				return TipoComando.DIVISAO;
			} else if ("x".equals(valor)) {
				return TipoComando.MULTI;
			} else if ("+".equals(valor)) {
				return TipoComando.SOMA;
			} else if ("-".equals(valor)) {
				return TipoComando.SUB;
			} else if ("=".equals(valor)) {
				return TipoComando.IGUAL;
			} else if (",".equals(valor) && !textoAtual.contains(",")) {
				return TipoComando.VIRGULA;
			}
		}
		
		return null;
	}
}
