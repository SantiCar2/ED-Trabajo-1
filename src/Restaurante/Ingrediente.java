package Restaurante;

public class Ingrediente {

	String nombre; 
	int cantidad;
	String id;
	
	//Constructor
	public Ingrediente(String nombre, int cantidad, String id) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.id = id;
	}

	
	//getters and setters
	public String getNombre() {
		return nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getId() {
		return id;
	}


	
	
	
	
}
