package pack_reto3;

import java.io.Serializable;

public class VentasZona implements Serializable{
	private String zona;
	private String responsable;
	private int[] ventasMensuales;
	
	public VentasZona() {
		this.ventasMensuales = new int[12];
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public int[] getVentasMensuales() {
		return ventasMensuales;
	}

	public void setVentasMensuales(int mes, int ventas) {
		this.ventasMensuales[mes] = this.ventasMensuales[mes] + ventas;
	}
	
	public void sumarVentas(int mes, int ventas) {
		this.ventasMensuales[mes] = this.ventasMensuales[mes] + ventas;
	}
}
