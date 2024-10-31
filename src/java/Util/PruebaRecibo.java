
package Util;

import Modelo.Recibo;
import Persistencia.DaoRecibo;
import static Persistencia.DaoRecibo.editar;
import com.sun.tools.rngom.digested.Main;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PruebaRecibo {

    public static void main(String[] args) {
        // Crear un objeto Recibo con los datos que deseas actualizar
        Recibo recibo = new Recibo();
        recibo.setIdRecibo(7); // ID del recibo que quieres actualizar
        recibo.setPlu(12345); // Nuevo valor para el PLU
        recibo.setDescripcion("Nueva descripción"); // Nueva descripción
        recibo.setCantidad(5); // Nueva cantidad
        recibo.setCosto(10.99); // Nuevo costo
        recibo.setProductos_idProductos(2); // Nuevo ID del producto

        // Llamar al método editar para intentar actualizar el recibo en la base de datos
        boolean actualizacionExitosa = DaoRecibo.editar(recibo);

        // Verificar si la actualización fue exitosa o no
        if (actualizacionExitosa) {
            System.out.println("¡La actualización del recibo fue exitosa!");
        } else {
            System.out.println("¡La actualización del recibo falló!");
        }
    }
 /*  public static void main(String[] args) {
        
         Main main = new Main();
        HttpServletRequest request = null; // Simulación de la solicitud HTTP
        HttpServletResponse response = null; // Simulación de la respuesta HTTP
        
    }

    private void actualizarRecibo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Simulación de los datos de la solicitud
            int ide = 1; // ID del recibo a actualizar
            String pluParam = "123"; // Parámetro para el PLU del recibo
            String descripcionParam = "Descripción de prueba"; // Parámetro para la descripción del recibo
            String cantidadParam = "10"; // Parámetro para la cantidad del recibo
            String costoParam = "50"; // Parámetro para el costo del recibo
            String productosIdParam = "2"; // Parámetro para el ID del producto del recibo

            // Utilizar la variable de instancia para obtener el ID
            Recibo rec = DaoRecibo.obtenerReciboPorId(ide);
            request.setAttribute("User", rec);

            Recibo recibo = new Recibo();

            recibo.setPlu(Integer.parseInt(pluParam));
            recibo.setDescripcion(descripcionParam);
            recibo.setCantidad(Integer.parseInt(cantidadParam));
            recibo.setCosto(Integer.parseInt(costoParam));
            recibo.setProductos_idProductos(Integer.parseInt(productosIdParam));
            recibo.setIdRecibo(ide);

            boolean actualizacionExitosa = DaoRecibo.editar(recibo);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Consecutivo actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Consecutivo");
            }

            List<Recibo> lt = DaoRecibo.listar();
            request.setAttribute("listaRecibo", lt);
            request.getRequestDispatcher("Vistas/ListaRecibo.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarRecibo(request, response);
        }
    }*/

}
        
        
        
        
        
        
        
        
        
      
   
    

