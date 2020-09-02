package br.fortsdev.contracheque.rest;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import br.fortsdev.contracheque.dao.LoginDAO;
import br.fortsdev.contracheque.models.Resposta;
import br.fortsdev.contracheque.models.Usuario;

@Path("/login")
public class ServicosLogin {

	private LoginDAO loginDAO;
 
	@PostConstruct
	private void init() {
		loginDAO = new LoginDAO();
	}
	
	@GET
	@Path("/list")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario realizaLogin(@HeaderParam("login") String login, @HeaderParam("senha") String senha) {
		System.out.println("realziando login...");
		Usuario usuario = loginDAO.autenticaUsuario(login, senha);
		return usuario;
	}
	
	@POST
	@Path("/edit")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean atualizaSenha(@HeaderParam("cpf") String cpf) throws MessagingException {
		System.out.println("atualizando senha...");
		System.out.println("resultado da edição: " + loginDAO.atualizaSenhaUsuario(cpf)); 
		return loginDAO.atualizaSenhaUsuario(cpf);
	}
	
	@POST
	@Path("/editemail")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean atualiza_email(@HeaderParam("email") String email, @HeaderParam("cpf") String cpf){
		System.out.println("atualizando email...");
		return loginDAO.insereEmail(email, cpf);
	}
	
	@POST
	@Path("/editusuario")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean atualiza_usuario(@HeaderParam("email") String email, @HeaderParam("telefone") String telefone, @HeaderParam("cpf") String cpf){
		System.out.println("atualizando usuario...  " + email + " telefone: " + telefone + "  cpf: " + cpf);
		
		return loginDAO.atualizaDadosUsuario(email, telefone, cpf);
	}
	
	@POST
	@Path("/insert")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(MediaType.TEXT_PLAIN)
	public Resposta insereUsuario(String inputJson) throws MessagingException, JSONException {
		JSONObject recoData = new JSONObject(inputJson);
		String cpf = recoData.get("cpf").toString();
		String senha = recoData.get("senha").toString();
		System.out.println("inserindo usuário...");

		return loginDAO.cadastrarFuncionario(cpf, senha);
	}
}
