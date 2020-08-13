/* Ejemplos de uso de una suite.
 * Autor: Marcos Guerrero
 * Fecha: 12-08-2020
 * 
 * Requiere agregar a dependencias Maven
 * import static org.junit.jupiter.api.Assertions.*;
 */
package cl.set.junit5.Suites;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

import cl.set.junit5.AnidadosTests;
import cl.set.junit5.ConAnotacionesBasicasTests;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Ejecución en ambiente DEV")
@SelectClasses({ConAnotacionesBasicasTests.class, AnidadosTests.class})
public class DevTests {
    DevTests(){

    }
}