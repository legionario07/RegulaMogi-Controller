package br.com.regulamogi.dao.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.regulamogi.builder.EspecialidadeBuilder;
import br.com.regulamogi.dao.RepositoryDao;
import br.com.regulamogi.domain.EntidadeDominio;
import br.com.regulamogi.domain.Especialidade;

public class TESTEspecialidadeDAO {

	private RepositoryDao dao;
	private Especialidade unidade;
	private List<EntidadeDominio> lista;
	
	@Before
	public void inicializarComponentes(){
		
		dao = new RepositoryDao();
		lista = new ArrayList<EntidadeDominio>();
		
	}
	
	@Test
	public void testSave(){
		
		unidade = new Especialidade();
		unidade = EspecialidadeBuilder.getEntity();
		
		unidade.setId(dao.save(unidade));
		
		assertEquals(Long.valueOf(2), unidade.getId());
		
		
	}
	
	@Ignore
	public void testUpdate(){
		unidade = new Especialidade();
		unidade = EspecialidadeBuilder.getEntity();
		
		assertEquals(true, dao.update(unidade));
		
	}
	
	@Ignore
	public void testFind(){
		unidade = new Especialidade(2l);
		
		unidade = (Especialidade) dao.find(unidade);
		
		
	}
	
	@Test
	public void testFindAll(){
		lista = dao.findAll(new Especialidade());
		
		for(EntidadeDominio e : lista){
			
			System.out.println(((Especialidade) e) .getId());
			System.out.println(((Especialidade) e) .getEspecialidade());
			
		}
		
	}
	
}
