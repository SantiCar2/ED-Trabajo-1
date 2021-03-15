package Restaurante;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import Excepciones.*;

public class Restaurante {
	
	Cliente clientes[] = new Cliente[0];
	Plato platos[] = new Plato[0]; //arreglo de platos que significan el menú del restaurante
	Orden ordenes[] = new Orden[0];
	Factura facturas[] = new Factura[0];
	Mesa mesas[] = new Mesa[0];
	
	//ingredientes en la despensa
	//controlado internamente en la cocina por un programa aparte
	Ingrediente i1 = new Ingrediente("tomate", 3, "120");
	Ingrediente i2 = new Ingrediente("lechuga", 2, "1202");	
	Ingrediente i3 = new Ingrediente("pan", 4, "12032");	
	Ingrediente i4 = new Ingrediente("carne de hamburguesa", 4, "123");	
	Ingrediente i5 = new Ingrediente("salsa de tomate", 4, "2342");	
	
	Ingrediente ingredientes[] = {i1,i2,i3,i4,i5};
	
	
	//meseros del restaurante
	Mesero m1 = new Mesero("Adonay", "1021", "3823", "calle", "sura", 1);
	Mesero m2 = new Mesero("Pedro", "102321", "234", "carrera", "colsanitas", 5);
	Mesero m3 = new Mesero("Samuel", "2423", "34", "sur", "prepa", 5);
	
	Mesero meseros[] = {m1,m2,m3};

	

	//Agregar cliente
	public void agregarCliente(Cliente cliente) throws EClienteExiste   {
			
			if(buscarCliente(cliente.getId())==-1) {
				clientes = Arrays.copyOf(clientes, clientes.length+1);
				clientes[clientes.length-1]= cliente;
			}
			else {
				throw new EClienteExiste("El cliente " +cliente.getId() + " que intenta agregar ya existe" );
			}

	}
	
	//buscar cliente
	public int buscarCliente(String codigoCliente) {
		int i=0;
		
		while(i<clientes.length && !codigoCliente.equals(clientes[i].getId())) {
			i=i+1;
		}
		
		if (i<clientes.length) {
			return i;
		}
		else 
			return -1;
	}
	
	//eliminar cliente
	public void eliminarCliente(Cliente cliente) throws EClienteExiste {
		int i = buscarCliente(cliente.getId());
		
		if(i!=-1) {
			clientes[i]=null;
			clientes[i]=clientes[clientes.length-1];
			clientes=Arrays.copyOf(clientes, clientes.length-1);
		}
		else {
			throw new EClienteExiste("El cliente que intenta eliminar no existe");
		}
	}
	
	
	//Agregar mesa
	public void agregarMesa(Mesa mesa) throws EMesaExiste   {
			
			if(buscarMesa(mesa.getNroMesa())==-1) {
				mesas = Arrays.copyOf(mesas, mesas.length+1);
				mesas[mesas.length-1]= mesa;
			}
			else {
				throw new EMesaExiste("La mesa " +mesa.getNroMesa() + " que intenta agregar ya existe" );
			}

	}
	
	//buscar mesa
	public int buscarMesa(int nroMesa) {
		int i=0;
		
		while(i<mesas.length && mesas[i].getNroMesa() != nroMesa) {
			i=i+1;
		}
		
		if (i<mesas.length) {
			return i;
		}
		else 
			return -1;
	}
	
	//eliminar mesa
	public void eliminarMesa(Mesa mesa) throws EMesaExiste {
		int i = buscarMesa(mesa.getNroMesa());
		
		if(i!=-1) {
			mesas[i]=null; //vaciar espacio de mesa eliminada
			mesas[i]=mesas[mesas.length-1]; //poner la mesa de la última posición en la que queda vacía
			mesas=Arrays.copyOf(mesas, mesas.length-1); //redimensionar el arreglo para que no quede con espacios vacíos
		}
		else {
			throw new EMesaExiste("La mesa que intenta eliminar no existe");
		}
	}
	
	

	
	//buscar ingrediente
	public int buscarIngrediente(String idIngrediente) {
		int i=0;
		
		while(i<ingredientes.length && !idIngrediente.equals(ingredientes[i].getId())&& ingredientes[i].getCantidad()!=0) {
			i=i+1;
		}
		
		if (i<ingredientes.length) {
			return i;
		}
		else 
			return -1;
	}
	

	
	//buscar mesero
	public int buscarMesero(String idMesero) {
		int i=0;
		
		while(i<meseros.length && !idMesero.equals(meseros[i].getId())) {
			i=i+1;
		}
		
		if (i<meseros.length) {
			return i;
		}
		else 
			return -1;
	}

	
	
