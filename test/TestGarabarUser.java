
import Modelo.Usuarios;
import Persistencia.DaoUsuarios;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestGarabarUser {

    private DaoUsuarios daoUsuarios; // Suponiendo que tengas una clase UsuariosDAO que contiene el método listar()

    public TestGarabarUser() {
        daoUsuarios = new DaoUsuarios(); // Instancia de tu DAO
    }

    @Test
    public void testGrabar() {
        Usuarios usuario = new Usuarios();
        usuario.setNombres("Test Jorge");
        usuario.setCorreo("correo@example3.com");
        usuario.setUsuario("jd");
        usuario.setClave("cc3");
        usuario.setPerfil_idPerfil(1); // Id de perfil válido
        try {
             // Comprueba si el método devuelve true
            assertTrue(daoUsuarios.grabar(usuario));
            System.out.println("Registro Exitoso en BD");

        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage()); // Si se lanza una excepción, la prueba falla
        }
    }
    
    

    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
