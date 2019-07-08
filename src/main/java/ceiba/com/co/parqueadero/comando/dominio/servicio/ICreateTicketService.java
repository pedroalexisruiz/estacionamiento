package ceiba.com.co.parqueadero.comando.dominio.servicio;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;

public interface ICreateTicketService {

	public Long registrarEntradaDeVehiculo(Ticket ticket);
}
