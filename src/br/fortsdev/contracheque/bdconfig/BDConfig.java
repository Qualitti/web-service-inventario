package br.fortsdev.contracheque.bdconfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConfig {
	private static final String USUARIO = "sankhya";
	private static final String SENHA = "tecsis";
	private static final String PATH = "jdbc:oracle:thin:@192.168.0.246:1521:ORCL";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	public static Connection obtemConexao() throws SQLException{
	    try{
	    	Class.forName(DRIVER);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    return DriverManager.getConnection(PATH, USUARIO, SENHA);
	}    
}
