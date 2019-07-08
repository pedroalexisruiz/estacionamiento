package ceiba.com.co.parqueadero.comando.infraestructura.persistencia.builder;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.entidad.TicketEntity;

@Component
public class TicketBuilder {

	public TicketEntity convertToEntity(Ticket ticket) {
		return new TicketEntity(ticket.getId(), ticket.getPlate(), ticket.getInTime(), ticket.getOutTime(),
				ticket.getVehicleType(), ticket.getDisplacement(), ticket.getTotalAPagar());
	}

	public Ticket convertToDomain(TicketEntity ticketEntity) {
		return new Ticket(ticketEntity.getId(), ticketEntity.getPlate(), ticketEntity.getInTime(),
				ticketEntity.getOutTime(), ticketEntity.getVehicleType(), ticketEntity.getDisplacement(),
				ticketEntity.getTotalAPagar());
	}
}
