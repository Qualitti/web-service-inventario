package br.fortsdev.imobilizado.rest;

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

import br.fortsdev.imobilizado.dao.BemDAO;
import br.fortsdev.imobilizado.dao.LoginDAO;
import br.fortsdev.imobilizado.models.Resposta;
import br.fortsdev.imobilizado.models.Usuario;

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
		System.out.println("realziando login..." + login);
		Usuario usuario = loginDAO.autenticaUsuario(login, senha);
		System.out.println("Usuario: " + usuario.getLogin());
		return usuario;
	}
}
