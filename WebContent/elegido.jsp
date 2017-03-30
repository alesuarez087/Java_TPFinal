<%@ page import="controlador.Controlador" %>
<%@ page import="entidades.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/dashboard.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<% Controlador ctrl = new Controlador(); %>
<% Usuario user = (Usuario)request.getSession().getAttribute("userSession"); %>
<body>


 <nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
		</div>
		<div>
        	<ul class="nav navbar-nav">
        		<li class="active"><a href="itemUser.jsp">Discos</a></li>
        		<li><a href="compras.jsp">Compras</a></li>
        	</ul>
					
			
			<ul class="nav navbar-nav navbar-right">
				<% 	if(user != null){ 
					int nro=0;
					if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito") != null){ 
           				nro = ((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")).size();
           			} else nro = 0; %>
        		<li><a>Carrito de compras <span clase="badge">(<%=nro %>)</a></li> <% } %> 
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
            	<li class="active"><a href="#">Discos <span class="sr-only">(current)</span></a></li>
            	<%	
            		for(Genero genero : ctrl.getAllGeneroHab()){ %>
            	<li><a href="#"><%= genero.getDescripcion() %></a></li>
            	<% } %>
            </ul>
        </div>
        
        
        <form role="form" action="srvCompra" method="post" id="compra" name="compra">
	        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          		<h2 class="page-header">Rock</h2>
          		<%
          			Item item = (Item)request.getSession().getAttribute("item");
          		%>
          	<div class="row placeholders">
            	<div class="col-xs-6 col-sm-3 placeholder">
              		<table>
              			<tr>
              				<td colspan="2">
              				<img src="<%= item.getUrlPortada() %>" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">		
              				</td>
              			</tr> <tr>
              				<td colspan="2">
              					<h4><%= item.getTitulo() %></h4>		
              				</td>
              			</tr>
              			<tr>
              				<td colspan="2">
              					<span class="text-muted"><%= item.GetArtista().getNombre() %></span>		
              				</td>
              			</tr>
              			<tr>
              				<td style="vertical-align:middle">
              					<h3>Cantidad</h3>
              				</td>
              				<td style="vertical-align:middle">
              				<select class="form-control" id="cmbCantidad" name="cmbCantidad">
              					<option></option>
              					<option>1</option>
              					<option>2</option>
			              		<option>3</option>
              					<option>4</option>
              					<option>5</option>
              				</select>
              				</td>
              			</tr>
              		</table>
              		<input type="hidden" name="idSelect" id="idSelect" value="<%=item.getId()%>"/>
			  		<input class="btn btn-success btn-sm" type="submit" value="Agregar" id="addCarrito" name="addCarrito" />
            	</div>
          	</div>
		</form>
	</div>
</div>

</body>
</html>