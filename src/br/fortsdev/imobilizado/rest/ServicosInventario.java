package br.fortsdev.imobilizado.rest;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.JSONException;
import org.json.JSONObject;
import br.fortsdev.imobilizado.dao.InventarioDAO;
import br.fortsdev.imobilizado.models.Resposta;


@Path("/inventario")
public class ServicosInventario {

	private InventarioDAO inventarioDAO;
	 
	@PostConstruct
	private void init() {
		inventarioDAO = new InventarioDAO();
	}

	
	@POST
	@Path("/insert")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(("application/json; charset=UTF-8"))
	public Resposta insereItensInventarioNoBD(String inputJson) throws JSONException {
	       return inventarioDAO.insereItensInventario(inputJson);
	}
}
