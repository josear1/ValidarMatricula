package ValidarMatricula;

/**
 * Clase que nos sirve para comprobar si una matr�cula es v�lida en Espa�a a partir del a�o 2000 en adelante.
 * @author Jose AR
 * @version 1.0
 */
public class ValidarMatricula
{
	/**
	 * Guarda la matricula en su formato por defecto ("0000 BBB").
	 */
	private String matricula; 	// Se guarda la matricula en su formato por defecto ("0000 BBB")
	
	/**
	 * Guarda los 4 primeros d�gitos de la matr�cula que son numeros enteros.
	 */
	private int numeros; 		// Se guardan los 4 primeros d�gitos de la matr�cula que son numeros enteros.
	
	/**
	 * Guarda las 3 letras (los 3 �ltimos caracteres) de la matr�cula.
	 */
	private String letras; 		// Se guardan las 3 letras (los 3 �ltimos caracteres) de la matr�cula.
	
	// Constructor default (sin par�metros), inicializa las variables (ints = 0; strings = "").
	/**
	 * Constructor por defecto sin par�metros, �ste inicializa las variables a valores por defecto (vacios y ceros).
	 */
	public ValidarMatricula()
	{
		setMatricula("");
		setNumeros(0);
		setLetras("");
	}
	
	// Constructor con un par�matro, inicializa la variable matricula con el valor del primer par�metro introducido.
	/**
	 * Constructor en el que se introduce la m�tricula en forma de string (Ejemplo: "0000 BBB").
	 * @param m
	 * Par�metro para introducir la matr�cula en forma de string.
	 */
	public ValidarMatricula(String m)
	{
		setMatricula(m);
		setNumeros(0);
		setLetras("");
	}
	
	// Getters y setters para acceder a los valores de las variables o establecer sus valores
	/**
	 * M�todo que devuelve la matr�cula introducida.
	 * @return
	 * devuelve la matr�cula tal cual ha sido insertada.
	 */
	public String getMatricula() { return matricula; }
	
	/**
	 * M�todo para declarar la matr�cula y sea usada en la clase correctamente (si directamente no se introduce a trav�s del constructor).
	 * @param m
	 * Matr�cula (Ejemplo: "0000 BBB").
	 */
	public void setMatricula(String m) { matricula = m; }
	
	// El m�todo getNumerosStr() devuelve string por est�tica para mostrar 4 caracteres.
	/**
	 * M�todo que nos muestra la matr�cula en un formato esperado.
	 * @return
	 * Devuelve la matr�cula introducida en formato "<strong>NNNN XXX</strong>" (donde <strong>N</strong> son n�meros y <strong>X</strong> son letras).
	 */
	public String getNumerosStr() { return String.format("%04d", numeros); }
	
	/**
	 * M�todo que devuelve la parte num�rica de la matr�cula introducida.
	 * @return
	 * Devuelve como <strong>int</strong> la parte num�rica de la matr�cula.
	 */
	public int getNumeros() { return numeros; }
	
	/**
	 * M�todo para introducir de manera manual la parte num�rica de la matr�cula.
	 * @param n
	 * Parte num�rica de la matr�cula.
	 */
	public void setNumeros(int n) { numeros = n; }
	
	/**
	 * M�todo que devuelve la parte de las letras de la matr�cula introducida.
	 * @return
	 * Devuelve como <strong>string</strong> la parte de las letras de la matr�cula.
	 */
	public String getLetras() { return letras; }
	
	/**
	 * M�todo para introducir de manera manual la parte de las letras de la matr�cula.
	 * @param l
	 * Parte de las letras de la matr�cula.
	 */
	public void setLetras(String l) { letras = l; }
	
	/*
		M�todo que comprueba si la matr�cula es v�lida, si todas las comprobaciones son correctas, devolver� true.
			En caso contrario devolver� excepciones.
	 
	 	Se dar�n excepciones en los siguientes casos:
			- Si la matricula no se ha definido dar� una excepci�n de tipo MatriculaVaciaException indic�ndolo.
			- Si la matr�cula definida no tiene el formato esperado (comprobado con regex) dar� excepci�n de tipo MatriculaMalFormatoException.
			- Si no se puede parsear la parte de los numeros en la matr�cula dar� excepci�n de tipo NumberFormatException (aunque previamente se comprueba que la parte a parsear sea num�rica mediante regex).
	*/
	/**
	 * M�todo que comprueba si la matr�cula es v�lida, si todas las comprobaciones son correctas, devolver� true. En caso contrario devolver� excepciones.
	 * @return
	 * true si todas las comprobaciones son correctas y no hemos tenido excepciones.
	 * @throws MatriculaVaciaException
	 * Si la matricula no se ha definido.
	 * @throws MatriculaMalFormatoException
	 * Si la matr�cula definida no tiene el formato esperado.
	 * @throws NumberFormatException
	 * Si no se puede parsear la parte num�rica en la matr�cula.
	 */
	public boolean validar() throws MatriculaVaciaException, MatriculaMalFormatoException, NumberFormatException
	{
		// Si la matr�cula no esta definida, dar excepci�n MatriculaVaciaException.
		if (getMatricula().isEmpty())
			throw new MatriculaVaciaException("Debes especificar la matricula antes de validarla.");
		
		// Se comprueba con expresiones regulares si el formato de la matr�cula es correcto. 
		// Se hacen tres grupos "0000 BBB" (4 numeros del 0 al 9; un espacio; 3 letras sean o no mayusculas) - con el ?i lo hacemos no sensitivo
		// En caso de no ser correcto damos una excepci�n de tipo MatriculaMalFormatoException.
		if (!getMatricula().matches("[0-9]{4}[ ](?i)[b-z&&[^eioqu]]{3}"))
			throw new MatriculaMalFormatoException("Formato no v�lido (ejemplo formato v�lido: \"0000 BBB\").");
		
		// Le asignamos los valores correspondientes a las variables.
		setNumeros(Integer.parseInt(getMatricula().substring(0, 4)));
		
		// Le asignamos la parte de las letras convertido a may�sculas por si acaso la introducen en min�sculas.
		setLetras(getMatricula().substring(5, 8).toUpperCase());
		
		// Le asignamos a la variable matricula el valor de la matr�cula.
		setMatricula(getNumerosStr() + " " + getLetras());
		
		// Todo correcto, devolvemos true.
		return true;
	}
	
