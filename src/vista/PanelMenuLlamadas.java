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

public class PanelMenuLlamadas extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private JRadioButton registraLlamada;
    private JRadioButton muestraLlamadas;
    private JButton atras;
    private JButton sig;
    private ImplementacionVista vista;
    

    PanelMenuLlamadas(ImplementacionVista vista) {
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
        menuSeleccion.setLayout(new GridLayout(3,1));
        JLabel texto = new JLabel("Choose an option for phone calls:");
        menuSeleccion.add(texto);
        menuSeleccion.add(registraLlamada = new JRadioButton("Register a phone call"));
        menuSeleccion.add(muestraLlamadas = new JRadioButton("To list a client phone calls"));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(registraLlamada);
        buttonGroup.add(muestraLlamadas);
        add(menuSeleccion);
        atras = new JButton("Back");
	sig = new JButton("Next");
	sig.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (!getMenuSeleccionado().equals("Ninguno")){
		setVisible(false);
		vista.muestraPanel(getMenuSeleccionado());
	    }}
	    

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
        if(registraLlamada.isSelected())
            return "RegistraLlamada";
        else if(muestraLlamadas.isSelected())
            return "MuestraLlamadas";
        else return "Ninguno";
    }
}