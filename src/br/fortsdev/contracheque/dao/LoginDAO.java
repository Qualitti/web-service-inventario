package br.fortsdev.contracheque.dao;

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

import br.fortsdev.contracheque.models.Resposta;
import br.fortsdev.contracheque.models.Usuario;

public class LoginDAO extends DataAccessObject{
	Map<String, String> env = System.getenv();
	String dataLimite;
	int codFunc, empresaFunc;
	String nomeFunc;
	
	
	public Usuario autenticaUsuario(String login, String senha) {
		Usuario usuario = new Usuario();
		
		String query = "SELECT APP.CGC_CPF, \r\n" + 
				"       APP.INTERNO, \r\n" + 
				"       NVL(APP.ATIVO,'N'), \r\n" + 
				"       APP.TIPOUSUARIO, \r\n" + 
				"       NVL(APP.NOMEUSU,'Erro'), \r\n" + 
				"       NVL(APP.EMAIL,'Erro'), \r\n" + 
				"       NVL(APP.TELEFONE, 'Erro')\r\n" + 
				"       \r\n" + 
				"FROM AD_USUAPP APP\r\n" +  
				"     \r\n" + 
				"WHERE APP.CGC_CPF = ?";
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();
		parametros.put(1, login);
        
		ArrayList<List<Object>> resultadoQuery = consulta(query, parametros, 7);
		
		if (resultadoQuery != null && resultadoQuery.size() > 0) {
				usuario.setLogin(resultadoQuery.get(0).get(0).toString());
				usuario.setSenha(resultadoQuery.get(0).get(1).toString());
				usuario.setAtivo(resultadoQuery.get(0).get(2).toString());	
				usuario.setTipoUsu(Integer.parseInt(resultadoQuery.get(0).get(3).toString()));
				usuario.setNome(resultadoQuery.get(0).get(4).toString());
				usuario.setEmail(resultadoQuery.get(0).get(5).toString());
				usuario.setTelefone(resultadoQuery.get(0).get(6).toString());
		}
		
		return usuario;
	}
	
	public boolean atualizaSenhaUsuario(String cpf){
		boolean respostaEnvioEmail = false;
		Random gerador = new Random();
		String novaSenha = ""+gerador.nextInt(9999);
			
		try {	
			respostaEnvioEmail = enviaEmail(cpf, novaSenha);
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
		String query = "UPDATE AD_USUAPP SET INTERNO = ? WHERE CGC_CPF = ?";
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();
		parametros.put(1, novaSenha);
		parametros.put(2, cpf);
			
		if (atualizaDados(query, parametros) != 0 && respostaEnvioEmail) {
			return true;
		}else { 
			return false;
		}	
	}
	
	public boolean insereEmail(String email, String cpf){
		String query = "UPDATE AD_USUAPP SET EMAIL = ? WHERE CGC_CPF = ?";
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();
		parametros.put(1, email);
		parametros.put(2, cpf);
			
		if (atualizaDados(query, parametros) != 0) {
			return true;
		}else { 
			return false;
		}
	}
	
	public boolean atualizaDadosUsuario(String email, String telefone, String cpf) {
		String query = "UPDATE AD_USUAPP SET EMAIL = ?, TELEFONE = ? WHERE CGC_CPF = ?";
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();
		parametros.put(1, email);
		parametros.put(2, telefone);
		parametros.put(3, cpf);
			
		if (atualizaDados(query, parametros) != 0) {
			System.out.println("retornei verdadeiro");
			return true;
		}else { 
			System.out.println("retornei um erro");
			return false; 
		}
	}
	
	public boolean enviaEmail(String cpf, String novaSenha) throws MessagingException {
			try{
				//codigo para envio de e-mail
			    String assunto = "Nova senha aplicativo Qualitti";
			    String email = consultaEmail(cpf);
			    String msg = "Sua nova senha do aplicativo Qualitti é: "+ novaSenha;
	
			    final String fromEmail = env.get("USUARIO_EMAIL"); 
				final String password = env.get("SENHA_EMAIL"); 
				final String toEmail = email;
					
				System.out.println("TLSEmail Start");
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
					
				Authenticator auth = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmail, password);
					}
				};
				Session session = Session.getInstance(props, auth);

				MimeMessage mimeMessage = new MimeMessage(session);
			    mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
			    mimeMessage.addHeader("format", "flowed");
			    mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
			    mimeMessage.setFrom(new InternetAddress("no_reply@example.com", "Aplicativo Qualitti"));
			    mimeMessage.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
			    mimeMessage.setSubject(assunto, "UTF-8");
			    mimeMessage.setText(msg, "UTF-8");
			    mimeMessage.setSentDate(new Date());

			    mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		    	Transport.send(mimeMessage);  

			    System.out.println("EMail Sent Successfully!!");
				
