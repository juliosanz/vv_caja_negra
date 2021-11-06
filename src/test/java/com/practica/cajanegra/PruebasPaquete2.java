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
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PruebasPaquete2 {
	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> nElementos;
	private static HashMap<String,SingleLinkedListImpl<String>> hmap;
	
	/*
	 * En el beforeAll asignamos valores al hashMap que usaremos
	 * en algunos de los métodos, por lo que también instanciamos
	 * cada una de las listas.
	 */
	
	@Test
	@BeforeAll
	static void setUp() {
	vacia = new SingleLinkedListImpl<String>();
	unElemento = new SingleLinkedListImpl<String>("A");
	dosElementos = new SingleLinkedListImpl<String>("A", "B");
	nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
	
	hmap = new HashMap<String,SingleLinkedListImpl<String>>();
	hmap.put("vacia", vacia);
	hmap.put("unElemento", unElemento);
	hmap.put("dosElementos", dosElementos);
	hmap.put("nElementos", nElementos);

	}
	
	/*
	 * En el beforeEach reinstanciamos las variables que referencian cada 
	 * lista utilizada para asegurarnos de la atomicidad en cada método.
	 * 
	 */
	@Test
	@BeforeEach
	public void init() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
	}
	
	
	/*
	 * 	El siguiente conjunto de tests comprueba
	 * 	la funcionalidad del método RemoveLast
	 * 	mediante Robustness for worst case
	 */
	@Test
	public void removeLastVaciaTest() {
		assertThrows(EmptyCollectionException.class, () -> {vacia.removeLast();});
	}
	
	@DisplayName("Remove last de una lista no vacía")
	@ParameterizedTest()
	@CsvSource({
	"unElemento",
	"dosElementos",
	"nElementos"
	})
	public void removeLastNotEmptyTest(String key) throws EmptyCollectionException {
		SingleLinkedListImpl<String> lista = hmap.get(key);
		String strSolucion = "[]";
		if(lista.size()!=1) {
			strSolucion = lista.toString().substring(0,lista.toString().length()-4) + "]";
		}
		String elemSolucion = lista.toString().substring(lista.toString().length()-2, lista.toString().length()-1);
		String elemPrueba = lista.removeLast();
		assertEquals(elemSolucion, elemPrueba);
		assertEquals(strSolucion, lista.toString());
	}
	
	/*
	 * 	El siguiente test comprueba la 
	 *  funcionalidad del método Size
	 */
	
	@DisplayName("Testea size Parametrizado")
	@ParameterizedTest()
	@CsvSource({
	"vacia,0",
	"unElemento,1",
	"dosElementos,2",
	"nElementos,7"
	})
	public void sizeTest(String key, int size) {
		SingleLinkedListImpl<String> lista = hmap.get(key);
		assertEquals(size, lista.size());
		
	}
	

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
			lista = nElementos;
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
	
	
	
	/*
	 * 	El siguiente conjunto de tests comprueba
	 * 	la funcionalidad del método addFirst
	 * 	mediante Robustness for worst case
	 */
	
	@DisplayName("Add First para los casos válidos")
	@ParameterizedTest()
	@CsvSource({
	"Z,vacia",  
	"Z,unElemento",
	"Z,dosElementos",
	"Z,nElementos",
	"Y,vacia",  
	"Y,unElemento",
	"Y,dosElementos",
	"Y,nElementos",
	"M,vacia",  
	"M,unElemento",
	"M,dosElementos",
	"M,nElementos",
	"B,vacia",  
	"B,unElemento",
	"B,dosElementos",
	"B,nElementos",
	"A,vacia",  
	"A,unElemento",
	"A,dosElementos",
	"A,nElementos",
	})
	public void addFirstValidos(String str, String key) {
		SingleLinkedListImpl<String> lista = hmap.get(key);
		String solucion = lista.toString();
		solucion = "[" + str + ", " + solucion.substring(1);
		lista.addFirst(str);
		String prueba = lista.toString();
		assertEquals(solucion, prueba);
	}
	
	
	
	@DisplayName("Add First para los casos inválidos")
	@ParameterizedTest()
	@CsvSource({
	"@,vacia",        
	"[,vacia",
	"@,unElemento",        
	"[,unElemento",
	"@,dosElementos",        
	"[,dosElementos",
	"@,nElementos",        
	"[,nElementos"
	})
	public void addFirstInvalidos(String str, String key) {
		SingleLinkedListImpl<String> lista = hmap.get(key);
		String solucion = lista.toString();
		lista.addFirst(str);
		String prueba = lista.toString();
		assertEquals(solucion, prueba);
	}
	/**
	 * 
	 * @param t referencia el tamaño de la lista que vamos a buscar
	 * @param p referencia la posicion a partir de la que se encuentra la subLista
	 */
	@ParameterizedTest()
	@MethodSource("getIsSublistParameters")
	public void isSublistTest(int t, int p, boolean pruebaVacia)
	{
		SingleLinkedListImpl<String> listaPrueba;
		if(pruebaVacia)
		{
			listaPrueba = vacia;
		}
		else
		{
			listaPrueba = nElementos;
		}
		SingleLinkedListImpl<String> sublista = new SingleLinkedListImpl<String>(getLista(t, p - 1));
		int parametroSalida = listaPrueba.isSubList(sublista);
		if(t == 0)
		{
			assertEquals(0, parametroSalida);
		}
		else if(listaPrueba.size() - (p - 1) - t < 0)
		{
			assertEquals(-1, parametroSalida);
		}
		else
		{
			assertEquals(p, parametroSalida);
		}
	}
	
	/**
	 * Devuelve la lista que se va a pasar al método isSublist en
	 * las pruebas. Será una lista cuyos elementos coincidan con
	 * los de la lista desde la que se llame al método a partir
	 * de la posición p; si el tamaño de la sublista supera al
	 * de la lista, se rellena con "X".
	 * @param t Tamaño de la sublista.
	 * @param p Posición a partir de la cual se copian los elementos
	 * de la lista padre.
	 * @return La sublista con la que se probará el método.
	 */
	public String[] getLista(int t, int p)
	{
		System.out.println();
		System.out.println("Tamaño: " + t + "; Posicion: " + p);
		String[] nElementosArray = getLetras(nElementos);
		String[] solucion;
		if(t == 0)
		{
			solucion = new String[0];
			return solucion;
		}
		else
		{
			solucion = new String[t];
			int counter = 0;
			if(p < nElementosArray.length)
			{
				int i = p;
				while(i < nElementosArray.length && t != 0)
				{
					solucion[counter] = nElementosArray[i];
					System.out.print(solucion[counter]);
					counter++;
					t--;
					i++;
				}
			}
			if(t != 0)
			{
				for(; t > 0; t--)
				{
					solucion[counter] = "X";
					System.out.print(solucion[counter]);
					counter++;
				}
			}
			System.out.println();
			return solucion;
		}
	}
	
	/**
	 * Devuelve los parámetros de las pruebas del método isSublist.
	 * @return Matriz de strings donde cada fila es un caso de prueba.
	 */
	public static String[][] getIsSublistParameters()
	{
		String[] tamanos = { "0", "1", "2", "4", "5", "6" };
		String[] posiciones = { "1", "2", "4", "6", "7", "8" };
		String[][] casosDePrueba = new String[tamanos.length * posiciones.length * 2][3];
		getCartesian(tamanos, posiciones, casosDePrueba, 0);
		getCartesian(tamanos, posiciones, casosDePrueba, 36);
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
	 * @param params1 El primer string del producto cartesiano.
	 * @param params2 El segundo string del producto cartesiano.
	 * @param cartesianMatrix La matriz donde se copiará el producto cartesiano.
	 * @param start A partir de qué fila de la matriz copiar el producto cartesiano.
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
	
	/**
	 * Devuelve los elementos de la lista como array de strings para que
	 * sea más fácil operar con ellos.
	 * @param lista.
	 * @return array de strings donde cada elemento del array
	 * es un elemento de la lista.
	 */
	public String[] getLetras(SingleLinkedListImpl<String> lista)
	{
		String nElementosString = lista.toString();
		
		ArrayList<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile("[A-Z]").matcher(nElementosString);
		while (m.find()) {
		   allMatches.add(m.group());
		}
		String[] arrayLista = new String[allMatches.size()];
		allMatches.toArray(arrayLista);
		
		return arrayLista;
	}
	
}
