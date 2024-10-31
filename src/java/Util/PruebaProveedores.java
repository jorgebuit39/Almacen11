
package Util;

import Modelo.Proveedores;
import static Persistencia.DaoProveedores.grabar;

public class PruebaProveedores {


    public static void main(String[] args) {
        // Crear un objeto Proveedores con los datos que deseas insertar
        Proveedores proveedor = new Proveedores();
        proveedor.setNombres("Proveedor de Prueba");
        proveedor.setTelefono("123456789");
        proveedor.setDomicilio("Dirección de Prueba");
        proveedor.setCorreo("correo@prueba.com");
        proveedor.setDocumento_idDocumento(1); // Reemplaza esto con el id del documento deseado
       

        // Llamar al método grabar y mostrar el resultado
        if (grabar(proveedor)) {
            System.out.println("Proveedor insertado correctamente en la base de datos.");
        } else {
            System.out.println("Hubo un error al insertar el proveedor en la base de datos.");
        }
    }
    
}
