package Restaurante;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import Excepciones.*;

public class Restaurante {
	
	Cliente clientes[] = new Cliente[0];
	Mesero meseros[] = new Mesero[0];
	Plato platos[] = new Plato[0]; //arreglo de platos que significan el menú del restaurante
	Orden ordenes[] = new Orden[0];
	Factura facturas[] = new Factura[0];
	Mesa mesas[] = new Mesa[0];
	Ingrediente ingredientes[]= new Ingrediente[0];
	

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
	
	
	
	//Comprar ingrediente
	public void comprarIngrediente(Ingrediente ingrediente) throws EIngredienteExiste   {
			
			if(buscarIngrediente(ingrediente.getId())==-1) {
				ingredientes = Arrays.copyOf(ingredientes, ingredientes.length+1);
				ingredientes[ingredientes.length-1]= ingrediente;
			}
			else {
				throw new EIngredienteExiste("El ingrediente " +ingrediente.getId() + " que intenta agregar ya existe" );
			}

	}
	
	//buscar ingrediente
	public int buscarIngrediente(String idIngrediente) {
		int i=0;
		
		while(i<ingredientes.length && !idIngrediente.equals(ingredientes[i].getId())) {
			i=i+1;
		}
		
		if (i<ingredientes.length) {
			return i;
		}
		else 
			return -1;
	}
	
	//eliminar ingrediente 
	public void eliminarIngrediente(Ingrediente ingrediente) throws EIngredienteExiste, EPlatoExiste {
		int i = buscarIngrediente(ingrediente.getId());
		
		if(i!=-1) {
			ingredientes[i]=null;
			ingredientes[i]=ingredientes[ingredientes.length-1];
			ingredientes=Arrays.copyOf(ingredientes, ingredientes.length-1);
			//verificar qué platos se inhabilitan al eliminar el ingrediente
			for (int j = 0; j < platos.length; j++) {
				inhabilitarPlato(platos[i]);
			} 
		}
		else {
			throw new EIngredienteExiste("El ingrediente que intenta eliminar no existe");
		}
	}
	
	//Agregar cantidad ingrediente
	public void agregarCantidadIngrediente(int cantidad, String idIngrediente) throws EIngredienteExiste   {
			int i = buscarIngrediente(idIngrediente);
			ingredientes[i].setCantidad(ingredientes[i].getCantidad()+cantidad);

	}
	
	
	//Contratar mesero
	public void contratarMesero(Mesero mesero) throws EMeseroExiste   {
			
			if(buscarMesero(mesero.getId())==-1) {
				meseros = Arrays.copyOf(meseros, meseros.length+1);
				meseros[meseros.length-1]= mesero;
			}
			else {
				throw new EMeseroExiste("El mesero " +mesero.getId() + " que intenta agregar ya existe" );
			}

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
	
	//despedir mesero
	public void despedirMesero(Mesero mesero) throws EMeseroExiste {
		int i = buscarMesero(mesero.getId());
		
		if(i!=-1) {
			meseros[i]=null;
			meseros[i]=meseros[meseros.length-1];
			meseros=Arrays.copyOf(meseros, meseros.length-1);
		}
		else {
			throw new EMeseroExiste("El mesero que intenta eliminar no existe");
		}
	}
	
	
	//Hacer orden
	public void hacerOrden(Orden o) {
		ordenes = Arrays.copyOf(ordenes, ordenes.length + 1); //Agrega una posicion a la lista
		ordenes[ordenes.length - 1] = o;	//Agrega la orden a la ultima posicion
	}
	
	//Buscar orden
	public int buscarOrden(Orden o) throws Exception{
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
	public void eliminarOrden(Orden o) throws Exception{
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
		for (int i = 0; i < ingredientes.length; i++) {
			if(plato.buscarIngrediente(ingredientes[i].getId())!=-1) {
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
		while(acum<mesas.length && (!mesas[acum].isDisponibilidad()||mesas[acum].getNroPuestos()!=nroPersonas)) {
			acum++;	
		}
		if (acum<mesas.length) {
			return acum;
		}
		return -1;
	}
	
	//asignar mesa

	public int asignarMesa(int nroPersonas) throws ENoHayMesasDisponibles {
		int i = verificarDisponibilidadMesa(nroPersonas);
		if(i!=-1) {
			mesas[i].setDisponibilidad(false);
		return mesas[i].getNroMesa();
		}
		else {
			throw new ENoHayMesasDisponibles("En el momento no hay mesas disponibles");
		}
	}
	

	//verificar disponibilidad mesero
	public int verificarDisponibilidadMesero() {
		int acum=0;
		//recorre el arreglo de mesas hasta encontrar una que este disponible y tenga el numero de puestos requerido 
		while(acum<meseros.length && (!meseros[acum].isDisponibilidad())) {
			acum++;	
		}
		if (acum<meseros.length) {
			return acum;
		}
		return -1;
	}
	
	//asignar mesero

	public Mesero asignarMesero() throws ENoHayMeseros {
		int i = verificarDisponibilidadMesero();
		if(i!=-1) {
			meseros[i].setMesasDisponibles(meseros[i].getMesasDisponibles()+1);//agregar el número de mesas que está atendiendo un mesero
			int j= meseros[i].getMesasAtencion();
			int k = meseros[i].getMesasDisponibles();
			if(j==k) {
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
		
		if (m.getMesasAtencion()>=3) {//para saber si el mesero tiene mas de 3 mesas asignadas
			
			m.setMesasAtencion(m.getMesasAtencion()-1);//reduce las mesas que atiende el mesero 											
			m.setDisponibilidad(true);//hace disponible al mesero que atendia esa mesa
			
		}else if(m.getMesasAtencion()<3) {//lo mismo que antes, solo que ya no hace falta cambiar la disponibilidad
																	   //pues menos que 3 siempre es true
			m.setMesasAtencion(m.getMesasAtencion()-1);
			
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
}

