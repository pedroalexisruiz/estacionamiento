package ceiba.com.co.parqueadero.comando.dominio.servicio;

import java.time.LocalDateTime;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;

public interface ServicioRegistrarEntrada {

	public LocalDateTime registrarEntradaDeVehiculo(Ticket ticket);
}
