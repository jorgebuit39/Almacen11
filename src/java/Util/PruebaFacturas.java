
package Util;

import Modelo.Facturas;
import Persistencia.DaoFacturas;
import java.util.List;


public class PruebaFacturas {

   
    public static void main(String[] args) {
        
         List<Facturas> facturasList = DaoFacturas.listar();

    if (facturasList.isEmpty()) {
        System.out.println("No hay facturas disponibles.");
    } else {
        System.out.println("Lista de facturas:");
        for (Facturas factura : facturasList) {
            System.out.println("ID: " + factura.getIdFacturas());
            System.out.println("Número de Orden: " + factura.getNumeroOrden());
            System.out.println("Fecha de Entrada: " + factura.getFechaEntrada());
            System.out.println("ID de Usuario: " + factura.getUsuarios_idUsuarios());
            System.out.println("ID de Proveedor: " + factura.getProveedores_idProveedores());
            System.out.println("---------------------------------------");
        }
    }
        
        
        
        
        
    }
    
}
