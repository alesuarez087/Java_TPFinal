<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="controlador.Controlador" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<%Controlador ctrl = new Controlador(); %>
	<%Usuario user = (Usuario)request.getSession().getAttribute("userSession"); %>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
			<ul class="nav navbar-nav">
        	  	<li class="active"><a href="itemUser.jsp">Discos</a></li>
          		<%  if(user!=null) if(user.getTipoUsuario() == entidades.Usuario.TiposUsuario.Administrador){ %>
          		<li><a href="item.jsp">Editar</a></li> <% } else { %>
          		<li><a href="compras.jsp">Compras</a></li> <% } %> 
        	</ul> 
		</div>
        <div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
           	<% 	if(user != null){ int nro=0;
			if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito") != null){ 
           		nro = ((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")).size();
           	} else nro = 0; %>
        	<li><a href="carrito.jsp">
        	<img alt="Brand" src="bootstrap/img/carrito25.png"> Carrito de compras <span clase="badge">(<%=nro %>)</a></li> 

        	<% } %>
        	<%if(user != null){	%>
        	<form action="srvInicio" method="post" id="cerrar" name="cerrar">        	
        		<li><button class="btn btn-default navbar-btn" id="logout" name="logout">Cerrar Sesi�n</button></li>
        	</form>
        	<% } else { %>
        	<li><a href="inicio.jsp">Iniciar Sesi�n</a></li> <% } %>
          	</ul>
          	<form action="srvItem" method="post" class="navbar-form navbar-right">
            	<input type="text" class="form-control" id="buscar" name="buscar" placeholder="Que est�s buscando?">
          	</form>
        </div>
	</div>
</nav>

</body>
</html>