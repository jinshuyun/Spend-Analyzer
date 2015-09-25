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

import bean.Chartdata;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.render.JsonRenderer;

import data.TransactionDB;

public class popup extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String chart = req.getParameter("chart");
		HttpSession session = req.getSession();

		String url;
		int uid = (int) session.getAttribute("uid");
		if ("line".equals(chart)) 
			{
				ArrayList<Chartdata> data =  TransactionDB.diplayByChart(uid);
				if (data != null)
				{   
				    
					DataTable data3 = new DataTable();
				   
				    data3.addColumn(new ColumnDescription("0", ValueType.TEXT, "Date"));
				    data3.addColumn(new ColumnDescription("1", ValueType.NUMBER, "Supermarkets"));
				    data3.addColumn(new ColumnDescription("2", ValueType.NUMBER, "Gasoline"));
				    data3.addColumn(new ColumnDescription("3", ValueType.NUMBER, "Restaurants"));
				   

				    // Fill the data table.
				    for (int i=0; data.size()>i; i++)
				    {
				      			try {
									data3.addRowFromValues(data.get(i).getDate(), data.get(i).getSupermarket(), data.get(i).getGasoline(), data.get(i).getRestaurant());
								} catch (TypeMismatchException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}


				    }
				    JsonNode root = null;
				    String json = JsonRenderer.renderDataTable(data3, true, false).toString();

				            try{
				                JsonParser parser = new JsonFactory().createJsonParser(json)
				                    .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
				                    .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
				                     root = new ObjectMapper().readTree(parser);
				                }catch(Exception e){
				                	e.printStackTrace();
				                }

				            req.setAttribute("data3",root.toString());

				}
				
			}
			if ("bar".equals(chart)) 
			{
				ArrayList<Chartdata> data =  TransactionDB.diplayByChart(uid);
				if (data != null)
				{  
				    
					DataTable data4 = new DataTable();
				   
				    data4.addColumn(new ColumnDescription("0", ValueType.TEXT, "Date"));
				    data4.addColumn(new ColumnDescription("1", ValueType.NUMBER, "Supermarkets"));
				    data4.addColumn(new ColumnDescription("2", ValueType.NUMBER, "Gasoline"));
				    data4.addColumn(new ColumnDescription("3", ValueType.NUMBER, "Restaurants"));
				   

				    // Fill the data table.
				    for (int i=0; data.size()>i; i++)
				    {
				      			try {
									data4.addRowFromValues(data.get(i).getDate(), data.get(i).getSupermarket(), data.get(i).getGasoline(), data.get(i).getRestaurant());
								} catch (TypeMismatchException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}


				    }
				    JsonNode root = null;
				    String json = JsonRenderer.renderDataTable(data4, true, false).toString();

				            try{
				                JsonParser parser = new JsonFactory().createJsonParser(json)
				                    .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
				                    .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
				                     root = new ObjectMapper().readTree(parser);
				                }catch(Exception e){
				                	e.printStackTrace();
				                }

				            req.setAttribute("data4",root.toString());


				}
				
			}
			if ("combo".equals(chart))
			{
				ArrayList<Chartdata> data =  TransactionDB.diplayByCombo(uid);
				if (data != null)
				{   
				    
					DataTable data5 = new DataTable();
				   
				    data5.addColumn(new ColumnDescription("0", ValueType.TEXT, "Date"));
				    data5.addColumn(new ColumnDescription("1", ValueType.NUMBER, "Supermarkets"));
				    data5.addColumn(new ColumnDescription("2", ValueType.NUMBER, "Gasoline"));
				    data5.addColumn(new ColumnDescription("3", ValueType.NUMBER, "Restaurants"));
				    data5.addColumn(new ColumnDescription("4", ValueType.NUMBER, "Average"));
				   

				    // Fill the data table.
				    for (int i=0; data.size()>i; i++)
				    {
				      			try {
									data5.addRowFromValues(data.get(i).getDate(), data.get(i).getSupermarket(), data.get(i).getGasoline(), data.get(i).getRestaurant(), data.get(i).getAvg() );
								} catch (TypeMismatchException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}


				    }
				    JsonNode root = null;
				    String json = JsonRenderer.renderDataTable(data5, true, false).toString();

				            try{
				                JsonParser parser = new JsonFactory().createJsonParser(json)
				                    .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
				                    .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
				                     root = new ObjectMapper().readTree(parser);
				                }catch(Exception e){
				                	e.printStackTrace();
				                }

				            req.setAttribute("data5",root.toString());


				}
				
			}
			if ("area".equals(chart)) 
			{
				ArrayList<Chartdata> data =  TransactionDB.diplayByArea(uid);
				if (data != null)
				{  
				    
					DataTable data6 = new DataTable();
				   
				    data6.addColumn(new ColumnDescription("0", ValueType.TEXT, "Date"));
				    data6.addColumn(new ColumnDescription("1", ValueType.NUMBER, "Supermarkets & Restaurants & Gasoline"));
				    data6.addColumn(new ColumnDescription("2", ValueType.NUMBER, "All Categories"));
				   

				    // Fill the data table.
				    for (int i=0; data.size()>i; i++)
				    {
				      			try {
									data6.addRowFromValues(data.get(i).getDate(), data.get(i).getSupermarket(), data.get(i).getAvg());
								} catch (TypeMismatchException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}


				    }
				    JsonNode root = null;
				    String json = JsonRenderer.renderDataTable(data6, true, false).toString();

				            try{
				                JsonParser parser = new JsonFactory().createJsonParser(json)
				                    .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
				                    .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
				                     root = new ObjectMapper().readTree(parser);
				                }catch(Exception e){
				                	e.printStackTrace();
				                }

				            req.setAttribute("data6",root.toString());


				}
			}
				if ("stack".equals(chart)) 
				{
					ArrayList<Chartdata> data =  TransactionDB.diplayByChartStack(uid);
					if (data != null)
					{   
					    
						DataTable data7 = new DataTable();
					   
					    data7.addColumn(new ColumnDescription("0", ValueType.TEXT, "Date"));
					    data7.addColumn(new ColumnDescription("1", ValueType.NUMBER, "Supermarkets"));
					    data7.addColumn(new ColumnDescription("2", ValueType.NUMBER, "Gasoline"));
					    data7.addColumn(new ColumnDescription("3", ValueType.NUMBER, "Restaurants"));
					    data7.addColumn(new ColumnDescription("4", ValueType.NUMBER, "Department Stores"));
					    data7.addColumn(new ColumnDescription("5", ValueType.NUMBER, "Merchandise"));
					    data7.addColumn(new ColumnDescription("6", ValueType.NUMBER, "Services"));
					    data7.addColumn(new ColumnDescription("7", ValueType.NUMBER, "Education"));
					    data7.addColumn(new ColumnDescription("8", ValueType.NUMBER, "Mail"));
					    // Fill the data table.
					    for (int i=0; data.size()>i; i++)
					    {
					      			try {
										data7.addRowFromValues(data.get(i).getDate(), data.get(i).getSupermarket(), data.get(i).getGasoline(), data.get(i).getRestaurant(), 
												data.get(i).getDepartment(), data.get(i).getMerchandise(), data.get(i).getService(), data.get(i).getEducation(), data.get(i).getMail());
									} catch (TypeMismatchException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}


					    }
					    JsonNode root = null;
					    String json = JsonRenderer.renderDataTable(data7, true, false).toString();

					            try{
					                JsonParser parser = new JsonFactory().createJsonParser(json)
					                    .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
					                    .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
					                     root = new ObjectMapper().readTree(parser);
					                }catch(Exception e){
					                	e.printStackTrace();
					                }

					            req.setAttribute("data7",root.toString());


					}
				
			}
	        url = "pop";
			RequestDispatcher view = req.getRequestDispatcher(url);
			view.forward(req, resp);
	
		}
	}
	