	//Hacer orden
	public void hacerOrden(Orden o) {
		ordenes = Arrays.copyOf(ordenes, ordenes.length + 1); //Agrega una posicion a la lista
		ordenes[ordenes.length - 1] = o;	//Agrega la orden a la ultima posicion
	}
	
	//Buscar orden
	public int buscarOrden(Orden o) throws EOrdenExiste{
		boolean found = false;
		int cont = 0;
		while(cont < ordenes.length && !found) {	//Repite hasta que encuentre la orden o se termine el arreglo
			if(ordenes[cont] == o) found = !found;
			cont++;
		}
		if(found) {	//Si encuentre retorna la posicion
			return cont;
		}
		throw new EOrdenExiste("La orden no existe");	//Si no encuntra lanza error
	}
	
	//Eliminar orden
	public void eliminarOrden(Orden o) throws EOrdenExiste{
		int pos = buscarOrden(o);	//Busca la posicion de la orden en el arreglo
		ordenes[pos] = ordenes[ordenes.length - 1];	//Reemplaza la orden con la ultima orden en el arreglo
		ordenes = Arrays.copyOf(ordenes, ordenes.length - 1);	//Reduce el tamano del arreglo
	}
	
	//buscar plato
	public int buscarPlato(String idPlato) {
		int i=0;
		
		while(i<platos.length && !idPlato.equals(platos[i].getId())) {
			i++;
		}
		if(i<platos.length) {
			return i;
		}
		else {
			return -1;
		}
	}

	//crear plato
	public void crearPlato(Plato plato) throws EPlatoExiste {
		
		if(buscarPlato(plato.getId())==-1) {
			platos = Arrays.copyOf(platos, platos.length+1);
			platos[platos.length-1]= plato;
		}
		else {
			throw new EPlatoExiste("El plato " + plato.getId() + " que intenta agregar ya existe");
		}
	}
	
	
	//eliminar plato
	public void eliminarPlato(Plato plato) throws EPlatoExiste {
		int i = buscarPlato(plato.getId());
	
		if(i!=-1) {
			System.arraycopy(platos, i + 1, plato , i , platos.length - 1 - i);
		}
		else {
			throw new EPlatoExiste("El plato que intenta eliminar no existe");
		}
	}
	
	//inhabilitar plato
	public void inhabilitarPlato(Plato plato) throws EPlatoExiste {
		
		int j= plato.getIngredientes().length;
		int acum=0;
		for (int i = 0; i < j; i++) {
			if(buscarIngrediente(plato.ingredientes[i].getId())!=-1) {
				acum=acum+1;
			}
		}
		
		if(buscarPlato(plato.getId())!=-1 && acum!=j) {
			platos[buscarPlato(plato.getId())].setDisponibilidad(false);
		}
		else {
			throw new EPlatoExiste("El plato que intenta inhabilitar no existe o todos sus ingredientes existen");
		} 
	}
	


	//verificar disponibilidad mesa
	public int verificarDisponibilidadMesa(int nroPersonas) {
		int acum=0;
		//recorre el arreglo de mesas hasta encontrar una que este disponible y tenga el numero de puestos requerido 
		while(acum<mesas.length && (!mesas[acum].isDisponibilidad()||mesas[acum].getNroPuestos()<nroPersonas)) {
			acum++;	
		}
		if (acum<mesas.length) {
			return acum;
		}
		return -1;
	}
	
	//asignar mesa

	public int asignarMesa(int j) throws ENoHayMesasDisponibles {
		int i = verificarDisponibilidadMesa(j);
		if(i!=-1) {
			mesas[i].setDisponibilidad(false);
		return mesas[i].getNroMesa();
		}
		else {
			throw new ENoHayMesasDisponibles("En el momento no hay mesas disponibles");
		}
	}
	

	//verificar disponibilidad mesero
	public int verificarDisponibilidadMesero() throws ENoHayMeseros {
		int acum=0;
		//recorre el arreglo de mesas hasta encontrar una que este disponible y tenga el numero de puestos requerido 
		while(acum<meseros.length && (!meseros[acum].isDisponibilidad())) {
			acum=acum+1;	
		}
		if (acum<meseros.length) {
			return acum;
		}
		throw new ENoHayMeseros("En el momento no hay meseros disponibes");
	}
	
	//asignar mesero

