package prueba.test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import modelo.CliLlamFac;
import modelo.ImplementacionModelo;
import modelo.tiposDatos.Clientes.ClientsEmpresa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import excepcion.ClienteNoExiste;
import excepcion.FacturaNoExiste;
import excepcion.FechaIncorrecta;



public class prueba2 {
    private ImplementacionModelo<CliLlamFac> fc;
    @Before
    public void init(){
	fc =new ImplementacionModelo<CliLlamFac>();
    }
    
    @Test
    public void testClienteNoExiste() {
	try{
	    ClientsEmpresa cli =new ClientsEmpresa();
	    fc.borrarCliente(cli.getNIF());
	    
	}catch(ClienteNoExiste e){
	}
    }
    
    @Test
    public void testClienteYaExiste(){
	    ClientsEmpresa cli =new ClientsEmpresa();
	    fc.darDeAlta(cli);
	    fc.darDeAlta(cli);
    }
    
    @Test
    public void testFacturaNoExiste(){
	try{
	    ClientsEmpresa cli =new ClientsEmpresa();
	    fc.darDeAlta(cli);
	    fc.datosFactura(8);
	}catch(FacturaNoExiste e){
	    
	}
    }
    
    @Test
    public void testFechaIncorrecta(){
	try{
	    
	    ClientsEmpresa cli =new ClientsEmpresa();
	    fc.darDeAlta(cli);
	    @SuppressWarnings("deprecation")
	    Date f1=new Date(2013,2,1);
	    @SuppressWarnings("deprecation")
	    Date f2= new Date(2010,2,1);
	    Collection<CliLlamFac> tt = new ArrayList<>();
		for (CliLlamFac c : fc.recuperarClientes()) {
		    tt.add(c);
		}
	    fc.genericoPrueba(tt, f1, f2);
	}catch(FechaIncorrecta e){
	    
	}
    }
    
    @After
    public void finish(){
	fc=null;
    }

}
