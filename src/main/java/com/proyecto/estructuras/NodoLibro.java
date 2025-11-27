package com.proyecto.estructuras;
import com.proyecto.modelos.*;

public class NodoLibro {
	
	private Libro1 dato;
	private NodoLibro sig; //definiendo el campo enlace sig
	private NodoLibro ant;
	
	public NodoLibro() {
		sig = null;
		ant = null;
	}
	public NodoLibro(Libro1 dato) {
		this.dato = dato;
		sig = null;
		ant = null;
	}

	public Libro1 getDato() {
		return dato;
	}

	public void setDato(Libro1 dato) {
		this.dato = dato;
	}

	public NodoLibro getAnt() {
		return ant;
	}

	public void setAnt(NodoLibro ant) {
		this.ant = ant;
	}

	public NodoLibro getSig() {
		return sig;
	}

	public void setSig(NodoLibro sig) {
		this.sig = sig;
	}
	
}

