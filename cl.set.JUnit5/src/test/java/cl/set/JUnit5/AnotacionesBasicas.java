package cl.set.JUnit5;
import org.junit.jupiter.api.*;
import cl.set.Calculadora.Core;

public class AnotacionesBasicas {
    Core calculadora = new Core();
    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll ejecutado");
    }
     
    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach ejecutado");
    }
     
    @Tag("DEV")
    @Test
    void testCalcOne()
    {
        System.out.println("======TEST 1 ejecutado=======");
        Assertions.assertEquals( 4 , calculadora.Suma(2, 2));
    }
     
    @Tag("PROD")
    @Disabled
    @Test
    void testCalcTwo()
    {
        System.out.println("======TEST 2 ejecutado=======");
        Assertions.assertEquals( 6 , calculadora.Suma(2, 4));
    }
     
    @AfterEach
    void tearThis(){
        System.out.println("@AfterEach ejecutado");
    }
     
    @AfterAll
    static void tear(){
        System.out.println("@AfterAll ejecutado");
    }
}
