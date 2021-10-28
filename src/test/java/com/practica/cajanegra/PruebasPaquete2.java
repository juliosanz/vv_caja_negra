package com.practica.cajanegra;

import com.cajanegra.EmptyCollectionException;
import com.cajanegra.SingleLinkedListImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

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
