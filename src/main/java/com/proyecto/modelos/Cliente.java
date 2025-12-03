package com.proyecto.modelos;
import java.io.Serializable;
import java.util.Scanner;
public class Cliente extends Persona  implements Serializable{
	private String correo;
	private String direccion;
	public Cliente(String nombre, int ci, int telefono, String genero, String correo, String direccion) {
		super(nombre, ci, telefono, genero);
		this.correo = correo;
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public String toString() {
		return "Cliente: "+super.toString()+"correo=" + correo + ", direccion=" + direccion + "";
	}
	
	public void mostrar() {
		System.out.println(this.toString());
	}
	public void leer() {
		Scanner sc = new Scanner(System.in);

		System.out.println("--- Ingrese los datos del cliente ---");
		super.leer(); // lee nombre, ci, teléfono y género desde Persona

		System.out.print("Ingrese el correo electrónico: ");
		correo = sc.nextLine();

		System.out.print("Ingrese la dirección: ");
		direccion = sc.nextLine();
	}
}
