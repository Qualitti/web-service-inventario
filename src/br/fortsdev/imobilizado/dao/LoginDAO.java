package br.fortsdev.imobilizado.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.fortsdev.imobilizado.models.Usuario;
import br.fortsdev.imobilizado.services.ServicosBancoDeDados;

public class LoginDAO{

	ServicosBancoDeDados servicosBancoDeDados = new ServicosBancoDeDados();
	
	public Usuario autenticaUsuario(String login, String senha) {
		Usuario usuario = new Usuario();
		
		String query = "SELECT *\r\n" + 
				"\r\n" + 
				"FROM AD_USUAPPINVENTARIO\r\n" + 
				"\r\n" + 
				"WHERE LOGIN = ?";
		Map<Integer, Object> parametros = new HashMap<Integer, Object>();
		parametros.put(1, login);
        
		ArrayList<List<Object>> resultadoQuery = servicosBancoDeDados.consulta(query, parametros, 6);
		
		if (resultadoQuery != null && resultadoQuery.size() > 0) {
				usuario.setLogin(resultadoQuery.get(0).get(3).toString());
				usuario.setSenha(resultadoQuery.get(0).get(4).toString());
				usuario.setAtivo(resultadoQuery.get(0).get(5).toString());
		}
		return usuario;
	}

}
