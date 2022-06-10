package sn.boom.apptennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.sgi.hibernate.HibernateManager;
import sn.boom.sgi.jdbc.DBManagerBDS;

public class JoueurRepositoryImpl {

	public void create(Joueur joueur) throws Exception {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.persist(joueur);
			
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			throw new Exception(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
	}
	
	
	public void update(Joueur joueur) throws Exception {
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
			
		session.update(joueur);
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
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		
		Joueur joueur = session.get(Joueur.class, id);
		
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
