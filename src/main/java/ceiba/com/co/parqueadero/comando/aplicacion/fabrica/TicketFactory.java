package ceiba.com.co.parqueadero.comando.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.TicketCommand;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;

@Component
public class TicketFactory {
	public Ticket create(TicketCommand ticketCommand) {
		return new Ticket(ticketCommand.getPlate(), ticketCommand.getVehicleType(), ticketCommand.getDisplacement());
	}
}
