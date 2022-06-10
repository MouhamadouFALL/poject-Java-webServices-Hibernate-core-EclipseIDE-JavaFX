/**
 * 
 */
package sn.boom.apptennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import org.hibernate.Session;

import sn.boom.apptennis.core.entities.Score;
import sn.boom.sgi.jdbc.DBManagerBDS;

/**
 * @author nabyFall
 *
 */
public class ScoreRepositoryImpl {
	
	public void create(Score score) throws Exception {
		
		Session session = null;
		try{
			
			
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
