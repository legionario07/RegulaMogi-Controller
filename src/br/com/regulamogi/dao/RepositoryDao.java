package br.com.regulamogi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;

import br.com.regulamogi.domain.EntidadeDominio;
import br.com.regulamogi.domain.Paciente;
import br.com.regulamogi.domain.UnidadeDeSaude;
import br.com.regulamogi.factory.HibernateUtil;

public class RepositoryDao implements IDAO {

	private Session session = null;

	@Override
	public Long save(EntidadeDominio entidade) {

		Long idResult = null;

		session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			idResult = (Long) session.save(entidade);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro ao Persistir objeto no Banco de Dados - \n" + e.getMessage());
		} finally {
			session.close();
		}

		return idResult;
	}

	@Override
	public Long update(EntidadeDominio entidade) {

		session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.update(entidade);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Erro ao Persistir objeto no Banco de Dados - \n" + e.getMessage());
			return 0l;
		} finally {
			session.close();
		}

		return 1l;

	}

	@Override
	public Long delete(EntidadeDominio entidade) {

		session = HibernateUtil.getSession();

		try {
			session.beginTransaction();
			entidade = session.load(entidade.getClass(), entidade.getId());
			session.remove(entidade);
			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro ao Deletar objeto no Banco de Dados - \n" + e.getMessage());
			return 0l;
		} finally {
			session.close();
		}

		return 1l;

	}

	@Override
	public EntidadeDominio find(EntidadeDominio entidade) {

		EntidadeDominio entidadeRetorno = null;

		session = HibernateUtil.getSession();
		try {
			entidadeRetorno = session.find(entidade.getClass(), entidade.getId());
		} catch (Exception e) {
			System.out.println("Erro ao Pesquisar objeto no Banco de Dados - \n" + e.getMessage());
		} finally {
			session.close();
		}

		return entidadeRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntidadeDominio> findAll(EntidadeDominio entidade) {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		StringBuilder sql = new StringBuilder();
		sql.append("from ");
		sql.append(entidade.getClass().getSimpleName());
		sql.append(" order by id asc");

		session = HibernateUtil.getSession();
		try {

			Query query = session.createQuery(sql.toString());
			lista = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao pesquisar objetos no Banco de Dados - \n" + e.getMessage());
		} finally {
			session.close();
		}

		return lista;
	}

	/**
	 * 
	 * @param entidade
	 * @return
	 */
	public EntidadeDominio loginPaciente(EntidadeDominio entidade) {

		Paciente paciente = null;

		if (!(entidade instanceof Paciente))
			return null;

		paciente = new Paciente();
		paciente = (Paciente) entidade;

		Paciente pacienteRetorno = new Paciente();

		StringBuilder sql = new StringBuilder();
		sql.append("from Colaborador c ");
		sql.append("where c.usuario.login = ? and ");
		sql.append("c.usuario.senha = md5(?)");

		session = HibernateUtil.getSession();
		try {

			Query query = session.createQuery(sql.toString());
			query.setParameter(0, paciente.getConta().getLogin().toUpperCase());
			query.setParameter(1, paciente.getConta().getSenha());

			pacienteRetorno = (Paciente) query.getSingleResult();

		} catch (NoResultException nre) {
			return null;
		} catch (Exception e) {
			System.out.println("Usuario ou senha Inválidos - \n" + e.getMessage());
			return null;
		} finally {
			session.close();
		}

		return pacienteRetorno;
	}

	/**
	 * 
	 * @param estado
	 *            Recebe um entidade Paciente e localiza pelo seu numero SIS
	 * @return retorna null se nao encontrar ou um entidade Paciente preenchida
	 */
	public Paciente findPacienteBySIS(Paciente paciente) {

		Paciente pacienteRetorno = null;

		session = HibernateUtil.getSession();
		try {

			Query query = session.createQuery("from Paciente where SIS = ?");
			query.setParameter(0, paciente.getSIS());

			pacienteRetorno = new Paciente();
			pacienteRetorno = (Paciente) query.getSingleResult();

		} catch (NoResultException nre) {
			return null;
		} catch (Exception e) {
			System.out.println("Erro ao Pesquisar objeto no Banco de Dados - \n" + e.getMessage());
		} finally {
			session.close();
		}

		return pacienteRetorno;
	}

	/**
	 * Metodo que encontra todas as solicitacoes de um paciente
	 * 
	 * @param unidade
	 * @return List de EntidadeDominio contendos os pacientes encontrados
	 */
	@SuppressWarnings("unchecked")
	public List<EntidadeDominio> findSolicitacoesByPaciente(Paciente paciente) {

		List<EntidadeDominio> listaSolicitacoes = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM regulamogi.paciente_solicitacao ");
		sql.append("inner join paciente on paciente.id = paciente_solicitacao.Paciente_id ");
		sql.append("where paciente.SIS = ?");

		session = HibernateUtil.getSession();
		try {

			Query query = session.createQuery(sql.toString());
			query.setParameter(0, paciente.getSIS());

			listaSolicitacoes = query.getResultList();

		} catch (NoResultException nre) {
			return null;
		} catch (Exception e) {
			System.out.println("Erro ao Pesquisar objeto no Banco de Dados - \n" + e.getMessage());
		} finally {
			session.close();
		}

		return listaSolicitacoes;
	}
	

	/**
	 * Metodo que retorna todos os paciente de um determinado memorando
	 * de uma especifica UnidadeDeSaude
	 * @param numeroMemorando
	 * @return List entidadeDominio contendo todas as solicitacoes
	 */
	@SuppressWarnings("unchecked")
	public List<EntidadeDominio> findSolicitacoesByNumeroMemorando(String numeroMemorando, UnidadeDeSaude unidade) {

		List<EntidadeDominio> listaSolicitacoes = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("from Solicitacao ");
		sql.append("where numeroMemorando = ? and unidadeDeSaude_id = ?");

		session = HibernateUtil.getSession();
		try {

			Query query = session.createQuery(sql.toString());
			query.setParameter(0, numeroMemorando);
			query.setParameter(1, unidade.getId());

			listaSolicitacoes =query.getResultList();

		} catch (NoResultException nre) {
			return null;
		} catch (Exception e) {
			System.out.println("Erro ao Pesquisar objeto no Banco de Dados - \n" + e.getMessage());
		} finally {
			session.close();
		}

		return listaSolicitacoes;
	}


}
