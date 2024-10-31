package Controlador;

import Modelo.Recibo;
import Persistencia.DaoRecibo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorRecibo extends HttpServlet {

    Recibo user = new Recibo();
    int ide;  // Variable de instancia para almacenar el ID

    //La variable serialVersionUID se utiliza en Java para asignar una versión única 
    //a una clase Serializable.
    //En este contexto, private static final long serialVersionUID = 1L; simplemente está estableciendo 
    //el serialVersionUID de la clase ControladorConsecutivo
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String action = request.getParameter("accion");

        switch (action) {

            case "registrar":
                registrarRecibo(request, response);
                break;

            case "listar":
                listarRecibo(request, response);
                break;
            // editar= "name" que viene del Tabla 
            case "editar":
                // Referenciar un Metodos
                editarRecibo(request, response);
                break;
            // editarRecibo= "name" que viene del Formulario
            case "editarRecibo2":
                actualizarRecibo(request, response);
                break;

            case "buscar":
                buscarRecibo(request, response);
                break;

            case "eliminar":
                eliminarRecibo(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
    }

    private void registrarRecibo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            //Modelo Recibo
            Recibo recibo = new Recibo();

            recibo.setPlu(Integer.parseInt(request.getParameter("plu")));
            recibo.setDescripcion(request.getParameter("descripcion"));
            recibo.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
            recibo.setCosto(Double.parseDouble(request.getParameter("costo")));
            recibo.setProductos_idProductos(Integer.parseInt(request.getParameter("productos_idProductos")));

            if (DaoRecibo.grabar(recibo)) {

                request.setAttribute("mensaje", "el recibo fue registrado");
            } else {
                request.setAttribute("mensaje", "el recibo no fue registrado, validar campos ingresados");
            }

            List<Recibo> lt = DaoRecibo.listar();
            request.setAttribute("listaRecibo", lt);
            request.getRequestDispatcher("Vistas/ListaRecibo.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
            listarRecibo(request, response);
        }
    }

    private void listarRecibo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Recibo> lt = DaoRecibo.listar();
            request.setAttribute("listaRecibo", lt);
            request.getRequestDispatcher("Vistas/ListaRecibo.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListaRecibo.jsp").forward(request, response);
        }
    }

    private void editarRecibo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia
            ide = Integer.parseInt(request.getParameter("id"));
            // Cargar el objeto de Modelo Recibo y utilizar el metodo Dao .obtenerReciboPorId(ide); para listar y poder editor
            Recibo rec = DaoRecibo.obtenerReciboPorId(ide);
            // Aca Seteamos el atributo "User" // Esta palabra va en el Input (campo del formulario)
            //Y sirve para poder listar los valores
            request.setAttribute("User", rec);
            // Se envia para el metodo Listar
            listarRecibo(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarRecibo(request, response);
        }
    }

    private void actualizarRecibo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Utilizar la variable de instancia para obtener el ID
            Recibo rec = DaoRecibo.obtenerReciboPorId(ide);
            request.setAttribute("User", rec);

            Recibo recibo = new Recibo();

            recibo.setPlu(Integer.parseInt(request.getParameter("plu")));
            recibo.setDescripcion(request.getParameter("descripcion"));
            recibo.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
            recibo.setCosto(Double.parseDouble(request.getParameter("costo")));
            recibo.setProductos_idProductos(Integer.parseInt(request.getParameter("productos_idProductos")));
            recibo.setIdRecibo(ide);

            boolean actualizacionExitosa = DaoRecibo.editar(recibo);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Consecutivo actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Consecutivo");
            }

            listarRecibo(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarRecibo(request, response);
        }
    }

    private void eliminarRecibo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idRecibo = Integer.parseInt(request.getParameter("id"));

            if (DaoRecibo.eliminar(idRecibo)) {
                request.setAttribute("mensaje", "El recibo fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el recibo");
            }

            List<Recibo> lt = DaoRecibo.listar();
            request.setAttribute("listaRecibo", lt);
            request.getRequestDispatcher("Vistas/ListaRecibo.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
            listarRecibo(request, response);
        }
    }

    private void buscarRecibo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String texto = request.getParameter("txtbuscar");
            List<Recibo> lt = DaoRecibo.buscarRecibo(texto);
            request.setAttribute("listaRecibo", lt);
            request.getRequestDispatcher("Vistas/ListaRecibo.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListaRecibo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
