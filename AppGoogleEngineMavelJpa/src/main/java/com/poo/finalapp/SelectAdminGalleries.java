package com.poo.finalapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Admin;
import model.Gallery;

@SuppressWarnings("serial")
public class SelectAdminGalleries extends HttpServlet {

DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Select Admin Galleries";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
	      
	      Integer adminId = new Integer(req.getParameter("id"));
	      	      
	      out.println(docType +
		 	         "<html lang=es>\n" +
		 	            "<head><title>" + title + "</title>"
		 	            		+ "<meta charset=utf-8/></head>\n" +
		 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	    	  
	      dbadmin.connect();
	      Admin admin = dbadmin.find(Admin.class, adminId);
	      dbadmin.close();  	    	  
	    	
	      if(admin==null||admin.getId()==0){
	    	  out.println("<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
	    	    	  "<tr><td><h3>El administrador no es correcto</h3></td></tr>");
	      }else{
	      
	    	  if(admin.getGalleries().size()==0){
	    		  out.println("<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
		    	    	  "<tr><td><h3>El administrador no tiene galerías</h3></td></tr>");
	    		  
	    	  }else{
	    	  
	    	  out.println("<table style='text-align:center;' bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
	    	  "<tr><td><h3>ID Galería</h3></td><td><h3>Nombre Galería</h3></td></tr>");
	    			  for(Gallery gallery: admin.getGalleries()){
	    	  		out.print("<tr><td>"+ gallery.getId() +"</td><td>"+ gallery.getName() +"</td></tr>");
	    			  }
	    	  }
	      
	      }
	      out.println(	"</table style='text-align:center;'></p></p></p>"+
	      				"</p><a href='index.html'><p>Volver al menú principal</p></a>" +
	      				"</body></html>");
	     
	      
		
	}
	
}
