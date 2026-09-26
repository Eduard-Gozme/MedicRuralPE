package medicruralpe;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class RecursoMedicoTest {

    @Test
    public void debeDetectarRecursoConStockCritico() {

        RecursoMedico medicamento = new Medicamento(
                "MED001",
                "Paracetamol",
                10,
                20,
                "Tabletas"
        );

        boolean resultado = medicamento.esCritico();

        assertTrue(resultado);
    }
}