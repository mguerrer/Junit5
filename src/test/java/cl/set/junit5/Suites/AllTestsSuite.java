/* Ejemplos de uso de una suite que ejecuta todas las pruebas en un paquete.
 * Autor: Marcos Guerrero
 * Fecha: 12-08-2020
 * Nota: A la fecha Suites solamente se pueden correr usando runner de JUNit4. 
 * Ver estado en https://github.com/junit-team/junit5/pull/2295
 */
package cl.set.junit5.Suites;

import org.junit.jupiter.api.Tag;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;

@Tag("Suite")
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Ejecución de todas las pruebas")
@SelectPackages("cl.set.junit5") // Tome todas las clases en el paquete cl.set.JUnit5.
@IncludeEngines("junit-jupiter") // Use la máquina de ejecución de JUNit5.
public class AllTestsSuite{ // Los métodos son ignorados en ejecución.

}