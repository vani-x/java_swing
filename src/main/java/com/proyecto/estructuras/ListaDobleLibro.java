package com.proyecto.estructuras;
import java.io.Serializable;

import com.proyecto.modelos.*;
public class ListaDobleLibro  implements Serializable{
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
