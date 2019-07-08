package ceiba.com.co.parqueadero.comando.dominio.repositorio;

import org.springframework.stereotype.Repository;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;

@Repository
public interface ITicketRepository {

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
	boolean existeVehiculoEnParqueadero(String plate);
	
	Ticket buscarPorId(Long id);
	
	Ticket buscarPorPlacaSinSalida(String plate);
}
