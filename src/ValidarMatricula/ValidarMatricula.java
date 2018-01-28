package ValidarMatricula;

/**
 * Clase que nos sirve para comprobar si una matrícula es válida en España a partir del año 2000 en adelante.
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
	 * Guarda los 4 primeros dígitos de la matrícula que son numeros enteros.
	 */
	private int numeros; 		// Se guardan los 4 primeros dígitos de la matrícula que son numeros enteros.
	
	/**
	 * Guarda las 3 letras (los 3 últimos caracteres) de la matrícula.
	 */
	private String letras; 		// Se guardan las 3 letras (los 3 últimos caracteres) de la matrícula.
	
	// Constructor default (sin parámetros), inicializa las variables (ints = 0; strings = "").
	/**
	 * Constructor por defecto sin parámetros, éste inicializa las variables a valores por defecto (vacios y ceros).
	 */
	public ValidarMatricula()
	{
		setMatricula("");
		setNumeros(0);
		setLetras("");
	}
	
	// Constructor con un parámatro, inicializa la variable matricula con el valor del primer parámetro introducido.
	/**
	 * Constructor en el que se introduce la mátricula en forma de string (Ejemplo: "0000 BBB").
	 * @param m
	 * Parámetro para introducir la matrícula en forma de string.
	 */
	public ValidarMatricula(String m)
	{
		setMatricula(m);
		setNumeros(0);
		setLetras("");
	}
	
	// Getters y setters para acceder a los valores de las variables o establecer sus valores
	/**
	 * Método que devuelve la matrícula introducida.
	 * @return
	 * devuelve la matrícula tal cual ha sido insertada.
	 */
	public String getMatricula() { return matricula; }
	
	/**
	 * Método para declarar la matrícula y sea usada en la clase correctamente (si directamente no se introduce a través del constructor).
	 * @param m
	 * Matrícula (Ejemplo: "0000 BBB").
	 */
	public void setMatricula(String m) { matricula = m; }
	
	// El método getNumerosStr() devuelve string por estética para mostrar 4 caracteres.
	/**
	 * Método que nos muestra la matrícula en un formato esperado.
	 * @return
	 * Devuelve la matrícula introducida en formato "<strong>NNNN XXX</strong>" (donde <strong>N</strong> son números y <strong>X</strong> son letras).
	 */
	public String getNumerosStr() { return String.format("%04d", numeros); }
	
	/**
	 * Método que devuelve la parte numérica de la matrícula introducida.
	 * @return
	 * Devuelve como <strong>int</strong> la parte numérica de la matrícula.
	 */
	public int getNumeros() { return numeros; }
	
	/**
	 * Método para introducir de manera manual la parte numérica de la matrícula.
	 * @param n
	 * Parte numérica de la matrícula.
	 */
	public void setNumeros(int n) { numeros = n; }
	
	/**
	 * Método que devuelve la parte de las letras de la matrícula introducida.
	 * @return
	 * Devuelve como <strong>string</strong> la parte de las letras de la matrícula.
	 */
	public String getLetras() { return letras; }
	
	/**
	 * Método para introducir de manera manual la parte de las letras de la matrícula.
	 * @param l
	 * Parte de las letras de la matrícula.
	 */
	public void setLetras(String l) { letras = l; }
	
	/*
		Método que comprueba si la matrícula es válida, si todas las comprobaciones son correctas, devolverá true.
			En caso contrario devolverá excepciones.
	 
	 	Se darán excepciones en los siguientes casos:
			- Si la matricula no se ha definido dará una excepción de tipo MatriculaVaciaException indicándolo.
			- Si la matrícula definida no tiene el formato esperado (comprobado con regex) dará excepción de tipo MatriculaMalFormatoException.
			- Si no se puede parsear la parte de los numeros en la matrícula dará excepción de tipo NumberFormatException (aunque previamente se comprueba que la parte a parsear sea numérica mediante regex).
	*/
	/**
	 * Método que comprueba si la matrícula es válida, si todas las comprobaciones son correctas, devolverá true. En caso contrario devolverá excepciones.
	 * @return
	 * true si todas las comprobaciones son correctas y no hemos tenido excepciones.
	 * @throws MatriculaVaciaException
	 * Si la matricula no se ha definido.
	 * @throws MatriculaMalFormatoException
	 * Si la matrícula definida no tiene el formato esperado.
	 * @throws NumberFormatException
	 * Si no se puede parsear la parte numérica en la matrícula.
	 */
	public boolean validar() throws MatriculaVaciaException, MatriculaMalFormatoException, NumberFormatException
	{
		// Si la matrícula no esta definida, dar excepción MatriculaVaciaException.
		if (getMatricula().isEmpty())
			throw new MatriculaVaciaException("Debes especificar la matricula antes de validarla.");
		
		// Se comprueba con expresiones regulares si el formato de la matrícula es correcto. 
		// Se hacen tres grupos "0000 BBB" (4 numeros del 0 al 9; un espacio; 3 letras sean o no mayusculas) - con el ?i lo hacemos no sensitivo
		// En caso de no ser correcto damos una excepción de tipo MatriculaMalFormatoException.
		if (!getMatricula().matches("[0-9]{4}[ ](?i)[b-z&&[^eioqu]]{3}"))
			throw new MatriculaMalFormatoException("Formato no válido (ejemplo formato válido: \"0000 BBB\").");
		
		// Le asignamos los valores correspondientes a las variables.
		setNumeros(Integer.parseInt(getMatricula().substring(0, 4)));
		
		// Le asignamos la parte de las letras convertido a mayúsculas por si acaso la introducen en minúsculas.
		setLetras(getMatricula().substring(5, 8).toUpperCase());
		
		// Le asignamos a la variable matricula el valor de la matrícula.
		setMatricula(getNumerosStr() + " " + getLetras());
		
		// Todo correcto, devolvemos true.
		return true;
	}
	
	/*
	 	Método que genera la siguiente matrícula a la introducida.
	 	
		Se darán excepciones si:
			- La parte numérica o la de letars no están definidas porque no se ha llamado al método validar() anteriormente (MatriculaFaltaValidarException).
			- Se intenta generar la siguiente matrícula a la máxima posible "9999 ZZZ" (MatriculaMaximaException).
	*/
	/**
	 * Método que genera la siguiente matrícula a la introducida.
	 * @throws MatriculaFaltaValidarException
	 * La parte numérica o la de letars no están definidas porque no se ha llamado al método validar() anteriormente.
	 * @throws MatriculaMaximaException
	 * Se intenta generar la siguiente matrícula a la máxima posible "9999 ZZZ".
	 */
	public void incrementarMatricula() throws MatriculaFaltaValidarException, MatriculaMaximaException
	{
		// Si la parte numérica o de letras de la matrícula no están definidas llamamos al método validar() para que nos las defina.
		if (getNumeros() == 0 && getLetras().isEmpty())
			throw new MatriculaFaltaValidarException("No se ha validado la matrícula, debes validarla mediante el método validar() antes de incrementarla.");
		
		// Si vamos por la matrícula maxima "9999 ZZZ" dar excepcion MatriculaMaximaException.
		if (getNumeros() == 9999 && getLetras().equals("ZZZ"))
			throw new MatriculaMaximaException("Última matrícula alcanzada, no se pueden generar mas!");
		
		// Si al incrementar la parte de los números va a llegar a su límite (9999) 
		// entonces entramos en la condición sino incrementamos en 1 la parte de los números.
		if (getNumeros() == 9999)
		{
			// Ponemos la parte numérica a 0.
			setNumeros(0);
			
			// Convertimos la parte de las letras de la matrícula a un array (para comparar caracter por caracter).
			char[] letrasTemp = getLetras().toCharArray();
			
			// Guardamos en un array las letras posibles que pueden aparecer en la matrícula (solo consonantes y se quitan la Ñ y la Q).
			char[] letrasPosibles = "BCDFGHJKLMNPRSTVWXYZ".toCharArray();
			
			// Bool para comprobar si se ha cambiado/comprobado la primera letra de la matrícula y no causar una excepción de tipo ArrayIndexOutOfBoundsException.
			boolean ultimaLetra = false;
			
			// Recorremos letra por letra de la matrícula.
			for (int j = letrasTemp.length - 1; j > 0; j--)
			{
				// Si ya hemos comprobado/cambiado la primera letra de la matrícula no hacer la iteración j = 0.
				if (ultimaLetra)
					break;
				
				// Recorremos la variable de las letras posibles para comparar si una de las 3 letras llegan a la letra límite (Z) y poder 
				// modificar la letra anterior y la actual ponerla a la primera letra posible (B).
				for (int i = 0; i < letrasPosibles.length; i++)
				{
					// Si ambas letras coinciden
					if (letrasTemp[j] == letrasPosibles[i])
					{
						// Comprobamos si es la última letra de la matrícula y directamente le ponemos la siguiente letra
						if (j == (letrasTemp.length - 1))
						{
							for (int c = 0; c < letrasPosibles.length; c++)
							{
								// Si damos con la letra que estamos buscando
								if (letrasTemp[j] == letrasPosibles[c])
								{
									// Si la letra que comprobamos no es la misma que la última posible, le ponemos la siguiente posible
									if (c < letrasPosibles.length - 1)
									{
										letrasTemp[j] = letrasPosibles[c + 1];
										break;
									}
								}
							}
						}
						
						// Si la letra que estamos comprobando es la última letra posible (Z) nos pondrá ultimaLetra a true.
						if (checkLastLetter(letrasTemp, letrasPosibles, j, i))
							ultimaLetra = true;
						
						break;
					}
				}
			}
			
			// Declaramos la nueva parte de las letras.
			setLetras(new String(letrasTemp));
		}
		else // Incrementamos 1 la parte de los números.
			setNumeros(getNumeros() + 1);
	}
	
	// Método que devuelve la matrícula para el usuario final (Formato: "0000 BBB").
	/**
	 * Método que devuelve la matrícula lista para ser imprimida al usuario final.
	 * @return
	 * Matrícula en el formato esperado "0000 BBB" (4 números seguidos de un espacio y 3 letras mayusculas).
	 */
	@Override
	public String toString()
	{
		return getNumerosStr() + " " + getLetras();
	}

	/**
	 * Método de uso interno a la hora de incrementar la matrícula, sirve para comprobar si debemos salir del bucle for principal antes de lo esperado.
	 * @param letrasTemp
	 * char[]
	 * @param letrasPosibles
	 * char[]
	 * @param j
	 * int
	 * @param i
	 * int
	 * @return
	 * true si no podemos aumentar la primera letra de la matrícula.
	 * false si lo anterior es posible.
	 */
	private boolean checkLastLetter(char[] letrasTemp, char[] letrasPosibles, int j, int i)
	{
		// Si la letra que estamos comprobando es la última letra posible (Z).
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
					// Si la letra que comprobamos es la misma que la última posible
					if (c == letrasPosibles.length - 1)
					{
						// Si lo que intentamos es cambiar la primera letra de la matrícula, no hacerlo y sumar +1 en la parte numérica y saltar a la siguiente iteración.
						if ((j - 1) == 0)
						{
							setNumeros(getNumeros() + 1);
							break;
						}
					}
					else // Si no es la última letra, entonces poner la siguiente posible
					{
						// Ponemos la siguiente letra posible
						letrasTemp[j - 1] = letrasPosibles[c + 1];
						
						// Si intentamos cambiar la primera letra de la matrícula, no hacerlo y salir del for principal (debido al bool ultimaLetra) y del actual.
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
