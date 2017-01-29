//La interfaz se encarga de limitar los tipos de la biblioteca principal(Clients, Llamadas y facturas).

package modelo;

import java.util.Date;

public interface CliLlamFac {
    public Date getFecha();

    public String toString();
}
