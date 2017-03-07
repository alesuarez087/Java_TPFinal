package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Controlador;
import entidades.Artista;
import entidades.Genero;
import entidades.Item;
import entidades.Item.TiposDisco;
import entidades.Usuario;
import entidades.Usuario.TiposUsuario;
import entidades.Entidad.States;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		if(request.getParameter("eventoBuscar")!=null){
			ArrayList<Item> items = ctrl.ItemsGetOne(request.getParameter("titBuscar"));
			request.setAttribute("items", items);
			request.getRequestDispatcher("item.jsp").forward(request, response);
		}
		
		if(request.getParameter("buscarDisco")!=null){
			Disco disco = new Disco();
			int codigo = Integer.parseInt(request.getParameter("codigoDisco"));
			disco = con.buscarDisco(codigo);
			if(disco!=null){
				request.setAttribute("tituloDisco", disco.getTitulo());
				request.setAttribute("codigoDisco", disco.getCodDisco());
				request.setAttribute("precioDisco", disco.getPrecio());
				request.setAttribute("añoDisco", disco.getAñoLanzamiento());
				request.setAttribute("stockDisco", disco.getCantCopiasDisp());
				request.setAttribute("autorDisco", disco.getAutor().getNombreAutor());
				request.setAttribute("generoDisco", disco.getGenero().getDescGenero());
			}				
			request.getRequestDispatcher("discosAdmin.jsp").forward(request, response);
		}*/
		Usuario user = (Usuario)request.getSession().getAttribute("userSession");
		if(user != null){
		if(user.getTipoUsuario() == TiposUsuario.Administrador){

			if(request.getParameter("saveItem")!=null){
				if(request.getParameter("idItem").equals("")){
					item = new Item();
					item.setState(States.Alta);
				} else{
					request.getSession().setAttribute("FormSession", null);
					item = ctrl.GetOne(Integer.parseInt(request.getParameter("idItem")));
					item.setState(States.Modificacion);
				}
			
				item.setTitulo(request.getParameter("tituloItem"));
				item.setAnioLanzamiento(request.getParameter("anioLanzamiento"));
				item.setHabilitado(true);
				item.setPrecio(Double.parseDouble(request.getParameter("precioItem")));
				item.setStock(Integer.parseInt(request.getParameter("stock")));
				item.setUrlPortada(request.getParameter("urlPortada"));
			
				Artista art = ctrl.ArtistaGetOne(request.getParameter("cmbArtista"));
				Genero gen = ctrl.GetOne(request.getParameter("cmbGenero"));
			
				item.setIdArtista(art.getId());
				item.setIdGenero(gen.getId());
			
				/*if(request.getParameter("tipoDisco")!=null){*/
					switch(request.getParameter("cmbTipoDisco")){
						case "BlueRay": item.setTipoDisco(TiposDisco.BlueRay); break;
						case "CD": item.setTipoDisco(TiposDisco.CD); break;
						case "DVD": item.setTipoDisco(TiposDisco.DVD); break;
						case "Pasta": item.setTipoDisco(TiposDisco.Pasta); break;
						case "Vinilo": item.setTipoDisco(TiposDisco.Vinilo); break;
					}/*
				} else item.setTipoDisco(ctrl.GetOne(Integer.parseInt(request.getParameter("idItem"))).getTipoDisco());*/
			
			
			
			
				if(item.getState() == States.Alta){
					if(this.validArtistaItem(request.getParameter("tituloItem"), request.getParameter("artistaItem"))){
						ctrl.Save(item);
					} else {
						//MENSAJE DE ERROR
					}
				} else{
					ctrl.Save(item);
					request.getSession().setAttribute("FormSession", "");
				}
				request.getRequestDispatcher("item.jsp").forward(request, response);
			}
		
			if(request.getParameter("eventUpdate")!=null){
				Item item = ctrl.GetOne(Integer.parseInt(request.getParameter("idSelect")));
			
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
				item = ctrl.GetOne(Integer.parseInt(request.getParameter("idSelect")));
				if(item!=null){
					item.setState(States.Baja);
					ctrl.Save(item);
				}
				request.getSession().setAttribute("FormSession", null);
				request.getRequestDispatcher("item.jsp").forward(request, response);
			}
		} else if(user.getTipoUsuario() == TiposUsuario.Empleado){
			
		} else {
			request.getRequestDispatcher("itemUser.jsp").forward(request, response);
		}
		}else {
			if(request.getParameter("filtroGenero")!=null){
				ArrayList<Item> listado = ctrl.ItemsGetAllForGenero(request.getParameter("cmbGenero"));
				request.getSession().setAttribute("listado", listado);
				request.getRequestDispatcher("itemGenero.jsp").forward(request, response);
		}
		}
		
	}


			
	private boolean validArtistaItem(String titulo, String artista){
		boolean valid = true;
		for(Item item : ctrl.ItemsGetAllForArtista(artista)){
			if(item.getTitulo().equals(titulo)) valid = false;
		}
		return valid;
	}
}

