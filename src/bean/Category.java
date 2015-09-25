package bean;
import java.io.Serializable;

public class Category implements Serializable
{
  private String name;
  private String merchant;
  private int id;
  
  public Category()
  {
	  name = "";
	  merchant = "";
	  id = 0;
  }
  
  public void setId(int cid)
	{
		id = cid;
	}
	public int getId()
	{
		return id;
	}
	public void setName(String n)
	{
		name = n;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setMerchant(String m)
	{
		merchant = m;
	}
	
	public String getMerchant()
	{
		return merchant;
	}
}
