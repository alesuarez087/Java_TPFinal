package entidades;

import java.time.*;

public class Item extends Entidad {
	enum TiposDisco{ CD, DVD, Vinilo, Pasta, BlueRay}
	
	private double precioTotal;
	private int stock;
	private String titulo;
	private String anioLanzamiento;
	private LocalTime duracion;
	private TiposDisco tipoDisco;
	private int idArtista;
	private int idGenero;
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAnioLanzamiento() {
		return anioLanzamiento;
	}
	public void setAnioLanzamiento(String anioLanzamiento) {
		this.anioLanzamiento = anioLanzamiento;
	}
	public LocalTime getDuracion() {
		return duracion;
	}
	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}
	public TiposDisco getTipoDisco() {
		return tipoDisco;
	}
	public void setTipoDisco(TiposDisco tipoDisco) {
		this.tipoDisco = tipoDisco;
	}
	public int getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
}
