package uniandes.dpoo.taller0.modelo;

/**
 * Esta clase encapsula la información sobre los productos base del menu y su precio
 */
public class ProductoMenu
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El nombre del producto
	 */
	private String nombre;

	/**
	 * El precio del producto
	 */
	private Integer precioBase;


	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo producto e inicializa sus atributos con la información de
	 * los parámetros. 
	 * 
	 * @param elNombre El nombre del producto.
	 * @param elPrecio El precio base del producto.
	 */
	public ProductoMenu(String elNombre, Integer elPrecio)
	{
		this.nombre = elNombre;
		this.precioBase = elPrecio;
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el nombre del producto
	 * 
	 * @return nombre
	 */
	public String darNombre()
	{
		return nombre;
	}

	/**
	 * Consulta el precio del producto
	 * 
	 * @return precio
	 */
	public Integer darPrecio()
	{
		return precioBase;
	}

	// ************************************************************************
	// Otros métodos
	// ************************************************************************


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
			ProductoMenu otro = (ProductoMenu) obj;
			return this.nombre.equals(otro.nombre);
		}
	}

	@Override
	public int hashCode()
	{
		return nombre.hashCode();
	}

	@Override
	public String toString()
	{
		return nombre;
	}

}
