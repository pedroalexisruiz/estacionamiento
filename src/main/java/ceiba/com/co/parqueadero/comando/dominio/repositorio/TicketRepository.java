package ceiba.com.co.parqueadero.comando.dominio.repositorio;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;

public interface TicketRepository {

	/**
	 * Permite crear un ticket
	 * 
	 * @param ticket
	 * @return el id del ticket generado
	 */
	Long create(Ticket ticket);

	/**
	 * Permite actualizar un ticket
	 * 
	 * @param ticket
	 */
	void update(Ticket ticket);

	/**
	 * Permite validar si existe un ticket con un placa dada
	 * 
	 * @param plate
	 * @return si existe o no
	 */
	boolean exists(String plate);
}
