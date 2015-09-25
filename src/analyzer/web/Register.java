package analyzer.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserDB;

//check whether username has existed
//check whether password input correctly in two times

public class Register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("username");
		String pw1 = req.getParameter("password1");
		String pw2 = req.getParameter("password2");
		
		String message = "";
		String url = "";
		
		if(UserDB.checkRegister(name))
		{
			message = "This username has existed.";
			url = "register.jsp";
		}
		else
		{
			if((pw1.equals(pw2))&& (pw1.length()>5))
			{
				message = "Register sucessfully!";
				url = "login.jsp";
				UserDB.insertUser(name, pw1);
			}
			else
			{
				message = "Password input incorrect!";
				url = "register.jsp";
			}
		}
		req.setAttribute("message", message);
		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req, resp);

	}
	
	

}
