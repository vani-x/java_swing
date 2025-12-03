package com.proyecto.modelos;
import java.io.Serializable;

import com.proyecto.estructuras.*;
public class Libreria  implements Serializable{
	private String nombre;
	private String direccion;
	private Mp_ColaCircularFactura F;
	private ListaDobleCircularLibro LD;
	public Libreria(String nombre, String direccion, Mp_ColaCircularFactura f, ListaDobleCircularLibro lD) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		F = f;
		LD = lD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Mp_ColaCircularFactura getF() {
		return F;
	}
	public void setF(Mp_ColaCircularFactura f) {
		F = f;
	}
	public ListaDobleCircularLibro getLD() {
		return LD;
	}
	public void setLD(ListaDobleCircularLibro lD) {
		LD = lD;
	}
	@Override
	public String toString() {
		return "Libreria nombre=" + nombre + ", \ndireccion=" + direccion + "";
	}
	public void mostrar() {
		System.out.println(this.toString());
		F.mostrar();
		//LD.mostrar(); revisar esto me da error
		
	}
}
