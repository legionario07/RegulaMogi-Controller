package br.com.regulamogi.builder;

import br.com.regulamogi.domain.Conta;
import br.com.regulamogi.domain.Perfil;
import br.com.regulamogi.utils.EncryptMD5Util;

public class ContaBuilder {

	private static Conta conta;
	
	public static Conta getEntity(){
		
		conta = new Conta();
		conta.setLogin("ubsjundiapeba");
		conta.setPerfil(new Perfil(7l));
		conta.setSenha(EncryptMD5Util.getEncryptMD5("123"));
		
		
		return conta;
	}
	
}
