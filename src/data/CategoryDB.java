package data;

import java.sql.*;
import java.util.ArrayList;

import bean.Category;


public class CategoryDB {
	public static ArrayList<Category> showCategory() 
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM category GROUP By merchant;";
		
		try
		{
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			ArrayList<Category> categories = new ArrayList<Category>();
			while (rs.next())
			{
				Category c = new Category();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setMerchant(rs.getString("merchant"));
				categories.add(c);
			}
			return categories;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}		
      }
	
	public static int changeCategory(String newcat, int tid)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		
		String query = "Update transaction SET category=? WHERE id=?";
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, newcat);
			ps.setInt(2, tid);
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
}