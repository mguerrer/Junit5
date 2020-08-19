package cl.set.junit5;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;

@Tag("regresion")
@ExcludeTags("seguridad") // No tiene efecto
@Execution(ExecutionMode.CONCURRENT) 
public class RegresionPagoTest {
  @Tag("aceptacion")
  @Tag("baseline")
  @Test 
  void payment_success_test() {
    System.out.println("prueba_pago_exitoso(): " + Thread.currentThread().getName());
  }
  @Tag("aceptacion")
  @Test 
  void payment_decline_test() {
    System.out.println("prueba_pago_declinado(): " + Thread.currentThread().getName());
  }
  @Tag("seguridad")
  @Test 
  void invalid_country_test() {
    System.out.println("prueba_pais_invalido(): " + Thread.currentThread().getName());
  }
}
