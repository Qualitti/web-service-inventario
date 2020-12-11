package br.fortsdev.imobilizado.rest;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import br.fortsdev.imobilizado.models.Bem;


@Path("/inventario")
public class ServicosInventario {

	//private BemDAO bemDAO;
	 
	/*@PostConstruct
	private void init() {
		bemDAO = new BemDAO();
	}*/
	
	@POST
	@Path("/insertcabecalho")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(("application/json; charset=UTF-8"))
	public boolean insereCabecalhoInventarioNoBD(String inputJson) throws JSONException {
        System.out.println("Enviando cabe�alho de invent�rio.. ");
		JSONObject recoData = new JSONObject(inputJson);
		System.out.println("C�digo do departamento no qual foi realizado o invent�rio: " + recoData.get("codDep"));
		return true;	
	}
	
	@POST
	@Path("/insertitens")
	@Produces(("application/json; charset=UTF-8"))
	@Consumes(("application/json; charset=UTF-8"))
	public boolean insereItensInventarioNoBD(String inputJson) throws JSONException {
        System.out.println("Enviando itens do invent�rio.. ");
        ArrayList<Bem> listaDeBens = new ArrayList<Bem>();
		JSONArray jsonArrayBens = new JSONArray(inputJson);
        
		for (int i = 0; i < jsonArrayBens.length(); i++) {
			JSONObject jsonObject = jsonArrayBens.getJSONObject(i);
		    Bem bem = new Bem();
		    bem.setCodBem(jsonObject.get("codBem").toString());
		    bem.setDescrBem(jsonObject.get("descrBem").toString());
		    listaDeBens.add(bem);
		    System.out.println("Inseri o bem: " + bem.getDescrBem());
		}	
		return true;	
	}
}
