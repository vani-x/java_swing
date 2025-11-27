package com.proyecto.estructuras;
import com.proyecto.modelos.*;

public class NodoCarrito {

    private Libro1 libro;

    private NodoCarrito ant,sig;

    public NodoCarrito() {
        this.ant = null;
        this.sig = null;
    }

    public Libro1 getLibro() {
        return libro;
    }

    public void setLibro(Libro1 libro) {
        this.libro = libro;
    }

    public NodoCarrito getSig() {
        return sig;
    }

    public void setSig(NodoCarrito sig) {
        this.sig = sig;
    }
    public NodoCarrito getAnt() {
        return ant;
    }
    public void setAnt(NodoCarrito ant) {
        this.ant = ant;
    }
}
