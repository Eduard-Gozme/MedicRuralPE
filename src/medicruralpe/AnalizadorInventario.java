package medicruralpe;

import java.util.List;

public class AnalizadorInventario {

    public List<RecursoMedico> obtenerRecursosCriticos(List<RecursoMedico> recursos) {
        return recursos.stream()
                .filter(recurso -> recurso.esCritico())
                .toList();
    }

    public List<RecursoMedico> priorizarRecursosCriticos(List<RecursoMedico> recursos) {
        return recursos.stream()
                .filter(recurso -> recurso.esCritico())
                .sorted((recurso1, recurso2) ->
                    Integer.compare(
                        recurso2.calcularFaltante(),
                        recurso1.calcularFaltante()
                    )
                )
                .toList();
    }

    public int calcularTotalUnidades(List<RecursoMedico> recursos) {
        return recursos.stream()
                .map(recurso -> recurso.getStockActual())
                .reduce(0, (total, stock) -> total + stock);
    }

    public long contarRecursosCriticos(List<RecursoMedico> recursos) {
        return recursos.stream()
                .filter(recurso -> recurso.esCritico())
                .count();
    }
}