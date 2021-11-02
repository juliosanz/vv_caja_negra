package com.practica.cajanegra;

import com.cajanegra.EmptyCollectionException;
import com.cajanegra.SingleLinkedListImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.awt.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PruebasPaquete2 {
	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> cincoElementos;
	
	@Test
	@BeforeAll
	static void setUp() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
		cincoElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E");
	}
	@Test
	@BeforeEach
	public void init() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
		cincoElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E");
	}
	
	
	/*
	 * 	El siguiente conjunto de tests comprueba
	 * 	la funcionalidad del método RemoveLast
	 * 	mediante Robustness for worst case
	 */
	@Test
	public void removeLastVacia() {
		assertThrows(EmptyCollectionException.class, () -> {vacia.removeLast();});
	}
	
	@Test
	public void removeLastUnElemento() throws EmptyCollectionException {
		String prueba = unElemento.removeLast();
		assertEquals(unElemento.size(), 0); //CUIDADO CON ESTO POR FAVOR ES SIZE NO SABEMOS SI FUNCIONAAAAAA QUE HACEMOS?????
		assertEquals(prueba, "A");
	}
	
	@Test
	public void removeLastDosElementos() throws EmptyCollectionException {
		String prueba = dosElementos.removeLast();
		assertEquals(unElemento.size(), 1); 
		assertEquals(prueba, "B");
	}
	
	@Test
	public void removeLastCincoElementos() throws EmptyCollectionException {
		String prueba = cincoElementos.removeLast();
		assertEquals(cincoElementos.size(), 4); 
		assertEquals(prueba, "E");
	}
	
	/*
	 * 	El siguiente conjunto de tests comprueba
	 * 	la funcionalidad del método Size
	 * 	mediante Robustness for worst case
	 */
	
	@Test
	public void sizeVacio() {
		assertEquals(vacia.size(),0);
	}
	
	@Test
	public void sizeUnElemento() {
		assertEquals(unElemento.size(),1);
	}
	
	@Test
	public void sizeDosElementos() {
		assertEquals(dosElementos.size(),2);
	}
	
	@Test
	public void sizeCincoElementos() {
		assertEquals(cincoElementos.size(),5);
	}
	
	/*
	 * 	El siguiente conjunto de tests comprueba
	 * 	la funcionalidad del método addFirst
	 * 	mediante Robustness for worst case
	 */
	

	/*
	 * 	El siguiente conjunto de tests comprueba
	 * 	la funcionalidad del método addNTimes
	 * 	mediante Robustness for worst case
	 */
	
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
			lista = cincoElementos;
		}
		// Si n es negativo, comprobamos que lance la excepción que debe
		// lanzar según la documentación.
		if(n < 0)
		{
			assertThrows(IllegalArgumentException.class, () -> {
				lista.addNTimes(letra, n);
			});
		}
		else
		{
			String[] elementosIniciales = getLetras(lista);
			// Llegados a este bloque, n es mayor o igual que cero por el
			// if anterior, luego la excepción no debería lanzarse.
			try
			{
				lista.addNTimes(letra, n);
			}
			catch(IllegalArgumentException e)
			{
				fail("Excepción inesperada");
			}
			String[] elementosFinales = getLetras(lista);
			
			// Comprobamos primero si los elementos de la lista inicial
			// han permanecido igual y en su misma posición.
			int index;
			for(index = 0; index < elementosIniciales.length; index++)
			{
				assertEquals(elementosIniciales[index], elementosFinales[index]);
			}
			// Si la letra insertada no es válida, nos aseguramos de que no se
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
	 * Devuelve los elementos de la lista como array de strings.
	 * @param lista
	 * @return array de strings donde cada elemento del array
	 * es un elemento de la lista
	 */
	public String[] getLetras(SingleLinkedListImpl lista)
	{
		String cincoElementosString = lista.toString();
		
		ArrayList<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile("[A-Z]").matcher(cincoElementosString);
		while (m.find()) {
		   allMatches.add(m.group());
		}
		
		String[] arrayLista = new String[allMatches.size()];
		allMatches.toArray(arrayLista);
		
		return arrayLista;
	}
	
	/**
	 * Devuelve los parámetros para los tests del método AddNTimes
	 * @return Matriz donde cada fila es un caso de prueba.
	 */
	public static String[][] getAddNTimesParameters()
	{
		String[] letras = {"[", "A", "B", "M", "Y", "Z", "@"};
		String[] enes = {"-1", "0", "1", "5", "9", "10", "11"};
		String[][] casosDePrueba = new String[letras.length * enes.length * 2][3];
		getCartesian(letras, enes, casosDePrueba, 0);
		getCartesian(letras, enes, casosDePrueba, 49);
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
	
	/**
	 * Copia el producto cartesiano de dos arrays de strings en una matriz
	 * de strings a partir de la fila "start" de la matriz.
	 * @param params1 El primer string del producto cartesiano
	 * @param params2 El segundo string del producto cartesiano
	 * @param cartesianMatrix La matriz donde se copiará el producto cartesiano
	 * @param start A partir de qué fila de la matriz copiar el producto cartesiano
	 */
	public static void getCartesian(String[] params1, String[] params2, String[][] cartesianMatrix, int start)
	{
		int k = start;
		for(int i = 0; i < params1.length; i++)
		{
			for(int j = 0; j < params2.length; j++)
			{
				cartesianMatrix[k][0] = params1[i];
				cartesianMatrix[k][1] = params2[j];
				k++;
			}
		}
	}

	@DisplayName("Add First para una lista vacia de los casos válidos")
	@ParameterizedTest()
	@CsvSource({
	"Z",        
	"Y", 
	"M",
	"B",
	"A",
	})
	public void vaciaAddFirstValido(String str) {
		vacia.addFirst(str);
		String prueba = vacia.toString();
		String solucion = "[" + str + "]";
	    assertEquals(prueba, solucion);
	}
	
	@DisplayName("Add First para una lista vacia de los casos inválidos")
	@ParameterizedTest()
	@CsvSource({
	"@",        
	"["
	})
	public void vaciaAddFirstInvalido(String str) {
		vacia.addFirst(str);
		String prueba = vacia.toString();
		String solucion = "[]";
	    assertEquals(prueba, solucion);
	}
	
	@DisplayName("Add First para una lista de un elemento de los casos válidos")
	@ParameterizedTest()
	@CsvSource({
	"Z",        
	"Y", 
	"M",
	"B",
	"A",
	})
	public void unElementoAddFirstValido(String str) {
		unElemento.addFirst(str);
		String prueba = unElemento.toString();
		String solucion = "[" + str + ", A]";
	    assertEquals(prueba, solucion);
	}
	
	@DisplayName("Add First para una lista de un elemento de los casos inválidos")
	@ParameterizedTest()
	@CsvSource({
	"@",        
	"["
	})
	public void unElementoAddFirstInvalido(String str) {
		unElemento.addFirst(str);
		String prueba = unElemento.toString();
		String solucion = "[A]";
	    assertEquals(prueba, solucion);
	}
	
	
	@DisplayName("Add First para una lista de dos elementos de los casos válidos")
	@ParameterizedTest()
	@CsvSource({
	"Z",        
	"Y", 
	"M",
	"B",
	"A",
	})
	public void dosElementosAddFirstValido(String str) {
		dosElementos.addFirst(str);
		String prueba = dosElementos.toString();
		String solucion = "[" + str + ", A, B]";
	    assertEquals(prueba, solucion);
	}
	
	@DisplayName("Add First para una lista de dos elementos de los casos inválidos")
	@ParameterizedTest()
	@CsvSource({
	"@",        
	"["
	})
	public void dosElementosAddFirstInvalido(String str) {
		dosElementos.addFirst(str);
		String prueba = dosElementos.toString();
		String solucion = "[A, B]";
	    assertEquals(prueba, solucion);
	}

	
	@DisplayName("Add First para una lista de cinco elementos de los casos válidos")
	@ParameterizedTest()
	@CsvSource({
	"Z",        
	"Y", 
	"M",
	"B",
	"A",
	})
	public void cincoElementosAddFirstValido(String str) {
		cincoElementos.addFirst(str);
		String prueba = dosElementos.toString();
		String solucion = "[" + str + ", A, B, C, D, E]";
	    assertEquals(prueba, solucion);
	}
	
	@DisplayName("Add First para una lista de cinco elementos de los casos inválidos")
	@ParameterizedTest()
	@CsvSource({
	"@",        
	"["
	})
	public void cincoElementosAddFirstInvalido(String str) {
		cincoElementos.addFirst(str);
		String prueba = cincoElementos.toString();
		String solucion = "[A, B, C, D, E]";
	    assertEquals(prueba, solucion);
	}
}
