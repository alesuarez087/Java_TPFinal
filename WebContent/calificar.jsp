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
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artistas</title>
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
          <li class="active"><a>Editar</a></li>
          </ul>
        <ul class="nav navbar-nav navbar-right">
        	<li><a href="srvInicio.jps">Cerrar Sesión</a>
        </ul>
      </div>
    </div>
</nav>
<div class="container-fluid">
	<h3>Compras</h3>
      <div class="row">
        <div class="col-sm-4 col-md-4">
			<table class="table table-hover">
       <thead> 
         <tr> 
           <th>Código de Compra</th>
           <th>Nombre de Disco</th>
           <th>Artista</th>
           <th>Calificacion</th>
           <th></th> 
         </tr> 
       </thead>
       <tbody>
       	<% for(VentaItem ventaItem : ctrl.VentaItemGetAll(user.getId())){  %>
         <tr>
           <td style="vertical-align:middle">
           		<input type="hidden" name="nro" id="nro" value="<%=ventaItem.getIdVenta()%>" /><%=ventaItem.getIdVenta()%>
           </td>
           <td style="vertical-align:middle">
           		<input type="hidden" name="disco" id="disco" value="<%=ventaItem.GetItem().getTitulo()%>"/><%=ventaItem.GetItem().getTitulo()%>
           </td>
           <td style="vertical-align:middle">
           		<input type="hidden" name="artista" id="artista" value="<%=ventaItem.GetItem().GetArtista().getNombre()%>"/><%=ventaItem.GetItem().GetArtista().getNombre()%>
           </td>
           <td style="vertical-align:middle">
           		<select class="form-control" id="cmbCalif" name="cmbCalif">
 		   				<option>
 		   				<%if(request.getAttribute("generoItem")!=null){%> 
 		   				<%= request.getAttribute("generoItem") %> <%}%>
 		   				</option>
 				   		<option>1</option>
 				   		<option>2</option>
 				   		<option>3</option>
 				   		<option>4</option>
 				   		<option>5</option>
				</select>
           </td>
           <form role="form" action="srvCompra" method="post" id="botonera" name="botonera">
           		<td style="vertical-align:middle">
           			<input type="hidden" name="idSelect" id="idSelect" value="<%=ventaItem.getId()%>"/>
           			<input class="btn btn-success btn-sm" type="submit" value="Calificar" id="eventCalif" name="eventCalif" />
           		</td>
           </form>
         </tr>
         <%} %>
       </tbody>
     </table>

		</div>
	</div>
</div>

</body>
</html>