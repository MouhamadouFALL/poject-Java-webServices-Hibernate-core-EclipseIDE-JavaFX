package sn.boom.apptennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.sgi.jdbc.DBManagerBDS;

public class JoueurRepositoryImpl {

	public void create(Joueur joueur) throws Exception {
		
		try(Connection connection = DBManagerBDS.getConnection()) {
			
			String query = "Insert Into Joueur(nom, prenom, sexe) Values(?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, joueur.getNom());
			preparedStatement.setString(2, joueur.getPrenom());
			preparedStatement.setString(3, joueur.getSexe().toString());
			
			preparedStatement.execute();
			ResultSet rskey = preparedStatement.getGeneratedKeys();
			
			if (rskey.next()) {
				joueur.setId(rskey.getLong(1));
			}
			
			connection.close();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void update(Joueur joueur) throws Exception {
		
		try(Connection connection = DBManagerBDS.getConnection()){
			
			String query = "Update Joueur set nom=?, prenom=?, sexe=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, joueur.getNom());
			preparedStatement.setString(2, joueur.getPrenom());
			preparedStatement.setString(3, joueur.getSexe().toString());
			preparedStatement.setLong(4, joueur.getId());
			
			preparedStatement.execute();
			
			connection.close();
			
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void delete(long id) throws Exception {
		try(Connection connection = DBManagerBDS.getConnection()){
			
			String query = "Delete From joueur Where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			
			preparedStatement.execute();
			
			connection.close();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Joueur getById(long id) throws Exception {
		
		Joueur joueur = null;
		
		try(Connection connection = DBManagerBDS.getConnection()){
			
			String query = "Select id, nom, prenom, sexe From joueur Where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				joueur = new Joueur();
				joueur.setId(id);
				joueur.setNom(result.getString("nom"));
				joueur.setPrenom(result.getString("prenom"));
				joueur.setSexe(result.getString("sexe").charAt(0));
			}
			
			connection.close();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return joueur;
	}
	
	public List<Joueur> list() throws Exception {
		
		List<Joueur> joueurs = new ArrayList<>();
		
		try(Connection connection = DBManagerBDS.getConnection()){
			
			String query = "Select * From joueur";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Joueur joueur = new Joueur();
				joueur.setId(result.getLong("id"));
				joueur.setNom(result.getString("nom"));
				joueur.setPrenom(result.getString("prenom"));
				joueur.setSexe(result.getString("sexe").charAt(0));
				
				joueurs.add(joueur);
			}
			
			connection.close();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return joueurs;
	}
	
}
