package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class PanelNuevosItemsTarifa extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTextField dni;
    private JCheckBox tardes;
    private JCheckBox dom;
    private JLabel eligeDNI;
    private JButton cambiar;
    private JButton atras;
    private JTextArea areaNueva;
    private ImplementacionVista vista;

    public PanelNuevosItemsTarifa(ImplementacionVista vista) {
        super();
        this.vista=vista;
        creaGUI();
    }
    
    private void creaGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        creaPanelAñade();
    }
    
    private void creaPanelAñade() {
        JPanel menuSeleccion = new JPanel();
        JLabel texto = new JLabel("Add a new items for your phone rate:");
        JPanel selec = new JPanel();
        selec.setLayout(new GridLayout(6,1));
        eligeDNI = new JLabel("Introduce the ID:");
        dni = new JTextField();
        JPanel botones = new JPanel();
        botones.setLayout(new GridLayout(2,1));
        tardes = new JCheckBox("Half-price afternoons(16-20pm)");
        dom = new JCheckBox("Half-price sundays");
        botones.add(tardes);
        botones.add(dom);
        selec.add(texto);
        selec.add(eligeDNI);
        selec.add(dni);
        selec.add(botones);
        areaNueva = new JTextArea();
        JScrollPane rueda= new JScrollPane(areaNueva);
        
        selec.add(rueda);
        areaNueva.setSize(400, 600);
        menuSeleccion.add(selec);
        atras = new JButton("Back");
	cambiar = new JButton("Add");
	cambiar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		vista.añadeExtras();
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
	gh.add(cambiar);
	gh.add(atras);
	menuSeleccion.add(gh);
        add(menuSeleccion);
    }
    
    public String getMenuSeleccionado() {
        if(tardes.isSelected() && !dom.isSelected())
            return "tardes";
        if(dom.isSelected() && !tardes.isSelected())
            return "domingos";
        if (dom.isSelected() && tardes.isSelected())
            return "todo";
        return null;
    }

    public String getDNI() {
	return dni.getText();
    }

    public void warning(String text) {
	JOptionPane.showMessageDialog(null, text);
	
    }
    
}
