package com.practica.cajanegra.pruebasconsultas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;

public class indexOfTest {

	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> nElementos;
	private static SingleLinkedListImpl<String> listConRepetidos;
	private static HashMap<String,SingleLinkedListImpl<String>> hmap;
	
	@Test
	@BeforeAll
	static void setUp() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
		listConRepetidos = new SingleLinkedListImpl<>("A", "B", "C", "A", "M", "A", "B", "Y", "Z", "Y", "O");
		
		hmap = new HashMap<String,SingleLinkedListImpl<String>>();
		hmap.put("vacia", vacia);
		hmap.put("unElemento", unElemento);
		hmap.put("dosElementos", dosElementos);
		hmap.put("nElementos", nElementos);
	}
	
	@Test
	@BeforeEach
	public void init() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
		listConRepetidos = new SingleLinkedListImpl<>("A", "B", "C", "A", "M", "A", "B", "Y", "Z", "Y", "O");
	}
	
	// Test indexOf
    @ParameterizedTest()
    @CsvSource({ "@", "[", "R"})
    public void testIndexOfInvalidos(String s) {
        assertThrows(NoSuchElementException.class, () -> {
          listConRepetidos.indexOf(s); // Elem. no valido precedente/posterior
        });
      }

      @Test
      public void testIndexOfNull() {
        assertThrows(NullPointerException.class, () -> {
          listConRepetidos.indexOf(null); // Elem. nulo
        });
      }

    @Test
    public void testIndexOfValidos() {
      assertTrue(listConRepetidos.indexOf("A") == 1); // A en primera pos.
      assertTrue(listConRepetidos.indexOf("B") == 2); // B en segunda pos.
      assertTrue(listConRepetidos.indexOf("M") == listConRepetidos.size() / 2); // M en pos. media
      assertTrue(listConRepetidos.indexOf("Z") == listConRepetidos.size() - 2); // Z en antepenult. pos.
      assertNotEquals(listConRepetidos.indexOf("Y"), listConRepetidos.size() - 1); // Y repetida penult. pos. ignorada
      assertTrue(listConRepetidos.indexOf("O") == listConRepetidos.size()); // O en Ult. pos
    }
}
