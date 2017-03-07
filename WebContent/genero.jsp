<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Genero" %>
<%@ page import="controlador.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/dashboard.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>G�neros</title>
</head>
<body>
<% Controlador ctrl = new  Controlador(); %>
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
            <li><a href="artista.jsp">Artistas</a></li>
            <li class="active"><a>G�neros<span class="sr-only">(current)</span></a></li>
            <li><a href="item.jsp">Discos</a></li>
            <li><a href="usuario.jsp">Usuarios</a></li>
            </ul>
        </div>
	</div>
</div>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

<h2 class="page-header">G�neros</h2>

	<form role="form" action="srvGenero" method="post" id="formBuscar" name="formBuscar">
		<div class="form-inline">
        	<input type="text" class="form-control" id="descSearch" name="descSearch" placeholder="�Qu� est� buscando?" size="60" maxlength="45" style="height:100">
			<input class="btn btn-primary" type="submit" value="Buscar" id="searchGenero" name="searchGenero"/>
		</div>
	</form>
	
	<br>
	
	<form role="form" action="srvGenero" method="post" id="formBuscarD" name="formBuscarD">
		<table>
			<tr>
				<td><b>C�digo:</b></td>
			<td> 
				 <div class = "form-inline">
					<input type="text" enabled="false" class="form-control" id="idGenero" name="idGenero" readonly value="<%if(request.getAttribute("idGenero")!=null){%><%=request.getAttribute("idGenero") %><% }%>"  size="43">
				 </div>
			</td>
		</tr>
		<tr>
			<td><b>Nombre:</b></td>
			<td><input type="text" class="form-control" id="descGenero" name="descGenero" value="<%if(request.getAttribute("descGenero")!=null){%><%=request.getAttribute("descGenero") %><% }%>"></td>
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
		<input class="btn btn-success" type="submit" value="Guardar" id="newGenero" name="newGenero"/>
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
       	<%
       		ArrayList<Genero> generos = new ArrayList<Genero>();
   	   		if(request.getAttribute("generos")==null)
       		generos = ctrl.GetAllHabilitados();
   	   		else
   	   		generos = (ArrayList<Genero>) request.getAttribute("generos"); int i = 0;
       		for(Genero genero : generos){  %>
         <tr>
           <td style="vertical-align:middle">
           		<input type="hidden" name="nro" id="nro" value="<%=genero.getId()%>"/><%=genero.getId()%>
           </td>
           <td style="vertical-align:middle">
           		<input type="hidden" name="desc" id="desc" value="<%=genero.getDescripcion()%>"/><%=genero.getDescripcion()%>
           </td>
           <form role="form" action="srvGenero" method="post" id="botonera" name="botonera">
           		<td style="vertical-align:middle">
           			<input type="hidden" name="descSelect" id="descSelect" value="<%=genero.getDescripcion()%>"/>
           			<input class="btn btn-success btn-sm" type="submit" value="Modificar" id="eventUpdate" name="eventUpdate" />
  					<input class="btn btn-danger btn-sm" type="submit" value="Eliminar" id="eventDelete" name="eventDelete" />
           		</td>
           </form>
         </tr>
         <% i++;} %>
       </tbody>
     </table>




</div>


</body>
</html>