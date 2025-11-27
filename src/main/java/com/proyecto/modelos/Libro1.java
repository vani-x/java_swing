package com.proyecto.modelos;
import com.proyecto.estructuras.*;
import java.util.Scanner;
public class Libro1 {
	private String nombre;
	private String autor;
	private int paginas;
	private String estado;
	private int precio;
	public Libro1(String nombre, String autor, int paginas, String estado, int precio) {
		this.nombre = nombre;
		this.autor = autor;
		this.paginas = paginas;
		this.estado = estado;
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Libro nombre=" + nombre + ", autor=" + autor + ", paginas=" + paginas + ", estado=" + estado
				+ ", precio=" + precio + "";
	}
	public void mostrar() {
		System.out.println(this.toString());
	}
	public void leer() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Ingrese el nombre del libro: ");
		nombre = sc.nextLine();

		System.out.print("Ingrese el autor del libro: ");
		autor = sc.nextLine();

		System.out.print("Ingrese la cantidad de páginas: ");
		paginas = sc.nextInt();
		sc.nextLine(); // limpiar el buffer

		System.out.print("Ingrese el estado del libro (nuevo/usado): ");
		estado = sc.nextLine();

		System.out.print("Ingrese el precio del libro: ");
		precio = sc.nextInt();
	}
}
