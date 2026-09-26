package medicruralpe;

public class InsumoMedico extends RecursoMedico {

    private String unidadMedida;

    public InsumoMedico(String codigo, String nombre, int stockActual,
                        int stockMinimo, String unidadMedida) {

        super(codigo, nombre, stockActual, stockMinimo);
        setUnidadMedida(unidadMedida);
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {

        if (unidadMedida == null || unidadMedida.isBlank()) {
            throw new IllegalArgumentException(
                    "La unidad de medida es obligatoria."
            );
        }

        this.unidadMedida = unidadMedida;
    }
}