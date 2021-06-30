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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.CsvFileSource;

@Tag("Integracion")
public class ParametrizadosTestsIT {
	@Test
	@DisplayName("1 + 1 = 2")
	void SumaDosNúmeros() {
		final Calculadora calculadora = new Calculadora();
		assertEquals(2, calculadora.Suma(1, 1), ()-> "1 + 1 debe ser igual a 2");
	}

	@DisplayName("Prueba con fuente fija")
	@Tag("Integracion")
	@ParameterizedTest(name = "{0} + {1} = {2}")
	@CsvSource({ "0,    1,   1", "1,    2,   3", "49,  51, 100", "1,  100, 101" })
	void SumaListaDeNumeros(final int primer, final int segundo, final int resultadoEsperado) {
		final Calculadora calculadora = new Calculadora();
		System.out.println(primer + " + " + segundo + " debe ser igual a " + resultadoEsperado);
		assertEquals(resultadoEsperado, calculadora.Suma(primer, segundo),
				() -> primer + " + " + segundo + " debe ser igual a " + resultadoEsperado);
	}

	@DisplayName("Prueba con archivos .csv")
	@ParameterizedTest (name = "Usuario {0} {1}")
	@CsvFileSource(resources = "/usuarios.csv", numLinesToSkip = 1)
	void testWithCsvFileSourceFromFile(final String nombre, final String apellidos) {
		System.out.println("Usuario:"+nombre+" "+apellidos );
		/* Agregue aquí lo que desee hacer con los datos del archivo. */
	}

	@DisplayName("Prueba con otro archivo .csv delimitado con $")
	@ParameterizedTest (name = "Libro={0} Autor={1}")
	@CsvFileSource(resources = "/books.csv", numLinesToSkip = 1, delimiter='$')
	void testWithOtherCsvFileSourceFromFile(final String nombre, final String autor) {
		System.out.println("Libro:"+nombre+" Autor:"+autor);
		/* Agregue aquí lo que desee hacer con los datos del archivo. */
	}
	@BeforeEach void MiThread(){
		System.out.println("Thread="+java.lang.Thread.activeCount());
	}
	public static Object[] strings() {
		return new Object[][] { //
				{ "str", true }, //
				{ "str", true }, //
				{ "str", false }, //
				{ "exactly 5 objects", false }, //
				{ "at least 5 objects", false }, //
				{ "\"more than\" 5 objects", false },//
		};
	}
	@ParameterizedTest
	@MethodSource("strings")
	void stringsMethodSource(String str, boolean trueFalse) {
		System.out.println("Datos="+str+"  Boolean="+trueFalse);
	}
}
