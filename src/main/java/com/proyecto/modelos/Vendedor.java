package com.proyecto.modelos;
import com.proyecto.estructuras.*;
import java.util.Scanner;
public class Vendedor extends Persona {
	private double salario;
	private String rol;
	public Vendedor(String nombre, int ci, int telefono, String genero, double salario, String rol) {
		super(nombre, ci, telefono, genero);
		this.salario= salario;
		this.rol = rol;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	@Override
	public String toString() {
		return "Vendedor "+super.toString() +"salario=" + salario + ", rol=" + rol + "";
	}
	public void mostrar() {
		System.out.println( this.toString());
	}
	public void leer() {
		Scanner sc = new Scanner(System.in);

		System.out.println("--- Ingrese los datos del vendedor ---");
		super.leer(); // Llama al método leer() de Persona

		System.out.print("Ingrese el salario: ");
		salario = sc.nextDouble();
		sc.nextLine(); // limpiar buffer

		System.out.print("Ingrese el rol (por ejemplo: cajero, gerente, etc.): ");
		rol = sc.nextLine();
	}
}
