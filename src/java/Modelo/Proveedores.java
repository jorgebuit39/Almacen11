
package Modelo;


public class Proveedores {
    
     private int idProveedores;
    private String nombres;
    private String telefono;
    private String domicilio;
    private String correo;
    private int documento_idDocumento;

    public int getIdProveedores() {
        return idProveedores;
    }

    public void setIdProveedores(int idProveedores) {
        this.idProveedores = idProveedores;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getDocumento_idDocumento() {
        return documento_idDocumento;
    }

    public void setDocumento_idDocumento(int documento_idDocumento) {
        this.documento_idDocumento = documento_idDocumento;
    }
   

}