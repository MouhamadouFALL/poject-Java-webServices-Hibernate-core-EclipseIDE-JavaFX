/**
 * 
 */
package sn.boom.apptennis.core.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import sn.boom.apptennis.core.entities.Epreuve;
import sn.boom.sgi.hibernate.HibernateManager;

/**
 * @author nabyFall
 *
 */
public class EpreuveRepositoryImpl {
	
	public void create(Epreuve epreuve) {
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		session.persist(epreuve);
	}
	
	public Epreuve getById(long id) {
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		return session.find(Epreuve.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Epreuve> list() {
		
		List<Epreuve> epreuves = new ArrayList<>();
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("From Epreuve");
		epreuves = query.getResultList();
		
		return epreuves;
	}
	
	@SuppressWarnings("unchecked")
	public List<Epreuve> list(String code) {
		
		List<Epreuve> epreuves = new ArrayList<>();
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		// permet de garder la relation en LAZY et de faire une jointure entre le entités pour des raison de performence
		Query query = session.createQuery("From Epreuve e join fetch e.tournoi where e.tournoi.code = ?0"); 
		//Query query = session.createQuery("From Epreuve e where e.tournoi.code = ?0");
		query.setParameter(0, code);
		epreuves = query.getResultList();
		
		return epreuves;
	}
	
	public void update(Epreuve epreuve) {
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		session.update(epreuve);
	}
	
	public void delete(long id) {
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Epreuve epreuve = getById(id);
		if (epreuve != null) session.delete(epreuve);
	}

}
