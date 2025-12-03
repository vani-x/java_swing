package com.proyecto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.proyecto.modelos.*;;

public class modoGrafico extends JFrame {
    
    private LibreriaServicio lib;
    private JTextArea resultadoArea;
    private JTextField inputAutor;
    private JTextField inputClienteCi;
    private JTextField inputFecha;


         
    private CardLayout cardLayout;
    private JPanel contentHolderPanel; // El panel que contiene las vistas

    // Nombres de las opciones
    private static final String INICIO = "Inicio";
    private static final String INVENTARIO = "Inventario";
    private static final String CLIENTES = "Clientes";
    private static final String REPORTES = "Reportes"; // historial de transacciones
    private static final String MOSTAR_FACTURA_CLIENTE = "Buscar Facturas";
    private static final String TOTAL_VENTAS = "Total Ventas";
    private static final String BUSCAR_CLIENTE_POR_FECHA = "Clientes por Fecha";

    
    private DefaultTableModel tablaClientesModelo; 
    private JTable tablaClientes;
    private JScrollPane tablaClientesScrollPane;
    // ---------------------------------------------
    
 // construir la interfaz
 // no tocar esto ------------------------------
    public modoGrafico() {
        this.lib = new LibreriaServicio();

    }
    public void createAndShowGUI() {
        
        // panel principal
        JFrame frame = new JFrame("Mi Libreria - Modo Gráfico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 800)); // Tamaño
        
        JPanel rightPanelContainer = createRightPanelContainer();
        
        JPanel navbarPanel = createNavbarPanel();

        navbarPanel.setPreferredSize(new Dimension(350, 800));

        //colocar el contenido
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, navbarPanel, rightPanelContainer);
        
        // 70
        splitPane.setResizeWeight(0.3); 
        // 30
        splitPane.setDividerLocation(350); 
        
        splitPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        frame.add(splitPane, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    private JPanel createNavbarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(40, 44, 52));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("Libreria Kamote", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(new Color(173, 216, 230));
        panel.add(title, BorderLayout.NORTH);

        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
        menu.setBackground(new Color(40, 44, 52));
        menu.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        String[] items = {INICIO, INVENTARIO, CLIENTES, REPORTES, MOSTAR_FACTURA_CLIENTE, TOTAL_VENTAS, BUSCAR_CLIENTE_POR_FECHA};
        for (String item : items) {
            JButton button = new JButton(item);
            // estilos del los botones
            button.setPreferredSize(new Dimension(300, 40));
            button.setBackground(new Color(60, 65, 75));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(90, 95, 105), 1));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // mostramos lso contenidos 
                    cardLayout.show(contentHolderPanel, item);
                }
            });
            menu.add(button);
        }

        panel.add(menu, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createRightPanelContainer() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(245, 245, 245));
        
       // queria hacer un buscador
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // Separador inferior
        
        JTextField searchField = new JTextField(30);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setText("Buscar Libros por nombre...");
        

        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(new Color(59, 130, 246)); 
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        container.add(searchPanel, BorderLayout.NORTH);
        // 
        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim();
            System.out.println("Buscando libros con nombre que contenga: " + query);
            cardLayout.show(contentHolderPanel, "FoundBooks");
        });
        cardLayout = new CardLayout();
        contentHolderPanel = new JPanel(cardLayout);
        contentHolderPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // vistas con sus metodos
        contentHolderPanel.add(createStartView(), INICIO);
        contentHolderPanel.add(createInventoryView(), INVENTARIO);
        contentHolderPanel.add(createClientsView(), CLIENTES);
        contentHolderPanel.add(createReportsView(), REPORTES);
        contentHolderPanel.add(createShowReceiptView(), MOSTAR_FACTURA_CLIENTE);
        contentHolderPanel.add(createTotalSalesView(), TOTAL_VENTAS);
        contentHolderPanel.add(createSearchClientByDateView(), BUSCAR_CLIENTE_POR_FECHA);
        
        contentHolderPanel.add(createFoundBooksView(), "FoundBooks");

        container.add(contentHolderPanel, BorderLayout.CENTER);
        
        return container;
    }
    // no tocar lo de arriba ------------------------------

    // hacer aqui las vistas -------------------------------------- tarea 
    
        private JPanel createFoundBooksView() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(255, 250, 240)); 
            
            JLabel label = new JLabel("Vista: Resultados de Búsqueda de Libros", SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.ITALIC, 24));
            panel.add(label, BorderLayout.CENTER);
            return panel;

            // falta hacer esto 
        }

        // la vista de incicio no se si hacer
    private JPanel createStartView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel label = new JLabel("Vista: Dashboard Principal", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setForeground(new Color(40, 44, 52));
        panel.add(label, BorderLayout.CENTER);
        
        JTextArea info = new JTextArea("Este es el panel principal");
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        info.setEditable(false);
        panel.add(info, BorderLayout.SOUTH);
        
        return panel;
    }
    // vista de inventario falta por hacer-------------------
    private JPanel createInventoryView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 245, 255));
        JLabel label = new JLabel("Vista: Gestión de Usuarios", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        panel.add(label);
        return panel;
    }
    // vista de clientes falta por hacer-------------------
    private JPanel createClientsView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 245, 230)); 
        JLabel label = new JLabel("Vista: Reportes y Análisis", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        panel.add(label);
        return panel;
    }
    // vista de reportes falta por hacer-------------------
    private JPanel createReportsView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 255, 230)); 
        JLabel label = new JLabel("Vista: Configuración de la App", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        panel.add(label);
        return panel;
    }
    // esto ya lo hice 
    private JPanel createShowReceiptView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 230, 245)); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel label = new JLabel("Vista: Buscar Facturas por Cliente", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));

        JLabel instructionLabel = new JLabel("Ingrese el CI del Cliente:");
        instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(instructionLabel);
        panel.add(Box.createVerticalStrut(10));

        JTextField ciField = new JTextField(15);
        Dimension preferredSize = ciField.getPreferredSize();

        ciField.setMaximumSize(preferredSize);


        ciField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        ciField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(ciField);
        panel.add(Box.createVerticalStrut(10));

        JButton searchButton = new JButton("Buscar Facturas");
        searchButton.setBackground(new Color(255, 105, 180)); 
        searchButton.setForeground(Color.WHITE);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(searchButton);
        
        
        searchButton.addActionListener(e -> {
            String ci_str = ciField.getText().trim();
            try {
                int ci = Integer.parseInt(ci_str);
                String result = lib.mostrarFacturasDeCliente(lib.getLibreria().getF(), ci);
                // como un alert de javascript
                JOptionPane.showMessageDialog(panel, result, "Facturas del Cliente CI: " + ci, JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Por favor, ingrese un número de CI válido.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        });


        return panel;
    }
    // esto falta por hacer-------------------
    private JPanel createTotalSalesView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 255, 230)); 
        JLabel label = new JLabel("Vista: Ventas Totales", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        panel.add(label);
        return panel;
    }
    // esto ya lo hice es casi lo mismo del otro
    private JPanel createSearchClientByDateView() {
        String[] nombresColumnas = {"Nombre", "Ci", "Correo", "Direccion", "Genero"};
        
        tablaClientesModelo = new DefaultTableModel(nombresColumnas, 0); 
        tablaClientes = new JTable(tablaClientesModelo);
        tablaClientesScrollPane = new JScrollPane(tablaClientes);
   

         JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 230, 245)); 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel label = new JLabel("Vista: Mostrar Clientes por fecha", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));

        JLabel instructionLabel = new JLabel("Ingrese la fecha del Cliente:");
        instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(instructionLabel);
        panel.add(Box.createVerticalStrut(10));

        JTextField dateField = new JTextField(15);
        Dimension preferredSize = dateField.getPreferredSize();

        dateField.setMaximumSize(preferredSize);


        dateField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        dateField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(dateField);
        panel.add(Box.createVerticalStrut(10)); // esto son espacios como br en html

        JButton searchButton = new JButton("Buscar Clientes");
        searchButton.setBackground(new Color(255, 105, 180)); 
        searchButton.setForeground(Color.WHITE);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(searchButton);
        
        tablaClientesScrollPane.setMaximumSize(new Dimension(800, 300));
        tablaClientesScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20)); 
        panel.add(tablaClientesScrollPane);
        
        searchButton.addActionListener(e -> {
            String date_str = dateField.getText().trim();
            try {
                // esto usa una lista no se como hacerlo con pilas o colas o otro
                ArrayList<Cliente> result = lib.mostrarClientesQueComparonFechaX(lib.getLibreria().getF(), date_str);
             // resetar la tabla antes de mostrar
                tablaClientesModelo.setRowCount(0); 

                //iterarmos sobre lista de objetos y añadir filas al modelo
                for (Cliente p : result) {
                    Object[] fila = new Object[5];
                    fila[0] = p.getNombre();
                    fila[1] = p.getCi();
                    fila[2] = p.getCorreo();
                    fila[3] = p.getDireccion();
                    fila[4] = p.getGenero();
                    
                    tablaClientesModelo.addRow(fila);
                }
          
                // actualizamos
                panel.revalidate();
                panel.repaint(); 
                } 
                catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Por favor, ingrese un número de CI válido.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        });


        return panel;
    }

}