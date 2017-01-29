package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

import vista.Vista;
import modelo.tiposDatos.Clientes.ClientsEmpresa;
import modelo.tiposDatos.TarifaDecorator.Tarifa;
import excepcion.*;

public final class ImplementacionModelo<T extends CliLlamFac> implements
	Modelo, Serializable {
    private static final long serialVersionUID = 1165014988510611760L;
    private Map<ClientsEmpresa, LinkedList<CliLlamFac>> mapaCliente_Fac;
    private LinkedList<Factura> fact;
    private Map<ClientsEmpresa, LinkedList<CliLlamFac>> mapaCliente_Llam;
    private Map<ClientsEmpresa, HashSet<CliLlamFac>> llama_pend_fac;
    private int codFac;
    private Vista vista;

    public ImplementacionModelo() {
	this.mapaCliente_Fac = new HashMap<ClientsEmpresa, LinkedList<CliLlamFac>>();
	this.fact = new LinkedList<Factura>();
	this.mapaCliente_Llam = new HashMap<ClientsEmpresa, LinkedList<CliLlamFac>>();
	this.llama_pend_fac = new HashMap<ClientsEmpresa, HashSet<CliLlamFac>>();
	this.codFac = 0;
    }

    public void setVista(Vista vista) {
	this.vista = vista;
    }

    @Override
    public Collection<CliLlamFac> genericoPrueba(Collection<CliLlamFac> clist,
	    Date f1, Date f2) throws FechaIncorrecta {
	if (f1.after(f2) || f2.before(f1))
	    throw new FechaIncorrecta();
	Collection<CliLlamFac> retorno = new LinkedList<CliLlamFac>();

	for (CliLlamFac objeto : clist) {
	    if (f1.before(objeto.getFecha()) && f2.after(objeto.getFecha())) {
		retorno.add(objeto);
	    }
	}
	return retorno;
    }

    public void darDeAlta(ClientsEmpresa cli){
	@SuppressWarnings("unused")
	ClientsEmpresa cl = null;
	try {
	    cl = recuperaDatos(cli.getNIF());
	    if (vista!=null)
		vista.setWarningRegistraCliente();
	} catch (ClienteNoExiste e) {
	    LinkedList<CliLlamFac> fac = new LinkedList<CliLlamFac>();
	    LinkedList<CliLlamFac> llam = new LinkedList<CliLlamFac>();
	    HashSet<CliLlamFac> k = new HashSet<CliLlamFac>();
	    mapaCliente_Llam.put(cli, llam);
	    mapaCliente_Fac.put((ClientsEmpresa) cli, fac);
	    llama_pend_fac.put(cli, k);
	    if (vista!=null)
		vista.setContestacion(cli.toString());
	}
    }

    public void borrarCliente(String NIF) throws ClienteNoExiste {
	ClientsEmpresa j = null;
	try {
	    j = recuperaDatos(NIF);
	    for (Factura f : fact) {
		if (f.getCodCli().equals(NIF))
		    fact.remove(f);
	    }
	    mapaCliente_Fac.remove(j);
	    mapaCliente_Llam.remove(j);
	    llama_pend_fac.remove(j);

	} catch (ClienteNoExiste e) {
	    throw new ClienteNoExiste();
	}

    }

    public void cambiarTarifa(Tarifa tarifa, String NIF) throws ClienteNoExiste {
	ClientsEmpresa j = null;
	try {
	    j = recuperaDatos(NIF);
	    j.setTarifa(tarifa);
	} catch (ClienteNoExiste e) {
	    throw new ClienteNoExiste();
	}
    }

    public ClientsEmpresa recuperaDatos(String NIF) throws ClienteNoExiste {
	for (ClientsEmpresa j : mapaCliente_Fac.keySet()) {
	    if (j.getNIF().equals(NIF)) {
		return j;
	    }
	}
	throw new ClienteNoExiste();

    }

    public Collection<CliLlamFac> recuperarClientes() {
	Collection<CliLlamFac> c = new LinkedList<CliLlamFac>(
		mapaCliente_Fac.keySet());
	return c;
    }

    public void altaLlamada(String NIF, Llamada llam) throws ClienteNoExiste {
	ClientsEmpresa j = null;
	try {
	    j = recuperaDatos(NIF);
	    LinkedList<CliLlamFac> h = mapaCliente_Llam.get(j);
	    HashSet<CliLlamFac> i = llama_pend_fac.get(j);
	    i.add(llam);
	    h.add(llam);
	    llama_pend_fac.put(j, i);
	    mapaCliente_Llam.put(j, h);
	} catch (ClienteNoExiste e) {
	    throw new ClienteNoExiste();
	}
    }

    public LinkedList<CliLlamFac> emiteLlamadas(String NIF)
	    throws ClienteNoExiste {
	ClientsEmpresa j = null;
	try {
	    j = recuperaDatos(NIF);
	} catch (ClienteNoExiste e) {
	    throw new ClienteNoExiste();
	}
	return mapaCliente_Llam.get(j);
    }

    public Factura emiteFactura(String NIF) throws ClienteNoExiste {
	ClientsEmpresa j = null;

	try {
	    j = recuperaDatos(NIF);
	    codFac++;
	    Date fecha = new Date();
	    Factura f = new Factura();
	    f.setTarifa(j.getTarifa());
	    f.setCodigo(codFac);
	    f.setCodCli(j.getNIF());
	    Periodo per = new Periodo();
	    f.setFecha(fecha);
	    if (mapaCliente_Fac.get(j).isEmpty()) {
		per.setInicial(j.getFecha());
	    } else {
		CliLlamFac nf = mapaCliente_Fac.get(j).getLast();
		per.setInicial(nf.getFecha());
	    }
	    per.setAlfinal(fecha);
	    f.setPeriodo(per);
	    double total = 0.0;
	    Tarifa tar = j.getTarifa();
	    if (!llama_pend_fac.get(j).isEmpty()) {
		for (CliLlamFac k : llama_pend_fac.get(j))
		    total += tar.getPrecio((Llamada) k);
	    }
	    f.setImporte(total);
	    LinkedList<CliLlamFac> g = mapaCliente_Fac.get(j);
	    g.add(f);
	    mapaCliente_Fac.put(j, g);
	    HashSet<CliLlamFac> nueva = new HashSet<CliLlamFac>();
	    llama_pend_fac.put(j, nueva);
	    fact.add(f);
	    return f;
	} catch (ClienteNoExiste e) {
	    throw new ClienteNoExiste();
	}
    }

    public Factura datosFactura(int cod) throws FacturaNoExiste {
	for (Factura f : fact) {
	    if (f.getCodigo() == cod) {
		return f;
	    }
	}
	throw new FacturaNoExiste();
    }

    public LinkedList<CliLlamFac> todasFacturas(String dni)
	    throws ClienteNoExiste {
	ClientsEmpresa j = null;
	try {
	    j = recuperaDatos(dni);
	} catch (ClienteNoExiste e) {
	    throw new ClienteNoExiste();
	}
	return mapaCliente_Fac.get(j);
    }
}