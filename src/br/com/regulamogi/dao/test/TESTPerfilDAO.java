package br.com.regulamogi.dao.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.regulamogi.builder.PerfilBuilder;
import br.com.regulamogi.dao.RepositoryDao;
import br.com.regulamogi.domain.EntidadeDominio;
import br.com.regulamogi.domain.Perfil;

public class TESTPerfilDAO {

	private RepositoryDao dao;
	private Perfil perfil;
	private List<EntidadeDominio> lista;
	
	@Before
	public void inicializarComponentes(){
		
		dao = new RepositoryDao();
		lista = new ArrayList<EntidadeDominio>();
		
	}
	
	@Test
	public void testSave(){
		
		perfil = new Perfil();
		perfil = PerfilBuilder.getEntity();
		
		perfil.setId(dao.save(perfil));
		
		assertEquals(Long.valueOf(2), perfil.getId());
		
		
	}
	
	@Ignore
	public void testUpdate(){
		perfil = new Perfil();
		perfil = PerfilBuilder.getEntity();
		
		assertEquals(true, dao.update(perfil));
		
	}
	
	@Ignore
	public void testFind(){
		perfil = new Perfil(2l);
		
		perfil = (Perfil) dao.find(perfil);
		
		
	}
	
	@Test
	public void testFindAll(){
		lista = dao.findAll(new Perfil());
		
		for(EntidadeDominio e : lista){
			
			System.out.println(((Perfil) e) .getId());
			System.out.println(((Perfil) e) .getPerfil());
			
		}
		
	}
	
}
