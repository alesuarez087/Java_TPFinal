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
			<ul class="nav navbar-nav">
        	  	<li class="active"><a href="itemUser.jsp">Discos</a></li>
          		<%  if(user!=null) if(user.getTipoUsuario() == entidades.Usuario.TiposUsuario.Administrador){ %>
          		<li><a href="adminInicio.jsp">Editar</a></li> <% } else { %>
          		<li><a href="listCompras.jsp">Compras</a></li> <% } %> 
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
        	<li><a href="login.jsp">Iniciar Sesi�n</a></li> <% } %>
          	</ul>
          	<form action="srvItem" method="post" class="navbar-form navbar-right">
            	<input type="text" class="form-control" id="buscar" name="buscar" placeholder="Que est�s buscando?">
          	</form>
        </div>
	</div>
</nav>

<div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
          	<li><a>Seleccione B�squeda<span class="sr-only">(current)</span></a></li>
          	<li href="itemTop.jsp"><a>Inicio</a></li>
            <li><a href="itemForGenero.jsp">G�neros</a></li>
            </ul>
        </div>
	</div>
</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h3 class="page-header">Discos</h3>
				<div class="row placeholders">
				<% ArrayList<Item> listado = (ArrayList<Item>)request.getSession().getAttribute("listado"); 
				if(listado.size() != 0){
				for(Item item : listado){ %>
            		<div class="col-xs-6 col-sm-3 placeholder">
              			<img src="<%= item.getUrlPortada() %>" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
              			<h4><%= item.getTitulo() %></h4>
              			<span class="text-muted"><%= item.GetArtista().getNombre() %></span>
              			<h4>$<%=ctrl.getOnePrecioToday(item.getId()).getValor() %></h4>
					<form action="srvCompra" method="post" id="compra" name="compra">
              			<input type="hidden" name="idSelect" id="idSelect" value="<%=item.getId()%>"/>
           				<input class="btn btn-success btn-sm" type="submit" value="Agregar" id="eventSale" name="eventSale" />
           	  		</form>
            		</div>
            	<% } } else {%>
            		<h4>Su b�squeda no arroj� resultados </h4><%} %>
				</div>
	</div>
	
</body>
</html>