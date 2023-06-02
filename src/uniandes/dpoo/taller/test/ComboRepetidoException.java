package uniandes.dpoo.taller.test;

public class ComboRepetidoException extends HamburguesaException{

	private static final long serialVersionUID = 1L;
	private String combo;
	
	public ComboRepetidoException(String combo) {
		super(combo);
		this.combo = combo;
	}

    public String getCombo() {
        return combo;
    }

}
