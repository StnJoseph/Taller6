package uniandes.dpoo.taller0.procesamiento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.taller0.modelo.ProductoMenu;
import uniandes.dpoo.taller0.modelo.Combo;
import uniandes.dpoo.taller0.modelo.Ingrediente;
import uniandes.dpoo.taller0.modelo.Pedido;
import uniandes.dpoo.taller0.modelo.ProductoAjustado;

/**
 * Esta es la clase que es capaz de calcular estadísticas sobre los juegos
 * olímpicos. Para calcular las estadísticas, esta clase agrupa la información
 * sobre los atletas, países y eventos, pero no tiene información sobre las
 * participaciones (eso es responsabilidad de los atletas y de los eventos).
 */
public class Restaurante
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	//Una lista con todos los productos del menu.
	private List<ProductoMenu> productosMenu;

	//Una lista con todos los ingredientes.
	private List<Ingrediente> ingredientes;

	//Una lista con los combos registrados. 
	private List<Combo> combos;

	//Un mapa con los pedidos registrados. 
	private Map<Integer, Pedido> pedidos;
	
	
	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un restaurante guardando la info sobre productos base, ingredientes y combos.
	 * 
	 * @param productosMenu Un mapa donde las llaves son los nombres de
	 *                	los prodcutos y los valores los precios.
	 * @param ingredientes  Un mapa donde las llaves son los ingredientes 
	 * 					y los valores son los precios.
	 * @param combos Una lista con los combos.
	 */
	public Restaurante(Map<String, ProductoMenu> productosMenu, Map<String, Ingrediente> ingredientes, Map<String, Combo> combos)
	{
		this.productosMenu = new ArrayList<ProductoMenu>(productosMenu.values());
		this.ingredientes = new ArrayList<Ingrediente>(ingredientes.values());
		this.combos = new ArrayList<Combo>(combos.values());
		this.pedidos = new HashMap<Integer, Pedido>();
	}

	// ************************************************************************
	// Métodos
	// ************************************************************************


	//Retorna el producto con el nombre indicado
	private ProductoMenu buscarProducto(String nombreProducto)
	{
		ProductoMenu elProducto = null;
		for (int i = 0; i < productosMenu.size() && elProducto == null; i++)
		{
			if (productosMenu.get(i).darNombre().equals(nombreProducto))
				elProducto = productosMenu.get(i);
		}
		return elProducto;
	}

	//Retorna el ingrediente con el nombre indicado
	private Ingrediente buscarIngrediente(String nombreIngrediente)
	{
		Ingrediente elIngrediente = null;
		for (int i = 0; i < ingredientes.size() && elIngrediente == null; i++)
		{
			if (ingredientes.get(i).darNombre().equals(nombreIngrediente))
				elIngrediente = ingredientes.get(i);
		}
		return elIngrediente;
	}
	
	//Retorna una lista con los nombres de los productos 
	public List<String> darNombreProductos()
	{
		List<String> nombres = new ArrayList<String>();
		for (ProductoMenu producto : productosMenu)
		{
			nombres.add(producto.darNombre());
		}
		return nombres;
	}
	
	//Retorna una lista con los precios de los productos 
	public List<Integer> darPrecioProductos()
	{
		List<Integer> precios = new ArrayList<Integer>();
		for (ProductoMenu producto : productosMenu)
		{
			precios.add(producto.darPrecio());
		}
		
		return precios;
	}

	//Retorna una lista con los nombres de los combos
	public List<String> darNombreCombos()
	{
		List<String> nombres = new ArrayList<String>();
		for (Combo combos : combos)
		{
			nombres.add(combos.darNombre());
		}
		
		return nombres;
	}
	
	//Retorna una lista con los precios de los combos
	public List<Integer> darPrecioCombo()
	{
		List<Integer> precios = new ArrayList<Integer>();
		for (Combo combo : combos)
		{
			int precioCombo = 0;
			
			for (String item : combo.consultarItems()) 
			{
				precioCombo += (buscarProducto(item).darPrecio()*(1-combo.darDescuento()));
			}
			
			precios.add(precioCombo);
		}

		return precios;
	}
	
	//Retorna una lista con los nombres de los ingredientes
	public List<String> darNombreIngredientes()
	{
		List<String> nombres = new ArrayList<String>();
		for (Ingrediente ingrediente : ingredientes)
		{
			nombres.add(ingrediente.darNombre());
		}

		return nombres;
	}
	
	//Retorna una lista con los precios de los ingredientes 
	public List<Integer> darPrecioIngredientes()
	{
		List<Integer> precios = new ArrayList<Integer>();
		for (Ingrediente ingrediente : ingredientes)
		{
			precios.add(ingrediente.darPrecio());
		}
			
		return precios;
	}
	
	
	//Crea un HashMap que contendra los pedidos con el id como llave 
	//Map<Integer, Pedido> pedidos = new HashMap<Integer, Pedido>();
	
	
	//Crea una lista con los ids de los pedidos como llave y su informacion como value
	public Map<Integer, Pedido> crearPedido(String nombre, String direccion, int numPedidos, int id, ArrayList<List<List<Integer>>> items)
	{
		Pedido pedido = new Pedido(nombre, direccion, numPedidos, id, true);
		
		//FUNCION DE AGREGAR
		//agregarPedido(id, items);
		
		for (List<List<Integer>> item :items) 
		{
			pedido.agregarItem(item);
		}
	
		pedidos.put(id, pedido);
		
		return pedidos;
	}
	
	
	
	//Crear un producto ajustado
	public ProductoAjustado crearProductoAjustado(Integer numProducto, List<Integer> agregar, List<Integer> eliminar) 
	{
		String nombreProducto = darNombreProductos().get(numProducto-1);
		ProductoAjustado productoAjustado = new ProductoAjustado(nombreProducto);
		
		for (String nombreIngredienteAgregar: darNombreItem(agregar)) 
		{
			productoAjustado.agregarIngrediente(buscarIngrediente(nombreIngredienteAgregar));
		}
		
		for (String nombreIngredienteEliminar: darNombreItem(eliminar)) 
		{
			productoAjustado.eliminarIngrediente(buscarIngrediente(nombreIngredienteEliminar));
		}
		
		return productoAjustado;
	}
	
	//Lista con los nombre de los items que antes eran numero
	public List<String> darNombreItem(List<Integer> items)
	{	
		List<String> nombresItems = new ArrayList<String>();

		for (int numItem: items)
		{
			if (numItem <= 22) 
				nombresItems.add(darNombreProductos().get(numItem-1));
			else if (numItem > 22 && numItem <= 26) 
				nombresItems.add(darNombreCombos().get(numItem-23));
			else if (numItem > 26 && numItem <= 40)
				nombresItems.add(darNombreIngredientes().get(numItem-27));
		}
		
		return nombresItems;
	}
	
	//Cierra el pedido y prepara la factura
	public void factura(Integer id)
	{
		System.out.println("\n----------------------------------------------------");
		System.out.println("FACTURA");
		System.out.println("----------------------------------------------------");
		
		Pedido pedido = darPedidos().get(id);
		pedido.cambiarEstadoPedido(false);
		int total = 0;

		System.out.println("DATOS\n");		
		System.out.println("Nombre: "+pedido.darNombreCliente());
		System.out.println("Direccion: "+pedido.darDireccionCliente());
		System.out.println("Pedido numero: "+pedido.darNumeroPedidos());
		System.out.println("Id pedido: "+pedido.darIdPedido());
		System.out.println("\n----------------------------------------------------");
		System.out.println("PRODUCTOS");

		List<Integer> precios = darProductosPedido(pedido);

		System.out.println("\n----------------------------------------------------\n");
		
		for (int precio: precios) 
		{
			total +=precio;
		}
		double iva = 0.19;
		double totalNeto = total + (total*iva);

		System.out.println("Valor neto-------> $"+total);
		System.out.println("IVA--------------> $"+total*iva);
		System.out.println("Precio Total-----> $"+totalNeto);
		System.out.println("\n----------------------------------------------------\n");	
		return ;
	}
	
	
	//Agregar pedido
	public void agregarPedido(int id, ArrayList<List<List<Integer>>> items) 
	{
		Pedido pedido = darPedidos().get(id);
		
		for (List<List<Integer>> item :items) 
		{
			pedido.agregarItem(item);
		}
	
		pedidos.put(id, pedido);
		return ;
	}
	
	//Imprime los productos del pedido
	public List<Integer> darProductosPedido (Pedido pedido) 
	{
		List<Integer> precios = new ArrayList<Integer>();
		
		for (List<List<Integer>> item: pedido.darItems()) 
		{				
			int cont = 0;
			for(List<Integer> listadatos: item)
			{
				cont++;
				for (Integer dato: listadatos) 
				{					
					if (dato >0 && dato <= 22) 
					{
						precios.add(darPrecioProductos().get(dato-1));
						System.out.println("\n"+darNombreProductos().get(dato-1)+"----------> $"+darPrecioProductos().get(dato-1));
					}
					
					else if (dato > 22 && dato <= 26)
					{
						precios.add(darPrecioCombo().get(dato-23));
						combos.get(dato-23).darDescuento();
						System.out.println("\n"+darNombreCombos().get(dato-23)+"----------> $"+darPrecioCombo().get(dato-23));						
						List<String> products = combos.get(dato-23).consultarItems();
						
						for (String producto: products) 
						{
							System.out.println("   "+producto+"-------> +$"+buscarProducto(producto).darPrecio());
						}
					}
					
					else if (dato > 26 && dato <= 40)
					{
						if(cont==2) 
						{
							precios.add(darPrecioIngredientes().get(dato-27));
							System.out.println("   Ad "+darNombreIngredientes().get(dato-27)+"-----> +$"+darPrecioIngredientes().get(dato-27));
						}
						
						else
							System.out.println("   Del "+darNombreIngredientes().get(dato-27)+"-----> $"+darPrecioIngredientes().get(dato-27));
					}
					else 
						continue;	
				}	
			}
		}
		return precios;
		
	}
	
	
	//Tabla con los ids y los respectivos pedidos
	public Map<Integer, Pedido> darPedidos()
	{	
		Map<Integer, Pedido> listaPedidos = pedidos;
		
		return listaPedidos;
	}
	
}
