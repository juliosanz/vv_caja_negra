package com.practica.cajanegra;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;

public class PruebasPaquete3 {
    private static SingleLinkedListImpl<String> lista3Elem, lista7Elem, vacia, listConRepetidos;
	
	@BeforeEach
	void init() {
        lista3Elem = new SingleLinkedListImpl<String>("C", "D", "E");
        lista7Elem = new SingleLinkedListImpl<>("A", "B", "C", "D", "E", "F", "G");
		vacia = new SingleLinkedListImpl<String>();
        listConRepetidos = new SingleLinkedListImpl<>("A", "B", "C", "A", "M", "A", "B", "Y", "Z", "Y", "O");
	}
	
	@ParameterizedTest()
	@CsvSource({
	"@",        
	"["
	})
	void addLastInvalido(String s) {
        int sizeOri = lista3Elem.size();
		String solucion = lista3Elem.toString();
        lista3Elem.addLast(s);
        assertEquals(sizeOri, lista3Elem.size());
        assertEquals(lista3Elem.toString(), solucion);
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
        assertTrue(vacia.toString().equals("[]"));
    }

    // Test indexOf

    @Test
    public void testIndexOfValidos() {
        assertTrue(listConRepetidos.indexOf("A") == 1); // A en primera pos.
        assertTrue(listConRepetidos.indexOf("B") == 2); // B en segunda pos.
        assertTrue(listConRepetidos.indexOf("M") == listConRepetidos.size() / 2); // M en pos. media
        assertTrue(listConRepetidos.indexOf("Z") == listConRepetidos.size() - 2); // Z en penult. pos.
        assertTrue(listConRepetidos.indexOf("O") == listConRepetidos.size()); // O en Ult. pos
        assertNotEquals(listConRepetidos.indexOf("Y"), listConRepetidos.size() - 1); // Y repetida penult. pos. ignorada
        assertThrows(NoSuchElementException.class, () -> {
            listConRepetidos.indexOf("K"); // Elem. valido en ninguna pos.
        });
    }

    @Test
    public void testIndexOfInvalidos() {
        assertThrows(NoSuchElementException.class, () -> {
            listConRepetidos.indexOf("@"); // Elem. no valido precedente
        });
        assertThrows(NoSuchElementException.class, () -> {
            listConRepetidos.indexOf("["); // Elem. no valido posterior
        });
    }

}
