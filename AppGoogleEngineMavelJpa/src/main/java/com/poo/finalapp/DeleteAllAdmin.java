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
public class DeleteAllAdmin extends HttpServlet {

DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Delete All Admins";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
	      
	      out.println(docType +
		 	         "<html lang=es>\n" +
		 	            "<head><title>" + title + "</title>"
		 	            		+ "<meta charset=utf-8/></head>\n" +
		 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	    	  
	      dbadmin.connect();
	      ArrayList<Admin> listAdmins = dbadmin.selectAll(Admin.class);
	      dbadmin.close();  	  
	      
	      if(listAdmins.size()==0||listAdmins==null){
	    	  out.println("<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+
	    	    	  "<tr><td><h3>No hay administradores en la base de datos</h3></td></tr>");
	      }else{
	    	  
	    	  dbadmin.connect();
	    	  dbadmin.deleteAll(Admin.class);
	    	  dbadmin.close();
	      }
	    	  
	    	  out.println("<h2>Todos los administradores y todos los registros de Galerías e Items han sido borrados</h2>");
	      
	      out.println(	"</table></p></p></p>"+
	      				"</p></p></p><a href='index.html'><p>Volver al menú principal</p></a>" +
	      				"</body></html>");
	     
		
	}
	
}
