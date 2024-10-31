
package Modelo;


public class Productos {
    
    private int idProductos;
    private String nombreProductos;
    private int plu;
    private double cantidad;
    private int categorias_idCategorias;
    private int proveedores_idProveedores;

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombreProductos() {
        return nombreProductos;
    }

    public void setNombreProductos(String nombreProductos) {
        this.nombreProductos = nombreProductos;
    }

    public int getPlu() {
        return plu;
    }

    public void setPlu(int plu) {
        this.plu = plu;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getCategorias_idCategorias() {
        return categorias_idCategorias;
    }

    public void setCategorias_idCategorias(int categorias_idCategorias) {
        this.categorias_idCategorias = categorias_idCategorias;
    }

    public int getProveedores_idProveedores() {
        return proveedores_idProveedores;
    }

    public void setProveedores_idProveedores(int proveedores_idProveedores) {
        this.proveedores_idProveedores = proveedores_idProveedores;
    }

}