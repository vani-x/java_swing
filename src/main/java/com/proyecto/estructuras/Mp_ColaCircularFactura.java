package com.proyecto.estructuras;
import com.proyecto.modelos.*;

import java.io.Serializable;
import java.util.Scanner;
public class Mp_ColaCircularFactura  implements Serializable{
	private ColaCircularFactura[] c=new ColaCircularFactura[100];
    private int n; // número de colas

    public Mp_ColaCircularFactura(int n) {
        this.n = n;
        for (int i = 0; i < n; i++)
            c[i] = new ColaCircularFactura();
    }

    // 1. nroElem(i)
    public int nroElem(int i) {
        return c[i].nroElem();
    }

    // 2. esVacia(i)
    public boolean esVacia(int i) {
        return c[i].esVacia();
    }

    // 3. esLlena(i)
    public boolean esLlena(int i) {
        return c[i].esLlena();
    }

    // 4. adicionar(i, factura)
    public void adicionar(int i, Factura f) {
        c[i].adi(f);
    }

    // 5. eliminar(i)
    public Factura eliminar(int i) {
        return c[i].eli();
    }

    // 6. llenar(n)
    public void llenar(int n) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < this.n; i++) {
            System.out.println("Llenando cola circular #" + (i + 1));
            System.out.print("¿Cuántas facturas desea adicionar?: ");
            int cant = sc.nextInt();
            c[i].llenar(cant);
        }
    }

    // 7. llenar(i, n)
    public void llenar(int i, int n) {
        System.out.println("Llenando cola circular #" + (i + 1));
        c[i].llenar(n);
    }

    // 8. mostrar()
    public void mostrar() {
        for (int i = 0; i < n; i++) {
            c[i].mostrar();
        }
    }

    // 9. mostrar(i)
    public void mostrar(int i) {
        System.out.println("\nFactura #" + (i + 1) + ":");
        c[i].mostrar();
    }

    // 10. vaciar(i, c)
    public void vaciar(int i, ColaCircularFactura aux) {
        c[i].vaciar(aux);
    }

    // 11. vaciar(i, j)
    public void vaciar(int i, int j) {
        c[j].vaciar(c[i]);
    }

    // 12. getN()
    public int getN() {
        return n;
    }
}
