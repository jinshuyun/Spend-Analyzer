package bean;
import java.io.Serializable;

public class Chartdata implements Serializable{
	  private String date;
	  private double supermarket;
	  private double restaurant;
	  private double gasoline;
	  private double department;
	  private double merchandise;
	  private double service;
	  private double education;
	  private double mail;
	  private double average;
	
	  public Chartdata()
	  {
		  date = "";
		  supermarket = 0;
		  restaurant = 0;
		  gasoline = 0;
		  department = 0;
		  merchandise = 0;
		  service = 0;
		  education = 0;
		  mail = 0;
		  average = 0;
	  }
	  
	  public void setDate(String date)
		{
			this.date = date;
		}
		public String getDate()
		{
			return date;
		}
		public void setSupermarket(double s)
		{
			supermarket = s;
		}
		
		public double getSupermarket()
		{
			return supermarket;
		}
		
		public void setRestaurant(double r)
		{
			restaurant = r;
		}
		
		public double getRestaurant()
		{
			return restaurant;
		}
		
		public void setGasoline(double g)
		{
			gasoline = g;
		}
		
		public double getGasoline()
		{
			return gasoline;
		}
		
		public void setDepartment(double d)
		{
			department = d;
		}
		
		public double getDepartment()
		{
			return department;
		}
		
		public double getMerchandise()
		{
			return merchandise;
		}
		
		public void setMerchandise(double m)
		{
			merchandise = m;
		}
		
		public double getService()
		{
			return service;
		}
		
		public void setService(double ser)
		{
			service = ser;
		}
		
		public double getEducation()
		{
			return education;
		}
		
		public void setEducation(double e)
		{
			education = e;
		}
		public double getMail()
		{
			return mail;
		}
		
		public void setMail(double ma)
		{
			mail = ma;
		}
		public void setAvg(double a)
		{
			average = a;
		}
		
		public double getAvg()
		{
			return average;
		}
}
