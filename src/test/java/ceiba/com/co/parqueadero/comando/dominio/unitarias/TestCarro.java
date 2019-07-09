package ceiba.com.co.parqueadero.comando.dominio.unitarias;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ceiba.com.co.parqueadero.comando.dominio.entidad.TicketCarro;
import ceiba.com.co.parqueadero.comando.testdatabuilder.TicketCarroBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestCarro {

	TicketCarro carro;

	@Before
	public void setUp() {

	}

	@Test
	public void calcularPrecioPorUnDia() {
		// arrange
		LocalDateTime horaDeEntrada = LocalDateTime.of(2019, 07, 9, 0, 0);
		LocalDateTime horaDeSalida = LocalDateTime.of(2019, 07, 10, 0, 0);
		long totalAPagar = TicketCarro.VALOR_DIA;
		carro = new TicketCarroBuilder().conHoraDeEntrada(horaDeEntrada).conHoraDeSalida(horaDeSalida).build();

		// act
		carro.calcularPrecioAPagar();
		// assert
		assertEquals(totalAPagar, carro.getTotalAPagar());
	}

	@Test
	public void calcularPrecioPorHoras() {
		// arrange
		int cantidadDeHoras = 5;
		LocalDateTime horaDeEntrada = LocalDateTime.of(2019, 07, 9, 0, 0);
		LocalDateTime horaDeSalida = LocalDateTime.of(2019, 07, 9, cantidadDeHoras, 0);
		long totalAPagar = TicketCarro.VALOR_HORA * cantidadDeHoras;

		carro = new TicketCarroBuilder().conHoraDeEntrada(horaDeEntrada).conHoraDeSalida(horaDeSalida).build();

		// act
		carro.calcularPrecioAPagar();
		// assert
		assertEquals(totalAPagar, carro.getTotalAPagar());
	}

	@Test
	public void calcularPrecioCuandoCumpleNueveHoras() {
		// arrange
		int cantidadDeHoras = 9;
		LocalDateTime horaDeEntrada = LocalDateTime.of(2019, 07, 9, 0, 0);
		LocalDateTime horaDeSalida = LocalDateTime.of(2019, 07, 9, cantidadDeHoras, 0);
		long totalAPagar = TicketCarro.VALOR_DIA;

		carro = new TicketCarroBuilder().conHoraDeEntrada(horaDeEntrada).conHoraDeSalida(horaDeSalida).build();

		// act
		carro.calcularPrecioAPagar();
		// assert
		assertEquals(totalAPagar, carro.getTotalAPagar());
	}

	@Test
	public void calcularPrecioPorUnDiaYHorasAdicionalesMayoresAlLimite() {
		// arrange
		int cantidadDeHoras = 9;
		LocalDateTime horaDeEntrada = LocalDateTime.of(2019, 07, 9, 0, 0);
		LocalDateTime horaDeSalida = LocalDateTime.of(2019, 07, 10, cantidadDeHoras, 0);
		long totalAPagar = TicketCarro.VALOR_DIA * 2;
		carro = new TicketCarroBuilder().conHoraDeEntrada(horaDeEntrada).conHoraDeSalida(horaDeSalida).build();

		// act
		carro.calcularPrecioAPagar();
		// assert
		assertEquals(totalAPagar, carro.getTotalAPagar());
	}
	
	@Test
	public void calcularPrecioPorUnDiaYHorasAdicionalesMenoresAlLimite() {
		// arrange
		int cantidadDeHoras = 5;
		LocalDateTime horaDeEntrada = LocalDateTime.of(2019, 07, 9, 0, 0);
		LocalDateTime horaDeSalida = LocalDateTime.of(2019, 07, 10, cantidadDeHoras, 0);
		long totalAPagar = TicketCarro.VALOR_DIA + TicketCarro.VALOR_HORA * cantidadDeHoras;
		carro = new TicketCarroBuilder().conHoraDeEntrada(horaDeEntrada).conHoraDeSalida(horaDeSalida).build();

		// act
		carro.calcularPrecioAPagar();
		// assert
		assertEquals(totalAPagar, carro.getTotalAPagar());
	}
}
