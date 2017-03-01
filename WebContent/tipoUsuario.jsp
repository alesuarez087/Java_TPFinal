 	<%@ page import="java.util.ArrayList" %>
    <%@ page import="controlador.ControladorTipoUsuario" %>
    <%@ page import="entidades.TipoUsuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>

    <title>Tipo Usuario</title>

  </head>

  <body>
        <div>
          <h1>Dashboard</h1>
        	<table>
        		<thead>
        		<tr>
        			<th>ID</th>
        			<th>Descripcion</th>
        		</tr>
        		</thead>
        		<tbody>
        			<%
        				ControladorTipoUsuario ctrl = new ControladorTipoUsuario();
        				ArrayList<TipoUsuario> list = ctrl.TipoUsuarioGetAll();
        				for(TipoUsuario tipo : list){ %>
				<tr>
					<td><%=tipo.getId() %></td>
					<td><%=tipo.getDescripcion() %></td>
				</tr>        				
        			<%	}
        			%>
        		</tbody>
        	</table>  
        </div>



  </body>
</html>
