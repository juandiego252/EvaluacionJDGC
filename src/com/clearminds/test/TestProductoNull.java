package com.clearminds.test;

import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestProductoNull {

	public static void main(String[] args) {
		MaquinaDulces maquina=new MaquinaDulces();
		maquina.configurarMaquina("A1", "A2", "B1", "B2");
		
		Producto producto = new Producto("KE34", "Papitas", 0.85);
		maquina.cargarProducto(producto, "B1", 4);
		
		// Null pointer, no se puede accerder al nombre debido a que esta null
		Producto prod=maquina.buscarProductoEnCelda("A1");
		// Error linea 16
		System.out.println("Producto encontrado:"+prod.getNombre());
	}

}
