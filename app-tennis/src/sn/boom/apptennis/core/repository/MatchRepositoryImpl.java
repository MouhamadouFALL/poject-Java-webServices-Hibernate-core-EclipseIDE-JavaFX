/**
 * 
 */
package sn.boom.apptennis.core.repository;

import org.hibernate.Session;

import sn.boom.apptennis.core.entities.Match;
import sn.boom.sgi.hibernate.HibernateManager;


/**
 * @author nabyFall
 *
 */
public class MatchRepositoryImpl {
	
	public void create(Match match) throws Exception{
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		session.persist(match);
	}
	
	public Match getById(long id) {
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		return session.get(Match.class, id);
	}
	
	public void update(Match match) {
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		session.update(match);
	}
	
	public void delete(long id) {
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Match match = getById(id);
		if (match != null) session.delete(match);
	}
}
