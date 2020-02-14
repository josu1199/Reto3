package pack_reto3;
import java.io.*;

public class Vendedor {

	private int codEmp;
	private String nombre;
	private int ventasMes;
	private String zona;
	
	public Vendedor() {
		
	}
	
	public Vendedor(int codEmp, String nombre, int ventasMes, String zona) {
		this.codEmp = codEmp;
		this.nombre = nombre;
		this.ventasMes = ventasMes;
		this.zona = zona;
	}

	public int getCodEmp() {
		return codEmp;
	}

	public void setCodEmp(int codEmp) {
		this.codEmp = codEmp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVentasMes() {
		return ventasMes;
	}

	public void setVentasMes(int ventasMes) {
		this.ventasMes = ventasMes;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
	
	public String toString() {
		return "Vendedor [codEmp=" + codEmp + ", nombre=" + nombre + ", ventasMes=" + ventasMes + ", zona=" + zona
				+ "]";
	}
	
	public void cargarVendedores() throws FileNotFoundException {
		FileInputStream Vendedores = new FileInputStream("C:\\RETO3\\Vendedores.dat");
		boolean hola;
	}
}
