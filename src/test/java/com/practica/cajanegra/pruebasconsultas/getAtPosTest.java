package com.practica.cajanegra.pruebasconsultas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cajanegra.SingleLinkedListImpl;

public class getAtPosTest {
	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> nElementos;
	private static HashMap<String,SingleLinkedListImpl<String>> hmap;
	
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
	
	@Test
	@BeforeEach
	public void init() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
	}

	// Tests getAtPost
    @Test
    public void testGetAtPosInvalidas() {
      assertThrows(IllegalArgumentException.class, () -> {
        nElementos.getAtPos(0);
      });
      assertThrows(IllegalArgumentException.class, () -> {
        nElementos.getAtPos(nElementos.size() + 1);
      });
      assertThrows(IllegalArgumentException.class, () -> {
        vacia.getAtPos(vacia.size());
      });
        // NO SE PRUEBA getAtPos(null) por no ser permitido por le compilador
    }

    @Test
    public void testGetAtPosValidas() {
        assertEquals("A", nElementos.getAtPos(1));
        assertEquals("B", nElementos.getAtPos(2));
        assertEquals("C", nElementos.getAtPos(nElementos.size() / 2)); // Val. medio
        assertEquals("F", nElementos.getAtPos(nElementos.size() - 1));
        assertEquals("G", nElementos.getAtPos(nElementos.size())); // Ult. valor
    }
}
