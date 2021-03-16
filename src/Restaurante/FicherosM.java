package Restaurante;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FicherosM implements Serializable {
		
	private String nombre; 
	private String id; 
	private String tel;
	private String direccion; 
	private String segSocial;
	public FicherosM(String nombre, String id, String tel, String direccion, String segSocial, String PATH) {
		this.nombre = nombre;
		this.id = id;
		this.tel = tel;
		this.direccion = direccion;
		this.segSocial = segSocial;
	}
	//Escribir fichero
	
		public void wFicheroM(String PATH) throws IOException {
			
			FileOutputStream fm = new FileOutputStream(PATH+"\\"+this.nombre+".javaObj");
			ObjectOutputStream om= new ObjectOutputStream(fm);
			
			om.writeObject(this);
			om.close();
			fm.close();
		}
		
		//Leer fichero
		
		public FicherosM rFicheroM(String PATH)throws IOException, ClassNotFoundException{
			
			FileInputStream fm1= new FileInputStream(PATH+"\\"+this.nombre+".javaObj");
			ObjectInputStream om1= new ObjectInputStream(fm1);
			FicherosM nm = (FicherosM)om1.readObject();
			
			om1.close();
			fm1.close();
			return nm;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getSegSocial() {
			return segSocial;
		}

		public void setSegSocial(String segSocial) {
			this.segSocial = segSocial;
		}
	
	
	
}

