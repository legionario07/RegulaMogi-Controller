package br.com.regulamogi.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.regulamogi.dao.RepositoryDao;
import br.com.regulamogi.domain.Especialidade;
import br.com.regulamogi.domain.PrioridadeType;
import br.com.regulamogi.domain.Solicitacao;
import br.com.regulamogi.domain.StatusType;
import br.com.regulamogi.domain.UnidadeDeSaude;

public class SolicitacaoBuilder {

	private static Solicitacao solicitacao;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Solicitacao getEntity(){
		
		solicitacao = new Solicitacao();

		solicitacao.setNumeroSIS("140919");
		
		solicitacao.setDataConsulta(Calendar.getInstance());
		
		String data = "31/10/2017";
		Date dataEnvio = null;
		try {
			dataEnvio = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar c = Calendar.getInstance();
		c.setTime(dataEnvio);
		
		solicitacao.setDataEnvio(c);
		Especialidade e = new Especialidade(2l);
		e = (Especialidade) new RepositoryDao().find(e);
		
		solicitacao.setEspecialidade(e);
		
		solicitacao.setNumeroMemorando("02/2017");
		solicitacao.setPrioridade(PrioridadeType.URGENTE);
		solicitacao.setStatus(StatusType.ENVIADO);
		
		UnidadeDeSaude u = new UnidadeDeSaude(1l);
		u = (UnidadeDeSaude) new RepositoryDao().login(u);
		
		solicitacao.setUnidadeDeSaude(u);
		
		return solicitacao;
	}
	
}
