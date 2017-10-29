package br.com.regulamogi.builder;

import br.com.regulamogi.domain.UnidadeDeSaude;

public class UnidadeDeSaudeBuilder {

	private static UnidadeDeSaude unidade;
	
	public static UnidadeDeSaude getEntity(){
		
		unidade = new UnidadeDeSaude();
		
		unidade.setNomeUnidade("Jardim Camila");
		
		return unidade;
		
	}
	
}
