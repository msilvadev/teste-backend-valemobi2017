package br.com.valemobi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory
{
	/**
	  * Cria conexão com o Banco de Dados.
	  * 
	  * @return            conexão ou exception
	  * @throws 	       SQLException
	  * @author            Matheus Silva
	  */
	public Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/testbackvalemobi", "root", "admin");
		}catch(SQLException e){
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
