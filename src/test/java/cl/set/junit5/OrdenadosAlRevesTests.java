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
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class OrdenadosAlRevesTests {
    @Test
    @Order(3)
    void pruebaUno() {
        System.out.println("======TEST 1 ejecutado=======");
    }

    @Test
    @Order(2)
    void pruebaDos() {
        System.out.println("======TEST 2 ejecutado=======");
    }

    @Test
    @Order(1)
    void pruebaTres() {
        System.out.println("======TEST 3 ejecutado=======");
    }
    
}