package com.proyecto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class modoGrafico extends JFrame {
    
    private LibreriaServicio lib;
    private JTextArea resultadoArea;
        private JTextField inputAutor;
        private JTextField inputClienteCi;
        private JTextField inputFecha;

    public modoGrafico(LibreriaServicio lib) {
        this.lib = lib;
        //setTitle("Libreria UMSA - Modo Grafico");
        //setSize(600, 400);

        
            
            // Configuración general del JFrame
            setTitle("📚 Dashboard de Gestión de Librería");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(900, 650);
            setLocationRelativeTo(null); // Centrar la ventana
            setLayout(new BorderLayout(10, 10)); // Layout principal

            // --- Panel de Controles (Norte) ---
            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new GridLayout(6, 1, 10, 5));
            controlPanel.setBorder(BorderFactory.createTitledBorder("Funciones del Sistema (Problemas)"));
            
            // --- Componentes de entrada ---
            inputAutor = new JTextField("Gabriel Garcia Marquez", 15);
            inputClienteCi = new JTextField("2345678", 15);
            inputFecha = new JTextField("2025-10-29", 15);
            
            // Estilo general para los botones
            UIManager.put("Button.font", new Font("Arial", Font.BOLD, 12));
            UIManager.put("Button.background", new Color(60, 179, 113)); // Medium Sea Green
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 10, 5, 10));
            
            // --- Botones y sus acciones ---
            
            // P1: Historial
            JButton btnP1 = new JButton("P1: Historial de Transacciones");
            btnP1.setBackground(new Color(46, 139, 87)); // Sea Green
            btnP1.addActionListener(e -> {
               // String result = lib.generarHistorialTransacciones();
               // mostrarResultado("Historial Completo de Transacciones", result);
            });
            
            // P2: Buscar Libro por Autor
            JButton btnP2 = new JButton("P2: Buscar Libros por Autor");
            btnP2.addActionListener(e -> {
                String autor = inputAutor.getText().trim();
                String result = lib.buscarLibroPorAutor(lib.getLibreria().getLD(),autor);
                mostrarResultado("Libros del Autor: " + autor, result);
            });
            JPanel p2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p2Panel.add(btnP2);
            p2Panel.add(new JLabel("Autor:"));
            p2Panel.add(inputAutor);

            // P3: Facturas por Cliente
            JButton btnP3 = new JButton("P3: Facturas por Cliente (CI)");
            btnP3.addActionListener(e -> {
                try {
                    int ci = Integer.parseInt(inputClienteCi.getText().trim());
                    String result = lib.mostrarFacturasDeCliente(lib.getLibreria().getF(),ci);
                    mostrarResultado("Facturas del Cliente CI: " + ci, result);
                } catch (NumberFormatException ex) {
                    mostrarError("Error de Entrada", "Por favor, ingrese un número de CI válido.");
                }
            });
            JPanel p3Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p3Panel.add(btnP3);
            p3Panel.add(new JLabel("CI Cliente:"));
            p3Panel.add(inputClienteCi);

            // P4: Total de Ventas
            JButton btnP4 = new JButton("P4: Calcular Total General de Ventas");
            btnP4.setBackground(new Color(72, 61, 139)); // Dark Slate Blue
            btnP4.addActionListener(e -> {
                double total = lib.calcularTotalVentas(lib.getLibreria().getF());
                mostrarResultado("Cálculo de Ventas", String.format("💰 El total general de ventas es: %.2f Bs", total));
            });

            // P5: Clientes por Fecha
            JButton btnP5 = new JButton("P5: Clientes que Compraron en Fecha X");
            btnP5.addActionListener(e -> {
                String fecha = inputFecha.getText().trim();
                String result = lib.mostrarClientesQueComparonFechaX(lib.getLibreria().getF(), fecha);
                mostrarResultado("Clientes por Fecha: " + fecha, result);
            });
            JPanel p5Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p5Panel.add(btnP5);
            p5Panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
            p5Panel.add(inputFecha);
            
            
            // Añadir componentes al Panel de Controles
            controlPanel.add(btnP1);
            controlPanel.add(p2Panel);
            controlPanel.add(p3Panel);
            controlPanel.add(btnP4);
            controlPanel.add(p5Panel);
            
            // --- Panel de Resultados (Centro) ---
            resultadoArea = new JTextArea();
            resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
            resultadoArea.setEditable(false);
            resultadoArea.setText("Bienvenido al Dashboard de la Librería. Seleccione una función para ver el resultado.");
            JScrollPane scrollPane = new JScrollPane(resultadoArea);
            scrollPane.setBorder(BorderFactory.createTitledBorder("Resultados de la Operación"));

            // Añadir al JFrame
            add(controlPanel, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
            
            // Información de la Librería en el Sur
            JLabel infoLabel = new JLabel(
                "Librería: " + lib.getLibreria().getNombre() + " | Dirección: " + lib.getLibreria().getDireccion(), 
                SwingConstants.CENTER
            );
            infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
            add(infoLabel, BorderLayout.SOUTH);
        }

        /**
         * Muestra el resultado de una operación en el JTextArea
         * @param title Título de la sección de resultados
         * @param content Contenido a mostrar
         */
        private void mostrarResultado(String title, String content) {
            resultadoArea.setText("===============================================\n");
            resultadoArea.append("--- " + title + " ---\n");
            resultadoArea.append("===============================================\n\n");
            resultadoArea.append(content);
            resultadoArea.setCaretPosition(0); // Scroll al inicio
        }
        
        /**
         * Muestra un mensaje de error usando JOptionPane
         * @param title Título del error
         * @param message Mensaje de error
         */
        private void mostrarError(String title, String message) {
            // Usamos JOptionPane en lugar de System.out.println para mostrar el error al usuario
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
        }
    
}

