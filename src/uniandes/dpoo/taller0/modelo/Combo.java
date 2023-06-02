package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase encapsula la información sobre los combos, su descuento y sus items
 */
public class Combo extends Producto
{
	// ************************************************************************
	// Atributos
	// ************************************************************************
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
	public Combo(String elNombre, Integer elDescuento)
	{
		super(elNombre, elDescuento);
		this.itemsCombo = new ArrayList<ProductoMenu>();
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	//Consulta el nombre del combo
	public String darNombre()
	{
		return super.getNombre();
	}

	//Consulta el descuento del combo
	public int darDescuento()
	{
		return super.getPrecio();
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

}
