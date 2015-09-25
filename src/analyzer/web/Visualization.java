package analyzer.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser; 



import org.codehaus.jackson.map.ObjectMapper;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;




import com.google.visualization.datasource.render.JsonRenderer;

import bean.Chartdata;
import bean.Transaction;
import data.TransactionDB;



public class Visualization extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = "";
        String delete = req.getParameter("yes");
        String keep = req.getParameter("no");
/*        String chart = req.getParameter("chart");
        String submit = req.getParameter("submit");*/
		HttpSession session = req.getSession();
		int uid = (int) session.getAttribute("uid");
		
		if ("Yes".equals(delete))
		{
		    TransactionDB.deleteUserTransaction(uid);
			session.invalidate();
		    resp.sendRedirect("login");
		}
		else if ("No".equals(keep))
		{
			session.invalidate();
		    resp.sendRedirect("login");
		}
		else 
		{     		
					//SUM AMOUNT FOR each type of category
					ArrayList<Transaction> transactions = TransactionDB.sumAmount(uid);
					if (transactions != null)
					{
					    
						DataTable data = new DataTable();
					   
					    data.addColumn(new ColumnDescription("0", ValueType.TEXT, "Category"));
					    data.addColumn(new ColumnDescription("1", ValueType.NUMBER, "Amount"));
					   
			
					    // Fill the data table.
					    for (int i=0; transactions.size()>i; i++)
					    {
					      			try {
										data.addRowFromValues(transactions.get(i).getCategory(), transactions.get(i).getAmount());
									} catch (TypeMismatchException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
			
			
					    }
					    JsonNode root = null;
					    String json = JsonRenderer.renderDataTable(data, true, false).toString();
			
					            try{
					                JsonParser parser = new JsonFactory().createJsonParser(json)
					                    .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
					                    .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
					                     root = new ObjectMapper().readTree(parser);
					                }catch(Exception e){
					                	e.printStackTrace();
					                }
			
					            req.setAttribute("data",root.toString());
					}
					
					//Spending History
					ArrayList<Transaction> transactions2 = TransactionDB.sumByMonth(uid);
					if (transactions2 != null)
					{
						
						
						DataTable data2 = new DataTable();
					   
					    data2.addColumn(new ColumnDescription("0", ValueType.TEXT, "Months"));
					    data2.addColumn(new ColumnDescription("1", ValueType.NUMBER, "Amount"));
					   
			
					    // Fill the data table.
					    for (int i=0; transactions2.size()>i; i++)
					    {
					      			try {
										data2.addRowFromValues(transactions2.get(i).getDate(), transactions2.get(i).getAmount());
									} catch (TypeMismatchException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
			
			
					    }
					    JsonNode root2 = null;
					    String json2 = JsonRenderer.renderDataTable(data2, true, false).toString(); //Transaction data to json format
			
					            try{
					                JsonParser parser2 = new JsonFactory().createJsonParser(json2)
					                    .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
					                    .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
					                     root2 = new ObjectMapper().readTree(parser2);
					                }catch(Exception e){
					                	e.printStackTrace();
					                }
					            session.setAttribute("data2", root2.toString());
					            
					}
					
					ArrayList<Transaction> paneldata = TransactionDB.findUserTransactions(uid);
					req.setAttribute("paneldata", paneldata);
					url = "last";
					RequestDispatcher view = req.getRequestDispatcher(url);
					view.forward(req, resp);
					}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		int uid = (int) session.getAttribute("uid");
		
		String url ="";
		String month = req.getParameter("mon");

		if (month != null)
		{
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			ArrayList<Transaction> paneldata = TransactionDB.findUserTransactions(uid);
			if (month.equals("0")) 
			{  
				transactions = TransactionDB.sumAmount(uid); 
				paneldata = TransactionDB.findUserTransactions(uid);
				req.setAttribute("paneldata", paneldata);
				url = "chart.jsp?mon=0";
			 }
			if (month.equals("1"))
			{  
				transactions = TransactionDB.selectMonth(uid, 1);
				paneldata = TransactionDB.displayMonth(uid, 1);
				req.setAttribute("paneldata", paneldata);
				url = "chart.jsp?mon=1";
			 }
			if (month.equals("2"))
			{   
			    transactions = TransactionDB.selectMonth(uid, 2);
			    paneldata = TransactionDB.displayMonth(uid, 2);
			    req.setAttribute("paneldata", paneldata);
			    url = "chart.jsp?mon=2";
		     }
			if (month.equals("3"))
			{  
			    transactions = TransactionDB.selectMonth(uid, 3);
			    paneldata = TransactionDB.displayMonth(uid, 3);
			    req.setAttribute("paneldata", paneldata);
			    url = "chart.jsp?mon=3";
		     }
			
			
			if (transactions != null)
			{       
				DataTable data = new DataTable();
			   
			    data.addColumn(new ColumnDescription("0", ValueType.TEXT, "Category"));
			    data.addColumn(new ColumnDescription("1", ValueType.NUMBER, "Amount"));
			   

			    // Fill the data table.
			    for (int i=0; transactions.size()>i; i++)
			    {
			      			try {
								data.addRowFromValues(transactions.get(i).getCategory(), transactions.get(i).getAmount());
							} catch (TypeMismatchException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}


			    }
			    JsonNode root = null;
			    String json = JsonRenderer.renderDataTable(data, true, false).toString();

			            try{
			                JsonParser parser = new JsonFactory().createJsonParser(json)
			                    .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
			                    .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
			                     root = new ObjectMapper().readTree(parser);
			                }catch(Exception e){
			                	e.printStackTrace();
			                }

			            req.setAttribute("data",root.toString());
			}

		}
			
		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req, resp);
	}

}
