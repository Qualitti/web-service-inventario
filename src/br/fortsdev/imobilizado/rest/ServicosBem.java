package br.fortsdev.imobilizado.rest;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.fortsdev.imobilizado.dao.BemDAO;
import br.fortsdev.imobilizado.models.Bem;


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
	public ArrayList<Bem> buscaBensPorDepartamento(@HeaderParam("codDep") int codDep) {
		System.out.println("buscando bens do departamento: " + codDep);
		return bemDAO.buscaListaBem(codDep);
	}
	
	
	@GET
	@Path("/listbem")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(MediaType.APPLICATION_JSON)
	public Bem buscaBem(@HeaderParam("codRfid") String codRfid) {
		System.out.println("buscando bem: " + codRfid);
		
		Bem bem1 = new Bem();
		bem1.setCodBem("8");
		bem1.setDescrBem("Cadeira");
		bem1.setCodRFID(codRfid);
		bem1.setCodDep(10);
		bem1.setDescrDep("T.I");
		bem1.setCodEmp(10);
		
		return bem1;
	}
}
