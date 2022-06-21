/**
 * 
 */
package sn.boom.apptennis.core.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sn.boom.apptennis.core.entities.Epreuve;
import sn.boom.sgi.jpa.EntityManagerHolder;

/**
 * @author nabyFall
 *
 */
public class EpreuveRepositoryImpl {
	
	public void create(Epreuve epreuve) {
		//Session session = HibernateManager.getSessionFactory().getCurrentSession();
		//session.persist(epreuve);
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		em.persist(epreuve);
		
	}
	
	public Epreuve getById(long id) {
		
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		return em.find(Epreuve.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Epreuve> list() {
		
		List<Epreuve> epreuves = new ArrayList<>();
		
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		Query query = em.createQuery("From Epreuve");
		epreuves = query.getResultList();
		
		return epreuves;
	}
	
	@SuppressWarnings("unchecked")
	public List<Epreuve> list(String code) {
		
		List<Epreuve> epreuves = new ArrayList<>();
		
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		// permet de garder la relation en LAZY et de faire une jointure entre le entités pour des raison de performence
		Query query = em.createQuery("From Epreuve e join fetch e.tournoi where e.tournoi.code = ?0"); 
		//Query query = session.createQuery("From Epreuve e where e.tournoi.code = ?0");
		query.setParameter(0, code);
		epreuves = query.getResultList();
		
		return epreuves;
	}
	
	public void update(Epreuve epreuve) {
		
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		em.merge(epreuve);
	}
	
	public void delete(long id) {
		
		EntityManager em = EntityManagerHolder.getCurrentEntityManager();
		Epreuve epreuve = getById(id);
		if (epreuve != null) em.remove(epreuve);
	}

}
