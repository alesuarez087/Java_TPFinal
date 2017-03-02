package controlador;

import java.util.ArrayList;

import data.DataUsuario;
import entidades.Usuario;

public class ControladorInicio {

	public ArrayList<Usuario> GetAll(){
		return DataUsuario.GetAll();
	}
	
	public Usuario Login(String user, String pass){
		return DataUsuario.Login(user, pass);
	}
	
	public Usuario GetOne(String userName){
		return DataUsuario.GetOne(userName);
	}
	
	public void Save(Usuario user){
		DataUsuario.Save(user);
	}
}
