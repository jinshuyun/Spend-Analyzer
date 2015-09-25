package analyzer.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import bean.Transaction;
//import bean.Transaction;
import data.RecordDB;
//import data.TransactionDB;
import data.TransactionDB;

public class Uploadfile extends HttpServlet {
	//private String uploadPath = "C:\\Users\\shuyun\\Documents\\workspace-sts-3.4.0.RELEASE\\analyzer\\web\\WEB-INF\\upload";
	private String uploadPath = "C:\\ProgramData\\MySQL\\MySQL Server 5.6\\data\\spend\\";
	private String tempPath = "C:\\Users\\shuyun\\Documents\\workspace-sts-3.4.0.RELEASE\\analyzer\\web\\WEB-INF\\";
	File tempPathFile;
		
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        String message="No";
    	String url = "";
 
    	boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
    	// Check that we have a file upload request
		if (isMultipartContent) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			factory.setRepository(tempPathFile);
			
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

	        
			// Parse the request
			List<FileItem> items;
			try {
				    items = upload.parseRequest(req);//upload a file
					Iterator<FileItem> iter = items.iterator();					
					while (iter.hasNext())
					{
					    FileItem item = iter.next();
					    String fileName = item.getName();
					    String filePath = uploadPath + fileName;
				    	 message = filePath;

					    if (fileName != null) {
			                  File fullFile = new File(item.getName());
			                  File savedFile = new File(uploadPath, fullFile.getName());
			                  item.write(savedFile);		              
			                 }
			            if( RecordDB.insertRecordWOC(fileName)>0)//without category
			             {
			            	message="Load";
			            	HttpSession session = req.getSession();
			            	int uid = (int) session.getAttribute("uid");
			            	if (RecordDB.toTransactionwoc(uid)!=0)// from record temp table to transaction table
			            	{ 
			            		ArrayList<Transaction> transactions = TransactionDB.findUserTransactions(uid); // show transaction for this user
			            	    session.setAttribute("transactions", transactions);
				    			if (RecordDB.deleteRecordwoc()>0)//delete the records from record temp table
				    				message = "Successfully uploaded! We have auto-categoried your transacions. Click Step 1 to continue uploading.";				    
			                 }
					      }	
	                	if( RecordDB.insertRecordWC(fileName)>0) //with category
			             {
			            	message="Load";
			            	HttpSession session = req.getSession();
			            	int uid = (int) session.getAttribute("uid");

			            	if (RecordDB.toTransaction(uid)!=0)// from record temp table to transaction table
			            	{  
			            		ArrayList<Transaction> transactions = TransactionDB.findUserTransactions(uid); // show transaction for this user
			            	    session.setAttribute("transactions", transactions);
				    			if (RecordDB.deleteRecord()>0)//delete the records from record temp table
				    				message = "Successfully uploaded! Click Step 1 to continue uploading.";				    
			                 }
					      }
	                 } 	//iter while		            		               
				   }//try
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   }
				
		  }//check upload

		req.setAttribute("message", message);
		url = "table.jsp";
		RequestDispatcher view = req.getRequestDispatcher(url);
		view.forward(req, resp);
    }		
		public void init() throws ServletException {
		       File uploadFile = new File(uploadPath);
		       if (!uploadFile.exists()) {
		           uploadFile.mkdirs();
		       }
		       File tempPathFile = new File(tempPath);
		        if (!tempPathFile.exists()) {
		           tempPathFile.mkdirs();
		       }
		    }

}

