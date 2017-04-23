package me.r2cam.model;


public class Motor {
	private String nombre;
	private short direccion;
	public Motor(String nombre, short direccion) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
	}
	/**
	 * 0 = Parado , 1 = Adelante, 2 = Izquierda, 3 = Derecha
	 * @return Valor correspondiente a la direccion
	 */
	public short getDireccion() {
		return direccion;
	}
	
	public void setDireccion(short direccion) {
		this.direccion = direccion;
	}
	

	
}
