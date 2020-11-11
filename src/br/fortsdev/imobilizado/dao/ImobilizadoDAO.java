package br.fortsdev.imobilizado.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.fortsdev.imobilizado.models.Imobilizado;
import br.fortsdev.imobilizado.models.Resposta;
import br.fortsdev.imobilizado.models.Usuario;
import br.fortsdev.imobilizado.services.ServicosBancoDeDados;

public class ImobilizadoDAO{
	Map<String, String> env = System.getenv();
	String dataLimite;
	int codFunc, empresaFunc;
	String nomeFunc;
	ServicosBancoDeDados servicosBancoDeDados = new ServicosBancoDeDados();
	
	public boolean buscaImobilizado (int codRfid) {
		Imobilizado imobilizado = new Imobilizado();
		
		String query = "SELECT BEM.CODBEM\r\n" + 
				"\r\n" + 
				"FROM TCIBEM BEM\r\n" + 
				"\r\n" + 
				"WHERE AD_CODRFID = ?";
		
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();
		parametros.put(1, codRfid);
        
		ArrayList<List<Object>> resultadoQuery = servicosBancoDeDados.consulta(query, parametros, 1);
		
		if (resultadoQuery != null && resultadoQuery.size() > 0) 
			return true;
		else 
			return false;
	}
}
