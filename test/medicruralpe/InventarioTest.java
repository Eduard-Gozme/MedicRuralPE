package medicruralpe;

import org.junit.Test;
import static org.junit.Assert.assertThrows;

public class InventarioTest {

    @Test
    public void debeRechazarCodigoDuplicado() {

        Inventario inventario = Inventario.getInstancia();

        RecursoMedico primerRecurso = new Medicamento(
                "TEST001",
                "Paracetamol prueba",
                10,
                20,
                "Tabletas"
        );

        RecursoMedico recursoDuplicado = new Medicamento(
                "TEST001",
                "Ibuprofeno prueba",
                15,
                25,
                "Tabletas"
        );
        
        primerRecurso.asignarResponsable("RESP_TEST_01");
        recursoDuplicado.asignarResponsable("RESP_TEST_02");
        
        inventario.registrarRecurso(primerRecurso);

        assertThrows(
                IllegalArgumentException.class,
                () -> inventario.registrarRecurso(recursoDuplicado)
        );
    }
}