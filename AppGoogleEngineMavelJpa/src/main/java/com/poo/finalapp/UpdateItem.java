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
import model.Item;

@SuppressWarnings("serial")
public class UpdateItem extends HttpServlet{


	DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Updating Item";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");

	     Integer itemId = new Integer(req.getParameter("id"));
	     String newName = req.getParameter("newName").trim();
	     String newDescription = req.getParameter("newDescription").trim();
	     Float newPrice = new Float(req.getParameter("newPrice"));
	     
	     out.println(docType +
	 	         "<html lang=es>\n" +
	 	            "<head><title>" + title + "</title>"
	 	            		+ "<meta charset=utf-8/></head>\n" +
	 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	      
		   if(itemId==0||itemId==null){
		   	 out.println("<font<h3 style='color:red;'>Error, no has escogido una galería correctamente</h3>");
		   }else if(newName.equals("")||newName==null||isNumeric(newName)||isNumeric(newDescription)||newDescription==null){
			   out.println("<font<h3 style='color:red;'>Error, no has introducido un nuevo nombre o descripción correctamente</h3>");
		   }else{
	    	  
			   dbadmin.connect();
			   ArrayList<Item> itemsList = dbadmin.selectEqual(Item.class, "name", newName);
			   dbadmin.close(); 
			   
			   if(itemsList.size()>0){
				   out.println("<font<h3 style='color:red;'>Error, el nuevo nombre de Item ya existe en la base de datos</h3>");
			   }else{
			   
				  dbadmin.connect();
		    	  Item item =  dbadmin.find(Item.class, itemId);
		    	  dbadmin.close();
		    	  		
		    	  item.setName(newName);
		    	  item.setDescription(newDescription);
		    	  item.setPrice(newPrice);
		    	  dbadmin.updateItem(item);
		    	  	    	  
		    	  out.println("<h3>Item modificado correctamente</h3><br/>");
		    	  out.println("<h4>Nuevo nombre: "+item.getName()+"</h4>");
		    	  out.println("<h4>ID de Item: "+item.getId()+"</h4>");
		    	  out.println("<h4>Descripción: "+item.getDescription()+"</h4>");
		    	  out.println("<h4>Precio Item: "+item.getPrice()+"</h4>");

			   }
	    	  
	      }
	      
	      
	      out.println(	"</p></p></p><a href='/SelectItemListForUpdate'><p>Volver a update Item</p></a>" +
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
