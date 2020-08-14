/* Ejemplos de uso de una suite que ejecuta solamente pruebas con un TAG.
 * Autor: Marcos Guerrero
 * Fecha: 12-08-2020
 * 
 * Requiere agregar a dependencias Maven
 * import static org.junit.jupiter.api.Assertions.*;
 */
package cl.set.junit5.Suites;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Ejecución en ambiente DEV")
@SelectPackages("cl.set.junit5") // Tome todas las clases en el paquete cl.set.JUnit5.
@IncludeEngines("junit-jupiter") // Use la máquina de ejecución de JUNit5.
public class AllTests{ // Los métodos son ignorados en ejecución.

}