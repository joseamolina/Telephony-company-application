package modelo.tiposDatos.TarifaDecorator;

import java.io.Serializable;

import modelo.Llamada;

public class TarifaTardes extends MejorTarifa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public TarifaTardes(Tarifa tarifa){
	super(tarifa);
	b1 = tarifa.getB1();
	b2 = tarifa.getB2();
	b3 = true;
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public double getPrecio(Llamada ll) {
	if ((ll.getHorLlam().getHours() >= 16 && ll.getHorLlam().getHours()<20) && !(super.getTarifa().getPrecio(ll)==0.0)){
	    return ((double) super.getTarifa().getPrecio(ll))/2f;
	}else{
	    return super.getTarifa().getPrecio(ll);
	}
    }
    
    @Override
    public String getTarifaDescrip() {
	return tar.getTarifaDescrip() + ", Tarifa Tardes a la mitad de precio" ;
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
