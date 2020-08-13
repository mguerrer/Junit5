/* Ejemplos de uso de Pruebas anidadas.   Prueba los métodos de la clase Stack.
 * Autor: Marcos Guerrero
 * Fecha: 12-08-2020
 * 
 * Requiere agregar a dependencias Maven
 * import org.junit.jupiter.api.Nested;
 */
package cl.set.junit5;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Un stack")
public class TestAnidados {
    
    Stack<Object> stack;

    @Test
    @DisplayName("se instancia con un nuevo Stack()")
    void isInstantiatedWithNew() {
        new Stack<>();
    }

    @Nested
    @DisplayName("Cuando es creada")
    class CuandoEsNuevo {

        // Antes de cada test crea un nuevo Stack.
        @BeforeEach
        void crearNuevoStack() {
            stack = new Stack<>();
        }

        @Test
        @DisplayName("está vacía")
        void estaVacio() {
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("Lanza excepción EmptyStackException cuando se intenta sacar")
        void lanzaExcepcionEnPopVacio() {
            assertThrows(EmptyStackException.class, stack::pop);
        }

        @Test
        @DisplayName("Lanza excepción  EmptyStackException cuando se hace peek")
        void lanzaExcepcionAlMirar() {
            assertThrows(EmptyStackException.class, stack::peek);
        }

        @Nested
        @DisplayName("Luego de poner un elemento")
        class LuegoDeAgregarElemento {

            String anElement = "un elemento";

            @BeforeEach
            void poneUnElemento() {
                stack.push(anElement);
            }

            @Test
            @DisplayName("ya no debe estar vacía")
            void noEstaVacio() {
                assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("saca el elemento cuando el elemento es tomado y ve que stack está vacío de nuevo")
            void retornaElementoAlTomarlo() {
                assertEquals(anElement, stack.pop());
                assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("retorna el elemento al mirar el elemento pero stack no debe estar vacío.")
            void retornaElementoAlMirarlo() {
                assertEquals(anElement, stack.peek());
                assertFalse(stack.isEmpty());
            }
        }
    }
}
