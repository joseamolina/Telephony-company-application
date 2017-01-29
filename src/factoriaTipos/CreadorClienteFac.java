package factoriaTipos;

import java.io.Serializable;
import java.util.Date;

import modelo.Direc;
import modelo.tiposDatos.Clientes.ClientsEmpresa;
import modelo.tiposDatos.TarifaDecorator.Tarifa;


public abstract class CreadorClienteFac implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */

    public abstract ClientsEmpresa factoryMethodEmpresa(String nombre, String NIF, Direc direccion,
	    String email, Date fecha, Tarifa tarifa);
    
    public abstract ClientsEmpresa factoryMethodParticular(String nombre, String NIF, Direc direccion,
	    String email, Date fecha, Tarifa tarifa, String apellidos);
    
    public abstract ClientsEmpresa factoryMethodEmpresaRegular();
    
    public abstract ClientsEmpresa factoryMethodParticularRegular();
}
