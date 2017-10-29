package br.com.regulamogi.dao.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.regulamogi.builder.SolicitacaoBuilder;
import br.com.regulamogi.dao.RepositoryDao;
import br.com.regulamogi.domain.EntidadeDominio;
import br.com.regulamogi.domain.Solicitacao;

public class TESTSolicitacaoDAO {

	private RepositoryDao dao;
	private Solicitacao solicitacao;
	private List<EntidadeDominio> lista;
	
	@Before
	public void inicializarComponentes(){
		
		dao = new RepositoryDao();
		lista = new ArrayList<EntidadeDominio>();
		
	}
	
	@Ignore
	public void testSave(){
		
		solicitacao = new Solicitacao();
		solicitacao = SolicitacaoBuilder.getEntity();
		
		solicitacao.setId(dao.save(solicitacao));
		
		assertEquals(Long.valueOf(2), solicitacao.getId());
		
		
	}
	
	@Test
	public void testUpdate(){
		solicitacao = new Solicitacao();
		solicitacao = SolicitacaoBuilder.getEntity();
		solicitacao.setId(12l);
		
		assertEquals(true, dao.update(solicitacao));
		
	}
	
	@Ignore
	public void testFind(){
		solicitacao = new Solicitacao(2l);
		
		solicitacao = (Solicitacao) dao.find(solicitacao);
		
		
	}
	
	@Test
	public void testFindAll(){
		lista = dao.findAll(new Solicitacao());
		
		for(EntidadeDominio e : lista){
			
			System.out.println(((Solicitacao) e) .getId());
			System.out.println(((Solicitacao) e) .getNumeroMemorando());
			
		}
		
	}
	
}
