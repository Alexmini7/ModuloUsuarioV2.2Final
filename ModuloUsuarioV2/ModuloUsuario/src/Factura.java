import java.util.List;
import java.util.ArrayList;

public class Factura {
    private final String numeroFactura;
    private Usuario cliente;
    private Fecha fechaEmision;
    private List<Pieza> items;
    private double total;
    private String estado; // Ej: "Pagada", "Pendiente", "Vencida"

    public Factura(String numeroFactura, Usuario cliente, Fecha fechaEmision) {
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.fechaEmision = fechaEmision;
        this.items = new ArrayList<>();
        this.estado = "Pendiente"; // Estado inicial por defecto
    }

    public void agregarItem(Pieza item) {
        items.add(item);
        calcularTotal();
    }

    public void calcularTotal() {
        total = 0;
        for (Pieza item : items) {
            // Suponemos que Pieza ya tiene un atributo precio y su getter correspondiente.
            total += item.getPrecio() * item.getCantidad();
        }
    }

    // Otros métodos como registrarPago(), actualizarEstado(), etc.

    // Getters y setters para las propiedades que necesitas exponer
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Fecha getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Fecha fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public List<Pieza> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura{");
        sb.append("numeroFactura='").append(numeroFactura).append('\'');
        sb.append(", cliente=").append(cliente);
        sb.append(", fechaEmision=").append(fechaEmision);
        sb.append(", items=").append(items);
        sb.append(", total=").append(total);
        sb.append(", estado='").append(estado).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

