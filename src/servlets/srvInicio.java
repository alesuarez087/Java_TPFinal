package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.ControladorInicio;
import entidades.Entidad.States;
import entidades.Usuario;
import entidades.Usuario.TiposUsuario;
import utils.Validate;

/**
 * Servlet implementation class srvInicio
 */
@WebServlet("/srvInicio")
public class srvInicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvInicio() {
        //super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario user = new Usuario();
		ControladorInicio ctrl = new ControladorInicio();
		if(request.getParameter("eventLogin")!=null){
			user = ctrl.Login(request.getParameter("userLogin"), request.getParameter("passLogin"));
			if(user!=null){
				request.getSession().setAttribute("userSession", user);
				
				// TENER EN CUENTA COMO PROSEGUIR DEPENDIENDO DE QUE TIPO DE USAURIO SEA 
				
				request.getRequestDispatcher("itemUser.jsp").forward(request, response);
				
				
				
			} else{
				request.setAttribute("messageError", "Usuario y/o Contraseña incorrecto");
				request.getRequestDispatcher("inicio.jsp").forward(request, response);
			}
		}
		
		if(request.getParameter("eventRegisterUser")!=null){
			user = ctrl.GetOne(request.getParameter("userCreate"));
			if(user == null){
				if(Validate.Email(request.getParameter("email"))){
					user = new Usuario();
					user.setDni(request.getParameter("email"));
					user.setNombreUsuario(request.getParameter("userCreate"));
					user.setApellido(request.getParameter("apellido"));
					user.setClave(request.getParameter("passCreate"));
					user.setEmail(request.getParameter("dni"));
					user.setHabilitado(true);
					user.setNombre(request.getParameter("nombre"));
					user.setTipoUsuario(TiposUsuario.Usuario);
					user.setState(States.Alta);
					ctrl.Save(user);
					request.setAttribute("messageError2", "Usuario Creado Correctamente");
					request.getRequestDispatcher("inicio.jsp").forward(request, response);
				}
				else{
					request.setAttribute("messageError2", "Email inválido");
					request.getRequestDispatcher("inicio.jsp").forward(request, response);
				} 
			} else{
				request.setAttribute("messageError2", "Ya existe un Usuario con ese nombre");
				request.getRequestDispatcher("inicio.jsp").forward(request, response);
			}
		}
		
		if(request.getParameter("logout")!=null){
			request.getSession().setAttribute("userSession", null);
			request.getRequestDispatcher("itemUser.jsp").forward(request, response);;
		}
		
	}
}
