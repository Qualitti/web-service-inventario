package br.fortsdev.imobilizado.rest;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.fortsdev.imobilizado.dao.ImobilizadoDAO;


@Path("/imobilizado")
public class ServicosImobilizado {

	private ImobilizadoDAO imobilizadoDAO;
	 
	@PostConstruct
	private void init() {
		imobilizadoDAO = new ImobilizadoDAO();
	}
	
	@GET
	@Path("/list")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean buscaInfoImobilizado(@HeaderParam("codRfid") int codRfid) {
		System.out.println("buscando informações imobilizado: " + codRfid);
		return imobilizadoDAO.buscaImobilizado(codRfid);
	}
	
}
