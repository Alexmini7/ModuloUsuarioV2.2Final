public class Usuario {
    private String nombre;
    private String apellido;
    private int cedula;
    private String telefono;
    private int edad;

    public Usuario(String nombre, String apellido, int cedula, String telefono, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.edad= edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Usuario: " +
                " Nombre: " + nombre +
                ", Apellido: " + apellido +
                ", Cédula: " + cedula +
                ", Teléfono: " + telefono +
                ", Edad: " + edad;
    }
}
