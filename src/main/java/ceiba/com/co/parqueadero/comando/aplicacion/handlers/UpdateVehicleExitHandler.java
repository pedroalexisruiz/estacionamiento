package ceiba.com.co.parqueadero.comando.aplicacion.handlers;

import java.util.Date;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.TicketCommand;
import ceiba.com.co.parqueadero.comando.aplicacion.fabrica.TicketFactory;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.servicio.IUpdateVehicleExitService;

@Component
public class UpdateVehicleExitHandler implements CommandResponseHandler<TicketCommand, CommandResponse<Date>> {

	private final IUpdateVehicleExitService createTicketService;
	private final TicketFactory ticketFactory;

	public UpdateVehicleExitHandler(TicketFactory ticketFactory, IUpdateVehicleExitService createTicketService) {
		this.ticketFactory = ticketFactory;
		this.createTicketService = createTicketService;
	}

	@Override
	public CommandResponse<Date> execute(TicketCommand ticketCommand) {
		Ticket ticket = this.ticketFactory.create(ticketCommand);
		return new CommandResponse<Date>(this.createTicketService.updateVehicleExit(ticket.getPlate()).getOutTime());
	}

}
