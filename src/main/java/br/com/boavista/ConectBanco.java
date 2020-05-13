package br.com.boavista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLInput;

public class ConectBanco {
	
	private static final String SQL_INSERT = "insert into treinamento(" +
	"id , treinamento , nome, email, area  )" + " VALUES (?,?,?,?,?)";
	
		
	private Connection con = null;
	java.sql.Statement sql = null;
	PreparedStatement sqlInsert = null;
		
	public ConectBanco() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			  con = DriverManager.getConnection("jdbc:sqlite:cadastro.db");
			  
			  sql = con.createStatement();
			  sql.executeUpdate("drop table if exists treinamento");
			  sql.executeUpdate("create table treinamento (id integer, treinamento string, nome string, email string ,area string)");
			  con.prepareStatement(SQL_INSERT);
		} catch (Exception e) {
			System.out.println("Erro ao processar dados" + e.getMessage());
		}
	}

	public void Insert(String b) {

		ModelCliente model = new ModelCliente(b);
		
		try {
			PreparedStatement sqlInsert = con.prepareStatement(SQL_INSERT);
			sqlInsert.setString(1, model.getId());
			sqlInsert.setString(2, model.getTreinamento());
			sqlInsert.setString(3, model.getArea());
			sqlInsert.setString(4, model.getEmail());
			
			sqlInsert.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro ao insert os dados" + e.getMessage());
		}
	}
	
	public void close() {
		try {
			if(con != null) {
			con.close();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao fechar o banco" + e.getMessage());
		} 
	}
}