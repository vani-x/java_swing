package com.proyecto.estructuras;
import java.io.Serializable;

import com.proyecto.modelos.*;
public class ColaFactura extends vectorFactura  implements Serializable{
	protected int fr;
	protected int fi;
	
	public ColaFactura() {
		this.fr = 0;
		this.fi = 0;
	}
}
