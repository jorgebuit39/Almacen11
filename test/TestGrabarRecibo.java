

import Modelo.Recibo;
import Persistencia.DaoRecibo;
import static org.testng.Assert.*;

import org.testng.annotations.Test;


public class TestGrabarRecibo {
    
      private DaoRecibo daoRecibo; // Suponiendo que tengas una clase UsuariosDAO que contiene el método listar()
    
    public TestGrabarRecibo() {
        daoRecibo = new DaoRecibo(); // Instancia de tu DAO
    }

    
    @Test
    public void testGrabar() {
        
       Recibo recibo = new Recibo();
        recibo.setPlu(123456);
        recibo.setDescripcion("correo@example3.com");
        recibo.setCantidad(25.5);
        recibo.setCosto(5850.25);
        recibo.setProductos_idProductos(1); // Id de perfil válido
        try {
             // Comprueba si el método devuelve true
            assertTrue(daoRecibo.grabar(recibo));
            System.out.println("Registro Exitoso en BD");

        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage()); // Si se lanza una excepción, la prueba falla
        }
    }    
        }
   

