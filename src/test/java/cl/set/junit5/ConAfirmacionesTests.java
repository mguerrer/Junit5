/* Ejemplos de uso de Asserts.
 * Autor: Marcos Guerrero
 * Fecha: 12-08-2020
 * 
 * Requiere agregar a dependencias Maven
 * import static org.junit.jupiter.api.Assertions.*;
 */
package cl.set.junit5;

import java.time.Duration;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ConAfirmacionesTests {
    private final Calculadora Calculadora = new Calculadora();
    private final Persona person = new Persona("Jane", "Doe");

    @Test
    void afirmacionesNormales() {
        assertEquals(2, Calculadora.Suma(1, 1));
        assertEquals(4, Calculadora.Multiplica(2, 2),
                "El mensaje de error opcional es el último parámetro");
        assertTrue('a' < 'b', () -> "Los mensajes de aserción se pueden evaluar perezosamente -- "
                + "para evitar la construcción de mensajes complejos innecesariamente.");
    }

    @Test
    void afirmacionesAgrupadas() {
        // En una aserción agrupada se ejecutan todas las aserciones, 
        // y todos las fallas serán reportadas juntas.
        assertAll("properties",
            () -> assertEquals("Jane", person.nombre()),
            () -> assertEquals("Doe", person.apellido())
        );
    }

    @Test
    void afirmacionesDependientes() {
        // Dentro de un bloque de código, si una afirmación falla
        // se omitirá el código subsiguiente en el mismo bloque.
        assertAll("properties",
            () -> {
                String firstName = person.nombre();
                assertNotNull(firstName,"Nombre es null");

                // Solo se ejecuta si la aserción anterior es válida.
                assertAll("Nombre",
                    () -> assertTrue(firstName.startsWith("J")),
                    () -> assertTrue(firstName.endsWith("e"))
                );
            },
            () -> {
                // Afirmación agrupada, procesada de forma independiente
                // de los resultados de las afirmaciones de nombre
                String lastName = person.apellido();
                assertNotNull(lastName);

                // Ejecuta sólo si la anterior es válida.
                assertAll("Apellido",
                    () -> assertTrue(lastName.startsWith("D")),
                    () -> assertTrue(lastName.endsWith("e"))
                );
            }
        );
    }

    @Test
    void afirmacionesDeExcepciones() {
        // Útiles para evaluar condiciones de borde o fallas de métodos.
        Exception exception = assertThrows(ArithmeticException.class, () ->
            Calculadora.Divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void afirmacionesDeTiempoMaximo() {
        // Útiles para pruebas de performance.
        // La siguiente afirmación falla.
        assertTimeout(Duration.ofMillis(10), () -> {
            // Demora poco menos de los 10 milisegundos.  Esto puede fallar en algunas condiciones.
            Thread.sleep(8);
        });
    }

    @Test
    void afirmacionesDeResultadoYTiempo() {
        // La siguiente aserción se realiza correctamente y devuelve el objeto proporcionado.
        String Resultado = assertTimeout(ofMinutes(2), () -> {
            return "un resultado cualquiera";
        });
        assertEquals("un resultado cualquiera", Resultado);
        // En esta versión se invoca una referencia a un método y se retorna el valor devuelto por él.
        String saludo = assertTimeout(ofMinutes(2), ConAfirmacionesTests::greeting);
        assertEquals("Saludos!", saludo);
    }

    private static String greeting() {
        return "Saludos!";
    }

}