	public Mesero asignarMesero() throws ENoHayMeseros {
		int i = verificarDisponibilidadMesero();
		if(i!=-1) {
			meseros[i].setMesasDisponibles(meseros[i].getMesasDisponibles()-1);//agregar el número de mesas que está atendiendo un mesero
		
			if(meseros[i].getMesasDisponibles()==0) {
				meseros[i].setDisponibilidad(false);
			}
			return meseros[i];
		}
		else throw new ENoHayMeseros("En el momento no hay meseros disponibes");
	}

	
	//cerrar cliente
	public void cerrarCliente(Factura factura) throws EClienteExiste {
		
	int posicionMesa;
	Mesero m;
	Cliente c;
	
		posicionMesa=buscarMesa(factura.getOrden().getNroMesa());
		mesas[posicionMesa].setDisponibilidad(true); //hace disponible la mesa donde se va a retirar el cliente
		m=factura.getOrden().getMesero();
		c=factura.getOrden().getClientePpal();
		
		if (m.getMesasDisponibles()==0) {//para saber si el mesero está ocupado con su máximo de mesas
			
			m.setMesasDisponibles(m.getMesasDisponibles()+1);//aumenta la disponiblidad de mesas  que atiende el mesero 											
			m.setDisponibilidad(true);//hace disponible al mesero que atendia esa mesa
			
		}else{
			m.setMesasDisponibles(m.getMesasDisponibles()+1);//aumenta la disponibolidad de  las mesas que atiende el mesero 											
			
		}
		//llama al metodo para eliminar el cliente que se va del array
		eliminarCliente(c);
		
	}

	
	//MÉTODO FACTURA
	//Crear Factura
   	public void crearFactura(Factura factura) {
       	facturas = Arrays.copyOf(facturas, facturas.length + 1); //Agrega una posición a la lista de las facturas
       	facturas[facturas.length - 1] = factura; //agrega la factura a la lista de facturas
    }
   	
   	//calcular precio
	 
   	public double calcularPrecio (Orden orden) throws EListaVacia {
		if (orden.getPlatos()==null || orden.getPlatos().length==0) {
			throw new EListaVacia("No hay ningún plato en la orden");
		}
		return orden.calcularPrecio(orden.getPlatos(),0);
   	}

	//imprimir  
	public void imrpimirC() {
		for (int i = 0; i < clientes.length; i++) {
			System.out.println("Cliente "+ i + " "+ clientes[i].getNombre());
		}
	}
	
	public void imprimirMesas() {
		for (int i = 0; i < mesas.length; i++) {
			System.out.println("Mesa "+ mesas[i].getNroMesa() + ", Puestos: "+ mesas[i].getNroPuestos());	
		}
	}
	
	public void imprimirMeseros() {
		for (int i = 0; i < meseros.length; i++) {
			System.out.println("Mesero: " + meseros[i].getNombre());
		}
	}

	public void imprimirI() {
		for (int i = 0; i < ingredientes.length; i++) {
			System.out.println("Ingrediente: "+ ingredientes[i].getNombre() + " "+ ingredientes[i].getCantidad());
		}
	}
	
	public void imprimirPlatos() {
		for (int i = 0; i < platos.length; i++) {
			System.out.println("Plato: "+ platos[i].getNombre());
		}
	}
	//FICHEROS 
	
	//Clientes
	public  void escribirClientesFichero() {
		
		
		String fichero = "\\Users\\juanitamedinabetancur\\Escritorio\\clientesR.txt";
		FileWriter fw = null;
		BufferedWriter b = null;
		try {
			fw = new FileWriter(fichero);
			b = new BufferedWriter(fw);
			
			for (int i = 0; i < clientes.length; i++) {
				
				b.write(clientes[i].nombre + " " + clientes[i].getId());b.newLine();
			}
		}
		catch(FileNotFoundException e){
			System.out.println("No se pudo encontrar el fichero");
		}
		catch(IOException e){
			System.out.println("No se pudo escribir el fichero");
		}
		finally {
			if(fw != null)
				try {
					b.close();
					fw.close();
				} catch(IOException e) {
					System.out.println("No se pudo cerrar el fichero");
				}
		}
	}
	
	
	//getters and setters
	public Plato[] getPlatos() {
		return platos;
	}
	public Ingrediente[] getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Ingrediente[] ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public void setPlatos(Plato[] platos) {
		this.platos = platos;
	}

	public Orden[] getOrden() {
		return ordenes;
	}
	
	public void setOrden(Orden[] o) {
		this.ordenes = o;
	}

	public Mesero[] getMeseros() {
		return meseros;
	}
	
}

