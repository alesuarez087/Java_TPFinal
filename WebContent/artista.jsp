<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Artista" %>
<%@ page import="controlador.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artistas</title>
</head>
<body>

	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/dashboard.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artistas</title>
</head>
<body>
<% ControladorArtista ctrl = new  ControladorArtista(); %>
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
        	<li><a href="srvInicio.jps">Cerrar Sesi�n</a>
        </ul>
      </div>
    </div>
</nav>

<div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a>Artistas <span class="sr-only">(current)</span></a></li>
            <li><a href="genero.jsp">G�neros</a></li>
            <li><a href="item.jsp">Discos</a></li>
            <li><a href="usuario.jsp">Usuarios</a></li>
            </ul>
        </div>
	</div>
</div>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

<h2 class="page-header">Artistas</h2>
<form role="form" action="srvArtista" method="post" id="formBuscar" name="formBuscar">
      <div class="form-inline">
        <input type="text" class="form-control" id="descSearch" name="descSearch" placeholder="�Qu� est� buscando?" size="60" maxlength="45" style="height:100">
  		<input class="btn btn-primary" type="submit" value="Buscar" id="searchArtista" name="searchArtista"/>
  	  </div>
    </form>
    
<br>
  	  <form role="form" action="srvArtista" method="post" id="formBuscarD" name="formBuscarD">
  	  <table>
		<tr>
			<td><b>C�digo:</b></td>
			<td> 
				 <div class = "form-inline">
					<input type="text" enabled="false" class="form-control" id="idArtista" name="idArtista" readonly value="<%if(request.getAttribute("idArtista")!=null){%><%=request.getAttribute("idArtista") %><% }%>"  size="43">
				 </div>
			</td>
		</tr>
		<tr>
			<td><b>Nombre:</b></td>
			<td><input type="text" class="form-control" id="descArtista" name="descArtista" value="<%if(request.getAttribute("descArtista")!=null){%><%=request.getAttribute("descArtista") %><% }%>"></td>
		</tr>
		<tr>
			<td colspan="2">
				<%if (request.getAttribute("messageError")!=null){ %> <font color="#FF0000"> <%=request.getAttribute("messageError") %></font><%} %>
      		</td>
      		<td>
      		</td>
		</tr>
	</table>
	
	<br>
	
	
	<div align="center">
	<input class="btn btn-success" type="submit" value="Guardar" id="newArtista" name="newArtista"/>
	<input class="btn btn-default" type="submit" value="Cancelar" id="clearForm" name="clearForm"/>
    </div>
    </form>
    
    <br><br><br>
    
    <table class="table table-hover">
       <thead> 
         <tr> 
           <th>C�digo</th>
           <th>Nombre</th>
           <th></th> 
         </tr> 
       </thead>
       <tbody>
       	<% for(Artista artista : ctrl.GetAll()){  %>
         <tr>
           <td style="vertical-align:middle">
           		<input type="hidden" name="nro" id="nro" value="<%=artista.getId()%>" /><%=artista.getId()%>
           </td>
           <td style="vertical-align:middle">
           		<input type="hidden" name="desc" id="desc" value="<%=artista.getNombre()%>"/><%=artista.getNombre()%>
           </td>
           <form role="form" action="srvArtista" method="post" id="botonera" name="botonera">
           		<td style="vertical-align:middle">
           			<input type="hidden" name="nombreSelect" id="nombreSelect" value="<%=artista.getNombre()%>"/>
           			<input class="btn btn-success btn-sm" type="submit" value="Modificar" id="eventUpdate" name="eventUpdate" />
  					<input class="btn btn-danger btn-sm" type="submit" value="Eliminar" id="eventDelete" name="eventDelete" />
           		</td>
           </form>
         </tr>
         <%} %>
       </tbody>
     </table>

</body>
</html>