				System.out.println("codigo disparado: " + msg);
				return true;
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		
	}
	
	public String consultaEmail(String cpfCnpj) {
		String query = "SELECT EMAIL FROM AD_USUAPP WHERE CGC_CPF = ?";
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();
		parametros.put(1, cpfCnpj);
		String email = null;
		
		ArrayList<List<Object>> resultadoQuery = consulta(query, parametros, 1);
		if (resultadoQuery != null && resultadoQuery.size() > 0) {	
				email =  (String) resultadoQuery.get(0).get(0);			
		}else {
			return email;
		}
		
		return email;
	}

	public Resposta cadastrarFuncionario(String cpf, String senha) {
		Resposta r = new Resposta();
		String query = "SELECT COUNT(CGC_CPF) AS QTD_CPF_CNPJ FROM AD_USUAPP WHERE CGC_CPF = ?";
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();
		parametros.put(1, cpf);
		ArrayList<List<Object>> resultadoQuery = consulta(query, parametros, 1);
		if (resultadoQuery != null && resultadoQuery.size() > 0) {
			int qtd_cpf_cnpj = ((BigDecimal) resultadoQuery.get(0).get(0)).intValue();
			System.out.println("quantidade de usuarios com este cpf "+cpf+" : " + qtd_cpf_cnpj);
			if(qtd_cpf_cnpj > 0) {
				r.setMsgErro("Este cpf já está cadastrado no aplicativo!");
				r.setCorpoResposta(null);
				return r;
			}
		}

		query = "SELECT COUNT(CODFUNC) AS QTD_FUNC, CODFUNC, CODEMP, NOMEFUNC FROM TFPFUN WHERE CPF = ? AND SITUACAO = 1 GROUP BY CODFUNC, CODEMP, NOMEFUNC";
		parametros = new HashMap<Integer, Object>();
		parametros.put(1, cpf);
		resultadoQuery = consulta(query, parametros, 4);
		if (resultadoQuery != null && resultadoQuery.size() > 0) {
			codFunc = ((BigDecimal) resultadoQuery.get(0).get(1)).intValueExact();
			empresaFunc = ((BigDecimal) resultadoQuery.get(0).get(2)).intValueExact();
			nomeFunc = resultadoQuery.get(0).get(3).toString();	
		}else {
			r.setMsgErro("Este cpf não pertence a um funcionário da empresa!");
			r.setCorpoResposta(null);
			return r;
		}

		query = "SELECT MAX(DTLIMITECONTRACHEQUE) FROM AD_USUAPP";
		parametros = new HashMap<Integer, Object>();
        resultadoQuery = consulta(query, parametros, 1);
        if (resultadoQuery != null && resultadoQuery.size() > 0)
			dataLimite = (String) resultadoQuery.get(0).get(0);		
        
        int codUsuario = getMaxCodUsuario();
		
		String query_insert = "INSERT INTO AD_USUAPP(\r\n" + 
					"    CODUSU, \r\n" + 
					"    NOMEUSU, \r\n" + 
					"    ATIVO, \r\n" + 
					"    INTERNO, \r\n" + 
					"    EMAIL, \r\n" + 
					"    CODVEND, \r\n" + 
					"    CODEMP, \r\n" + 
					"    CODFUNC, \r\n" + 
					"    CGC_CPF, \r\n" + 
					"    CODPARC,\r\n" + 
					"    TIPOUSUARIO,\r\n" +
					"    DTLIMITECONTRACHEQUE\r\n" +
					"    ) \r\n" + 
					"\r\n" + 
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			parametros = new HashMap<Integer, Object>();
			parametros.put(1, codUsuario);
			parametros.put(2, nomeFunc); 
			parametros.put(3, "S");
			parametros.put(4, senha);
			parametros.put(5, null);
			parametros.put(6, null);
			parametros.put(7, empresaFunc);
			parametros.put(8, codFunc);
			parametros.put(9, cpf);
			parametros.put(10, null);
			parametros.put(11, 2);
			parametros.put(12, dataLimite);
			
			//inserindo o funcionário no banco de dados
			if (insereDados(query_insert, parametros) != 0) {
				r.setMsgErro(null);
				r.setCorpoResposta("Usuário cadastrado com sucesso!");
				return r;
			}else {
				r.setMsgErro("Erro ao cadastrar usuário!");
				r.setCorpoResposta(null);
				return r;
			}		
				
	}
	
	public int getMaxCodUsuario(){
			String query = "SELECT MAX(CODUSU) AS CODUSU FROM AD_USUAPP";
			Map<Integer, Object> parametros = new HashMap<Integer, Object>();
			int codFunc = 0;
			
			ArrayList<List<Object>> resultadoQuery = consulta(query, parametros, 1);
			if (resultadoQuery != null && resultadoQuery.size() > 0) 
					codFunc = (int) ((BigDecimal) resultadoQuery.get(0).get(0)).intValueExact();
			
			return codFunc + 1;		
	}

}
