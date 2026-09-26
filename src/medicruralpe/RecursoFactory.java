package medicruralpe;

public class RecursoFactory {

    public static RecursoMedico crearMedicamento(
            String codigo,
            String nombre,
            int stockActual,
            int stockMinimo,
            String presentacion) {

        return new Medicamento(
                codigo,
                nombre,
                stockActual,
                stockMinimo,
                presentacion
        );
    }

    public static RecursoMedico crearInsumo(
            String codigo,
            String nombre,
            int stockActual,
            int stockMinimo,
            String unidadMedida) {

        return new InsumoMedico(
                codigo,
                nombre,
                stockActual,
                stockMinimo,
                unidadMedida
        );
    }
}
