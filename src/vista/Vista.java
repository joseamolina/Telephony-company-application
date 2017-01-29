package vista;

import java.io.Serializable;

import javax.swing.JFrame;

public interface Vista extends Serializable {

    
    
    public void GUI();
    
    public JFrame getVentana();
    
    public void muestraPanel(String texto);

    public void setWarningRegistraCliente();

    public void setContestacion(String contestacion);
}
