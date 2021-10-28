package com.practica.cajanegra;

import com.cajanegra.EmptyCollectionException;
import com.cajanegra.SingleLinkedListImpl;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

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
	
}
