package com.proyecto.estructuras;
import com.proyecto.modelos.*;
public class ListaDobleLibro {
	protected NodoLibro P;
	
	ListaDobleLibro(){
		this.P = null;
	}

	public NodoLibro getP() {
		return P;
	}

	public void setP(NodoLibro p) {
		P = p;
	}
}
