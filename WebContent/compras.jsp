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
</head>
<body>

	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/dashboard.css" rel="stylesheet">
	<link href="bootstrap/css/propio.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compras</title>
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
            <li class="active"><a href="artista.jsp">Compras<span class="sr-only">(current)</span></a></li>
            <li><a href="itemsComprados.jsp">Clasficaciones</a></li>
            </ul>
        </div>
	</div>
</div>

<div class="col-sm-4 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<div class="container-fluid">
		<h3>Compras</h3>
		<div class="row">
			<table class="table table-hover" style="text-align: left">
       		<thead> 
			<tr> 
				<th>Código</th>
           		<th>Discos</th>
           		<th>Fecha</th>
           		<th>Precio Final</th> 
         	</tr> 
       		</thead>
       		<tbody>
       		<% for(Venta venta : ctrl.getAllVentaForUser(user.getId())){  %>
         	<tr>
           		<td style="vertical-align:middle">
           			<input type="hidden" name="nro" id="nro" value="<%=venta.getId()%>" /><%=venta.getId()%>
           		</td>
           		<td style="vertical-align:middle">
           			<% for(VentaItem ventaItem : ctrl.getAllVentaVentaItem(venta.getId())){ %>
           				<input type="hidden" name="disco" id="disco" value="<%=ventaItem.GetItem().getTitulo()%>"/><%=ventaItem.GetItem().getTitulo() %> 
					<% } %>
           		</td>
           		<td style="vertical-align:middle">
           			<input type="hidden" name="artista" id="artista" value="<%=venta.getFecha()%>"/><%=venta.getFecha()%>
           		</td>
           		<td style="vertical-align:middle">
  					<input type="hidden" name="artista" id="artista" value="$ <%=venta.getMontoTotal()%>"/><%=venta.getMontoTotal()%>
           		</td>
			</tr>
         	<% } %>
			</tbody>
			</table>
		</div>
	</div>
</div>

</body>
</html>