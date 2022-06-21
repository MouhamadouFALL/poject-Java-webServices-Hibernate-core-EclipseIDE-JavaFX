package sn.boom.apptennis.core.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import sn.boom.apptennis.core.entities.Tournoi;
import sn.boom.sgi.hibernate.HibernateManager;
import sn.boom.sgi.jpa.EntityManagerHolder;

public class TournoiRepositoryImpl {
	
	public void create(Tournoi obj) {
		
		//Session session = HibernateManager.getSessionFactory().getCurrentSession();
		//session.persist(obj);
		
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		em.persist(obj);
	}
	
	public Tournoi getById(long id) {
		
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		Tournoi tournoi = em.find(Tournoi.class, id);
		return tournoi;
	}
	
	public void update(Tournoi obj) {
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Tournoi tournoi = getById(obj.getId());
		if (tournoi != null) {
			tournoi.setNom(obj.getNom());
			tournoi.setCode(obj.getCode());
		}
		
		session.merge(tournoi);
	}
	
	public void delete(long id) {
		
		//Session session = HibernateManager.getSessionFactory().getCurrentSession();
		//Tournoi tournoi = getById(id);
		//if (tournoi != null) session.delete(tournoi);
		
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		Tournoi tournoi = em.find(Tournoi.class, id);
		if (tournoi != null) em.remove(tournoi);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tournoi> list(){
		List<Tournoi> tournois = new ArrayList<>();
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("From Tournoi");
		tournois = query.getResultList();
		
		return tournois;
	}

}
