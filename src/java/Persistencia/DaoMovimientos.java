package Persistencia;

import Config.Conexion;
import Modelo.Movimientos;
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
public class DaoMovimientos {

    static Conexion cn = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
    static Movimientos movimientos = new Movimientos();

    public static boolean grabar(Movimientos movimientos) {
        try {
            con = cn.conectar();
            String sql = "INSERT INTO movimientos(nombre, fecha, cantidad, productos_idProductos) "
                    + "VALUES(?,?,?,?);";

            ps = con.prepareStatement(sql);

            ps.setString(1, movimientos.getNombre());
            ps.setString(2, movimientos.getFecha());
            ps.setDouble(3, movimientos.getCantidad());
            ps.setInt(4, movimientos.getProductos_idProductos());

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

    public static List<Movimientos> listar() {
        List<Movimientos> lista = new ArrayList<>();
        try {
            con = cn.conectar();
            String sql = "SELECT * FROM movimientos;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Movimientos movimientos1 = new Movimientos();

                movimientos1.setIdMovimientos(rs.getInt("idMovimientos"));
                movimientos1.setNombre(rs.getString("nombre"));
                movimientos1.setFecha(rs.getString("fecha"));
                movimientos1.setCantidad(rs.getDouble("cantidad"));
                movimientos1.setProductos_idProductos(rs.getInt("productos_idProductos"));
                lista.add(movimientos1);
            }
        } catch (Exception e) {
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    //Se debe asegurar el mismo orden
    public static boolean editar(Movimientos movimientos) {
        try {
            con = cn.conectar();
            String sql = "UPDATE movimientos SET nombre =?,fecha = ?, "
                    + " cantidad =?, productos_idProductos =?,"
                    + " WHERE idMovimientos =?";

            ps = con.prepareStatement(sql);

            ps.setString(1, movimientos.getNombre());
            ps.setString(2, movimientos.getFecha());
            ps.setDouble(3, movimientos.getCantidad());
            ps.setInt(4, movimientos.getProductos_idProductos());
            ps.setInt(6, movimientos.getIdMovimientos());
            
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

    public static boolean eliminar(int idMovimientos) {
        try {
            con = cn.conectar();
            String sql = "DELETE FROM movimientos WHERE idMovimientos=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMovimientos);

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
    public static Movimientos obtenerMovimientosPorId(int id) {
        Movimientos movimientos = null;

        String sql = "SELECT * FROM movimientos WHERE idMovimientos=?";

        try (Connection con = cn.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    movimientos = new Movimientos();

                    movimientos.setIdMovimientos(rs.getInt("idMovimientos"));
                    movimientos.setNombre(rs.getString("nombre"));
                    movimientos.setFecha(rs.getString("fecha"));
                    movimientos.setCantidad(rs.getDouble("cantidad"));
                    movimientos.setProductos_idProductos(rs.getInt("productos_idProductos"));

                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoMovimientos.class
                    .getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        }

        return movimientos;
    }

    public static List<Movimientos> buscarMovimientos(String texto) {
        List<Movimientos> listaMovimientos = new ArrayList<>();

        try {
            con = cn.conectar();

            String sql = "SELECT * FROM Movimientos "
                    + "WHERE nombre LIKE ?"
                    + " OR fecha  LIKE ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Movimientos movimientos1 = new Movimientos();

                movimientos1.setIdMovimientos(rs.getInt("idMovimientos"));
                movimientos1.setNombre(rs.getString("nombre"));
                movimientos1.setFecha(rs.getString("fecha"));
                movimientos1.setCantidad(rs.getDouble("cantidad"));
                movimientos1.setProductos_idProductos(rs.getInt("productos_idProductos"));
                listaMovimientos.add(movimientos1);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listaMovimientos;
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
