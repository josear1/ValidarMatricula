package ValidarMatricula;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValidarMatriculaAssertsTest
{
	// Definimos las variables de tipo ValidarMatricula.
	private static ValidarMatricula vm0, vm1;
	
	// Inicializamos/declaramos las variables.
	@BeforeClass
	public static void crearValidadorMatricula()
	{
		vm0 = new ValidarMatricula();
		vm1 = new ValidarMatricula("9999 ZZZ");
	}
	
	// Vaciamos las variables (no necesario pero para darle uso al @AfterClass).
	@AfterClass
	public static void borrarValidadorMatricula()
	{
		vm0 = null;
		vm1 = null;
	}
	
	// M�todo que espera una excepci�n de tipo MatriculaVaciaException (da excepcion si la matr�cula est� vacia).
	@Test (expected = MatriculaVaciaException.class)
	public final void testMatriculaVaciaException()
	{
		try
		{
			vm0.validar();
		}
		catch (MatriculaVaciaException e)
		{
			assertTrue("Excepci�n \"MatriculaVaciaException\" esperada", true);
		}
		catch (Exception e) { fail("testMatriculaVaciaException() -> " + e.getMessage()); }
	}
	
	// M�todo que espera una excepci�n de tipo MatriculaFaltaValidarException 
	//   (excepci�n que ocurre cuando no se verifica una matr�cula antes de incrementarla).
	@Test (expected = MatriculaMaximaException.class)
	public final void testMatriculaMaximaException()
	{
		try
		{
			vm1.incrementarMatricula();
		}
		catch (MatriculaFaltaValidarException e)
		{
			assertTrue("Excepci�n \"MatriculaFaltaValidarException\" esperada.", true);
		}
		catch (Exception e) { fail("testMatriculaMaximaException() -> " + e.getMessage()); }
	}
}
