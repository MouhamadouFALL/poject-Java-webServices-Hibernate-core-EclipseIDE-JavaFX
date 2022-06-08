/**
 * 
 */
package sn.boom.apptennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sn.boom.apptennis.core.entities.Score;
import sn.boom.sgi.jdbc.DBManagerBDS;

/**
 * @author nabyFall
 *
 */
public class ScoreRepositoryImpl {
	
	public void create(Score score) throws Exception {
		
		try(Connection connection = DBManagerBDS.getConnection()){
			
			String query = "Insert Into score_vainqueur(id_match, set_1, set_2, set_3, set_4, set_5)"+
			" values(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, score.getMatch().getId());
			preparedStatement.setByte(2, score.getSet1());
			preparedStatement.setByte(3, score.getSet2());
			preparedStatement.setByte(4, score.getSet3());
			preparedStatement.setByte(5, score.getSet4());
			preparedStatement.setByte(6, score.getSet5());
			
			preparedStatement.execute();
			ResultSet rsKey = preparedStatement.getGeneratedKeys();
			if(rsKey.next()) {
				score.setId(rsKey.getLong(1));
			}
			
			connection.close();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
