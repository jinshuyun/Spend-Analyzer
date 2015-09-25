package data;

import java.sql.*;


public class RecordDB {
	public static int insertRecordWC(String filename)//format date and insert record
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
	
		 String query="LOAD DATA INFILE '"+filename+"' INTO TABLE record FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' IGNORE 1 LINES (@var1,@var2,Description,Amount,Category) SET TransDate=STR_TO_DATE(@var1, '%m/%d/%Y'), PostDate=STR_TO_DATE(@var2, '%m/%d/%Y')";
		 
		 try
		 {
			 ps = connection.prepareStatement(query);
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
	public static int insertRecordWOC(String filename)//format date and insert record
	{
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
	
		 String query="LOAD DATA INFILE '"+filename+"' INTO TABLE recordwoc FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\n' (@var,Description,Amount) SET TransDate=STR_TO_DATE(SUBSTRING(@var, 1, 10),'%m/%d/%Y')";
		 
		 try
		 {
			 ps = connection.prepareStatement(query);
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
	
	 public static int toTransaction(int uid) // transfer record to transaction table, add uid into transaction table
	 {
		    ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps1 = null;
			PreparedStatement ps = null;
			String query1="DELETE FROM record WHERE Amount<0";
			try {
				ps1 = connection.prepareStatement(query1);
				ps1.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			String query="INSERT INTO transaction(date, description, amount, category, uid)"
			 		+ "    SELECT TransDate, Description, Amount, Category, ? FROM record";
			 
			 try
			 {
				 ps = connection.prepareStatement(query);
				 ps.setInt(1,  uid);
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
	 
     public static int toTransactionwoc(int uid)
     {
		    ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			ResultSet rs1 = null;
			String query1 = "SELECT * FROM recordwoc";
			ResultSet rs2 = null;
			String query2 = "SELECT * FROM category";
			
			try {
				ps1 = connection.prepareStatement(query1);
				rs1 = ps1.executeQuery();
				while(rs1.next())
				{
					String desc = rs1.getString("Description");
					Double amount = rs1.getDouble("Amount");
					ps2 = connection.prepareStatement(query2);
					rs2 = ps2.executeQuery();
					int i = 1;
					while(rs2.next())
					{
						String name = rs2.getString("name");
						if ((desc.toLowerCase().contains(name.toLowerCase()))&&(amount>0))//check substring, if found insert into transaction
						{
							String merchant = rs2.getString("merchant");
							RecordDB.addTransaction(merchant, uid, desc, amount);
						    break;
						}
						if ((i == 23)&&(amount>0)) //23 rows records in category, if no found, insert null as merchant into transaction
						{
							String merchant = "null";
							RecordDB.addTransaction(merchant, uid, desc, amount);
						}
						i++;
	
					}
		
				
				}
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}			
     }
	 
     public static int addTransaction(String name, int uid, String desc, Double amount)
 	{
 		ConnectionPool pool = ConnectionPool.getInstance();
 		Connection connection = pool.getConnection();
 		PreparedStatement ps = null;
 		
 		 String query = "INSERT INTO transaction(date, description, amount, category, uid)"
 		 		+ "    SELECT TransDate, Description, Amount, ?, ? FROM recordwoc WHERE Description=? AND Amount=?";
 		 
 		 try
 		 {
 			 ps = connection.prepareStatement(query);
 			 ps.setString(1,  name);
 			 ps.setInt(2,  uid);
 			 ps.setString(3, desc);
 			 ps.setDouble(4, amount);
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
	 public static int deleteRecord()
		{
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps = null;
		
			 String query="DELETE FROM record";
			 
			 try
			 {
				 ps = connection.prepareStatement(query);
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
	 public static int deleteRecordwoc()
		{
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps = null;
		
			 String query="DELETE FROM recordwoc";
			 
			 try
			 {
				 ps = connection.prepareStatement(query);
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
