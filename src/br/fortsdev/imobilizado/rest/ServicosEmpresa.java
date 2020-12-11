package br.fortsdev.imobilizado.rest;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.fortsdev.imobilizado.dao.EmpresaDAO;
import br.fortsdev.imobilizado.models.Empresa;

@Path("/empresa")
public class ServicosEmpresa {
	private EmpresaDAO empresaDAO;
	 
	@PostConstruct
	private void init() {
		empresaDAO = new EmpresaDAO();
	}
	
	@GET
	@Path("/list")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Empresa> buscaInfoImobilizado() {
		System.out.println("Buscando empresas..");
		return empresaDAO.listaEmpresas();
	}

}
