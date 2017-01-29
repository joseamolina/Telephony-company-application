package excepcion;

public class FacturaNoExiste extends Exception {

    private static final long serialVersionUID = 1L;

    public FacturaNoExiste() {
	super("La factura no existe.");
    }
}
