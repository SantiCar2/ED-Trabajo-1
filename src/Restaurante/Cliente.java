package Restaurante;

public class Cliente extends Persona{

	private int nroComensales;

	//constructor
	public Cliente(String nombre, String id, String tel, int nroComensales) {
		super(nombre, id, tel);
		this.nroComensales = nroComensales;
	}

	//getters and setters
	public int getNroComensales() {
		return nroComensales;
	}

	public void setNroComensales(int nroComensales) {
		this.nroComensales = nroComensales;
	}
	
	
}
