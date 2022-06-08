/**
 * 
 */
package sn.boom.apptennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sn.boom.apptennis.core.entities.Match;
import sn.boom.sgi.jdbc.DBManagerBDS;

/**
 * @author nabyFall
 *
 */
public class MatchRepositoryImpl {
	
	public void create(Match match) throws Exception{
		
		try(Connection connection = DBManagerBDS.getConnection()){
			
			String query = "Insert Into match_tennis(id_epreuve, id_vainqueur, id_finaliste) values(?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, match.getEpreuve().getId());
			preparedStatement.setLong(2, match.getVainqueur().getId());
			preparedStatement.setLong(3, match.getFinaliste().getId());
			
			preparedStatement.execute();
			ResultSet rsKey = preparedStatement.getGeneratedKeys();
			if(rsKey.next()) {
				match.setId(rsKey.getLong(1));
			}
			
			connection.close();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
