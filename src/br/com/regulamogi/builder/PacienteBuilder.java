package br.com.regulamogi.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.regulamogi.dao.RepositoryDao;
import br.com.regulamogi.domain.Conta;
import br.com.regulamogi.domain.Paciente;
import br.com.regulamogi.domain.Solicitacao;
import br.com.regulamogi.domain.Telefone;
import br.com.regulamogi.domain.TelefoneType;

public class PacienteBuilder {

	private static Paciente paciente;
	
	public static Paciente getEntity(){
		
		paciente = new Paciente();
		paciente.setNome("Paulo Sergio Moreira dos Santos Dias");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar c = Calendar.getInstance();

		
		try {
			c.setTime(sdf.parse(sdf.format(c.getTime())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		paciente.setLastLogin(c);
		paciente.setSIS("111111");
	
		Conta conta = new Conta();
		conta = (Conta) new RepositoryDao().find(conta);
		paciente.setConta(conta);
		
		List<Solicitacao> solicitacoes = new ArrayList<>();
		solicitacoes.add((Solicitacao) new RepositoryDao().find(new Solicitacao(10l)));
		solicitacoes.add((Solicitacao) new RepositoryDao().find(new Solicitacao(12l)));
		
		paciente.setSolicitacoes(solicitacoes);
		
		List<Telefone> telefones = new ArrayList<>();
		Telefone t1 = new Telefone();
		t1.setTelefoneType(TelefoneType.CELULAR);
		t1.setNumero("(11) 971986033");
		
		
		Telefone t2 = new Telefone();
		t2.setTelefoneType(TelefoneType.COMERCIAL);
		t2.setNumero("(11) 47986737");

		t1.setId(new RepositoryDao().save(t1));
		t2.setId(new RepositoryDao().save(t2));
		
		telefones.add(t1);
		telefones.add(t2);
		
		paciente.setTelefones(telefones);
		
		
		return paciente;
	}
	
}