	/*
	 	M�todo que genera la siguiente matr�cula a la introducida.
	 	
		Se dar�n excepciones si:
			- La parte num�rica o la de letars no est�n definidas porque no se ha llamado al m�todo validar() anteriormente (MatriculaFaltaValidarException).
			- Se intenta generar la siguiente matr�cula a la m�xima posible "9999 ZZZ" (MatriculaMaximaException).
	*/
	/**
	 * M�todo que genera la siguiente matr�cula a la introducida.
	 * @throws MatriculaFaltaValidarException
	 * La parte num�rica o la de letars no est�n definidas porque no se ha llamado al m�todo validar() anteriormente.
	 * @throws MatriculaMaximaException
	 * Se intenta generar la siguiente matr�cula a la m�xima posible "9999 ZZZ".
	 */
	public void incrementarMatricula() throws MatriculaFaltaValidarException, MatriculaMaximaException
	{
		// Si la parte num�rica o de letras de la matr�cula no est�n definidas llamamos al m�todo validar() para que nos las defina.
		if (getNumeros() == 0 && getLetras().isEmpty())
			throw new MatriculaFaltaValidarException("No se ha validado la matr�cula, debes validarla mediante el m�todo validar() antes de incrementarla.");
		
		// Si vamos por la matr�cula maxima "9999 ZZZ" dar excepcion MatriculaMaximaException.
		if (getNumeros() == 9999 && getLetras().equals("ZZZ"))
			throw new MatriculaMaximaException("�ltima matr�cula alcanzada, no se pueden generar mas!");
		
		// Si al incrementar la parte de los n�meros va a llegar a su l�mite (9999) 
		// entonces entramos en la condici�n sino incrementamos en 1 la parte de los n�meros.
		if (getNumeros() == 9999)
		{
			// Ponemos la parte num�rica a 0.
			setNumeros(0);
			
			// Convertimos la parte de las letras de la matr�cula a un array (para comparar caracter por caracter).
			char[] letrasTemp = getLetras().toCharArray();
			
			// Guardamos en un array las letras posibles que pueden aparecer en la matr�cula (solo consonantes y se quitan la � y la Q).
			char[] letrasPosibles = "BCDFGHJKLMNPRSTVWXYZ".toCharArray();
			
			// Bool para comprobar si se ha cambiado/comprobado la primera letra de la matr�cula y no causar una excepci�n de tipo ArrayIndexOutOfBoundsException.
			boolean ultimaLetra = false;
			
			// Recorremos letra por letra de la matr�cula.
			for (int j = letrasTemp.length - 1; j > 0; j--)
			{
				// Si ya hemos comprobado/cambiado la primera letra de la matr�cula no hacer la iteraci�n j = 0.
				if (ultimaLetra)
					break;
				
				// Recorremos la variable de las letras posibles para comparar si una de las 3 letras llegan a la letra l�mite (Z) y poder 
				// modificar la letra anterior y la actual ponerla a la primera letra posible (B).
				for (int i = 0; i < letrasPosibles.length; i++)
				{
					// Si ambas letras coinciden
					if (letrasTemp[j] == letrasPosibles[i])
					{
						// Comprobamos si es la �ltima letra de la matr�cula y directamente le ponemos la siguiente letra
						if (j == (letrasTemp.length - 1))
						{
							for (int c = 0; c < letrasPosibles.length; c++)
							{
								// Si damos con la letra que estamos buscando
								if (letrasTemp[j] == letrasPosibles[c])
								{
									// Si la letra que comprobamos no es la misma que la �ltima posible, le ponemos la siguiente posible
									if (c < letrasPosibles.length - 1)
									{
										letrasTemp[j] = letrasPosibles[c + 1];
										break;
									}
								}
							}
						}
						
						// Si la letra que estamos comprobando es la �ltima letra posible (Z) nos pondr� ultimaLetra a true.
						if (checkLastLetter(letrasTemp, letrasPosibles, j, i))
							ultimaLetra = true;
						
						break;
					}
				}
			}
			
			// Declaramos la nueva parte de las letras.
			setLetras(new String(letrasTemp));
		}
		else // Incrementamos 1 la parte de los n�meros.
			setNumeros(getNumeros() + 1);
	}
	
