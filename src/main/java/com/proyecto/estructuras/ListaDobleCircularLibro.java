package com.proyecto.estructuras;
import com.proyecto.modelos.*;
import java.util.Scanner;

public class ListaDobleCircularLibro extends ListaDobleLibro{
	public ListaDobleCircularLibro() {
		super();
	}
	public boolean esVacia() {
		if(this.P == null)
			return true;
		return false;
	}
	int nroNodos() {
		int c = 0;
		NodoLibro R = P;
		if(!esVacia()) {
			while(R.getSig() != P) {
				c++;
				R = R.getSig();
			}
			c++;
		}
		return c;
	}
	void adiPrimero(String nombre, String autor, int paginas, String estado, int precio){
		NodoLibro nuevo = new NodoLibro();
		Libro1 n1 = new Libro1(nombre, autor, paginas, estado, precio);
		nuevo.setDato(n1);

		if(esVacia()) {
			P = nuevo;
			P.setSig(P);
			P.setAnt(P);
		}
		else {
			NodoLibro R = P.getAnt();
			nuevo.setSig(P);
			P.setAnt(nuevo);
			P = nuevo;
			P.setAnt(R);
			R.setSig(P);
		}
	}
	void mostrar() {
		NodoLibro R = P;
		System.out.println();
		if(!esVacia()) {
			while(R.getSig() != P) {
				Libro1 z = R.getDato();
				z.mostrar();
				R = R.getSig();
			}
			Libro1 z = R.getDato();
			z.mostrar();
		}
		else
			System.out.println("Lista Vacia");
	}
	// los metedos tienen que estar en public para accerder desde otra clase
	public void adiFinal(String nombre, String autor, int paginas, String estado, int precio) {
		NodoLibro nuevo = new NodoLibro();
		Libro1 n1 = new Libro1(nombre, autor, paginas, estado, precio);
		nuevo.setDato(n1);

		if(esVacia()) {
			P = nuevo;
			P.setSig(P);
			P.setAnt(P);
		}
		else {
			NodoLibro R = P.getAnt();
			R.setSig(nuevo);
			nuevo.setAnt(R);
			nuevo.setSig(P);
			P.setAnt(nuevo);
		}
	}

	NodoLibro eliPrimero() {
		NodoLibro x = new NodoLibro();
		if(!esVacia()) {
			if(nroNodos() == 1) {
				x = P;
				P = null;
			}else {
				x = P;
				NodoLibro R = P.getAnt();
				P = P.getSig();
				P.setAnt(R);
				R.setSig(P);
			}
			x.setSig(null);
			x.setAnt(null);
		}
		return x;
	}

	NodoLibro eliFinal() {
		NodoLibro x = new NodoLibro();
		if(!esVacia()) {
			if(nroNodos() == 1) {
				x = P;
				P = null;
			}else {
				x = P.getAnt();
				NodoLibro R = x.getAnt();
				R.setSig(P);
				P.setAnt(R);
			}
			x.setSig(null);
			x.setAnt(null);
		}
		return x;
	}

	void leer1(int n) {
		Scanner lee =  new Scanner(System.in);
		for (int i = 1; i <= n; i++) {
			System.out.println("ingresa datos del libro");
			String nom = lee.next();
			String autor = lee.next();
			int paginas = lee.nextInt();
			String estado = lee.next();
			int precio = lee.nextInt();
			adiPrimero(nom, autor, paginas, estado, precio);
		}
	}

	void leer2(int n) {
		Scanner lee =  new Scanner(System.in);
		for (int i = 1; i <= n; i++) {
			System.out.println("nom - edad");
			String nom = lee.next();
			String autor = lee.next();
			int paginas = lee.nextInt();
			String estado = lee.next();
			int precio = lee.nextInt();
			adiFinal(nom, autor, paginas, estado, precio);
		}
	}
}
