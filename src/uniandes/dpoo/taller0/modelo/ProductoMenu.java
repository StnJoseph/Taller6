package uniandes.dpoo.taller0.modelo;

/**
 * Esta clase encapsula la información sobre los productos base del menu y su precio
 */
public class ProductoMenu extends Producto
{
	
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
		super(elNombre, elPrecio);
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
		return super.getNombre();
	}

	/**
	 * Consulta el precio del producto
	 * 
	 * @return precio
	 */
	public Integer darPrecio()
	{
		return super.getPrecio();
	}

}
