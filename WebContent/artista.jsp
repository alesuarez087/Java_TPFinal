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
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Géneros</title>
</head>
<body>
<% ControladorArtista ctrl = new  ControladorArtista(); %>
<nav class="navbar navbar-inverse navbar-fixed-top">
     <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand">TiendaCD</a>
      </div>
      <div>
        <ul class="nav navbar-nav">
          <li><a href="discosAdmin.jsp">Discos</a></li> 
          <li><a href="autoresAdmin.jsp">Autores</a></li>
          <li class="active"><a href="generosAdmin.jsp">Géneros</a></li>
          <li><a href="descuentosAdmin.jsp">Descuentos</a></li>          
          <li><a href="usuariosAdmin.jsp">Usuarios</a></li>
        </ul>
      </div>
    </div>
</nav>

<div class="container" style="background-color:#aaa">
  <br><br><br>
  <div class="container">  
	<div align="center">
	  <form role="form" action="srvArtista" method="post" id="formBuscar" name="formBuscar">
      <div class="form-inline">
        <input type="text" class="form-control" id="descSearch" name="descSearch" placeholder="¿Qué está buscando?" size="60" maxlength="45" style="height:100">
  		<input class="btn btn-primary" type="submit" value="Buscar" id="searchArtista" name="searchArtista"/>
  	  </div>
    </form>
    </div>
    <br>
    <div class="col-sm-8 col-sm-offset-2" style="background-color:#ffffff">
  	  <br>
  	  <form role="form" action="srvArtista" method="post" id="formBuscarD" name="formBuscarD">
  	  <table align="center" style="background-color:#ffffff" >
		<tr>
			<td><b>Código:</b></td>
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
    </div>
    </form>
	<br>
  </div>
  </div>
  <br>

  <div class="col-sm-11.5 col-sm-offset-0.5" style="background-color:#ccc">
 	<div class="container">
  	<h3>Artistas</h3>
   	</div>
  </div>
  <div class="col-sm-11.5 col-sm-offset-0.5">
     <table class="table table-hover" style="background-color:#ffffff">
       <thead> 
         <tr> 
           <th>Código</th>
           <th>Nombre</th> 
         </tr> 
       </thead>
       <tbody>
       	<%
       		for(Artista artista : ctrl.GetAll()){  %>
         <tr>
           <td><%=artista.getId()%></td>
           <td><%=artista.getNombre()%></td>
         </tr>
         <%} %>
       </tbody>
     </table>
  </div>

</div>

</body>
</html>