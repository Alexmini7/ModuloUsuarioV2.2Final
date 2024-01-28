import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VTNLogin {
    private JPanel vtnLogin;
    private JButton btnAdmin;
    private JButton btnAgendar;
    private JButton btnRegistro;
    private JButton btnVerCitas;
    private JFrame frame;
    private Controlador controlador;

    public VTNLogin(Controlador controlador) {
        this.controlador = controlador;
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarLogAdmin();
            }
        });
        btnAgendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarAgendamiento();
            }
        });
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarRegistroUsuario();
            }
        });

    }
    public JPanel getPanel() {
        return vtnLogin;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
