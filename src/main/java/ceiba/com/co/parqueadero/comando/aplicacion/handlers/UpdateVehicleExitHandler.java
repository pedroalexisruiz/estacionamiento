package ceiba.com.co.parqueadero.comando.aplicacion.handlers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.TicketCommand;
import ceiba.com.co.parqueadero.comando.aplicacion.fabrica.TicketFactory;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.servicio.IUpdateVehicleExitService;

@Component
public class UpdateVehicleExitHandler implements CommandResponseHandler<TicketCommand, CommandResponse<LocalDateTime>> {

	private final IUpdateVehicleExitService createTicketService;
	private final TicketFactory ticketFactory;

	public UpdateVehicleExitHandler(TicketFactory ticketFactory, IUpdateVehicleExitService createTicketService) {
		this.ticketFactory = ticketFactory;
		this.createTicketService = createTicketService;
	}

	@Override
	public CommandResponse<LocalDateTime> execute(TicketCommand ticketCommand) {
		Ticket ticket = this.ticketFactory.create(ticketCommand);
		return new CommandResponse<LocalDateTime>(this.createTicketService.updateVehicleExit(ticket.getPlaca()).getHoraDeSalida());
	}

}
