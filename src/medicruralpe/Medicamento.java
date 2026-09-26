package medicruralpe;

public class Medicamento extends RecursoMedico {

    private String presentacion;

    public Medicamento(String codigo, String nombre, int stockActual,
                       int stockMinimo, String presentacion) {

        super(codigo, nombre, stockActual, stockMinimo);
        setPresentacion(presentacion);
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {

        if (presentacion == null || presentacion.isBlank()) {
            throw new IllegalArgumentException(
                    "La presentacion es obligatoria."
            );
        }

        this.presentacion = presentacion;
    }
}