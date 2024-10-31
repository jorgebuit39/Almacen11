package Controlador;

import Modelo.Proveedores;
import Persistencia.DaoProveedores;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControladorProveedores extends HttpServlet {

    Proveedores user = new Proveedores();
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
        String action = request.getParameter("accion");

        switch (action) {
            case "registrar":
                registrarProveedores(request, response);
                break;

            case "listar":
                listarProveedores(request, response);
                break;

            case "editar":
                editarProveedores(request, response);
                break;

            case "editarProveedores":
                actualizarProveedores(request, response);
                break;

            case "buscar":
                buscarProveedores(request, response);
                break;

            case "eliminar":
                eliminarProveedores(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
    }

    //metodos
    private void registrarProveedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
         Proveedores proveedor = new Proveedores();

            proveedor.setNombres(request.getParameter("nombres"));
            proveedor.setTelefono(request.getParameter("telefono"));
            proveedor.setDomicilio(request.getParameter("domicilio"));
            proveedor.setCorreo(request.getParameter("correo"));
            proveedor.setDocumento_idDocumento(Integer.parseInt(request.getParameter("documento_idDocumento")));

            if (DaoProveedores.grabar(proveedor)) {

                request.setAttribute("mensaje", "el Proveedor fue registrado");
            } else {
                request.setAttribute("mensaje", "el Proveedor no fue registrado, validar campos ingresados");
            }

            List<Proveedores> lt = DaoProveedores.listar();
            request.setAttribute("listaProveedores", lt);
            request.getRequestDispatcher("Vistas/ListaProveedores.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
            listarProveedores(request, response);
        }
    }

    private void listarProveedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
          List<Proveedores> lt = DaoProveedores.listar();
            request.setAttribute("listaProveedores", lt);
            request.getRequestDispatcher("Vistas/ListaProveedores.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los proveedores");
            request.getRequestDispatcher("Vistas/ListaProveedores.jsp").forward(request, response);
        }
    }

    private void editarProveedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia
            ide = Integer.parseInt(request.getParameter("id"));
            Proveedores proveedor = DaoProveedores.obtenerProveedoresPorId(ide);
            request.setAttribute("User", proveedor);
            listarProveedores(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el consecutivo");
            listarProveedores(request, response);
        }
    }

    private void actualizarProveedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
          // Utilizar la variable de instancia para obtener el ID
            Proveedores proveedor = DaoProveedores.obtenerProveedoresPorId(ide);
            request.setAttribute("User", proveedor);

            Proveedores prov = new Proveedores();

            prov.setNombres(request.getParameter("nombres"));
            prov.setTelefono(request.getParameter("telefono"));
            prov.setDomicilio(request.getParameter("domicilio"));
            prov.setCorreo(request.getParameter("correo"));
            prov.setDocumento_idDocumento(Integer.parseInt(request.getParameter("documento_idDocumento")));
            prov.setIdProveedores(ide);

            boolean actualizacionExitosa = DaoProveedores.editar(prov);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Proveedor actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el proveedor");
            }

            listarProveedores(request, response);

        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el proveedor: " + ex.getMessage());
            listarProveedores(request, response);
        }
    }

    private void eliminarProveedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           try {
            int idProveedores = Integer.parseInt(request.getParameter("id"));

            if (DaoProveedores.eliminar(idProveedores)) {
                request.setAttribute("mensaje", "El proveedor fue eliminado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el proveedor");
            }

            listarProveedores(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el proveedor");
            listarProveedores(request, response);
        }
    
    }
     

    private void buscarProveedores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try {
            String texto = request.getParameter("txtbuscar");
            List<Proveedores> lt = DaoProveedores.buscarProveedores(texto);
            request.setAttribute("listaProveedores", lt);
            request.getRequestDispatcher("Vistas/ListaProveedores.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los proveedores");
            request.getRequestDispatcher("Vistas/ListaProveedores.jsp").forward(request, response);
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
    }
}
