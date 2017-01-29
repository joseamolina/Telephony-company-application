package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelMenuFacturas extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private JRadioButton lanzaFacturas;
    private JRadioButton infoFacturas;
    private JRadioButton infoTodasFacturas;
    private JButton sig;
    private JButton atras;
    private ImplementacionVista vista;
    

    PanelMenuFacturas(ImplementacionVista vista) {
	
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
        JLabel texto = new JLabel("Choose an option for invoices:");
        menuSeleccion.add(texto);
        menuSeleccion.add(lanzaFacturas = new JRadioButton("Launch an invoice"));
        menuSeleccion.add(infoFacturas = new JRadioButton("Recover invoice information"));
        menuSeleccion.add(infoTodasFacturas = new JRadioButton("Recover information of all invoices"));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(lanzaFacturas);
        buttonGroup.add(infoFacturas);
        buttonGroup.add(infoTodasFacturas);
        add(menuSeleccion);
        atras = new JButton("Back");
	sig = new JButton("Next");
	sig.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (!getMenuSeleccionado().equals("Ninguno")){
		setVisible(false);
		vista.muestraPanel(getMenuSeleccionado());
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
        if(lanzaFacturas.isSelected())
            return "LanzarFactura";
        else if(infoFacturas.isSelected())
            return "infoFactura";
        else if(infoTodasFacturas.isSelected())
            return "infoTodasFacturas";
        else return "Ninguno";
    }
}
