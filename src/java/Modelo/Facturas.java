package Modelo;

public class Facturas {

    private int idFacturas;
    private int numeroOrden;
    private String fechaEntrada;
    private int usuarios_idUsuarios;
    private int proveedores_idProveedores;

    public int getIdFacturas() {
        return idFacturas;
    }

    public void setIdFacturas(int idFacturas) {
        this.idFacturas = idFacturas;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public int getProveedores_idProveedores() {
        return proveedores_idProveedores;
    }

    public void setProveedores_idProveedores(int proveedores_idProveedores) {
        this.proveedores_idProveedores = proveedores_idProveedores;
    }

    public int getUsuarios_idUsuarios() {
        return usuarios_idUsuarios;
    }

    public void setUsuarios_idUsuarios(int usuarios_idUsuarios) {
        this.usuarios_idUsuarios = usuarios_idUsuarios;
    }

}
