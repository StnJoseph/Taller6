package uniandes.dpoo.taller.test;

public class ValorSuperadoException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ValorSuperadoException(String producto) {		
		super("El producto" + producto + "no se puede agragar pues supera el límite de $150000");
	}

}