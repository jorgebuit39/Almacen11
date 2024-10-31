package Controlador;

import Modelo.Productos;
import Persistencia.DaoProductos;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorProductos extends HttpServlet {

    Productos user = new Productos();
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
                registrarProductos(request, response);
                break;

            case "listar":
                listarProductos(request, response);
                break;

            case "editar":
                editarProductos(request, response);
                break;

            case "editarProductos":
                actualizarProductos(request, response);
                break;

            case "buscar":
                buscarProductos(request, response);
                break;

            case "eliminar":
                eliminarProductos(request, response);
                break;

            default:
                // Handle any other actions or provide a default behavior
                break;
        }
    }

    private void registrarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Productos producto = new Productos();

            producto.setNombreProductos(request.getParameter("nombreProductos"));
            producto.setPlu(Integer.parseInt(request.getParameter("plu")));
            producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
            producto.setCategorias_idCategorias(Integer.parseInt(request.getParameter("categorias_idCategorias")));
            producto.setProveedores_idProveedores(Integer.parseInt(request.getParameter("proveedores_idProveedores")));
          
            if (DaoProductos.grabar(producto)) {
                request.setAttribute("mensaje", "el Producto fue registrado");
            } else {
                request.setAttribute("mensaje", "el producto no fue registrado, validar campos ingresados");
            }

            List<Productos> lt = DaoProductos.listar();
            request.setAttribute("listaProductos", lt);
            request.getRequestDispatcher("Vistas/ListaProductos.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al registrar el Consecutivo");
            listarProductos(request, response);
        }
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Productos> lt = DaoProductos.listar();
            request.setAttribute("listaProductos", lt);
            request.getRequestDispatcher("Vistas/ListaProductos.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al listar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListaProductos.jsp").forward(request, response);
        }
    }

    private void editarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener el ID del parámetro de solicitud y almacenarlo en la variable de instancia
            ide = Integer.parseInt(request.getParameter("id"));
            Productos pro = DaoProductos.obtenerProductosPorId(ide);
            request.setAttribute("User", pro);

             listarProductos(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al editar el Consecutivo");
            listarProductos(request, response);
        }
    }

    private void actualizarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Utilizar la variable de instancia para obtener el ID
            Productos pro = DaoProductos.obtenerProductosPorId(ide);
            request.setAttribute("User", pro);

            Productos producto = new Productos();

            producto.setNombreProductos(request.getParameter("nombreProductos"));
            producto.setPlu(Integer.parseInt(request.getParameter("plu")));
            producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
            producto.setCategorias_idCategorias(Integer.parseInt(request.getParameter("categorias_idCategorias")));
            producto.setProveedores_idProveedores(Integer.parseInt(request.getParameter("proveedores_idProveedores")));
            producto.setIdProductos(ide);
            
            boolean actualizacionExitosa = DaoProductos.editar(producto);

            if (actualizacionExitosa) {
                request.setAttribute("mensaje", "Consecutivo actualizado correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo actualizar el Consecutivo");
            }

            listarProductos(request, response);
            
        } catch (IOException | NumberFormatException | ServletException ex) {
            request.setAttribute("mensaje", "Error al actualizar el Consecutivo: " + ex.getMessage());
            listarProductos(request, response);
        }
    }

    private void eliminarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idProductos = Integer.parseInt(request.getParameter("id"));

            if (DaoProductos.eliminar(idProductos)) {
                request.setAttribute("mensaje", "El producto fue Eliminado Correctamente");
            } else {
                request.setAttribute("mensaje", "No se pudo eliminar el producto");
            }

            List<Productos> lt = DaoProductos.listar();
            request.setAttribute("listaProductos", lt);
            request.getRequestDispatcher("Vistas/ListaProductos.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al eliminar el Consecutivo");
            listarProductos(request, response);
        }
    }

    private void buscarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String texto = request.getParameter("txtbuscar");
            List<Productos> lt = DaoProductos.buscarProductos(texto);
            request.setAttribute("listaProductos", lt);
            request.getRequestDispatcher("Vistas/ListaProductos.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", "Error al buscar los Consecutivos");
            request.getRequestDispatcher("Vistas/ListaProductos.jsp").forward(request, response);
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
