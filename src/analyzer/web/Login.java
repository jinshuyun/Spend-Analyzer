package analyzer.web;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Category;
import bean.Transaction;
import data.CategoryDB;
import data.UserDB;
import data.TransactionDB;

public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String n = req.getParameter("username");
		String p = req.getParameter("password");
		
		String message = "";
        String url = "";
		
		if (UserDB.checkUser(n, p))//check user exists
		{ 
			HttpSession session = req.getSession();
			int uid = UserDB.selectUserId(n);
			ArrayList<Transaction> transactions = TransactionDB.findUserTransactions(uid);
			session.setAttribute("transactions", transactions);
			session.setAttribute("uid", uid);
			ArrayList<Category> categories = CategoryDB.showCategory();
			session.setAttribute("categories", categories);
			url = "result.jsp";
		 }

		else 
		{
			message = "Your username or password is incorrect.";
			url = "login.jsp";
		}
		req.setAttribute("message", message);
		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req, resp);
	}

}