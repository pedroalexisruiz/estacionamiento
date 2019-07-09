package ceiba.com.co.parqueadero.comando.infraestructura.persistencia.dao.implejpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.entidad.TicketEntity;

@Repository
public interface ITicketDao extends JpaRepository<TicketEntity, Long> {
	
	Optional<TicketEntity> findById(Long id);
	
	@Query(nativeQuery = true, value="select 'true' from ticket t where t.placa=?1 and t.horaDeSalida IS null")
	String existeVehiculoEnParqueadero(String placa);
	
	@Query(value="select t from Ticket t where t.placa=?1 and t.horaDeSalida IS null")
	TicketEntity buscarPorPlacaSinSalida(String placa);
	
    Long countByTipoDeVehiculoAndHoraDeSalidaIsNull(String tipoDeVehiculo);
}
