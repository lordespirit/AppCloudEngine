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
public class InsertItemIntoGallery extends HttpServlet{


DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Insert Item";
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
	    	  out.println("<table style='text-align:center;' bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
	    	    	  "<tr><td><h3>No hay galerias en la base de datos</h3></td></tr><tr><td>h4>Inserte primero unas galeria antes de insertar el item</h4></td></tr>");
	      }else{
	    	  out.println("<h2>Selecciona un administrador de la lista para añadir la galería</h2>"
	    	  		+ "<form id='galerias' action='/InsertItem' method='post'>");
	    	  
	    	  out.println("<label for='so'>Escoga una galeria: </label><br/>" +
	    			  	"<select size='5' name='id' form='galerias' required>");
	    	  
	    	  for(Gallery gallery: listGalleries)    	  
	    		  out.println("<option value='"+ gallery.getId() +"'>"+gallery.getName()+"</option>");

	    	  out.println("</select><br/>");
	    	  
	    	  out.println("<table style='text-align:center;' bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%''>"
	    	  		+ "<tr>"
	    	  		+ "<td width='70%' ><p style='color:black'>Nombre item:</p></td>"
	    	  		+ "<td width='50%'><input type = 'text' name = 'item' required/></td>"
	    	  		+ "<td width='70%' ><p style='color:black'>Descripción item:</p></td>"
	    	  		+ "<td width='50%'><input type = 'text' name = 'description' required/></td>"
	    	  		+ "<td width='70%' ><p style='color:black'>Precio item:</p></td>"
	    	  		+ "<td width='50%'><input type = 'number' step='0.01' name = 'price' required/></td>"
	    	  		+ "</tr>"
	    	  		+ "</table>");

	    	  out.println("<input type = 'submit' value = 'Insert Item' />");
	    	  out.println("</form>");
		  
	
	      }
	      
	      out.println(
    				"</p></p></p><a href='index.html'><p>Volver al menú principal</p></a>" +
    				"</body></html>");
	      
	}
	
	
}

