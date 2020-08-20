/* Ejemplos de uso de una suite que ejecuta todas las pruebas en un paquete.
 * Autor: Marcos Guerrero
 * Fecha: 12-08-2020
 * 
 */
package cl.set.junit5.Suites;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;

@Tag("Suite")
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Ejecución paralela de todas las pruebas en paralelo.")
@SelectPackages("cl.set.junit5") // Tome todas las clases en el paquete cl.set.JUnit5.
@IncludeEngines("junit-jupiter") // Use la máquina de ejecución de JUNit5.
@Execution(ExecutionMode.CONCURRENT) // Indica ejecución concurrente.
public class ParallelTestsSuite{ 
}