package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.text.View;

import model.*;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar, actionListener_añadir,
			actionListener_recuperar, actionListener_recuperarTodos;
	private ActionListener actionListener_buscarString;
	private String error = "Error no se encuentra el archivo, o es posible que haya algun error en la insercion de los datos.";

	public GestionEventos(GestionDatos model, LaunchView view) {
		this.model = model;
		this.view = view;
	}

	public void contol() {
		// Comparar
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					call_compararContenido();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// call_GuardarLibro();
			}
		};
		view.getComparar().addActionListener(actionListener_comparar);

		// Buscar
		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_buscarPalabra();
			}
		};
		view.getBuscar().addActionListener(actionListener_buscar);

		// Añadir
		actionListener_añadir = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					call_GuardarLibro();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getAñadir().addActionListener(actionListener_añadir);

		// Recuperar
		actionListener_recuperar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					call_RecuperarLibro();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getRecuperar().addActionListener(actionListener_recuperar);

		// Recuperar todos
		actionListener_recuperarTodos = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					call_RecuperarLibroTodo();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getRecuperarTodos().addActionListener(actionListener_recuperarTodos);

		// Buscar string
		actionListener_buscarString = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					call_recuperarString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getBuscarString().addActionListener(actionListener_buscarString);
	}

	private int call_compararContenido() throws FileNotFoundException {
		try {
			String fichero1 = view.getFichero1().getText();
			String fichero2 = view.getFichero2().getText();

			model.compararContenido(fichero1, fichero2);

			if (model.compararContenido(fichero1, fichero2) == true) {
				view.getTextArea().setText("SON IGUALES. \n");
			} else {
				view.getTextArea().setText("SON DIFERENTES. \n");
			}
		} catch (IOException e) {
			view.showError(error);
		}

		return 1;
	}

	private int call_buscarPalabra() {
		try {
			String fichero1 = view.getFichero1().getText();
			String palabraBuscar = view.getPalabra().getText();
			Boolean primera_aparicion = view.getPrimera().isSelected();
			int numeroLinea = model.numeroLineaPalabra;

			if (model.buscarPalabra(fichero1, palabraBuscar, primera_aparicion) == 1) {
				view.showAdvert("SI NO APARECE NADA, VOLVER A PULSAR EL BOTON");
				view.getTextArea().setText(
						"Primera aparicion de la palabra (" + palabraBuscar + ") en la linea: " + numeroLinea + ".");
			}
			if (model.buscarPalabra(fichero1, palabraBuscar, primera_aparicion) == 0) {
				view.showAdvert("SI NO APARECE NADA, VOLVER A PULSAR EL BOTON");
				view.getTextArea().setText(
						"Segunda aparicion de la palabra (" + palabraBuscar + ") en la linea: " + numeroLinea + ".");
			}
		} catch (IOException e) {
			view.showError(error);
		}
		return 1;
	}

	private int call_GuardarLibro() throws FileNotFoundException {
		try {
			Libro libro = new Libro();
			int id = libro.getId() + 1;
			String titLib = view.getTit_lib().getText();
			String autLib = view.getAut_lib().getText();
			int fecha = Integer.parseInt(view.getAño_lib().getText());
			String ediLib = view.getEdi_lib().getText();
			int pagLib = Integer.parseInt(view.getPag_lib().getText());

			if (!(titLib.isEmpty() || autLib.isEmpty() || pagLib < 0 || ediLib.isEmpty() || pagLib < 0)) {
				libro.setId(id + 1);
				libro.setTitulo(titLib);
				libro.setAutor(autLib);
				libro.setAño(fecha);
				libro.setEditor(ediLib);
				libro.setPaginas(pagLib);

				model.guardar_libro(libro);

				view.getTit_lib().setText(null);
				view.getAut_lib().setText(null);
				view.getAño_lib().setText(null);
				view.getEdi_lib().setText(null);
				view.getPag_lib().setText(null);
			}
		} catch (Exception e) {
			view.showError(error + e);
		}
		return 1;
	}

	private int call_RecuperarLibro() {
		try {
			String titRec = view.getTit_lib_rec().getText();

			Libro libr = model.recuperar_libro(titRec);

			view.getTextArea()
					.setText("Título: " + libr.getTitulo() + "\n" + "Autor: " + libr.getAutor() + "\n" + "Editor: "
							+ libr.getEditor() + "\n" + "Paginás: " + libr.getPaginas() + "\n" + "Año: "
							+ libr.getAño());
		} catch (Exception e) {
			view.showError(error + e);
		}
		return 1;
	}

	private int call_RecuperarLibroTodo() {
		view.getTextArea().setText(null);

		try {
			ArrayList<Libro> libr1 = model.recuperar_todos();
			int i = 0;
			Iterator itP = libr1.iterator();
			while (itP.hasNext()) {
				view.getTextArea().append("[Libro " + (i + 1) + "]" + "\n" + "Titulo: " + libr1.get(i).getTitulo()
						+ "\n" + "Autor: " + libr1.get(i).getAutor() + "\n" + "__________" + "\n");
				i++;

				System.out.println("ESTO QUE I: " + i);
				System.out.println("ESTO QUE .get: " + libr1.get(i));
			}
		} catch (Exception e) {
			view.showError(error + e);
		}
		return 1;
	}

	private int call_recuperarString() {
		int aparicionesString = 0;
		String fichero = view.getFicheroBuscar().getText();
		String stringBuscar = view.getStringBuscar().getText();

		if (stringBuscar.isEmpty()) {
			view.showError(error);
		}else {
			try {
				view.getTextArea().setText("Aparicion de la palabra" + stringBuscar + ", "
						+ model.buscarStringPalabra(fichero, stringBuscar) + "veces.");

				if ((model.buscarStringPalabra(fichero, stringBuscar) == 0)) {
					view.showError(error);
				}
			} catch (Exception e) {
				view.showError(error + e);
			}
		}

		return aparicionesString;
	}
}
