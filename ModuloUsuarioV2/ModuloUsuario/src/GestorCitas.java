import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class GestorCitas {

    private ArrayList<Cita> listaCitas;
    private ListaUsuario listaUsuarios;
    private Vehiculo vehiculo;
    private ArrayList<Cita> citasDespachadas;


    public GestorCitas(ListaUsuario listaUsuarios) {
        listaCitas = new ArrayList<>();
        citasDespachadas = new ArrayList<>();
        this.listaUsuarios = listaUsuarios;
        this.vehiculo = vehiculo;
    }


    public ListaUsuario getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ListaUsuario listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ArrayList<Cita> getListaCitas() {
        return listaCitas;
    }

    public ArrayList<Cita> getCitasDespachadas() {
        return citasDespachadas;
    }

    public void setCitasDespachadas(ArrayList<Cita> citasDespachadas) {
        this.citasDespachadas = citasDespachadas;
    }

    public void setListaCitas(ArrayList<Cita> listaCitas) {
        this.listaCitas = listaCitas;
    }

    public void agregarCita(Usuario usuario, Vehiculo vehiculo, Fecha fecha,int prioridad) throws Exception {


        // Verificar si ya existe una cita para el vehículo en la misma fecha
        for (Cita ci : listaCitas) {
            if (ci.getVehiculo().getPlaca().equalsIgnoreCase(vehiculo.getPlaca())){
                if (ci.getFecha().getAnio()==fecha.getAnio() && ci.getFecha().getDia() == fecha.getDia() && ci.getFecha().getMes() == fecha.getMes()) {
                    throw new Exception("Ya existe una cita para este vehículo en la fecha proporcionada.");
                }
            }

        }

        // Agregar la nueva cita
        Cita nuevaCita = new Cita(usuario,fecha,prioridad,"", 0.0F,vehiculo); // Asegúrate de que el constructor de Cita esté definido adecuadamente
        listaCitas.add(nuevaCita);
        throw new Exception("Cita agendada exitosamente.");

    }

public boolean despacharPrimera(Vehiculo vehiculo, String descripcion, float costo) {
    for (Cita c : listaCitas) {
        // Verifica si coincide el vehículo y la fecha
        if (c.getVehiculo().getPlaca().equals(vehiculo.getPlaca())) {
            // Actualiza los detalles de la cita encontrada con los datos proporcionados
            c.setDescripcion(descripcion);
            c.setCosto(costo);
            despacharCita(vehiculo.getPlaca());
            return true; // Retorna true indicando que la cita fue actualizada
        }
    }
    return false; // Retorna false si no se encuentra la cita
}
    public void


    ordenarCitasPorPrioridad() {
        // Ordenar toda la lista por fecha usando Comparator
        listaCitas.sort(new Comparator<Cita>() {
            @Override
            public int compare(Cita c1, Cita c2) {
                // Comparación de fechas aquí
                Fecha fecha1 = c1.getFecha();
                Fecha fecha2 = c2.getFecha();
                // Asumiendo que Fecha tiene métodos getAno, getMes, getDia
                if (fecha1.getAnio() != fecha2.getAnio()) {
                    return fecha1.getAnio() - fecha2.getAnio();
                } else if (fecha1.getMes() != fecha2.getMes()) {
                    return fecha1.getMes() - fecha2.getMes();
                } else {
                    return fecha1.getDia() - fecha2.getDia();
                }
            }
        });

        // El código para ordenar por prioridad dentro de las fechas sigue igual
        boolean intercambio;
        do {
            intercambio = false;
            for (int i = 0; i < listaCitas.size() - 1; i++) {
                if (listaCitas.get(i).getFecha().equals(listaCitas.get(i + 1).getFecha()) &&
                        listaCitas.get(i).getPrioridad() > listaCitas.get(i + 1).getPrioridad()) {
                    Cita temp = listaCitas.get(i);
                    listaCitas.set(i, listaCitas.get(i + 1));
                    listaCitas.set(i + 1, temp);
                    intercambio = true;
                }
            }
        } while (intercambio);}


    public boolean despacharCita(String placa) {
        Iterator<Cita> iterator = listaCitas.iterator();
        while (iterator.hasNext()) {
            Cita cita = iterator.next();
            if (cita.getVehiculo().getPlaca().equals(placa)) {
                citasDespachadas.add(cita); // Agrega la cita a la lista de despachados
                iterator.remove(); // Elimina la cita de la lista principal
                return true; // Indica que la cita fue despachada exitosamente
            }
        }
        return false; // No se encontró la cita para despachar
    }

    public String metodoPrueba() {
        if (listaCitas.isEmpty()) {
            return "No hay citas agendadas.";
        }

        StringBuilder resultado = new StringBuilder();
        for (Cita cita : listaCitas) {
            String infoCita = String.format("Usuario: %s, Vehículo: %s, Marca: %s, Prioridad: %d, Fecha: %s\n",
                    cita.getUsuario().getNombre(), // Asume que Cita tiene un método getUsuario() que devuelve un Usuario, y Usuario tiene un método getNombre()
                    cita.getVehiculo().getPlaca(), // Asume que Cita tiene un método getVehiculo() que devuelve un Vehiculo, y Vehiculo tiene un método getPlaca()
                    cita.getVehiculo().getMarca(), // Asume que Vehiculo tiene un método getMarca()
                    cita.getPrioridad(), // Asume que Cita tiene un método getPrioridad() que devuelve un int
                    cita.getFecha().toString()); // Asume que Cita tiene un método getFecha() que devuelve algo que puede ser convertido a String, por ejemplo, LocalDate
            resultado.append(infoCita);
        }

        return resultado.toString();
    }
    public void mostrarCitasDespachadasEnTextArea(JTextArea textArea) {
        StringBuilder sb = new StringBuilder();

        for (Cita cita : citasDespachadas) {
            // Construir la cadena de texto con la información deseada de cada cita
            String infoCita = String.format("Usuario: %s, Vehículo: %s, Marca: %s, Prioridad: %d, costo %s, descripcion: %s\n",
                    cita.getUsuario().getNombre(), // Asume que Cita tiene un método getUsuario() que devuelve un Usuario, y Usuario tiene un método getNombre()
                    cita.getVehiculo().getPlaca(), // Asume que Cita tiene un método getVehiculo() que devuelve un Vehiculo, y Vehiculo tiene un método getPlaca()
                    cita.getVehiculo().getMarca(), // Asume que Vehiculo tiene un método getMarca()
                    cita.getPrioridad(), // Asume que Cita tiene un método getPrioridad() que devuelve un int
                    cita.getCosto(),
                    cita.getDescripcion()); // Asume que Cita tiene un método getFecha() que devuelve algo que puede ser convertido a String, por ejemplo, LocalDate
            sb.append(infoCita);
        }

        // Establecer el texto acumulado en el JTextArea
        textArea.setText(sb.toString());
    }

    public void eliminarCita(Cita c){
        for (int i=0 ; i<listaCitas.size(); i++){
            Cita cn = listaCitas.get(i);

            if (cn.getVehiculo().getPlaca().equalsIgnoreCase(c.getVehiculo().getPlaca())&&cn.getFecha().getDia()==c.getFecha().getDia()&&cn.getFecha().getMes()==c.getFecha().getMes()&&cn.getFecha().getAnio()==c.getFecha().getAnio()){
                listaCitas.remove(i);
            }
            break;
        }
    }
    public boolean actualizarCita(String placa, Fecha cn, Vehiculo vehiculoActualizado, int prioridadActualizada, Fecha fechaActualizada) {
        for (Cita c : listaCitas) {
            // Encuentra la cita basada en la placa del vehículo y la fecha original de la cita
            if (c.getVehiculo().getPlaca().equalsIgnoreCase(placa) && cn.getDia()==c.getFecha().getDia()&&cn.getMes()==c.getFecha().getMes()&&cn.getAnio()==c.getFecha().getAnio()) {
                // Actualiza la información del vehículo
                c.getVehiculo().setPlaca(vehiculoActualizado.getPlaca());
                c.getVehiculo().setModelo(vehiculoActualizado.getModelo());
                c.getVehiculo().setMarca(vehiculoActualizado.getMarca());
                c.getVehiculo().setColor(vehiculoActualizado.getColor());

                // Actualiza la prioridad y la fecha de la cita
                c.setPrioridad(prioridadActualizada);
                c.setFecha(fechaActualizada);

                return true; // Indica que la cita fue actualizada exitosamente
            }
        }
        return false; // Retorna false si no se encuentra la cita para actualizar
    }



//    public String obtenerCitasOrdenadasPorPrioridad() {
//        PriorityQueue<Cita> citasOrdenadas = clasificarCitasPorPrioridad();
//        StringBuilder result = new StringBuilder();
//        while (!citasOrdenadas.isEmpty()) {
//            Cita cita = citasOrdenadas.poll();
//            result.append(cita.toString());
//        }
//        return result.toString();
//    }

//    public void eliminarCitaPorUsuario(String nombre, String apellido, String cedula) {
//        PriorityQueue<Cita> colaTemporal = new PriorityQueue<>();
//        while (!listaCitas.isEmpty()) {
//            Cita cita = listaCitas.poll();
//            Usuario usuario = cita.getUsuario();
//            if (usuario.getNombre().equalsIgnoreCase(nombre) &&
//                    usuario.getApellido().equalsIgnoreCase(apellido) &&
//                    usuario.getCedula().equalsIgnoreCase(cedula)) {
//                // Skip adding this cita
//            } else {
//                colaTemporal.add(cita);
//            }
//        }
//        listaCitas.addAll(colaTemporal);
//    }

    // ... any additional methods ...
}
