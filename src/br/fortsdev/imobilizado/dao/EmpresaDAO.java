package br.fortsdev.imobilizado.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.fortsdev.imobilizado.models.Empresa;
import br.fortsdev.imobilizado.services.ServicosBancoDeDados;

public class EmpresaDAO {
	ServicosBancoDeDados servicosBancoDeDados = new ServicosBancoDeDados();
	
	public ArrayList<Empresa>listaEmpresas(){
		String query = "SELECT CODEMP,\r\n" + 
				"       RAZAOSOCIAL,\r\n" + 
				"       CGC,\r\n" + 
				"       NOMEFANTASIA\r\n" + 
				"\r\n" + 
				"FROM TSIEMP";
		
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();
        
		ArrayList<List<Object>> resultadoQuery = servicosBancoDeDados.consulta(query, parametros, 4);
		Empresa empresa;
		ArrayList<Empresa> listaDeEmpresas = null;
		
		if (resultadoQuery != null && resultadoQuery.size() > 0) {
			listaDeEmpresas = new ArrayList<>();	
			for(int i = 0; i < resultadoQuery.size(); i++) {
					empresa = new Empresa();
					empresa.setCodEmp(((BigDecimal) resultadoQuery.get(i).get(0)).intValue());
					empresa.setRazaoSocial(resultadoQuery.get(i).get(1).toString());
					empresa.setCnpj(resultadoQuery.get(i).get(2).toString());
					empresa.setNomeEmpresa(resultadoQuery.get(i).get(3).toString());
					listaDeEmpresas.add(empresa);
			}
		}
		return listaDeEmpresas;
	}
}
