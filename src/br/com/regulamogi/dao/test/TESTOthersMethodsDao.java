package br.com.regulamogi.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.regulamogi.dao.RepositoryDao;
import br.com.regulamogi.domain.EntidadeDominio;
import br.com.regulamogi.domain.Paciente;
import br.com.regulamogi.domain.Solicitacao;
import br.com.regulamogi.domain.UnidadeDeSaude;

public class TESTOthersMethodsDao {

	private List<EntidadeDominio> lista;
	private RepositoryDao dao;
	
	@Before
	public void inicializar(){
		lista = new ArrayList<>();
		dao = new RepositoryDao();
	}
	
	@Ignore
	public void findSolicitacoesByNumeroMemorando(){
	
		Paciente paciente = new Paciente();
		paciente.setSIS("111111");
		paciente = dao.findPacienteBySIS(paciente);
		
		UnidadeDeSaude u = new UnidadeDeSaude(2l);
		
		lista = dao.findSolicitacoesByNumeroMemorando("01/2017", u);
		
		System.out.println(lista.size());
		
	}
	
	@Test
	public void findPacienteBySIS(){
		
		Paciente paciente = new Paciente();
		paciente.setSIS("111111");
		paciente = dao.findPacienteBySIS(paciente);
		
		for(Solicitacao s : paciente.getSolicitacoes()){
			System.out.println(s.getNumeroMemorando());
		}
		
	}
	
}
