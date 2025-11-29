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


         // Referencias para la gestión de las vistas en el panel derecho
    private CardLayout cardLayout;
    private JPanel contentHolderPanel; // El panel que contiene las "cartas" (vistas)

    // Nombres de las "cartas" (deben coincidir con el nombre de las vistas)
    private static final String INICIO = "Inicio";
    private static final String INVENTARIO = "Inventario";
    private static final String CLIENTES = "Clientes";
    private static final String REPORTES = "Reportes"; // historial de transacciones
    private static final String MOSTAR_FACTURA_CLIENTE = "Buscar Facturas";
    private static final String TOTAL_VENTAS = "Total Ventas";
    private static final String BUSCAR_CLIENTE_POR_FECHA = "Clientes por Fecha";
    
 // Método para construir la interfaz
    public modoGrafico() {
        // Configurar y mostrar la GUI en el hilo de despacho de eventos
      //  SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    public void createAndShowGUI() {
        
        // 1. Configuración de la ventana principal (JFrame)
        JFrame frame = new JFrame("Aplicación con JSplitPane (30/70)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 800)); // Tamaño inicial grande para ver la división

                // 2. Crear los paneles de contenido
        
        // Panel Derecho (70%): Contenedor con buscador permanente y contenido cambiante
        JPanel rightPanelContainer = createRightPanelContainer();
        
        // Panel Izquierdo (30%): Navbar con funcionalidad para cambiar el contenido
        JPanel navbarPanel = createNavbarPanel();
        

        navbarPanel.setPreferredSize(new Dimension(350, 800));


        // 3. Configurar el JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, navbarPanel, rightPanelContainer);
        
        // El panel de la izquierda (navbar) mantiene su ancho inicial, el de la derecha absorbe el espacio
        splitPane.setResizeWeight(0.3); 

        // 30% del ancho para el panel izquierdo
        splitPane.setDividerLocation(350); 
        
        splitPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // 4. Añadir el JSplitPane a la ventana principal
        frame.add(splitPane, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    // --- Componentes Reutilizables ---

    // Crea el panel de navegación (lado izquierdo) y maneja la lógica de cambio de vista
    private JPanel createNavbarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(40, 44, 52));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("Libreria Kamote", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(new Color(173, 216, 230));
        panel.add(title, BorderLayout.NORTH);

        // Lista de opciones de menú
        JPanel menu = new JPanel();
       // menu.setLayout(new GridLayout(0, 1, 0, 10));
        menu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
        menu.setBackground(new Color(40, 44, 52));
        menu.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        String[] items = {INICIO, INVENTARIO, CLIENTES, REPORTES, MOSTAR_FACTURA_CLIENTE, TOTAL_VENTAS, BUSCAR_CLIENTE_POR_FECHA};
        for (String item : items) {
            JButton button = new JButton(item);

            button.setPreferredSize(new Dimension(300, 40));
            button.setBackground(new Color(60, 65, 75));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(90, 95, 105), 1));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // Lógica de cambio de vista al hacer clic en el botón
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Muestra la "carta" (vista) que coincide con el texto del botón
                    cardLayout.show(contentHolderPanel, item);
                }
            });
            menu.add(button);
        }

        panel.add(menu, BorderLayout.CENTER);
        return panel;
    }

    // Crea el contenedor derecho que incluye el buscador fijo y el CardLayout
    private JPanel createRightPanelContainer() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(245, 245, 245));
        
        // 1. Buscador Permanente (NORTH)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // Separador inferior
        
        JTextField searchField = new JTextField(30);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setText("Buscar Libros por nombre...");
        
        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(new Color(59, 130, 246)); // Tailwind blue-500
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        container.add(searchPanel, BorderLayout.NORTH);
        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim();
            // Aquí iría la lógica de búsqueda real
            System.out.println("Buscando libros con nombre que contenga: " + query);
            // Por ahora, simplemente mostramos la vista de resultados de búsqueda
            cardLayout.show(contentHolderPanel, "FoundBooks");
        });


        // 2. Contenedor de Vistas Dinámicas (CENTER)
        cardLayout = new CardLayout();
        contentHolderPanel = new JPanel(cardLayout);
        contentHolderPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Añadir todas las vistas al CardLayout
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

    // --- Definición de Vistas ---
    
        private JPanel createFoundBooksView() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(255, 250, 240)); // Beige muy claro
            
            JLabel label = new JLabel("Vista: Resultados de Búsqueda de Libros", SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.ITALIC, 24));
            panel.add(label, BorderLayout.CENTER);
            return panel;
        }

    private JPanel createStartView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel label = new JLabel("Vista: Dashboard Principal", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setForeground(new Color(40, 44, 52));
        panel.add(label, BorderLayout.CENTER);
        
        JTextArea info = new JTextArea("Este es el panel principal con métricas y resúmenes. El buscador de arriba es permanente.");
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        info.setEditable(false);
        panel.add(info, BorderLayout.SOUTH);
        
        return panel;
    }

    private JPanel createInventoryView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 245, 255)); // Azul muy claro
        JLabel label = new JLabel("Vista: Gestión de Usuarios", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        panel.add(label);
        return panel;
    }

    private JPanel createClientsView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 245, 230)); // Naranja muy claro
        JLabel label = new JLabel("Vista: Reportes y Análisis", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        panel.add(label);
        return panel;
    }

    private JPanel createReportsView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 255, 230)); // Verde muy claro
        JLabel label = new JLabel("Vista: Configuración de la App", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        panel.add(label);
        return panel;
    }
    private JPanel createShowReceiptView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 230, 245)); // Rosa muy claro
        JLabel label = new JLabel("Vista: Buscar Facturas por Cliente", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        panel.add(label);
        return panel;
    }

    private JPanel createTotalSalesView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 255, 230)); // Verde muy claro
        JLabel label = new JLabel("Vista: Ventas Totales", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        panel.add(label);
        return panel;
    }

    private JPanel createSearchClientByDateView() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 245, 255)); // Azul muy claro
        JLabel label = new JLabel("Vista: Buscar Cliente por Fecha", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.ITALIC, 24));
        panel.add(label);
        return panel;
    }


    //     // 2. Crear los paneles de contenido
        
    //     // Panel Izquierdo: Navbar/Barra Lateral (30%)
    //     JPanel navbarPanel = createNavbarPanel();
        
    //     // Panel Derecho: Contenido Principal (70%)
    //     JPanel contentPanel = createContentPanel();

    //     // 3. Configurar el JSplitPane
        
    //     // Creamos el JSplitPane para dividir horizontalmente los dos paneles
    //     JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, navbarPanel, contentPanel);
        
    //     // Evitamos que los componentes se expandan más allá de su tamaño preferido
    //     splitPane.setResizeWeight(0.0); 

    //     // Establecemos la ubicación inicial del divisor. 
    //     // 0.3 (30%) significa que el componente de la izquierda tomará el 30% del espacio.
    //     // Si quisieras el 40%, simplemente usa 0.4.
    //     splitPane.setDividerLocation(0.3); 
        
    //     // Añadimos un borde decorativo para que el JSplitPane se vea mejor
    //     splitPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    //     // 4. Añadir el JSplitPane a la ventana principal
    //     frame.add(splitPane, BorderLayout.CENTER);

    //     // 5. Mostrar la ventana
    //     frame.pack();
    //     frame.setLocationRelativeTo(null); // Centrar en la pantalla
    //     frame.setVisible(true);
    // }

    // // --- Componentes Reutilizables ---

    // // Crea el panel de navegación (lado izquierdo)
    // private JPanel createNavbarPanel() {
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new BorderLayout());
    //     panel.setBackground(new Color(40, 44, 52)); // Gris oscuro para el navbar
    //     panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    //     // Título del Navbar
    //     JLabel title = new JLabel("Menú de Navegación", SwingConstants.CENTER);
    //     title.setFont(new Font("SansSerif", Font.BOLD, 18));
    //     title.setForeground(new Color(173, 216, 230)); // Azul claro
    //     panel.add(title, BorderLayout.NORTH);

    //     // Lista de opciones de menú
    //     JPanel menu = new JPanel();
    //     menu.setLayout(new GridLayout(0, 1, 0, 10)); // Layout de una columna con espacio vertical
    //     menu.setBackground(new Color(40, 44, 52));
    //     menu.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

    //     String[] items = {"Dashboard", "Gestión de Usuarios", "Reportes", "Configuración"};
    //     for (String item : items) {
    //         JButton button = new JButton(item);
    //         button.setBackground(new Color(60, 65, 75)); // Botón más oscuro
    //         button.setForeground(Color.WHITE);
    //         button.setFocusPainted(false);
    //         button.setBorder(BorderFactory.createLineBorder(new Color(90, 95, 105), 1));
    //         button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    //         menu.add(button);
    //     }

    //     panel.add(menu, BorderLayout.CENTER);
    //     return panel;
    // }

    // // Crea el panel de contenido (lado derecho)
    // private JPanel createContentPanel() {
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new BorderLayout());
    //     panel.setBackground(Color.WHITE);
    //     panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    //     JLabel title = new JLabel("Contenido Principal (70%)", SwingConstants.CENTER);
    //     title.setFont(new Font("SansSerif", Font.BOLD, 24));
    //     title.setForeground(new Color(50, 50, 50));
    //     panel.add(title, BorderLayout.NORTH);

    //     // Área de texto con scroll para simular contenido
    //     JTextArea contentArea = new JTextArea();
    //     contentArea.setText("Aquí se cargarán los componentes de la vista principal.\n\n"
    //                         + "El JSplitPane permite al usuario arrastrar el divisor para cambiar la proporción "
    //                         + "entre la barra lateral y el contenido en tiempo de ejecución. \n\n"
    //                         + "El peso de redimensionamiento (resize weight) está fijado en 0.0, lo que "
    //                         + "significa que al redimensionar la ventana, la porción extra de espacio "
    //                         + "la tomará el componente de la derecha (el contenido principal, el 70%).");
    //     contentArea.setWrapStyleWord(true);
    //     contentArea.setLineWrap(true);
    //     contentArea.setEditable(false);
    //     contentArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    //     contentArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    //     JScrollPane scrollPane = new JScrollPane(contentArea);
    //     scrollPane.setPreferredSize(new Dimension(600, 400));
        
    //     panel.add(scrollPane, BorderLayout.CENTER);
    //     return panel;
    // }
}






    // public modoGrafico(LibreriaServicio lib) {
    //     this.lib = lib;
    //     //setTitle("Libreria UMSA - Modo Grafico");
    //     //setSize(600, 400);

        



            
        //     // Configuración general del JFrame
        //     setTitle("📚 Dashboard de Gestión de Librería");
        //     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //     setSize(900, 650);
        //     setLocationRelativeTo(null); // Centrar la ventana
        //     setLayout(new BorderLayout(10, 10)); // Layout principal

        //     // --- Panel de Controles (Norte) ---
        //     JPanel controlPanel = new JPanel();
        //     controlPanel.setLayout(new GridLayout(6, 1, 10, 5));
        //     controlPanel.setBorder(BorderFactory.createTitledBorder("Funciones del Sistema (Problemas)"));
            
        //     // --- Componentes de entrada ---
        //     inputAutor = new JTextField("Gabriel Garcia Marquez", 15);
        //     inputClienteCi = new JTextField("2345678", 15);
        //     inputFecha = new JTextField("2025-10-29", 15);
            
        //     // Estilo general para los botones
        //     UIManager.put("Button.font", new Font("Arial", Font.BOLD, 12));
        //     UIManager.put("Button.background", new Color(60, 179, 113)); // Medium Sea Green
        //     UIManager.put("Button.foreground", Color.WHITE);
        //     UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 10, 5, 10));
            
        //     // --- Botones y sus acciones ---
            
        //     // P1: Historial
        //     JButton btnP1 = new JButton("P1: Historial de Transacciones");
        //     btnP1.setBackground(new Color(46, 139, 87)); // Sea Green
        //     btnP1.addActionListener(e -> {
        //        // String result = lib.generarHistorialTransacciones();
        //        // mostrarResultado("Historial Completo de Transacciones", result);
        //     });
            
        //     // P2: Buscar Libro por Autor
        //     JButton btnP2 = new JButton("P2: Buscar Libros por Autor");
        //     btnP2.addActionListener(e -> {
        //         String autor = inputAutor.getText().trim();
        //         String result = lib.buscarLibroPorAutor(lib.getLibreria().getLD(),autor);
        //         mostrarResultado("Libros del Autor: " + autor, result);
        //     });
        //     JPanel p2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //     p2Panel.add(btnP2);
        //     p2Panel.add(new JLabel("Autor:"));
        //     p2Panel.add(inputAutor);

        //     // P3: Facturas por Cliente
        //     JButton btnP3 = new JButton("P3: Facturas por Cliente (CI)");
        //     btnP3.addActionListener(e -> {
        //         try {
        //             int ci = Integer.parseInt(inputClienteCi.getText().trim());
        //             String result = lib.mostrarFacturasDeCliente(lib.getLibreria().getF(),ci);
        //             mostrarResultado("Facturas del Cliente CI: " + ci, result);
        //         } catch (NumberFormatException ex) {
        //             mostrarError("Error de Entrada", "Por favor, ingrese un número de CI válido.");
        //         }
        //     });
        //     JPanel p3Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //     p3Panel.add(btnP3);
        //     p3Panel.add(new JLabel("CI Cliente:"));
        //     p3Panel.add(inputClienteCi);

        //     // P4: Total de Ventas
        //     JButton btnP4 = new JButton("P4: Calcular Total General de Ventas");
        //     btnP4.setBackground(new Color(72, 61, 139)); // Dark Slate Blue
        //     btnP4.addActionListener(e -> {
        //         double total = lib.calcularTotalVentas(lib.getLibreria().getF());
        //         mostrarResultado("Cálculo de Ventas", String.format("💰 El total general de ventas es: %.2f Bs", total));
        //     });

        //     // P5: Clientes por Fecha
        //     JButton btnP5 = new JButton("P5: Clientes que Compraron en Fecha X");
        //     btnP5.addActionListener(e -> {
        //         String fecha = inputFecha.getText().trim();
        //         String result = lib.mostrarClientesQueComparonFechaX(lib.getLibreria().getF(), fecha);
        //         mostrarResultado("Clientes por Fecha: " + fecha, result);
        //     });
        //     JPanel p5Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //     p5Panel.add(btnP5);
        //     p5Panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        //     p5Panel.add(inputFecha);
            
            
        //     // Añadir componentes al Panel de Controles
        //     controlPanel.add(btnP1);
        //     controlPanel.add(p2Panel);
        //     controlPanel.add(p3Panel);
        //     controlPanel.add(btnP4);
        //     controlPanel.add(p5Panel);
            
        //     // --- Panel de Resultados (Centro) ---
        //     resultadoArea = new JTextArea();
        //     resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        //     resultadoArea.setEditable(false);
        //     resultadoArea.setText("Bienvenido al Dashboard de la Librería. Seleccione una función para ver el resultado.");
        //     JScrollPane scrollPane = new JScrollPane(resultadoArea);
        //     scrollPane.setBorder(BorderFactory.createTitledBorder("Resultados de la Operación"));

        //     // Añadir al JFrame
        //     add(controlPanel, BorderLayout.NORTH);
        //     add(scrollPane, BorderLayout.CENTER);
            
        //     // Información de la Librería en el Sur
        //     JLabel infoLabel = new JLabel(
        //         "Librería: " + lib.getLibreria().getNombre() + " | Dirección: " + lib.getLibreria().getDireccion(), 
        //         SwingConstants.CENTER
        //     );
        //     infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        //     add(infoLabel, BorderLayout.SOUTH);
        // }

        // /**
        //  * Muestra el resultado de una operación en el JTextArea
        //  * @param title Título de la sección de resultados
        //  * @param content Contenido a mostrar
        //  */
        // private void mostrarResultado(String title, String content) {
        //     resultadoArea.setText("===============================================\n");
        //     resultadoArea.append("--- " + title + " ---\n");
        //     resultadoArea.append("===============================================\n\n");
        //     resultadoArea.append(content);
        //     resultadoArea.setCaretPosition(0); // Scroll al inicio
        // }
        
        // /**
        //  * Muestra un mensaje de error usando JOptionPane
        //  * @param title Título del error
        //  * @param message Mensaje de error
        //  */
        // private void mostrarError(String title, String message) {
        //     // Usamos JOptionPane en lugar de System.out.println para mostrar el error al usuario
        //     JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
        // }
    
