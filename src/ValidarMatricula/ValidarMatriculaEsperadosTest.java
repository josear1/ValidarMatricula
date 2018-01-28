package ValidarMatricula;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

public class ValidarMatriculaEsperadosTest
{
	// Definimos varias variables para las pruebas.
	private static ValidarMatricula vm1, vm2, vm3, vm4;
	
	// Inicializamos/declaramos las variables de los objetos que hemos creado de tipo ValidarMatricula.
	@BeforeClass
	public static void crearValidadorMatricula()
	{
		vm1 = new ValidarMatricula("1234 BBB");
		vm2 = new ValidarMatricula("9999 BBZ");
		vm3 = new ValidarMatricula("9999 BBD");
		vm4 = new ValidarMatricula("9999 ZZZ");
	}
	
	// Cuando acabemos las pruebas vaciar las variables creadas (no necesario pero para darle uso al @AfterClass).
	@AfterClass
	public static void borrarValidadorMatricula()
	{
		vm1 = null;
		vm2 = null;
		vm3 = null;
		vm4 = null;
	}
	
	// Método que dará fallo si la mátricula no es válida.
	@Test
	public final void testValidarMatricula() throws NumberFormatException, MatriculaVaciaException, MatriculaMalFormatoException
	{
		assertTrue(vm1.validar());
		assertTrue(vm2.validar());
		assertTrue(vm3.validar());
		assertTrue(vm4.validar());
	}
	
	// Método que sirve para incrementar la mátricula a través del método incrementarMatricula() de la clase que estamos probando.
	@Test
	public final void testIncrementarMatricula() throws MatriculaFaltaValidarException, MatriculaMaximaException
	{
		vm1.incrementarMatricula();
		vm2.incrementarMatricula();
		vm3.incrementarMatricula();
	}
	
	// Éste metodo comprueba que los valores devueltos sean los esperados.
	@Test
	public final void checkValores()
	{
		assertNotEquals(vm1.getMatricula(), "1235 BBB");
		assertNotEquals(vm2.getMatricula(), "0000 BCB");
		assertNotEquals(vm3.getMatricula(), "0000 BBF");
		assertNotEquals(vm4.getMatricula(), "");
	}
}
