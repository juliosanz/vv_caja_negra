package com.practica.cajanegra.pruebasadiciones;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;

public class addLastTest {
	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> lista3Elem;
	private static SingleLinkedListImpl<String> nElementos;
	private static HashMap<String,SingleLinkedListImpl<String>> hmap;
	
	@Test
	@BeforeAll
	static void setUp() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
        lista3Elem = new SingleLinkedListImpl<String>("C", "D", "E");
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
        lista3Elem = new SingleLinkedListImpl<String>("C", "D", "E");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
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
}
