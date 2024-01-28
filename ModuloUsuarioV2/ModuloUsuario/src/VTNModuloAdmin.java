import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class VTNModuloAdmin {
    private JPanel vtnModuloAdmin;
    private JTabbedPane tabbedPane1;
    private JList lstUsuarios;
    private JButton btnListar;
    private JButton btnVolver;
    private JTextField txtANombre;
    private JTextField txtAApellido;
    private JTextField txtATelefono;
    private JTextField txtAEdad;
    private JButton btnActualizar;
    private JTextField txtACedula;
    private JButton btnEliminar;
    private JPanel vtnAgendamiento;
    private JButton btnBuscar;
    private JTextField txtPlaca;
    private JTextField txtModelo;
    private JTextField txtMarca;
    private JTextField txtColor;
    private JTextField txtFecha;
    private JButton btnDespachar;
    private JTextArea txtADescripcion;
    private JSpinner spCosto;
    private JList lstCitas;
    private JTextArea txtAprueva;
    private JButton btnListaPrueba;
    private JButton btnEliminarCita;
    private JButton btnListar2;
    private JList lstCitas2;
    private JTextField txtPlaca2;
    private JTextField txtMarca2;
    private JTextField txtModelo2;
    private JTextField txtColor2;
    private JSpinner spnPrioridad2;
    private JTextField txtFecha2;
    private JButton btnActualizar2;
    private JTextField txtFecha3;
    private JTextField txtPlacaN;
    private JFrame frame;
    private ListaUsuario usuariosRegistrados;
    private Controlador controlador;
    private GestorCitas gestorCitas;
    private Usuario us1,us2;
    private Fecha f1, f2, f3;
    private int prioridadN;

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

    private void llenarJlist(){
        List<Usuario> listado = usuariosRegistrados.getUsuariosRegistrados();
        DefaultListModel dlm = new DefaultListModel();
        for (Usuario u: listado){
            dlm.addElement(u.toString());
        }
        lstUsuarios.setModel(dlm);
    }
    private void llenarJlist1(){
        gestorCitas.ordenarCitasPorPrioridad();
        List<Cita> listado = gestorCitas.getListaCitas();
        DefaultListModel dlm = new DefaultListModel();
        for (Cita u: listado){
            dlm.addElement(u.toString());
        }
        lstCitas.setModel(dlm);
    }
    private void llenarJlist2(){
        gestorCitas.ordenarCitasPorPrioridad();
        List<Cita> listado = gestorCitas.getListaCitas();
        DefaultListModel dlm = new DefaultListModel();
        for (Cita u: listado){
            dlm.addElement(u.toString());
        }
        lstCitas2.setModel(dlm);
    }
    public VTNModuloAdmin(Controlador controlador, ListaUsuario usuariosRegistrados,GestorCitas gestorCitas ) {
        this.controlador = controlador;
        this.usuariosRegistrados = usuariosRegistrados;
        this.gestorCitas=gestorCitas;


        lstUsuarios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (lstUsuarios.getSelectedIndex()!=-1){
                    int indice = lstUsuarios.getSelectedIndex();
                    Usuario u = usuariosRegistrados.getUsuariosRegistrados().get(indice);
                    txtACedula.setText(""+u.getCedula());
                    txtANombre.setText(""+u.getNombre());
                    txtAApellido.setText(""+u.getApellido());
                    txtATelefono.setText(""+u.getTelefono());
                    txtAEdad.setText(""+u.getEdad());
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //usuariosRegistrados.getUsuariosRegistrados().clear();
                //usuariosRegistrados.datosQuemados();
                if (usuariosRegistrados.getUsuariosRegistrados().isEmpty()){
                    JOptionPane.showMessageDialog(null,"No existen usuarios registrados");
                }
                llenarJlist();
            }
        });
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarLogAdmin();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtANombre.getText();
                String apellido = txtAApellido.getText();
                String cedulaTexto = txtACedula.getText();
                String telefono = txtATelefono.getText();
                String edadTexto = txtAEdad.getText();

                if (nombre.isEmpty() || apellido.isEmpty() || cedulaTexto.isEmpty() ||
                        telefono.isEmpty() || edadTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                    return;
                }

                try {
                    int cedula = Integer.parseInt(cedulaTexto);
                    int edad = Integer.parseInt(edadTexto);

                    Usuario usuario = new Usuario(nombre, apellido, cedula, telefono, edad);
                    if(usuariosRegistrados.actualizar(usuario)){
                        JOptionPane.showMessageDialog(null, "Información de usuario actualizada!");
                        llenarJlist();
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(null, "No es posible actualizar");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Cédula y edad deben ser números válidos");
                }
            }
        });

        validarSoloLetras(txtANombre, "nombre");
        validarSoloLetras(txtAApellido, "apellido");
        validarSoloNumeros(txtACedula, "cedula");
        validarSoloNumeros(txtAEdad, "edad");
        validarSoloNumeros(txtATelefono, "telefono");

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = lstUsuarios.getSelectedIndex();
                if (indice != -1){
                    Usuario usuarioSelect = usuariosRegistrados.getUsuariosRegistrados().get(indice);
                    usuariosRegistrados.eliminarUsuario(usuarioSelect.getCedula());
                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
                    llenarJlist();
                    limpiarCampos();
                }else {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado ningun usuario de la lista");
                }
            }
        });
        lstCitas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (lstCitas.getSelectedIndex()!=-1){
                    int indice = lstCitas.getSelectedIndex();
                    Cita u = gestorCitas.getListaCitas().get(indice);
                    txtPlaca.setText(""+u.getVehiculo().getPlaca());
                    txtModelo.setText(""+u.getVehiculo().getModelo());
                    txtMarca.setText(""+u.getVehiculo().getMarca());
                    txtColor.setText(""+u.getVehiculo().getColor());
                    spCosto.setValue(u.getCosto());
                    txtADescripcion.setText(""+u.getDescripcion());
                    txtFecha.setText(""+u.getFecha().toString());

                    prioridadN=u.getPrioridad();
                    us1=new Usuario(u.getUsuario().getNombre(),u.getUsuario().getApellido(),u.getUsuario().getCedula(),u.getUsuario().getTelefono(),u.getUsuario().getEdad());



                }

            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestorCitas.getListaCitas().isEmpty()){
                    JOptionPane.showMessageDialog(null,"No existen citas registradas");
                }
                llenarJlist1();
            }
        });
        btnDespachar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descripcion = txtADescripcion.getText();
                float costo =Float.parseFloat(spCosto.getValue().toString());
                String placa = txtPlaca.getText();
                String marca=txtMarca.getText();
                String modelo=txtModelo.getText();
                String color = txtColor.getText();
                Vehiculo vehiculo= new Vehiculo(marca,placa,color,modelo);

                if (descripcion.isEmpty()||costo==0) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                    return;
                    }

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

                            f1 =new Fecha(fecha.getDayOfMonth(),fecha.getMonthValue(), fecha.getYear());

                            JOptionPane.showMessageDialog(null, "Cita agendada correctamente.", "Cita Agendada", JOptionPane.INFORMATION_MESSAGE);
                        } catch (DateTimeParseException ex) {
                            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use el formato dd-MM-yyyy.", "Error en la fecha", JOptionPane.ERROR_MESSAGE);
                        }

                        if(gestorCitas.despacharPrimera(vehiculo,descripcion,costo)){
                            JOptionPane.showMessageDialog(null, "AUTO DESPACHADO");
                            llenarJlist1();
                        } else {
                            JOptionPane.showMessageDialog(null, "No es posible actualizar");
                        }

            }
        });
        btnListaPrueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gestorCitas.mostrarCitasDespachadasEnTextArea(txtAprueva);
            }
        });
        btnEliminarCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int indice = lstCitas.getSelectedIndex();
                if (indice != -1){
                    Cita citaselect = gestorCitas.getListaCitas().get(indice);
                    gestorCitas.eliminarCita(citaselect);
                    JOptionPane.showMessageDialog(null, "Cita eliminado exitosamente");
                    llenarJlist1();
                }else {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna cita de la lista");
                }

            }
        });
        lstCitas2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (lstCitas2.getSelectedIndex()!=-1){
                    int indice = lstCitas2.getSelectedIndex();
                    Cita u = gestorCitas.getListaCitas().get(indice);
                    txtPlacaN.setText(""+u.getVehiculo().getPlaca());
                    txtModelo2.setText(""+u.getVehiculo().getModelo());
                    txtMarca2.setText(""+u.getVehiculo().getMarca());
                    txtColor2.setText(""+u.getVehiculo().getColor());
                    spnPrioridad2.setValue(u.getPrioridad());
                    txtFecha3.setText(""+u.getFecha().toString());
                    String fechaTexto = txtFecha3.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fecha = LocalDate.parse(fechaTexto, formatter);
                    f3 =new Fecha(fecha.getDayOfMonth(),fecha.getMonthValue(), fecha.getYear());



                    us2=new Usuario(u.getUsuario().getNombre(),u.getUsuario().getApellido(),u.getUsuario().getCedula(),u.getUsuario().getTelefono(),u.getUsuario().getEdad());
                }
                }
        });
        btnActualizar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int prioridad =Integer.parseInt(spnPrioridad2.getValue().toString());
                String placaAux=txtPlacaN.getText();
                String placa = txtPlaca2.getText();
                String marca=txtMarca2.getText();
                String modelo=txtModelo2.getText();
                String color = txtColor2.getText();
                Vehiculo vehiculo= new Vehiculo(marca,placa,color,modelo);

                if (placa.isEmpty()||prioridad<=0||marca.isEmpty()||modelo.isEmpty()||color.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                    return;
                }

                String fechaTexto = txtFecha2.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                try {
                    // Intentar parsear la fecha
                    LocalDate fecha = LocalDate.parse(fechaTexto, formatter);

                    // Verificar que la fecha no es anterior a la fecha actual
                    if (fecha.isBefore(LocalDate.now())) {
                        JOptionPane.showMessageDialog(null, "La fecha no puede ser anterior a la fecha actual.", "Error en la fecha", JOptionPane.ERROR_MESSAGE);
                        return; // Detener la ejecución del método aquí
                    }

                    f2 =new Fecha(fecha.getDayOfMonth(),fecha.getMonthValue(), fecha.getYear());

                    JOptionPane.showMessageDialog(null, "Cita agendada correctamente.", "Cita Agendada", JOptionPane.INFORMATION_MESSAGE);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use el formato dd-MM-yyyy.", "Error en la fecha", JOptionPane.ERROR_MESSAGE);
                }

                if(gestorCitas.actualizarCita(placaAux,f3,vehiculo,prioridad,f2)){
                JOptionPane.showMessageDialog(null, "Cita actualizada");
                llenarJlist2();
                }else {
                    JOptionPane.showMessageDialog(null, "No es posible actualizar");
                }





            }
        });
        btnListar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (gestorCitas.getListaCitas().isEmpty()){
                    JOptionPane.showMessageDialog(null,"No existen citas registradas");
                }
                llenarJlist2();

            }
        });
    }
    private void limpiarCampos(){
        txtANombre.setText("");
        txtAApellido.setText("");
        txtACedula.setText("");
        txtAEdad.setText("");
        txtATelefono.setText("");
    }
    public JPanel getPanel() {
        return vtnModuloAdmin;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
