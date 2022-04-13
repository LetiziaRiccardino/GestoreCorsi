package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	public List<Corso> getCorsoByPeriodo(int periodo){
		//ho bisogno della stringa che contenga la query. devo togliere gli \n e se ho la query su più righe devo aggiungere 
		//uno spazio al fondo
		String sql= "select *"  //pimporto la query da heidi facendo qualche piccola modifica
		            +"from corso "
				    +"where d = ? ";
		List<Corso> result= new ArrayList<Corso>();
		
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			
			st.setInt(1, periodo);//qua l'indicizzazione parte da 1 e non da 0 come per gli array
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				Corso c =new Corso(rs.getString("codins"), rs.getInt("crediti"),
						rs.getString("nome"), rs.getInt("pd"));//devo specificare il nome della colonna nel database tra ""
				result.add(c);
			}
			conn.close();//chiudo la connessione prima di ritornare result
			rs.close();
			st.close();
			
			return result;
			
			
			
		}catch(SQLException e){
			System.out.println("Errore sul DAO");
			e.printStackTrace();
			return null;
			
		}
	}
	
	
	public Map<Corso, Integer> getIscritti(int periodo){
			String sql="SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS n "
					+ "FROM corso c, iscrizione  "
					+ "WHERE c.codins= i.codins AND c.pd=1 "
					+ "GROUP BY c.codins, c.crediti, c.nome, c.pd "
					+ ""; //modifica la query
			Map<Corso,Integer> result= new HashMap<Corso,Integer>();
			
			try {
				Connection conn= ConnectDB.getConnection();
				PreparedStatement st=conn.prepareStatement(sql);
				st.setInt(1, periodo);
				ResultSet rs= st.executeQuery();
				while(rs.next()) {
					result.put(new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")), rs.getInt("n"));
				}
				conn.close();//chiudo la connessione prima di ritornare result
				rs.close();
				st.close();
				return result;
				
			}catch(SQLException e){
				System.out.println("Errore sul DAO");
				e.printStackTrace();
				return null;
			}
			
			
		}
}
