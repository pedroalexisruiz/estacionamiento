package ceiba.com.co.parqueadero.comando.dominio.repositorio;

import java.time.LocalDateTime;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;

public interface RepositorioTicket {

	/**
	 * Permite crear un ticket
	 * 
	 * @param ticket
	 * @return el id del ticket generado
	 */
	LocalDateTime registrarEntrada(Ticket ticket);

	/**
	 * Permite actualizar un ticket
	 * 
	 * @param ticket
	 */
	void registrarSalida(Ticket ticket);

	/**
	 * Permite validar si existe un ticket con un placa dada
	 * 
	 * @param plate
	 * @return si existe o no
	 */
	boolean existeVehiculoEnParqueadero(String plate);
	
	Ticket buscarPorId(Long id);
	
	Ticket buscarPorPlacaSinSalida(String plate);
	
	Long contarVehiculosParqueadosPorTipo(String tipoDeVehiculo);
	
	void borrarTodos();
}
