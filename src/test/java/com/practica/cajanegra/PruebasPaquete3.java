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
    public void init() {
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
    public void addLastInvalido(String s) {
        int sizeOri = lista3Elem.size();
		String solucion = lista3Elem.toString();
        lista3Elem.addLast(s);
        assertEquals(sizeOri, lista3Elem.size(), "An invalid element has been wrongfully added.");
        assertEquals(lista3Elem.toString(), solucion);
	}
	
    @Test
    public void addLastInvalidoConNull() {
      int sizeOri = lista3Elem.size();
      assertThrows(NullPointerException.class, () -> {
        lista3Elem.addLast(null);
      });
      assertEquals(lista3Elem.size(), sizeOri, "A null element has been wrongfully added");
    }
	
	@ParameterizedTest()
	@CsvSource({
	"A",        
	"B",
	"M",
	"Y",
	"Z"
	})
    public void addLastValido(String s) {
        int sizeOri = lista3Elem.size();
        lista3Elem.addLast(s);
		String solucion = "[C, D, E, " + s + "]";
        assertEquals(sizeOri + 1, lista3Elem.size());
        assertEquals(lista3Elem.toString(), solucion);
	}
	
    @Test
    public void addLastValidoListaVacia() {
      int sizeOri = vacia.size();
      String solucion = "[M]";
      vacia.addLast("M");
      assertEquals(sizeOri + 1, vacia.size());
      assertEquals(vacia.toString(), solucion);
    }

    // Tests getAtPost
    @Test
    public void testGetAtPosInvalidas() {
      assertThrows(IllegalArgumentException.class, () -> {
        lista7Elem.getAtPos(0);
      });
      assertThrows(IllegalArgumentException.class, () -> {
        lista7Elem.getAtPos(lista7Elem.size() + 1);
      });
      assertThrows(IllegalArgumentException.class, () -> {
        vacia.getAtPos(vacia.size());
      });
        // NO SE PRUEBA getAtPos(null) por no ser permitido por le compilador
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
