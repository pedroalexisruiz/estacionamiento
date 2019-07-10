package ceiba.com.co.parqueadero.comando.controladores.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import ceiba.com.co.parqueadero.comando.aplicacion.entidad.TicketCommand;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Vigilante;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.ParqueaderoSinEspacioException;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.VehicleDoubleEntryException;
import ceiba.com.co.parqueadero.comando.infraestructura.controladores.TicketCommandController;
import ceiba.com.co.parqueadero.comando.infraestructura.persistencia.dao.TicketDao;
import ceiba.com.co.parqueadero.comando.testdatabuilder.TicketCommandBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TicketCommandControllerIntegrationTest {
//mockMVC
	@Autowired
	TicketCommandController controlador;

	@Autowired
	TicketDao dao;

	TicketCommand ticketCommand;

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		dao.borrarTodos();
	}

	@Test
	public void ingresarCarro() {
		// arrange
		ticketCommand = new TicketCommandBuilder().conPlaca("PED123").build();
		boolean vehiculoGuardado = false;

		// act
		controlador.crear(ticketCommand);

		// assert

		vehiculoGuardado = dao.existeVehiculoEnParqueadero(ticketCommand.getPlaca());
		assertTrue(vehiculoGuardado);
	}

	@Test
	public void ingresarMoto() {
		// arrange
		ticketCommand = new TicketCommandBuilder().conPlaca("PED123").conCilindraje(250).conTipoDeVehiculo(Ticket.MOTO)
				.build();
		boolean vehiculoGuardado = false;

		// act
		controlador.crear(ticketCommand);

		// assert

		vehiculoGuardado = dao.existeVehiculoEnParqueadero(ticketCommand.getPlaca());
		assertTrue(vehiculoGuardado);
	}

	@Test
	public void sacarCarroNoRegistrado() {
		// arrange
		ticketCommand = new TicketCommandBuilder().conPlaca("PED123").build();
		try {
			// act
			controlador.registrarSalida(ticketCommand);
		} catch (VehicleDoubleEntryException e) {
			// assert
			assertEquals(Vigilante.EL_VEHICULO_NO_SE_ENCUENTRA_EN_EL_PARQUEADERO, e.getMessage());
		}
	}

	@Test
	public void registrarSalida() {
		// arrange
		ticketCommand = new TicketCommandBuilder().conPlaca("PED123").build();
		boolean vehiculoGuardado = false;
		controlador.crear(ticketCommand);

		// act
		controlador.registrarSalida(ticketCommand);

		// assert

		vehiculoGuardado = dao.existeVehiculoEnParqueadero(ticketCommand.getPlaca());
		assertFalse(vehiculoGuardado);
	}

	@Test
	public void ingresarCarroYaIngresado() {
		// arrange
		ticketCommand = new TicketCommandBuilder().conPlaca("PED123").build();

		controlador.crear(ticketCommand);

		// act
		try {
			controlador.crear(ticketCommand);
		} catch (VehicleDoubleEntryException e) {
			// assert
			assertEquals(Vigilante.EL_VEHICULO_SE_ENCUENTRA_EN_EL_PARQUEADERO, e.getMessage());
		}
	}

	@Test
	@Sql("/insertarCarros.sql")
	public void ingresarCarroSinCupo() {
		// arrange
		ticketCommand = new TicketCommandBuilder().conPlaca("PED123").build();

		try {
			// act
			controlador.crear(ticketCommand);
			fail();
		} catch (ParqueaderoSinEspacioException e) {
			// assert
			assertEquals(Vigilante.NO_HAY_ESPACIOS_DISPONIBLES, e.getMessage());
		}
	}
}
