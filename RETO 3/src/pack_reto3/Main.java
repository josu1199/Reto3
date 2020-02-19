package pack_reto3;

import java.io.*;
import java.util.*;

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
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
									"2.Añadir Vendedores \n" +
									"3.Volcar Ventas \n" +
									"4.Copia de Seguridad \n" +
									"5.Listado Vendedores \n");
				opcVendedor = sc.nextInt();
				switch(opcVendedor) {
				case 1:
					 cargarVendedores();
					 break;
				case 2:
					aniadirVendedores();
					break;
				case 4:
					copiaSeguridad();
					break;
				case 5:
					listarVendedores();
					break;
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

	public static void cargarVendedores() {
		int cont = 0;
		
		File fichero = new File("C:\\RETO3\\Vendedores.dat");
		
		try {
			FileInputStream VendedoresI = new FileInputStream(fichero);
			ObjectInputStream leerVentas = new ObjectInputStream(VendedoresI);
			
			
			
			Vendedor[] listaVendedores = new Vendedor[12];
			
			try {
				while(true) {
					
					Vendedor vendedor = (Vendedor)leerVentas.readObject();
					System.out.println(cont);
					listaVendedores[cont] = vendedor;
					cont++;
					
				}
				
			} catch (EOFException e) {
				// TODO Auto-generated catch block
				System.out.println("No quedan mas objetos");
				leerVentas.close();
				
			} catch (ClassNotFoundException c) {
				System.out.println("Error");
			}
	
			fichero.delete();
			boolean salir = false;
			
			for(int i = 0; listaVendedores.length > i && !salir; i++) {
				listaVendedores[i].setVentasMes(0);
				if(fichero.exists()) {
					FileOutputStream VendedoresO = new FileOutputStream(fichero, true);
					MiObjectOutputStream escribirVentas = new MiObjectOutputStream(VendedoresO);
					Vendedor vendedor = listaVendedores[i];
					escribirVentas.writeObject(vendedor);
					escribirVentas.close();
					VendedoresO.close();
				}else {
					FileOutputStream VendedoresO = new FileOutputStream(fichero);
					ObjectOutputStream escribirVentas = new ObjectOutputStream(VendedoresO);
					Vendedor vendedor = listaVendedores[i];
					escribirVentas.writeObject(vendedor);
					escribirVentas.close();
					VendedoresO.close();
				}
				
				if(listaVendedores[i+1] == null) {
					salir = true;
				}
			}
			VendedoresI.close();
		}catch(IOException i){
			
		}
		
	}
	
	public static void aniadirVendedores() throws IOException {
		File fichero = new File("C:\\RETO3\\Vendedores.dat");

		System.out.println("Introduce un codigo de empleado");
		int codEmp = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduce un nombre");
		String nombre = sc.nextLine();
		System.out.println("Introduce el numero de ventas del empleado");
		int ventasMes = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduce una zona");
		String zona = sc.nextLine();
		
		Vendedor vendedor = new Vendedor(codEmp, nombre, ventasMes, zona);
		
		if(fichero.exists()) {
			FileOutputStream Vendedores2 = new FileOutputStream(fichero, true);
			MiObjectOutputStream aniadirVentasCabecera = new MiObjectOutputStream(Vendedores2);
			aniadirVentasCabecera.writeObject(vendedor);
			aniadirVentasCabecera.close();
			Vendedores2.close();
			
		}else {
			FileOutputStream Vendedores = new FileOutputStream(fichero);
			ObjectOutputStream aniadirVentas = new ObjectOutputStream(Vendedores);
			aniadirVentas.writeObject(vendedor);
			aniadirVentas.close();
			Vendedores.close();
		}	
	}
	
	public static void listarVendedores() throws IOException {
		String mensaje;
		File fichero = new File("C:\\RETO3\\Vendedores.dat");
		
		FileInputStream VendedoresI = new FileInputStream(fichero);
		ObjectInputStream leerVentas = new ObjectInputStream(VendedoresI);
		
		try {
			while(true) {
					Vendedor vendedor = (Vendedor)leerVentas.readObject();
					mensaje = vendedor.toString();
					System.out.println(mensaje);
			}
			
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			System.out.println("No quedan mas objetos");
			leerVentas.close();
		
		} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
			System.out.println("Error");
		}
	}
	
	public static void copiaSeguridad() {
		String ruta;
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la fecha a dia de hoy");
		ruta = sc.nextLine();
		File fichero = new File("C:\\RETO3\\Vendedores.dat");
		File ficheroCopia = new File("C:\\RETO3\\" + ruta + ".dat");
		
		try {
			FileInputStream VendedoresI = new FileInputStream(fichero);
			ObjectInputStream leerVentas = new ObjectInputStream(VendedoresI);
			FileOutputStream Vendedores2 = new FileOutputStream(ficheroCopia, true);
			FileOutputStream Vendedores = new FileOutputStream(ficheroCopia);
			MiObjectOutputStream aniadirVentasCabecera = new MiObjectOutputStream(Vendedores2);
			ObjectOutputStream aniadirVentas = new ObjectOutputStream(Vendedores);

			
			while(true) {
				Vendedor vendedor;
				vendedor = (Vendedor)leerVentas.readObject();
				if(ficheroCopia.exists()) {
					aniadirVentasCabecera.writeObject(vendedor);
					aniadirVentasCabecera.close();
					
				}else {
					aniadirVentas.writeObject(vendedor);
					aniadirVentas.close();
					Vendedores2.close();
					Vendedores.close();
				}

				leerVentas.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
		} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
		}
	}
	
}
