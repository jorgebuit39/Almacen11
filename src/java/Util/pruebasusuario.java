package Util;

import Modelo.Usuarios;
import Persistencia.DaoUsuarios;
import java.util.List;
import java.util.Scanner;

public class pruebasusuario {

    // Metodo Grabar
    
    /* public static void main(String[] args) {
//instanciar la clase (crear un obejto)
        
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        Usuarios usuarios = new Usuarios();

        Scanner Leer = new java.util.Scanner(System.in);

        String nombres;
        String usu;
        String clave;
        String correo;
        int perfil_idPerfil;

        System.out.println("Por favor, nombres");
        nombres = Leer.nextLine();
        
        System.out.println("Por favor, usuarios");
        usu = Leer.next();
        
        System.out.println("Por favor, clave");
        clave = Leer.next();
        
        System.out.println("Por favor, correo");
        correo = Leer.next();
        
        System.out.println("Por favor, perfil_idPerfil");
        perfil_idPerfil = Leer.nextInt();

        usuarios.setNombres(nombres);
        usuarios.setUsuarios(usu);
        usuarios.setClave(clave);
        usuarios.setCorreo(correo);
        usuarios.setPerfil_idPerfil(perfil_idPerfil);

        boolean respuesta = daoUsuarios.grabar(usuarios);
        if (respuesta == true) {
            System.out.println("exito");

        } else {
            System.out.println("error");
        }
    }*/
    
    //Metodo para listar 
    public static void main(String[] args) {
        List<Usuarios> listaUsuarios = DaoUsuarios.listar();

        // Imprimir los documentos obtenidos For i
        for (Usuarios usuarios : listaUsuarios) {
            System.out.println("ID: " + usuarios.getIdUsuarios()
                    + ", Nombre: " + usuarios.getNombres()
                    + ", Correo: " + usuarios.getCorreo()
                    + ", Correo: " + usuarios.getClave()
                    + ", Usuario: " + usuarios.getUsuario()
                    + ", IdPerfil: " + usuarios.getPerfil_idPerfil());
        }
    }
}
