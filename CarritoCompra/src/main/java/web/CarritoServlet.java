package web;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //1) recuperamos la sesión
        HttpSession sesion = request.getSession();

        //2) Recuperar la lista de los artículos q previamente haya creado el cliente
        List<String> articulos = (List<String>) sesion.getAttribute("articulos");

        //3) comprobamos si esxiste
        if (articulos == null) {
            //No hemos agregado nunguno
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);
        }
        //4) 
        String articulosNuevo = request.getParameter("articulo");

        //5) Vamos a compribar el valor de nuestro artículo y lo agregamos
        if (articulosNuevo != null && !articulosNuevo.trim().equals("")) {
            articulos.add(articulosNuevo);
        }

        //6) ;ostramos nuesto carrito de compra en cada solicitud
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("<h1>Lista de la compra: </h1>");
        for (String articulo : articulos) {
            out.print("<li>" + articulo + "</li>");
        }
        out.print("<br/>");
        out.print("<a href='/CarritoCompra'>Inicio<a/>");

        out.close();
    }
}

