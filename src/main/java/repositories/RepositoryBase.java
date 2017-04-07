package repositories;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Entity;
import domain.User;
import mappers.IMapResultSetToEntity;

public abstract class RepositoryBase<TEntity>  {

	protected Connection connection;
	protected Statement createTable;
	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement get;
	protected PreparedStatement list;
	protected IMapResultSetToEntity<TEntity> mapper;
	private Connection on;

	public RepositoryBase(Connection connection,
			IMapResultSetToEntity<TEntity> mapper) {

		try {
			this.mapper = mapper;
			this.connection = connection;
			createTable = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if (tableName().equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			if (!tableExists)
				createTable.executeUpdate(createTableSql());

			insert = connection.prepareStatement(insertSql());
			delete = connection.prepareStatement(deleteSql());
			update = connection.prepareStatement(updateSql());
			get = connection.prepareStatement(getSql());
			list = connection.prepareStatement(listSql());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(User u) {
		try {
			delete.setInt(1, u.getId());
			delete.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(User p) {
		try {
			setUpdateQuery(p);
			update.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void add(User p) {
		try {
			setInsertQuery(p);
			insert.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<User> getAll() {
		List<User> users = new ArrayList<User>();

		try {
			//connection = null;
			Statement statement =connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from user");

			while (rs.next()) {
				//users.add((User) mapper.map(rs));
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setType(rs.getString("type"));
				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public User get(String Login) {

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from user where login like '"+ Login+"'");
			rs.next();
			User user = new User();
			user.setLogin(rs.getString("login"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setType(rs.getString("type"));
			return  user;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TEntity withLogin(String Login){
		
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("update user set type = 'Premium' where login like '"+Login+"'");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
public TEntity withLoginDown(String Login){
		
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("update user set type = 'Normal' where login like '"+Login+"'");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public TEntity getPass(String login){
		
		try{
			Statement statement =connection.createStatement();
			ResultSet rs = statement.executeQuery("select password from user where login like "+ login);
			return mapper.map(rs);
		}
			catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
		
	}
	

	protected String deleteSql() {
		return "DELETE FROM " + tableName() + " WHERE id=?";
	}

	protected String getSql() {
		return "SELECT * FROM " + tableName() + " WHERE id = ?";
	}

	protected String listSql() {
		return "SELECT * FROM " + tableName();
	}

	protected abstract void setUpdateQuery(User p) throws SQLException;

	protected abstract void setInsertQuery(User p) throws SQLException;

	protected abstract String tableName();

	protected abstract String createTableSql();

	protected abstract String insertSql();

	protected abstract String updateSql();
}
