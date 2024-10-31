package Modelo;

public class Categorias {

    private int idCategorias;
    private String tipos;
    private String descripcion;
    private int cantidad;
    private String area;
    private int proveedores_idProveedores; //kf

    public int getIdCategorias() {
        return idCategorias;
    }

    public void setIdCategorias(int idCategorias) {
        this.idCategorias = idCategorias;
    }

    public String getTipos() {
        return tipos;
    }

    public void setTipos(String tipos) {
        this.tipos = tipos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getProveedores_idProveedores() {
        return proveedores_idProveedores;
    }

    public void setProveedores_idProveedores(int proveedores_idProveedores) {
        this.proveedores_idProveedores = proveedores_idProveedores;
    }

   

}
