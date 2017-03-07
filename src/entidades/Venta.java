package entidades;

import java.util.Date;

public class Venta extends Entidad {
	private double montoTotal;
	private String titularTarjeta;
	private int nroTarjeta;
	private int idUsuario;
	private Date fecha;
	
	public double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getTitularTarjeta() {
		return titularTarjeta;
	}
	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}
	public int getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(int nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
