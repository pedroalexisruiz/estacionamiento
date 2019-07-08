package ceiba.com.co.parqueadero.comando.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.TicketCommand;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketCarro;
import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketMoto;

@Component
public class TicketFactory {

	public Ticket create(TicketCommand ticketCommand) {
		if (ticketCommand.getVehicleType().equals(Ticket.MOTO)) {
			return new TicketMoto(ticketCommand.getPlate(), ticketCommand.getVehicleType(),
					ticketCommand.getDisplacement());
		}
		return new TicketCarro(ticketCommand.getPlate(), ticketCommand.getVehicleType());
	}
}
