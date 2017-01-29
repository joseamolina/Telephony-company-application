package excepcion;

public class FechaIncorrecta extends Exception {
    private static final long serialVersionUID = 1L;

    public FechaIncorrecta() {
	super("La fecha no está en el orden correcto");
    }

}
