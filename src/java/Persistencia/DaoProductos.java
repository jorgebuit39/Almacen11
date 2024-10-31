package Persistencia;

import Config.Conexion;
import Modelo.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*crear metodos*/

 /*adicionar*/
public class DaoProductos {

    static Conexion cn = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
    static Productos productos = new Productos();

    public static boolean grabar(Productos productos) {
        try {
            con = cn.conectar();
            String sql = "INSERT INTO productos (nombreProductos, plu, cantidad, categorias_idCategorias,proveedores_idProveedores) "
                    + "VALUES(?,?,?,?,?);";
            ps = con.prepareStatement(sql);

            ps.setString(1, productos.getNombreProductos());
            ps.setInt(2, productos.getPlu());
            ps.setDouble(3, productos.getCantidad());
            ps.setInt(4, productos.getCategorias_idCategorias());
            ps.setInt(5, productos.getProveedores_idProveedores());

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        } finally {
            cerrarRecursos();
        }
        return false;
    }

    public static List<Productos> listar() {
        List<Productos> lista = new ArrayList<>();
        try {
            con = cn.conectar();
            String sql = "SELECT * FROM productos;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Productos productos1 = new Productos();

                productos1.setIdProductos(rs.getInt("idProductos"));
                productos1.setNombreProductos(rs.getString("nombreProductos"));
                productos1.setPlu(rs.getInt("plu"));
                productos1.setCantidad(rs.getDouble("cantidad"));
                productos1.setCategorias_idCategorias(rs.getInt("categorias_idCategorias"));
                productos1.setProveedores_idProveedores(rs.getInt("proveedores_idProveedores"));
                lista.add(productos1);
            }
        } catch (Exception e) {
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    //Se debe asegurar el mismo orden
    public static boolean editar(Productos productos) {
        try {
            con = cn.conectar();
            String sql = "UPDATE productos SET nombreProductos = ?, "
                    + "plu=?, cantidad =?, categorias_idCategorias=?, "
                    + "proveedores_idProveedores =? WHERE idProductos =?";

            ps = con.prepareStatement(sql);
            ps.setString(1, productos.getNombreProductos());
            ps.setInt(2, productos.getPlu());
            ps.setDouble(3, productos.getCantidad());
            ps.setInt(4, productos.getCategorias_idCategorias());
            ps.setInt(5, productos.getProveedores_idProveedores());
            ps.setInt(6, productos.getIdProductos());

            ps.executeUpdate();

            // Ejecutar la consulta de actualización
            int rowsAffected = ps.executeUpdate();

            // Verificar si se actualizó algún registro
            if (rowsAffected > 0) {
                return true; // La actualización fue exitosa

            }

        } catch (SQLException ex) {

        } finally {
            // Cerrar recursos (conexión, declaración, etc.) en el bloque "finally"
            // para garantizar que se cierren incluso si ocurre una excepción.
            cerrarRecursos();
        }
        return false;
    }

    public static String obtenerNombreProductos(int id) {
        try {
            con = cn.conectar();
            String sql = "SELECT nombreProductos  FROM productos WHERE idProductos=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nombreProductos");
            } else {
                return "--";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "--";
    }

    public static boolean eliminar(int idProductos) {
        try {
            con = cn.conectar();
            String sql = "DELETE FROM productos WHERE idProductos=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProductos);

            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja las excepciones de mejor manera, por ejemplo, lanzando una excepción personalizada.
        } finally {
            cerrarRecursos();
        }
        return false;
    }

    // Metodo Refactorizado para listar y editar 
   public static Productos obtenerProductosPorId(int id) {
        Productos productos = null;

        String sql = "SELECT * FROM productos WHERE idProductos=?";

        try (Connection con = cn.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Productos productos2 = new Productos();

                    productos2.setIdProductos(rs.getInt("idProductos"));
                    productos2.setNombreProductos(rs.getString("nombreProductos"));
                    productos2.setPlu(rs.getInt("plu"));
                    productos2.setCantidad(rs.getDouble("cantidad"));
                    productos2.setCategorias_idCategorias(rs.getInt("categorias_idCategorias"));
                    productos2.setProveedores_idProveedores(rs.getInt("proveedores_idProveedores"));

                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoProductos.class
                    .getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        }

        return productos;
    }

    public static List<Productos> buscarProductos(String texto) {
        List<Productos> listaProductos = new ArrayList<>();

        try {
            con = cn.conectar();

            String sql = "SELECT * FROM productos "
                    + "WHERE nombreProductos LIKE ?"
                    + " OR plu LIKE ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Productos productos1 = new Productos();

                productos1.setIdProductos(rs.getInt("idProductos"));
                productos1.setNombreProductos(rs.getString("nombreProductos"));
                productos1.setPlu(rs.getInt("plu"));
                productos1.setCantidad(rs.getDouble("cantidad"));
                productos1.setCategorias_idCategorias(rs.getInt("categorias_idCategorias"));
                productos1.setProveedores_idProveedores(rs.getInt("proveedores_idProveedores"));
                listaProductos.add(productos1);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listaProductos;
    }

    //**********************************************
    //metodo jasypt para encriptar contraseña
    //Nota:Se debe manejar el mismo orden tanto en l asentencia Sql como en el ps
    // Agrega este método para cerrar las conexiones y recursos
    private static void cerrarRecursos() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
