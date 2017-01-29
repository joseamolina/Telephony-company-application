package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelLanzaFactura extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private JButton atras;
    private JButton fac;
    private JTextField eligeDNI;
    private JTextArea contestacion;
    private ImplementacionVista vista;
    
    PanelLanzaFactura(ImplementacionVista vista) {
        super();
        this.vista=vista;
        creaGUI();
    }

    private void creaGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        creaPanel();
    }


    private void creaPanel() {
	JPanel seleccionTipo = new JPanel();
        seleccionTipo.setLayout(new GridLayout(13,1));
        JLabel inicio = new JLabel("You are going to launch an invoice:");
        JLabel dni = new JLabel("Introduce the ID:");
        eligeDNI = new JTextField();
        contestacion = new JTextArea();
        seleccionTipo.add(inicio);
        seleccionTipo.add(dni);
        seleccionTipo.add(eligeDNI);
        seleccionTipo.add(contestacion);
        add(seleccionTipo);
        JPanel gh = new JPanel();
        atras = new JButton("Atras");
        fac = new JButton("Facturar");
        fac.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		vista.emiteFac();
	    }
	});

	atras.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
		vista.muestraPanel("MenuFacturas");
	    }
	});
        gh.add(atras);
        gh.add(fac);
        add(gh);
	
    }

    public String getDNI() {
	return eligeDNI.getText();
    }

    public void setText(String text) {
	contestacion.setText(text);
	vista.getVentana().pack();
    }

    public void warning(String text) {
	JOptionPane.showMessageDialog(null, text);
    }

}
