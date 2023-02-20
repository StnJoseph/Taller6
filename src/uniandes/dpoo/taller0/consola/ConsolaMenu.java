package uniandes.dpoo.taller0.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import uniandes.dpoo.taller0.procesamiento.Restaurante;
import uniandes.dpoo.taller0.modelo.Pedido;
import uniandes.dpoo.taller0.procesamiento.LoaderMenu;

public class ConsolaMenu
{
	/**
	 * Esta es la calculadora de estadísticas que se usará para hacer todas las
	 * operaciones de la aplicación.
	 */
	private Restaurante calculadora;

	/**
	 * Ejecuta la aplicación: le muestra el menú al usuario y la pide que ingrese
	 * una opción, y ejecuta la opción seleccionada por el usuario. Este proceso se
	 * repite hasta que el usuario seleccione la opción de abandonar la aplicación.
	 */
	public void ejecutarAplicacion()
	{
		ejecutarCargarArchivos();
		System.out.println("BIENVENIDX A EL CORRAL\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor selecciona una opción"));
				System.out.println("\n----------------------------------------------------");				
				
				if (opcion_seleccionada == 1) 
				{
					ejecutarProductosMenu();
					ejecutarCombos();
					ejecutarIngredientes();	
				}
					
				else if (opcion_seleccionada == 2 && calculadora != null) 
					ejecutarNuevoPedido();

				else if (opcion_seleccionada == 3 && calculadora != null) 
					ejecutarAdicionarItem();
			
				else if (opcion_seleccionada == 4 && calculadora != null)
					ejecutarProductosPedido();
				
				else if (opcion_seleccionada == 5 && calculadora != null) 
					ejecutarCalcularFactura();
				
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Finalizando orden...");
					continuar = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("----------------------------------------------------");
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	
	
	/**
	 * Muestra al usuario el menú con las opciones para que escoja la siguiente
	 * acción que quiere ejecutar.
	 */
	public void mostrarMenu()
	{		
		System.out.println("----------------------------------------------------");
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("----------------------------------------------------");
		System.out.println("1. Mostrar el menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Consultar la información de un pedido dado su id");
		System.out.println("5. Cerrar un pedido y guardar la factura");
		System.out.println("6. Salir de la aplicación");
		System.out.println("----------------------------------------------------");
	}

