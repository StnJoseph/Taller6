package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;
import java.util.List;
import uniandes.dpoo.taller0.procesamiento.Restaurante;

public class Pedido 
{
	// ************************************************************************
	// Atributos
	// ************************************************************************
	private Restaurante rest;
	/**
	 * El numero de pedidos realizados
	 */
	private int numeroPedidos;

	/**
	 * El numero o id unico del pedido
	 */
	private int idPedido;
	
	/**
	 * El nombre del cliente
	 */
	private String nombreCliente;
	
	/**
	 * El correo del cliente
	 */
	private String direccionCliente;
	
	/**
	 * El pedido esta abierto(no finalizado)
	 */
	private boolean abierto;
	
	/**
	 * Una lista con los productos individuales del combo.
	 */
	private ArrayList<List<List<Integer>>> itemsPedido;


	// ************************************************************************
	// Constructores
	// ************************************************************************

	public Pedido(String elNombre, String laDireccion, int numPedido, int id, boolean estado)
	{
		this.nombreCliente = elNombre;
		this.direccionCliente = laDireccion;
		this.itemsPedido = new ArrayList<List<List<Integer>>>();
		this.numeroPedidos = numPedido;
		this.idPedido = id;
		this.abierto = estado;
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	//Consulta el nombre del cliente
	public String darNombreCliente()
	{
		return nombreCliente;
	}

	//Consulta la direccion del cliente
	public String darDireccionCliente()
	{
		return direccionCliente;
	}
	
	//Consulta el descuento del combo
	public int darNumeroPedidos()
	{
		return numeroPedidos;
	}
	
	//Consulta el descuento del combo
	public int darIdPedido()
	{
		return idPedido;
	}
	
	//Consulta el estado del pedido
	public boolean darEstadoPedido()
	{
		return abierto;
	}
	
	//Cambiar el estado del pedido
	public void cambiarEstadoPedido(boolean cerrado) 
	{
        this.abierto = cerrado;
    }

	
	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	//Registra el item seleccionado en la lista del pedido.

	public void agregarProducto(List<List<Integer>> items, int id)
	{	
		itemsPedido.add(items);
	}
	
	public ArrayList<List<List<Integer>>> darProductos(){
		return itemsPedido;
	}
	
//	public List<ProductoMenu> consultarItems()
//	{
//		List<ProductoMenu> items = new ArrayList<ProductoMenu>();
//		for (ProductoMenu item : itemsPedido)
//		{
//			items.add(item);
//		}
//		return items;
//	}

	// ************************************************************************
	// Métodos sobrecargados de una superclase
	// ************************************************************************

	@Override
	public boolean equals(Object obj)
	{
		if (obj.getClass() != this.getClass())
			return false;
		else
		{
			Pedido otro = (Pedido) obj;
			return this.nombreCliente.equals(otro.nombreCliente);
		}
	}

	@Override
	public int hashCode()
	{
		return nombreCliente.hashCode();
	}

	@Override
	public String toString()
	{
		return nombreCliente;
	}
}
