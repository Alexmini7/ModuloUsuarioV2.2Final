public class Cita {
    private Usuario usuario;
    private Fecha fecha;

    private int prioridad;
    private String descripcion;
    private float costo;
    private Vehiculo vehiculo;


    public Cita(Usuario usuario, Fecha fecha, int prioridad, String descripcion, float costo, Vehiculo vehiculo) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.costo = costo;
        this.vehiculo = vehiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public int getPrioridad() {
        return prioridad;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return  prioridad+" "
                +usuario.getNombre()+" " +
                vehiculo.getPlaca()+" "+
                vehiculo.getMarca()+" "+
                vehiculo.getModelo()+" "+
                fecha.toString();
    }
}
