package sn.boom.apptennis.core.services;

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
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			joueurRepository.create(joueur);
			System.out.println("Joueur "+ joueur.getId() +" a été bien sauvegarder!");
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
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
	
	public void updateJoueur(long id, String nom) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Joueur joueur = joueurRepository.getById(id);
			if (joueur != null) {
				joueur.setNom(nom);
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
}
