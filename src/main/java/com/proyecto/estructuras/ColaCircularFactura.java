package com.proyecto.estructuras;
import java.io.Serializable;
import java.util.Scanner;
import com.proyecto.modelos.*;

public class ColaCircularFactura extends ColaFactura implements Serializable{
	public ColaCircularFactura() {
		super();
	}
	public int nroElem() {
		return (fi - fr + MAX) % MAX;
	}
	public boolean esVacia() {
		if(nroElem() == 0)
			return true;
		return false;
		
	}
	public boolean esLlena() {
		if(nroElem() == MAX-1)
			return true;
		return false;
	}
	public void adi(Factura elem) {
		if(!esLlena()) {
			fi = (fi + 1) % MAX;
			v[fi] = elem;
		}else
			System.out.println("cola circular llena!!!");
	}
	public Factura eli() {
		Factura elem = new Factura();
		if(!esVacia()) {
			fr = (fr + 1) % MAX;
			elem = v[fr];
		}else
			System.out.println("Cola circular vacia!!");
		return elem;
	}
	
	public void vaciar(ColaCircularFactura z) {
		while(!z.esVacia()) {
			adi(z.eli());
		}
	}
	public void llenar(int n) {
		Scanner lee = new Scanner(System.in);
		for (int i = 1; i <= n; i++) {
			System.out.println("Intr. factura");
			Factura elem = new Factura();
			elem.leer();
			adi(elem);
		}
	}
	public void mostrar() {
		ColaCircularFactura aux = new ColaCircularFactura();
		while(!esVacia()) {
			Factura elem = eli();
			elem.mostrar();
			aux.adi(elem);
		}
		vaciar(aux);
	}
}

