package com.practica.cajanegra;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;

public class Prueba {

    private SingleLinkedListImpl<String> lista;
    private static SingleLinkedListImpl<String> vacia;

    @BeforeEach
    public void setUp() {
        lista = new SingleLinkedListImpl<>("A", "B", "C", "D", "E", "F", "G");
        vacia = new SingleLinkedListImpl<String>();
    }
	
   @Test
   public void miTestDeEjemplo(){
       SingleLinkedListImpl<String> mylist = new SingleLinkedListImpl<String>("A", "B");
       assertEquals(2,mylist.size());
   }

   // Tests getAtPost

   @Test
   public void testGetAtPosPosInvalidas() {
       assertThrows(IllegalArgumentException.class, () -> {
           lista.getAtPos(0);
       });
       assertThrows(IllegalArgumentException.class, () -> {
           lista.getAtPos(lista.size() + 1);
       });
   }

   @Test
   public void testGetAtPosValidas() {
       assertEquals("A", lista.getAtPos(1));
       assertEquals("B", lista.getAtPos(2));
       assertEquals("C", lista.getAtPos(lista.size() / 2)); // Val. medio
       assertEquals("F", lista.getAtPos(lista.size() - 1));
       assertEquals("G", lista.getAtPos(lista.size())); // Ult. valor
   }

   // Test toString

   @Test
   public void testToString() {
       assertTrue(lista.toString().equals("[A, B, C, D, E, F, G]"));
   }

   // Test addLast NOTA: esto funciona cuando el enunciado dice que no deberia

   @Test
   public void testAddLastValidos() {
       int sizeOriginal = lista.size();
       lista.addLast("A");
       assertTrue(sizeOriginal == (lista.size() - 1));
       assertEquals("A", lista.getAtPos(lista.size()));

       sizeOriginal = lista.size();
       lista.addLast("M");
       assertTrue(sizeOriginal == (lista.size() - 1));
       assertEquals("M", lista.getAtPos(lista.size()));

       sizeOriginal = lista.size();
       lista.addLast("Z");
       assertTrue(sizeOriginal == (lista.size() - 1));
       assertEquals("Z", lista.getAtPos(lista.size()));
   }

   @Test
   public void testAddLastInvalidos() {
       lista.addLast("@");
       System.out.println(lista.toString());
   }

   @DisplayName("Add First para una lista vacia de los casos inválidos")
   @ParameterizedTest()
   @CsvSource({ "@", "[" })
   public void vaciaAddFirstInvalido(String str) {
       vacia.addLast(str);
       System.out.println(vacia.toString());
       String prueba = vacia.toString();
       String solucion = "[]";
       assertEquals(prueba, solucion);
   }

}
