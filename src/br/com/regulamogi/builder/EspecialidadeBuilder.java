package br.com.regulamogi.builder;

import br.com.regulamogi.domain.Especialidade;

public class EspecialidadeBuilder {

	private static Especialidade especialidade;
	
	public static Especialidade getEntity(){
		
		especialidade = new Especialidade();
		especialidade.setEspecialidade("Endocrinologia");
		
		return especialidade;
	}
	
}
