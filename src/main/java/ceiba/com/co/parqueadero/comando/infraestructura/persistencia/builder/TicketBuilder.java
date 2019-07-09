package ceiba.com.co.parqueadero.comando.infraestructura.persistencia.builder;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketCarro;
import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketMoto;
import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.entidad.TicketEntity;

@Component
public class TicketBuilder {

	public TicketEntity convertToEntity(Ticket ticket) {
		if(ticket == null) {
			return null;
		}
		if (ticket instanceof TicketMoto) {
			return new TicketEntity(ticket.getId(), ticket.getPlaca(), ticket.getHoraDeEntrada(),
					ticket.getHoraDeSalida(), ticket.getTipoDeVehiculo(), ((TicketMoto) ticket).getCilindraje(),
					ticket.getTotalAPagar());
		}
		return new TicketEntity(ticket.getId(), ticket.getPlaca(), ticket.getHoraDeEntrada(), ticket.getHoraDeSalida(),
				ticket.getTipoDeVehiculo(), null, ticket.getTotalAPagar());
	}

	public Ticket convertToDomain(TicketEntity ticketEntity) {
		if(ticketEntity == null) {
			return null;
		}
		if (ticketEntity.getTipoDeVehiculo().equals(Ticket.MOTO)) {
			new TicketMoto(ticketEntity.getId(), ticketEntity.getPlaca(), ticketEntity.getHoraDeEntrada(),
					ticketEntity.getHoraDeSalida(), ticketEntity.getTipoDeVehiculo(), ticketEntity.getTotalAPagar(),
					ticketEntity.getCilindraje());
		}
		return new TicketCarro(ticketEntity.getId(), ticketEntity.getPlaca(), ticketEntity.getHoraDeEntrada(),
				ticketEntity.getHoraDeSalida(), ticketEntity.getTipoDeVehiculo(), ticketEntity.getTotalAPagar());
	}
}
