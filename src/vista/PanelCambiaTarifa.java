package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelCambiaTarifa extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTextField jtfNIF;
    private JTextField jtfTari;
    private JButton atras;
    private JButton cambia;
    private JLabel textArea;
    private ImplementacionVista vista;
    
    
    

    PanelCambiaTarifa(ImplementacionVista vista) {
        super();
        this.vista=vista;
        creaGUI();
    }

    private void creaGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        creaPanelDatosTarifa();
        CreaLabelContestacion();
    }
    
  

    private void CreaLabelContestacion() {
	JPanel panelLabel = new JPanel();
	JLabel label = new JLabel();
	panelLabel.add(label);
	add(panelLabel);
    }

    private void creaPanelDatosTarifa() {
        JPanel datosClientes = new JPanel();
        JLabel texto = new JLabel("Change the numeric rate:");
        datosClientes.add(texto);
        datosClientes.add(new JLabel("ID:"));
        datosClientes.add(jtfNIF = new JTextField(10));
        datosClientes.add(new JLabel("New numeric rate €/min:"));
        datosClientes.add(jtfTari = new JTextField(4));
        textArea = new JLabel();
        datosClientes.add(textArea);
        JPanel botones = new JPanel();
        atras = new JButton("Back");
        cambia = new JButton("Change");
        cambia.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		vista.cambiaTarifa1();
	    }
	});

	atras.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
		vista.muestraPanel("MenuClientes");
	    }
	});
        botones.add(atras);
        botones.add(cambia);
        datosClientes.add(botones);
        add(datosClientes);
    }


    public String getNIF() {
	String dato = jtfNIF.getText();
	jtfNIF.setText("");
        return dato;
    }
    
    public Double getTarifa(){
	Double dato = Double.parseDouble(jtfTari.getText());
	jtfTari.setText("");
	return dato;
    }
    
    public void warning(String text) {
	JOptionPane.showMessageDialog(null, text);
    }

    public void setText(String text) {
	textArea.setText(text);
	vista.getVentana().pack();
    }
    
}
