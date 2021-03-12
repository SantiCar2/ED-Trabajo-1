package Restaurante;

public class Factura {
	

	Orden orden;
	double precioTotal;
	
	//constructor
	public Factura( Orden orden) {
		this.orden = orden;
		this.precioTotal = orden.calcularPrecio(orden.getPlatos(), 0);
	}


	//getters and setters
	public Orden getOrden() {
		return orden;
	}


	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	
	
	

}
