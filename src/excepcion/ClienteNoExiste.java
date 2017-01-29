package excepcion;

public class ClienteNoExiste extends Exception {

    private static final long serialVersionUID = 1L;

    public ClienteNoExiste() {
	super("No existía tal cliente.");
    }

}
