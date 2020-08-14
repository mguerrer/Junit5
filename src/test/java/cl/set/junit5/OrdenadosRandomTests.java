/* Ejemplos de uso de pruebas con orden numerado.
 * Autor: Marcos Guerrero
 * Fecha: 12-08-2020
 * 
 * Requiere agregar a dependencias Maven
 *  import org.junit.jupiter.api.TestMethodOrder;
 *  import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
 *  import org.junit.jupiter.api.Order
 */
package cl.set.junit5;
import org.junit.jupiter.api.MethodOrderer.Random;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// Aquí definimos uso de ordenador random.
@TestMethodOrder(Random.class)
public class OrdenadosRandomTests {
    @Test
    @Tag("DEV")
    void pruebaUno() {
        System.out.println("======Prueba 1 ejecutada=======");
    }

    @Test
    void pruebaDos() {
        System.out.println("======Prueba 2 ejecutada=======");
    }

    @Test
    void pruebaTres() {
        System.out.println("======Prueba 3 ejecutada=======");
    }
    
}