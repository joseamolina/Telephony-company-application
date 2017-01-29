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
import javax.swing.JTextField;


public class PanelBorrarCliente extends JPanel implements Serializable {

    private static final long serialVersionUID = 1L;
    private JTextField dniIntro;
    private JLabel area;
    private JButton atras;
    private JButton bor;
    private ImplementacionVista vista;
    

    PanelBorrarCliente(ImplementacionVista vista) {
        super();
        this.vista=vista;
        creaGUI();
    }

    private void creaGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        creaPanelDatosCliente();
    }

    private void creaPanelDatosCliente() {
        JPanel datosClientes = new JPanel();
        datosClientes.setLayout(new GridLayout(4,1));
        JLabel texto = new JLabel("You are going to delete a client:");
        datosClientes.add(texto);
        datosClientes.add(new JLabel("ID:"));
        datosClientes.add(dniIntro = new JTextField(10));
        datosClientes.add(area = new JLabel());
        add(datosClientes);
        atras = new JButton("Back");
	bor = new JButton("Delete");
	bor.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		vista.borra();
	    }
	});

	atras.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
		vista.muestraPanel("MenuClientes");
	    }
	});
	JPanel gh = new JPanel();
	gh.add(bor);
	gh.add(atras);
	add(gh);
    }
    
    public void setContestacion(String text){
	area.setText(text);
	vista.getVentana().pack();
    }

    public String getNIF() {
        return dniIntro.getText();
    }

    public void warning(String text) {
	JOptionPane.showMessageDialog(null, text);
	
    }
    
}
