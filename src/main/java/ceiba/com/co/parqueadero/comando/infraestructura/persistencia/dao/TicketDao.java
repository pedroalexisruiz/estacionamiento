package ceiba.com.co.parqueadero.comando.infraestructura.persistencia.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.repositorio.ITicketRepository;
import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.builder.TicketBuilder;
import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.dao.implejpa.ITicketDao;

@Repository
public class TicketDao implements ITicketRepository {

	@Autowired
	ITicketDao dao;
	@Autowired
	TicketBuilder builder;

	@Override
	public Long create(Ticket ticket) {
		return dao.save(builder.convertToEntity(ticket)).getId();
	}

	@Override
	public void update(Ticket ticket) {
		dao.save(builder.convertToEntity(ticket));
	}

	@Override
	public boolean existeVehiculoEnParqueadero(String plate) {
		return dao.existeVehiculoEnParqueadero(plate) == "true";
	}

	@Override
	public Ticket buscarPorId(Long id) {
		return builder.convertToDomain(dao.findById(id).get());
	}

	@Override
	public Ticket buscarPorPlacaSinSalida(String plate) {
		return builder.convertToDomain(dao.buscarPorPlacaSinSalida(plate));
	}
}