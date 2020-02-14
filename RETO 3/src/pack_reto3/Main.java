package pack_reto3;

import java.io.*;
import java.util.*;

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean salir = false;
		int opcCliente, opcVendedor, opcZona, opcInicial;
		
		do {
			System.out.println("Seleccione tipo de operacion: \n" +
								"1.SOBRE CLIENTES \n" +
								"2.SOBRE VENDEDORES \n" +
								"3.SOBRE ZONAS \n" +
								"0.SALIR \n");
			opcInicial = sc.nextInt();
			switch(opcInicial) {
			case 1:
				System.out.println("SOBRE CLIENTES: \n" + 
									"1.Añadir Cliente \n" +
									"2.Lista Clientes \n" +
									"3.Buscar Clientes \n" +
									"4.Borrar Cliente \n" +
									"5.Borrar fichero de Clientes \n" + 
									"6.Generar informe Deudas \n");
				opcCliente = sc.nextInt();
				switch(opcCliente) {
					case 1:
						aniadirCliente();
						break;
					case 2:
						leerFichero();
						break;
					case 3:
						borrar();
						break;
				}
				break;
			case 2:
				System.out.println("SOBRE VENDEDORES: \n" + 
									"1.Cargar Vendedores \n" +
									"2.Volcar Ventas \n" +
									"3.Copia de Seguridad \n" +
									"4.Listado Vendedores \n");
				opcVendedor = sc.nextInt();
				switch(opcVendedor) {
				
				}
				break;
			case 3:
				System.out.println("SOBRE ZONAS: \n" +
									"1. Realizar listados \n");
				break;
			case 0:
				salir = true;
				break;
			default:
				System.out.println("Opcion No Valida!");
				break;
			}
		}while(!salir);
		
	}
	
	public static void aniadirCliente() {
		String NIF, nombre, direccion;
		int telefono, deuda;
		try {
			File fichero = new File("C:\\RETO3\\CLIENTES.DAT");
			fichero.createNewFile();
			FileOutputStream fos = new FileOutputStream(fichero);
			ObjectOutputStream ficheroSalida = new ObjectOutputStream(fos);
			System.out.println("Introduce NIF: ");
			NIF = sc.nextLine();
			sc.nextLine();
			System.out.println("Introduce nombre: ");
			nombre = sc.nextLine();
			System.out.println("Introduce telefono: ");
			telefono = sc.nextInt();
			System.out.println("Introduce Direccion: ");
			direccion = sc.nextLine();
			sc.nextLine();
			System.out.println("Introduce deuda: ");
			deuda = sc.nextInt();
			Cliente cliente = new Cliente(NIF, nombre, telefono, direccion, deuda);
			ficheroSalida.writeObject(cliente);
			ficheroSalida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void leerFichero() {
		Cliente c;
		
		try {
			File fichero = new File("C:\\RETO3\\CLIENTES.DAT");
			FileInputStream fos = new FileInputStream(fichero);
			ObjectInputStream ficheroLectura = new ObjectInputStream(fos);
			c = (Cliente) ficheroLectura.readObject();
			c.mostrarCliente();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void borrar() {
		File fich = new File("C:\\RETO3\\CLIENTES.DAT");
		fich.delete();
	}
	
}
