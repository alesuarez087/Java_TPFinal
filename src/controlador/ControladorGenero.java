package controlador;

import java.util.ArrayList;
import entidades.Genero;
import data.DataGenero;

public class ControladorGenero {
	public ArrayList<Genero> GetAll(){
		return DataGenero.GetAll();
	}
	
	public Genero GetOne(String desc){
		return DataGenero.GetOne(desc);
	}
	
	public void Save(Genero gen){
		DataGenero.Save(gen);
	}
}
