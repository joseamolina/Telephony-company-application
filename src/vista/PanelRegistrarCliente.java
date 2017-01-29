package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

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


public class PanelRegistrarCliente extends JPanel implements Serializable{
    private static final long serialVersionUID = 1L;
    private JTextField jtfNombre;
    private JTextField jtfApellidos;
    private JTextField jtfNIF;
    private JRadioButton jrbParticular;
    private JRadioButton jrbEmpresa;
    private JRadioButton aleatorioSig;
    private JTextField jtfCP;
    private JTextField jtfPoblacion;
    private JTextField jtfProv;
    private JTextField jtfEmail;
    private JTextField jtfTari;
    private JRadioButton aMano;
    private JTextArea area;
    private JButton atras;
    private JButton reg;
    private ImplementacionVista vista;

    PanelRegistrarCliente(ImplementacionVista vista) {
        super();
        this.vista=vista;
        creaGUI();
    }

    private void creaGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        creaPanelDatosCliente();
        creaPanelSeleccionTipo();
        creaPanelAleatorio();
        crearContestacion();
    }

    private void crearContestacion() {
	area = new JTextArea();
	area.setEditable(false);
	JScrollPane sP = new JScrollPane(area);
	add(sP);
	atras = new JButton("Back");
	reg = new JButton("Register");
	reg.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (getAleatorio().equals("A"))
		    vista.generator();
		else{ if (PanelRegistrarCliente.this.getTipoSeleccionado().equals("Particular"))
		    vista.altaParticular();
		else if (PanelRegistrarCliente.this.getTipoSeleccionado().equals("Empresa"))
		    vista.altaEmpresa();
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
	JPanel gh = new JPanel();
	gh.add(reg);
	gh.add(atras);
	add(gh);
    }

    private void creaPanelSeleccionTipo() {
        JPanel seleccionTipo = new JPanel();
        seleccionTipo.add(jrbParticular = new JRadioButton("Owner"));
        jrbParticular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfApellidos.setEnabled(true);
            }
        });
        seleccionTipo.add(jrbEmpresa = new JRadioButton("Enterprice"));
        jrbEmpresa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfApellidos.setEnabled(false);
            }
        });
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jrbParticular);
        buttonGroup.add(jrbEmpresa);
        add(seleccionTipo);
    }
    
    private void creaPanelAleatorio(){
	JPanel aleatorio = new JPanel();
	aleatorio.add(aleatorioSig = new JRadioButton("Aleatory register"));
	aleatorioSig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfApellidos.setEnabled(false);
                jtfCP.setEnabled(false);
                jtfEmail.setEnabled(false);
                jtfNIF.setEnabled(false);
                jtfNombre.setEnabled(false);
                jtfPoblacion.setEnabled(false);
                jtfProv.setEnabled(false);
                jtfTari.setEnabled(false);
                jrbParticular.setEnabled(false);
                jrbEmpresa.setEnabled(false);
            }
        });
	aleatorio.add(aMano = new JRadioButton("Introduce by your own"));
	aMano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtfApellidos.setEnabled(true);
                jtfCP.setEnabled(true);
                jtfEmail.setEnabled(true);
                jtfNIF.setEnabled(true);
                jtfNombre.setEnabled(true);
                jtfPoblacion.setEnabled(true);
                jtfProv.setEnabled(true);
                jtfTari.setEnabled(true);
                jrbParticular.setEnabled(true);
                jrbEmpresa.setEnabled(true);
            }
        });
	ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(aleatorioSig);
        buttonGroup.add(aMano);
	add(aleatorio);
    }

    private void creaPanelDatosCliente() {
        JPanel datosClientes = new JPanel();
        datosClientes.setLayout(new GridLayout(17,1));
        JLabel texto = new JLabel("Register a new client:");
        datosClientes.add(texto);
        datosClientes.add(new JLabel("Name:"));
        datosClientes.add(jtfNombre = new JTextField(10));
        datosClientes.add(new JLabel("Surname:"));
        datosClientes.add(jtfApellidos = new JTextField(15));
        datosClientes.add(new JLabel("ID:"));
        datosClientes.add(jtfNIF = new JTextField(10));
        datosClientes.add(new JLabel("PC:"));
        datosClientes.add(jtfCP = new JTextField(5));
        datosClientes.add(new JLabel("Town:"));
        datosClientes.add(jtfPoblacion = new JTextField(10));
        datosClientes.add(new JLabel("Province:"));
        datosClientes.add(jtfProv = new JTextField(10));
        datosClientes.add(new JLabel("email:"));
        datosClientes.add(jtfEmail = new JTextField(25));
        datosClientes.add(new JLabel("numeric rate €/min:"));
        datosClientes.add(jtfTari = new JTextField(4));
        add(datosClientes);
    }
    
    public void setContestacion(String text){
	area.setText(text);
	vista.getVentana().pack();
    }

    String getTipoSeleccionado() {
        if(jrbParticular.isSelected())
            return "Particular";
        else if(jrbEmpresa.isSelected())
            return "Empresa";
        else return "Ninguno";
    }
    
    public String getAleatorio(){
	if(aleatorioSig.isSelected())
	    return "A";
	return "B";
    }

    public String getNombre() {
	String fase = jtfNombre.getText();
	jtfNombre.setText("");
        return fase;
    }

    public String getApellidos() {
	String fase = jtfApellidos.getText();
	jtfApellidos.setText("");
        return fase;
    }

    public String getNIF() {
	String fase = jtfNIF.getText();
	jtfNIF.setText("");
        return fase;
    }
    
    public int getCP(){
	int fase = Integer.parseInt(jtfCP.getText());
	jtfCP.setText("");
	return fase;
    }
    
    public void warning(String text){
	JOptionPane.showMessageDialog(null, text);
    }
    
    public String getPoblacion(){
	String fase = jtfPoblacion.getText();
	jtfPoblacion.setText("");
	return fase;
    }
    
    public String getProvincia(){
	String fase = jtfProv.getText();
	jtfProv.setText("");
	return fase;
    }
    
    public String getEmail(){
	String fase = jtfNIF.getText();
	jtfEmail.setText("");
	return fase;
    }
    
    public Double getTarifa(){
	Double dev = Double.parseDouble(jtfTari.getText());
	jtfTari.setText("");
	
	return dev;
    }
   
}