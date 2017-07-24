package com.poo.finalapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Admin;

@SuppressWarnings("serial")
public class FindAdminByName extends HttpServlet{

DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Buscador de Admin";
	      String docType = "<!doctype html >\n";
	      
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
	      
	      String name = req.getParameter("name"); 
	      
	      out.println(docType +
		 	         "<html lang=es>\n" +
		 	            "<head><title>" + title + "</title>"
		 	            		+ "<meta charset=utf-8/></head>\n" +
		 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	      if(name.equals("")||name==null){
	    	  out.println(""
	    	  		+ "<font<h3 style='color:red;'>Error, el nombre pasado es incorrecto</h3>");
	      }else{
	    	  
	    	  dbadmin.connect();
	    	  ArrayList<Admin> adminList = dbadmin.selectLike(Admin.class, "name", name);
	    	  dbadmin.close();	
	    	  if(adminList.size()==0||adminList==null){
	    		  out.println("<font<h3 style='color:red;'>Lista vacía</h3>");
	    	  }else{
	    		  if(adminList.size()==1){
		    	  out.println("<h3>Un administrador encontrado:<h3></p>"+
	    		  "<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>" +
		    			"<tr><td><h3>ID</h3></td/><td><h3>Nombre</h3></td></tr>" +
		    	  		"<tr><td>"+adminList.get(0).getId()+"</td><td>"+ adminList.get(0).getName() +"</td></tr>" +
		    	  		"</table>");
	    		  }else{
			    	  out.println("<h3>Varios administradores encontrados:<h3></p>"
			    	  		+ "<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
			    			  "<tr><td><h3>ID</h3></td/><td><h3>Nombre</h3></td></tr>");
			    			  
			    			  for(Admin admin: adminList){
			  	    	  		out.print("<tr><td>"+ admin.getId() +"</td><td>"+ admin.getName() +"</td></tr>");
			  	    			  }
			    			  out.println("</table>");
	    		  }
	    	  }
	      }
	      
	      
	      out.println(	"</p></p></p><a href='/InputFindAdminByName'><p>Volver al menú de buscar Administrador</p></a>" +
	      				"</p></p></p><a href='index.html'><p>Volver al menú principal</p></a>" +
	      				"</body></html>");
	     
		
		
	}
	
}
