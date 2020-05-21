package br.com.boavista;

import lombok.Data;

@Data
public class ModelCliente {

	private int id;
	private String treinamento;
	private String nome;
	private String email ="";
	private String area = "";

	public ModelCliente(String l) {
		
		String[] linha = l.split(";");
		
		id = Integer.parseInt(linha[0]);
		treinamento = linha[1];
		nome = linha[2];
		email = linha[3];
		area = linha[4];		
	}
}
