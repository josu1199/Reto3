package pack_reto3;

import java.io.*;
import java.util.*;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static ArrayList<Cliente> listaClientes = new ArrayList();
	static ArrayList<Cliente> copiaLista = listaClientes;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
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
						sc.nextLine();
						aniadirCliente();
						break;
					case 2:
						leerFichero();
						break;
					case 3:
						sc.nextLine();
						buscarClientes();
						break;
					case 4:
						sc.nextLine();
						borrarCliente();
						break;
					case 5:
						borrarFichero();
						break;
					case 6:
						informeDeudas();
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
				case 3:
					volcarVentas();
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
									"1. Listar ventas por zona \n");
				opcZona = sc.nextInt();
				switch(opcZona) {
				case 1:
					 listarZonas();
					 break;
				}
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
			File ruta = new File("C:\\RETO3ARCHIVOS");
			File fichero = new File(ruta, "CLIENTES.DAT");
			if(!ruta.exists()) {
				ruta.mkdir();
			}

			System.out.println("Introduce NIF: ");
			NIF = sc.nextLine();
			System.out.println("Introduce nombre: ");
			nombre = sc.nextLine();
			System.out.println("Introduce telefono: ");
			telefono = sc.nextInt();
			sc.nextLine();
			System.out.println("Introduce Direccion: ");
			direccion = sc.nextLine();
			System.out.println("Introduce deuda: ");
			deuda = sc.nextInt();
			
			Cliente cliente = new Cliente(NIF, nombre, telefono, direccion, deuda);
			
			if(fichero.exists()) {
				FileOutputStream fos2 = new FileOutputStream(fichero, true);
				MiObjectOutputStream ficheroSinCabecera = new MiObjectOutputStream(fos2);
				ficheroSinCabecera.writeObject(cliente);
				ficheroSinCabecera.close();
				fos2.close();
			}else{
				FileOutputStream fos1 = new FileOutputStream(fichero);
				ObjectOutputStream ficheroSalida = new ObjectOutputStream(fos1);
				ficheroSalida.writeObject(cliente);
				ficheroSalida.close();
				fos1.close();
			}
			
			listaClientes.add(cliente);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void leerFichero() throws ClassNotFoundException, IOException, FileNotFoundException {
			File fichero = new File("C:\\RETO3ARCHIVOS\\CLIENTES.DAT");
			FileInputStream fis = new FileInputStream(fichero);
			ObjectInputStream ficheroLectura = new ObjectInputStream(fis);
			Cliente c;
			
			try {	
				while(true) {
					c = (Cliente) ficheroLectura.readObject();
					System.out.printf("\nCliente: %s\n",c.toString());
				}			
			} catch(ClassNotFoundException e){
				
		    } catch(EOFException e){
		    
			} catch(IOException e){
				
			} finally {
				fis.close();
				ficheroLectura.close();
			}				
	}
	
	public static void buscarClientes() throws IOException, ClassNotFoundException {
		Cliente c = null;
		String compDNI;
		boolean encontrado = false;
		File fichero = new File("C:\\RETO3ARCHIVOS\\CLIENTES.DAT");
		FileInputStream fos = new FileInputStream(fichero);
		ObjectInputStream ficheroLectura = new ObjectInputStream(fos);	
		System.out.println("Introduce el dni del cliente a comprobar: ");
		compDNI = sc.nextLine();
		try {
			while(!encontrado) {
				c = (Cliente) ficheroLectura.readObject();
				if(c.getNIF().equals(compDNI)) {
					encontrado = true;
				}
				
			}			
			
		} catch (EOFException e) {
			
		}
		
		if(encontrado == true) {
			System.out.println("EL CLIENTE EXISTE");
			System.out.println(c.toString());
		}
		else {
			System.out.println("EL CLIENTE NO EXISTE");
		}
		
	}
	
	public static void borrarCliente() throws IOException, ClassNotFoundException {
		File fichero = new File("C:\\RETO3ARCHIVOS\\CLIENTES.DAT");
		fichero.delete();
		
		String dni;
		Cliente cliente = null;
		
		boolean existe = false;
		
		System.out.println("INTRODUCE DNI");
		dni = sc.nextLine();
		
		for(Cliente c: listaClientes) {
			if(c.getNIF().equals(dni)) {
				existe = true;
				cliente = c;
			}
		}
		if(existe == true) {
			copiaLista.remove(cliente);
			System.out.println("CLIENTE BORRADO");
			for(int i = 0; i < copiaLista.size(); i++) {
				cliente = copiaLista.get(i);
				if(i == 0) {
					FileOutputStream fos1 = new FileOutputStream(fichero);
					ObjectOutputStream ficheroSalida = new ObjectOutputStream(fos1);
					ficheroSalida.writeObject(cliente);
					ficheroSalida.close();
					fos1.close();
				}else{
					FileOutputStream fos2 = new FileOutputStream(fichero, true);
					MiObjectOutputStream ficheroSinCabecera = new MiObjectOutputStream(fos2);
					ficheroSinCabecera.writeObject(cliente);
					ficheroSinCabecera.close();
					fos2.close();		
				}
			}
		}
		else {
			System.out.println("EL CLIENTE QUE QUIERES BORRAR NO EXISTE \nA CONTINUACION TE MOSTRAMOS LOS CLIENTES LISTADOS");
		}
	}
	
	public static void borrarFichero() {
		File fichero = new File("C:\\RETO3ARCHIVOS\\CLIENTES.DAT");
		fichero.delete();
		System.out.println("EL FICHERO SE BORRO COMPLETAMENTE");
	}
	
	public static void informeDeudas() throws IOException {
		Cliente cliente;
		File deudas = new File("C:\\RETO3ARCHIVOS\\DEUDAS.txt");
		for(int i = 0; i < copiaLista.size(); i++) {
			cliente = copiaLista.get(i);
			if(i == 0) {
				FileOutputStream fos1 = new FileOutputStream(deudas);
				ObjectOutputStream ficheroSalida = new ObjectOutputStream(fos1);
				ficheroSalida.writeObject(cliente);
				ficheroSalida.close();
				fos1.close();
			}else{
				FileOutputStream fos2 = new FileOutputStream(deudas, true);
				MiObjectOutputStream ficheroSinCabecera = new MiObjectOutputStream(fos2);
				ficheroSinCabecera.writeObject(cliente);
				ficheroSinCabecera.close();
				fos2.close();		
			}
		}
		System.out.println("FICHERO CREADO, SE MOSTRARA SU CONTENIDO A CONTINUACION: ");

		FileInputStream fis = new FileInputStream(deudas);
		ObjectInputStream ficheroLectura = new ObjectInputStream(fis);
		
		try {	
			while(true) {
				cliente = (Cliente) ficheroLectura.readObject();
				System.out.printf("\nCliente: %s\n",cliente.generarInforme());
			}			
		} catch(ClassNotFoundException e){
			
	    } catch(EOFException e){
	    	
	    }finally {
			fis.close();
			ficheroLectura.close();
	    }	
	}

	public static void cargarVendedores() {
		int cont = 0;
		
		File ruta = new File("C:\\RETO3");
		File fichero = new File(ruta, "Vendedores.dat");
		if(!ruta.exists()) {
			ruta.mkdir();
		}
		
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
		File ruta = new File("C:\\RETO3");
		File fichero = new File(ruta, "Vendedores.dat");
		if(!ruta.exists()) {
			ruta.mkdir();
		}

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
		while(!zona.equals("Norte") && !zona.equals("Sur") && !zona.equals("Este") && !zona.equals("Oeste")) {
			System.out.println("Introduce una zona");
			zona = sc.nextLine();
		}
		
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
		File ruta = new File("C:\\RETO3");
		File fichero = new File(ruta, "Vendedores.dat");
		
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
	
	public static void copiaSeguridad() throws IOException {
		String dia, mes, anio;
		Scanner sc = new Scanner(System.in);
		
		GregorianCalendar gregCal = new GregorianCalendar();
		dia = Integer.toString(gregCal.get(Calendar.DATE)); //CON ESTO LO DEFINE COMO EL DIA ACTUAL QUE MUESTRA EL SISTEMA
		mes = Integer.toString(gregCal.get(Calendar.MONTH) + 1); //CON ESTO LO DEFINE COMO EL MES ACTUAL QUE MUESTRA EL SISTEMA
		anio = Integer.toString(gregCal.get(Calendar.YEAR)); //CON ESTO LO DEFINE COMO EL AÑO ACTUAL QUE MUESTRA EL SISTEMA
		
		File fichero = new File("C:\\RETO3\\Vendedores.dat");
		File ficheroCopia = new File("C:\\RETO3\\" + dia + "-" + mes + "-" + anio + ".dat");
		
		
		FileInputStream VendedoresI = new FileInputStream(fichero);
		ObjectInputStream leerVentas = new ObjectInputStream(VendedoresI);
		FileOutputStream Vendedores2 = new FileOutputStream(ficheroCopia, true);
		FileOutputStream Vendedores = new FileOutputStream(ficheroCopia);
		MiObjectOutputStream aniadirVentasCabecera = new MiObjectOutputStream(Vendedores2);
		ObjectOutputStream aniadirVentas = new ObjectOutputStream(Vendedores);
		
		try {
			
			while(true) {	
				Vendedor vendedor = (Vendedor)leerVentas.readObject();
				if(ficheroCopia.exists()) {
					aniadirVentasCabecera.writeObject(vendedor);
				
				}else {
					aniadirVentas.writeObject(vendedor);
					
				}
				System.out.println("vuelta");
			}
			
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
			aniadirVentas.close();
			aniadirVentasCabecera.close();
			Vendedores2.close();
			Vendedores.close();
			leerVentas.close();
			
		} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
		}
	}
	
	public static void volcarVentas() throws IOException {
		Scanner sc = new Scanner(System.in);
		int mes, cont = 0;
		Vendedor[] listaVendedores = new Vendedor[12];
		System.out.println("Introduce el mes actual");
		mes = sc.nextInt();
		
		File ruta = new File("C:\\RETO3");
		File ficheroNuevo = new File(ruta, "VentasZona.dat");
		File fichero = new File(ruta, "Vendedores.dat");
		if(!ruta.exists()) {
			ruta.mkdir();
		}
		
		VentasZona norte = new VentasZona();
		VentasZona sur = new VentasZona();
		VentasZona este = new VentasZona();
		VentasZona oeste = new VentasZona();
		
		try {
			if(!ficheroNuevo.exists()) {
				FileInputStream ventas = new FileInputStream(fichero);
				ObjectInputStream leerVentas = new ObjectInputStream(ventas);
				while(true) {
					System.out.println("----");
					Vendedor vendedor = (Vendedor)leerVentas.readObject();
					if(vendedor.getZona().equals("Norte")) {
						norte.sumarVentas(mes, vendedor.getVentasMes());
					}
					else if(vendedor.getZona().equals("Sur")) {
						sur.sumarVentas(mes, vendedor.getVentasMes());
					}
					else if(vendedor.getZona().equals("Este")) {
						este.sumarVentas(mes, vendedor.getVentasMes());
					}
					else if(vendedor.getZona().equals("Oeste")) {
						oeste.sumarVentas(mes, vendedor.getVentasMes());
					}
					
					listaVendedores[cont] = vendedor;
					cont++;
				}
			}else if(ficheroNuevo.exists()) {
				FileInputStream ventas = new FileInputStream(fichero);
				ObjectInputStream leerVentas = new ObjectInputStream(ventas);
				
				FileInputStream zonas = new FileInputStream(ficheroNuevo);
				ObjectInputStream leerZonas = new ObjectInputStream(zonas);
				
				norte = (VentasZona)leerZonas.readObject();
				sur = (VentasZona)leerZonas.readObject();
				este = (VentasZona)leerZonas.readObject();
				oeste = (VentasZona)leerZonas.readObject();			
				leerZonas.close();
				while(true) {
					System.out.println("----");
					Vendedor vendedor = (Vendedor)leerVentas.readObject();
					if(vendedor.getZona().equals("Norte")) {
						norte.sumarVentas(mes, vendedor.getVentasMes());
					}
					else if(vendedor.getZona().equals("Sur")) {
						sur.sumarVentas(mes, vendedor.getVentasMes());
					}
					else if(vendedor.getZona().equals("Este")) {
						este.sumarVentas(mes, vendedor.getVentasMes());
					}
					else if(vendedor.getZona().equals("Oeste")) {
						oeste.sumarVentas(mes, vendedor.getVentasMes());
					}
					
					listaVendedores[cont] = vendedor;
					cont++;
				}
			}
			
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
			boolean salir = false;
			if(ficheroNuevo.exists()) {
				ficheroNuevo.delete();
			}
			
			fichero.delete();
			
			FileOutputStream Vendedores2 = new FileOutputStream(ficheroNuevo, true);
			MiObjectOutputStream aniadirZonasCabecera = new MiObjectOutputStream(Vendedores2);
			FileOutputStream Vendedores = new FileOutputStream(ficheroNuevo);
			ObjectOutputStream aniadirZonas = new ObjectOutputStream(Vendedores);
			
			aniadirZonas.writeObject(norte);
			aniadirZonasCabecera.writeObject(sur);
			aniadirZonasCabecera.writeObject(este);
			aniadirZonasCabecera.writeObject(oeste);
			
			FileOutputStream Vendedores4 = new FileOutputStream(fichero, true);
			FileOutputStream Vendedores3 = new FileOutputStream(fichero);
			MiObjectOutputStream aniadirVentasCabecera = new MiObjectOutputStream(Vendedores4);
			ObjectOutputStream aniadirVentas = new ObjectOutputStream(Vendedores3);
			
			for(int i = 0; i < listaVendedores.length && !salir; i++) {
				System.out.println("----");
				listaVendedores[i].setVentasMes(0);
				
				if(!fichero.exists()) {
					Vendedor vendedor = listaVendedores[i];
					aniadirVentas.writeObject(vendedor);
				}else {
					Vendedor vendedor = listaVendedores[i];
					aniadirVentasCabecera.writeObject(vendedor);
				}
				
				if(listaVendedores[i+1] == null) {
					salir = true;
				}
				
			}
			
			aniadirZonas.close();
			aniadirZonasCabecera.close();
			aniadirVentasCabecera.close();
			aniadirVentas.close();
			
		} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
		}
	}
	
	public static void listarZonas() throws IOException {
		boolean salir = false;
		
		File ruta = new File("C:\\RETO3");
		File ficheroZona = new File(ruta, "VentasZona.dat");
		
		VentasZona norte = new VentasZona();
		VentasZona sur = new VentasZona();
		VentasZona este = new VentasZona();
		VentasZona oeste = new VentasZona();
		
		FileInputStream zonas = new FileInputStream(ficheroZona);
		ObjectInputStream leerZonas = new ObjectInputStream(zonas);
		
		try {
			norte = (VentasZona)leerZonas.readObject();
			sur = (VentasZona)leerZonas.readObject();
			este = (VentasZona)leerZonas.readObject();
			oeste = (VentasZona)leerZonas.readObject();			
			
			leerZonas.close();
			
			System.out.println("Meses:                      1    2    3   4   5   6   7   8   9   10   11   12");
			
			System.out.print("Ventas de la zona Norte: ");
			for(int i = 0; i < norte.getVentasMensuales().length && !salir; i++) {
				if(norte.getVentasMensuales()[i] == 0) {
					salir = true;
				}else {
					System.out.print(norte.getVentasMensuales()[i] + "   ");
				}
				
				if(norte.getVentasMensuales()[i+1] == 0) {
					salir = true;
				}
			}
			System.out.println("");
			
			System.out.print("Ventas de la zona Sur: ");
			for(int i = 0; i < sur.getVentasMensuales().length && !salir; i++) {
				if(sur.getVentasMensuales()[i] == 0) {
					salir = true;
				}else {
					System.out.print(sur.getVentasMensuales()[i] + "   ");
				}
				
				if(sur.getVentasMensuales()[i+1] == 0) {
					salir = true;
				}
			}
			System.out.println("");
			
			System.out.print("Ventas de la zona Este: ");
			for(int i = 0; i < este.getVentasMensuales().length && !salir; i++) {
				if(este.getVentasMensuales()[i] == 0) {
					salir = true;
				}else {
					System.out.print(este.getVentasMensuales()[i] + "   ");
				}
				
				if(este.getVentasMensuales()[i+1] == 0) {
					salir = true;
				}
				System.out.println("");
			}
			System.out.println("");
			
			System.out.print("Ventas de la zona Oeste: ");
			for(int i = 0; i < oeste.getVentasMensuales().length && !salir; i++) {
				if(oeste.getVentasMensuales()[i] == 0) {
					salir = true;
				}else {
					System.out.print(oeste.getVentasMensuales()[i] + "   ");
				}
				
				if(oeste.getVentasMensuales()[i+1] == 0) {
					salir = true;
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}
		
	}
}
