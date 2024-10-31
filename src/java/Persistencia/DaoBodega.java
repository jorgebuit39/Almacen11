package Persistencia;

import Config.Conexion;
import Modelo.Bodega;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoBodega {

    static Conexion cn = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
    static Bodega bodega = new Bodega();

    public static boolean grabar(Bodega bodega) {
        try {
            con = cn.conectar();
            String sql = "INSERT INTO bodega(cantidadInventario,unidadMedida,costo, "
                    + "productos_idProductos,recibo_idRecibo) "
                    + "VALUES(?,?,?,?,?);";
            ps = con.prepareStatement(sql);

            ps.setDouble(1, bodega.getCantidadInventario());
            ps.setString(2, bodega.getUnidadMedida());
            ps.setDouble(3, bodega.getCosto());
            ps.setInt(4, bodega.getProductos_idProductos());
            ps.setInt(5, bodega.getRecibo_idRecibo());

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

    public static List<Bodega> listar() {
        List<Bodega> lista = new ArrayList<>();
        try {
            con = cn.conectar();
            String sql = "SELECT * FROM bodega;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bodega bodega1 = new Bodega();
                bodega1.setIdBodega(rs.getInt("idBodega"));
                bodega1.setCantidadInventario(rs.getDouble("cantidadInventario"));
                bodega1.setUnidadMedida(rs.getString("unidadMedida"));
                bodega1.setCosto(rs.getDouble("costo"));
                bodega1.setProductos_idProductos(rs.getInt("productos_idproductos"));
                bodega1.setRecibo_idRecibo(rs.getInt("recibo_idRecibo"));

                lista.add(bodega1);
            }
        } catch (Exception e) {
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    //Se debe asegurar el mismo orden
    public static boolean editar(Bodega bodega) {
        try {
            con = cn.conectar();
            String sql = "UPDATE bodega SET cantidadInventario = ?, "
                    + "unidadMedida =?, costo =?, productos_idProductos =?, "
                    + "recibo_idRecibo =? WHERE idBodega =?";

            ps = con.prepareStatement(sql);
            ps.setDouble(1, bodega.getCantidadInventario());
            ps.setString(2, bodega.getUnidadMedida());
            ps.setDouble(3, bodega.getCosto());
            ps.setInt(4, bodega.getProductos_idProductos());
            ps.setInt(5, bodega.getRecibo_idRecibo());

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

    public static boolean eliminar(int idBodega) {
        try {
            con = cn.conectar();
            String sql = "DELETE FROM bodega WHERE idBodega=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idBodega);

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
    public static Bodega obtenerBodegaPorId(int id) {
        Bodega bodega = null;

        String sql = "SELECT * FROM bodega WHERE idBodega=?";

        try (Connection con = cn.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bodega = new Bodega();

                    bodega.setIdBodega(rs.getInt("idBodega"));
                    bodega.setCantidadInventario(rs.getDouble("cantidadInventario"));
                    bodega.setUnidadMedida(rs.getString("unidadMedida"));
                    bodega.setCosto(rs.getDouble("costo"));
                    bodega.setProductos_idProductos(rs.getInt("productos_idproductos"));
                    bodega.setRecibo_idRecibo(rs.getInt("recibo_idRecibo"));

                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoBodega.class
                    .getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        }

        return bodega;
    }

    public static List<Bodega> buscarBodega(String texto) {
        List<Bodega> listaBodega = new ArrayList<>();

        try {
            con = cn.conectar();

            String sql = "SELECT * FROM bodega "
                    + "WHERE cantidadInventarioLIKE ?"
                    + " OR bodega LIKE ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Bodega bodega1 = new Bodega();
                
                bodega1.setIdBodega(rs.getInt("idBodega"));
                bodega1.setCantidadInventario(rs.getDouble("cantidadInventario"));
                bodega1.setUnidadMedida(rs.getString("unidadMedida"));
                bodega1.setCosto(rs.getDouble("costo"));
                bodega1.setProductos_idProductos(rs.getInt("productos_idproductos"));
                bodega1.setRecibo_idRecibo(rs.getInt("recibo_idRecibo"));

                listaBodega.add(bodega1);

            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listaBodega;
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
