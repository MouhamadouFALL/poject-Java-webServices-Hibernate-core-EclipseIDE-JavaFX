/**
 * 
 */
package sn.boom.apptennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

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
			if (score.getSet3() == null)
				preparedStatement.setNull(4, Types.TINYINT);
			else
				preparedStatement.setByte(4, score.getSet3());
			if (score.getSet4() == null)
				preparedStatement.setNull(5, Types.TINYINT);
			else
				preparedStatement.setByte(5, score.getSet4());
			if (score.getSet5() == null)
				preparedStatement.setNull(6, Types.TINYINT);
			else
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
