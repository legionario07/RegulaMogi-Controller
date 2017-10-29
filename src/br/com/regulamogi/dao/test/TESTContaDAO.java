package br.com.regulamogi.dao.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.regulamogi.builder.ContaBuilder;
import br.com.regulamogi.dao.RepositoryDao;
import br.com.regulamogi.domain.EntidadeDominio;
import br.com.regulamogi.domain.Conta;

public class TESTContaDAO {

	private RepositoryDao dao;
	private Conta conta;
	private List<EntidadeDominio> lista;
	
	@Before
	public void inicializarComponentes(){
		
		dao = new RepositoryDao();
		lista = new ArrayList<EntidadeDominio>();
		
	}
	
	@Test
	public void testSave(){
		
		conta = new Conta();
		conta = ContaBuilder.getEntity();
		
		conta.setId(dao.save(conta));
		
		assertEquals(Long.valueOf(2), conta.getId());
		
		
	}
	
	@Ignore
	public void testUpdate(){
		conta = new Conta();
		conta = ContaBuilder.getEntity();
		conta.setId(8l);
		
		assertEquals(true, dao.update(conta));
		
	}
	
	@Ignore
	public void testFind(){
		conta = new Conta(2l);
		
		conta = (Conta) dao.find(conta);
		
		
	}
	
	@Test
	public void testFindAll(){
		lista = dao.findAll(new Conta());
		
		for(EntidadeDominio e : lista){
			
			System.out.println(((Conta) e) .getId());
			System.out.println(((Conta) e) .getPerfil().getPerfil());
			
		}
		
	}
	
}
