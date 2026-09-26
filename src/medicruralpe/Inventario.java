package medicruralpe;

import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private static Inventario instancia;
    private List<RecursoMedico> recursos;

    private Inventario() {
        recursos = new ArrayList<>();
    }

    public static Inventario getInstancia() {

        if (instancia == null) {
            instancia = new Inventario();
        }

        return instancia;
    }

    public void registrarRecurso(RecursoMedico recurso) {

        if (recurso == null) {
            throw new IllegalArgumentException(
                    "El recurso no puede ser nulo."
            );
        }

        if (buscarPorCodigo(recurso.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un recurso con el codigo "
                    + recurso.getCodigo()
            );
        }

        recursos.add(recurso);
    }

    public RecursoMedico buscarPorCodigo(String codigo) {

        for (RecursoMedico recurso : recursos) {

            if (recurso.getCodigo().equalsIgnoreCase(codigo)) {
                return recurso;
            }
        }

        return null;
    }

    public void actualizarStock(String codigo, int nuevoStock) {

        if (nuevoStock < 0) {
            throw new IllegalArgumentException(
                    "El nuevo stock no puede ser negativo."
            );
        }

        RecursoMedico recurso = buscarPorCodigo(codigo);

        if (recurso == null) {
            throw new IllegalArgumentException(
                    "No existe un recurso con el codigo " + codigo
            );
        }

        recurso.setStockActual(nuevoStock);
    }

    public List<RecursoMedico> obtenerRecursos() {
        return new ArrayList<>(recursos);
    }
}