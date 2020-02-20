package pack_reto3;

import java.io.Serializable;

public class Cliente implements Serializable{

	private String NIF;
	private String nombre;
	private int telefono;
	private String direccion;
	private int deuda;
	
	public Cliente() {
		
	}
	
	public Cliente(String NIF, String nombre, int telefono, String direccion, int deuda) {
		this.NIF = NIF;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.deuda = deuda;
	}

	
	public String toString() {
		return "Cliente [NIF=" + NIF + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion
				+ ", deuda=" + deuda + "]";
	}
	
	public String generarInforme() {
		return "[NIF: " + NIF + ", Deuda: " + deuda + "€]";
	}


	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getDeuda() {
		return deuda;
	}

	public void setDeuda(int deuda) {
		this.deuda = deuda;
	}
	
	public void mostrarCliente() {
		System.out.println("NIF: " + NIF);
		System.out.println("Nombre: " + nombre);
		System.out.println("Telefono: " + telefono);
		System.out.println("Direccion: " + direccion);
		System.out.println("Deuda: " + deuda);
		System.out.println("--------------------------------------");
	}
	
}
