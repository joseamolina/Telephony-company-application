package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

import vista.PanelBorrarCliente;
import vista.PanelCambiaTarifa;
import vista.PanelLanzaFactura;
import vista.PanelMenuClientes;
import vista.PanelMenuGenerico;
import vista.PanelMuestraTodo;
import vista.PanelNuevosItemsTarifa;
import vista.PanelRegistraLlamada;
import vista.PanelRegistrarCliente;
import vista.Vista;
import es.uji.www.GeneradorDatosINE;
import excepcion.ClienteNoExiste;
import excepcion.FacturaNoExiste;
import excepcion.FechaIncorrecta;
import factoriaTipos.CreadorClienteFac;
import factoriaTipos.CreadorClientes;
import factoriaTipos.CreadorTarifaFac;
import factoriaTipos.CreadorTarifas;
import modelo.CliLlamFac;
import modelo.Direc;
import modelo.Factura;
import modelo.ImplementacionModelo;
import modelo.Llamada;
import modelo.tiposDatos.Clientes.ClientsEmpresa;
import modelo.tiposDatos.TarifaDecorator.Tarifa;
import modelo.tiposDatos.TarifaDecorator.TarifaBase;

public final class ImplementacionControlador implements Controlador,
	Serializable {
    private static final long serialVersionUID = 1L;
    ImplementacionModelo<CliLlamFac> modelo;
    GeneradorDatosINE gD;
    Random rD;
    CreadorClienteFac cC;
    CreadorTarifaFac cT;
    Vista v;

    public ImplementacionControlador() {
	gD = new GeneradorDatosINE();
	rD = new Random();
	cC = new CreadorClientes();
	cT = new CreadorTarifas();
	entrada();
    }

    public void setModelo(ImplementacionModelo<CliLlamFac> modelo) {
	this.modelo = modelo;

    }

    public void setVista(Vista vista) {
	this.v = vista;

    }

    
    public void entrada() {
	ObjectInputStream ois = null;
	try {
	    try {
		FileInputStream fis = new FileInputStream("st.bin");
		ois = new ObjectInputStream(fis);
		modelo = ((ImplementacionModelo<CliLlamFac>) ois.readObject());
	    } finally {
		if (ois != null)
		    ois.close();
	    }
	} catch (FileNotFoundException e) {
	    System.out.println("sdf");

	} catch (IOException e) {
	    e.printStackTrace();

	} catch (ClassNotFoundException e) {

	    System.out.println("kolk");
	}
    }

    public void salida() {
	ObjectOutputStream oos = null;
	try {
	    try {
		FileOutputStream fos = new FileOutputStream("st.bin");
		oos = new ObjectOutputStream(fos);
		oos.writeObject(modelo);
	    } finally {
		oos.close();
	    }
	} catch (FileNotFoundException exc) {

	    System.out.println("nc");
	} catch (IOException exc) {
	    exc.printStackTrace();

	}
    }

    public void borra(PanelBorrarCliente pBC) {
	String dni = pBC.getNIF();
	try {
	    modelo.borrarCliente(dni);
	    pBC.setContestacion("Client was deleted properly.");
	} catch (ClienteNoExiste e) {
	    pBC.warning("Client doesn't exist");
	}
    }

    public void altaEmpresa(PanelRegistrarCliente pRC) {
	ClientsEmpresa c = null;
	String nom = pRC.getNombre();
	String dni = pRC.getNIF();
	int cp = pRC.getCP();
	String pob = pRC.getPoblacion();
	String prov = pRC.getProvincia();
	Double tar = pRC.getTarifa();
	Tarifa tarifa = cT.factoryMethodTarifaBase(tar);
	String email = pRC.getEmail();
	Date fecha = new Date();
	Direc direccion = new Direc(cp, prov, pob);
	c = cC.factoryMethodEmpresa(nom, dni, direccion, email, fecha, tarifa);
	modelo.darDeAlta(c);
    }

    public void altaParticular(PanelRegistrarCliente pRC) {
	ClientsEmpresa c = null;
	String nom = pRC.getNombre();
	String dni = pRC.getNIF();
	int cp = pRC.getCP();
	String pob = pRC.getPoblacion();
	String prov = pRC.getProvincia();
	Double tar = pRC.getTarifa();
	Tarifa tarifa = cT.factoryMethodTarifaBase(tar);
	String email = pRC.getEmail();
	Date fecha = new Date();
	Direc direccion = new Direc(cp, prov, pob);
	String apell = pRC.getApellidos();
	c = cC.factoryMethodParticular(nom, dni, direccion, email, fecha,
		tarifa, apell);
	modelo.darDeAlta(c);
    }

    public void generator(PanelRegistrarCliente pRC) {
	String nombre = gD.getNombre();
	String dni = gD.getNIF();
	int cp = rD.nextInt(99999 - 1) + 1;
	String provincia = gD.getProvincia();
	String poblacion = gD.getPoblacion(provincia);
	Direc dirg = new Direc(cp, provincia, poblacion);
	Date fech = new Date();
	String email = "*Sin email*";
	Tarifa tar = new TarifaBase(rD.nextFloat());
	boolean b = rD.nextBoolean();
	ClientsEmpresa c = null;
	if (b) {
	    String ap = gD.getApellido();
	    c = cC.factoryMethodParticular(nombre, dni, dirg, email, fech, tar,
		    ap);
	} else {
	    c = cC.factoryMethodEmpresa(nombre, dni, dirg, email, fech, tar);
	}
	modelo.darDeAlta(c);
	pRC.setContestacion(c.toString());
    }

    public void cambiaTarifa1(PanelCambiaTarifa pCT) {
	String dni = pCT.getNIF();
	Double tarifa = pCT.getTarifa();
	try {
	    ClientsEmpresa j = modelo.recuperaDatos(dni);
	    Tarifa tar = j.getTarifa();
	    tar.setTar(tarifa);
	    modelo.cambiarTarifa(tar, dni);
	    pCT.setText("Numeric rate was changed properly");
	} catch (ClienteNoExiste e) {
	    pCT.warning("That client doesn't exist");
	}
    }

    public void recuperaCliente1(PanelMuestraTodo pMT) {
	try {
	    String dni = pMT.getDato();
	    ClientsEmpresa j = null;
	    j = modelo.recuperaDatos(dni);
	    pMT.setText(j.toString());
	} catch (ClienteNoExiste e) {
	    pMT.warning("That client doesn't exist.");
	}
    }

    public void recuperaListado1(PanelMenuClientes pMC) {
	Collection<CliLlamFac> cl = modelo.recuperarClientes();
	if (cl.isEmpty()) {
	    pMC.setText("There aren't clients");
	} else {
	    String text = "";
	    text += "Here you have the clients:\n";

	    for (CliLlamFac c : cl) {
		text += c.toString() + "\n";
	    }

	    pMC.setText(text);
	}
    }

    public void añadeExtras(PanelNuevosItemsTarifa pIT) {
	String dni = pIT.getDNI();
	ClientsEmpresa j = null;
	try {
	    j = modelo.recuperaDatos(dni);
	    Tarifa t = j.getTarifa();
	    if (pIT.getMenuSeleccionado().equals("Domingos"))
		t = cT.factoryMethodTarifaDomingos(t);
	    if (pIT.getMenuSeleccionado().equals("Tardes"))
		t = cT.factoryMethodTarifaTardes(t);
	    j.setTarifa(t);
	} catch (ClienteNoExiste e) {
	    pIT.warning("That client doesn't exist.");
	}

    }

    public void emiteFac(PanelLanzaFactura pLF) {
	String dni = pLF.getDNI();
	Factura fac = null;
	try {
	    fac = modelo.emiteFactura(dni);
	    String text = "";
	    text += "Here you have the invoice \n";
	    text += fac.toString() + "\n";

	    pLF.setText(text);
	} catch (ClienteNoExiste e) {
	    pLF.warning("That client doesn't exist.");
	}
    }

    //
    public void recuperaDatFac(PanelMuestraTodo pMT) {
	Factura fac = null;
	int cod = Integer.parseInt(pMT.getDato());
	try {
	    fac = modelo.datosFactura(cod);
	    String text = "";
	    text += "Here you have the invoice \n";
	    text += fac.toString() + "\n";
	    pMT.setText(text);
	} catch (FacturaNoExiste e) {
	    pMT.warning("That invoice doesn't exist");
	}
    }

    //
    public void recuperaFacs(PanelMuestraTodo pMT) {
	String dni = pMT.getDato();
	LinkedList<CliLlamFac> lf = null;
	try {
	    lf = modelo.todasFacturas(dni);
	    if (lf.isEmpty()) {
		pMT.setText("There aren't invoices in data base");
	    } else {
		String text = "";
		text += "Here you have the invoice:\n";
		for (CliLlamFac fac : lf) {
		    text += fac.toString() + "\n";
		}
		pMT.setText(text);
	    }

	} catch (ClienteNoExiste e) {
	    pMT.setText("That client doesn't exist");
	}

    }

    public void facturasGenerico(PanelMenuGenerico pGT) {
	String dni = pGT.getDNI();
	Date fe1 = pGT.getFecha1();
	Date fe2 = pGT.getFecha2();
	try {
	    LinkedList<CliLlamFac> tf = modelo.todasFacturas(dni);
	    try {
		Collection<CliLlamFac> r = modelo.genericoPrueba(tf, fe1, fe2);
		if (r.isEmpty()) {
		    pGT.setText("No matches");
		} else {
		    String text = "";
		    for (CliLlamFac ttl : r)
			text += ttl.toString() + "\n";
		    pGT.setText(text);
		}
	    } catch (FechaIncorrecta e) {
		pGT.warning("Dates weren't introduced properly, try it again.");
	    }
	} catch (ClienteNoExiste e) {
	    pGT.warning("That client doesn't exist");
	}
    }

    public void llamadasGenerico(PanelMenuGenerico pGT) {
	String dni = pGT.getDNI();
	Date fe1 = pGT.getFecha1();
	Date fe2 = pGT.getFecha2();
	try {
	    LinkedList<CliLlamFac> tf = modelo.emiteLlamadas(dni);
	    try {
		Collection<CliLlamFac> r = modelo.genericoPrueba(tf, fe1, fe2);
		if (r.isEmpty()) {
		    pGT.setText("No matches");
		} else {
		    String text = "";
		    for (CliLlamFac ttl : r)
			text += ttl.toString() + "\n";
		    pGT.setText(text);
		}
	    } catch (FechaIncorrecta e) {
		pGT.warning("Dates weren't introduced properly, try it again.");
	    }
	} catch (ClienteNoExiste e) {
	    pGT.warning("That client doesn't exist");
	}
    }

    public void clientesGenerico(PanelMenuGenerico pGT) {
	Date fe1 = pGT.getFecha1();
	Date fe2 = pGT.getFecha2();
	Collection<CliLlamFac> clist = modelo.recuperarClientes();
	try {
	    Collection<CliLlamFac> r = modelo.genericoPrueba(clist, fe1, fe2);
	    if (r.isEmpty()) {
		pGT.setText("No matches");
	    } else {
		String text = "";
		for (CliLlamFac ttl : r)
		    text += ttl.toString() + "\n";
		pGT.setText(text);
	    }
	} catch (FechaIncorrecta e) {
	    pGT.warning("Dates weren't introduced properly, try it again.");
	}

    }

    public void altaLlamada(PanelRegistraLlamada pRL) {
	String dni = pRL.getDNI();
	try {
	    modelo.recuperaDatos(dni);
	    Date fec = pRL.getFecha();
	    Time hor = pRL.getHora();
	    int telf = pRL.getTelf();
	    int dur = pRL.getDur();
	    Llamada llam = new Llamada(telf, fec, hor, dur);
	    modelo.altaLlamada(dni, llam);
	    pRL.setText(llam.toString());
	} catch (ClienteNoExiste e) {
	    pRL.warning("That client doesn't exist.");
	}
    }

    public void listaLlamadas(PanelMuestraTodo pMT) {
	LinkedList<CliLlamFac> llams = null;
	String dni = pMT.getDato();
	try {
	    llams = modelo.emiteLlamadas(dni);

	    if (llams.isEmpty())
		pMT.setText("That client doesn't have phone calls");
	    else {
		String text = "";
		text += "You are going to list all the phone calls \n";
		for (CliLlamFac l : llams) {
		    text += l.toString() + "\n";
		}
		pMT.setText(text);
	    }
	} catch (ClienteNoExiste e) {
	    pMT.warning("That client doesn't exist");
	}
    }
}