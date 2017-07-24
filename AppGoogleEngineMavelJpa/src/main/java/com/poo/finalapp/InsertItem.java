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
public class InsertItem extends HttpServlet{


	DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Insert Item into Gallery";
	      String docType = "<!doctype html>\n";
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
	      
	     Integer galleryId = new Integer(req.getParameter("id"));
	     String itemName = req.getParameter("item").trim();
	     String description = req.getParameter("description").trim();
	     Float price = new Float(req.getParameter("price"));

	     
	     out.println(docType +
	 	         "<html lang=es>\n" +
	 	            "<head><title>" + title + "</title>"
	 	            		+ "<meta charset=utf-8/></head>\n" +
	 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	      
		   if(galleryId==0||galleryId==null){
		   	 out.println("<font<h3 style='color:red;'>Error, no has escogido una galería correctamente</h3>");
		   }else if(itemName.equals("")||itemName==null||isNumeric(itemName)||
				   		description.equals("")||description==null||price==null||
				   			isNumeric(description)){
			   out.println("<font<h3 style='color:red;'>Error, no has introducido correctamente los datos del item.</h3>");
		   }else{
	    	  
			   dbadmin.connect();
			   ArrayList<Item> galleryList = dbadmin.selectEqual(Item.class, "name", itemName);
			   dbadmin.close(); 
			   
			   if(galleryList.size()>0){
				   out.println("<font<h3 style='color:red;'>Error, el nombre del item ya existe en la base de datos</h3>");
			   }else{
			   
				  dbadmin.connect();
		    	  Gallery gallery =  dbadmin.find(Gallery.class, galleryId);
		    	  dbadmin.close();
		    	  
		    	  Item item = new Item();
		    	  item.setName(itemName);
		    	  item.setDescription(description);
		    	  item.setPrice(price);
		    	  
		    	  dbadmin.createItem(gallery, item);
		    	  	    	  
		    	  out.println("<h3>Item introducido correctamente</h3><br/>");
		    	  out.println("<h4>Nombre Item: "+item.getName()+"</h4>");
		    	  out.println("<h4>ID del Item: "+item.getId()+"</h4>");
		    	  out.println("<h4>Nombre galería: "+item.getGallery().getName()+"</h4>");
		    	  out.println("<h4>ID de galería: "+item.getGallery().getId()+"</h4>");
			   }
	    	  
	      }
	      
	      
	      out.println(	"</p></p></p><a href='/InsertItemIntoGallery'><p>Volver a insertar un nuevo item</p></a>" +
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
