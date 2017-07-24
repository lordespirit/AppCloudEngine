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
public class InsertAdmin extends HttpServlet {

	DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
		  String title = "Insert Admin";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
 
	      String name = req.getParameter("name").trim(); 
	      
	      
	      out.println(docType +
		 	         "<html lang=es>\n" +
		 	            "<head><title>" + title + "</title>"
		 	            		+ "<meta charset=utf-8/></head>\n" +
		 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	        
	        
	      if(name.equals("")||name==null||isNumeric(name)){
	    	  out.println(""
	    	  		+ "<font<h3 style='color:red;'>Error, el nombre pasado es incorrecto</h3>");
	      }else{
	    	  
	    	  dbadmin.connect();
	    	  ArrayList<Admin> adminRecover = dbadmin.selectEqual(Admin.class, "name", name);
	    	  dbadmin.close();
	    	  
	    	  if(adminRecover.size()>0){
	    		  out.println("<h3>Lo sentimos, ese nombre ya se encuentra en la base de datos</h3>");
	    		  out.println("<h4>Prueba con otro nombre distinto</h4>");
	    	  }else{
	    	  
	    	  Admin admin =  new Admin();
	    	  admin.setName(name);
	    	  dbadmin.createAdmin(admin);
	    	 
	    	  out.println("<h3>Administrador insertado correctamente</h3><br/><h4>Datos del administrador:</h4><br/>");
	    	  out.println("<table border='1' cellpadding='10' cellspacing='1' width='40%'>"
	    			+ "<tr><td>Nombre</td><td>ID</td></tr>"
	    	  		+ "<tr><td>"+ admin.getName() +"</td><td>"+ admin.getId() +"</td></tr>" +
	    	  		"</table>");
	    	  }
	      }
	      
	      
	      out.println(	"</p></p></p><a href='/InputInsertAdmin'><p>Volver al menu de insertar</p></a>" +
	      				"</p></p></p><a href='index.html'><p>Volver al menu principal</p></a>"+
    				"</body></html>");
	      	
		
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    @SuppressWarnings("unused")
		double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
}
