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

}
