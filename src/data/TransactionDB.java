package data;

import java.sql.*;
import java.util.ArrayList;

import bean.Transaction;
import bean.Chartdata;

public class TransactionDB {
	public static ArrayList<Transaction> findUserTransactions(int uid) 
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM transaction WHERE uid = ? order by date desc";
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			while (rs.next())
			{
				Transaction t = new Transaction();
				t.setId(rs.getInt("id"));
				t.setDate(rs.getString("date"));
				t.setDescription(rs.getString("description"));
				t.setAmount(rs.getDouble("amount"));
				t.setCategory(rs.getString("category"));
				transactions.add(t);
			}
			return transactions;
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
	
	public static ArrayList<Transaction> sumAmount(int uid)//FOR PIE
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT category, sum(amount) AS sum  FROM transaction WHERE uid = ? group by category";
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			while(rs.next())
			{
				Transaction t = new Transaction();
				t.setCategory(rs.getString("category"));
				t.setAmount(rs.getDouble("sum"));
				transactions.add(t);
			}
			return transactions;
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
	
	
   public static ArrayList<Transaction> sumByMonth(int uid) //For COLUMN
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;
		
	   String query = "SELECT sum(amount) AS SUM, DATE_FORMAT(date, '%Y/%m') AS MON FROM transaction WHERE uid = ? group by MON";
	   
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			while(rs.next())
			{
				Transaction t = new Transaction();
				t.setDate(rs.getString("MON"));
				t.setAmount(rs.getDouble("SUM"));
				transactions.add(t);
			}
			return transactions;
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
   
   
  /* public static ArrayList<Transaction> selectOneMonth(int uid)
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;
	
	   //Get the most recent month
	   String query = "SELECT DATE_FORMAT(date, '%Y/%m') AS MON FROM transaction WHERE uid = ? group by MON DESC LIMIT 1"; 
	   
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			
			while(rs.next())
			{
				String mon = rs.getString("MON");
				transactions.addAll(selectCategorySum(uid, mon));
	
				
			}
			return transactions;
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
   */
   public static ArrayList<Transaction> selectMonth(int uid, int num)
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;
	
	   //Get the most recent month
	   String query = "SELECT DATE_FORMAT(date, '%Y/%m') AS MON FROM transaction WHERE uid = ? group by MON DESC LIMIT 3"; 
	   
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			String[] mon = new String[3];
			int i = 0;
			while(rs.next())
			{
				mon[i]= rs.getString("MON");
	            i++;
				
			}
			transactions = selectCategorySum(uid, mon[num-1]);
			return transactions;
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
   
   public static ArrayList<Transaction> selectCategorySum(int uid, String mon) //prepare category, sum columns for the required month
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;
	   
	
	   //Get the most recent month
	   String query = "SELECT category, sum(amount) AS SUM FROM transaction WHERE uid = ? AND DATE_FORMAT(date, '%Y/%m') = ? group by category";
	   
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			ps.setString(2, mon);
			rs = ps.executeQuery();
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			while(rs.next())
			{
					Transaction t = new Transaction();
					t.setCategory(rs.getString("category"));
					t.setAmount(rs.getDouble("SUM"));
					transactions.add(t);
				}
				     
				return transactions;
			
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
   
   
   public static ArrayList<Transaction> displayMonth(int uid, int num)//FOR Paneldata
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;
	
	   //Get the most recent month
	   String query = "SELECT DATE_FORMAT(date, '%Y/%m') AS MON FROM transaction WHERE uid = ? group by MON DESC LIMIT 3"; 
	   
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			String[] mon = new String[3];
			int i = 0;
			while(rs.next())
			{
				mon[i]= rs.getString("MON");
	            i++;
				
			}
			transactions = displayInPanel(uid, mon[num-1]);
			return transactions;
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
   
   
   public static ArrayList<Transaction> displayInPanel(int uid, String mon) //prepare category, sum columns for the required month
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;
	   
	
	   //Get the most recent month
	   String query = "SELECT * FROM transaction WHERE uid = ? AND DATE_FORMAT(date, '%Y/%m') = ? order by date desc";
	   
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			ps.setString(2, mon);
			rs = ps.executeQuery();
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			while(rs.next())
			{
				Transaction t = new Transaction();
				t.setId(rs.getInt("id"));
				t.setDate(rs.getString("date"));
				t.setDescription(rs.getString("description"));
				t.setAmount(rs.getDouble("amount"));
				t.setCategory(rs.getString("category"));
				transactions.add(t);
				}
				     
				return transactions;
			
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
   
   public static ArrayList<Chartdata> diplayByChart(int uid) //prepare category, sum, month columns for the required month
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;	   
	
	   
	   String query = "SELECT DATE_FORMAT(date, '%Y/%m') AS MON FROM transaction WHERE uid = ? group by MON DESC LIMIT 3"; 
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			ArrayList<Chartdata> data = new ArrayList<Chartdata>();
			while(rs.next())
			{
				Chartdata c = new Chartdata();
				String mon = rs.getString("MON");
				c.setDate(mon);
				double s = prepareCategorySum(uid, "Supermarkets", mon);
				c.setSupermarket(s);
				double r = prepareCategorySum(uid, "Restaurants", mon);
				c.setRestaurant(r);
				double g = prepareCategorySum(uid, "Gasoline", mon);
				c.setGasoline(g);
			    data.add(c);
				}	
	
				return data;
			
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
   
