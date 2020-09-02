package br.fortsdev.contracheque.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.fortsdev.contracheque.bdconfig.BDConfig;

public abstract class DataAccessObject{
	public ArrayList<List<Object>> consulta(String query, Map<Integer, Object> parametros, int qtd_parametros_resposta) {
		try {
			    Connection conexao  = BDConfig.obtemConexao();
				java.sql.PreparedStatement ppstr = conexao.prepareStatement(query);	
				
				Iterator it = parametros.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        ppstr.setObject((int) pair.getKey(), pair.getValue());
			        it.remove(); 
			    }
			
				ResultSet resultSet = ppstr.executeQuery();
				ArrayList<List<Object>> resultadoQuery  = new ArrayList<List<Object>>();
				while(resultSet.next()){
					List<Object> resultado = new ArrayList<Object>();
					for(int i = 1; i <= qtd_parametros_resposta; i++) {
						resultado.add(resultSet.getObject(i));
					}
					resultadoQuery.add(resultado);
				}
				resultSet.close();
				ppstr.close();
				conexao.close();
				resultSet = null;
				ppstr = null;
				conexao = null;
				return resultadoQuery;
		}catch (Exception e) {
			System.out.println("Erro na consulta do banco.");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public int insereDados(String query, Map<Integer, Object> parametros) {
		
		try {
			Connection conexao  = BDConfig.obtemConexao();
			java.sql.PreparedStatement ppstr = conexao.prepareStatement(query);
			
			for (Entry<Integer, Object> entry : parametros.entrySet()) {
			    Integer key = entry.getKey();
			    Object value = entry.getValue();
			    
			    ppstr.setObject(key, value);
			}
			
			int resultado = ppstr.executeUpdate();
			ppstr.close();
			conexao.close();
			ppstr = null;
			conexao = null;
		    return resultado;
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int atualizaDados(String query, Map<Integer, Object> parametros) {
		try {
			Connection conexao  = BDConfig.obtemConexao();
			java.sql.PreparedStatement ppstr = conexao.prepareStatement(query);
			
			for (Entry<Integer, Object> entry : parametros.entrySet()) {
			    Integer key = entry.getKey();
			    Object value = entry.getValue();
			    ppstr.setObject(key, value);
			}
			
			int resultado = ppstr.executeUpdate();
			ppstr.close();
			conexao.close();
			ppstr = null;
			conexao = null;
		    return resultado;
		    
		} catch (SQLException e) {
			e.printStackTrace(); 
			return 0;
		}
	}
}
