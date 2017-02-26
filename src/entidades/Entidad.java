package entidades;

public class Entidad {

	public enum States{Alta, Baja, Consulta, Modificacion}
	
	private int id;
	private boolean habilitado;
	private States saste;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public States getSaste() {
		return saste;
	}
	public void setSaste(States saste) {
		this.saste = saste;
	}

}
