package com.practica.cajanegra;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;

public class PruebasConsultas {
	
	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> lista3Elem;
	private static SingleLinkedListImpl<String> nElementos;
	private static SingleLinkedListImpl<String> listaAux;
	private static SingleLinkedListImpl<String> listConRepetidos;
	private static HashMap<String,SingleLinkedListImpl<String>> hmap;
	
	@Test
	@BeforeAll
	static void setUp() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
        lista3Elem = new SingleLinkedListImpl<String>("C", "D", "E");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
		listaAux = new SingleLinkedListImpl<String>();
		listConRepetidos = new SingleLinkedListImpl<>("A", "B", "C", "A", "M", "A", "B", "Y", "Z", "Y", "O");
		
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
		listConRepetidos = new SingleLinkedListImpl<>("A", "B", "C", "A", "M", "A", "B", "Y", "Z", "Y", "O");
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
	    
	    /*
		 * 	El siguiente test comprueba la 
		 *  funcionalidad del metodo Size
		 */
		
		@DisplayName("Testea size Parametrizado")
		@ParameterizedTest()
		@CsvSource({
		"vacia,0",
		"unElemento,1",
		"dosElementos,2",
		"nElementos,7"
		})
		public void sizeTest(String key, int size) {
			SingleLinkedListImpl<String> lista = hmap.get(key);
			assertEquals(size, lista.size());
			
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
