package controlador;

import java.util.ArrayList;

import data.*;
import entidades.*;

public class Controlador {
	
	//ARTISTAS
	public ArrayList<Artista> ArtistaGetAll(){
		return DataArtista.GetAll();
	}
	
	public Artista ArtistaGetOne(String desc){
		return DataArtista.GetOne(desc);
	}
	
	public void Save(Artista artista){
		DataArtista.Save(artista);
	}
	
	//GENEROS
	public ArrayList<Genero> GeneroGetAll(){
		return DataGenero.GetAll();
	}
	public ArrayList<Genero> GetAllHabilitados(){
		return DataGenero.GetAllHabilitados();
	}
	public Genero GetOne(String desc){
		return DataGenero.GetOne(desc);
	}
	
	public void Save(Genero gen){
		DataGenero.Save(gen);
	}
	
	//ITEMS
	public ArrayList<Item> ItemsGetAll(){
		return DataItem.GetAll();
	}
	public ArrayList<Item> ItemsGetAllForArtista(String artista){
		return DataItem.ItemsGetAllForArtista(artista);
	}
	public ArrayList<Item> ItemsGetAllForGenero(String genero){
		return DataItem.ItemsGetAllForGenero(genero);
	}
	public Item GetOne(int idItem){
		return DataItem.GetOne(idItem);
	}
	public void Save(Item item){
		DataItem.Save(item);
	}

	//VENTA
	public ArrayList<Venta> VentasGetAll(){
		return DataVenta.GetAll();
	}
	public void Save(Venta venta){
		DataVenta.Insert(venta);
	}
	public int UltimaVenta(){
		return DataVenta.ultimaVenta();
	}
	
	//VENTAITEM
	public void Save(VentaItem ventaItem){
		DataVentaItem.Insert(ventaItem);
	}
	public ArrayList<VentaItem> VentaItemGetAll(int idUsuario){
		return DataVentaItem.GetAll(idUsuario);
	}
	public VentaItem VentaItemGetOne(int idVentaItem){
		return DataVentaItem.GetOne(idVentaItem);
	}
	
}
