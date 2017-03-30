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

/**
 * Servlet implementation class srvCompra
 */
@WebServlet("/srvCompra")
public class srvCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Controlador ctrl = new Controlador();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvCompra() {
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

		if(request.getParameter("eventSale")!=null){
			if(request.getSession().getAttribute("userSession")!=null){
				Item item = ctrl.getOneItem(Integer.parseInt(request.getParameter("idSelect")));
				request.getSession().setAttribute("item", item);
				request.getRequestDispatcher("elegido.jsp").forward(request, response);
			} else request.getRequestDispatcher("inicio.jsp").forward(request, response);
		}
		
		if(request.getParameter("addCarrito")!=null){
			ArrayList<VentaItem> carrito;
			if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")!=null) carrito = (ArrayList<VentaItem>)request.getSession().getAttribute("carrito");
				else carrito = new ArrayList<VentaItem>();
			VentaItem fila = new VentaItem();
			fila.setIdItem(((Item)request.getSession().getAttribute("item")).getId());
			fila.setCantidad(Integer.parseInt(request.getParameter("cmbCantidad")));
			carrito.add(fila);
			request.getSession().setAttribute("carrito", carrito);
			request.getRequestDispatcher("itemUser.jsp").forward(request, response);
		}
		
		if(request.getParameter("eventQuitarCarro")!=null){
			ArrayList<VentaItem> carrito = (ArrayList<VentaItem>)request.getSession().getAttribute("carrito");
			ArrayList<VentaItem> carrito_new = new ArrayList<VentaItem>();
			for(VentaItem i : carrito){
				if(i.getIdItem() != Integer.parseInt(request.getParameter("idSelected"))){
					carrito_new.add(i);
				}
			}
			request.getSession().setAttribute("carrito", carrito_new);
			request.getRequestDispatcher("compra.jsp").forward(request, response);
		}
		
		if(request.getParameter("eventConfirmar")!=null){
			Venta venta = new Venta(); Item item;
			Usuario user = (Usuario)request.getSession().getAttribute("userSession");
			ArrayList<VentaItem> ventaItem = (ArrayList<VentaItem>)request.getSession().getAttribute("carrito");
			double monto = 0;
			
			for(VentaItem vi : ventaItem){
				monto = monto + vi.getCantidad()*ctrl.getOneItem(vi.getIdItem()).getPrecio();
			}
			
			venta.setIdUsuario(user.getId());
			venta.setMontoTotal(monto);
			venta.setNroTarjeta(Integer.parseInt(request.getParameter("nroTarjeta")));
			venta.setTitularTarjeta(request.getParameter("titTarjeta"));
			
			ctrl.save(venta);
			venta.setId(ctrl.ultimaVenta());
			
			for(VentaItem vi : ventaItem){
				vi.setIdVenta(venta.getId());
				
				item = ctrl.getOneItem(vi.getIdItem());
				item.quitoStock(vi.getCantidad());
				item.setState(States.Modificacion);
				
				ctrl.save(item);
				ctrl.save(vi);
			}
			request.getSession().setAttribute("carrito", null);
			request.getRequestDispatcher("itemUser.jsp").forward(request, response);
		}
		
		
	}

}
