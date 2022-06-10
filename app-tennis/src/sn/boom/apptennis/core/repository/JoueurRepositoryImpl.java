package sn.boom.apptennis.core.repository;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.sgi.hibernate.HibernateManager;

public class JoueurRepositoryImpl {

	public void create(Joueur joueur) {
		
		Session session = HibernateManager.getSessionFactory().openSession();
		session.persist(joueur);
	}
	
	
	public void update(Joueur joueur) {
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		session.merge(joueur);	
		//session.update(joueur);
	}
	
	public Joueur getById(long id) {
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Joueur joueur = session.get(Joueur.class, id);
		
		return joueur;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Joueur> list(){
		
		List<Joueur> joueurs = new ArrayList<>();
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("From Joueur");
		joueurs = query.getResultList();
		
		return joueurs;
	}
	
	public void delete(long id) {
		
		Joueur joueur = getById(id);
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		session.delete(joueur);	
	}
	
}
