<%@ page import="controlador.Controlador" %>
<%@ page import="entidades.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Discos</title>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="bootstrap/css/dashboard.css" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
	<%Controlador ctrl = new Controlador(); 
		Usuario user = null;
		if((Usuario)request.getSession().getAttribute("userSession")!=null) 
			user = (Usuario)request.getSession().getAttribute("userSession");
	%>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
     <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand">Luzbelito</a>
      </div>
      <div>
        <ul class="nav navbar-nav">
          	<li class="active"><a href="itemUser.jsp">Discos</a></li>
          	<%  if(user!=null) if(user.getTipoUsuario() == entidades.Usuario.TiposUsuario.Administrador){ %>
          	<li><a href="item.jsp">Editar</a></li> <% } else { %>
          	<li><a href="compras.jsp">Compras</a></li> <% } %> 
        </ul>
        <ul class="nav navbar-nav navbar-right">
        	<% 	if(user != null){ int nro=0;
			if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito") != null){ 
           		nro = ((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")).size();
           	} else nro = 0; %>
        	<li><a href="carrito.jsp">
        	<img alt="Brand" src="bootstrap/img/carrito25.png"> Carrito de compras <span clase="badge">(<%=nro %>)</a></li> 

        	<% }
        		
        	if(user != null){	%>        	
        	<form action="srvInicio" method="post" id="cerrar" name="cerrar">
        		<li><button class="btn btn-default navbar-btn navbar-right" id="logout" name="logout">Cerrar Sesión</button></li> 
        	</form><% } else { %>
        	<li><a href="inicio.jsp">Iniciar Sesión</a></li> <% } %>        	
        </ul>
      </div>
    </div>
</nav>

<div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
          	<li><a>Seleccione Búsqueda<span class="sr-only">(current)</span></a></li>
          	<li><a href="itemUser.jsp">Todos</a></li>
            <li><a href="itemArtista.jsp">Artistas</a></li>
            <li class="active"><a>Géneros<span class="sr-only">(current)</span></a></li>
            <li><a href="itemUsuario.jsp">Togp</a></li>
            </ul>
        </div>
	</div>
</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h3 class="page-header">Discos</h3>
		<form action="srvItem" method="post" id="porGenero" name="porGenero">
			<table>
				<tr>
					<td>
					<h3>Género</h3>
					</td>
					<td>
						<select class="form-control" id="cmbGenero" name="cmbGenero">
 		   				<option>
 		   				<%if(request.getAttribute("generoItem")!=null){%> 
 		   				<%= request.getAttribute("generoItem") %> <%}%>
 		   				</option>
  						<%
          	 				for(Genero genero : ctrl.getAllGenero()){
		           		%>
 				   		<option>
 		   					<%=genero.getDescripcion()%>
 		   					
 		   				</option>
 		   				<%} %>
						</select>
					</td>
						
					<td>
						<input class="btn btn-default" type="submit" value="Seleccionar" id="filtroGenero" name="filtroGenero"/>
					</td>
				</tr>
			</table>
		</form>
		<br>
				<div class="row placeholders">
				<% ArrayList<Item> listado = (ArrayList<Item>)request.getSession().getAttribute("listado"); 
				if(listado != null){
				for(Item item : listado){ %>
            		<div class="col-xs-6 col-sm-3 placeholder">
              			<img src="<%= item.getUrlPortada() %>" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
              			<h4><%= item.getTitulo() %></h4>
              			<span class="text-muted"><%= item.GetArtista().getNombre() %></span>
              			<h4>$<%=item.getPrecio() %></h4>
					<form action="srvCompra" method="post" id="compra" name="compra">
              			<input type="hidden" name="idSelect" id="idSelect" value="<%=item.getId()%>"/>
           				<input class="btn btn-success btn-sm" type="submit" value="Agregar" id="eventSale" name="eventSale" />
           	  		</form>
            		</div>
            	<% } } %>
				</div>
	</div>
	
</body>
</html>