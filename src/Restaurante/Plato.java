package Restaurante;

import java.util.Arrays;

import Excepciones.EIngredienteExiste;

public class Plato {

	String nombre; 
	String tipoPlato; 
	double precio; 
	String id; 
	Ingrediente[] ingredientes = new Ingrediente[0];
	boolean disponibilidad;
	
	//constructor
	public Plato(String nombre, String tipoPlato, double precio, String id, Ingrediente[] ingredientes) {
		this.nombre = nombre;
		this.tipoPlato = tipoPlato;
		this.precio = precio;
		this.id = id;
		this.ingredientes = ingredientes;
		this.disponibilidad = true;
	}

	
	//agregar ingrediente
	public void agregarIngrediente(Ingrediente ingrediente) throws EIngredienteExiste   {
			
			if(buscarIngrediente(ingrediente.getId())==-1) {
				ingredientes = Arrays.copyOf(ingredientes, ingredientes.length+1);
				ingredientes[ingredientes.length-1]= ingrediente;
			}
			else {
				throw new EIngredienteExiste("El ingrediente " +ingrediente.getId() + " que intenta agregar ya existe" );
			}

	}
	
	//buscar ingrediente
	public int buscarIngrediente(String idIngrediente) {
		int i=0;
		
		while(i<ingredientes.length && !idIngrediente.equals(ingredientes[i].getId())) {
			i=i+1;
		}
		
		if (i<ingredientes.length) {
			return i;
		}
		else 
			return -1;
	}
	
	//eliminar ingrediente 
	public void eliminarIngrediente(Ingrediente ingrediente) throws EIngredienteExiste {
		int i = buscarIngrediente(ingrediente.getId());
		
		if(i!=-1) {
			ingredientes[i]=null;
			ingredientes[i]=ingredientes[ingredientes.length-1];
			ingredientes=Arrays.copyOf(ingredientes, ingredientes.length-1);
			System.out.println("Ingrediente " + ingrediente.getNombre()+" eliminado");
		}
		else {
			throw new EIngredienteExiste("El mesero que intenta eliminar no existe");
		}
	}
	
	
	//getters and setters
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoPlato() {
		return tipoPlato;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getId() {
		return id;
	}

	public Ingrediente[] getIngredientes() {
		return ingredientes;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	} 
	
	

	
}
