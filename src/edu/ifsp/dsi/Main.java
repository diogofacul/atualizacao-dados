package edu.ifsp.dsi;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Main {
	
	public void executar() {		
		try (Connection conn = ConnectionProvider.getConnection()) {

			Statement st = conn.createStatement();

			/*
			 * Tarefa 1
			 * Implemente a inserção de duas novas contas, usando `Statement`s simples.
			 * 
			 * Antes, você vai precisar cadastrar pessoas, caso sua tabela `pessoa` esteja
			 * vazia. Use o seu cliente MySQL para fazer isso.
			 */
						
			st.execute("insert into pessoa(id, nome) values (null, 'Roberto');");
			st.execute("insert into conta(numero, saldo, titular) values (1, 2.75, 1);");
			st.execute("insert into conta(numero, saldo, titular) values (2, -17923728, 1);");
			st.execute("insert into conta(numero, saldo, titular) values (3, 1123.56, 1);");
			
			/* 
			 * Tarefa 2
			 * Usando `PreparedStatement`, registre uma sequência de movimentações, da 
			 * primeira para a segunda conta. 
			 * 
			 * Use os valores contidos no vetor a seguir.
			 * Não é necessário atualizar o saldo. Apenas registre as movimentações.  
			 */
			double[] valores = {50, 1200, -300, 100, -73, -41, 80, 15, -20};

			PreparedStatement pstm = conn.prepareStatement("insert into movimentacao(id, origem, destino, valor) values(null, 1, 3, ?);");

			for(int i = 0; i < valores.length; i++){
				pstm.setDouble(1, valores[i]);
				pstm.executeUpdate();
			}

		} catch (SQLException e) {
			System.err.println("Problemas com o banco de dados.");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Main().executar();
	}
}
