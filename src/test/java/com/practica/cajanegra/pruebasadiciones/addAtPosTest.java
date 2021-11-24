package com.practica.cajanegra.pruebasadiciones;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;

public class addAtPosTest {
	
	private static SingleLinkedListImpl<String> listAddAtPos;
	
	
	
	@Test
	@BeforeEach
	public void init() {
		listAddAtPos = new SingleLinkedListImpl<String>("L", "M", "N", "O", "P", "Q");
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
        int size_expected=listAddAtPos.size()+1;
        listAddAtPos.addAtPos(element, pos);
        assertEquals(size_expected, listAddAtPos.size());
        assertEquals(expected, listAddAtPos.toString());
    }
    
   
    
    
    /*
     * addAtPos
     *
     * Intenta insertar el elmento {0} en la posicion 0.
     * El resultado esperado es que de una excepcion en cada ejecucion.
     * */
    @ParameterizedTest(name= "{index} => intenta aniadir el elemento {0} en la posicion 0")
    @CsvSource(value={
        "A", "B", "M", "Y", "Z", "[", "@"
    })
    void illegalPos(String element) {
        
        assertThrows(IllegalArgumentException.class, () -> {
          listAddAtPos.addAtPos(element, 0);
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
     * que estan fuera del dominio
     * 
     * */
    @ParameterizedTest(name="{index} => intenta aniadir el elemento {0} en la posicion {1}")
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

    void addAtPosFueraDelDominio(String element, int pos) {
        String mylistExpected= listAddAtPos.toString();
        listAddAtPos.addAtPos(element, pos);
        assertEquals(mylistExpected, listAddAtPos.toString());
    }
    /*
     *addAtPos
     *
     *probamos a meter null en la posicion 1. 
     *no deberia modificar la lista pero si queda modificada
     * 
     * */
    
    @Test
    void addAtPos_null() {
        String mylistExpected= listAddAtPos.toString();

        listAddAtPos.addAtPos(null, 1);
        assertEquals(mylistExpected, listAddAtPos.toString());

    }
}
