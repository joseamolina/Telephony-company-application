package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import modelo.tiposDatos.Clientes.ClientsEmpresa;
import modelo.tiposDatos.TarifaDecorator.Tarifa;
import excepcion.ClienteNoExiste;
import excepcion.FacturaNoExiste;
import excepcion.FechaIncorrecta;

public interface Modelo extends Serializable {
    public Collection<CliLlamFac> genericoPrueba(Collection<CliLlamFac> clist,
	    Date f1, Date f2) throws FechaIncorrecta;
    
    public ClientsEmpresa recuperaDatos(String NIF) throws ClienteNoExiste;
    
    public Collection<CliLlamFac> recuperarClientes();
    
    public LinkedList<CliLlamFac> emiteLlamadas(String NIF) throws ClienteNoExiste;
    
    public Factura datosFactura(int cod) throws FacturaNoExiste;
    
    public LinkedList<CliLlamFac> todasFacturas(String dni) throws ClienteNoExiste;
    
    public void darDeAlta(ClientsEmpresa cli);
    
    public void borrarCliente(String NIF) throws ClienteNoExiste;
    
    public void cambiarTarifa(Tarifa tarifa, String NIF) throws ClienteNoExiste;
    
    public void altaLlamada(String NIF, Llamada llam) throws ClienteNoExiste;
    
    public Factura emiteFactura(String NIF) throws ClienteNoExiste;
    
    
}
