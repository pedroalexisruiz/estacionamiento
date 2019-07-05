package ceiba.com.co.parqueadero.comando.dominio.servicio;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.VehicleDoubleEntryException;
import ceiba.com.co.parqueadero.comando.dominio.repositorio.TicketRepository;

public class CreateTicketService {

	private static final String EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehículo ya se encuentra"
			+ "en el parqueadero.";
	
	private final TicketRepository ticketRepository;

	public CreateTicketService(TicketRepository ticketRepository) {
		super();
		this.ticketRepository = ticketRepository;
	}
	
	public Long execute(Ticket ticket) {
		checkVehicleIn(ticket);
		return this.ticketRepository.create(ticket);
	}
	
	private void checkVehicleIn(Ticket ticket) {
		boolean exists = this.ticketRepository.exists(ticket.getPlate());
		if(exists) {
			throw new VehicleDoubleEntryException(EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
	}
}
