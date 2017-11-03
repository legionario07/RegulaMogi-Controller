package br.com.regulamogi.dao.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.regulamogi.builder.PacienteBuilder;
import br.com.regulamogi.dao.RepositoryDao;
import br.com.regulamogi.domain.EntidadeDominio;
import br.com.regulamogi.domain.Paciente;
import br.com.regulamogi.domain.Telefone;

public class TESTPacienteDAO {

	private RepositoryDao dao;
	private Paciente paciente;
	private List<EntidadeDominio> lista;
	
	@Before
	public void inicializarComponentes(){
		
		dao = new RepositoryDao();
		lista = new ArrayList<EntidadeDominio>();
		
	}
	
	
	@Ignore
	public void testSave(){
		
		paciente = new Paciente();
		paciente = PacienteBuilder.getEntity();
		
		paciente.setId(dao.save(paciente));
		
		assertEquals(Long.valueOf(2), paciente.getId());
		
		
	}
	
	@Ignore
	public void testUpdate(){
		paciente = new Paciente();
		paciente = PacienteBuilder.getEntity();
		paciente.setId(6l);
		
		assertEquals(true, dao.update(paciente));
		
	}
	
	@Ignore
	public void testFind(){
		paciente = new Paciente(2l);
		
		paciente = (Paciente) dao.find(paciente);
		
		
	}
	
	@Ignore
	public void testFindAll(){
		lista = dao.findAll(new Paciente());
		
		for(EntidadeDominio e : lista){
			
			System.out.println(((Paciente) e) .getId());
			System.out.println(((Paciente) e) .getNome());
			System.out.println(((Paciente) e) .getConta().getPerfil().getPerfil());
			
		}
		
	}
	
	@Ignore
	public void testFindBySIS(){
		paciente = new Paciente(2l);
		paciente.setSIS("111111");
		
		paciente = (Paciente) dao.findPacienteBySIS(paciente);
		
		System.out.println(paciente.getId());
		System.out.println(paciente.getConta().getPerfil().getPerfil());
		System.out.println(paciente.getSolicitacoes().size());
	}
	
	@Ignore
	public void loginPaciente(){
		paciente = new Paciente();
		paciente.getConta().setLogin("111111");
		paciente.getConta().setSenha("123");
		
		paciente = (Paciente) dao.loginPaciente(paciente);
		
		System.out.println(paciente.getId());
		System.out.println(paciente.getConta().getPerfil().getPerfil());
		System.out.println(paciente.getTelefones().size());
	}
	
	@Test
	public void findPacienteBySISAndCelular(){
		paciente = new Paciente();
		
		paciente.getConta().setLogin("111111");
		
		Telefone t = new Telefone();
		t.setNumero("11971986033");
		
		paciente.getTelefones().add(t);
		
		paciente = (Paciente) dao.findPacienteBySIS(paciente);
		
//		if(paciente!=null && paciente.getId() > 0){
//			for(Telefone telefone : paciente.getTelefones()){
//				if(telefone.equals(numero)){
//					return paciente;
//				}
//			}
//		}
		
		System.out.println(paciente.getId());
		System.out.println(paciente.getConta().getPerfil().getPerfil());
		System.out.println(paciente.getTelefones().size());
	}
	
	
}
