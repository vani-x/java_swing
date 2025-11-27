package com.proyecto.estructuras;
import com.proyecto.modelos.*;
import java.util.Scanner;

public class LDCarrito {
	private NodoCarrito p;

	public LDCarrito() {
		this.p = null;
	}

	public NodoCarrito getP() {
		return p;
	}

	public void setP(NodoCarrito p) {
		this.p = p;
	}

	public boolean esVacia() {
		if (p == null)
			return true;
		return false;
	}

	public void adiPrincipio(Libro1 z) {
		NodoCarrito nuevo = new NodoCarrito();
		nuevo.setLibro(z);
		nuevo.setSig(p);
		p = nuevo;
	}

	public void adiFinal(Libro1 z) {
		NodoCarrito nuevo = new NodoCarrito();
		nuevo.setLibro(z);

		if (esVacia())
			p = nuevo;
		else {
			NodoCarrito R = p;
			while (R.getSig() != null) {
				R = R.getSig();
			}
			R.setSig(nuevo);
		}
	}

	@Override
	public String toString() {
		String s = "";
		NodoCarrito R = p;
		while (R != null) {
			s += R.getLibro() + "\n";
			R = R.getSig();
		}
		return s;
	}

	public void mostrar() {
		NodoCarrito R = p;
		while (R != null) {
			System.out.println(R.getLibro());
			R = R.getSig();
		}
	}

	public int nroNodos() {
		NodoCarrito R = p;
		int c = 0;
		if (this.esVacia())
			return 0;
		else {
			while (R != null) {
				c++;
				R = R.getSig();
			}
		}
		return c;
	}

	public NodoCarrito eliPrincipio() {
		NodoCarrito x = null;
		if (!esVacia()) {
			x = p;
			p = p.getSig();
			x.setSig(null);
		}
		return x;
	}

	public NodoCarrito eliFinal() {
		NodoCarrito x = null;
		if (!esVacia()) {
			if (nroNodos() == 1) {
				x = p;
				p = null;
			} else {
				NodoCarrito R = p;
				NodoCarrito S = p;
				while (R.getSig() != null) {
					S = R;
					R = R.getSig();
				}
				x = R;
				S.setSig(null);
			}
		}
		return x;
	}

	public void leer() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese numero de libros: ");
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			Libro1 l = new Libro1("", "", 0, "", 0);
			l.leer();
			adiFinal(l);
		}
	}
}