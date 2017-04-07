package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IMapResultSetToEntity<User> {
	
	public User map(ResultSet rs) throws SQLException;

}
