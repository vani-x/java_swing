package com.proyecto.modelos;
import com.proyecto.estructuras.*;

import java.io.Serializable;
import java.util.Scanner;
public class Persona  implements Serializable{
	protected String nombre;
	protected int ci;
	protected int telefono;
	protected String genero;
	public Persona(String nombre, int ci, int telefono, String genero) {
		this.nombre = nombre;
		this.ci = ci;
		this.telefono = telefono;
		this.genero = genero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCi() {
		return ci;
	}
	public void setCi(int ci) {
		this.ci = ci;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	@Override
	public String toString() {
		return "nombre=" + nombre + ", ci=" + ci + ", telefono=" + telefono + ", genero=" + genero + " ";
	}
	
	public void mostrar() {
		System.out.println(this.toString());
	}
	public void leer() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Ingrese el nombre: ");
		nombre = sc.nextLine();

		System.out.print("Ingrese el CI: ");
		ci = sc.nextInt();

		System.out.print("Ingrese el teléfono: ");
		telefono = sc.nextInt();
		sc.nextLine(); // limpiar el buffer

		System.out.print("Ingrese el género: ");
		genero = sc.nextLine();
	}
}
