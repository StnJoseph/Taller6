package uniandes.dpoo.taller0.modelo;

/**
 * Esta clase encapsula la información sobre los ingredientes y su precio
 */
public class Ingrediente extends Producto
{
	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo ingrediente e inicializa sus atributos con la información de
	 * los parámetros. 
	 * 
	 * @param elNombre El nombre del ingrediente.
	 * @param elPrecio El precio del ingrediente.
	 */
	public Ingrediente(String elNombre, Integer elPrecio)
	{
		super(elNombre, elPrecio);		
	}

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el nombre del ingrediente
	 * 
	 * @return nombre
	 */
	public String darNombre()
	{
		return super.getNombre();
	}

	/**
	 * Consulta el precio del ingrediente
	 * 
	 * @return precio
	 */
	public Integer darPrecio()
	{
		return super.getPrecio();
	}
}
