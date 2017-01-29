package modelo.tiposDatos.TarifaDecorator;

import java.io.Serializable;

import modelo.Llamada;

public class TarifaBase extends Tarifa implements Serializable {
    private static final long serialVersionUID = 1L;

    public TarifaBase(double ratio) {
	super(ratio);
	b1=true;
	b2=false;
	b3=false;
    }
    public TarifaBase(){
	super(25);
	b1=true;
    }

    public double getPrecio(Llamada ll) {
	return ll.getDuración()*(getTar()/60f);
    }
    
    public double getTar(){
	return super.getTar();
    }
    
    @Override
    public void setTar(double ratio){
	super.setTar(ratio);
    }

    public String getTarifaDescrip() {
	return "La tarifa base: " + Math.rint(super.getTar()*100)/100 + "€/min";
    }
    
    public boolean getB1(){
	return b1;
    }
    
    public boolean getB2(){
	return b2;
    }
    
    public boolean getB3(){
	return b3;
    }
    

}
