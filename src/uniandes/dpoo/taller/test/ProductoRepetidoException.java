package uniandes.dpoo.taller.test;

public class ProductoRepetidoException extends HamburguesaException{

	private static final long serialVersionUID = 1L;
	private String producto;
	
	public ProductoRepetidoException(String producto) {
		super(producto);
		this.producto = producto;
	}

    public String getProducto() {
        return producto;
    }
}