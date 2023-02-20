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
import uniandes.dpoo.taller0.modelo.Combo;
import uniandes.dpoo.taller0.modelo.ProductoMenu;

public class LoaderMenu
{

	public static Restaurante cargarArchivo() throws FileNotFoundException, IOException
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
			while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
			{
				// Separar los los valores por el signo ";"
				String[] partes = linea.split(";");
				
				//Extrae el primer dato(nombre) y segundo (valor, descuento o costo)
				String nombre = partes[0];
				int valor = Integer.parseInt(partes[1].replace("%", ""));
				
				//Busca la clase con el nombre
				Combo elCombo = combos.get(nombre);
				ProductoMenu elProducto = productos.get(nombre);
				Ingrediente elIngrediente = ingredientes.get(nombre);

				if (archivo == "./data/menu.txt") 
				{
					// Si no se había encontrado antes el producto, se agrega como un nuevo producto.
					if (elProducto == null)
					{
						elProducto = new ProductoMenu(nombre, valor);
						productos.put(nombre, elProducto);
					}
				}
				
				else if (archivo == "./data/combos.txt")
				{
					double porcentajeDescuento = Double.parseDouble(partes[1].replace("%", ""));
					double descuento = porcentajeDescuento/100;
					String item1 = partes[2];
					String item2 = partes[3];
					String item3 = partes[4];
					
					// Si no se había encontrado antes a el combo, se agrega como un nuevo combo.
					if (elCombo == null)
					{
						elCombo = new Combo(nombre, descuento);
						combos.put(nombre, elCombo);
					}

					combos.get(nombre).agregarItem(productos.get(item1));
					combos.get(nombre).agregarItem(productos.get(item2));
					combos.get(nombre).agregarItem(productos.get(item3));
				}
				
				else if (archivo == "./data/ingredientes.txt")
				{
					// Si no se había encontrado antes a el ingrediente, se agrega como un nuevo ingrediente.
					if (elIngrediente == null)
					{
						elIngrediente = new Ingrediente(nombre, valor);
						ingredientes.put(nombre, elIngrediente);
					}
				}
				
				linea = br.readLine(); // Leer la siguiente línea
			}

			br.close();
		}
		Restaurante calculadora = new Restaurante(productos, ingredientes, combos);
		return calculadora;
	}
}
