package Modelo;

public class Bodega {

    private int idBodega;
    private Double cantidadInventario;
    private String unidadMedida;
    private Double costo;
    private int productos_idProductos;
    private int recibo_idRecibo ;

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public Double getCantidadInventario() {
        return cantidadInventario;
    }

    public void setCantidadInventario(Double cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public int getProductos_idProductos() {
        return productos_idProductos;
    }

    public void setProductos_idProductos(int productos_idProductos) {
        this.productos_idProductos = productos_idProductos;
    }

    public int getRecibo_idRecibo() {
        return recibo_idRecibo;
    }

    public void setRecibo_idRecibo(int recibo_idRecibo) {
        this.recibo_idRecibo = recibo_idRecibo;
    }

}