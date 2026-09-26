package medicruralpe;

public abstract class RecursoMedico {

    private String codigo;
    private String nombre;
    private int stockActual;
    private int stockMinimo;

    public RecursoMedico(String codigo, String nombre, int stockActual, int stockMinimo) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio.");
        }

        this.codigo = codigo;
        setNombre(nombre);
        setStockActual(stockActual);
        setStockMinimo(stockMinimo);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStockActual() {
        return stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        this.nombre = nombre;
    }

    public void setStockActual(int stockActual) {
        if (stockActual < 0) {
            throw new IllegalArgumentException(
                    "El stock actual no puede ser negativo."
            );
        }

        this.stockActual = stockActual;
    }

    public void setStockMinimo(int stockMinimo) {
        if (stockMinimo <= 0) {
            throw new IllegalArgumentException(
                    "El stock minimo debe ser mayor que cero."
            );
        }

        this.stockMinimo = stockMinimo;
    }

    public boolean esCritico() {
        return stockActual <= stockMinimo;
    }

    public int calcularFaltante() {
        if (stockActual >= stockMinimo) {
            return 0;
        }

        return stockMinimo - stockActual;
    }
}