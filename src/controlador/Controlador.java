package controlador;

import java.util.ArrayList;
import data.*;
import entidades.*;

public class Controlador {
	
	//FORMATO: funcion + Clase (+ Hab) 
	//ejemplo: getAllArtista() --> devuelve todos los artistas
	// 		   getAllArtistaHab() --> devuelve todos los artistas que no fueron "eliminados"
	//		   getAllVentaForUsuario() --> devuelve todas las ventas correspondiente a un usuario
	
	//ARTISTAS
	public ArrayList<Artista> getAllArtista(){
		return DataArtista.GetAll();
	}
	
	public ArrayList<Artista> getAllArtistaHab(){
		return DataArtista.GetAllHabilitados();
	}
	
	public Artista getOneArtista(String desc){
		return DataArtista.GetOne(desc);
	}
	
	public Artista getOneArtista(int idArtista){
		return DataArtista.GetOne(idArtista);
	}
	
	public void save(Artista artista){
		DataArtista.Save(artista);
	}
	
	//GENEROS
	public ArrayList<Genero> getAllGenero(){
		return DataGenero.GetAll();
	}
	public ArrayList<Genero> getAllGeneroHab(){
		return DataGenero.GetAllHabilitados();
	}
	public Genero getOneGenero(String desc){
		return DataGenero.GetOne(desc);
	}
	
	public Genero getOneGenero(int idGenero){
		return DataGenero.GetOne(idGenero);
	}
	
	public void save(Genero gen){
		DataGenero.Save(gen);
	}
	
	//ITEMS
	public ArrayList<Item> getAllItem(){
		return DataItem.GetAll();
	}
	public ArrayList<Item> getAllItemForArtista(String artista){
		return DataItem.ItemsGetAllForArtista(artista);
	}
	public ArrayList<Item> getAllItemForGenero(String genero){
		return DataItem.ItemsGetAllForGenero(genero);
	}
	public Item getOneItem(int idItem){
		return DataItem.GetOne(idItem);
	}
	public void save(Item item){
		DataItem.Save(item);
	}

	//VENTA
	public ArrayList<Venta> getAllVenta(){
		return DataVenta.GetAll();
	}
	public ArrayList<Venta> getAllVentaForUser(int idUsuario){
		return DataVenta.VentasGetAllForUser(idUsuario); 
	}
	public void save(Venta venta){
		DataVenta.Insert(venta);
	}
	public int ultimaVenta(){
		return DataVenta.ultimaVenta();
	}
	
	//VENTAITEM
	public void save(VentaItem ventaItem){
		DataVentaItem.Insert(ventaItem);
	}
	public ArrayList<VentaItem> getAllVentaItem(int idUsuario){
		return DataVentaItem.GetAll(idUsuario);
	}
	public VentaItem getOneVentaItem(int idVentaItem){
		return DataVentaItem.GetOne(idVentaItem);
	}
	public ArrayList<VentaItem> getAllVentaVentaItem(int idVenta){
		return DataVentaItem.GetAll(idVenta);
	}
	
	//CLASIFICACION
	public ArrayList<Clasificacion> getAllClasificacion(int idItem){
		return DataClasificacion.GetAll(idItem);
	}
	public void save(Clasificacion clas){
		DataClasificacion.Save(clas);
	}
	public Clasificacion getOneClasificacion(int idItem, int idUsuario){
		return DataClasificacion.GetOne(idItem, idUsuario);
	}
	public int getPromedio(int idItem){
		return DataClasificacion.GetPromedio(idItem);
	}
	//TIPOS DE USUARIO
	public ArrayList<TipoUsuario> getAllTipoUsuario(){
		return DataTipoUsuario.GetAll();
	}
	
	//USUARIO
	public ArrayList<Usuario> getAllUsuario(){
		return DataUsuario.GetAll();
	}
	public Usuario getOneUsuario(int idUsuario){
		return DataUsuario.GetOne(idUsuario);
	}
	public void save(Usuario user){
		DataUsuario.Save(user);
	}
}
