public class Pieza {
    private String nombre;
    private int cantidad;
    private Fecha fechaAdquisicion;
    private String numeroSerie;
    private String proveedor;
    private double precio; // Añadido el precio de la pieza

    public Pieza(String nombre, int cantidad, Fecha fechaAdquisicion, String numeroSerie, String proveedor, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaAdquisicion = fechaAdquisicion;
        this.numeroSerie = numeroSerie;
        this.proveedor = proveedor;
        this.precio = precio; // Inicializar el precio en el constructor
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Fecha getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Fecha fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Pieza{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", fechaAdquisicion=" + fechaAdquisicion +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", precio=" + precio +
                '}';
    }





}
