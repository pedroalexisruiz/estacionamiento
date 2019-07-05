package ceiba.com.co.parqueadero.comando.infraestructura.persistencia.mapeo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.entidad.TicketDto;

public class TicketMapper implements RowMapper<TicketDto> {

	@Override
	public TicketDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		long id = resultSet.getLong("id");
		String plate = resultSet.getString("plate");
		LocalDateTime inTime = resultSet.getTimestamp("inTime").toLocalDateTime();
		LocalDateTime outTime = resultSet.getTimestamp("outTime").toLocalDateTime();
		String vehicleType = resultSet.getString("vehicleType");
		int displacement = resultSet.getInt("displacement");
		return new TicketDto(id, plate, inTime, outTime, vehicleType, displacement);
	}

}
