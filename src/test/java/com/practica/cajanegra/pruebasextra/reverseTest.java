package com.practica.cajanegra.pruebasextra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;
import com.practica.cajanegra.Utilidad;

public class reverseTest {
	
	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> nElementos;
	private static SingleLinkedListImpl<String> listaAux;
	private static HashMap<String,SingleLinkedListImpl<String>> hmap;
	
	@Test
	@BeforeAll
	static void setUp() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
		listaAux = new SingleLinkedListImpl<String>();
		
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
	
	/*
     *reverse
     *
     * Comprueba que la lista [{0}] se convierte en {1}
     * 
     * */
    @ParameterizedTest(name="{index} => Comprueba que la lista [{0}] se convierte en {1}")
    @CsvSource(value = { 
            "A:[A]",
            "A,B:[B, A]",
            "A,B,C,D,E,F,G,H:[H, G, F, E, D, C, B, A]"},
    delimiter = ':')
    void testReverse (String list, String expected) {
    	listaAux= Utilidad.crearLista(list);
        assertEquals(expected, listaAux.reverse().toString());
    }
    

    /*
     * reverse
     * 
     * Creamos una lista vacia y comprobamos que al hacer reverse se queda como esta
     * */
    @Test
    void testReverseEmpty() {
         listaAux=new SingleLinkedListImpl<String>();
        assertEquals("[]", listaAux.reverse().toString());

    }

}
