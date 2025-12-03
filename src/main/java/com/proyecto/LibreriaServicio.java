package com.proyecto;
import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.proyecto.estructuras.*;
import com.proyecto.modelos.*;

import com.proyecto.estructuras.ColaCircularFactura;
import com.proyecto.estructuras.LDCarrito;
import com.proyecto.estructuras.ListaDobleCircularLibro;
import com.proyecto.estructuras.Mp_ColaCircularFactura;
import com.proyecto.estructuras.NodoLibro;

public class LibreriaServicio {
    private Libreria libreria;
    private final String RUTA_ARCHIVO = "datos_libreria.dat"; // persistencia 

    public LibreriaServicio() {
        // cargamos los datos guardados (si existen)
        this.libreria = cargarDatos();

        if (this.libreria == null) {
            System.out.println("No hay datos guardados. Inicializando datos por defecto...");
            inicializarDatosPorDefecto();
        } else {
            System.out.println("Datos cargados correctamente del disco.");
        }
    }

    // guardar cambios
    public void guardarCambios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) {
            oos.writeObject(this.libreria);
            System.out.println("Librería guardada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // cargar datos
    private Libreria cargarDatos() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return null; 
            // si no hay datos guardados retornamos null
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Libreria) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void inicializarDatosPorDefecto() {

    
        // === Clientes (Sin acentos) ===
        Cliente c1 = new Cliente("Carlos Perez", 1234567, 76543210, "Masculino", "carlos.perez@gmail.com",
                "Av. Bolivar Nro 123");
        Cliente c2 = new Cliente("Ana Lopez", 2345678, 71234567, "Femenino", "ana.lopez@yahoo.com",
                "Calle Sucre Nro 456");
        Cliente c3 = new Cliente("Luis Gomez", 3456789, 78901234, "Masculino", "luis.gomez@hotmail.com",
                "Av. America Nro 789");
        Cliente c4 = new Cliente("Maria Rodriguez", 4567890, 70011223, "Femenino", "maria.r@gmail.com",
                "Calle Comercio Nro 100");
        Cliente c5 = new Cliente("Jorge Gutierrez", 5678901, 72233445, "Masculino", "j.gutierrez@hotmail.com",
                "Av. Arce Nro 200");
        Cliente c6 = new Cliente("Lucia Fernandez", 6789012, 73344556, "Femenino", "lucia.f@yahoo.com",
                "Zona Sur, Calle 15");
        Cliente c7 = new Cliente("Miguel Soliz", 7890123, 74455667, "Masculino", "miguel.soliz@gmail.com",
                "Obrajes, Calle 8");
        Cliente c8 = new Cliente("Sofia Castro", 8901234, 75566778, "Femenino", "sofia.c@gmail.com",
                "Miraflores, Plaza Uyuni");
        Cliente c9 = new Cliente("David Mamani", 9012345, 76677889, "Masculino", "david.m@hotmail.com",
                "El Alto, Ceja");
        Cliente c10 = new Cliente("Valeria Quispe", 1023456, 77788990, "Femenino", "val.quispe@yahoo.com",
                "Sopocachi, Plaza Abaroa");
        Cliente c11 = new Cliente("Ricardo Tapia", 1123456, 79988776, "Masculino", "rick.tapia@live.com",
                "Calacoto, Calle 10");
        Cliente c12 = new Cliente("Fernanda Vega", 1223456, 76544332, "Femenino", "fer.vega@gmail.com",
                "Irpavi, Calle 2");
        Cliente c13 = new Cliente("Alan Brito", 1323456, 75433221, "Masculino", "alan.brito@gmail.com",
                "Cota Cota, Laguna");
        Cliente c14 = new Cliente("Marisol Luna", 1423456, 71122334, "Femenino", "marisol.luna@hotmail.com",
                "San Pedro, Plaza Sucre");
        Cliente c15 = new Cliente("Kevin Morales", 1523456, 72233445, "Masculino", "kevin.m@yahoo.com", "Villa Fatima");

        // === Vendedores (Sin acentos) ===
        Vendedor v1 = new Vendedor("Maria Perez", 1234567, 71234567, "Femenino", 3500.50, "Cajera");
        Vendedor v2 = new Vendedor("Jose Ramirez", 2345678, 78901234, "Masculino", 4200.00, "Gerente de ventas");
        Vendedor v3 = new Vendedor("Lucia Torres", 3456789, 75678901, "Femenino", 3000.75, "Asistente de ventas");
        Vendedor v4 = new Vendedor("Pedro Alarcon", 4012345, 74567890, "Masculino", 3200.00, "Cajero");
        Vendedor v5 = new Vendedor("Carla Mendoza", 5023456, 75123456, "Femenino", 4000.00, "Supervisora");

        // === Libros (Sin acentos) ===
        Libro1 l1 = new Libro1("Cien anos de soledad", "Gabriel Garcia Marquez", 471, "Nuevo", 120);
        Libro1 l2 = new Libro1("Don Quijote de la Mancha", "Miguel de Cervantes", 863, "Usado", 90);
        Libro1 l3 = new Libro1("El Principito", "Antoine de Saint-Exupery", 120, "Nuevo", 60);
        Libro1 l4 = new Libro1("Rayuela", "Julio Cortazar", 500, "Nuevo", 110);
        Libro1 l5 = new Libro1("La Odisea", "Homero", 400, "Usado", 75);
        Libro1 l6 = new Libro1("1984", "George Orwell", 326, "Nuevo", 95);
        Libro1 l7 = new Libro1("Crimen y Castigo", "Fiodor Dostoyevski", 671, "Usado", 85);
        Libro1 l8 = new Libro1("Fahrenheit 451", "Ray Bradbury", 249, "Nuevo", 80);
        Libro1 l9 = new Libro1("El Amor en los Tiempos del Colera", "Gabriel Garcia Marquez", 348, "Nuevo", 115);
        Libro1 l10 = new Libro1("La Metamorfosis", "Franz Kafka", 200, "Usado", 70);
        Libro1 l11 = new Libro1("El Senor de los Anillos", "J.R.R. Tolkien", 1200, "Nuevo", 380);
        Libro1 l12 = new Libro1("Dune", "Frank Herbert", 412, "Nuevo", 210);
        Libro1 l13 = new Libro1("Fundacion", "Isaac Asimov", 256, "Usado", 100);
        Libro1 l14 = new Libro1("El nombre del viento", "Patrick Rothfuss", 662, "Nuevo", 240);
        Libro1 l15 = new Libro1("Ensayo sobre la ceguera", "Jose Saramago", 350, "Nuevo", 130);
        Libro1 l16 = new Libro1("El Aleph", "Jorge Luis Borges", 200, "Usado", 90);
        Libro1 l17 = new Libro1("Clean Code", "Robert C. Martin", 464, "Nuevo", 250);
        Libro1 l18 = new Libro1("Design Patterns (GoF)", "Erich Gamma et al.", 395, "Nuevo", 300);
        Libro1 l19 = new Libro1("Introduction to Algorithms (CLRS)", "Cormen, Leiserson, Rivest, Stein", 1312, "Nuevo",
                450);
        Libro1 l20 = new Libro1("Head First Java", "Kathy Sierra, Bert Bates", 722, "Usado", 180);
        Libro1 l21 = new Libro1("Estructuras de Datos con Java", "Joyanes Aguilar", 900, "Nuevo", 320);
        Libro1 l22 = new Libro1("Bases de Datos (Fundamentos)", "Silberschatz, Korth, Sudarshan", 1376, "Nuevo", 400);
        Libro1 l23 = new Libro1("The Pragmatic Programmer", "Andrew Hunt, David Thomas", 352, "Nuevo", 280);
        Libro1 l24 = new Libro1("Sistemas Operativos Modernos", "Andrew S. Tanenbaum", 1104, "Usado", 220);
        Libro1 l25 = new Libro1("Redes de Computadoras", "Andrew S. Tanenbaum", 960, "Nuevo", 350);
        Libro1 l26 = new Libro1("Inteligencia Artificial: Un Enfoque Moderno", "Stuart Russell, Peter Norvig", 1136,
                "Nuevo", 500);
        Libro1 l27 = new Libro1("Java: The Complete Reference", "Herbert Schildt", 1248, "Nuevo", 420);
        Libro1 l28 = new Libro1("Cracking the Coding Interview", "Gayle Laakmann McDowell", 687, "Nuevo", 330);
        Libro1 l29 = new Libro1("Fisica Universitaria Vol 1", "Sears, Zemansky", 1400, "Nuevo", 550);
        Libro1 l30 = new Libro1("Calculus", "James Stewart", 1392, "Nuevo", 600);

        // llenado al carrito de libros que compro cada cliente
        LDCarrito carrito1 = new LDCarrito();
        carrito1.adiFinal(l1);
        carrito1.adiFinal(l4);
        LDCarrito carrito2 = new LDCarrito();
        carrito2.adiFinal(l2);
        carrito2.adiFinal(l5);
        LDCarrito carrito3 = new LDCarrito();
        carrito3.adiFinal(l3);
        carrito3.adiFinal(l6);
        LDCarrito carrito4 = new LDCarrito();
        carrito4.adiFinal(l17);
        carrito4.adiFinal(l10);
        carrito4.adiFinal(l15);
        LDCarrito carrito5 = new LDCarrito();
        carrito5.adiFinal(l18);
        carrito5.adiFinal(l12);
        LDCarrito carrito6 = new LDCarrito();
        carrito6.adiFinal(l19);
        carrito6.adiFinal(l13);
        carrito6.adiFinal(l9);
        LDCarrito carrito7 = new LDCarrito();
        carrito7.adiFinal(l20);
        LDCarrito carrito8 = new LDCarrito();
        carrito8.adiFinal(l21);
        carrito8.adiFinal(l8);
        LDCarrito carrito9 = new LDCarrito();
        carrito9.adiFinal(l22);
        carrito9.adiFinal(l14);
        LDCarrito carrito10 = new LDCarrito();
        carrito10.adiFinal(l23);
        LDCarrito carrito11 = new LDCarrito();
        carrito11.adiFinal(l29);
        carrito11.adiFinal(l30);
        carrito11.adiFinal(l30);
        LDCarrito carrito12 = new LDCarrito();
        carrito12.adiFinal(l17);
        carrito12.adiFinal(l11);
        carrito12.adiFinal(l1);

        // === Facturas (Agrupadas por fecha) ===

        // --- GRUPO 1: Fecha "2025-10-29" (Irán a C1) ---
        Factura f1 = new Factura("2025-10-29", 120, 10, c1, carrito1, v1);
        Factura f2 = new Factura("2025-10-29", 90, 5, c2, carrito2, v2);
        Factura f3 = new Factura("2025-10-29", 60, 0, c3, carrito3, v3);
        Factura f5 = new Factura("2025-10-29", 250, 10, c4, carrito4, v4);
        Factura f6 = new Factura("2025-10-29", 300, 15, c5, carrito5, v5);
        Factura f7 = new Factura("2025-10-29", 450, 20, c6, carrito6, v1);
        Factura f8 = new Factura("2025-10-29", 180, 0, c7, carrito7, v2);
        Factura f9 = new Factura("2025-10-29", 320, 10, c8, carrito8, v3);
        Factura f10 = new Factura("2025-10-29", 400, 25, c9, carrito9, v4);
        Factura f11 = new Factura("2025-10-29", 280, 0, c10, carrito10, v5);

        // --- GRUPO 2: Fecha "2025-11-11" (Irán a C2) ---
        Factura f4 = new Factura("2025-11-11", 60, 0, c3, carrito3, v3);
        Factura f15 = new Factura("2025-11-11", 420, 30, c2, carrito1, v4);
        Factura f16 = new Factura("2025-11-11", 330, 15, c3, carrito2, v5);
        Factura f17 = new Factura("2025-11-11", 380, 20, c4, carrito11, v1);
        Factura f18 = new Factura("2025-11-11", 210, 0, c5, carrito12, v2);
        Factura f19 = new Factura("2025-11-11", 100, 0, c6, carrito3, v3);
        Factura f20 = new Factura("2025-11-11", 240, 10, c7, carrito4, v4);
        Factura f21 = new Factura("2025-11-11", 95, 5, c8, carrito6, v5);
        Factura f22 = new Factura("2025-11-11", 85, 0, c9, carrito7, v1);
        Factura f23 = new Factura("2025-11-11", 600, 50, c10, carrito10, v2);
        Factura f24 = new Factura("2025-11-11", 550, 40, c11, carrito11, v3);
        Factura f25 = new Factura("2025-11-11", 250, 0, c12, carrito12, v4);
        // --- GRUPO 3: Fecha "2025-11-12" (Irán a C3) ---
        Factura f26 = new Factura("2025-11-12", 220, 0, c11, carrito5, v1);
        Factura f27 = new Factura("2025-11-12", 350, 10, c12, carrito6, v2);
        Factura f28 = new Factura("2025-11-12", 500, 50, c1, carrito7, v3);
        Factura f29 = new Factura("2025-11-12", 120, 0, c13, carrito1, v4);
        Factura f30 = new Factura("2025-11-12", 90, 0, c14, carrito2, v5);
        Factura f31 = new Factura("2025-11-12", 75, 0, c15, carrito5, v1);
        Factura f32 = new Factura("2025-11-12", 95, 0, c1, carrito6, v2);
        Factura f33 = new Factura("2025-11-12", 80, 5, c2, carrito8, v3);
        Factura f34 = new Factura("2025-11-12", 115, 10, c3, carrito9, v4);
        Factura f35 = new Factura("2025-11-12", 70, 0, c4, carrito10, v5);

        // --- GRUPO 4: Fecha "2025-11-13" (Irán a C4) ---
        Factura f36 = new Factura("2025-11-13", 210, 20, c5, carrito12, v1);
        Factura f37 = new Factura("2025-11-13", 100, 10, c6, carrito8, v2);
        Factura f38 = new Factura("2025-11-13", 240, 0, c7, carrito9, v3);
        Factura f39 = new Factura("2025-11-13", 130, 0, c8, carrito10, v4);
        Factura f40 = new Factura("2025-11-13", 90, 0, c9, carrito11, v5);
        Factura f41 = new Factura("2025-11-13", 250, 0, c10, carrito12, v1);
        Factura f42 = new Factura("2025-11-13", 300, 30, c11, carrito1, v2);
        Factura f43 = new Factura("2025-11-13", 450, 45, c12, carrito2, v3);
        Factura f44 = new Factura("2025-11-13", 180, 0, c13, carrito3, v4);
        Factura f45 = new Factura("2025-11-13", 320, 10, c14, carrito4, v5);

        // === Llenado de Colas ===
        ColaCircularFactura C1 = new ColaCircularFactura();
        ColaCircularFactura C2 = new ColaCircularFactura();
        ColaCircularFactura C3 = new ColaCircularFactura();
        ColaCircularFactura C4 = new ColaCircularFactura();

        // Llenando C1 (2025-10-29)
        C1.adi(f1);
        C1.adi(f2);
        C1.adi(f3);
        C1.adi(f5);
        C1.adi(f6);
        C1.adi(f7);
        C1.adi(f8);
        C1.adi(f9);
        C1.adi(f10);
        C1.adi(f11);

        // Llenando C2 (2025-11-11)
        C2.adi(f4);
        C2.adi(f15);
        C2.adi(f16);
        C2.adi(f17);
        C2.adi(f18);
        C2.adi(f19);
        C2.adi(f20);
        C2.adi(f21);
        C2.adi(f22);
        C2.adi(f23);
        C2.adi(f24);
        C2.adi(f25);

        // Llenando C3 (2025-11-12)
        C3.adi(f26);
        C3.adi(f27);
        C3.adi(f28);
        C3.adi(f29);
        C3.adi(f30);
        C3.adi(f31);
        C3.adi(f32);
        C3.adi(f33);
        C3.adi(f34);
        C3.adi(f35);

        // Llenando C4 (2025-11-13)
        C4.adi(f36);
        C4.adi(f37);
        C4.adi(f38);
        C4.adi(f39);
        C4.adi(f40);
        C4.adi(f41);
        C4.adi(f42);
        C4.adi(f43);
        C4.adi(f44);
        C4.adi(f45);

        // === Llenado de Multicola ===
        Mp_ColaCircularFactura mp1 = new Mp_ColaCircularFactura(100);

        mp1.vaciar(0, C1);
        mp1.vaciar(1, C2);
        mp1.vaciar(2, C3);
        mp1.vaciar(3, C4);

        // === Llenado de Lista de Libros ===
        ListaDobleCircularLibro lista = new ListaDobleCircularLibro();

        lista.adiFinal("Cien anos de soledad", "Gabriel Garcia Marquez", 471, "Nuevo", 120);
        lista.adiFinal("Don Quijote de la Mancha", "Miguel de Cervantes", 863, "Usado", 90);
        lista.adiFinal("El Principito", "Antoine de Saint-Exupery", 120, "Nuevo", 60);
        lista.adiFinal("Rayuela", "Julio Cortazar", 500, "Nuevo", 110);
        lista.adiFinal("La Odisea", "Homero", 400, "Usado", 75);
        lista.adiFinal("1984", "George Orwell", 326, "Nuevo", 95);
        lista.adiFinal("Crimen y Castigo", "Fiodor Dostoyevski", 671, "Usado", 85);
        lista.adiFinal("Fahrenheit 451", "Ray Bradbury", 249, "Nuevo", 80);
        lista.adiFinal("El Amor en los Tiempos del Colera", "Gabriel Garcia Marquez", 348, "Nuevo", 115);
        lista.adiFinal("La Metamorfosis", "Franz Kafka", 200, "Usado", 70);
        lista.adiFinal("El Senor de los Anillos", "J.R.R. Tolkien", 1200, "Nuevo", 380);
        lista.adiFinal("Dune", "Frank Herbert", 412, "Nuevo", 210);
        lista.adiFinal("Fundacion", "Isaac Asimov", 256, "Usado", 100);
        lista.adiFinal("El nombre del viento", "Patrick Rothfuss", 662, "Nuevo", 240);
        lista.adiFinal("Ensayo sobre la ceguera", "Jose Saramago", 350, "Nuevo", 130);
        lista.adiFinal("El Aleph", "Jorge Luis Borges", 200, "Usado", 90);
        lista.adiFinal("Clean Code", "Robert C. Martin", 464, "Nuevo", 250);
        lista.adiFinal("Design Patterns (GoF)", "Erich Gamma et al.", 395, "Nuevo", 300);
        lista.adiFinal("Introduction to Algorithms (CLRS)", "Cormen, Leiserson, Rivest, Stein", 1312, "Nuevo", 450);
        lista.adiFinal("Head First Java", "Kathy Sierra, Bert Bates", 722, "Usado", 180);
        lista.adiFinal("Estructuras de Datos con Java", "Joyanes Aguilar", 900, "Nuevo", 320);
        lista.adiFinal("Bases de Datos (Fundamentos)", "Silberschatz, Korth, Sudarshan", 1376, "Nuevo", 400);
        lista.adiFinal("The Pragmatic Programmer", "Andrew Hunt, David Thomas", 352, "Nuevo", 280);
        lista.adiFinal("Sistemas Operativos Modernos", "Andrew S. Tanenbaum", 1104, "Usado", 220);
        lista.adiFinal("Redes de Computadoras", "Andrew S. Tanenbaum", 960, "Nuevo", 350);
        lista.adiFinal("Inteligencia Artificial: Un Enfoque Moderno", "Stuart Russell, Peter Norvig", 1136, "Nuevo",
                500);
        lista.adiFinal("Java: The Complete Reference", "Herbert Schildt", 1248, "Nuevo", 420);
        lista.adiFinal("Cracking the Coding Interview", "Gayle Laakmann McDowell", 687, "Nuevo", 330);
        lista.adiFinal("Fisica Universitaria Vol 1", "Sears, Zemansky", 1400, "Nuevo", 550);
        lista.adiFinal("Calculus", "James Stewart", 1392, "Nuevo", 600);

        // === Creación de Libreria ===
        this.libreria = new Libreria("Libreria de Informatica UMSA", "Av. 6 de Agosto esq. Campos", mp1, lista);
        
        System.out.println("Libreria creada exitosamente");
        guardarCambios();
        // System.out.println("===============================================");
        // System.out.println("------------------Problema 1-------------------");
        // System.out.println("===============================================");
        // generarHistorialTransacciones(Libreria);
        // // 2 Buscar libros de un autor específico
        // System.out.println("===============================================");
        // System.out.println("------------------Problema 2-------------------");
        // System.out.println("===============================================");
        // System.out.println("Autor:" + "Gabriel Garcia Marquez");
        // buscarLibroPorAutor(lista, "Gabriel Garcia Marquez");

        // // 3 Mostrar facturas de un cliente específico
        // System.out.println("===============================================");
        // System.out.println("------------------Problema 3-------------------");
        // System.out.println("===============================================");
        // System.out.println("Cliente " + 2345678);
        // mostrarFacturasDeCliente(mp1, 2345678);

        // // 4 Calcular el total de ventas
        // System.out.println("===============================================");
        // System.out.println("------------------Problema 4-------------------");
        // System.out.println("===============================================");
        // double total = calcularTotalVentas(mp1);
        // System.out.println("💰 El total general de ventas es: " + total + " Bs");
        // // 5 Mostrar Clientes Que Comparon en Fecha X
        // System.out.println("===============================================");
        // System.out.println("------------------Problema 5-------------------");
        // System.out.println("===============================================");
        // mostrarClientesQueComparonFechaX(mp1, "2025-10-29");
    }
    
// hay que cambiar todas los resultados lo que devuelve
    public static void generarHistorialTransacciones(Libreria A) {
        Mp_ColaCircularFactura mc = A.getF();
        System.out.println("Historial De Transacciones");
        mc.mostrar();
    }

    public String buscarLibroPorAutor(ListaDobleCircularLibro lista, String autorBuscado) {
        
        if (lista.esVacia()) {
            System.out.println("No hay libros registrados en la librería.");
            return "";
            //break
        }


        NodoLibro r = lista.getP();
        boolean encontrado = false;
        do {
            Libro1 L = r.getDato();
            if (L.getAutor().equalsIgnoreCase(autorBuscado)) {
                System.out.println("📖 " + L);
                encontrado = true;
            }
            r = r.getSig();
        } while (r != lista.getP());

        if (!encontrado)
            return ("No se encontraron libros del autor: " + autorBuscado);

        return "";
    }

    public  String mostrarFacturasDeCliente(Mp_ColaCircularFactura mc, int ciCliente) {
        for (int i = 0; i < mc.getN(); i++) {
            ColaCircularFactura cola = new ColaCircularFactura();
            Factura f;

            while (!mc.esVacia(i)) {
                f = mc.eliminar(i);
                if (f.getP().getCi() == ciCliente)
                    return("🧾 " + f);
                cola.adi(f);
            }
            mc.vaciar(i, cola); // restaurar contenido original
        }
        return("No se encontraron facturas para el cliente con CI: " + ciCliente);
    }

    public  double calcularTotalVentas(Mp_ColaCircularFactura mc) {
        double total = 0;
        for (int i = 0; i < mc.getN(); i++) {
            ColaCircularFactura aux = new ColaCircularFactura();
            Factura f;
            while (!mc.esVacia(i)) {
                f = mc.eliminar(i);
                total += f.getTotal();
                aux.adi(f);
            }
            mc.vaciar(i, aux);
        }
        return total;
    }

    public ArrayList<Cliente> mostrarClientesQueComparonFechaX(Mp_ColaCircularFactura mc, String fecha) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        System.out.println("Fecha:" + fecha);
        for (int i = 0; i < mc.getN(); i++) {
            ColaCircularFactura aux = new ColaCircularFactura();
            Factura f;
            while (!mc.esVacia(i)) {
                f = mc.eliminar(i);
                if (f.getFecha().equals(fecha)) {
                    //return("🧾 " + f.getP());
                    clientes.add((f.getP()));
                    // f.getP().mostrar();
                }
                aux.adi(f);
            }
            mc.vaciar(i, aux);
        }
        return clientes;

    }

    public Libreria getLibreria() {
        return libreria;
    }

    public void setLibreria(Libreria libreria) {
        this.libreria = libreria;
    }
}