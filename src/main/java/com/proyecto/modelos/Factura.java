package com.proyecto.modelos;
import com.proyecto.estructuras.*;
import java.util.Scanner;
public class Factura {
	private String fecha;
	private double total;
	private double descuento;
	private Cliente p;
	private LDCarrito L;
	private Vendedor V;
	public Factura() {
	}
	public Factura(String fecha, double total, double descuento, Cliente p, LDCarrito l, Vendedor v) {
		this.fecha = fecha;
		this.total = total;
		this.descuento = descuento;
		this.p = p;
		L = l;
		V = v;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public Cliente getP() {
		return p;
	}
	public void setP(Cliente p) {
		this.p = p;
	}
	public LDCarrito getL() {
		return L;
	}
	public void setL(LDCarrito l) {
		L = l;
	}
	public Vendedor getV() {
		return V;
	}
	public void setV(Vendedor v) {
		V = v;
	}
	@Override
	public String toString() {
		return "Factura: fecha=" + fecha + ", total=" + total + ", descuento=" + descuento + "";
	}
	public void mostrar(){
                System.out.println("--------------------------");
		System.out.println(this.toString()+ "\n"+p.toString()+"\n"+L.toString()+"\n"+V.toString());
	}
	public void leer() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese la fecha de la factura: ");
		fecha = sc.nextLine();

		System.out.print("Ingrese el total: ");
		total = sc.nextDouble();

		System.out.print("Ingrese el descuento: ");
		descuento = sc.nextDouble();
		sc.nextLine(); // limpiar el buffer

		System.out.println("\n--- Datos del cliente ---");
		p.leer();

		System.out.println("\n--- Datos del libro ---");
		L.leer();

		System.out.println("\n--- Datos del vendedor ---");
		V.leer();
	}
}
