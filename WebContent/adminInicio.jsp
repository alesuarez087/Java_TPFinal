<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.*" %>
<%@ page import="controlador.*" %>
<%@ page import="utils.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Usuario user = (Usuario)request.getSession().getAttribute("userSession");
	if(user.getTipoUsuario() == Usuario.TiposUsuario.Administrador){ 
 %>
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
<script>
function valida(){
	if(formTabla.descArtista.value == ''){alert('Ingrese de Nombre de Artista'); return false;}
	else return true
}
</script>
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
          <li><a href="itemTop.jsp">Discos</a></li> 
          <li class="active"><a href="adminInicio">Editar</a></li>
          </ul>
        <ul class="nav navbar-nav navbar-right">
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
          <% if(user.getTipoUsuario() == Usuario.TiposUsuario.Administrador){ %>
            <li><a href="adminArtista.jsp">Artistas</a></li>
            <li><a href="adminGenero.jsp">Géneros</a></li>
            <li><a href="adminItem.jsp">Discos</a></li>
            <li><a href="adminUsuario.jsp">Usuarios</a></li>
            <% } %>
			<li><a href="adminStockPrecio.jsp">Remarcar</a></li>
        </div>
	</div>
</div>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

</div>
</body>
<%
	} else { if(user.getTipoUsuario() == null) {
				response.sendRedirect("login.jsp");
			} else { response.sendRedirect("itemTop.jsp");
				}
	}
%>
</html>