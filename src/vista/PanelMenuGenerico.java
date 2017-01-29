package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.wannawork.jcalendar.JCalendarComboBox;

public class PanelMenuGenerico extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private JRadioButton cliGen;
    private JRadioButton llamGen;
    private JRadioButton facGen;
    private JButton atras;
    private JButton sig;
    private JTextField pideDNI;
    private JTextArea muestra;
    private JCalendarComboBox fecha1;
    private JCalendarComboBox fecha2;
    private ImplementacionVista vista;
    

    PanelMenuGenerico(ImplementacionVista vista) {
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
        JLabel texto = new JLabel("Select a generic option:");
        menuSeleccion.add(texto);
        menuSeleccion.add(cliGen = new JRadioButton("To show a couple of clients registered between 2 different dates:"));
        menuSeleccion.add(llamGen = new JRadioButton("To show a couple of phone calls, of a client, effected between 2 different dates:"));
        menuSeleccion.add(facGen = new JRadioButton("To show a couple of invoices, of a client, launched between 2 different dates:"));
        cliGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pideDNI.setEnabled(false);
            }
        });
        llamGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pideDNI.setEnabled(true);
            }
        });
        facGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pideDNI.setEnabled(true);
            }
        });
        JPanel seleccion = new JPanel();
        seleccion.setLayout(new GridLayout(7,2));
        JLabel dni = new JLabel("Introduce the ID:");
        pideDNI = new JTextField();
        JLabel introF1 = new JLabel("Introduce the first date:");
        fecha1 = new JCalendarComboBox();
        JLabel introF2 = new JLabel("Introduce the last date:");
        fecha2 = new JCalendarComboBox();
        seleccion.add(dni);
        seleccion.add(pideDNI);
        seleccion.add(introF1);
        seleccion.add(fecha1);
        seleccion.add(introF2);
        seleccion.add(fecha2);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(cliGen);
        buttonGroup.add(llamGen);
        buttonGroup.add(facGen);
        add(menuSeleccion);
        atras = new JButton("Back");
	sig = new JButton("Next");
	sig.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (!getMenuSeleccionado().equals("Ninguno")){
		    if (getMenuSeleccionado().equals("Clients")){
			vista.clis();
		    }else if(getMenuSeleccionado().equals("Llams")){
			vista.llams();
		    }else if(getMenuSeleccionado().equals("Facs")){
			vista.fas();
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
	muestra = new JTextArea();
	JScrollPane muestraPanel = new JScrollPane(muestra);
	JPanel gh = new JPanel();
	gh.add(sig);
	gh.add(atras);
	gh.add(muestraPanel);
	add(seleccion);
	add(gh);
	
        
    }
    
    String getMenuSeleccionado() {
        if(cliGen.isSelected())
            return "Clients";
        else if(llamGen.isSelected())
            return "Llams";
        else if(facGen.isSelected())
            return "Facs";
        else return "Ninguno";
    }

    public Date getFecha1() {
	return fecha1.getDate();
    }

    public Date getFecha2() {
	return fecha2.getDate();
    }

    public void setText(String text) {
	muestra.setText(text);
	vista.getVentana().pack();
	
    }

    public void warning(String text) {
	JOptionPane.showMessageDialog(null, text);
	
    }

    public String getDNI() {
	return pideDNI.getText();
    }
}