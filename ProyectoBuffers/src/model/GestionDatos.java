package model;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import model.Libro;

import controller.GestionEventos;

public class GestionDatos {

	public static int numeroLineaPalabra, numeroLineaPalabraPr, numeroLineaPalabraSe;

	public ArrayList<Libro> listaLibros = new ArrayList<Libro>();

	public GestionDatos() {

	}

	// TODO: Implementa una función para abrir ficheros

	// TODO: Implementa una función para cerrar ficheros

	// Metodo booleano para comparar el contenido de los ficheros
	public Boolean compararContenido(String fichero1, String fichero2) throws IOException {

		// Abrimos los ficheros y los pasamos por un BufferedReader para poder trabajar
		// sobre ellos de una forma mas comoda
		FileReader fr1 = new FileReader(fichero1);
		BufferedReader br1 = new BufferedReader(fr1);
		String contFicher1 = br1.readLine();
		br1.close();

		FileReader fr2 = new FileReader(fichero2);
		BufferedReader br2 = new BufferedReader(fr2);
		String contFicher2 = br2.readLine();
		br2.close();

		// Se hace la comparacion de los contenido de los ficheros y se devuelve true o
		// false
		if (contFicher1.equals(contFicher2)) {
			return true;
		} else {
			return false;
		}
	}

	// Metodo que devuelve en un int, en el que se busca en un fichero la palabra
	// que le pasemos y dependiendo de un booleano,
	// mostrara una cosa u otra.
	public int buscarPalabra(String fichero1, String palabra, boolean primera_aparicion) throws IOException {
		numeroLineaPalabra = 0;
		numeroLineaPalabraPr = 0;
		numeroLineaPalabraSe = 0;
		int numeroLineas = 0;
		int encontrado = 0;

		FileReader fr1 = new FileReader(fichero1);
		BufferedReader br1 = new BufferedReader(fr1);
		String lineaFichero;

		if (primera_aparicion == true) {
			while ((lineaFichero = br1.readLine()) != null) {
				numeroLineas++;
				if (lineaFichero.contains(palabra)) {
					encontrado++;
					if (encontrado == 1) {
						numeroLineaPalabraPr = numeroLineas;
						numeroLineaPalabra = numeroLineaPalabraPr;
						return 1;
					}
				}
			}
		}

		if (primera_aparicion == false) {
			numeroLineaPalabraSe = numeroLineaPalabraPr;
			while ((lineaFichero = br1.readLine()) != null) {
				numeroLineaPalabraSe++;
				if (lineaFichero.contains(palabra)) {
					numeroLineaPalabra = numeroLineaPalabraSe;
				}
			}
		}
		br1.close();
		fr1.close();
		return 0;
	}

	public int guardar_libro(Libro libro) {
		String ficheroIdentificador;

		ObjectOutputStream out = null;

		try {
			listaLibros.add(libro);

			ficheroIdentificador = "Libro" + libro.getTitulo() + ".txt";
			out = new ObjectOutputStream(new FileOutputStream(ficheroIdentificador));
			out.writeObject(libro);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			cerrarFlujo(out);
		}

		return 0;
	}

	public Libro recuperar_libro(String identificador) {
		Libro lib = null;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(identificador));
			lib = (Libro) in.readObject();
		} catch (ClassNotFoundException ex) {
			System.err.println("Error de fichero");
		} catch (IOException ex) {
			System.err.println("Error IO");
		} finally {
			cerrarFlujo(in);
		}
		return lib;
	}

	public ArrayList<Libro> recuperar_todos() {
		ObjectInputStream in = null;
		ArrayList<Libro> libros = new ArrayList<Libro>();
		try {
			Iterator itP = listaLibros.iterator();

			while (itP.hasNext()) {
				Libro l = (Libro) itP.next();

				libros.add(l);
			}
		} catch (Exception ex) {
			System.err.println("Error de fichero");
		} finally {
			cerrarFlujo(in);
		}
		
		return libros;
	}

	public int buscarStringPalabra(String fichero1, String stringBuscar) throws IOException {
		int contador = 0;
		FileReader fr1 = new FileReader(fichero1);
		BufferedReader br1 = new BufferedReader(fr1);
		
		String lineaFichero = br1.readLine();

		while (lineaFichero != null) {
			if(lineaFichero.contains(stringBuscar)) {
				contador++;
			}
			lineaFichero = br1.readLine();
		}
		
		br1.close();
		fr1.close();
		
		return contador;
	}

	public void cerrarFlujo(Closeable flujoCerrar) {
		try {
			if (flujoCerrar != null) {
				flujoCerrar.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}
}
