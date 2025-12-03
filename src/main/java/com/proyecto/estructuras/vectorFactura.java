package com.proyecto.estructuras;
import java.io.Serializable;

import com.proyecto.modelos.*;
public class vectorFactura implements Serializable {
	protected int MAX = 50;
	protected Factura[] v = new Factura[MAX];
}
