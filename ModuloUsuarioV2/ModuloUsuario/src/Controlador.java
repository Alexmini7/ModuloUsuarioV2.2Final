import javax.swing.*;
import java.awt.*;

public class Controlador {
    private ListaUsuario usuariosRegistrados = new ListaUsuario();
    private GestorCitas gestorCitas=new GestorCitas(usuariosRegistrados);
    private VTNLogAdmin vtnLogAdmin;
    private VTNModuloAdmin vtnModuloAdmin;
    private VTNLogin vtnLogin;
    private VTNRegistroUsuario vtnRegistroUsuario;
    private VTNAgendamiento vtnAgendamiento;
    private JFrame frameLogAdmin;
    private JFrame frameModuloAdmin;
    private JFrame frameLogin;
    private JFrame frameRegistroUsuario;
    private JFrame frameAgendamiento;

    public Controlador(){

        usuariosRegistrados.agregar(new Usuario("Matias","Robayo",1,"091",19));
        usuariosRegistrados.agregar(new Usuario("Lenin","Cando",2,"092",20));
        usuariosRegistrados.agregar(new Usuario("Paul","Robalino",3,"093",21));
        usuariosRegistrados.agregar(new Usuario("Mateo","Coronel",4,"094",22));
        usuariosRegistrados.agregar(new Usuario("Alex","Logacho",5,"095",23));

        vtnLogin = new VTNLogin(this);
        vtnModuloAdmin = new VTNModuloAdmin(this, usuariosRegistrados,gestorCitas);
        vtnLogAdmin = new VTNLogAdmin(this);
        vtnRegistroUsuario = new VTNRegistroUsuario(this, usuariosRegistrados);
        vtnAgendamiento = new VTNAgendamiento(this, usuariosRegistrados,gestorCitas);

        frameLogin = new JFrame("Login General");
        frameLogin.setContentPane(vtnLogin.getPanel());
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setPreferredSize(new Dimension(400, 400));
        frameLogin.setResizable(false);
        frameLogin.pack();
        frameLogin.setLocationRelativeTo(null);

        frameLogAdmin = new JFrame("Login Administrador");
        frameLogAdmin.setContentPane(vtnLogAdmin.getPanel());
        frameLogAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogAdmin.setPreferredSize(new Dimension(400, 400));
        frameLogAdmin.setResizable(false);
        frameLogAdmin.pack();
        frameLogAdmin.setLocationRelativeTo(null);

        frameModuloAdmin = new JFrame("Módulo Administrador");
        frameModuloAdmin.setContentPane(vtnModuloAdmin.getPanel());
        frameModuloAdmin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameModuloAdmin.setPreferredSize(new Dimension(1000, 1000));
        frameModuloAdmin.setResizable(false);
        frameModuloAdmin.pack();
        frameModuloAdmin.setLocationRelativeTo(null);

        frameRegistroUsuario = new JFrame("Registro de Usuarios");
        frameRegistroUsuario.setContentPane(vtnRegistroUsuario.getPanel());
        frameRegistroUsuario.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameRegistroUsuario.setPreferredSize(new Dimension(400, 400));
        frameRegistroUsuario.setResizable(false);
        frameRegistroUsuario.pack();
        frameRegistroUsuario.setLocationRelativeTo(null);

        frameAgendamiento = new JFrame("Agendamiento de turnos");
        frameAgendamiento.setContentPane(vtnAgendamiento.getPanel());
        frameAgendamiento.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameAgendamiento.setPreferredSize(new Dimension(400, 600));
        frameAgendamiento.setResizable(false);
        frameAgendamiento.pack();
        frameAgendamiento.setLocationRelativeTo(null);

        vtnLogAdmin.setFrame(frameLogAdmin);
        vtnModuloAdmin.setFrame(frameModuloAdmin);
        vtnLogin.setFrame(frameLogin);
        vtnRegistroUsuario.setFrame(frameRegistroUsuario);
        vtnAgendamiento.setFrame(frameAgendamiento);
    }
    public void mostrarLogAdmin() {
        frameLogin.setVisible(false);
        frameModuloAdmin.setVisible(false);
        frameLogAdmin.setVisible(true);
    }

    public void mostrarModuloAdmin() {
        frameLogAdmin.setVisible(false);
        frameModuloAdmin.setVisible(true);
    }

    public void mostrarLogin(){
        frameRegistroUsuario.setVisible(false);
        frameLogAdmin.setVisible(false);
        frameAgendamiento.setVisible(false);
        frameLogin.setVisible(true);
    }

    public void mostrarRegistroUsuario(){
        frameLogin.setVisible(false);
        frameRegistroUsuario.setVisible(true);
    }

    public void mostrarAgendamiento(){
        frameLogin.setVisible(false);
        frameAgendamiento.setVisible(true);
    }

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        controlador.mostrarLogin();
    }
}
