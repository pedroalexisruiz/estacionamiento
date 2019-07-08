package ceiba.com.co.parqueadero.comando.dominio.entidad;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import ceiba.com.co.parqueadero.comando.dominio.excepcion.DiaNoHabilException;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.ParqueaderoSinEspacioException;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.VehicleDoubleEntryException;
import ceiba.com.co.parqueadero.comando.dominio.repositorio.ITicketRepository;
import ceiba.com.co.parqueadero.comando.dominio.servicio.ICreateTicketService;
import ceiba.com.co.parqueadero.comando.dominio.servicio.IUpdateVehicleExitService;

@Service
public class Vigilante implements ICreateTicketService, IUpdateVehicleExitService {

	private static final int NUMERO_MAXIMO_CARROS = 20;
	private static final int NUMERO_MAXIMO_MOTOS = 10;

	private static final char PRIMERA_LETRA_PLACA_NO_VALIDA = 'A';
	private static final String DIA_NO_HABIL = "No se permite el ingreso del vehiculo en día no habil";

	private static final String EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehículo ya se encuentra"
			+ " en el parqueadero.";
	private static final String EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO = "El vehículo no se encuentra"
			+ " en el parqueadero.";
	private static final String NO_HAY_ESPACIOS_DISPONIBLES = "No hay espacios disponibles" + " en el parqueadero.";

	private final ITicketRepository ticketRepository;

	public Vigilante(ITicketRepository ticketRepository) {
		super();
		this.ticketRepository = ticketRepository;
	}

	public Long registrarEntradaDeVehiculo(Ticket ticket) {
		verificarPlacasRestringidas(ticket.getPlaca());
		verificarDisponibilidad(ticket.getTipoDeVehiculo());
		verificarVehiculoDentro(ticket);
		ticket.setHoraDeEntrada(LocalDateTime.now());

		return this.ticketRepository.create(ticket);
	}

	public void verificarPlacasRestringidas(String placa) {
		char primeraLetra = placa.charAt(0);
		Calendar calendario = Calendar.getInstance();

		if (primeraLetra == PRIMERA_LETRA_PLACA_NO_VALIDA && !esDiaHabil(calendario.get(Calendar.DAY_OF_WEEK))) {
			throw new DiaNoHabilException(DIA_NO_HABIL);
		}

	}

	private boolean esDiaHabil(int dia) {
		return dia == Calendar.MONDAY || dia == Calendar.SUNDAY;
	}

	public void verificarDisponibilidad(String tipoDeVehiculo) {
		long cantidadOcupada = this.ticketRepository.contarVehiculosParqueadosPorTipo(tipoDeVehiculo);

		switch (tipoDeVehiculo) {
		case Ticket.MOTO:
			verificarDisponibilidadDeMotos(cantidadOcupada);
			break;
		case Ticket.CARRO:
			verificarDisponibilidadDeCarros(cantidadOcupada);
			break;
		default:
			break;
		}
	}

	private void verificarDisponibilidadDeMotos(long cantidadOcupada) {
		if (cantidadOcupada >= NUMERO_MAXIMO_MOTOS) {
			throw new ParqueaderoSinEspacioException(NO_HAY_ESPACIOS_DISPONIBLES);
		}
	}

	private void verificarDisponibilidadDeCarros(long cantidadOcupada) {
		if (cantidadOcupada >= NUMERO_MAXIMO_CARROS) {
			throw new ParqueaderoSinEspacioException(NO_HAY_ESPACIOS_DISPONIBLES);
		}
	}

	private void verificarVehiculoDentro(Ticket ticket) {
		boolean exists = this.ticketRepository.existeVehiculoEnParqueadero(ticket.getPlaca());
		if (exists) {
			throw new VehicleDoubleEntryException(EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
	}

	@Override
	public Ticket registrarSalidaDelVehiculo(String plate) {
		Ticket ticket = this.ticketRepository.buscarPorPlacaSinSalida(plate);
		if (ticket == null) {
			throw new VehicleDoubleEntryException(EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO);
		}
		ticket.setHoraDeSalida(LocalDateTime.now());
		ticket.calcularPrecioAPagar();
		this.ticketRepository.update(ticket);
		return ticket;
	}
}
