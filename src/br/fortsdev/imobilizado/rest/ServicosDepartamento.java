package br.fortsdev.imobilizado.rest;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.fortsdev.imobilizado.dao.DepartamentoDAO;
import br.fortsdev.imobilizado.models.Departamento;

@Path("/departamento")
public class ServicosDepartamento {

	private DepartamentoDAO departamentoDAO;
	 
	@PostConstruct
	private void init() {
		departamentoDAO = new DepartamentoDAO();
	}
	
	@GET
	@Path("/list")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Departamento> buscaDepartamentos(@HeaderParam("codEmp") int codEmp) {
		System.out.println("buscando departamentos... ");
		return departamentoDAO.listaDepartamentos();
	}
}
