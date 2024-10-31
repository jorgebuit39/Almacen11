
package Util;

import Modelo.Productos;
import Persistencia.DaoProductos;
import java.util.List;

public class PruebaProdustos {
    //Metodo para listar 
    public static void main(String[] args) {
        List<Productos> listaUsuarios = DaoProductos.listar();

        // Imprimir los documentos obtenidos For i
        for (Productos usuarios : listaUsuarios) {
            System.out.println("ID: " + usuarios.getIdProductos()
                    + ", Nombre: " + usuarios.getNombreProductos()
                    + ", Correo: " + usuarios.getCantidad()
                    + ", Correo: " + usuarios.getPlu()
                    + ", Usuario: " + usuarios.getProveedores_idProveedores()
                    + ", IdPerfil: " + usuarios.getCategorias_idCategorias());
        }
    }
    
    
    
    
    
    
}
