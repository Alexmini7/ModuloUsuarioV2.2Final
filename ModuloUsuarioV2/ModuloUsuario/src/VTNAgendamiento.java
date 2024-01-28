import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VTNAgendamiento {

    private JPanel vtnAgendamiento;
    private JTextField txtCedula;
    private JLabel lbInformacion;
    private JButton btnBuscar;
    private JTextField txtPlaca;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtColor;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JButton btnAgendar;
    private JButton btnVolver;
    private JSpinner spnPrioridad;
    private JButton btnLista;
    private JFrame frame;
    private Controlador controlador;
    private ListaUsuario usuariosRegistrados;
    private GestorCitas gestorCitas;

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

    public VTNAgendamiento(Controlador controlador, ListaUsuario usuariosRegistrados, GestorCitas gestorCitas) {
        this.controlador=controlador;
        this.usuariosRegistrados=usuariosRegistrados;
        this.gestorCitas=gestorCitas;


        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cedulaBuscada;
                try{
                    cedulaBuscada = Integer.parseInt(txtCedula.getText());

                }catch (NumberFormatException ex){
                    lbInformacion.setText("Campo vacio");
                    return;
                }

                boolean encontrado = false;
                for (Usuario usuario : usuariosRegistrados.getUsuariosRegistrados()){
                    if (usuario.getCedula()==cedulaBuscada){
                        lbInformacion.setText(usuario.toString());
                        Usuario usuario1 =usuario;
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado){
                    lbInformacion.setText("Usuario no encontrado");
                }
            }
        });
        validarSoloNumeros(txtCedula, "cedula");

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarLogin();
            }
        });
        btnAgendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa =txtPlaca.getText();
                String marca=txtMarca.getText();
                String modelo=txtModelo.getText();
                String color= txtColor.getText();
                int prioridad= (int) spnPrioridad.getValue();
                Vehiculo vehiculo=new Vehiculo(marca,placa,color,modelo);
                String fechaTexto = txtFecha.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                try {
                    // Intentar parsear la fecha
                    LocalDate fecha = LocalDate.parse(fechaTexto, formatter);

                    // Verificar que la fecha no es anterior a la fecha actual
                    if (fecha.isBefore(LocalDate.now())) {
                        JOptionPane.showMessageDialog(null, "La fecha no puede ser anterior a la fecha actual.", "Error en la fecha", JOptionPane.ERROR_MESSAGE);
                        return; // Detener la ejecución del método aquí
                    }
                    int cedulaBuscada;
                    try {
                        cedulaBuscada = Integer.parseInt(txtCedula.getText());

                    } catch (NumberFormatException ex) {
                        lbInformacion.setText("Campo vacio");
                        return;
                    }

                    Usuario usuarioN = null;
                    for (Usuario usuario : usuariosRegistrados.getUsuariosRegistrados()) {
                        if (usuario.getCedula() == cedulaBuscada) {
                            usuarioN = usuario;
                        }
                    }

                    // Si llegamos aquí, la fecha es válida y podemos continuar con la lógica de agendar
                    gestorCitas.agregarCita(usuarioN, vehiculo, new Fecha(fecha.getDayOfMonth(),fecha.getMonthValue(), fecha.getYear() ),prioridad);
                    // Suponiendo que tienes un método para crear una nueva cita...
                    // crearCita(new Fecha(fecha.getDayOfMonth(), fecha.getMonthValue(), fecha.getYear()), placa, marca, modelo, color);
                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Cita agendada correctamente.", "Cita Agendada", JOptionPane.INFORMATION_MESSAGE);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use el formato dd-MM-yyyy.", "Error en la fecha", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,  ex.getMessage());
                }



            }
        });
        btnLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Suponiendo que GestorCitas tiene un método que devuelve un String con todas las citas ordenadas
                String listaCitas = gestorCitas.metodoPrueba();

                // Comprobar si la lista de citas está vacía o no
                if (listaCitas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay citas agendadas.", "Lista de Citas", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, listaCitas, "Lista de Citas Agendadas", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
    }



    
    public JPanel getPanel() {
        return vtnAgendamiento;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
