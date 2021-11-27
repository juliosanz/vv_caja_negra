package com.practica.cajanegra.pruebasadiciones;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.cajanegra.SingleLinkedListImpl;
import com.practica.cajanegra.Utilidad;

public class addNTimesTest {
	private static SingleLinkedListImpl<String> vacia;

	private static SingleLinkedListImpl<String> nElementos;
	
	
	
	@BeforeEach
	public void init() {
		vacia = new SingleLinkedListImpl<String>();
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
	}
	
	@ParameterizedTest
	@MethodSource("getAddNTimesParameters")
	void addNTimesTests(String letra, int n, boolean pruebaVacia)
	{
		SingleLinkedListImpl<String> lista;
		if(pruebaVacia)
		{
			lista = vacia;
		}
		else
		{
			lista = nElementos;
		}
		// Si n es negativo, comprobamos que lance la excepcion que debe
        // lanzar segun la documentacion.
		if(n < 0)
		{
			assertThrows(IllegalArgumentException.class, () -> {
				lista.addNTimes(letra, n);
			});
		}
		else
		{
			String[] elementosIniciales = Utilidad.getLetras(lista);
			// Llegados a este bloque, n es mayor o igual que cero por el
			// if anterior, luego la excepcion no debería lanzarse.
			try
			{
				lista.addNTimes(letra, n);
			}
			catch(IllegalArgumentException e)
			{
				fail("Excepcion inesperada");
			}
			String[] elementosFinales = Utilidad.getLetras(lista);
			
			// Comprobamos primero si los elementos de la lista inicial
			// han permanecido igual y en su misma posicion.
			int index;
			for(index = 0; index < elementosIniciales.length; index++)
			{
				assertEquals(elementosIniciales[index], elementosFinales[index]);
			}
			// Si la letra insertada no es valida, nos aseguramos de que no se
			// haya insertado en la lista.
			if(letra == "@" || letra == "[")
			{
				assertEquals(elementosIniciales.length, elementosFinales.length);
			}
			// En otro caso, nos aseguramos de que la letra insertada sea la correcta
			// y que se haya insertado "n" veces.
			else
			{
				int counter = 0;
				for(; index < elementosFinales.length; index++)
				{
					assertEquals(letra, elementosFinales[index]);
					counter++;
				}
				assertEquals(n, counter);
			}
		}
	}
	
	/**
	 * Devuelve los parametros para los tests del metodo AddNTimes
	 * @return Matriz donde cada fila es un caso de prueba.
	 */
	public static String[][] getAddNTimesParameters()
	{
		String[] letras = {"[", "A", "B", "M", "Y", "Z", "@"};
		String[] enes = {"-1", "0", "1", "5", "9", "10", "11"};
		String[][] casosDePrueba = new String[letras.length * enes.length * 2][3];
		Utilidad.getCartesian(letras, enes, casosDePrueba, 0);
		Utilidad.getCartesian(letras, enes, casosDePrueba, 49);
		int index;
		for(index = 0; index < casosDePrueba.length / 2; index++)
		{
			casosDePrueba[index][2] = "true";
		}
		for(; index < casosDePrueba.length; index++)
		{
			casosDePrueba[index][2] = "false";
		}
		return casosDePrueba;
	}
}
