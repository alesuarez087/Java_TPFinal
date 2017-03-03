package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.ControladorGenero;
import entidades.Entidad.States;
import entidades.Genero;

/**
 * Servlet implementation class srvGenero
 */
@WebServlet("/srvGenero")
public class srvGenero extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ControladorGenero ctrl = new ControladorGenero();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvGenero() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Genero genero = new Genero();
		
		
		if(request.getParameter("newGenero")!=null){
			if(request.getSession().getAttribute("FormSession") == null){
				if(this.validDescripcion(request.getParameter("descGenero"))){
					genero.setDescripcion(request.getParameter("descGenero"));
					genero.setHabilitado(true);
					genero.setState(States.Alta);
					ctrl.Save(genero);
				} else{
					request.setAttribute("messageError", "Ya existe ese Género");
				}
			} else { 
				genero.setState(States.Modificacion); 
				genero.setId(Integer.parseInt(request.getParameter("idGenero")));
				genero.setDescripcion(request.getParameter("descGenero"));
				genero.setHabilitado(true);
				request.getSession().setAttribute("FormSession", null);
				ctrl.Save(genero);
			}
			request.getRequestDispatcher("genero.jsp").forward(request, response);
			
				
		}
		
		if(request.getParameter("searchGenero")!=null){
			genero = ctrl.GetOne(request.getParameter("descSearch"));
			if(genero!=null){
				request.setAttribute("idGenero", genero.getId());
				request.setAttribute("descGenero", genero.getDescripcion());
				request.getSession().setAttribute("FormSession", "Modificacion");
				request.getRequestDispatcher("genero.jsp").forward(request, response);
			}
		}
	}
	
	
	
	private boolean validDescripcion(String desc){ 
		Genero gen = ctrl.GetOne(desc);
		if(gen != null) return false;
			else return true;
	}
}
