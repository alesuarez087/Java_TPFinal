package controlador;

import java.util.ArrayList;

import data.DataTipoUsuario;
import entidades.TipoUsuario;


public class ControladorTipoUsuario {

	public ArrayList<TipoUsuario> TipoUsuarioGetAll(){
		return DataTipoUsuario.GetAll();
	}
}
