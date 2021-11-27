package com.practica.cajanegra.pruebasborrado;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.EmptyCollectionException;
import com.cajanegra.SingleLinkedListImpl;

public class removeLastTest {

	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> nElementos;
	private static HashMap<String,SingleLinkedListImpl<String>> hmap;
	

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
	
	
	@BeforeEach
	public void init() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
	}
	
	/*
     * RemoveLast
     * 
     * Comprueba que se devuelve el elemento que se pretende borrar
     * Comprueba que en la lista se borra la ultima aparicion dle elemento
     * que se quiere borrar
     * 
     * no funciona. 
     * 1 si el elemento a borrar esta el primero, entoces deja la lista vacia
     * 2 si el elemento aparece 2 veces, lo borra ambas
     * */
    @ParameterizedTest(name="{index} => Elimina de la lista la ultima aparicion de {0}")
    @CsvSource(delimiter=':', value= {
        "A: [A, B, M, Y, Z, B, Y, Z]",
        "B: [A, B, M, A, Y, Z, Y, Z]",
        "M: [A, B, A, Y, Z, B, Y, Z]",
        "Y: [A, B, M, A, Y, Z, B, Z]",
        "Z: [A, B, M, A, Y, Z, B, Y]",

        

    })
    void testRemoveLast_valid_tests(String element, String expected) throws NoSuchElementException, EmptyCollectionException {
        SingleLinkedListImpl<String> lista_incluye=new SingleLinkedListImpl<String>("A", "B", "M", "A", "Y", "Z", "B", "Y", "Z");
        assertEquals(element, lista_incluye.removeLast(element));//espera elemento
        assertEquals(expected, lista_incluye.toString());//espera lista
        
    }
    /*
     * removelast
     * 
     * intenta eliminar un elemento de una lista vacia
     * */
    @ParameterizedTest(name="{index} => Intenta eliminar el elemento {0} de una lista vacia")
    @CsvSource( value= {
        "@", "A","B", "M", "Y", "Z", "["

    })
    void testRemoveLast_emptylist(String element) {
        SingleLinkedListImpl<String> lista_vacia=new SingleLinkedListImpl<String>();
        assertThrows(EmptyCollectionException.class, () -> {
            lista_vacia.removeLast(element);
        });
    }
    /*
     * removeLast
     * 
     * intenta eliminar un elemento que no esta en la lista
     * 
     * no funciona. devuelve null pero no salta una excepcion
     * 
     * */
    @ParameterizedTest(name="{index} => Intenta eliminar el elemento {0}, que no esta incluido en la lista")
    @CsvSource(value = {
        "@", "A","B", "M", "Y", "Z", "["

    })
    void testRemoveLastNoMatch(String element) {
        SingleLinkedListImpl<String> lista=new SingleLinkedListImpl<String>("D", "E", "F", "G");
        assertThrows(NoSuchElementException.class, () -> {
            lista.removeLast(element);
            });
    }
    
    /*
     * 	removeLast sin parametros
     * 
	 * 	El siguiente test comprueba la
	 * 	funcionalidad del metodo RemoveLast
	 * 	mediante Robustness for worst case
	 */
	
	@DisplayName("Pruebas de remove last")
	@ParameterizedTest()
	@CsvSource({
	"vacia",
	"unElemento",
	"dosElementos",
	"nElementos"
	})
	public void removeLastNotEmptyTest(String key) throws EmptyCollectionException {
		SingleLinkedListImpl<String> lista = hmap.get(key);
		String strSolucion = "[]";
		if(lista.size() == 0) {
			assertThrows(EmptyCollectionException.class, () -> {vacia.removeLast();});
			
		} else {
			
			if(lista.size()!=1) {
				strSolucion = lista.toString().substring(0,lista.toString().length()-4) + "]";
			}
			
			String elemSolucion = lista.toString().substring(lista.toString().length()-2, lista.toString().length()-1);
			String elemPrueba = lista.removeLast();
			assertEquals(elemSolucion, elemPrueba);
			assertEquals(strSolucion, lista.toString());
		}
	}
}
