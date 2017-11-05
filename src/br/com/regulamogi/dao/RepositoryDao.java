package br.com.regulamogi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;

import br.com.regulamogi.domain.Conta;
import br.com.regulamogi.domain.EntidadeDominio;
import br.com.regulamogi.domain.Paciente;
import br.com.regulamogi.domain.Perfil;
import br.com.regulamogi.domain.UnidadeDeSaude;
import br.com.regulamogi.factory.ConnectionFactory;
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
		sql.append("from Paciente p ");
		sql.append("where p.conta.login = ? and ");
		sql.append("p.conta.senha = md5(?)");

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
	 * Metodo que retorna todos os paciente de um determinado memorando de uma
	 * especifica UnidadeDeSaude
	 * 
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
	 * 
	 * @param entidade
	 * @return
	 */
	public EntidadeDominio findBySISAndCelular(EntidadeDominio entidade) {

		Paciente paciente = null;

		if (!(entidade instanceof Paciente))
			return null;

		paciente = new Paciente();
		paciente = (Paciente) entidade;

		Paciente pacienteRetorno = new Paciente();
		StringBuilder sql = new StringBuilder();
		sql.append("select pes from Paciente pes left join Telefone ");
		sql.append("t where pes.conta.login = ? AND t.numero = ? ");
		sql.append("and t.tipo = 'CELULAR'");

		session = HibernateUtil.getSession();
		try {

			Query query = session.createQuery(sql.toString());
			query.setParameter(0, paciente.getConta().getLogin().toUpperCase());
			query.setParameter(1, paciente.getTelefones().get(0).getNumero());

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
	 * @param e
	 * @return
	 */
	public UnidadeDeSaude login(EntidadeDominio e) {

		UnidadeDeSaude unidade = null;
		
		UnidadeDeSaude u = new UnidadeDeSaude();
		u = (UnidadeDeSaude) e;

		StringBuilder sql = new StringBuilder();
		sql.append("select * from conta ");
		sql.append("inner join unidadedesaude_conta on unidadedesaude_conta.contas_id = conta.id ");
		sql.append("inner join unidadedesaude on unidadedesaude.id = unidadedesaude_conta.UnidadeDeSaude_id ");
		sql.append("where conta.login = ? and conta.senha = md5(?) ");

		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, u.getContas().get(0).getLogin().toUpperCase());
			ps.setString(2, u.getContas().get(0).getSenha());

			ResultSet rSet = ps.executeQuery();

			if (rSet.next()) {
				Conta conta = new Conta(rSet.getLong("contas_id"));
				Perfil p = new Perfil(rSet.getLong("perfil_id"));
				conta.setPerfil(p);
				
				unidade = new UnidadeDeSaude(rSet.getLong("UnidadeDeSaude_id"));
				List<Conta> contas = new ArrayList<>();
				contas.add(conta);
				unidade.setContas(contas);
				
			}

		} catch (NoResultException nre) {
			return null;
		} catch (Exception ex) {
			System.out.println("Erro ao Pesquisar objeto no Banco de Dados - \n" + ex.getMessage());
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return unidade;
	}
	
    public Long savePacienteSemConta(EntidadeDominio entidade) {
        if (!(entidade instanceof Paciente)) {
            return 0l;
        }

        Paciente paciente = new Paciente();
        paciente = (Paciente) entidade;

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO paciente ");
        sql.append("(SIS, lastLogin, nome) VALUES (?,?,?) ");

        Connection con = ConnectionFactory.getConnection();
        try {
            if (con == null || con.isClosed()) {
                con = ConnectionFactory.getConnection();
            }
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        PreparedStatement pstm;
        try {
            pstm = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String data = sdf.format(paciente.getLastLogin().getTime());
            
            pstm.setString(++i, paciente.getSIS());
            pstm.setString(++i, data);
            pstm.setString(++i, paciente.getNome().toUpperCase());

            pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                paciente.setId(rs.getLong(1));
            }
            rs.close();
            pstm.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0l;
        }

        return paciente.getId();
    }
    
    public void saveTabelaPacientesESolicitacoes(Long idPaciente, Long idSolicitacao) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO paciente_solicitacao ");
        sql.append("(Paciente_id, solicitacoes_id) VALUES (?,?) ");

        Connection con = ConnectionFactory.getConnection();
        try {
            if (con == null || con.isClosed()) {
                con = ConnectionFactory.getConnection();
            }
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        PreparedStatement pstm;
        try {
            pstm = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            
            pstm.setLong(++i, idPaciente);
            pstm.setLong(++i, idSolicitacao);

            pstm.executeUpdate();

            pstm.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