	// M�todo que devuelve la matr�cula para el usuario final (Formato: "0000 BBB").
	/**
	 * M�todo que devuelve la matr�cula lista para ser imprimida al usuario final.
	 * @return
	 * Matr�cula en el formato esperado "0000 BBB" (4 n�meros seguidos de un espacio y 3 letras mayusculas).
	 */
	@Override
	public String toString()
	{
		return getNumerosStr() + " " + getLetras();
	}

	/**
	 * M�todo de uso interno a la hora de incrementar la matr�cula, sirve para comprobar si debemos salir del bucle for principal antes de lo esperado.
	 * @param letrasTemp
	 * char[]
	 * @param letrasPosibles
	 * char[]
	 * @param j
	 * int
	 * @param i
	 * int
	 * @return
	 * true si no podemos aumentar la primera letra de la matr�cula.
	 * false si lo anterior es posible.
	 */
	private boolean checkLastLetter(char[] letrasTemp, char[] letrasPosibles, int j, int i)
	{
		// Si la letra que estamos comprobando es la �ltima letra posible (Z).
		if (i == (letrasPosibles.length - 1))
		{
			// Ponemos dicha letra a la primera posible (B).
			letrasTemp[j] = letrasPosibles[0];
			
			// Y la anterior la cambiamos a la siguiente posible.
			for (int c = 0; c < letrasPosibles.length; c++)
			{
				// Si damos con la letra que estamos buscando
				if (letrasTemp[j - 1] == letrasPosibles[c])
				{
					// Si la letra que comprobamos es la misma que la �ltima posible
					if (c == letrasPosibles.length - 1)
					{
						// Si lo que intentamos es cambiar la primera letra de la matr�cula, no hacerlo y sumar +1 en la parte num�rica y saltar a la siguiente iteraci�n.
						if ((j - 1) == 0)
						{
							setNumeros(getNumeros() + 1);
							break;
						}
					}
					else // Si no es la �ltima letra, entonces poner la siguiente posible
					{
						// Ponemos la siguiente letra posible
						letrasTemp[j - 1] = letrasPosibles[c + 1];
						
						// Si intentamos cambiar la primera letra de la matr�cula, no hacerlo y salir del for principal (debido al bool ultimaLetra) y del actual.
						if ((j - 1) == 0)
							return true;
					}
					break;
				}
			}
		}
		
		return false;
	}
	
	/* 
	 * Creado el main para hacer testeos correspondientes antes de crear los test con junit.
	 * 
	public static void main(String[] args)
	{
		ValidarMatricula vm = new ValidarMatricula("1100 BBB");
		try
		{
			vm.validar();
			System.out.print("#1 >> " + vm.getNumerosStr() + " -- " + vm.getLetras() + " -> ");
			vm.incrementarMatricula();
			System.out.print(vm.getNumerosStr() + " -- " + vm.getLetras() + "\n");
			
			vm.setMatricula("9999 CCX");
			vm.validar();
			System.out.print("#2 >> " + vm.getNumerosStr() + " -- " + vm.getLetras() + " -> ");
			vm.incrementarMatricula();
			System.out.print(vm.getNumerosStr() + " -- " + vm.getLetras() + "\n");
			
			System.out.println("--------------------------");
			
			vm.setMatricula("1234 BBB");
			vm.validar();
			System.out.print("#3 >> " + vm.getNumerosStr() + " -- " + vm.getLetras() + " -> ");
			vm.incrementarMatricula();
			System.out.print(vm.getNumerosStr() + " -- " + vm.getLetras() + "\n");
			
			vm.setMatricula("9999 BBZ");
			vm.validar();
			System.out.print("#4 >> " + vm.getNumerosStr() + " -- " + vm.getLetras() + " -> ");
			vm.incrementarMatricula();
			System.out.print(vm.getNumerosStr() + " -- " + vm.getLetras() + "\n");
			
			vm.setMatricula("9999 BBD");
			vm.validar();
			System.out.print("#5 >> " + vm.getNumerosStr() + " -- " + vm.getLetras() + " -> ");
			vm.incrementarMatricula();
			System.out.print(vm.getNumerosStr() + " -- " + vm.getLetras() + "\n");
			
			vm.setMatricula("9999 ZZZ");
			vm.validar();
			System.out.print("#6 >> " + vm.getNumerosStr() + " -- " + vm.getLetras() + " -> ");
			vm.incrementarMatricula();
			System.out.print(vm.getNumerosStr() + " -- " + vm.getLetras() + "\n");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}*/
}
