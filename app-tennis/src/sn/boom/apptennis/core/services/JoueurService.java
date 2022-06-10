package sn.boom.apptennis.core.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.apptennis.core.repository.JoueurRepositoryImpl;
import sn.boom.sgi.hibernate.HibernateManager;

public class JoueurService {
	
	private JoueurRepositoryImpl joueurRepository;
	
	public JoueurService() {
		this.joueurRepository = new JoueurRepositoryImpl();
	}

	public void createJoueur(Joueur joueur) {
		Session session = null;
		Transaction tx = null;
		try {
			
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			joueurRepository.create(joueur);
			
			tx.commit();
			
			System.out.println("Joueur "+ joueur.getId() +" a �t� bien sauvegarder!");
		} 
		catch (Exception e) {
			
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
	}
	
	public Joueur getJoueur(long id) {
		
		Joueur joueur = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			joueur = joueurRepository.getById(id);
			
			tx.commit();
		}
		catch (Exception e) {
			
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
		
		return joueur;
	}
	
	public void updateJoueur(Joueur obj) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Joueur joueur = joueurRepository.getById(obj.getId());
			if (joueur != null) {
				joueur.setNom(obj.getNom());
				joueur.setPrenom(obj.getPrenom());
				joueur.setSexe(obj.getSexe());
			}
			
			tx.commit();
		}
		catch (Exception e) {
			
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
	}
	
	public void deleteJoueur(long id) {
		
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			joueurRepository.delete(id);
			
			tx.commit();
		}
		catch (Exception e) {
			
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
	}
	
	public List<Joueur> listeJoueurs(){
		
		List<Joueur> joueurs = null;
		Session session = null;
		Transaction tx= null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			joueurs = joueurRepository.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if(session != null) session.close();
		}
		
		return joueurs;
	}
}
