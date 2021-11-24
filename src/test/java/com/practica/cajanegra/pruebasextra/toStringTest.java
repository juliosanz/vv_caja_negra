package com.practica.cajanegra.pruebasextra;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cajanegra.SingleLinkedListImpl;

public class toStringTest {
	
	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> nElementos;

	
	@Test
	@BeforeAll
	static void setUp() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
	
	}
	
	
	// Test toString
    @Test
    public void testToString() {
        assertTrue(nElementos.toString().equals("[A, B, C, D, E, F, G]"));
        assertTrue(unElemento.toString().equals("[A]"));
        assertTrue(vacia.toString().equals("[]"));
    }
}
