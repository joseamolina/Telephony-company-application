package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;


public class PanelMenuClientes extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private JRadioButton registraCliente;
    private JRadioButton borraCliente;
    private JRadioButton cambiaTarifa;
    private JRadioButton datosCliente;
    private JRadioButton datosClientes;
    private JRadioButton cambiaDatosTarifa;
    private JButton sig;
    private JButton atras;
    private JTextArea contestacion;
    private ImplementacionVista vista;
    

    PanelMenuClientes(ImplementacionVista vista) {
        super();
        this.vista=vista;
        creaGUI();
    }
    
    private void creaGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        creaPanelMenu();
    }
    
    private void creaPanelMenu() {
        JPanel menuSeleccion = new JPanel();
        menuSeleccion.setLayout(new GridLayout(7, 1));
        JLabel texto = new JLabel("Choose an option for clients");
        menuSeleccion.add(texto);
        menuSeleccion.add(registraCliente = new JRadioButton("Register a client"));
        menuSeleccion.add(borraCliente = new JRadioButton("Delete a client"));
        menuSeleccion.add(cambiaTarifa = new JRadioButton("Change a rate"));
        menuSeleccion.add(datosCliente = new JRadioButton("Recover client data"));
        menuSeleccion.add(datosClientes = new JRadioButton("Recover a list of all clients"));
        menuSeleccion.add(cambiaDatosTarifa = new JRadioButton("Add new items to your rate"));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(registraCliente);
        buttonGroup.add(borraCliente);
        buttonGroup.add(cambiaTarifa);
        buttonGroup.add(datosCliente);
        buttonGroup.add(datosClientes);
        buttonGroup.add(cambiaDatosTarifa);
        JPanel textContestacion = new JPanel();
        textContestacion.add(contestacion = new JTextArea());
        add(menuSeleccion);
        add(textContestacion);
        atras = new JButton("Back");
	sig = new JButton("Next");
	sig.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (!getMenuSeleccionado().equals("Ninguno")){
		    if (getMenuSeleccionado().equals("DatosClientes"))
			vista.recuperaListado1();
		    else{
			setVisible(false);
			vista.muestraPanel(getMenuSeleccionado());
		    }
		}
	    }

	});

	atras.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
		vista.muestraPanel("PanelInicial");
	    }
	});
	JPanel gh = new JPanel();
	gh.add(sig);
	gh.add(atras);
	add(gh);
    }
    
    String getMenuSeleccionado() {
        if(registraCliente.isSelected())
            return "RegistraCliente";
        else if(borraCliente.isSelected())
            return "BorraCliente";
        else if(cambiaTarifa.isSelected())
            return "CambiaTarifa";
        else if(datosCliente.isSelected())
            return "DatosCliente";
        else if(datosClientes.isSelected())
            return "DatosClientes";
        else if(cambiaDatosTarifa.isSelected())
            return "CambiaDatosTarifa";
        else return "Ninguno";
    }
    
    public void setText(String text){
	contestacion.setText(text);
	vista.getVentana().pack();
	
    }
}
