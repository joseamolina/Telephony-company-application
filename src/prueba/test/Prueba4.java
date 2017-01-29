package prueba.test;


import static org.junit.Assert.assertThat;

import java.util.Date;

import modelo.Direc;
import modelo.Llamada;
import modelo.tiposDatos.Clientes.ClientsEmpresa;
import modelo.tiposDatos.Clientes.ClientsParticular;
import modelo.tiposDatos.TarifaDecorator.Tarifa;
import modelo.tiposDatos.TarifaDecorator.TarifaBase;
import modelo.tiposDatos.TarifaDecorator.TarifaDomingo;
import modelo.tiposDatos.TarifaDecorator.TarifaTardes;

import org.hamcrest.core.Is;
import org.junit.Test;

import factoriaTipos.CreadorClienteFac;
import factoriaTipos.CreadorClientes;
import factoriaTipos.CreadorTarifaFac;
import factoriaTipos.CreadorTarifas;

// ¡ATENCION! 
public class Prueba4 {

    @Test
    public void factoria1() {
	CreadorClienteFac cC = new CreadorClientes();
	Date t = new Date();
	Tarifa ta = new TarifaBase();
	Direc d = new Direc();
	String c = new ClientsEmpresa("Jose", "0", d, "s", t, ta).toString();
	assertThat(cC.factoryMethodEmpresa("Jose", "0", d, "s", t, ta).toString(),
		Is.is(c));
    }

    @Test
    public void factoria2() {
	CreadorClienteFac cC = new CreadorClientes();
	Date t = new Date();
	Tarifa ta = new TarifaBase();
	Direc d = new Direc();
	String c = new ClientsParticular("Jose", "0", d, "s", t, ta, "h")
		.toString();
	assertThat(cC.factoryMethodParticular("Jose", "0", d, "s", t, ta, "h")
		.toString(), Is.is(c));
    }

    @Test
    public void factoria3() {
	CreadorTarifaFac cT = new CreadorTarifas();
	Tarifa t = cT.factoryMethodTarifaBase(1.0);
	t = cT.factoryMethodTarifaDomingos(t);
	t = cT.factoryMethodTarifaTardes(t);
	Tarifa r = new TarifaBase(1);
	r = new TarifaDomingo(r);
	r = new TarifaTardes(r);
	Llamada l = new Llamada();
	assertThat(t.getPrecio(l), Is.is(r.getPrecio(l)));

    }

}
