package vista;

import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelRecuperaFactura extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private JButton atras;
    private JButton sig;
    private JTextArea muestra;
    private JTextField pideFac;
    private ImplementacionVista vista;
    

    PanelRecuperaFactura(ImplementacionVista vista) {
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
        menuSeleccion.setLayout(new GridLayout(4,1));
        JLabel texto = new JLabel("Show an invoice:");
        menuSeleccion.add(texto);
        JPanel seleccion = new JPanel();
        seleccion.setLayout(new GridLayout(7,2));
        JLabel fac = new JLabel("Introduce the Invoice Code:");
        pideFac = new JTextField();
        seleccion.add(fac);
        seleccion.add(pideFac);
        add(menuSeleccion);
        atras = new JButton("Back");
	sig = new JButton("Show");
	muestra = new JTextArea();
	JScrollPane muestraPanel = new JScrollPane(muestra);
	JPanel gh = new JPanel();
	gh.add(sig);
	gh.add(atras);
	gh.add(muestraPanel);
	add(seleccion);
	add(gh);
    }

    public void setText(String text) {
	muestra.setText(text);
	vista.getVentana().pack();
	
    }

    public void warning(String text) {
	JOptionPane.showMessageDialog(null, text);
	
    }
    
    public int getFac() {
	return Integer.parseInt(pideFac.getText());
    }

  

}
