/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Modelo.Categorias;
import Persistencia.DaoCategorias;
import java.util.List;

/**
 *
 * @author jorge
 */
public class PruebaCatergorias {
   public static void main(String[] args) {
        List<Categorias> listaUsuarios = DaoCategorias.listar();

        // Imprimir los documentos obtenidos For i
        for (Categorias usuarios : listaUsuarios) {
            System.out.println("ID: " + usuarios.getArea()
                    + ", Nombre: " + usuarios.getDescripcion()
                    + ", Correo: " + usuarios.getCantidad()
                    + ", Correo: " + usuarios.getIdCategorias()
                    + ", Correo: " + usuarios.getTipos()
                    + ", Usuario: " + usuarios.getProveedores_idProveedores());
                
        }
    }
    

    
    
}
