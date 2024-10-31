package Persistencia;

import Config.Conexion;
import Modelo.Recibo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/*adicionar*/
public class DaoRecibo {

    static Conexion cn = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
    static Recibo recibo = new Recibo();

    public static boolean grabar(Recibo recibo) {
        try {
            con = cn.conectar();
            String sql = "INSERT INTO recibo (plu, descripcion, cantidad,"
                    + "costo , productos_idProductos) "
                    + "VALUES(?,?,?,?,?);";
            ps = con.prepareStatement(sql);

            ps.setInt(1, recibo.getPlu());
            ps.setString(2, recibo.getDescripcion());
            ps.setDouble(3, recibo.getCantidad());
            ps.setDouble(4, recibo.getCosto());
            ps.setInt(5, recibo.getProductos_idProductos());

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

    public static List<Recibo> listar() {
        List<Recibo> lista = new ArrayList<>();
        try {
            con = cn.conectar();
            String sql = "SELECT * FROM recibo;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Recibo recibo = new Recibo();

                recibo.setIdRecibo(rs.getInt("idRecibo"));
                recibo.setPlu(rs.getInt("plu"));
                recibo.setDescripcion(rs.getString("descripcion"));
                recibo.setCantidad(rs.getDouble("cantidad"));
                recibo.setCosto(rs.getDouble("costo"));
                recibo.setProductos_idProductos(rs.getInt("productos_idProductos"));
                lista.add(recibo);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejar correctamente la excepción, imprimir al menos
        } finally {
            cerrarRecursos(); // Cierra los recursos en el bloque finally
        }
        return lista;
    }
    
//////////////////////////////////////////////editar////////////////////////////////////////////////////////////
    //Se debe asegurar el mismo orden

    public static boolean editar(Recibo recibo) {
        try {
            con = cn.conectar();
            String sql = "UPDATE recibo SET  plu =?,"
                    + "descripcion =?, cantidad =?, costo =?,"
                    + "productos_idProductos =?  WHERE idRecibo =?";

            ps = con.prepareStatement(sql);

            ps.setInt(1, recibo.getPlu());
            ps.setString(2, recibo.getDescripcion());
            ps.setDouble(3, recibo.getCantidad());
            ps.setDouble(4, recibo.getCosto());
            ps.setInt(5, recibo.getProductos_idProductos());
            ps.setInt(6, recibo.getIdRecibo());

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
    
    
      public static String obtenerNombreRecibo(int id) {
        try {
            con = cn.conectar();
            String sql = "SELECT descripcion  FROM recibo WHERE idRecibo=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("descripcion");
            } else {
                return "--";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoRecibo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "--";
    }
    
    
//////////////////////////////eliminar-///////////////////////////////////

    public static boolean eliminar(int idRecibo) {
        try {
            con = cn.conectar();
            String sql = "DELETE FROM recibo WHERE idRecibo=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idRecibo);

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
    public static Recibo obtenerReciboPorId(int id) {
        Recibo recibo = null;

        String sql = "SELECT * FROM recibo WHERE idRecibo=?";

        try (Connection con = cn.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    recibo = new Recibo();

                    recibo.setIdRecibo(rs.getInt("idRecibo"));
                    recibo.setPlu(rs.getInt("plu"));
                    recibo.setDescripcion(rs.getString("descripcion"));
                    recibo.setCantidad(rs.getDouble("cantidad"));
                    recibo.setCosto(rs.getDouble("costo"));
                    recibo.setProductos_idProductos(rs.getInt("productos_idProductos"));

                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoRecibo.class
                    .getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        }

        return recibo;
    }

    public static List<Recibo> buscarRecibo(String texto) {
        List<Recibo> listaRecibo = new ArrayList<>();

        try {
            con = cn.conectar();

            String sql = "SELECT * FROM recibo "
                    + "WHERE plu LIKE ?"
                    + " OR descripcion LIKE ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Recibo recibo = new Recibo();

                recibo.setIdRecibo(rs.getInt("idRecibo"));
                recibo.setPlu(rs.getInt("plu"));
                recibo.setDescripcion(rs.getString("descripcion"));
                recibo.setCantidad(rs.getDouble("cantidad"));
                recibo.setCosto(rs.getDouble("costo"));
                recibo.setProductos_idProductos(rs.getInt("productos_idProductos"));
                listaRecibo.add(recibo);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listaRecibo;
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
