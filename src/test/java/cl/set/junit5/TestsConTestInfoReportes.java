/* Ejemplos de uso de los parámetros de contexto de una prueba para enriquecer reportes o depurar.
 * Autor: Marcos Guerrero
 * Fecha: 13-08-2020
 * 
 * Requiere agregar a dependencias Maven
 * import org.junit.jupiter.api.TestInfo;
 */
package cl.set.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Demuestra TestInfo, TestReporter")

public class TestsConTestInfoReportes {
    TestsConTestInfoReportes(TestInfo testInfo, TestReporter testReporter) {
        System.out.println("Constructor de:"+testInfo.getDisplayName());
        testReporter.publishEntry("REPORTE->Se inicia prueba de clase:"+testInfo.getDisplayName());
        assertEquals("Demuestra TestInfo, TestReporter", testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        String displayName = testInfo.getDisplayName();
        System.out.println("Ejecutando Before de:"+displayName);
        testReporter.publishEntry("Ejecutando Before de:"+displayName);
        assertTrue(displayName.equals("TEST 1") || displayName.equals("test2(TestInfo)"));
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("mi-tag")
    void test1(TestInfo testInfo) {
        System.out.println("Ejecutando:"+testInfo.getDisplayName());
        assertEquals("TEST 1", testInfo.getDisplayName());
        assertTrue(testInfo.getTags().contains("mi-tag"));
    }
    @Test
    void test2(TestInfo testInfo) {
        System.out.println("Ejecutando:"+testInfo.getDisplayName());
    }

}