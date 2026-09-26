package medicruralpe;

import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class ProtectorDatosTest {

    @Test
    public void debeProtegerIdentificadorSinGuardarTextoPlano() {

        RecursoMedico medicamento = new Medicamento(
                "MED100",
                "Medicamento de prueba",
                10,
                20,
                "Tabletas"
        );

        String identificadorOriginal = "RESP001";

        medicamento.asignarResponsable(identificadorOriginal);

        String identificadorProtegido =
                medicamento.getResponsableProtegido();

        assertNotNull(identificadorProtegido);

        assertNotEquals(
                identificadorOriginal,
                identificadorProtegido
        );
    }
}