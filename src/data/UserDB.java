package data;

import java.sql.*;

public class UserDB { // database query
	public static boolean checkUser(String userName, String passWord) //Check user exists
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM user WHERE username = ? AND password = ?";
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, userName); //set value for query
			ps.setString(2, passWord);
			rs = ps.executeQuery();
            return rs.next(); //it shifts the cursor to the next row of the result set from the database and returns true if there is no row, otherwise false.
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
	
	public static boolean checkRegister(String userName) // Check user name exists
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM  user WHERE username = ?";
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			return rs.next();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
	
	public static int insertUser(String userName, String passWord)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		
		String query = "INSERT INTO user (username, password) VALUES (?, ?)";
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, passWord);
			return ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
		finally
		{
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		
	}
	
	public static int selectUserId(String userName)// Check whether the user is new (with transaction record or not)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int uid = 0;
		
		String query = "SELECT * FROM user WHERE username = ?";
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			rs.next();
			uid = rs.getInt("uid");
			return uid;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
		finally
		{
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
}
