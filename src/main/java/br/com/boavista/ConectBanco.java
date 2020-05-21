package br.com.boavista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLInput;

public class ConectBanco {
	
	private static final String SQL_INSERT = "insert into treinamento VALUES (?,?,?,?,?)";
	
		
	private Connection con = null;
	java.sql.Statement sql = null;
	PreparedStatement sqlInsert = null;
		
	public ConectBanco() {
		
		try {
//			Class.forName("org.sqlite.JDBC");
			Class.forName("org.mariadb.jdbc.Driver");
//			  con = DriverManager.getConnection("jdbc:sqlite:cadastro.db");
			  con =  DriverManager.getConnection("jdbc:mariadb://localhost:3306/mydb?user=ubuntu&password=ubuntu");
			  
			  sql = con.createStatement();
//			  sql.executeUpdate("drop table if exists treinamento");
//			  sql.executeUpdate("create table treinamento (id integer, treinamento string, nome string, email string ,area string)");
			  con.prepareStatement(SQL_INSERT);
		} catch (Exception e) {
			System.out.println("Erro ao processar dados" + e.getMessage());
		}
	}

	public void Insert(String b) {

		ModelCliente model = new ModelCliente(b);
		
		try {
			PreparedStatement sqlInsert = con.prepareStatement(SQL_INSERT);
			sqlInsert.setInt(1, model.getId());
			sqlInsert.setString(2, model.getTreinamento());
			sqlInsert.setString(3,model.getNome());
			sqlInsert.setString(4, model.getEmail());
			sqlInsert.setString(5, model.getArea());
			
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