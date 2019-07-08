package ceiba.com.co.parqueadero.comando.dominio.entidad;

import java.util.Date;

import org.springframework.stereotype.Service;

import ceiba.com.co.parqueadero.comando.dominio.excepcion.VehicleDoubleEntryException;
import ceiba.com.co.parqueadero.comando.dominio.repositorio.ITicketRepository;
import ceiba.com.co.parqueadero.comando.dominio.servicio.ICreateTicketService;
import ceiba.com.co.parqueadero.comando.dominio.servicio.IUpdateVehicleExitService;

@Service
public class Vigilante implements ICreateTicketService, IUpdateVehicleExitService {
	private static final String EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehículo ya se encuentra"
			+ " en el parqueadero.";
	private static final String EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehículo no se encuentra"
			+ " en el parqueadero.";

	private final ITicketRepository ticketRepository;

	public Vigilante(ITicketRepository ticketRepository) {
		super();
		this.ticketRepository = ticketRepository;
	}

	public Long registerVehicle(Ticket ticket) {
		checkVehicleIn(ticket);
		ticket.setInTime(new Date());
		return this.ticketRepository.create(ticket);
	}

	private void checkVehicleIn(Ticket ticket) {
		boolean exists = this.ticketRepository.existeVehiculoEnParqueadero(ticket.getPlate());
		if (exists) {
			throw new VehicleDoubleEntryException(EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
	}

	@Override
	public Ticket updateVehicleExit(String plate) {
		Ticket ticket = this.ticketRepository.buscarPorPlacaSinSalida(plate);
		if (ticket == null) {
			throw new VehicleDoubleEntryException(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
		ticket.setOutTime(new Date());
		this.ticketRepository.update(ticket);
		return ticket;
	}
}
