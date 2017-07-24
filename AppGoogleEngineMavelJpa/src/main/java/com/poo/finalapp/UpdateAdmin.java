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
public class UpdateAdmin extends HttpServlet{


	DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Update an Admin";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");

	     Integer adminId = new Integer(req.getParameter("id"));
	     String newName = req.getParameter("newName").trim();

	     out.println(docType +
	 	         "<html lang=es>\n" +
	 	            "<head><title>" + title + "</title>"
	 	            		+ "<meta charset=utf-8/></head>\n" +
	 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	      
		   if(adminId==0||adminId==null){
		   	 out.println("<font<h3 style='color:red;'>Error, no has escogido un administrador correctamente</h3>");
		   }else if(newName.equals("")||newName==null||isNumeric(newName)){
			   out.println("<font<h3 style='color:red;'>Error, no has introducido un nuevo nombre correcto</h3>");
		   }else{
	    	  
			   dbadmin.connect();
			   ArrayList<Admin> adminList = dbadmin.selectEqual(Admin.class, "name", newName);
			   dbadmin.close(); 
			   
			   if(adminList.size()>0){
				   out.println("<font<h3 style='color:red;'>Error, el nuevo nombre ya existe en la base de datos</h3>");
			   }else{
			   
				  dbadmin.connect();
		    	  Admin admin =  dbadmin.find(Admin.class, adminId);
		    	  dbadmin.close();
		    	  
		    	  admin.setName(newName);
		    	  dbadmin.updateAdmin(admin);
		    	  	    	  
		    	  out.println("<h3>Admin modificado correctamente</h3><br/>");
		    	  out.println("<h4>Nuevo nombre: "+admin.getName()+"</h4>");
		    	  out.println("<h4>ID del admin: "+admin.getId()+"</h4>");
			   }
	    	  
	      }
	      
	      
	      out.println(	"</p></p></p><a href='/SelectAdminListForUpdate'><p>Volver a update administrador</p></a>" +
	      				"</p></p></p><a href='index.html'><p>Volver al menú principal</p></a>" +
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
