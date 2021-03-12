package Restaurante;

public class Mesero extends Persona{

	private String direccion; 
	private String segSocial;
	private boolean disponibilidad;
	private int mesasAtencion;
	private int mesasDisponibles;
	
	//constructor
	public Mesero(String nombre, String id, String tel, String direccion, String segSocial,
			int mesasAtencion) {
		super(nombre, id, tel);
		this.direccion = direccion;
		this.segSocial = segSocial;
		this.disponibilidad = true;
		this.mesasAtencion = mesasAtencion;
		this.mesasDisponibles=mesasAtencion;
	}

	//getters and setters 
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSegSocial() {
		return segSocial;
	}



	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	public void setMesasDisponibles(int mesas) {
		this.mesasDisponibles = mesas;
	}

	public int getMesasAtencion() {
		return mesasAtencion;
	}
	
	public int getMesasDisponibles() {
		return mesasDisponibles;
	}
	
	public void setMesasAtencion(int mesasAtencion) {
		this.mesasAtencion = mesasAtencion;
	}


	
	
	
	
}

