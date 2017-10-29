package br.com.regulamogi.builder;

import br.com.regulamogi.domain.Perfil;

public class PerfilBuilder {

	private static Perfil perfil;
	
	public static Perfil getEntity(){
		
		perfil = new Perfil();
		perfil.setPerfil("Paciente");
		
		return perfil;
	}
	
}