   public static ArrayList<Chartdata> diplayByChartStack(int uid) //prepare all category'sum, month columns for the required month
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;	   
	
	   
	   String query = "SELECT DATE_FORMAT(date, '%Y/%m') AS MON FROM transaction WHERE uid = ? group by MON DESC"; 
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			ArrayList<Chartdata> data = new ArrayList<Chartdata>();
			while(rs.next())
			{
				Chartdata c = new Chartdata();
				String mon = rs.getString("MON");
				c.setDate(mon);
				double s = prepareCategorySum(uid, "Supermarkets", mon);
				c.setSupermarket(s);
				double r = prepareCategorySum(uid, "Restaurants", mon);
				c.setRestaurant(r);
				double g = prepareCategorySum(uid, "Gasoline", mon);
				c.setGasoline(g);
				double d = prepareCategorySum(uid, "Department Stores", mon);
				c.setDepartment(d);
				double m = prepareCategorySum(uid, "Merchandise", mon);
				c.setMerchandise(m);
				double ser = prepareCategorySum(uid, "Services", mon);
				c.setService(ser);
				double e = prepareCategorySum(uid, "Education", mon);
				c.setEducation(e);
				double ma = prepareCategorySum(uid, "Mail", mon);
				c.setMail(ma);
			    data.add(c);
				}	
	
				return data;
			
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
   
   public static ArrayList<Chartdata> diplayByCombo(int uid) //prepare category, sum, month columns for the required month
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;	   
	
	   String query = "SELECT DATE_FORMAT(date, '%Y/%m') AS MON FROM transaction WHERE uid = ? group by MON DESC LIMIT 3"; 
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			ArrayList<Chartdata> data = new ArrayList<Chartdata>();
			while(rs.next())
			{
				Chartdata c = new Chartdata();
				String mon = rs.getString("MON");
				c.setDate(mon);
				double s = prepareCategorySum(uid, "Supermarkets", mon);
				c.setSupermarket(s);
				double r = prepareCategorySum(uid, "Restaurants", mon);
				c.setRestaurant(r);
				double g = prepareCategorySum(uid, "Gasoline", mon);
				c.setGasoline(g);
				double a = prepareAverage(uid, mon);
				c.setAvg(a);
			    data.add(c);
				}	
	
				return data;
			
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
   
   public static double prepareCategorySum(int uid, String cat, String mon ) //prepare category, sum, month columns for the required month
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;   
	
	   //Get the category amount separately
	   String query = "SELECT sum(amount) as SUM FROM transaction WHERE uid = ? AND category=? AND DATE_FORMAT(date, '%Y/%m')=?";
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			ps.setString(2, cat);
			ps.setString(3, mon);
			rs = ps.executeQuery();
			double amount = 0;
			while(rs.next())
			{
				amount = rs.getDouble("SUM");
				
			}
				     
				return amount;
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
   
   
   public static double prepareAverage(int uid, String mon ) //prepare average amount for the required month
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;   
	
	   //Get the category amount separately
	   String query = "SELECT sum(amount)/3 as Average FROM transaction WHERE uid = ? AND DATE_FORMAT(date, '%Y/%m')=? AND (category='Gasoline' or category='Supermarkets' or category='Restaurants')";
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			ps.setString(2, mon);
			rs = ps.executeQuery();
			double amount = 0;
			while(rs.next())
			{
				amount = rs.getDouble("Average");
				
			}
				     
				return amount;
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
   
   public static ArrayList<Chartdata> diplayByArea(int uid) //prepare category(supermarket+restaurant+gasoline/all categories), sum, month columns for the required month
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;	   
	
	   String query = "SELECT DATE_FORMAT(date, '%Y/%m') AS MON FROM transaction WHERE uid = ? group by MON DESC LIMIT 3"; 
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			ArrayList<Chartdata> data = new ArrayList<Chartdata>();
			while(rs.next())
			{
				Chartdata c = new Chartdata();
				String mon = rs.getString("MON");
				c.setDate(mon);
				double s = prepareThreeSum(uid, mon);
				c.setSupermarket(s); // use supermarket as three sum method
				double a = prepareMonthSum(uid, mon);
				c.setAvg(a); // use avg as all sum method
			    data.add(c);
				}	
	
				return data;
			
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
   
   public static double prepareThreeSum(int uid, String mon) //prepare average amount for the required month
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;   
	
	   //Get the category amount separately
	   String query = "SELECT Sum(amount) as Sum FROM transaction WHERE uid = ? AND DATE_FORMAT(date, '%Y/%m')=? And category IN ('Supermarkets', 'Restaurants' , 'Gasoline')";
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			ps.setString(2, mon);
			rs = ps.executeQuery();
			double amount = 0;
			while(rs.next())
			{
				amount = rs.getDouble("Sum");
				
			}
				     
				return amount;
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
   
   
   public static double prepareMonthSum(int uid, String mon) //prepare average amount for the required month
   {
	   ConnectionPool pool = ConnectionPool.getInstance();
	   Connection connection = pool.getConnection();
	   PreparedStatement ps = null;
	   ResultSet rs = null;   
	
	   //Get the category amount separately
	   String query = "SELECT Sum(amount) as Sum FROM transaction WHERE uid = ? AND DATE_FORMAT(date, '%Y/%m')=?";
	   try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, uid);
			ps.setString(2, mon);
			rs = ps.executeQuery();
			double amount = 0;
			while(rs.next())
			{
				amount = rs.getDouble("Sum");
				
			}
				     
				return amount;
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
   
   
   public static int deleteOneTransaction(int id)
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
	
		 String query="DELETE FROM transaction WHERE id=?";
		 
		 try
		 {
			 ps = connection.prepareStatement(query);
			 ps.setInt(1, id);
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
   
	 public static int deleteUserTransaction(int uid)
		{
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps = null;
		
			 String query="DELETE FROM transaction WHERE uid=?";
			 
			 try
			 {
				 ps = connection.prepareStatement(query);
				 ps.setInt(1, uid);
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
