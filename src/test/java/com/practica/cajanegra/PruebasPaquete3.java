package com.practica.cajanegra;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;

public class PruebasPaquete3 {
    private static SingleLinkedListImpl<String> lista3Elem;
    private static SingleLinkedListImpl<String> lista7Elem;
	private static SingleLinkedListImpl<String> vacia;
	
	@BeforeEach
	void init() {
        lista3Elem = new SingleLinkedListImpl<String>("C", "D", "E");
        lista7Elem = new SingleLinkedListImpl<>("A", "B", "C", "D", "E", "F", "G");
		vacia = new SingleLinkedListImpl<String>();
	}
	
	@ParameterizedTest()
	@CsvSource({
	"@",        
	"["
	})
	void addLastInvalido(String s) {
        int sizeOri = lista3Elem.size();
		String solucion = "[C, D, E]";
        lista3Elem.addLast(s);
        System.out.println(lista3Elem.toString());
        assertEquals(sizeOri, lista3Elem.size());
        assertEquals(lista3Elem.toString(), solucion);
	}
	

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
	
	@ParameterizedTest()
	@CsvSource({
	"A",        
	"B",
	"M",
	"Y",
	"Z"
	})
	void addLastValido(String s) {
        int sizeOri = lista3Elem.size();
        lista3Elem.addLast(s);
		String solucion = "[C, D, E, " + s + "]";
        assertEquals(sizeOri + 1, lista3Elem.size());
        assertEquals(lista3Elem.toString(), solucion);
	}
	
    // Tests getAtPost

    @Test
    public void testGetAtPosPosInvalidas() {
        assertThrows(IllegalArgumentException.class, () -> {
            lista7Elem.getAtPos(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            lista7Elem.getAtPos(lista7Elem.size() + 1);
        });
    }

    @Test
    public void testGetAtPosValidas() {
        assertEquals("A", lista7Elem.getAtPos(1));
        assertEquals("B", lista7Elem.getAtPos(2));
        assertEquals("C", lista7Elem.getAtPos(lista7Elem.size() / 2)); // Val. medio
        assertEquals("F", lista7Elem.getAtPos(lista7Elem.size() - 1));
        assertEquals("G", lista7Elem.getAtPos(lista7Elem.size())); // Ult. valor
    }

    // Test toString

    @Test
    public void testToString() {
        assertTrue(lista7Elem.toString().equals("[A, B, C, D, E, F, G]"));
    }
}
