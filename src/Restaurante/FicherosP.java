package Restaurante;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FicherosP implements Serializable {
	
	String nombre; 
	String tipoPlato; 
	double precio; 
	String id;
	
	public FicherosP(String nombre, String tipoPlato, double precio, String id, String PATH){
		this.nombre=nombre;
		this.tipoPlato=tipoPlato;
		this.precio=precio;
		this.id=id;
	}
	//Escribir fichero
	
	public void wFicheroP(String PATH) throws IOException {
		
		FileOutputStream fp = new FileOutputStream(PATH+"\\"+this.nombre+".javaObj");
		ObjectOutputStream op= new ObjectOutputStream(fp);
		
		op.writeObject(this);
		op.close();
		fp.close();
	}
	
	//Leer fichero
	
	public FicherosP rFicheroP(String PATH)throws IOException, ClassNotFoundException{
		
		FileInputStream fp1= new FileInputStream(PATH+"\\"+this.nombre+".javaObj");
		ObjectInputStream op1= new ObjectInputStream(fp1);
		FicherosP np = (FicherosP)op1.readObject();
		
		op1.close();
		fp1.close();
		return np;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoPlato() {
		return tipoPlato;
	}

	public void setTipoPlato(String tipoPlato) {
		this.tipoPlato = tipoPlato;
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

	public void setId(String id) {
		this.id = id;
	}
}