package br.com.regulamogi.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.regulamogi.dao.RepositoryDao;
import br.com.regulamogi.domain.EntidadeDominio;
import br.com.regulamogi.domain.Paciente;
import br.com.regulamogi.domain.Solicitacao;

public class TESTOthersMethodsDao {

	private List<EntidadeDominio> lista;
	private RepositoryDao dao;
	
	@Before
	public void inicializar(){
		lista = new ArrayList<>();
		dao = new RepositoryDao();
	}
	
	@Test
	public void findSolicitacoesByPaciente(){
	
		Paciente paciente = new Paciente();
		paciente.setSIS("111111");
		paciente = dao.findPacienteBySIS(paciente);
		
		lista = dao.findSolicitacoesByPaciente(paciente);
		
		for(EntidadeDominio e : lista){
			System.out.println(((Solicitacao) e).getDataConsulta());
		}
		
	}
	
}
