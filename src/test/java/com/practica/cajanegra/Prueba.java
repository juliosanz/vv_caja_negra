package com.practica.cajanegra;


import com.cajanegra.EmptyCollectionException;
import com.cajanegra.SingleLinkedList;
import com.cajanegra.SingleLinkedListImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.validator.PublicClassValidator;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class Prueba {

	 SingleLinkedListImpl<String> mylist;
	 /*
	  * Duda:
	  * la lista es de string o char
	  * 
	  * */
	 
	 /*
	  * Inicializamos una lista dummie cada vez que se ejecuta un test 
	  * */
	@BeforeEach
	public void setup() {
	       mylist = new SingleLinkedListImpl<String>("L", "M", "N", "O", "P", "Q");
	}

   /*
    * 
    * addAtPos

    * Inserta el elemento {0} en la posicion {1}. La lista debe modificarse de tal
    *  forma que coincida con el {3}
    *  */
   @ParameterizedTest(name= "{index} => aniade el elemento {0} en la posicion {1}")
   @CsvSource(delimiter=':', 
   value={
	   "A: 1: [A, L, M, N, O, P, Q]",
	   "A: 2: [L, A, M, N, O, P, Q]",
	   "B: 1: [B, L, M, N, O, P, Q]",
	   "B: 2: [L, B, M, N, O, P, Q]",
	   "M: 1: [M, L, M, N, O, P, Q]",
	   "M: 2: [L, M, M, N, O, P, Q]",
	   "Y: 1: [Y, L, M, N, O, P, Q]",
	   "Y: 2: [L, Y, M, N, O, P, Q]",
	   "Z: 1: [Z, L, M, N, O, P, Q]",
	   "Z: 2: [L, Z, M, N, O, P, Q]",
	   "A: 3: [L, M, A, N, O, P, Q]",
	   "B: 3: [L, M, B, N, O, P, Q]",
	   "M: 3: [L, M, M, N, O, P, Q]",
	   "Y: 3: [L, M, Y, N, O, P, Q]",
	   "Z: 3: [L, M, Z, N, O, P, Q]",
	   
	   "A: 6: [L, M, N, O, P, A, Q]",
	   "A: 7: [L, M, N, O, P, Q, A]",
	   "A: 8: [L, M, N, O, P, Q, A]",

	   "B: 6: [L, M, N, O, P, B, Q]",
	   "B: 7: [L, M, N, O, P, Q, B]",
	   "B: 8: [L, M, N, O, P, Q, B]",

	   "M: 6: [L, M, N, O, P, M, Q]",
	   "M: 7: [L, M, N, O, P, Q, M]",
	   "M: 8: [L, M, N, O, P, Q, M]",


	   "Y: 6: [L, M, N, O, P, Y, Q]",
	   "Y: 7: [L, M, N, O, P, Q, Y]",
	   "Y: 8: [L, M, N, O, P, Q, Y]",
	   
	   "Z: 6: [L, M, N, O, P, Z, Q]",
	   "Z: 7: [L, M, N, O, P, Q, Z]",
	   "Z: 8: [L, M, N, O, P, Q, Z]",
	   
   })
   void addAtPos_valid_tests( String element, int pos, String expected){
	   int size_expected=mylist.size()+1;
       mylist.addAtPos(element, pos);
       assertEquals(size_expected, mylist.size());
	   assertEquals(expected, mylist.toString());
   }
   
   
   /*
    * addAtPos
	*
    * Intenta insertar el elmento {0} en la posicion 0.
    * El resultado esperado es que de una excepcion en cada ejecucion.
    * */
   @ParameterizedTest(name= "{index} => intenta añadir el elemento {0} en la posicion 0")
   @CsvSource(value={
	   "A", "B", "M", "Y", "Z", "[", "@"
   })
   void illegalPos(String element) {
       
       assertThrows(IllegalArgumentException.class, () -> {
         mylist.addAtPos(element, 0);
       });
   }
   
   /*
    * addAtPos
    * 
    * Intenta insertar el elemento {0} en la posicion {1}.
    * El resultado esperado es que la lista quede sin modificar
    * 
    * no funciona.
    * La lista deberia quedar intacta, pero se produce la insercion de los caracteres
    * que están fuera del dominio
    * 
    * */
   @ParameterizedTest(name="{index} => intenta añadir el elemento {0} en la posicion {1}")
   @CsvSource(delimiter=':', value= {
		   "@: 1",
		   "@: 2",
		   "@: 3",
		   "@: 6",
		   "@: 7",
		   "@: 8",
		   
		   "[: 1",
		   "[: 2",
		   "[: 3",
		   "[: 6",
		   "[: 7",
		   "[: 8",
		   
   })

   void fueraDelDominio(String element, int pos) {
	   String mylistExpected= mylist.toString();
	   mylist.addAtPos(element, pos);
	   assertEquals(mylistExpected, mylist.toString());
   }
   
   
   /*
    * isEmpty
    * 
    * Creamos listas con un elemento o mas y nos aseguramos de que no estan vacias
    * */
	@ParameterizedTest(name= "{index} => Comprueba que la lista [{0}] no está vacía")
	@CsvSource(value = { 
			
			"A,:false",
			"A,B:false", 
			"A,B,C,D,E,F,G,H:false"
			
			}, delimiter = ':')
	
	void testIsNotEmpty (String list, String esperado) {
		crearLista(list);
		assertEquals(Boolean.parseBoolean(esperado), mylist.isEmpty());
	}
	/*
	 * isEmpty
	 * 
	 * Creamos una lista vacia y comprobamos que esta vacia
	 * */
	@Test
	void testIsEmpty() {
		mylist=new SingleLinkedListImpl<String>();
		assertEquals(true, mylist.isEmpty());
	}
	
	/*
	 *reverse
	 *
	 * Comprueba que la lista [{0}] se convierte en {1}
	 * */
	@ParameterizedTest(name="{index} => Comprueba que la lista [{0}] se convierte en {1}")
	@CsvSource(value = { 
			"A:[A]",
			"A,B:[B, A]",
			"A,B,C,D,E,F,G,H:[H, G, F, E, D, C, B, A]"},
	delimiter = ':')
	void testReverse (String list, String expected) {
		crearLista(list);
		assertEquals(expected, mylist.reverse().toString());
	}
	
	/*
	 * convierte una lista separada por comas en un objeto SingleLinkedListImpl y lo
	 * asigna al atributo mylist de la clase 
	 * */
	void crearLista(String list) {
		String[ ] elements = list.split(",");
		 mylist = new SingleLinkedListImpl();
		for (String letra: elements)
			mylist.addLast(letra);
	}
	
	/*
	 * reverse
	 * 
	 * Creamos una lista vacia y comprobamos que al hacer reverse se queda como esta
	 * */
	@Test
	void testReverseEmpty() {
		 mylist=new SingleLinkedListImpl<String>();
		assertEquals("[]", mylist.reverse().toString());

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
	@ParameterizedTest(name="{index} => Elimina de la lista la última aparicion de {0}")
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
	@ParameterizedTest(name="{index} => Intenta eliminar el elemento {0} de una lista vacía")
	@CsvSource( value= {
		"@", "A","B", "M", "Y", "Z", "["

	})
	void testRemoveLast_emptylist(String element) {
		SingleLinkedListImpl<String> lista_vacía=new SingleLinkedListImpl<String>();
		assertThrows(EmptyCollectionException.class, () -> {
			lista_vacía.removeLast(element);
		});
	}
	/*
	 * removeLast
	 * 
	 * intenta eliminar un elemento que no esta en la lista
	 * 
	 * no funciona. devuelve null pero no salta una excepcion
	 * */
	@ParameterizedTest(name="{index} => Intenta eliminar el elemento {0}, que no está incluido en la lista")
	@CsvSource(value = {
		"@", "A","B", "M", "Y", "Z", "["

	})
	void testRemoveLastNoMatch(String element) {
		SingleLinkedListImpl<String> lista=new SingleLinkedListImpl<String>("D", "E", "F", "G");
		assertThrows(NoSuchElementException.class, () -> {
			lista.removeLast(element);
			});
	}
   
}
