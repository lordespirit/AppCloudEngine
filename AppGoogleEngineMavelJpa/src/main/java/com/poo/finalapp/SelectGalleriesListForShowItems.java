package com.poo.finalapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Gallery;

@SuppressWarnings("serial")
public class SelectGalleriesListForShowItems extends HttpServlet{


DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Select Gallery Items";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");	     
	      
	      out.println(docType +
		 	         "<html lang=es>\n" +
		 	            "<head><title>" + title + "</title>"
		 	            		+ "<meta charset=utf-8/></head>\n" +
		 	           "<body bgcolor = \"#f0f0f0\">\n");
	    	  
	      dbadmin.connect();
	      ArrayList<Gallery> listGalleries = dbadmin.selectAll(Gallery.class);
	      dbadmin.close();  	
	      
	      if(listGalleries.size()==0||listGalleries==null){
	    	  out.println("<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
	    	    	  "<tr><td><h3>No hay galerías en la base de datos</h3></td></tr>");
	      }else{
	    	  out.println("<h2>Selecciona una galería de la lista</h2>"
	    	  		+ "<form id='galerias' action='/SelectGalleryItems' method='post'>");
	    	  
	    	  out.println("<label for='so'>Galerias</label><br/>" +
	    			  	"<select size='5' name='id' form='galerias' required><br/>");
	    	  
	    	  for(Gallery gallery: listGalleries)    	  
	    		  out.println("<option value='"+ gallery.getId() +"'>"+gallery.getName()+"</option>");

	    	  out.println("</select><br/>");
	    	  out.println("<input type = 'submit' value = 'Mostrar Items' />");
	    	  out.println("</form>");
		    	  	
		
	      }
	      
	      out.println(
    				"</p></p></p><a href='index.html'><p>Volver al menú principal</p></a>" +
    				"</body></html>");
   
	}
	
	
}

