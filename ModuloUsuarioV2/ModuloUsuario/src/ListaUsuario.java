import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListaUsuario {
    List<Usuario> usuariosRegistrados;

    public ListaUsuario(){
        usuariosRegistrados=new ArrayList<Usuario>();
    }
    public void agregar(Usuario dato){
        usuariosRegistrados.add(dato);
    }
    public void datosQuemados(){
        agregar(new Usuario("Matias","Robayo",1,"091",19));
        agregar(new Usuario("Lenin","Cando",2,"092",20));
        agregar(new Usuario("Paul","Robalino",3,"093",21));
        agregar(new Usuario("Mateo","Coronel",4,"094",22));
        agregar(new Usuario("Alex","Logacho",5,"095",23));
    }

    public boolean actualizar(Usuario dato){
        for (Usuario u: usuariosRegistrados){
            if(u.getCedula()== dato.getCedula()){
                u.setNombre(dato.getNombre());
                u.setApellido(dato.getApellido());
                u.setTelefono(dato.getTelefono());
                u.setEdad(dato.getEdad());
                return true;
            }
        }
        return false;
    }

    public List<Usuario> getUsuariosRegistrados(){
        return usuariosRegistrados;
    }

    public void ingresoNuevoUsuario(Usuario newUser) throws Exception{
        if (newUser.getNombre().isEmpty() || newUser.getApellido().isEmpty() || newUser.getCedula()==0
                || newUser.getTelefono().isEmpty() || newUser.getEdad()==0){
            throw new Exception("Llene todos los campos para registrarse.");
        }
        if (!cedulaUnica(newUser.getCedula())){
            throw new Exception("Ya existe un usuario registrado con esa cédula.");
        }
        if (!mayorEdad(newUser.getEdad())){
            throw new Exception("Debe ser mayor de edad, ingrese una edad adecuada");
        }
        usuariosRegistrados.add(newUser);
        throw new Exception("Usuario se ha registrado exitosamente.");
    }

    private boolean cedulaUnica(int cedula){
        for (Usuario u: usuariosRegistrados){
            if (u.getCedula()==cedula){
                return false;
            }
        }
        return true;
    }
    private boolean mayorEdad(int edad){
        if (edad>18 && edad<100){
            return true;
        }
        return false;
    }
    public void eliminarUsuario(int cedula){
        for (int i=0 ; i<usuariosRegistrados.size(); i++){
            Usuario usuario = usuariosRegistrados.get(i);

            if (usuario.getCedula()==cedula){
                usuariosRegistrados.remove(i);
            }
            break;
        }
    }
}
