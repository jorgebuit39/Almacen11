package Controlador;

import Modelo.Movimientos;
import Persistencia.DaoMovimientos;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorMovimientos extends HttpServlet {

    Movimientos mov = new Movimientos();
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
            //   case "add":
            //  request.getRequestDispatcher("Vistas/RegistrarUsuarios.jsp").forward(request, response);//
            //  break;

            case "registrar":
                registrarMovimientos(request, response);
                break;

            case "listar":
                listarMovimientos(request, response);
                break;

            case "editar":
                editarMovimientos(request, response);
                break;

            case "editarMovimientos":
                actualizarMovimientos(request, response);
                break;

            case "buscar":
                buscarMovimientos(request, response);
                break;

            case "eliminar":
                eliminarMovimientos(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
    }

    private void registrarMovimientos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            //Modelo Movmientno
            Movimientos mov = new Movimientos();

            mov.setNombre(request.getParameter("nombre"));
            mov.setFecha(request.getParameter("fecha"));
            mov.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
            mov.setProductos_idProductos(Integer.parseInt(request.getParameter("productos_idProductos")));
            mov.setIdMovimientos(ide);
            if (DaoMovimientos.grabar(mov)) {

                request.setAttribute("mensaje", "el movimiento  fue registrado");
            } else {
                request.setAttribute("mensaje", "el movimiento no fue registrado, validar campos ingresados");
            }

            List<Movimientos> lt = DaoMovimientos.listar();
            request.setAttribute("listaMovimientos", lt);
            request.getRequestDispatcher("Vistas/ListaMovimientos.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
            listarMovimientos(request, response);
        }
    }

    private void listarMovimientos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Movimientos> lt = DaoMovimientos.listar();
            request.setAttribute("listaMovimientos", lt);
            request.getRequestDispatcher("Vistas/ListaMovimientos.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListaMovimientos.jsp").forward(request, response);
        }
    }

    private void editarMovimientos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia
            ide = Integer.parseInt(request.getParameter("id"));
            Movimientos mv = DaoMovimientos.obtenerMovimientosPorId(ide);
            request.setAttribute("Mov", mv);

            listarMovimientos(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarMovimientos(request, response);
        }
    }

    private void actualizarMovimientos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Utilizar la variable de instancia para obtener el ID
            Movimientos mv = DaoMovimientos.obtenerMovimientosPorId(ide);
            request.setAttribute("Mov", mv);

            Movimientos movimientos = new Movimientos();

            movimientos.setNombre(request.getParameter("nombre"));
            movimientos.setFecha(request.getParameter("fecha"));
            movimientos.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
            movimientos.setProductos_idProductos(Integer.parseInt(request.getParameter("productos_idProductos")));
            movimientos.setIdMovimientos(ide);

            boolean actualizacionExitosa = DaoMovimientos.editar(movimientos);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Consecutivo actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Consecutivo");
            }

                listarMovimientos(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarMovimientos(request, response);
        }
    }

    private void eliminarMovimientos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idMovimientos = Integer.parseInt(request.getParameter("id"));

            if (DaoMovimientos.eliminar(idMovimientos)) {
                request.setAttribute("mensaje", "El movimiento fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el movimiento");
            }

            List<Movimientos> lt = DaoMovimientos.listar();
            request.setAttribute("listaMovimientos ", lt);
            request.getRequestDispatcher("Vistas/ListaMovimientos.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
            listarMovimientos(request, response);
        }
    }

    private void buscarMovimientos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String texto = request.getParameter("txtbuscar");
            List<Movimientos> lt = DaoMovimientos.buscarMovimientos(texto);
            request.setAttribute("listaMovimientos", lt);
            request.getRequestDispatcher("Vistas/ListaMovimientos.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListaMovimientos.jsp").forward(request, response);
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
