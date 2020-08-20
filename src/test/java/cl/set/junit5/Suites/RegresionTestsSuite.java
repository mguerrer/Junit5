package cl.set.junit5.Suites;

import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import cl.set.junit5.RegresionPagoTest;

@Tag("Suite")
@RunWith(JUnitPlatform.class)
@IncludeTags({"aceptacion","baseline"})
@IncludeEngines("junit-jupiter") // Use la máquina de ejecución de JUNit5.
@ExcludeTags("seguridad")
@SelectClasses(RegresionPagoTest.class)
public class RegresionTestsSuite {
    
}