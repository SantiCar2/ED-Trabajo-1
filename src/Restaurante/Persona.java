package Restaurante;

public class Persona {
	
	protected String nombre; 
	protected String id; 
	protected String tel;
	
	//constructor 
	public Persona(String nombre, String id, String tel) {
		this.nombre = nombre;
		this.id = id;
		this.tel = tel;
	}

	//getters and setters 
	public String getNombre() {
		return nombre;
	}


	public String getId() {
		return id;
	}


	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}	

}
