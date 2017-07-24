package com.poo.finalapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Item;

@SuppressWarnings("serial")
public class DeleteOneItem extends HttpServlet{


	DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Delete One Item";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
	      
	      Integer itemId = new Integer(req.getParameter("id"));


	     out.println(docType +
	 	         "<html lang=es>\n" +
	 	            "<head><title>" + title + "</title>"
	 	            		+ "<meta charset=utf-8/></head>\n" +
	 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
		   if(itemId==0||itemId==null){
		   	 out.println("<font<h3 style='color:red;'>Error, no has escogido un item correctamente</h3>");
		   }else{
	    	  
			  dbadmin.connect();
	    	  Item item =  dbadmin.find(Item.class, itemId);
	    	  dbadmin.close();
	    	  
	    	  dbadmin.removeItem(item);
	    	  	    	  
	    	  out.println("<h3>Item eliminado correctamente</h3>");
	      }
	      
	      
	      out.println(	"</p></p></p><a href='/SelectItemListForDelete'><p>Volver al menú de eliminar Item</p></a>" +
	      				"</p></p></p><a href='index.html'><p>Volver al menú principal</p></a>" +
	      				"</body></html>");

	}
	
}
