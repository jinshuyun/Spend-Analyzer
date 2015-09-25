package bean;

import java.io.Serializable;

public class User implements Serializable 
{
	private String userName;
	private String passWord;
	private int uid;
	
	public User()
	{
		userName = "";
		passWord = "";
		uid = 0;
	}
	
	public void setUid(int id)
	{
		uid = id;
	}
	public int getUid()
	{
		return uid;
	}
	public void setUserName(String n)
	{
		userName = n;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setPassWord(String p)
	{
		passWord = p;
	}
	
	public String getPassWord()
	{
		return passWord;
	}
}

