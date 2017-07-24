package com.poo.finalapp;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;

@SuppressWarnings("serial")
public class InputInsertAdmin extends HttpServlet{


	DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Input Admin";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
	      

	     out.println(docType +
	 	         "<html lang=es>\n" +
	 	            "<head><title>" + title + "</title>"
	 	            		+ "<meta charset=utf-8/></head>\n" +
	 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	     out.println("<h3>Insert Admin</h3>"+
		  
		  "<p>Introduce nombre de administrador</p>"+
		  
		  	"<form action='/InsertAdmin' method=post>"+
			"<table bgcolor ='#FFF5EE' border='1' cellpadding='10' cellspacing='1' width='40%'>"+

				"<tr>"+
			      " <td width='70%' ><p>Nombre:</p> </td>"+
			      " <td width='50%'><input type = 'text' name = 'name' /></td>"+
				"</tr>"+
			"</table>"+
			"<input type = 'submit' value = 'Insert Admin' />"+
		"</form>");
		  
	      
	      
	      out.println(
	      				"</p></p></p><a href='index.html'><p>Volver al menú principal</p></a>" +
	      				"</body></html>");

	}
	
	
}
