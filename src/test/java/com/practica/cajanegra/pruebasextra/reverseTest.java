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
	

	private static SingleLinkedListImpl<String> listaAux;
	
	@Test
	@BeforeAll
	static void setUp() {
	
		listaAux = new SingleLinkedListImpl<String>();
		
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
