package com.practica.cajanegra.pruebasconsultas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cajanegra.SingleLinkedListImpl;

public class getAtPosTest {
	private static SingleLinkedListImpl<String> vacia;

	private static SingleLinkedListImpl<String> nElementos;
	

	
	
	@BeforeEach
	public void init() {
		vacia = new SingleLinkedListImpl<String>();
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
