package com.clearminds.maquina;

import java.util.ArrayList;

import com.clearminds.componentes.Celda;
import com.clearminds.componentes.Producto;

public class MaquinaDulces {

	private Celda celda1 = new Celda(null);
	private Celda celda2 = new Celda(null);
	private Celda celda3 = new Celda(null);
	private Celda celda4 = new Celda(null);
	private double saldo;

	public void configurarMaquina(String codigo1, String codigo2, String codigo3, String codigo4) {
		this.celda1.setCodigo(codigo1);
		this.celda2.setCodigo(codigo2);
		this.celda3.setCodigo(codigo3);
		this.celda4.setCodigo(codigo4);
	}

	public void mostrarConfiguracion() {
		System.out.println("Codigo 1:" + celda1.getCodigo());
		System.out.println("Codigo 2:" + celda2.getCodigo());
		System.out.println("Codigo 3:" + celda3.getCodigo());
		System.out.println("Codigo 4:" + celda4.getCodigo());
		System.out.println("Saldo actual de la maquina: " + saldo);
	}

	public Celda buscarCelda(String codigo) {
		ArrayList<Celda> listaCeldas = new ArrayList<Celda>();
		listaCeldas.add(celda1);
		listaCeldas.add(celda2);
		listaCeldas.add(celda3);
		listaCeldas.add(celda4);

		for (Celda celda : listaCeldas) {
			if (codigo.equals(celda.getCodigo())) {
				return celda;
			}
		}

		return null;
	}

	public void cargarProducto(Producto producto, String codigo, int cantidadItems) {
		Celda celdaRecuperada;
		celdaRecuperada = buscarCelda(codigo);

		if (celdaRecuperada != null) {
			celdaRecuperada.ingresarProducto(producto, cantidadItems);
		} else {
			System.out.println("Celda con código " + codigo + " no encontrada. No se puede cargar el producto.");
		}
	}

	public void mostrarProductos() {
		ArrayList<Celda> listaCeldas = new ArrayList<Celda>();

		listaCeldas.add(celda1);
		listaCeldas.add(celda2);
		listaCeldas.add(celda3);
		listaCeldas.add(celda4);

		for (Celda celda : listaCeldas) {
			System.out.println("Codigo celda:" + celda.getCodigo());
			System.out.println("Stock actual:" + celda.getStock());

			Producto producto = celda.getProducto();

			if (producto != null) {
				System.out.println("Nombre:" + celda.getProducto().getNombre());
				System.out.println("Precio:" + celda.getProducto().getPrecio());
			} else {
				System.out.println("No hay producto en esta celda");
			}

		}
	}

	public Producto buscarProductoEnCelda(String codigo) {

		ArrayList<Celda> listaCeldas = new ArrayList<Celda>();

		listaCeldas.add(celda1);
		listaCeldas.add(celda2);
		listaCeldas.add(celda3);
		listaCeldas.add(celda4);

		for (Celda celda : listaCeldas) {
			if (codigo.equals(celda.getCodigo())) {
				return celda.getProducto();

			}
		}

		return null;
	}

	public double consultarPrecio(String codigo) {
		ArrayList<Celda> listaCeldas = new ArrayList<Celda>();

		listaCeldas.add(celda1);
		listaCeldas.add(celda2);
		listaCeldas.add(celda3);
		listaCeldas.add(celda4);

		double resultado = 0.0;

		for (Celda celda : listaCeldas) {
			if (codigo.equals(celda.getCodigo())) {
				resultado = celda.getProducto().getPrecio();

			}
		}

		return resultado;
	}

	public Celda buscarCeldaProducto(String codigoProducto) {

		ArrayList<Celda> listaCeldas = new ArrayList<Celda>();

		listaCeldas.add(celda1);
		listaCeldas.add(celda2);
		listaCeldas.add(celda3);
		listaCeldas.add(celda4);

		for (Celda celda : listaCeldas) {
			Producto producto = celda.getProducto();
			if (producto != null && codigoProducto.equals(producto.getCodigo())) {
				return celda;
			}
		}

		return null;
	}

	public void incrementarProducto(String codigo, int cantidadItemsIncre) {
		Celda celdaEncontrada;
		celdaEncontrada = buscarCeldaProducto(codigo);
		celdaEncontrada.setStock(cantidadItemsIncre);
	}

	public void vender(String codigo) {
		ArrayList<Celda> listaCeldas = new ArrayList<Celda>();

		listaCeldas.add(celda1);
		listaCeldas.add(celda2);
		listaCeldas.add(celda3);
		listaCeldas.add(celda4);

		for (Celda celda : listaCeldas) {

			int stockActual = celda.getStock();
			double precioActual;

			if (codigo.equals(celda.getCodigo())) {
				celda.setStock(stockActual - 1);
				precioActual = celda.getProducto().getPrecio();
				saldo += precioActual;

			}
		}

		mostrarProductos();
	}

	public double venderConCambio(String codigo_celda, double valor_ingresado) {

		ArrayList<Celda> listaCeldas = new ArrayList<Celda>();

		listaCeldas.add(celda1);
		listaCeldas.add(celda2);
		listaCeldas.add(celda3);
		listaCeldas.add(celda4);

		for (Celda celda : listaCeldas) {

			if (codigo_celda.equals(celda.getCodigo()) && celda.getStock() > 0) {
				double precioProducto = celda.getProducto().getPrecio();
				if (valor_ingresado >= precioProducto) {
					double cambio = valor_ingresado - precioProducto;
					saldo += precioProducto;
					celda.setStock(celda.getStock() - 1);
					System.out.println("Cambio a devolver $: " + cambio);
					return cambio;
				}

			}
		}
		return 0.0;

	}

}
