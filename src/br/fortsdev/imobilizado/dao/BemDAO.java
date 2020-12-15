package br.fortsdev.imobilizado.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.fortsdev.imobilizado.models.Bem;
import br.fortsdev.imobilizado.models.Departamento;
import br.fortsdev.imobilizado.services.ServicosBancoDeDados;

public class BemDAO{
	Map<String, String> env = System.getenv();
	String dataLimite;
	int codFunc, empresaFunc;
	String nomeFunc;
	ServicosBancoDeDados servicosBancoDeDados = new ServicosBancoDeDados();
	
	public boolean buscaBem (int codRfid) {
	
		String query = "SELECT BEM.CODBEM\r\n" + 
				"\r\n" + 
				"FROM TCIBEM BEM\r\n" + 
				"\r\n" + 
				"WHERE AD_CODRFID = ?";
		
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();
		parametros.put(1, codRfid);
        
		ArrayList<List<Object>> resultadoQuery = servicosBancoDeDados.consulta(query, parametros, 1);
		
		if (resultadoQuery != null && resultadoQuery.size() > 0) 
			return true;
		else 
			return false;
	}
	
	public ArrayList<Bem> buscaListaBem(int codDep){
		 String query = "SELECT BEM.CODPROD,\r\n" + 
		 		"       PRO.DESCRPROD,\r\n" + 
		 		"       BEM.CODBEM,\r\n" + 
		 		"       NVL(BEM.DESCRBEM, 'S/D'),\r\n" + 
		 		"       BEM.CODEMP,\r\n" + 
		 		"       DEP.CODDEPTO,\r\n" + 
		 		"       DEP.DESCRDEP,\r\n" + 
		 		"       BEM.AD_NUMPATRIMONIO AS COD_RFID\r\n" + 
		 		"\r\n" + 
		 		"FROM TCIBEM BEM,\r\n" + 
		 		"     TGFPRO PRO,\r\n" + 
		 		"     VCILOCATUAL DEP\r\n" + 
		 		"\r\n" + 
		 		"WHERE \r\n" + 
		 		"        BEM.CODPROD = PRO.CODPROD\r\n" + 
		 		"    AND BEM.CODBEM = DEP.CODBEM\r\n" + 
		 		"    AND DEP.CODDEPTO = ?\r\n" + 
		 		"    AND BEM.AD_NUMPATRIMONIO IS NOT NULL";
		 
		 Map<Integer, Object> parametros = new HashMap<Integer, Object>();
		 parametros.put(1, codDep);

	        
			ArrayList<List<Object>> resultadoQuery = servicosBancoDeDados.consulta(query, parametros, 8);
			Bem bem;
			ArrayList<Bem> listaDeBens = null;
			
			if (resultadoQuery != null && resultadoQuery.size() > 0) {
				listaDeBens = new ArrayList<>();	
				for(int i = 0; i < resultadoQuery.size(); i++) {
						bem = new Bem();
						bem.setCodProd(((BigDecimal) resultadoQuery.get(i).get(0)).intValue());
						bem.setDescrProd(resultadoQuery.get(i).get(1).toString());
						bem.setCodBem(resultadoQuery.get(i).get(2).toString());
						bem.setDescrBem(resultadoQuery.get(i).get(3).toString());
						bem.setCodEmp(((BigDecimal) resultadoQuery.get(i).get(4)).intValue());
						bem.setCodDep(((BigDecimal) resultadoQuery.get(i).get(5)).intValue());
						bem.setDescrDep(resultadoQuery.get(i).get(6).toString());
						bem.setCodRFID(resultadoQuery.get(i).get(7).toString());
						listaDeBens.add(bem);
						System.out.println(bem.toString());
				}
			}
			return listaDeBens;
	}
}
