package ceiba.com.co.parqueadero.consulta.infraestructura.persistencia.dao.mapeo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ceiba.com.co.parqueadero.consulta.dominio.dto.Ticket;

public class TicketMapper implements RowMapper<Ticket> {

	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		if(!rs.next()) {
			return null;
		}
		return new Ticket(rs.getLong("id"), rs.getString("placa"), rs.getTimestamp("horaDeEntrada").toLocalDateTime(),
				rs.getTimestamp("horaDeSalida").toLocalDateTime(), rs.getString("tipoDeVehiculo"), rs.getLong("totalAPagar"),
				rs.getInt("cilindraje"));
	}

}
