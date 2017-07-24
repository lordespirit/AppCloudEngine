package com.poo.finalapp;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Item;

@SuppressWarnings("serial")
public class SelectAllItems extends HttpServlet {

DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Select All Items";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
	      	      
	      out.println(docType +
	 	         "<html lang=es>\n" +
	 	            "<head><title>" + title + "</title>"
	 	            		+ "<meta charset=utf-8/></head>\n" +
	 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	    	  
	      dbadmin.connect();
	      ArrayList<Item> listItems = dbadmin.selectAll(Item.class);
	      dbadmin.close();  	    	  
	    	
	      if(listItems.size()==0||listItems==null){
	    	  out.println("<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
	    	    	  "<tr><td><h3>No hay ítems en la base de datos</h3></td></tr>");
	      }else{
	      
	    	  out.println("<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
	    	  "<tr><td><h3>ID</h3></td/><td><h3>Nombre</h3></td><td><h3>Descripción</h3></td><td><h3>Precio</h3></td><td><h3>Galería</h3></td></tr>");
	    			  for(Item item: listItems){
	    	  		out.print("<tr><td>"+ item.getId() +"</td><td>"+ item.getName() +"</td>"+"<td>"
	    	  				+item.getDescription() +"</td>"+"<td>"+ item.getPrice() +"</td>"
	    	  				+"<td>"+ item.getGallery().getName() +"</td></tr>");
	    			  }
	      
	      }
	      out.println(	"</table></p></p></p>"+
	      				"</p><a href='index.html'><p>Volver al menú principal</p></a>" +
	      				"</body></html>");
	     
	      
		
	}
	
}
