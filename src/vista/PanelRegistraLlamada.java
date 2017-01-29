package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.wannawork.jcalendar.JCalendarComboBox;
import lu.tudor.santec.jtimechooser.JTimeChooser;

public class PanelRegistraLlamada extends JPanel implements Serializable{
    private static final long serialVersionUID = 1L;
    private JButton atras;
    private JButton llam;
    private JTextField eligeDNI;
    private JTextField eligeTelf;
    private JCalendarComboBox eligeFecha;
    private JTimeChooser eligeHora;
    private JTextField eligeDur;
    private JLabel contesta;
    private ImplementacionVista vista;
   
    PanelRegistraLlamada(ImplementacionVista vista) {
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
        JLabel inicio = new JLabel("You are going to register a phone call");
        JLabel dni = new JLabel("Introduce the ID:");
        eligeDNI = new JTextField();
        JLabel telf = new JLabel("Introduce the telephone number:");
        eligeTelf = new JTextField();
        JLabel fecha = new JLabel("Introduce the date:");
        eligeFecha = new JCalendarComboBox();
        JLabel hora = new JLabel("Introduce the time call, drag for choosing:");
        eligeHora = new JTimeChooser();
        JLabel dur = new JLabel("Introduce the duration:");
        eligeDur = new JTextField();
        seleccionTipo.add(inicio);
        seleccionTipo.add(dni);
        seleccionTipo.add(eligeDNI);
        seleccionTipo.add(telf);
        seleccionTipo.add(eligeTelf);
        seleccionTipo.add(fecha);
        seleccionTipo.add(eligeFecha);
        seleccionTipo.add(hora);
        seleccionTipo.add(eligeHora);
        seleccionTipo.add(dur);
        seleccionTipo.add(eligeDur);
        seleccionTipo.add(contesta = new JLabel());
        add(seleccionTipo);
        atras = new JButton("Back");
	llam = new JButton("Register");
	llam.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		vista.altaLlamada();
	    }
	    

	});

	atras.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setVisible(false);
		vista.muestraPanel("MenuLlamadas");
	    }
	});
	JPanel gh = new JPanel();
	gh.add(llam);
	gh.add(atras);
	add(gh);
    }
    
    public String getDNI(){
	String toma = eligeDNI.getText();
	eligeDNI.setText("");
	return toma;
    }
    
    public int getTelf(){
	int toma = Integer.parseInt(eligeTelf.getText());
	eligeTelf.setText("");
	return toma;
    }
    
    public Date getFecha(){
	return eligeFecha.getDate();
    }
    
    @SuppressWarnings("deprecation")
    public Time getHora(){
	return new Time(eligeHora.getHours(),eligeHora.getMinutes(),eligeHora.getSeconds());
    }
    
    public int getDur(){
	int toma = Integer.parseInt(eligeDur.getText());
	eligeDur.setText("");
	return toma;
	
    }

    public void setText(String text) {
	contesta.setText(text);
	vista.getVentana().pack();
	
    }

    public void warning(String text) {
	JOptionPane.showMessageDialog(null, text);
	
    }
    
}