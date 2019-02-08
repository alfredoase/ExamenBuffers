package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class LaunchView extends JFrame {

	private JButton comparar, buscar, añadir, recuperar, recuperarTodos, buscarString;
	private JTextArea textArea;
	private JTextField fichero1,fichero2,tit_lib,aut_lib,año_lib,edi_lib,pag_lib,tit_lib_rec, stringBuscar, ficheroBuscar;
	private JTextField palabra;
	private JLabel label_f1,label_f2,label_pal,label_tit, label_autor, label_año, label_editor, label_paginas, label_recuperar, labelPalabraBuscar, labelFicheroBuscar;
	private JCheckBox primera;
	
	private JPanel panel;

	public LaunchView() {
		
		setBounds(200,200,1000,450);
		setTitle("Proyecto Buffers");	
		panel = new JPanel();
		
		comparar = new JButton("Comparar contenido");
		comparar.setPreferredSize(new Dimension(150, 26));
		buscar = new JButton("Buscar palabra");
		buscar.setPreferredSize(new Dimension(150, 26));
		añadir = new JButton("Añadir Libro");
		añadir.setPreferredSize(new Dimension(150, 26));
					
		fichero1 = new JTextField("",10);
		fichero2 = new JTextField("",10);
		palabra = new JTextField("",10);
		
		label_f1 = new JLabel("Fichero 1:");
		label_f2 = new JLabel("Fichero 2:");
		label_pal = new JLabel("Palabra:");
		
		primera = new JCheckBox("Primera aparición");
		primera.isSelected();
		
		label_tit = new JLabel("Titulo:");
		label_autor = new JLabel("Autor:");
		label_año = new JLabel("Año:");
		label_editor = new JLabel("Editor:");
		label_paginas = new JLabel("Paginas:");
		
		tit_lib = new JTextField("",10);
		aut_lib = new JTextField("",10);
		año_lib = new JTextField("",10);
		edi_lib = new JTextField("",10);
		pag_lib = new JTextField("",10);

		label_recuperar = new JLabel("Titulo libro para recuperar '(Introducir 'Libro(Nombre del libro).txt')': ");
		tit_lib_rec = new JTextField("",10);
		recuperar = new JButton("Recuperar libro");
		recuperar.setPreferredSize(new Dimension(150, 26));
		recuperarTodos = new JButton("Recuperar todos los libros");
		recuperarTodos.setPreferredSize(new Dimension(150, 26));
		
		labelPalabraBuscar = new JLabel("String a buscar: ");
		stringBuscar = new JTextField("",20);
		labelFicheroBuscar = new JLabel("Fichero a buscar: ");
		ficheroBuscar = new JTextField("",20);
		buscarString = new JButton("Buscar string");
		buscarString.setPreferredSize(new Dimension(150, 26));
		
		textArea = new JTextArea(20, 80);
		textArea.setBounds(50,50,50,50);
		textArea.setEditable(false);		
		
		panel.add(comparar);
		panel.add(buscar);
		panel.add(label_f1);
		panel.add(fichero1);
		panel.add(label_f2);
		panel.add(fichero2);
		panel.add(label_pal);
		panel.add(palabra);
		panel.add(primera);
		panel.add(label_tit);
		panel.add(tit_lib);
		panel.add(label_autor);
		panel.add(aut_lib);
		panel.add(label_año);
		panel.add(año_lib);
		panel.add(label_editor);
		panel.add(edi_lib);
		panel.add(label_paginas);
		panel.add(pag_lib);
		panel.add(añadir);
		
		panel.add(label_recuperar);
		panel.add(tit_lib_rec);
		panel.add(recuperar);
		panel.add(recuperarTodos);
		
		panel.add(labelFicheroBuscar);
		panel.add(ficheroBuscar);
		panel.add(labelPalabraBuscar);
		panel.add(stringBuscar);
		panel.add(buscarString);
		
		panel.add(textArea);
		
        // Añadimos el JPanel al JFrame
        this.getContentPane().add(panel);		
		
	}	
	
	public JTextField getFicheroBuscar() {
		return ficheroBuscar;
	}

	public void setFicheroBuscar(JTextField ficheroBuscar) {
		this.ficheroBuscar = ficheroBuscar;
	}

	public JButton getBuscarString() {
		return buscarString;
	}

	public void setBuscarString(JButton buscarString) {
		this.buscarString = buscarString;
	}
	
	public JTextField getStringBuscar() {
		return stringBuscar;
	}

	public void setStringBuscar(JTextField stringBuscar) {
		this.stringBuscar = stringBuscar;
	}

	public JButton getRecuperarTodos() {
		return recuperarTodos;
	}

	public void setRecuperarTodos(JButton recuperarTodos) {
		this.recuperarTodos = recuperarTodos;
	}

	public JButton getRecuperar() {
		return recuperar;
	}

	public void setRecuperar(JButton recuperar) {
		this.recuperar = recuperar;
	}

	public JTextField getTit_lib_rec() {
		return tit_lib_rec;
	}

	public void setTit_lib_rec(JTextField tit_lib_rec) {
		this.tit_lib_rec = tit_lib_rec;
	}

	public JTextField getTit_lib() {
		return tit_lib;
	}

	public void setTit_lib(JTextField tit_lib) {
		this.tit_lib = tit_lib;
	}

	public JTextField getAut_lib() {
		return aut_lib;
	}

	public void setAut_lib(JTextField aut_lib) {
		this.aut_lib = aut_lib;
	}

	public JTextField getAño_lib() {
		return año_lib;
	}

	public void setAño_lib(JTextField año_lib) {
		this.año_lib = año_lib;
	}

	public JTextField getEdi_lib() {
		return edi_lib;
	}

	public void setEdi_lib(JTextField edi_lib) {
		this.edi_lib = edi_lib;
	}

	public JTextField getPag_lib() {
		return pag_lib;
	}

	public void setPag_lib(JTextField pag_lib) {
		this.pag_lib = pag_lib;
	}

	public JButton getComparar() {
		return comparar;
	}

	public void setComparar(JButton comparar) {
		this.comparar = comparar;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}
	
	public JButton getAñadir() {
		return añadir;
	}

	public void setAñadir(JButton añadir) {
		this.añadir = añadir;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
			
	public JTextField getFichero1() {
		return fichero1;
	}

	public void setFichero1(JTextField fichero1) {
		this.fichero1 = fichero1;
	}

	public JTextField getFichero2() {
		return fichero2;
	}

	public void setFichero2(JTextField fichero2) {
		this.fichero2 = fichero2;
	}
	
	public JTextComponent getPalabra() {
		return palabra;
	}

	public void setPalabra(JTextComponent palabra) {
		this.palabra = (JTextField) palabra;
	}
	
	public JCheckBox getPrimera() {
		return primera;
	}

	public void setPrimera(JCheckBox primera) {
		this.primera = (JCheckBox) primera;
	}

	public void showError(String m){
		JOptionPane.showMessageDialog(this.panel,
			    m,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void showAdvert(String m){
		JOptionPane.showMessageDialog(this.panel,
			    m,
			    "Advertencia",
			    JOptionPane.INFORMATION_MESSAGE);
	}


}
