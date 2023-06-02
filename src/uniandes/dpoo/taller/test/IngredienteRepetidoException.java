package uniandes.dpoo.taller.test;

public class IngredienteRepetidoException extends HamburguesaException{
	
	private static final long serialVersionUID = 1L;
	private String ingrediente;
	
	public IngredienteRepetidoException(String ingrediente) {
		super(ingrediente);
		this.ingrediente = ingrediente;
	}

    public String getIngrediente() {
        return ingrediente;
    }
	
}