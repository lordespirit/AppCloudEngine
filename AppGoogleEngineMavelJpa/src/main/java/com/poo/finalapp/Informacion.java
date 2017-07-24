package com.poo.finalapp;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class Informacion extends HttpServlet {

	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException {
		      
			  PrintWriter out = response.getWriter();
			  String docType = "<!doctype html>\n";
			  String title =  "Acerca de";
			  
		    response.setContentType("text/html");
			out.println(docType +
		       "<html lang=es>\n" +
	            "<head><title>" + title + "</title><meta charset=utf-8></head>\n" +
		          	"<style>" +
		          	" div { border: 1;"
		          	+ "} " +
		          	" body {"
		          	+ "}" +
		          	 "</style>"	+
		          	"</head>\n" +
		          "<body bgcolor = \"#f0f0f0\">\r\n" +
		          	"<div>" +
		             "<h1> Aplicación web - Test de base de datos </h1>\r\n\r\n\r\n\r\n" +	         
		             "<h2> Primera App en Google Cloud Engine </h2>\r\n" +
		             "<h3> Versión para test v1.0a</h3>\r\n" +
		             "<h4> Prohibido su uso comercial</h4>\r\n\r\n\r\n\r\n" +
		             "<div>" +
		             "<footer>" +
		             	"<p> Owner: Eduard V.C. </p>\r\n\r\n" +
		             	"<a href='index.html'><button>Volver al menú principal</button></a>"+
		             "</footer>" +
		          "</body></html>"
		    );

		  }
}