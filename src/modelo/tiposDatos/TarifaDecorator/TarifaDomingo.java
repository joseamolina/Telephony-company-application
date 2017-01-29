package modelo.tiposDatos.TarifaDecorator;

import java.io.Serializable;
import java.util.Calendar;

import modelo.Llamada;


public class TarifaDomingo extends MejorTarifa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public TarifaDomingo(Tarifa tarifa){
	super(tarifa);
	b1 = tarifa.getB1();
	b2 = true;
	b3 = tarifa.getB3();
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public double getPrecio(Llamada ll){
	if (ll.getFecha().getDay()==Calendar.SUNDAY-1)
	    return 0.0;
	else
	    return super.getTarifa().getPrecio(ll);
    }

    @Override
    public String getTarifaDescrip() {
	return tar.getTarifaDescrip() + ", Tarifa Domingos Gratis ";
    }

    @Override
    public void setTar(double precio) {
	super.setTar(precio);
    }
    
    @Override
    public double getTar(){
	return super.getTar();
    }

    @Override
    public boolean getB1() {
	return b1;
    }

    @Override
    public boolean getB2() {
	return b2;
    }

    @Override
    public boolean getB3() {
	return b3;
    }

}
