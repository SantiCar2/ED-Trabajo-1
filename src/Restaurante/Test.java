package Restaurante;

import Excepciones.EClienteExiste;
import Excepciones.EIngredienteExiste;
import Excepciones.EMesaExiste;
import Excepciones.EMeseroExiste;
import Excepciones.ENoHayMesasDisponibles;
import Excepciones.ENoHayMeseros;
import Excepciones.EPlatoExiste;

public class Test {
	public static Restaurante r = new Restaurante();//crear restaurante
	public static InterfazRestaurante admin = new InterfazRestaurante();
	public static InterfazCliente cli = new InterfazCliente();


	public static void main(String[] args) {
		
		
		
		//crear clientes
		Cliente cliente1 = new Cliente("Juanita", "100","443");
		Cliente cliente2 = new Cliente("Pedro", "123","232");
		Cliente cliente3 = new Cliente("Pat", "42231","242");

		
		try {
			r.agregarCliente(cliente1);
			r.agregarCliente(cliente3);
			r.agregarCliente(cliente2);
		} catch (EClienteExiste e) {
			System.out.println(e.getMessage());
		}

		Mesa mesa1 = new Mesa(1,3);
		Mesa mesa2 = new Mesa(2, 4);
		Mesa mesa3 = new Mesa(3, 6);
		
		try {
			r.agregarMesa(mesa1);
			r.agregarMesa(mesa2);
			r.agregarMesa(mesa3);
		} catch (EMesaExiste e) {
			System.out.println(e.getMessage());
		}

		Plato p1 = new Plato("Guacamole", "Entrada",20000, "1201", r.getIngredientes());
		Plato p2 = new Plato("Pizza", "Fuerte",27000, "121201", r.getIngredientes());
		Plato p3 = new Plato("Agua", "Bebida",10000, "1201", r.getIngredientes());
		Plato p4 = new Plato("Hamburguesa", "Fuerte",30000, "12345", r.getIngredientes());
		Plato p5 = new Plato("Empanadas", "Entrada",15000, "65498", r.getIngredientes());
		Plato p6 = new Plato("Limonada", "Bebida",8000, "789654", r.getIngredientes());
		Plato[] pl = {p1,p2,p3,p4,p5,p6};
		
		r.setPlatos(pl);


		admin.main(null);
		

		cli.main(null);
		
		
	}
}
