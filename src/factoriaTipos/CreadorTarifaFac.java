package factoriaTipos;

import java.io.Serializable;

import modelo.tiposDatos.TarifaDecorator.Tarifa;

public abstract class CreadorTarifaFac implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public abstract Tarifa factoryMethodTarifaBase(double precio);
    
    public abstract Tarifa factoryMethodTarifaBaseRegular();
    
    public abstract Tarifa factoryMethodTarifaDomingos(Tarifa tarifa);
    
    public abstract Tarifa factoryMethodTarifaTardes(Tarifa tarifa);
    
}
