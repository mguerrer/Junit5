/*
 * Autor: Marcos Guerrero
 * Fecha: 31-07-2020
 * 
 * Requiere agregar org.junit.jupiter.params.ParameterizedTest a dependencias Maven.
 */

package cl.set.JUnit5;
import cl.set.Calculadora.Core;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestParametrizados {

	@Test
	@DisplayName("1 + 1 = 2")
	void addsTwoNumbers() {
		final Core calculadora = new Core();
		assertEquals(2, calculadora.Suma(1, 1), ()-> "1 + 1 debe ser igual a 2");
	}

	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({ "0,    1,   1", "1,    2,   3", "49,  51, 100", "1,  100, 101" })
	void add(final int primer, final int segundo, final int resultadoEsperado) {
		final Core calculadora = new Core();
		System.out.println(primer + " + " + segundo + " debe ser igual a " + resultadoEsperado);
		assertEquals(resultadoEsperado, calculadora.Suma(primer, segundo),
				() -> primer + " + " + segundo + " debe ser igual a " + resultadoEsperado);
	}
}
