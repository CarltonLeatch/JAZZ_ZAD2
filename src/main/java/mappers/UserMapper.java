package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class UserMapper implements IMapResultSetToEntity<User>{

	@Override
	public User map(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		User u = new User();
		u.setLogin(rs.getString("login"));
		u.setPassword(rs.getString("password"));
		u.setEmail(rs.getString("email"));
		return u;
	}

}
