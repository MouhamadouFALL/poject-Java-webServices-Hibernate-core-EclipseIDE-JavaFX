/**
 * 
 */
package sn.boom.apptennis.core.repository;

import org.hibernate.Session;

import sn.boom.apptennis.core.entities.Score;
import sn.boom.sgi.hibernate.HibernateManager;

/**
 * @author nabyFall
 *
 */
public class ScoreRepositoryImpl {
	
	public void create(Score score) throws Exception{
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		session.persist(score);
	}
	
	public Score getById(long id) {
		
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		return session.get(Score.class, id);
	}
	
	public void update(Score score) {
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		session.update(score);
	}
	
	public void delete(long id) {
		Session session = HibernateManager.getSessionFactory().getCurrentSession();
		Score score = getById(id);
		if (score != null) session.delete(score);
	}

}
