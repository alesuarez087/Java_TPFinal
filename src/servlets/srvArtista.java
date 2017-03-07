package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.ControladorArtista;
import entidades.Artista;
import entidades.Genero;
import entidades.Entidad.States;

/**
 * Servlet implementation class srvArtista
 */
@WebServlet("/srvArtista")
public class srvArtista extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	ControladorArtista ctrl = new ControladorArtista();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvArtista() {
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
		Artista artista = new Artista();
		
		
		if(request.getParameter("newArtista")!=null){
			if(request.getParameter("idArtista").equals("")){
				if(this.validArtista(request.getParameter("descArtista"))){
					artista.setNombre(request.getParameter("descArtista"));
					artista.setHabilitado(true);
					artista.setState(States.Alta);
					ctrl.Save(artista);
				} else{
					request.setAttribute("messageError", "Ya existe ese Artista");
				}
			} else { 
				artista.setState(States.Modificacion); 
				artista.setId(Integer.parseInt(request.getParameter("idArtista")));
				artista.setNombre(request.getParameter("descArtista"));
				artista.setHabilitado(true);
				request.getSession().setAttribute("FormSession", null);
				ctrl.Save(artista);
			}
			request.getRequestDispatcher("artista.jsp").forward(request, response);
			
				
		}
		
		if(request.getParameter("searchArtista")!=null){
			artista = ctrl.GetOne(request.getParameter("descSearch"));
			if(artista!=null){
				request.setAttribute("idArtista", artista.getId());
				request.setAttribute("descArtista", artista.getNombre());
				request.getSession().setAttribute("FormSession", "Modificacion");
				request.getRequestDispatcher("artista.jsp").forward(request, response);
			}
		}
		
		if(request.getParameter("eventUpdate")!=null){
			artista = ctrl.GetOne(request.getParameter("nombreSelect"));
			request.setAttribute("idArtista", artista.getId());
			request.setAttribute("descArtista", artista.getNombre());
			request.getSession().setAttribute("FormSession", "Modificacion");
			request.getRequestDispatcher("artista.jsp").forward(request, response);
		}
		
		if(request.getParameter("clearForm")!=null){
			request.setAttribute("idArtista", "");
			request.setAttribute("descArtista", "");
			request.getSession().setAttribute("FormSession", null);
			request.getRequestDispatcher("artista.jsp").forward(request, response);
		}
		
		if(request.getParameter("eventDelete")!=null){
			artista = ctrl.GetOne(request.getParameter("nombreSelect"));
			artista.setState(States.Baja);
			ctrl.Save(artista);
			request.getRequestDispatcher("artista.jsp").forward(request, response);
		}
	}

	private boolean validArtista(String desc){ 
		Artista art = ctrl.GetOne(desc);
		if(art != null) return false;
			else return true;
	}
}
