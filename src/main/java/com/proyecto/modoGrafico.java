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
    private JTextArea textArea;

    public modoGrafico(LibreriaServicio lib) {
        this.lib = lib;
        setTitle("Libreria UMSA - Modo Grafico");
        setSize(600, 400);
    }

}
