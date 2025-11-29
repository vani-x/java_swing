package com.proyecto;

import javax.swing.SwingUtilities;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(() -> {
            LibreriaServicio lib = new LibreriaServicio();
            //new modoGrafico(lib).setVisible(true);
            new modoGrafico().createAndShowGUI();
        });
    }
}
