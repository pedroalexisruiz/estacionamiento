package ceiba.com.co.parqueadero.comando.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ceiba.com.co.parqueadero.comando.dominio.repositorio.TicketRepository;
import ceiba.com.co.parqueadero.comando.dominio.servicio.CreateTicketService;

@Configuration
public class ServiceBean {

	@Bean
	public CreateTicketService createTicketService(TicketRepository ticketRepository) {
		return new CreateTicketService(ticketRepository);
	}
}
