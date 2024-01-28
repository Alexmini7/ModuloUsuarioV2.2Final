import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VTNRegistroUsuario {
    private JPanel vtnModuloUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCedula;
    private JTextField txtTelefono;
    private JTextField txtEdad;
    private JButton btnRegistrar;
    private JButton btnVolver;
    private ListaUsuario usuariosRegistrados;
    private JFrame frame;
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
    private void validarSoloNumeros(JTextField campoTexto, String nombreCampo) {
        campoTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || Character.isISOControl(c))) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Ingrese solo números para " + nombreCampo + ".", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public VTNRegistroUsuario(Controlador controlador, ListaUsuario usuariosRegistrados) {
        this.controlador= controlador;
        this.usuariosRegistrados = usuariosRegistrados;
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String cedulaTexto = txtCedula.getText();
                String telefono = txtTelefono.getText();
                String edadTexto = txtEdad.getText();

                if (nombre.isEmpty() || apellido.isEmpty() || cedulaTexto.isEmpty() ||
                        telefono.isEmpty() || edadTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                    return;
                }

                try {
                    int cedula = Integer.parseInt(cedulaTexto);
                    int edad = Integer.parseInt(edadTexto);

                    Usuario usuario = new Usuario(nombre, apellido, cedula, telefono, edad);
                    usuariosRegistrados.ingresoNuevoUsuario(usuario);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Cédula y edad deben ser números válidos");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    limpiarCampos();
                }
            }
        });
        validarSoloLetras(txtNombre, "nombre");
        validarSoloLetras(txtApellido, "apellido");
        validarSoloNumeros(txtCedula, "cedula");
        validarSoloNumeros(txtEdad, "edad");
        validarSoloNumeros(txtTelefono, "telefono");
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarLogin();
            }
        });
    }
    public void limpiarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtTelefono.setText("");
        txtCedula.setText("");
    }
    public JPanel getPanel() {
        return vtnModuloUsuario;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