//}

// {
//     import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class DashboardApp extends JFrame {

//     // 1. Nombres de las "cartas" para CardLayout
//     private static final String VISTA_HOME = "HomePanel";
//     private static final String VISTA_SETTINGS = "SettingsPanel";

//     // 2. Contenedor principal para las vistas y su Layout
//     private JPanel cardPanel;
//     private CardLayout cardLayout;

//     public DashboardApp() {
//         super("Dashboard con CardLayout");
        
//         // Inicializar el CardLayout y el JPanel que lo usará
//         cardLayout = new CardLayout();
//         cardPanel = new JPanel(cardLayout);

//         // --- 1. CREACIÓN DE VISTAS (JPanels) ---
        
//         // Vista 1: Panel de Inicio (Home)
//         JPanel homePanel = crearVista(VISTA_HOME, Color.LIGHT_GRAY, "¡Bienvenido al Dashboard!");
        
//         // Vista 2: Panel de Configuración (Settings)
//         JPanel settingsPanel = crearVista(VISTA_SETTINGS, Color.CYAN, "Configuración de la Aplicación");

//         // Añadir las vistas al contenedor principal (CardPanel)
//         cardPanel.add(homePanel, VISTA_HOME);
//         cardPanel.add(settingsPanel, VISTA_SETTINGS);

