package analyzer.web;

import java.io.IOException;




import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Transaction;

import java.lang.Integer;
import java.util.ArrayList;

import data.CategoryDB;
import data.TransactionDB;

public class Recategory extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			String id= req.getParameter("id");
			String remove = req.getParameter("Remove");
			String add = req.getParameter("Add");
			String newcat1 = req.getParameter("newcat1");
			String newcat2 = req.getParameter("newcat2");
			String ca = "";
			HttpSession session = req.getSession();
			int uid = (int) session.getAttribute("uid");
			int tid = Integer.parseInt(id);
			if ("Save Change".equals(add))
			{
				if(newcat2.equals(""))
				{
					ca = newcat1;
				}
				else
					ca = newcat2;
	           
				
	
				if (CategoryDB.changeCategory(ca, tid)!=0)
				{
	
					ArrayList<Transaction> transactions = TransactionDB.findUserTransactions(uid);
					session.setAttribute("transactions", transactions);
				}
			}	
			if ("Delete".equals(remove))
		    {
		    	if (TransactionDB.deleteOneTransaction(tid)!=0)
		    	{
		    		ArrayList<Transaction> transactions = TransactionDB.findUserTransactions(uid);
					session.setAttribute("transactions", transactions);
		    	}
		    }
			String url = "table.jsp";
			RequestDispatcher view = req.getRequestDispatcher(url);
			view.forward(req, resp);
			
		    
	}


}
