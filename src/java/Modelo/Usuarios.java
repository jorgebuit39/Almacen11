package Modelo;

public class Usuarios {

    private int idUsuarios;
    private String nombres;
    private String usuario;
    private String clave;
    private String correo;
   private int perfil_idPerfil; 

    public int getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPerfil_idPerfil() {
        return perfil_idPerfil;
    }

    public void setPerfil_idPerfil(int perfil_idPerfil) {
        this.perfil_idPerfil = perfil_idPerfil;
    }
   
   
   
}