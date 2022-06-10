/**
 * 
 */
package sn.boom.apptennis.core.services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.entities.Match;
import sn.boom.apptennis.core.repository.MatchRepositoryImpl;
import sn.boom.apptennis.core.repository.ScoreRepositoryImpl;
import sn.boom.sgi.hibernate.HibernateManager;

/**
 * @author nabyFall
 *
 */ 
public class MatchService {

	private MatchRepositoryImpl matchRepository;
	private ScoreRepositoryImpl scoreRepository;
	
	public MatchService() {
		matchRepository = new MatchRepositoryImpl();
		scoreRepository = new ScoreRepositoryImpl();
	}
	
	public void saveMatch(Match match) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			matchRepository.create(match);
			scoreRepository.create(match.getScore());
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +" | "+ e.getClass());
		} finally {
			if (session != null) session.close();
		}
	}
}
