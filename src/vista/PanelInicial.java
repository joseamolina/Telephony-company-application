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


public class PanelInicial extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private JRadioButton menuClientes;
    private JRadioButton menuFacturas;
    private JRadioButton menuLlamadas;
    private JRadioButton menuGenerico;
    private JButton salir;
    private JButton sig;
    private ImplementacionVista vista;

    PanelInicial(ImplementacionVista vista) {
	
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
        menuSeleccion.setLayout(new GridLayout(5,1));
        JLabel texto = new JLabel("Choose an option");
        menuSeleccion.add(texto);
        menuSeleccion.add(menuClientes = new JRadioButton("Clients menu"));
        menuSeleccion.add(menuFacturas = new JRadioButton("Invoices menu"));
        menuSeleccion.add(menuLlamadas = new JRadioButton("Phone calls menu"));
        menuSeleccion.add(menuGenerico = new JRadioButton("generic menu"));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(menuClientes);
        buttonGroup.add(menuFacturas);
        buttonGroup.add(menuLlamadas);
        buttonGroup.add(menuGenerico);
        add(menuSeleccion);
        sig = new JButton("Next");
	salir = new JButton("Exit");
	sig.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (!getMenuSeleccionado().equals("Ninguno")){
        		setVisible(false);
        		vista.muestraPanel(getMenuSeleccionado());
		}
	    }
	});
	salir.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		vista.salida();
		vista.getVentana().setVisible(false);
	    }
	});
	
	JPanel gh = new JPanel();
	gh.add(sig);
	gh.add(salir);
	add(gh);
    }
    
    String getMenuSeleccionado() {
        if(menuClientes.isSelected())
            return "MenuClientes";
        else if(menuFacturas.isSelected())
            return "MenuFacturas";
        else if(menuLlamadas.isSelected())
            return "MenuLlamadas";
        else if(menuGenerico.isSelected())
            return "MenuGenerico";
        else return "Ninguno";
    }

}
