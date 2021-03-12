package Restaurante;

import java.util.Arrays;

import Excepciones.*;

public class Orden {
	
	Cliente clientePpal;
	Plato[] platos = new Plato[0]; // arreglo de platos de la orden
	int nroMesa;
	Mesero mesero;
	
	//constructor: el nro de mesa  se llena con el métodos de asignació
	public Orden(Cliente clientePpal, int nroMesa, Mesero mesero) {
		this.clientePpal = clientePpal;
		this.nroMesa = nroMesa;
		this.mesero = mesero;
	} 
	

	
	//CalcularPrecio
	public double calcularPrecioF() {
		
		double precioTotal = 0;
		return precioTotal;
	}
	//Agregar plato
	public void addPlato(Plato plato, Restaurante r) throws EPlatoExiste {
				boolean sePuede = true;
				for (int i = 0; i < r.getPlatos().length; i++) {
					if(r.getPlatos()[i].getId().contentEquals(plato.getId()) && r.getPlatos()[i].isDisponibilidad()) {//Revisa la si el plato se puede usar
						Ingrediente[] ritemp = r.getIngredientes();
						Plato[] rptemp = r.getPlatos(); //crea lista temporal de ingredientes y de platos del RESTAURANTE
						for (int j = 0; j < plato.getIngredientes().length; j++) {  //Revisa cada ingrediente del plato
							for (int j2 = 0; j2 < ritemp.length; j2++) {    //Lo compara con cada ingrediente del restaurante
								if(plato.getIngredientes()[j].getId().contentEquals(ritemp[j2].getId()) && sePuede) {  //Revisa si existe
									if(ritemp[j2].getCantidad() <= 0) { //Si no hay ingrediente no se puede agregar plato
										sePuede = false;
									}else { //si hay ingrediente se agrega el plato
										sePuede = true;
										ritemp[j2].setCantidad(ritemp[j2].getCantidad() - 1);   //Se le quita 1 a cada ingrediente presente en el plato
										if(ritemp[j2].getCantidad() <= 0) { //Si la cantidad de ingredientes llega a 0, se "apaga" el plato
											rptemp[i].setDisponibilidad(false);
										}
									}
								}
							}
						}
						r.setIngredientes(ritemp);  //Se aplican los cambios a los arreglos del restaurante
						r.setPlatos(rptemp);
					}
				}
				if(sePuede) {
					platos = Arrays.copyOf(platos, platos.length + 1);  //Agrega el plato al arreglo de platos en la orden solo si se puede
					platos[platos.length - 1] = plato;
				}else {
					throw new EPlatoExiste("El plato no esta disponible o no existe");  //Sino lanza un error
				}
			}
	
		
	//Buscar Plato 
	public int buscarPlato(Plato plato) throws EPlatoExiste {
		boolean found = false;
		int cont = 0;
		while(cont < platos.length && !found) { //Verifica la existencia del plato
			if(platos[cont] == plato) found = !found;
			cont++;
		}
		if(found) {
			return cont;// Si lo encuntra retorna la posicion
		}
		throw new EPlatoExiste("El plato no existe"); //Si no lo encuentra retorna error
	}
	
	//Modificar plato 
	public Plato modificarPlato(Plato plato, String modificacion) {
		Plato ret = plato; //Crea un objeto Plato nuevo
		ret.setNombre(plato.getNombre() + " - " + modificacion); //Modifica el nombre del objeto recien creado.
		return ret;
	}
	
	
	 //Calcular precio recursividad 
	public  double calcularPrecio (Plato platos[], int pos) {
		if(pos==platos.length-1)
			return platos[platos.length-1].getPrecio();
		return platos[pos].getPrecio()+calcularPrecio(platos, pos+1);
	}
	 
	
	//imprimir platos 
	public void imprimirP() {
		for (int i = 0; i < platos.length; i++) {
			System.out.println(platos[i].getNombre());
		}
	}

	
	/*Prueba método calcular precio
	 * public static void main(String[] args) {

		Ingrediente i1 = new Ingrediente("tomate", 3, "120");
		Ingrediente i2 = new Ingrediente("lechuga", 4, "1202");
		
		Ingrediente ing[] = new Ingrediente[2];
		ing[0]=i1;
		ing[1]=i2;
		
		Plato p1 = new Plato("Guacamole", "Entrada",20000, "1201", ing);
		Plato p2 = new Plato("Pizza", "Fuerte",27000, "121201", ing);
		
		platos = Arrays.copyOf(platos, 2);
		platos[0]= p1;
		platos[1]=p2;
		
		System.out.println(calcularPrecio(platos,0));
		
	}*/
	
	//getters and setters
	public Cliente getClientePpal() {
		return clientePpal;
	}

	public void setClientePpal(Cliente clientePpal) {
		this.clientePpal = clientePpal;
	}

	public Plato[] getPlatos() {
		return platos;
	}


	public int getNroMesa() {
		return nroMesa;
	}
	
	public void setNroMesa(int nroMesa) {
		this.nroMesa = nroMesa;
	}


	public Mesero getMesero() {
		return mesero;
	}


	
	

	
	

}
