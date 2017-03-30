<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.*" %>
<%@ page import="controlador.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compras</title>

	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/dashboard.css" rel="stylesheet">
	<link href="bootstrap/css/propio.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<% Controlador ctrl = new  Controlador(); %>
	<% Usuario user = (Usuario)request.getSession().getAttribute("userSession"); %>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
		</div>
		<div>
        	<ul class="nav navbar-nav">
        		<li><a href="itemUser.jsp">Discos</a></li>
        		<li class="active"><a href="compras.jsp">Compras</a></li>
        	</ul>
					
			
			<ul class="nav navbar-nav navbar-right">
				<% 	if(user != null){ 
					int nro=0;
					if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito") != null){ 
           				nro = ((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")).size();
           			} else nro = 0; %>
        		<li><a>
        			<img alt="Brand" src="bootstrap/img/carrito25.png"> Carrito de compras <span clase="badge">(<%=nro %>)</a></li> <% } %> 
				<form action="srvInicio" method="post" id="cerrar" name="cerrar">
        			<li><button class="btn btn-default navbar-btn navbar-right" id="logout" name="logout">Cerrar Sesión</button></li> 
        		</form>
        	</ul>
		</div>
	</div>
</nav>

<div class="container-fluid">
      <div class="row">	
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="compras.jsp">Compras</a></li>
            <li class="active"><a href="itemsComprados.jsp">Clasficaciones<span class="sr-only">(current)</span></a></li>
            </ul>
        </div>
	</div>
</div>

<div class="col-sm-4 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h3 class="page-header">Clasificar</h3>
	<table class="table table-hover">
	<tr>
		<th>Titulo</th>
		<th>Artista</th>
		<th>Clasificacion</th>
		<th></th>
	</tr>
	<% for(VentaItem vi : ctrl.getAllVentaItem(user.getId())){ %>
	<tr>
		<td>
			<%=vi.GetItem().getTitulo() %>
		</td>
		<td>
			<%=vi.GetItem().GetArtista().getNombre() %>
		</td>
		<td>
			<% Clasificacion cl = ctrl.getOneClasificacion(vi.GetItem().getId(), user.getId());
			if(cl !=null){ %> <%= cl.getValor() %><% } %>
		</td>
		<form role="form" action="srvCalificar" method="post" id="botonera" name="botonera">
			<td style="vertical-align:middle">
				<input type="hidden" name="itemSelect" id="itemSelect" value="<%=vi.GetItem().getId()%>"/>
           		<input class="btn btn-success btn-sm" type="submit" value="Clasificar" id="eventSelect" name="eventSelect" />
           	</td>
		</form>
	</tr>
	<%} %>
	</table>
</div>


</body>
</html>