//         // --- 2. CREACIÓN DEL PANEL DE NAVEGACIÓN (Botones) ---
        
//         JPanel navPanel = new JPanel(new FlowLayout());
//         JButton btnHome = new JButton("Ir a Home");
//         JButton btnSettings = new JButton("Ir a Configuración");

//         // Lógica para cambiar las "cartas" (vistas)
//         btnHome.addActionListener(e -> cardLayout.show(cardPanel, VISTA_HOME));
//         btnSettings.addActionListener(e -> cardLayout.show(cardPanel, VISTA_SETTINGS));

//         navPanel.add(btnHome);
//         navPanel.add(btnSettings);
        
//         // --- 3. ENSAMBLAJE DEL FRAME PRINCIPAL ---
        
//         // Usamos BorderLayout en el JFrame para colocar la navegación arriba y las vistas en el centro
//         this.setLayout(new BorderLayout());
//         this.add(navPanel, BorderLayout.NORTH);  // Coloca los botones arriba
//         this.add(cardPanel, BorderLayout.CENTER); // Coloca las vistas en el centro

//         // Configuraciones finales del JFrame
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         this.setSize(600, 400);
//         this.setLocationRelativeTo(null); // Centrar en pantalla
//         this.setVisible(true);
//     }

//     // Método auxiliar para crear una vista (JPanel) simple
//     private JPanel crearVista(String name, Color color, String labelText) {
//         JPanel panel = new JPanel();
//         panel.setBackground(color);
//         panel.setBorder(BorderFactory.createTitledBorder(name));
        
//         // Añade componentes (Label, Listas, Botones, etc.)
//         JLabel label = new JLabel(labelText);
//         label.setFont(new Font("SansSerif", Font.BOLD, 18));
//         panel.add(label); 
        
//         // Aquí podrías añadir un JList, JTable, más botones, etc.
//         // Por ejemplo: panel.add(new JButton("Acción"));
        
//         return panel;
//     }

//     public static void main(String[] args) {
//         // Ejecutar la UI en el hilo de despacho de eventos (es una buena práctica en Swing)
//         SwingUtilities.invokeLater(() -> new DashboardApp());
//     }
// }
// }
