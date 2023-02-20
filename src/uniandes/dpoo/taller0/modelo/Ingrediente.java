package uniandes.dpoo.taller0.modelo;

/**
 * Esta clase encapsula la información sobre los ingredientes y su precio
 */
public class Ingrediente
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El nombre del ingrediente
	 */
	private String nombre;

	/**
	 * El costo del ingrediente
	 */
	private Integer costoAdicional;


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
		this.nombre = elNombre;
		this.costoAdicional = elPrecio;
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
		return nombre;
	}

	/**
	 * Consulta el precio del ingrediente
	 * 
	 * @return precio
	 */
	public Integer darPrecio()
	{
		return costoAdicional;
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
			Ingrediente otro = (Ingrediente) obj;
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
