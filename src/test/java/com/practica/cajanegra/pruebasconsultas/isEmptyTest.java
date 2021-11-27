package com.practica.cajanegra.pruebasconsultas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;
import com.practica.cajanegra.Utilidad;

public class isEmptyTest {


	private static SingleLinkedListImpl<String> listaAux;
	
	
	@BeforeAll
	static void setUp() {
	
		listaAux = new SingleLinkedListImpl<String>();
		
	}
	

	
	/*
	    * isEmpty
	    * 
	    * Creamos listas con un elemento o mas y nos aseguramos de que no estan vacias
	    * 
	    * */
	    @ParameterizedTest(name= "{index} => Comprueba que la lista [{0}] no esta vacia")
	    @CsvSource(value = { 
	            
	            "A",
	            "A,B", 
	            "A,B,C,D,E,F,G,H"
	            
	            }, delimiter = ':')
	    
	    void testIsNotEmpty (String list) {
	        listaAux = Utilidad.crearLista(list);
	        assertFalse(listaAux.isEmpty());
	    }
	    
	    /*
	     * isEmpty
	     * 
	     * Creamos una lista vacia y comprobamos que esta vacia
	     * */
	    @Test
	    void testIsEmpty() {
	        listaAux=new SingleLinkedListImpl<String>();
	        assertTrue(listaAux.isEmpty()); 
	    }
}
