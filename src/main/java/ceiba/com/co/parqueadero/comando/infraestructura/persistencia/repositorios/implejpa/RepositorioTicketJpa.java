package ceiba.com.co.parqueadero.comando.infraestructura.persistencia.repositorios.implejpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.entidad.EntidadTicket;

@Repository
public interface RepositorioTicketJpa extends JpaRepository<EntidadTicket, Long> {
	
	Optional<EntidadTicket> findById(Long id);
	
	@Query(nativeQuery = true, value="select 'true' from ticket t where t.placa=?1 and t.horaDeSalida IS null")
	String existeVehiculoEnParqueadero(String placa);
	
	@Query(value="select t from Ticket t where t.placa=?1 and t.horaDeSalida IS null")
	EntidadTicket buscarPorPlacaSinSalida(String placa);
	
    Long countByTipoDeVehiculoAndHoraDeSalidaIsNull(String tipoDeVehiculo);
}
