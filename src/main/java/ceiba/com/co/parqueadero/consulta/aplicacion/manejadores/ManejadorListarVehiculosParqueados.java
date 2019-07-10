package ceiba.com.co.parqueadero.consulta.aplicacion.manejadores;

import java.util.List;

import org.springframework.stereotype.Component;

import ceiba.com.co.parqueadero.consulta.dominio.dao.DaoTicket;
import ceiba.com.co.parqueadero.consulta.dominio.dto.Ticket;

@Component
public class ManejadorListarVehiculosParqueados {

	private final DaoTicket daoTicket;

	public ManejadorListarVehiculosParqueados(DaoTicket daoTicket) {
		this.daoTicket = daoTicket;
	}

	public List<Ticket> ejecutar() {
		return daoTicket.listarVehiculosIngresados();
	}
}
