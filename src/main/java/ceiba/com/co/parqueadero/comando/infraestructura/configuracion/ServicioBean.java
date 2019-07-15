package ceiba.com.co.parqueadero.comando.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ceiba.com.co.parqueadero.comando.dominio.entidad.util.GeneradorDeFecha;

@Configuration
public class ServicioBean {

	 /*@Bean
	public Vigilante crearVigilante(RepositorioTicket repositorioDeTickets, GeneradorDeFecha generadorDeFechas) {
		return new Vigilante(repositorioDeTickets, generadorDeFechas);
	}
	*/
	
	@Bean
	public GeneradorDeFecha crearGeneradorDeFechas() {
		return GeneradorDeFecha.obtenerInstancia();
	}
	
}
