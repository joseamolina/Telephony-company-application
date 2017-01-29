package controlador;

import vista.PanelBorrarCliente;
import vista.PanelCambiaTarifa;
import vista.PanelLanzaFactura;
import vista.PanelMenuClientes;
import vista.PanelMenuGenerico;
import vista.PanelMuestraTodo;
import vista.PanelNuevosItemsTarifa;
import vista.PanelRegistraLlamada;
import vista.PanelRegistrarCliente;

public interface Controlador {
    public void entrada();

    public void salida();

    public void borra(PanelBorrarCliente pBC);

    public void altaEmpresa(PanelRegistrarCliente pRC);

    public void altaParticular(PanelRegistrarCliente pRC);

    public void generator(PanelRegistrarCliente pRC);

    public void cambiaTarifa1(PanelCambiaTarifa pCT);

    public void recuperaCliente1(PanelMuestraTodo pMT);

    public void recuperaListado1(PanelMenuClientes pMC);

    public void añadeExtras(PanelNuevosItemsTarifa pIT);

    public void emiteFac(PanelLanzaFactura pLF);

    public void recuperaDatFac(PanelMuestraTodo pMT);

    public void recuperaFacs(PanelMuestraTodo pMT);

    public void facturasGenerico(PanelMenuGenerico pGT);

    public void llamadasGenerico(PanelMenuGenerico pGT);

    public void clientesGenerico(PanelMenuGenerico pGT);

    public void altaLlamada(PanelRegistraLlamada pRL);

    public void listaLlamadas(PanelMuestraTodo pMT);

}
