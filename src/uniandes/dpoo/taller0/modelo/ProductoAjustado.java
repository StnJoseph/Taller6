package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;
import java.util.List;

public class ProductoAjustado 
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	//El nombre del producto a modificar
	private String nombreProducto;
	
	//Una lista con los ingredientes agregados
	private List<Ingrediente> agregados;
	
	//Una lista con los ingredientes eliminados
	private List<Ingrediente> eliminados;
	
//	//Una lista con los ingredientes eliminados
//	private int precio;


	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo producto e inicializa sus atributos con la información de
	 * los parámetros. 
	 * 
	 * @param elNombre El nombre del combo.
	 * @param elDescuento El descuento del combo.
	 */
	public ProductoAjustado(String elNombre)
	{
		this.nombreProducto = elNombre;
		this.agregados = new ArrayList<Ingrediente>();;
		this.eliminados = new ArrayList<Ingrediente>();;
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	//Consulta el nombre del producto
	public String darNombre()
	{
		return nombreProducto;
	}

	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	//Agrega un ingrediente para agregar al producto.
	public void agregarIngrediente(Ingrediente nuevoIngrediente)
	{
		agregados.add(nuevoIngrediente);
	}
	
	//Agrega un ingrediente para eliminar del producto
	public void eliminarIngrediente(Ingrediente nuevoIngrediente)
	{
		eliminados.add(nuevoIngrediente);
	}
	
	//Retorna una lista con los ingredientes agregados 
	public List<String> consultarAgregados()
	{
		List<String> add = new ArrayList<String>();
		for (Ingrediente ingrediente : agregados)
		{
			add.add(ingrediente.darNombre());
		}
		return add;
	}
	
	//Retorna una lista con los ingredientes eliminados 
	public List<String> consultarEliminados()
	{
		List<String> del = new ArrayList<String>();
		for (Ingrediente ingrediente : eliminados)
		{
			del.add(ingrediente.darNombre());
		}
		return del;
	}
	
//	//Retorna el nuevo precio del producto 
//	public List<String> consultarPrecio()
//	{
//		for (Ingrediente nuevo : agregados)
//		{
//			nuevo.darPrecio();
//		}
//		return del;
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
			ProductoAjustado otro = (ProductoAjustado) obj;
			return this.nombreProducto.equals(otro.nombreProducto);
		}
	}

	@Override
	public int hashCode()
	{
		return nombreProducto.hashCode();
	}

	@Override
	public String toString()
	{
		return nombreProducto;
	}

}
