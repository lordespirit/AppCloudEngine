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
import model.Gallery;

@SuppressWarnings("serial")
public class InsertGallery extends HttpServlet{


	DBAdmin dbadmin = new DBAdmin();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
	      String title = "Insert Gallery to Admin";
	      String docType = "<!doctype html>\n";
	      
	      response.setContentType ("text/html;charset=ISO-8859-1");
	      req.setCharacterEncoding("ISO-8859-1");
	      
	     Integer adminId = new Integer(req.getParameter("id"));
	     String galleryName = req.getParameter("gallery").trim();

	     out.println(docType +
	 	         "<html lang=es>\n" +
	 	            "<head><title>" + title + "</title>"
	 	            		+ "<meta charset=utf-8/></head>\n" +
	 	           "<body bgcolor = \"#f0f0f0\">\n");
	      
	      
		   if(adminId==0||adminId==null){
		   	 out.println("<font<h3 style='color:red;'>Error, no has escogido un administrador correctamente</h3>");
		   }else if(galleryName.equals("")||galleryName==null||isNumeric(galleryName)){
			   out.println("<font<h3 style='color:red;'>Error, no has introducido un nombre de galería correcto</h3>");
		   }else{
	    	  
			   dbadmin.connect();
			   ArrayList<Gallery> galleryList = dbadmin.selectEqual(Gallery.class, "name", galleryName);
			   dbadmin.close(); 
			   
			   if(galleryList.size()>0){
				   out.println("<font<h3 style='color:red;'>Error, el nombre de la galería ya existe en la base de datos</h3>");
			   }else{
			   
				  dbadmin.connect();
		    	  Admin admin =  dbadmin.find(Admin.class, adminId);
		    	  dbadmin.close();
		    	  
		    	  Gallery gallery = new Gallery();
		    	  gallery.setName(galleryName);
		    	  
		    	  dbadmin.createGallery(admin, gallery);
		    	  	    	  
		    	  out.println("<h3>Galería introducida correctamente</h3><br/>");
		    	  out.println("<h4>Nombre Admin: "+gallery.getAdmin().getName()+"</h4>");
		    	  out.println("<h4>ID del admin: "+gallery.getAdmin().getId()+"</h4>");
		    	  out.println("<h4>Nombre galería: "+gallery.getName()+"</h4>");
		    	  out.println("<h4>ID de galería: "+gallery.getId()+"</h4>");
			   }
	    	  
	      }
	      
	      
	      out.println(	"</p></p></p><a href='/InsertGallerySelectAdmin'><p>Volver a insertar una nueva galería</p></a>" +
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
