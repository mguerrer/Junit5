/* Ejemplos de uso de una suite que ejecuta solamente pruebas con un TAG.
 * Autor: Marcos Guerrero
 * Fecha: 12-08-2020
 * 
 * Requiere agregar a dependencias Maven
 * import static org.junit.jupiter.api.Assertions.*;
 */
package cl.set.junit5.Suites;

import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.TestReporter;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Ejecución en ambiente DEV")
@SelectPackages("cl.set.junit5") // Tome todas las clases en el paquete cl.set.JUnit5.
@IncludeTags("DEV") // Incluya todas las pruebas con el TAG DEV
@IncludeEngines("junit-jupiter") // Use la máquina de ejecución de JUNit5.
@Timeout(10) // Establece tiempo máximo de ejecución.
public class DevTests{ // Los métodos son ignorados en ejecución.
    DevTests( final TestInfo testInfo, final TestReporter testReporter) {
        System.out.println(testInfo.getDisplayName());
        testReporter.publishEntry(testInfo.getDisplayName());
    }
}