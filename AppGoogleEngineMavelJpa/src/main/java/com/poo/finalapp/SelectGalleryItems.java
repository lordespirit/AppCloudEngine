package com.poo.finalapp;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Gallery;
import model.Item;

@SuppressWarnings("serial")
public class SelectGalleryItems extends HttpServlet {

DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Select Gallery Items";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
	      
	      Integer galleryId = new Integer(req.getParameter("id"));
	      	      
	      out.println(docType +
		 	         "<html lang=es>\n" +
		 	            "<head><title>" + title + "</title>"
		 	            		+ "<meta charset=utf-8/></head>\n" +
		 	           "<body bgcolor = \"#f0f0f0\">\n");
	    	  
	      dbadmin.connect();
	      Gallery gallery = dbadmin.find(Gallery.class, galleryId);
	      dbadmin.close();  	    	  
	    	
	      if(gallery==null||gallery.getId()==0){
	    	  out.println("<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
	    	    	  "<tr><td><h3>La galería no es correcta</h3></td></tr>");
	      }else{
	    	  
	    	  if(gallery.getItems().size()==0){
	    		  out.println("<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
		    	    	  "<tr><td><h3>La galería no tiene items</h3></td></tr>");
	    		  
	    	  }else{
	      
	    	  out.println("<table style='text-align:center;' bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
	    	  "<tr><td><h3>ID Item</h3></td><td><h3>Nombre Item</h3></td><td><h3>Descripción Item</h3><td><h3>Precio Item</h3></tr>");
	    			  for(Item item: gallery.getItems()){
	    	  		out.print("<tr><td>"+ item.getId() +"</td><td>"+ item.getName() +"</td><td>"+ item.getDescription() +"</td><td>"+ item.getPrice() +"</td></tr>");
	    			  }
	    	  }
	      
	      }
	      out.println(	"</table style='text-align:center;'></p></p></p>"+
	      				"</p><a href='index.html'><p>Volver al menú principal</p></a>" +
	      				"</body></html>");
	     
	      
		
	}
	
}
