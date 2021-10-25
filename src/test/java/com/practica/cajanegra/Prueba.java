package com.practica.cajanegra;

import com.cajanegra.SingleLinkedListImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Prueba {
	
   @Test
   public void miTestDeEjemplo(){
       SingleLinkedListImpl<String> mylist = new SingleLinkedListImpl<String>("A", "B");
       assertEquals(2,mylist.size());
   }

}