	//Cargar Archivo
	private void ejecutarCargarArchivos()
	{
		try
		{
			calculadora = LoaderMenu.cargarArchivo();
			System.out.println();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}

	//Imprimir un mensaje en la consola y luego lee lo que escriba el usuario.
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	
	//Imprime los productos.
	private void ejecutarProductosMenu()
	{
		System.out.println("\nProductos disponibles: \n");

		Collection<String> productos = calculadora.darNombreProductos();
		List<Integer> precios = calculadora.darPrecioProductos();
		
		int cont=0;
		
		for (String producto : productos)
		{
			System.out.println(" ("+(cont+1)+") "+producto+" - $"+precios.get(cont));
			cont++;
		}
	}
	
	//Imprime los combos.
	private void ejecutarCombos()
	{
		System.out.println("\nCombos disponibles: \n");
		
		Collection<String> combos = calculadora.darNombreCombos();		
		List<Integer> precios = calculadora.darPrecioCombo();
		
		int cont=0;
		
		for (String combo : combos)
		{
			System.out.println(" ("+(cont+23)+") "+combo+" - $"+precios.get(cont));
			cont++;
		}
	}
	
	//Imprime los ingredientes.
	private void ejecutarIngredientes()
	{
		System.out.println("\nAdiciones disponibles: \n");
			
		Collection<String> ingredientes = calculadora.darNombreIngredientes();		
		List<Integer> precios = calculadora.darPrecioIngredientes();

		int cont=0;
		
		for (String ingrediente : ingredientes)
		{
			System.out.println(" ("+(cont+27)+") "+ingrediente+" - $"+precios.get(cont));
			cont++;
		}
		System.out.println("");
	}
	

	//Ejecuta opcion 2
	private void ejecutarNuevoPedido() 
	{
		System.out.println("¡Iniciemos con el pedido!\n");
		String nombre = (input("Digita tu nombre"));
		String direccion = (input("Y tu direccion"));
		System.out.println("\nPerfecto... ahora de ordenar");
		System.out.println("(Recuerda digitar 0 para terminar o cerrar cualquier proceso)");
		Random aleatorio = new Random();
		int id =1000+aleatorio.nextInt( (10000+1) - 1000);
		int numPedido = 0;
		
		ArrayList<List<List<Integer>>> items = seleccionarItems(id);
		
		numPedido++;
		
		if (items.isEmpty()==false) 
			//------------------Creacion de pedido--------------------------//
			calculadora.crearPedido(nombre, direccion, numPedido, id, items);
			//--------------------------------------------------------------//	
	}
	
	
	// Crea la lista con los items de los producto y combos
	public ArrayList<List<List<Integer>>> seleccionarItems(int id)
	{
		ArrayList<List<List<Integer>>> items = new ArrayList<List<List<Integer>>>();
		boolean value = true;
		
		while (value) 
		{
			int item = Integer.parseInt(input("\nDigita el numero del item del menu"));
			
			List<List<Integer>> listaIngredintes = new ArrayList<>();
			
			if (item > 0 && item <=22) 
			{	
				System.out.println("("+calculadora.darNombreProductos().get(item-1)+")");
				List<Integer> listaItem = new ArrayList<>();
				listaItem.add(item);
				listaIngredintes.add(listaItem);
			
				//Agregar ingrediente
				int add = Integer.parseInt(input("\n-Deseas agregar un ingrediente si(1) o no(0)"));
				
				if (add == 1) 
				{
					//items.add(item);
					boolean valueAdd = true;
					List<Integer> listaAdicionar = new ArrayList<Integer>();

					while (valueAdd)
					{
						int ingrediente = Integer.parseInt(input("\n--Digita el numero del(os) ingrediente(s) (cero para salir)"));									
						
						if (ingrediente == 0)
							valueAdd = false;
						else if (ingrediente>26 && ingrediente<=41) 
						{
							System.out.println("  ("+calculadora.darNombreIngredientes().get(ingrediente-27)+")");
							listaAdicionar.add(ingrediente);
						}
						else
							System.out.println("--Numero no valido para un ingrediente");
					}
					
					listaIngredintes.add(listaAdicionar);
				}
				
				else 
				{
					List<Integer> no = new ArrayList<>();
					no.add(0);
					listaIngredintes.add(no);
				}
				
				
				//Eliminar ingrediente	
				int del = Integer.parseInt(input("\n-Deseas eliminar un ingrediente si(1) o no(0)"));
				if (del == 1) 
				{
					//items.del(item);
					boolean valueDel = true;
					List<Integer> listaEliminar = new ArrayList<Integer>();

					while (valueDel)
					{
						int ingrediente = Integer.parseInt(input("\n--Digita el numero del(os) ingrediente(s) (cero para salir)"));
															
						if (ingrediente == 0)
							valueDel = false;
						else if (ingrediente>26 && ingrediente<=41)
						{
							System.out.println("  ("+calculadora.darNombreIngredientes().get(ingrediente-27)+")");
							listaEliminar.add(ingrediente);
						}
						else
							System.out.println("--Numero no valido para un ingrediente");
					}
					
					listaIngredintes.add(listaEliminar);
				}
				
				else 
				{
					List<Integer> no = new ArrayList<>();
					no.add(0);
					listaIngredintes.add(no);
				}
			
				//---------------------//
				items.add(listaIngredintes);
				//---------------------//
			}
			
			else if (item >22 && item <= 26) 
			{
				System.out.println("("+calculadora.darNombreCombos().get(item-23)+")");
				List<Integer> listaItem = new ArrayList<>();
				listaItem.add(item);
				listaIngredintes.add(listaItem);
				items.add(listaIngredintes);
			}
				
			else if (item >26 && item <= 41)
					System.out.println("\nLos ingredientes no se pueden pedir sin un producto\n");	

			else if (item == 0)
			{
				value = false;
				System.out.println("\nPedido finalizado");
				System.out.println("id de tu pedido: "+id+"\n");
			}
			else
				System.out.println("\nIntenta otro numero de item, ese no lo tenemos");
					
		}
		return items;
	}
	
	
	//Ejecutar opcion 3
	public void ejecutarAdicionarItem() 
	{
		int id = Integer.parseInt(input("Digita el id de tu pedido"));
		
		if (calculadora.darPedidos().get(id)==null)
			System.out.println("\nPedido no encontrado\n");					
		else if(calculadora.darPedidos().get(id).darEstadoPedido() == false)					
			System.out.println("El pedido ya fue cerrado, no se pueden adicionar elementos");					
		else
		{
			ArrayList<List<List<Integer>>> items = seleccionarItems(id);
			calculadora.agregarPedido(id, items);
			System.out.println("Los items han sido adicionados a tu pedido");
		}
	}
	
	//Ejecutar opcion 4
	public void ejecutarProductosPedido () 
	{
		int id = Integer.parseInt(input("Digita el id de tu pedido"));
		System.out.println("----------------------------------------------------");
		Pedido pedido = calculadora.darPedidos().get(id);
		calculadora.darProductosPedido(pedido);
	}
	
	
	
	//Ejecuta opcion 5
	public void ejecutarCalcularFactura () 
	{
		int id = Integer.parseInt(input("Digita el id del pedido a cerrar"));

		if (calculadora.darPedidos().get(id)==null)
		{
			System.out.println("\nPedido no encontrado\n");
		}
		else 
		{
			System.out.println("\nEl pedido ha sido cerrado y su factura esta lista\n");
			calculadora.factura(id);
		}
	}
	
	
	/**
	 * Este es el método principal de la aplicación, con el que inicia la ejecución
	 * de la aplicación
	 * 
	 * @param args Parámetros introducidos en la línea de comandos al invocar la
	 *             aplicación
	 */
	public static void main(String[] args)
	{
		ConsolaMenu consola = new ConsolaMenu();
		consola.ejecutarAplicacion();
	}

}
