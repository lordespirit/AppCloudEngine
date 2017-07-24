package com.poo.finalapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Gallery;

@SuppressWarnings("serial")
public class DeleteOneGallery extends HttpServlet{


	DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Delete One Gallery";
	      String docType = "<!doctype html >\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
	     Integer galleryId = new Integer(req.getParameter("id"));


	     out.println(docType +
	 	         "<html lang=es>\n" +
	 	            "<head><title>" + title + "</title>"
	 	            		+ "<meta charset=utf-8/></head>\n" +
	 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	      
		   if(galleryId==0||galleryId==null){
		   	 out.println("<font<h3 style='color:red;'>Error, no has escogido una galería correctamente</h3>");
		   }else{
	    	  
			  dbadmin.connect();
	    	  Gallery gallery =  dbadmin.find(Gallery.class, galleryId);
	    	  dbadmin.close();
	    	  
	    	  dbadmin.removeGallery(gallery);
	    	  	    	  
	    	  out.println("<h3>Galería eliminada correctamente</h3>");
	      }
	      
	      
	      out.println(	"</p></p></p><a href='/SelectGalleryListForDelete'><p>Volver al menú de eliminar galería</p></a>" +
	      				"</p></p></p><a href='index.html'><p>Volver al menú principal</p></a>" +
	      				"</body></html>");

	}
	
}
