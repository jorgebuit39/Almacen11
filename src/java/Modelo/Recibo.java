package Modelo;

public class Recibo {

    private int idRecibo;
    private int plu;
    private String descripcion;
    private double cantidad;
    private double costo;
    private int productos_idProductos; //kf

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getPlu() {
        return plu;
    }

    public void setPlu(int plu) {
        this.plu = plu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getProductos_idProductos() {
        return productos_idProductos;
    }

    public void setProductos_idProductos(int productos_idProductos) {
        this.productos_idProductos = productos_idProductos;
    }

}
