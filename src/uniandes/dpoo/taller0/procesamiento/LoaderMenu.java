package uniandes.dpoo.taller0.procesamiento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.taller0.modelo.Ingrediente;
import uniandes.dpoo.taller.test.ComboRepetidoException;
import uniandes.dpoo.taller.test.HamburguesaException;
import uniandes.dpoo.taller.test.IngredienteRepetidoException;
import uniandes.dpoo.taller.test.ProductoRepetidoException;
import uniandes.dpoo.taller0.modelo.Combo;
import uniandes.dpoo.taller0.modelo.ProductoMenu;

public class LoaderMenu
{

	public static Restaurante cargarArchivo() throws FileNotFoundException, IOException, HamburguesaException
	{
		Map<String, ProductoMenu> productos = new HashMap<>();
		Map<String, Ingrediente> ingredientes = new HashMap<>();
		Map<String, Combo> combos = new HashMap<>();
		List<String> archivos = new ArrayList<>();

		archivos.add("./data/menu.txt");
		archivos.add("./data/combos.txt");
		archivos.add("./data/ingredientes.txt");
		
		
		for (String archivo : archivos)
		{
			// Abrir y leer el archivo menu.txt o ingredientes.txt 
			BufferedReader br = new BufferedReader(new FileReader(archivo));

			String linea = br.readLine();
			while (linea != null) 
			{
				String[] partes = linea.split(";");
				
				String nombre = partes[0];
				int valor = Integer.parseInt(partes[1].replace("%", ""));
				
				
				if (archivo == "./data/menu.txt") 
				{
					try {
						ProductoMenu elProducto = productos.get(nombre);
						
						if (elProducto == null) {
							elProducto = new ProductoMenu(nombre, valor);					
							productos.put(nombre, elProducto);
						}
						else 
							throw new ProductoRepetidoException(nombre);
																				
					} catch (ProductoRepetidoException e) {
						System.out.println("Producto repetido: " + e.getProducto());
					}	
				}
				
				else if (archivo == "./data/combos.txt")
				{
					try {
						Combo elCombo = combos.get(nombre);
						
						String item1 = partes[2];
						String item2 = partes[3];
						String item3 = partes[4];
						
						if (elCombo == null)
						{
							elCombo = new Combo(nombre, valor);					
							combos.put(nombre, elCombo);
							
							combos.get(nombre).agregarItem(productos.get(item1));
							combos.get(nombre).agregarItem(productos.get(item2));
							combos.get(nombre).agregarItem(productos.get(item3));
						}
						else
							throw new ComboRepetidoException(nombre);
						
					} catch (ComboRepetidoException e) {
						System.out.println("Combo repetido: " + e.getCombo());
					}										
				}									
				
				else if (archivo == "./data/ingredientes.txt")
				{
					try {
						Ingrediente elIngrediente = ingredientes.get(nombre);

						if (elIngrediente == null){
							elIngrediente = new Ingrediente(nombre, valor);												
							ingredientes.put(nombre, elIngrediente);
						}
						else 
							throw new IngredienteRepetidoException(nombre);
																				
					} catch (IngredienteRepetidoException e) {
						System.out.println("Ingrediente repetido: " + e.getIngrediente());
					}
					
				}
				
				linea = br.readLine(); 
			}

			br.close();
		}
		
		Restaurante calculadora = new Restaurante(productos, ingredientes, combos);
		return calculadora;
	}
}

