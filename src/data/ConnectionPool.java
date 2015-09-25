package data;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;

public class ConnectionPool {
	 private static ConnectionPool pool = null;
	 private static DataSource dataSource = null;
	 
	 private ConnectionPool() //create an instance of the connection pool
	 {
		 try
		 {
			 InitialContext ic = new InitialContext();
			 dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/spend"); //setting database, name is the same as "resource name" in website.xml
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
	 }

	 public static ConnectionPool getInstance() //check to see if the connection pool object exists
	 {
		 if (pool == null)
			 pool = new ConnectionPool();
		 return pool; 
	 }
	 // all above is to create a connection pool
	 public Connection getConnection()
	 {
		 try
		 {
			 return dataSource.getConnection(); //access the database
		 }
		 catch (SQLException sqle)
		 {
			 sqle.printStackTrace();
			 return null;
		 }
	 }
	 
	 public void freeConnection (Connection c) //close the specified connection object
	 {
		 try
		 {
			 c.close();
		 }
		 catch (SQLException sqle)
		 {
			 sqle.printStackTrace();
		 }
	 }
}
