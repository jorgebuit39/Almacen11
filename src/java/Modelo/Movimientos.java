
package Modelo;


public class Movimientos {
    
     private int idMovimientos;
     private String nombre;
    private String fecha;
    private Double cantidad;
    private int productos_idProductos;

    public int getIdMovimientos() {
        return idMovimientos;
    }

    public void setIdMovimientos(int idMovimientos) {
        this.idMovimientos = idMovimientos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public int getProductos_idProductos() {
        return productos_idProductos;
    }

    public void setProductos_idProductos(int productos_idProductos) {
        this.productos_idProductos = productos_idProductos;
    }

}