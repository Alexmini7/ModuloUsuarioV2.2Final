import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventario {
    private List<Pieza> piezas;

    public Inventario() {
        this.piezas = new ArrayList<>();
    }

    // A. Registro de Inventario
    public void agregarPieza(Pieza pieza) {
        piezas.add(pieza);
    }

    // B. Control de Utilización de Piezas
    public void registrarUtilizacionPieza(String nombrePieza, int cantidadUtilizada, String usuario) {
        Iterator<Pieza> iterator = piezas.iterator();
        while (iterator.hasNext()) {
            Pieza pieza = iterator.next();
            if (pieza.getNombre().equals(nombrePieza)) {
                int nuevaCantidad = pieza.getCantidad() - cantidadUtilizada;
                if (nuevaCantidad >= 0) {
                    pieza.setCantidad(nuevaCantidad);
                    System.out.println("Pieza utilizada por " + usuario);
                } else {
                    System.out.println("No hay suficientes piezas disponibles.");
                }
                break;
            }
        }
    }

    // C. Reabastecimiento de Inventario
    public void reabastecerInventario(String nombrePieza, int cantidadReabastecida, String proveedor) {
        Iterator<Pieza> iterator = piezas.iterator();
        while (iterator.hasNext()) {
            Pieza pieza = iterator.next();
            if (pieza.getNombre().equals(nombrePieza)) {
                int nuevaCantidad = pieza.getCantidad() + cantidadReabastecida;
                pieza.setCantidad(nuevaCantidad);
                pieza.setProveedor(proveedor);
                System.out.println("Inventario reabastecido con éxito.");
                break;
            }
        }
    }

    // D. Información de Piezas
    public String obtenerInformacionPiezas() {
        StringBuilder info = new StringBuilder();
        for (Pieza pieza : piezas) {
            info.append(pieza.toString()).append("\n");
        }
        return info.toString();
    }

    // Métodos adicionales si es necesario

    // Eliminar pieza por nombre
    public void eliminarPieza(String nombrePieza) throws Exception {
        Iterator<Pieza> iterator = piezas.iterator();
        while (iterator.hasNext()) {
            Pieza pieza = iterator.next();
            if (pieza.getNombre().equals(nombrePieza)) {
                iterator.remove();
                throw new Exception("Pieza eliminada con éxito.");

            }
        }
    }

    // Modificar información de una pieza
    public void modificarInformacionPieza(String nombrePieza, Pieza nuevaInformacion) throws Exception {
        Iterator<Pieza> iterator = piezas.iterator();
        while (iterator.hasNext()) {
            Pieza pieza = iterator.next();
            if (pieza.getNombre().equals(nombrePieza)) {
                pieza.setNombre(nuevaInformacion.getNombre());
                pieza.setCantidad(nuevaInformacion.getCantidad());
                pieza.setFechaAdquisicion(nuevaInformacion.getFechaAdquisicion());
                pieza.setNumeroSerie(nuevaInformacion.getNumeroSerie());
                pieza.setProveedor(nuevaInformacion.getProveedor());
                throw new Exception("Piezas Actualizadas");
            }
        }
    }

    public List<Pieza> getPiezas() {
        return piezas;
    }
}
