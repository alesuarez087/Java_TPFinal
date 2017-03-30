package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Controlador;
import entidades.*;
import entidades.Entidad.States;
import entidades.Item.TiposDisco;
import utils.Validate;

/**
 * Servlet implementation class srvItem
 */
@WebServlet("/srvItem")
public class srvItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	Controlador ctrl = new Controlador();
	Item item;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Usuario user = (Usuario)request.getSession().getAttribute("userSession");
		

			if(request.getParameter("saveItem")!=null){
				if(request.getParameter("idItem").equals("")){
					item = new Item();
					item.setState(States.Alta);
				} else{
					request.getSession().setAttribute("FormSession", null);
					item = ctrl.getOneItem(Integer.parseInt(request.getParameter("idItem")));
					item.setState(States.Modificacion);
				}
			
				item.setTitulo(request.getParameter("tituloItem"));
				item.setAnioLanzamiento(request.getParameter("anioLanzamiento"));
				item.setHabilitado(true);
				item.setPrecio(Double.parseDouble(request.getParameter("precioItem")));
				item.setStock(Integer.parseInt(request.getParameter("stock")));
				item.setUrlPortada(request.getParameter("urlPortada"));
			
				Artista art = ctrl.getOneArtista(request.getParameter("cmbArtista"));
				Genero gen = ctrl.getOneGenero(request.getParameter("cmbGenero"));
			
				item.setIdArtista(art.getId());
				item.setIdGenero(gen.getId());
			
				switch(request.getParameter("cmbTipoDisco")){
					case "BlueRay": item.setTipoDisco(TiposDisco.BlueRay); break;
					case "CD": item.setTipoDisco(TiposDisco.CD); break;
					case "DVD": item.setTipoDisco(TiposDisco.DVD); break;
					case "Pasta": item.setTipoDisco(TiposDisco.Pasta); break;
					case "Vinilo": item.setTipoDisco(TiposDisco.Vinilo); break;
				}
				
				if(item.getState() == States.Alta){
					if(Validate.ArtistaItem(request.getParameter("tituloItem"), request.getParameter("artistaItem"))){
						ctrl.save(item);
					} else {
							request.setAttribute("messageError", "Ya fue creado este disco");
						}
				} else ctrl.save(item);
				request.getRequestDispatcher("item.jsp").forward(request, response);
			}
		
			if(request.getParameter("eventUpdate")!=null){
				Item item = ctrl.getOneItem(Integer.parseInt(request.getParameter("idSelect")));
			
				request.setAttribute("idItem", item.getId());
				request.setAttribute("tituloItem", item.getTitulo());
				request.setAttribute("anioLanzamiento", item.getAnioLanzamiento());
				request.setAttribute("precioItem", item.getPrecio());
				request.setAttribute("stock", item.getStock());
			
				request.setAttribute("artistaItem", item.GetArtista().getNombre());
				request.setAttribute("generoItem", item.GetGenero().getDescripcion());
				request.setAttribute("urlPortada", item.getUrlPortada());
				switch(item.getTipoDisco()){
					case BlueRay: request.setAttribute("tipoDisco", "BlueRay"); break;
					case CD: request.setAttribute("tipoDisco", "CD"); break;
					case DVD: request.setAttribute("tipoDisco", "DVD"); break;
					case Pasta: request.setAttribute("tipoDisco", "Pasta"); break;
					case Vinilo: request.setAttribute("tipoDisco", "Vinilo"); break;
				}
			
				request.getSession().setAttribute("FormSession", "Modificacion");
				request.getRequestDispatcher("item.jsp").forward(request, response);
			}
		
			if(request.getParameter("eventDelete")!=null){
				Item item = new Item();
				item = ctrl.getOneItem(Integer.parseInt(request.getParameter("idSelect")));
				if(item!=null){
					item.setState(States.Baja);
					ctrl.save(item);
				}
				request.getSession().setAttribute("FormSession", null);
				request.getRequestDispatcher("item.jsp").forward(request, response);
			}
			
			if(request.getParameter("filtroGenero")!=null){
				ArrayList<Item> listado = ctrl.getAllItemForGenero(request.getParameter("cmbGenero"));
				request.getSession().setAttribute("listado", listado);
				request.getRequestDispatcher("itemGenero.jsp").forward(request, response);
			}
		}


			
	
}

