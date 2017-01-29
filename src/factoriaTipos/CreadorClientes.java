package factoriaTipos;

import java.io.Serializable;
import java.util.Date;

import modelo.Direc;
import modelo.tiposDatos.Clientes.ClientsEmpresa;
import modelo.tiposDatos.Clientes.ClientsParticular;
import modelo.tiposDatos.TarifaDecorator.Tarifa;


public class CreadorClientes extends CreadorClienteFac implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public ClientsEmpresa factoryMethodEmpresa(String nombre, String NIF, Direc direccion,
	    String email, Date fecha, Tarifa tarifa) {
	return new ClientsEmpresa(nombre, NIF,direccion,
		    email, fecha, tarifa);
    }
    
    @Override
    public ClientsEmpresa factoryMethodEmpresaRegular(){
	return new ClientsEmpresa();
    }

    @Override
    public ClientsEmpresa factoryMethodParticular(String nombre, String NIF, Direc direccion,
	    String email, Date fecha, Tarifa tarifa, String apellidos) {
	return new ClientsParticular(nombre, NIF, direccion,
		    email,fecha, tarifa, apellidos);
    }
    
    @Override
    public ClientsEmpresa factoryMethodParticularRegular(){
	return new ClientsParticular();
    }
    
}
