package ceiba.com.co.parqueadero.consulta.dominio.dao;

import java.util.List;

import ceiba.com.co.parqueadero.consulta.dominio.dto.Ticket;

public interface DaoTicket {
	
	List<Ticket> listarVehiculosIngresados();
	
}
