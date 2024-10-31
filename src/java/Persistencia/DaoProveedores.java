package Persistencia;

import Config.Conexion;
import Modelo.Proveedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoProveedores {

    static Conexion cn = new Conexion();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    public static boolean grabar(Proveedores proveedores) {
        try {
            con = cn.conectar();
            String sql = "INSERT INTO proveedores (nombres, telefono, domicilio, "
                  +"  correo, documento_idDocumento)"
                    + " VALUES(?,?,?,?,?);";
            ps = con.prepareStatement(sql);

            ps.setString(1, proveedores.getNombres());
            ps.setString(2, proveedores.getTelefono());
            ps.setString(3, proveedores.getDomicilio());
            ps.setString(4, proveedores.getCorreo());
            ps.setInt(5, proveedores.getDocumento_idDocumento());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return false;
    }

    public static List<Proveedores> listar() {
        List<Proveedores> lista = new ArrayList<>();
        try {
            con = cn.conectar();
            String sql = "SELECT * FROM proveedores;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedores proveedor = new Proveedores();

                proveedor.setIdProveedores(rs.getInt("idProveedores"));
                proveedor.setNombres(rs.getString("nombres"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setDomicilio(rs.getString("domicilio"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setDocumento_idDocumento(rs.getInt("documento_idDocumento"));
                lista.add(proveedor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    public static boolean editar(Proveedores proveedores) {
        try {
            con = cn.conectar();
            String sql = "UPDATE proveedores SET nombres = ?,"
                    + "telefono =?, domicilio =?, correo =?,"
                    +"documento_idDocumento=? WHERE idProveedores = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, proveedores.getNombres());
            ps.setString(2, proveedores.getTelefono());
            ps.setString(3, proveedores.getDomicilio());
            ps.setString(4, proveedores.getCorreo());
            ps.setInt(5, proveedores.getDocumento_idDocumento());
            ps.setInt(6, proveedores.getIdProveedores());

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

    public static boolean eliminar(int idProveedores) {
        try {
            con = cn.conectar();
            String sql = "DELETE FROM proveedores WHERE idProveedores=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProveedores);

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

    public static Proveedores obtenerProveedoresPorId(int id) {
        Proveedores proveedor = null;

        String sql = "SELECT * FROM proveedores WHERE idProveedores=?";

        try (Connection con = cn.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    proveedor = new Proveedores();
                    proveedor.setIdProveedores(rs.getInt("idProveedores"));
                    proveedor.setNombres(rs.getString("nombres"));
                    proveedor.setTelefono(rs.getString("telefono"));
                    proveedor.setDomicilio(rs.getString("domicilio"));
                    proveedor.setCorreo(rs.getString("correo"));
                    proveedor.setDocumento_idDocumento(rs.getInt("documento_idDocumento"));

                }
            }
        } catch (SQLException ex) {
            // Registra el error sin mostrar detalles al usuario
            Logger.getLogger(DaoProveedores.class
                    .getName()).log(Level.SEVERE, "Error al acceder a la base de datos", ex);

        }

        return proveedor;
    }

    public static List<Proveedores> buscarProveedores(String texto) {
        List<Proveedores> listaProveedores = new ArrayList<>();

        try {
            con = cn.conectar();

            String sql = "SELECT * FROM proveedores "
                    + "WHERE nombres LIKE ?"
                    + " OR telefono LIKE ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedores proveedor = new Proveedores();
                proveedor.setIdProveedores(rs.getInt("idProveedores"));
                proveedor.setNombres(rs.getString("nombres"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setDomicilio(rs.getString("domicilio"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setDocumento_idDocumento(rs.getInt("documento_IdDocumento"));
                listaProveedores.add(proveedor);
            }
        } catch (SQLException e) {
            // Manejo adecuado de la excepción (log, imprimir, etc.)
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos (ResultSet, PreparedStatement, Connection)
            cerrarRecursos();
        }

        return listaProveedores;
    }

    public static String obtenerNombreProveedores(int id) {
        try {
            con = cn.conectar();
            String sql = "SELECT nombres  FROM proveedores WHERE idProveedores=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nombres");
            } else {
                return "--";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "--";
    }

    // Otros métodos...
    // Método para cerrar los recursos (ResultSet, PreparedStatement, Connection)
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
