package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase encapsula la información sobre los combos, su descuento y sus items
 */
public class Combo
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	//El nombre del combo
	private String nombreCombo;

	//El descuento del combo
	private double descuento;
	
	//Una lista con los productos individuales del combo.
	private List<ProductoMenu> itemsCombo;


	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo combo e inicializa sus atributos con la información de
	 * los parámetros. 
	 * 
	 * @param elNombre El nombre del combo.
	 * @param elDescuento El descuento del combo.
	 */
	public Combo(String elNombre, Double elDescuento)
	{
		this.nombreCombo = elNombre;
		this.descuento = elDescuento;
		this.itemsCombo = new ArrayList<ProductoMenu>();
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	//Consulta el nombre del combo
	public String darNombre()
	{
		return nombreCombo;
	}

	//Consulta el descuento del combo
	public Double darDescuento()
	{
		return descuento;
	}

	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	//Adiciona un producto al menu.
	public void agregarItem(ProductoMenu nuevoItem)
	{
		itemsCombo.add(nuevoItem);
	}
	
	public List<String> consultarItems()
	{
		List<String> items = new ArrayList<String>();
		for (ProductoMenu item : itemsCombo)
		{
			items.add(item.darNombre());
		}
		return items;
	}

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
			Combo otro = (Combo) obj;
			return this.nombreCombo.equals(otro.nombreCombo);
		}
	}

	@Override
	public int hashCode()
	{
		return nombreCombo.hashCode();
	}

	@Override
	public String toString()
	{
		return nombreCombo;
	}

}
