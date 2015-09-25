package bean;

import java.io.Serializable;

public class Transaction implements Serializable 
{
	private String date;
	private String description;
	private double amount;
	private String category;
	private int uid;
	private int id;

	public Transaction() {
		id = 0;
		date = "";
		description = "";
		amount = 0;
		category = "";
		uid = 0;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
    
	public String getDate()
	{
		return date;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	
	public double getAmount()
	{
		return amount;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setUid(int uid)
	{
		this.uid = uid;
	}
	public int getUid()
	{
		return uid;
	}
}


