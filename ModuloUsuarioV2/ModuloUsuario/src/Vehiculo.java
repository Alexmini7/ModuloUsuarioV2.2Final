public class Vehiculo {
    private String marca;
    private String placa;
    private String color;
    private String modelo;

    public Vehiculo(String marca, String placa, String color, String modelo) {
        this.marca = marca;
        this.placa = placa;
        this.color = color;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return String.format(
                "\n"+
                "╔══════════════ Vehículo ══════════════╗%n" +
                        "║  Marca   : %-26s ║%n" +
                        "║  Placa   : %-26s ║%n" +
                        "║  Color   : %-26s ║%n" +
                        "║  Modelo  : %-26s ║%n" +
                        "╚═══════════════════════════════════════╝%n",
                marca, placa, color, modelo
        );
    }

}

