package br.com.regulamogi.dao;

import java.util.List;

import br.com.regulamogi.domain.EntidadeDominio;


public interface IDAO {

	/**
	 * 
	 * @param entidade
	 *            Recebe uma entidade Dominio por parametro e cadastra no banco
	 *            de Dados
	 * @return Um numero Long com o Id do Objeto Cadastrado no Banco de Dados
	 */
	public Long save(EntidadeDominio entidade);

	/**
	 * 
	 * @param entidade
	 *            Recebe uma entidade Dominio por parametro e atualiza no banco
	 *            de Dados
	 */
	public Long update(EntidadeDominio entidade);

	/**
	 * 
	 * @param entidade
	 *            Recebe uma entidade Dominio por parametro e remove do Banco de
	 *            Dados
	 */
	public Long delete(EntidadeDominio entidade);

	/**
	 * 
	 * @param entidade
	 *            Recebe uma entidade Dominio por parametro e localiza de acordo
	 *            com o Id informado na Entidade
	 * @return Uma Entidade Dominio se localizado ou null se nao encontrado
	 */
	public EntidadeDominio find(EntidadeDominio entidade);

	/**
	 * 
	 * @param entidade
	 *            Recebe uma entidade Dominio por parametro e localiza todas as
	 *            entidades dominios encontrados
	 * @return Uma Lista de EntidadeDominio.
	 */
	public List<EntidadeDominio> findAll(EntidadeDominio entidade);
}
