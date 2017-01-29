package vista;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controlador.Controlador;

public class ImplementacionVista implements Vista,Serializable {
    
    private static final long serialVersionUID = 1L;
    private Controlador controlador;
    private PanelRegistrarCliente rC;
    private PanelInicial pI;
    private PanelMenuClientes pC;
    private PanelMenuFacturas pF;
    private PanelMenuLlamadas pL;
    private PanelMenuGenerico pG;
    private PanelBorrarCliente rBC;
    private PanelRegistraLlamada pRL;
    private PanelNuevosItemsTarifa pNI;
    private PanelLanzaFactura pLF;
    private PanelCambiaTarifa pCT;
    private PanelMuestraTodo pMT;
    private JFrame ventana;
    private Container contenedor;

    public ImplementacionVista() {
	rC = new PanelRegistrarCliente(this);
	pI = new PanelInicial(this);
	pC = new PanelMenuClientes(this);
	pF = new PanelMenuFacturas(this);
	pL = new PanelMenuLlamadas(this);
	pG = new PanelMenuGenerico(this);
	rBC = new PanelBorrarCliente(this);
	pRL = new PanelRegistraLlamada(this);
	pNI = new PanelNuevosItemsTarifa(this);
	pLF = new PanelLanzaFactura(this);
	pCT = new PanelCambiaTarifa(this);
	pMT = new PanelMuestraTodo(this);
    }
    
    public void setControlador(Controlador controlador){
	this.controlador = controlador;
    }
    
    public void initGUI(){
	SwingUtilities.invokeLater(new Runnable() {
	    
	    @Override
	    public void run() {
		GUI();
		
	    }
	});
    }
    
    public void GUI() {
	controlador.entrada();
	ventana = new JFrame("Telephony data base");
	
	ventana.addWindowListener(new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
		controlador.salida();
	    }

	    @Override
	    public void windowOpened(WindowEvent e) {
		controlador.entrada();
	    }
	    
	});
	ventana.setResizable(false);
	contenedor = ventana.getContentPane();
	ventana.add(pI);
	ventana.setVisible(true);
	ventana.pack();
    }
    
    public JFrame getVentana(){
	return ventana;
    }
    
    public void muestraPanel(String texto){
	if (texto.equals("PanelInicial")){
	    pI.setVisible(true);
	    contenedor.add(pI);
	    ventana.pack();
	}else if (texto.equals("LanzaFactura")){
	    pLF.setVisible(true);
	    contenedor.add(pLF);
	    ventana.pack();
	}else if (texto.equals("infoFactura"))  { 
	    pMT.setLabel("Introduce the invoice code:");
	    pMT.setPresentacion("You are going to show an invoice");
	    pMT.setVisible(true);
   	    pMT.setTipo("MuestraFactura");
   	    contenedor.add(pMT);
 	    ventana.pack();
	}else if (texto.equals("infoTodasFacturas"))   {
	    pMT.setLabel("Introduce the invoice:");
	    pMT.setPresentacion("You are going to show all the invoice of the client");
	    pMT.setVisible(true);
   	    pMT.setTipo("MuestraFacturas");
   	    contenedor.add(pMT);
 	    ventana.pack();
	}else if (texto.equals("RegistraCliente")){
	    rC.setVisible(true);
	    contenedor.add(rC);
	    ventana.pack();
	}else if (texto.equals("BorraCliente")){
	    rBC.setVisible(true);
	    contenedor.add(rBC);
	    ventana.pack();
	}else if (texto.equals("CambiaTarifa")){
    	    pCT.setVisible(true);
    	    contenedor.add(pCT);
    	    ventana.pack();
	}else if (texto.equals("DatosCliente")){
	    pMT.setLabel("Introduce the ID:");
	    pMT.setPresentacion("You are going to show a client:");
   	    pMT.setVisible(true);
   	    pMT.setTipo("MuestraCliente");
   	    contenedor.add(pMT);
 	    ventana.pack();
	}else if (texto.equals("CambiaDatosTarifa")){
	    pNI.setVisible(true);
    	    contenedor.add(pNI);
    	    ventana.pack();
    	}else if (texto.equals("MenuClientes")){
    	    pC.setVisible(true);
    	    contenedor.add(pC);
    	    ventana.pack();
    	}else if (texto.equals("MenuFacturas")){
    	    pF.setVisible(true);
    	    contenedor.add(pF);
    	    ventana.pack();
    	}else if (texto.equals("MenuLlamadas")){
    	    pL.setVisible(true);
    	    contenedor.add(pL);
    	    ventana.pack();
    	}else if (texto.equals("MenuGenerico")){
    	    pG.setVisible(true);
    	    contenedor.add(pG);
    	    ventana.pack();
    	}else if (texto.equals("RegistraLlamada")){
	    pRL.setVisible(true);
	    contenedor.add(pRL);
	    ventana.pack();
    	}else if (texto.equals("MuestraLlamadas")){
    	    pMT.setLabel("Introduce the ID:");
	    pMT.setPresentacion("You are going to show the calls of a client:");
    	    pMT.setVisible(true);
	    pMT.setTipo("MuestraLlamadas");
	    contenedor.add(pMT);
	    ventana.pack();
    	}
    }

    public void añadeExtras() {
	controlador.añadeExtras(pNI);
	
    }

    public void altaLlamada() {
	controlador.altaLlamada(pRL);
	
    }

    public void recuperaDatFac() {
	controlador.recuperaDatFac(pMT);
	
    }

    public void recuperaFacs() {
	controlador.recuperaFacs(pMT);
	
    }

    public void recuperaCliente1() {
	controlador.recuperaCliente1(pMT);
	
    }

    public void listaLlamadas() {
	controlador.listaLlamadas(pMT);
	
    }

    public void clis() {
	controlador.clientesGenerico(pG);
	
    }

    public void llams() {
	controlador.llamadasGenerico(pG);
	
    }

    public void fas() {
	controlador.facturasGenerico(pG);
	
    }

    public void recuperaListado1() {
	controlador.recuperaListado1(pC);
	
    }

    public void emiteFac() {
	controlador.emiteFac(pLF);
	
    }

    public void cambiaTarifa1() {
	controlador.cambiaTarifa1(pCT);
	
    }

    public void borra() {
	controlador.borra(rBC);
	
    }

    public void generator() {
	controlador.generator(rC);
	
    }

    public void altaParticular() {
	controlador.altaParticular(rC);
	
    }

    public void altaEmpresa() {
	controlador.altaEmpresa(rC);
	
    }

    public void salida() {
	controlador.salida();
	
    }
    
    public void setWarningRegistraCliente(){
	rC.warning("Ese cliente ya existe.");
    }
    
    public void setContestacion(String contestacion){
	rC.setContestacion(contestacion);
    }
}