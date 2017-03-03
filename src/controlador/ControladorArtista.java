package controlador;

import java.util.ArrayList;
import data.DataArtista;
import entidades.Artista;

public class ControladorArtista {

	public ArrayList<Artista> GetAll(){
		return DataArtista.GetAll();
	}
	
	public Artista GetOne(String desc){
		return DataArtista.GetOne(desc);
	}
	
	public void Save(Artista artista){
		DataArtista.Save(artista);
	}
}
