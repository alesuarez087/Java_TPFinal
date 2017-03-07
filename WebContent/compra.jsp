<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="controlador.Controlador" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>TiendaCD</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/dashboard.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<% Controlador ctrl = new Controlador(); %>

<body>
   <nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">Luzbelito</a>
			</div>
		<div>
        <ul class="nav navbar-nav">
        <li><a href="itemUser.jsp">Discos</a></li>
		<% 	if(user != null){ int nro=0;
			if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito") != null){ 
           		nro = ((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")).size();
           	} else nro = 0; %>
        <li class="active"><a>Carrito de compras <span clase="badge">(<%=nro %>)</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right"> 
			<li><a href="inicio.jsp">Cerrar Sesión</a></li>
        </ul>
      </div>
    </div>
  </nav>
  
<div class="col-sm-9 col-md-10">

<h2 class="page-header">Carrito de Compras</h2>
<table class="table table-hover">
       <thead> 
         <tr> 
           <th>Título</th> 
           <th>Autor</th>
           <th>Año Lanzamiento</th>
           <th>Género</th>
           <th>Precio</th>
           <th>Cantidad</th>
           <th>Quitar del carro</th>
         </tr> 
       </thead>
       <tbody>
       <%
       ArrayList<VentaItem> carrito = (ArrayList<VentaItem>) request.getSession().getAttribute("carrito");
       double monto = 0;
       if(carrito != null){
       for(VentaItem vi : carrito){
       		Item item = ctrl.GetOne(vi.getIdItem());
       		monto = monto + item.getPrecio()*vi.getCantidad();
       %>
         <tr>
           <td style="vertical-align:middle"><%=item.getTitulo()%></td>
           <td style="vertical-align:middle"><%=item.GetArtista().getNombre() %></td> 
           <td style="vertical-align:middle"><%=item.getAnioLanzamiento() %></td>
           <td style="vertical-align:middle"><%=item.GetGenero().getDescripcion() %></td>
           <td style="vertical-align:middle">$<%=item.getPrecio() %></td>
           <td style="vertical-align:middle"><%= vi.getCantidad() %></td>    
           <td style="vertical-align:middle">
           		<form role="form" action="srvCompra" method="post" id="eliminar" name="eliminar">
           		<input type="hidden" name="idSelected" id="idSelected" value="<%=item.getId()%>"/>
  				<input class="btn btn-danger btn-sm" type="submit" value="Eliminar" id="eventQuitarCarro" name="eventQuitarCarro" />
           	</form>
           </td> 
         </tr>
         <% } } %>
       </tbody>
     </table>
	<div class="col-lg-8 col-lg-offset-1" style="background-color:#ccc">
 		<div class="container">
  		<h3>Total: $<%=monto %></h3> 
   	</div>
   	<form role="form" action="srvCompra" method="post" id="confirmar" name="confirmar">
   	<table align="center" >
		<tr>
			<td><b>Número de Tarjeta</b></td>
			<td> 
				<input type="text" enabled="false" class="form-control" id="nroTarjeta" name="nroTarjeta" value="<%if(request.getAttribute("nroTarjeta")!=null){%><%=request.getAttribute("nroTarjeta") %><% }%>"  size="43">
			</td>
		</tr>
		<tr>
			<td><b>Titular Tarjeta</b></td>
			<td><input type="text" class="form-control" id="titTarjeta" name="titTarjeta" value="<%if(request.getAttribute("titTarjeta")!=null){%><%=request.getAttribute("titTarjeta") %><% }%>"></td>
		</tr>
		<tr>
			
		</tr>
	</table>
   	
   	
   		<input class="btn btn-success" type="submit" value="Confirmar Compra" id="eventConfirmar" name="eventConfirmar" />  		
	</form>
	

</div>
</body>
</html>