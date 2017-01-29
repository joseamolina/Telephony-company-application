package factoriaTipos;

import java.io.Serializable;

import modelo.tiposDatos.TarifaDecorator.Tarifa;
import modelo.tiposDatos.TarifaDecorator.TarifaBase;
import modelo.tiposDatos.TarifaDecorator.TarifaDomingo;
import modelo.tiposDatos.TarifaDecorator.TarifaTardes;

public class CreadorTarifas extends CreadorTarifaFac implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    @Override
    public Tarifa factoryMethodTarifaBase(double precio) {
	return new TarifaBase(precio);
    }
    

    @Override
    public Tarifa factoryMethodTarifaDomingos(Tarifa tarifa) {
	return new TarifaDomingo(tarifa);
    }

    @Override
    public Tarifa factoryMethodTarifaTardes(Tarifa tarifa) {
	return new TarifaTardes(tarifa);
    }


    @Override
    public Tarifa factoryMethodTarifaBaseRegular() {
	return new TarifaBase();
    }
}
