package entidades;

public class Clasificacion extends Entidad {
	private int valor;
	private String detalles;
	private int idUsuario;
	private int idVentaItem;
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdVentaItem() {
		return idVentaItem;
	}
	public void setIdVentaItem(int idVentaItem) {
		this.idVentaItem = idVentaItem;
	} 
	
	
}
