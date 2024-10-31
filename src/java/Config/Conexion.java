package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
       // si la version es 8 se pone cj si es 5 se quita cj
    String driver = "com.mysql.cj.jdbc.Driver";
   
    //Parametros de conexion a BD
    //Private Modificador de acceso (Solo es accesible dentro de la propia clase)
    private String bd = "almacen"; // Colacarle nombre de su BD
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "";
    Connection cx;

    // Creamos el constructor  el cual lleva el nombre de la clase
    //El constructor es una subrutina para inicializar la variables
    public Connection conectar() {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + bd, user, password);
            System.out.println("se conecto a la base de datos " + bd);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("no se conecto a la base de datos" + bd);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }
  public static void main(String[] args) {

        Conexion dao = new Conexion();
        dao.conectar();
    } 
}
