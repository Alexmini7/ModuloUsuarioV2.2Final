import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VTNLogAdmin {
    private JTextField txtUserAdmin;
    private JPasswordField pswPassAdmin;
    private JButton btnIngresar;
    private JButton btnVolver;
    private JPanel vtnLogAdmin;
    private JFrame frame;
    private VTNModuloAdmin vtnModuloAdmin;
    private Controlador controlador;

    private void validarSoloLetras(JTextField campoTexto, String nombreCampo) {
        campoTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c))) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Ingrese solo letras para el " + nombreCampo + " de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public VTNLogAdmin(Controlador controlador) {
        this.controlador= controlador;
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUserAdmin.getText();
                String password = new String(pswPassAdmin.getPassword());
                if (usuario.equals("admin")&& password.equals("123")){
                    JOptionPane.showMessageDialog(null,"Credenciales correctas.\nBienvenido");
                    limpiarCampos();
                    controlador.mostrarModuloAdmin();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                }
            }
        });
        validarSoloLetras(txtUserAdmin, "nombre");

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarLogin();
            }
        });
    }
    private void limpiarCampos(){
        txtUserAdmin.setText("");
        pswPassAdmin.setText("");
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    public JPanel getPanel() {
        return vtnLogAdmin;
    }
}
