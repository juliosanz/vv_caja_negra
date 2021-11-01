package com.practica.cajanegra;

import com.cajanegra.SingleLinkedList;
import com.cajanegra.SingleLinkedListImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.validator.PublicClassValidator;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class Prueba {
	 SingleLinkedListImpl<String> mylist;
	 /*
	  * Duda:
	  * la lista es de string o char
	  * 
	  * */
	@BeforeEach
	public void setup() {
	       mylist = new SingleLinkedListImpl<String>("L", "M", "N", "O", "P", "Q");
	}

   
   @ParameterizedTest(name= "{index} => añade el elemento {0} en la posición {1}")
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
       mylist.addAtPos(element, pos);       
	   assertEquals(expected, mylist.toString());
   }
   
   
   
   @ParameterizedTest(name= "{index} => intenta añadir el elemento {0} en la posición 0")
   @CsvSource(value={
	   "A", "B", "M", "Y", "Z", "[", "@"
   })
   void illegalPos(String element) {
       
       assertThrows(IllegalArgumentException.class, () -> {
         mylist.addAtPos(element, 0);
       });
   }
   
   
   @ParameterizedTest(name="{index} => intenta añadir el elemento {0} en la posición {1}")
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
   /*
    * Esta es el unico test que no cumple la especificacion. 
    * La lista debería quedar intacta, pero se produce la inserción de los caracteres
    * que están fuera del dominio
    * 
    * */
   void fueraDelDominio(String element, int pos) {
	   String mylistExpected= mylist.toString();
	   mylist.addAtPos(element, pos);
	   assertEquals(mylistExpected, mylist.toString());
   }
   
	@ParameterizedTest(name= "{index} => Comprueba que la lista [{0}] no está vacía")
	@CsvSource(value = { "A,:false", "A,B:false", "A,B,C,D,E,F,G,H:false" }, delimiter = ':')
	void testIsNotEmpty (String list, String esperado) {
		String[ ] elements = list.split(",");
		SingleLinkedListImpl aux = new SingleLinkedListImpl();
		for (String letra: elements)
			aux.addLast(letra);
		assertEquals(Boolean.parseBoolean(esperado), aux.isEmpty());
	}
	
	@Test
	void testIsEmpty() {
		SingleLinkedListImpl<String> listaVacia=new SingleLinkedListImpl<String>();
		assertEquals(true, listaVacia.isEmpty());
	}
	
	
	@ParameterizedTest(name="{index} => Comprueba que la lista [{0}] se convierte en {1}")
	@CsvSource(value = { "A:[A]", "A,B:[B, A]", "A,B,C,D,E,F,G,H:[H, G, F, E, D, C, B, A]"}, delimiter = ':')
	void testReverse (String list, String expected) {
		String[ ] elements = list.split(",");
		SingleLinkedListImpl aux = new SingleLinkedListImpl();
		for (String letra: elements)
			aux.addLast(letra);
		assertEquals(expected, aux.reverse().toString());
	}
	
	@Test
	void testReverseEmpty() {
		SingleLinkedListImpl<String> listaVacia=new SingleLinkedListImpl<String>();
		assertEquals("[]", listaVacia.reverse().toString());

	}
	
   
}
