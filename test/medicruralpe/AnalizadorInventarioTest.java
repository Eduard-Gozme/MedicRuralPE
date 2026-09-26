package medicruralpe;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AnalizadorInventarioTest {

    @Test
    public void debePriorizarRecursoConMayorFaltante() {

        RecursoMedico paracetamol = new Medicamento(
                "MED001",
                "Paracetamol",
                8,
                20,
                "Tabletas"
        );

        RecursoMedico guantes = new InsumoMedico(
                "INS001",
                "Guantes descartables",
                5,
                30,
                "Caja"
        );

        List<RecursoMedico> recursos = Arrays.asList(
                paracetamol,
                guantes
        );

        AnalizadorInventario analizador =
                new AnalizadorInventario();

        List<RecursoMedico> priorizados =
                analizador.priorizarRecursosCriticos(recursos);

        assertEquals(
                "INS001",
                priorizados.get(0).getCodigo()
        );

        assertEquals(
                "MED001",
                priorizados.get(1).getCodigo()
        );
    }
}