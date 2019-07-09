package ceiba.com.co.parqueadero.comando.dominio.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ceiba.com.co.parqueadero.comando.dominio.entidad.Ticket;
import ceiba.com.co.parqueadero.comando.dominio.entidad.Vigilante;
import ceiba.com.co.parqueadero.comando.dominio.entidad.util.GeneradorDeFecha;
import ceiba.com.co.parqueadero.comando.dominio.excepcion.DiaNoHabilException;
import ceiba.com.co.parqueadero.comando.testdatabuilder.TicketCarroBuilder;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestVigilante {

	@Mock
	GeneradorDeFecha generadorDeFecha;

	@Spy 
	@InjectMocks
	Vigilante vigilante;

	@Before
	public void setUp() {

	}

	@Test
	public void registrarCarroEnDiaHabil() {
		// arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2019, 7, 11, 12, 0, 0);
		String placa = "ABC078";
		Ticket carro = new TicketCarroBuilder().conPlaca(placa).conHoraDeEntrada(fechaIngreso).build();
		Calendar fechaActual = Calendar.getInstance();
		long idTicket = 1;
		long resultado;

		fechaActual.setTime(Date.from(fechaIngreso.atZone(ZoneId.systemDefault()).toInstant()));
		
		doReturn(0L).when(vigilante).contarVehiculosParqueadosPorTipo(carro.getTipoDeVehiculo());
		doReturn(false).when(vigilante).existeVehiculoEnParqueadero(carro.getPlaca());
		doReturn(idTicket).when(vigilante).registrarEntrada(carro);
		
		when(generadorDeFecha.obtenerFechaActual()).thenReturn(fechaActual);
		when(generadorDeFecha.obtenerHoraLocalActual()).thenReturn(
				Instant.ofEpochMilli(fechaActual.getTime().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
		// act
		resultado = vigilante.registrarEntradaDeVehiculo(carro);

		// assert
		assertEquals(idTicket, resultado);
	}

	@Test
	public void registrarCarroEnDiaNoHabil() {
		// arrange
		LocalDateTime fechaIngreso = LocalDateTime.of(2019, 07, 7, 12, 0, 0);
		String placa = "ABC078";
		Ticket carro = new TicketCarroBuilder().conPlaca(placa).conHoraDeEntrada(fechaIngreso).build();

		Calendar fechaActual = Calendar.getInstance();
		fechaActual.setTime(Date.from(fechaIngreso.atZone(ZoneId.systemDefault()).toInstant()));
		System.out.println(fechaActual.getTime());
		when(generadorDeFecha.obtenerFechaActual()).thenReturn(fechaActual);
		// act
		try {
			vigilante.registrarEntradaDeVehiculo(carro);
			fail();
		} catch (DiaNoHabilException e) {
			// assert
			assertEquals(Vigilante.DIA_NO_HABIL, e.getMessage());
		}
	}
}
