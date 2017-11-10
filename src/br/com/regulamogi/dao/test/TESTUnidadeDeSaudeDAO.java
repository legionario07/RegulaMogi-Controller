package br.com.regulamogi.dao.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.regulamogi.builder.UnidadeDeSaudeBuilder;
import br.com.regulamogi.dao.RepositoryDao;
import br.com.regulamogi.domain.Conta;
import br.com.regulamogi.domain.EntidadeDominio;
import br.com.regulamogi.domain.UnidadeDeSaude;

public class TESTUnidadeDeSaudeDAO {

	private RepositoryDao dao;
	private UnidadeDeSaude unidade;
	private List<EntidadeDominio> lista;
	
	@Before
	public void inicializarComponentes(){
		
		dao = new RepositoryDao();
		lista = new ArrayList<EntidadeDominio>();
		
	}
	
	@Ignore
	public void testSave(){
		
		unidade = new UnidadeDeSaude();
		unidade = UnidadeDeSaudeBuilder.getEntity();
		
		unidade.setId(dao.save(unidade));
		
		assertEquals(Long.valueOf(2), unidade.getId());
		
		
	}
	
	@Ignore
	public void testUpdate(){
		unidade = new UnidadeDeSaude();
		unidade = UnidadeDeSaudeBuilder.getEntity();
		unidade.setId(1l);
		
		List<Conta> contas = new ArrayList<>();
		Conta conta = new Conta(10l);
		
		contas.add(conta);
		//unidade.setContas(contas);
		
		assertEquals(true, dao.update(unidade));
		
	}
	
	@Ignore
	public void testFind(){
		unidade = new UnidadeDeSaude(2l);
		
		unidade = (UnidadeDeSaude) dao.find(unidade);
		
		System.out.println(unidade.getNomeUnidade());
		
	}
	
	@Ignore
	public void testFindAll(){
		lista = dao.findAll(new UnidadeDeSaude());
		
		for(EntidadeDominio e : lista){
			
			System.out.println(((UnidadeDeSaude) e) .getId());
			System.out.println(((UnidadeDeSaude) e) .getNomeUnidade());
		//	System.out.println(((UnidadeDeSaude) e) .getContas().get(0).getLogin());
			
		}
		
	}
	
	@Test
	public void login(){
		unidade = new UnidadeDeSaude();
		Conta conta = new Conta();
		conta.setLogin("PROMULHER");
		conta.setSenha("123");
		unidade.setConta(conta);
		
		unidade = (UnidadeDeSaude) dao.login(unidade);
		System.out.println(unidade.getNomeUnidade());
		System.out.println(unidade.getConta().getSenha());
		
	}
	
}
