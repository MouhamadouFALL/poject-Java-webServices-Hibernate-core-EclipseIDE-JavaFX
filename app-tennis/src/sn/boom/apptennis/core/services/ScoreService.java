/**
 * 
 */
package sn.boom.apptennis.core.services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.repository.ScoreRepositoryImpl;
import sn.boom.sgi.hibernate.HibernateManager;

/**
 * @author nabyFall
 *
 */ 
public class ScoreService {

	private ScoreRepositoryImpl scoreRepository;
	
	public ScoreService() {
		scoreRepository = new ScoreRepositoryImpl();
	}
	
	
	public void delete(long id) {
		
		Session session = null;
		Transaction tx = null;
		try {
			
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			scoreRepository.delete(id); 
			
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +" | "+ e.getClass());
		} finally {
			if (session != null) session.close();
		}
	}
	
}
