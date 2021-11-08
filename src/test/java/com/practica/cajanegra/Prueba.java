package com.practica.cajanegra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cajanegra.SingleLinkedListImpl;

public class Prueba {

   @Test
   public void miTestDeEjemplo(){
       SingleLinkedListImpl<String> mylist = new SingleLinkedListImpl<String>("A", "B");
       assertEquals(2,mylist.size());
   }

}
