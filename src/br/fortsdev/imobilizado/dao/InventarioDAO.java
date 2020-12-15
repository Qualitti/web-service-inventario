package br.fortsdev.imobilizado.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import br.fortsdev.imobilizado.models.Resposta;
import br.fortsdev.imobilizado.services.ServicosBancoDeDados;

public class InventarioDAO {
	private ServicosBancoDeDados servicosBancoDeDados = new ServicosBancoDeDados();
	private Resposta resposta = new Resposta();
	private String query;
	private Map<Integer, Object> parametros;
	private JSONObject jsonObject;
	
	
	public Resposta insereItensInventario(String inputJson) throws JSONException {
        System.out.println("Enviando itens do inventário.. ");
        java.util.Date data = new Date();
		String dataFormatada = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(data);
        int codInventario = buscaProximoCodigoInventario();
 
        
		JSONArray jsonArrayBens = new JSONArray(inputJson);
        
		for (int i = 0; i < jsonArrayBens.length(); i++) {
			jsonObject = jsonArrayBens.getJSONObject(i);
		    
		    query = "INSERT INTO AD_INVENTARIO ("
					+ "CODINVENTARIO, "
					+ "CODBEM, "
					+ "CODRFID, "
					+ "CODEMP, "
					+ "CODUSU, "
					+ "CODDEP, "
					+ "DATA, "
					+ "ENCONTRADO) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
					
			 parametros = new HashMap<Integer, Object>();
			 parametros.put(1, codInventario);
			 parametros.put(2, (int) jsonObject.get("codBem"));
			 parametros.put(3, jsonObject.get("codRFID").toString());
			 parametros.put(4, (int) jsonObject.get("codEmp"));
			 parametros.put(5, (int) jsonObject.get("codUsu"));
			 parametros.put(6, (int) jsonObject.get("codDep"));
			 parametros.put(7, dataFormatada);
			 if((boolean) jsonObject.get("encontrado"))
				 parametros.put(8, "S");
			 else 
				 parametros.put(8, "N");
		 
			 if (servicosBancoDeDados.atualizaDados(query, parametros) != 1) {
				 resposta.setErro(true);
				 return  resposta;
			 }
		    
		    System.out.println("Inseri o bem: " + jsonObject.get("descrBem").toString());
		}	
		
		parametros = null;
	    jsonArrayBens = null;
	    jsonObject = null;
	    
        resposta.setErro(false);
		return resposta;	
	}
	
	private int buscaProximoCodigoInventario() {
	
		int codInventario = 0;
		String query = "SELECT NVL(MAX(CODINVENTARIO),0) FROM AD_INVENTARIO";
		parametros = new HashMap<Integer, Object>();
		
		ArrayList<List<Object>> resultado = servicosBancoDeDados.consulta(query, parametros, 1);
		query = null;
		if (resultado != null && resultado.size() > 0) {
			codInventario  = ((BigDecimal) resultado.get(0).get(0)).intValue();
			return codInventario + 1;
		}else
			return 0;
	}
	
}
