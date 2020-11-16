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
public class ServiçosEmpresa {
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
		System.out.println("buscando empresas..");
		
		Empresa e1 = new Empresa();
		e1.setCodEmp(10);
		e1.setRazaoSocial("Abatedora Avícola Santa Vitoria");
		
		Empresa e2 = new Empresa();
		e2.setCodEmp(1);
		e2.setRazaoSocial("Abatedora Avícola Piracanjuba");
		
		ArrayList<Empresa> lista = new ArrayList<>();
		lista.add(e1);
		lista.add(e2);
		
		return lista;
	}

}
