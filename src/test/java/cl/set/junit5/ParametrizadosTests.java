/*
 * Autor: Marcos Guerrero
 * Fecha: 31-07-2020
 * 
 * Requiere agregar a dependencias Maven
 * import org.junit.jupiter.params.ParameterizedTest;
 *  import org.junit.jupiter.params.provider.CsvSource;
 * https://junit.org/junit5/docs/snapshot/user-guide/#writing-tests-parameterized-tests-sources-CsvSource
 */

package cl.set.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvFileSource;

@Execution(ExecutionMode.CONCURRENT)
public class ParametrizadosTests {

	boolean showThread = false;
	@Test
	@DisplayName("1 + 1 = 2")
	void SumaDosNúmeros() {
		final Calculadora calculadora = new Calculadora();
		if (showThread) System.out.print("Thread:"+Thread.currentThread().getId()+"-");
		assertEquals(2, calculadora.Suma(1, 1), ()-> "1 + 1 debe ser igual a 2");
	}

	@DisplayName("Prueba con fuente fija")
	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({ "0,    1,   1", "1,    2,   3", "49,  51, 100", "1,  100, 101" })
	void SumaListaDeNumeros(final int primer, final int segundo, final int resultadoEsperado) {
		final Calculadora calculadora = new Calculadora();
		if (showThread) System.out.print("Thread:"+Thread.currentThread().getId()+"-");
		System.out.println(primer + " + " + segundo + " debe ser igual a " + resultadoEsperado);
		assertEquals(resultadoEsperado, calculadora.Suma(primer, segundo),
				() -> primer + " + " + segundo + " debe ser igual a " + resultadoEsperado);
	}

	@DisplayName("Prueba con archivos .csv")
	@ParameterizedTest (name = "Usuario {0} {1}")
	@CsvFileSource(resources = "/usuarios.csv", numLinesToSkip = 1)
	void testWithCsvFileSourceFromFile(final String nombre, final String apellidos) {
		if (showThread) System.out.print("Thread:"+Thread.currentThread().getId()+"-");
		System.out.println("Usuario:"+nombre+" "+apellidos );
		/* Agregue aquí lo que desee hacer con los datos del archivo. */
	}

	@DisplayName("Prueba con otro archivo .csv delimitado con $")
	@ParameterizedTest (name = "Libro={0} Autor={1}")
	@CsvFileSource(resources = "/books.csv", numLinesToSkip = 1, delimiter='$')
	void testWithOtherCsvFileSourceFromFile(final String nombre, final String autor) {
		if (showThread) System.out.print("Thread:"+Thread.currentThread().getId()+"-");
		System.out.println("Libro:"+nombre+" Autor:"+autor);
		/* Agregue aquí lo que desee hacer con los datos del archivo. */
	}
}
