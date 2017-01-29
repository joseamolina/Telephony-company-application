package prueba.test;

import static org.junit.Assert.*;

import modelo.CliLlamFac;
import modelo.ImplementacionModelo;
import modelo.Llamada;
import modelo.tiposDatos.Clientes.ClientsEmpresa;
import modelo.tiposDatos.TarifaDecorator.Tarifa;
import modelo.tiposDatos.TarifaDecorator.TarifaBase;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepcion.ClienteNoExiste;



public class Prueba1 {
    private ImplementacionModelo<CliLlamFac> fc;
    ClientsEmpresa cli;

    @Before
    public void init() {
	fc = new ImplementacionModelo<CliLlamFac>();
	cli = new ClientsEmpresa();
    }

    @Test
    public void testDarDeAlta(){
	fc.darDeAlta(cli);
	assertEquals(1, fc.recuperarClientes().size(), 0);
    }

    @Test
    public void testBorrarCliente() throws
	    ClienteNoExiste {
	fc.darDeAlta(cli);
	fc.borrarCliente(cli.getNIF());
	assertEquals(0, fc.recuperarClientes().size(), 0);
    }

    @Test
    public void testCambiarTarifa() throws
	    ClienteNoExiste {
	fc.darDeAlta(cli);
	Tarifa t = new TarifaBase();
	fc.cambiarTarifa(t, cli.getNIF());
	assertEquals(25, cli.getTarifa().getTar(), 0);
    }

    @Test
    public void testRecuperaDatos() throws
	    ClienteNoExiste {
	fc.darDeAlta(cli);
	assertThat(fc.recuperaDatos(cli.getNIF()), Is.is(cli));

    }

    @Test
    public void testRecuperaClientes(){
	fc.darDeAlta(cli);
	assertThat(fc.recuperarClientes().size(), Is.is(1));
    }

    @Test
    public void testAltaLlamada() throws
	    ClienteNoExiste {
	fc.darDeAlta(cli);
	fc.altaLlamada(cli.getNIF(), new Llamada());
	assertThat(fc.emiteLlamadas(cli.getNIF()).size(), Is.is(1));
    }

    @Test
    public void testEmiteFacturas() throws
	    ClienteNoExiste {
	fc.darDeAlta(cli);
	fc.emiteFactura(cli.getNIF());
	assertThat(fc.todasFacturas(cli.getNIF()).size(), Is.is(1));
    }

    @After
    public void finish() {
	fc = null;
    }
}
