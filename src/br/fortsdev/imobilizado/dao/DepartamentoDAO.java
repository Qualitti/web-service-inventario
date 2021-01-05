package br.fortsdev.imobilizado.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.fortsdev.imobilizado.models.Departamento;
import br.fortsdev.imobilizado.services.ServicosBancoDeDados;

public class DepartamentoDAO {

ServicosBancoDeDados servicosBancoDeDados = new ServicosBancoDeDados();
	
	public ArrayList<Departamento>listaDepartamentos(){
		String query = "SELECT CODDEP,\r\n" + 
				"       DESCRDEP\r\n" + 
				"\r\n" + 
				"FROM TFPDEP\r\n" + 
				"\r\n" + 
				"WHERE ATIVO = 'S'\r\n" + 
				"     AND CODDEP > 0 \r\n"+
				"     \r\n" + 
				"ORDER BY DESCRDEP";
		
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();

        
		ArrayList<List<Object>> resultadoQuery = servicosBancoDeDados.consulta(query, parametros, 2);
		Departamento departamento;
		ArrayList<Departamento> listaDeDepartamentos = null;
		
		if (resultadoQuery != null && resultadoQuery.size() > 0) {
			listaDeDepartamentos = new ArrayList<>();	
			for(int i = 0; i < resultadoQuery.size(); i++) {
					departamento = new Departamento();
					departamento.setCodDep(((BigDecimal) resultadoQuery.get(i).get(0)).intValue());
					departamento.setDescrDep(resultadoQuery.get(i).get(1).toString());
					listaDeDepartamentos.add(departamento);
				}
		}
		return listaDeDepartamentos;
	}
}
