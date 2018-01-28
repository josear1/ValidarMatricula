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
	
	// Método que espera una excepción de tipo MatriculaVaciaException (da excepcion si la matrícula está vacia).
	@Test (expected = MatriculaVaciaException.class)
	public final void testMatriculaVaciaException()
	{
		try
		{
			vm0.validar();
		}
		catch (MatriculaVaciaException e)
		{
			assertTrue("Excepción \"MatriculaVaciaException\" esperada", true);
		}
		catch (Exception e) { fail("testMatriculaVaciaException() -> " + e.getMessage()); }
	}
	
	// Método que espera una excepción de tipo MatriculaFaltaValidarException 
	//   (excepción que ocurre cuando no se verifica una matrícula antes de incrementarla).
	@Test (expected = MatriculaMaximaException.class)
	public final void testMatriculaMaximaException()
	{
		try
		{
			vm1.incrementarMatricula();
		}
		catch (MatriculaFaltaValidarException e)
		{
			assertTrue("Excepción \"MatriculaFaltaValidarException\" esperada.", true);
		}
		catch (Exception e) { fail("testMatriculaMaximaException() -> " + e.getMessage()); }
	}
}
