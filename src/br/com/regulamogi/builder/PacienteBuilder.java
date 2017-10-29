package br.com.regulamogi.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.regulamogi.domain.Conta;
import br.com.regulamogi.domain.Paciente;
import br.com.regulamogi.domain.Solicitacao;

public class PacienteBuilder {

	private static Paciente paciente;
	
	public static Paciente getEntity(){
		
		paciente = new Paciente();
		paciente.setNome("Paulo Sergio Moreira dos Santos Dias");
		paciente.setCelular("(11)971986033");
		paciente.setTelefone("(11)47986739");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Calendar c = Calendar.getInstance();

		
		try {
			c.setTime(sdf.parse(c.getTime().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		paciente.setLastLogin(c);
		paciente.setSIS("111111");
		paciente.setConta(new Conta(8l));
		
		List<Solicitacao> solicitacoes = new ArrayList<>();
		solicitacoes.add(new Solicitacao(10l));
		solicitacoes.add(new Solicitacao(12l));
		
		paciente.setSolicitacoes(solicitacoes);
		
		return paciente;
	}
	
}
