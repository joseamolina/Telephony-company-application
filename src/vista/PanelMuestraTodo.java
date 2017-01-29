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


public class PanelMuestraTodo extends JPanel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private JButton dale;
    private JButton atras;
    private JLabel pone;
    private JTextField introduce;
    private JTextArea contesta;
    private JLabel etq;
    String tipo;
    private ImplementacionVista vista;
    
    PanelMuestraTodo(ImplementacionVista vista) {
        super();
        this.vista=vista;
        creaGUI();
        
    }

    private void creaGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        creaPanel();
    }

    private void creaPanel() {
	JPanel seleccion = new JPanel();
	seleccion.setLayout(new GridLayout(3,1));
	seleccion.add(etq = new JLabel());
	seleccion.add(pone = new JLabel());
	seleccion.add(introduce = new JTextField());
	JPanel seleccion2 = new JPanel();
	seleccion2.add(contesta = new JTextArea());
	dale = new JButton("Mostrar");
	atras = new JButton("Atrás");
	dale.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (tipo.equals("MuestraFactura")){
		    vista.recuperaDatFac();
		}else if (tipo.equals("MuestraFacturas")){
		    vista.recuperaFacs();
		}else if (tipo.equals("MuestraCliente")){
		    vista.recuperaCliente1();
		}else if (tipo.equals("MuestraLlamadas")){
		    vista.listaLlamadas();
		}
	    }
	});

	atras.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
		vista.muestraPanel("MenuClientes");
	    }
	});
	JPanel seleccion3 = new JPanel();
	seleccion3.add(dale);
	seleccion3.add(atras);
	add(seleccion);
	add(seleccion2);
	add(seleccion3);
    }

    public String getDato() {
	String dato = introduce.getText();
	introduce.setText("");
	return dato;
    }

    public void warning(String text) {
	JOptionPane.showMessageDialog(null, text);
	
    }

    public void setText(String text) {
	contesta.setText(text);
	vista.getVentana().pack();
	
    }
    
    void setLabel(String text){
	pone.setText(text);
    }
    
    void setPresentacion(String text){
	etq.setText(text);
    }
    
    void setTipo(String tipo){
	this.tipo=tipo;
    }
    

}
