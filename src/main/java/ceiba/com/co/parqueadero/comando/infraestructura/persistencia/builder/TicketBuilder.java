package ceiba.com.co.parqueadero.comando.infraestructura.persistencia.builder;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketCarro;
import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketMoto;
import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.entidad.EntidadTicket;

@Component
public class TicketBuilder {

	public EntidadTicket convertirAEntidad(Ticket ticket) {
		if(ticket == null) {
			return null;
		}
		if (ticket instanceof TicketMoto) {
			return new EntidadTicket(ticket.getId(), ticket.getPlaca(), ticket.getHoraDeEntrada(),
					ticket.getHoraDeSalida(), ticket.getTipoDeVehiculo(), ((TicketMoto) ticket).getCilindraje(),
					ticket.getTotalAPagar());
		}
		return new EntidadTicket(ticket.getId(), ticket.getPlaca(), ticket.getHoraDeEntrada(), ticket.getHoraDeSalida(),
				ticket.getTipoDeVehiculo(), null, ticket.getTotalAPagar());
	}

	public Ticket convertirADominio(EntidadTicket entidadTicket) {
		if(entidadTicket == null) {
			return null;
		}
		if (entidadTicket.getTipoDeVehiculo().equals(Ticket.MOTO)) {
			new TicketMoto(entidadTicket.getId(), entidadTicket.getPlaca(), entidadTicket.getHoraDeEntrada(),
					entidadTicket.getHoraDeSalida(), entidadTicket.getTipoDeVehiculo(), entidadTicket.getTotalAPagar(),
					entidadTicket.getCilindraje());
		}
		return new TicketCarro(entidadTicket.getId(), entidadTicket.getPlaca(), entidadTicket.getHoraDeEntrada(),
				entidadTicket.getHoraDeSalida(), entidadTicket.getTipoDeVehiculo(), entidadTicket.getTotalAPagar());
	}
}
