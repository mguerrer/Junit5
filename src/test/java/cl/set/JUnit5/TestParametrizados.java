/*
 * Autor: Marcos Guerrero
 * Fecha: 31-07-2020
 * 
 * Requiere agregar a dependencias Maven
 * - org.junit.jupiter.params.ParameterizedTest 
 * - junit-jupiter-params
 * https://junit.org/junit5/docs/snapshot/user-guide/#writing-tests-parameterized-tests-sources-CsvSource
 */

package cl.set.JUnit5;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvFileSource;

class TestParametrizados {

	@Test
	@DisplayName("1 + 1 = 2")
	void addsTwoNumbers() {
		final Core calculadora = new Core();
		assertEquals(2, calculadora.Suma(1, 1), ()-> "1 + 1 debe ser igual a 2");
	}

	@ParameterizedTest(name = "{0} + {1} = {2}")
	@DisplayName("Prueba con fuente fija")
	@CsvSource({ "0,    1,   1", "1,    2,   3", "49,  51, 100", "1,  100, 101" })
	void add(final int primer, final int segundo, final int resultadoEsperado) {
		final Core calculadora = new Core();
		System.out.println(primer + " + " + segundo + " debe ser igual a " + resultadoEsperado);
		assertEquals(resultadoEsperado, calculadora.Suma(primer, segundo),
				() -> primer + " + " + segundo + " debe ser igual a " + resultadoEsperado);
	}

	@ParameterizedTest
	@DisplayName("Prueba con archivos .csv")
	@CsvFileSource(resources = "/usuarios.csv", numLinesToSkip = 1)
	void testWithCsvFileSourceFromFile(final String nombre, final String apellidos) {
		System.out.println("Usuario:"+nombre+" "+apellidos);
	}
}
