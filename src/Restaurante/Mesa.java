package Restaurante;

public class Mesa {

	int nroMesa; 
	boolean disponibilidad; 
	int nroPuestos;
	
	//constructor
	public Mesa(int nroMesa, int nroPuestos) {
		this.nroMesa = nroMesa;
		this.disponibilidad = true;
		this.nroPuestos = nroPuestos;
	}

	
	//getters and setters
	public int getNroMesa() {
		return nroMesa;
	}


	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public int getNroPuestos() {
		return nroPuestos;
	}

		
	
	
}
