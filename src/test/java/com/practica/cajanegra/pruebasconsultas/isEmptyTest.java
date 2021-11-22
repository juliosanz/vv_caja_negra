package com.practica.cajanegra.pruebasconsultas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;
import com.practica.cajanegra.Utilidad;

public class isEmptyTest {

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
	    * isEmpty
	    * 
	    * Creamos listas con un elemento o mas y nos aseguramos de que no estan vacias
	    * 
	    * */
	    @ParameterizedTest(name= "{index} => Comprueba que la lista [{0}] no esta vacia")
	    @CsvSource(value = { 
	            
	            "A,:false",
	            "A,B:false", 
	            "A,B,C,D,E,F,G,H:false"
	            
	            }, delimiter = ':')
	    
	    void testIsNotEmpty (String list, String esperado) {
	        listaAux = Utilidad.crearLista(list);
	        assertEquals(Boolean.parseBoolean(esperado), listaAux.isEmpty());
	    }
	    
	    /*
	     * isEmpty
	     * 
	     * Creamos una lista vacia y comprobamos que esta vacia
	     * */
	    @Test
	    void testIsEmpty() {
	        listaAux=new SingleLinkedListImpl<String>();
	        assertEquals(true, listaAux.isEmpty());
	    }
}
