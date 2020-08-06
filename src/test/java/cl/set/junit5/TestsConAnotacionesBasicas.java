package cl.set.junit5;
import org.junit.jupiter.api.*;

public class TestsConAnotacionesBasicas {
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
    @DisplayName("Suma 2+2 en DEV")
    void testCalcOne()
    {
        System.out.println("======TEST 1 ejecutado=======");
        Assertions.assertEquals( 4 , calculadora.Suma(2, 2));
    }
     
    @Tag("PROD")
    @Disabled
    @Test
    @DisplayName("Suma 2+4 en PROD")
    void testCalcTwo()
    {
        System.out.println("======TEST 2 ejecutado=======");
        Assertions.assertEquals( 6 , calculadora.Suma(2, 4));
    }

    @RepeatedTest(5)
    void sumaRepetida(TestInfo testInfo) {
        System.out.println(testInfo.getTestMethod().toString()+"-"+ testInfo.getDisplayName());
        Assertions.assertEquals(2, calculadora.Suma(1, 1), "1 + 1 debe ser 2");
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
