public class Fecha {
    private int dia;
    private int mes;
    private int anio;


    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }



    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }


    @Override
    public String toString() {
        if (dia<10&&mes<10){
            return "0"+dia+"-"+"0"+mes+"-"+anio;
        } else if (dia<10&&mes>=10) {
            return "0"+dia+"-"+mes+"-"+anio;
        }else if (dia>10&&mes<=9){
            return dia+"-"+"0"+mes+"-"+anio;
        }else{
            return dia+"-"+mes+"-"+anio;
        }
    }

}
