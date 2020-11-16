package br.fortsdev.imobilizado.rest;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.fortsdev.imobilizado.dao.BemDAO;


@Path("/bem")
public class ServicosBem {

	private BemDAO bemDAO;
	 
	@PostConstruct
	private void init() {
		bemDAO = new BemDAO();
	}
	
	@GET
	@Path("/list")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean buscaInfoImobilizado(@HeaderParam("codDep") int codRfid) {
		System.out.println("buscando informações imobilizado: " + codRfid);
		return bemDAO.buscaImobilizado(codRfid);
	}
	
}
