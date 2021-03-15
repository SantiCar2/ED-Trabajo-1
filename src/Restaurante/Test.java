package Restaurante;

import Excepciones.EClienteExiste;
import Excepciones.EIngredienteExiste;
import Excepciones.EMesaExiste;
import Excepciones.EMeseroExiste;
import Excepciones.ENoHayMesasDisponibles;
import Excepciones.ENoHayMeseros;
import Excepciones.EPlatoExiste;

public class Test {

	public static void main(String[] args) {
		
		Restaurante r = new Restaurante();//crear restaurante
		
		//crear clientes
		Cliente cliente1 = new Cliente("Juanita", "100","443", 2);
		Cliente cliente2 = new Cliente("Pedro", "123","232", 2);
		Cliente cliente3 = new Cliente("Pat", "42231","242", 2);

		
		try {
			r.agregarCliente(cliente1);
			r.agregarCliente(cliente3);
			r.agregarCliente(cliente2);
		} catch (EClienteExiste e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("CLIENTES");
		r.imrpimirC();
		System.out.println(" ");
		
		
		//agregar mesas
		Mesa mesa1 = new Mesa(1,3);
		Mesa mesa2 = new Mesa(2, 4);
		Mesa mesa3 = new Mesa(3, 10);
		
		try {
			r.agregarMesa(mesa1);
			r.agregarMesa(mesa2);
			r.agregarMesa(mesa3);
		} catch (EMesaExiste e) {
			System.out.println(e.getMessage());
		}
		System.out.println("MESAS");
		r.imprimirMesas();
		System.out.println(" ");
			
		System.out.println("MESEROS");
		r.imprimirMeseros();
		System.out.println(" ");
		
		//crear ingredientes
		Ingrediente i1 = new Ingrediente("tomate", 3, "120");
		Ingrediente i2 = new Ingrediente("lechuga", 4, "1202");
		
		System.out.println("INGREDIENTES");
		r.imprimirI();
		System.out.println(" ");
		
		//crear platos
		Ingrediente ing[] = new Ingrediente[2];
		ing[0]=i1;
		ing[1]=i2;
		
		Plato p1 = new Plato("Guacamole", "Entrada",20000, "1201", ing);
		Plato p2 = new Plato("Pizza", "Fuerte",27000, "121201", ing);
		
		try {
			r.crearPlato(p2);
			r.crearPlato(p1);
		} catch (EPlatoExiste e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("CARTA");
		r.imprimirPlatos();
		System.out.println(" ");
		
		//Crear orden 
		Orden o1 = null;
		try {
			//crear plato, llamando al método asignar mesa y asignar mesero
			o1 = new Orden(cliente1, r.asignarMesa(3), r.asignarMesero());
			try {
				o1.addPlato(o1.modificarPlato(p1, "Sin sal"),r);
				o1.addPlato(p2,r);
			} catch (EPlatoExiste e1) {
				System.out.println(e1.getMessage());
			}
		} catch (ENoHayMesasDisponibles | ENoHayMeseros e2) {
			e2.getMessage();
		}

	
		/* eliminar un ingrediente que inhabilita el plato 2
		 * try {
		 		r.eliminarIngrediente(i2);
			} catch (EIngredienteExiste | EPlatoExiste e) {
				System.out.println(e.getMessage());
			}
		 */

		System.out.println("Disponibilidad de " + p2.getNombre() + ": " +p2.isDisponibilidad());
		System.out.println("");
		
		o1.imprimirP();
		
	}
}
