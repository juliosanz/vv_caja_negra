package com.practica.cajanegra;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.cajanegra.SingleLinkedListImpl;

public class PruebasAdiciones {

	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> lista3Elem;
	private static SingleLinkedListImpl<String> nElementos;
	private static SingleLinkedListImpl<String> listAddAtPos;
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
		listAddAtPos = new SingleLinkedListImpl<String>("L", "M", "N", "O", "P", "Q");
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
		listAddAtPos = new SingleLinkedListImpl<String>("L", "M", "N", "O", "P", "Q");
		listConRepetidos = new SingleLinkedListImpl<>("A", "B", "C", "A", "M", "A", "B", "Y", "Z", "Y", "O");
	}
	
	@ParameterizedTest
	@MethodSource("getAddNTimesParameters")
	void addNTimesTests(String letra, int n, boolean pruebaVacia)
	{
		SingleLinkedListImpl<String> lista;
		if(pruebaVacia)
		{
			lista = vacia;
		}
		else
		{
			lista = nElementos;
		}
		// Si n es negativo, comprobamos que lance la excepcion que debe
        // lanzar segun la documentacion.
		if(n < 0)
		{
			assertThrows(IllegalArgumentException.class, () -> {
				lista.addNTimes(letra, n);
			});
		}
		else
		{
			String[] elementosIniciales = Utilidad.getLetras(lista);
			// Llegados a este bloque, n es mayor o igual que cero por el
			// if anterior, luego la excepcion no debería lanzarse.
			try
			{
				lista.addNTimes(letra, n);
			}
			catch(IllegalArgumentException e)
			{
				fail("Excepcion inesperada");
			}
			String[] elementosFinales = Utilidad.getLetras(lista);
			
			// Comprobamos primero si los elementos de la lista inicial
			// han permanecido igual y en su misma posicion.
			int index;
			for(index = 0; index < elementosIniciales.length; index++)
			{
				assertEquals(elementosIniciales[index], elementosFinales[index]);
			}
			// Si la letra insertada no es valida, nos aseguramos de que no se
			// haya insertado en la lista.
			if(letra == "@" || letra == "[")
			{
				assertEquals(elementosIniciales.length, elementosFinales.length);
			}
			// En otro caso, nos aseguramos de que la letra insertada sea la correcta
			// y que se haya insertado "n" veces.
			else
			{
				int counter = 0;
				for(; index < elementosFinales.length; index++)
				{
					assertEquals(letra, elementosFinales[index]);
					counter++;
				}
				assertEquals(n, counter);
			}
		}
	}
	
	/**
	 * Devuelve los parametros para los tests del metodo AddNTimes
	 * @return Matriz donde cada fila es un caso de prueba.
	 */
	public static String[][] getAddNTimesParameters()
	{
		String[] letras = {"[", "A", "B", "M", "Y", "Z", "@"};
		String[] enes = {"-1", "0", "1", "5", "9", "10", "11"};
		String[][] casosDePrueba = new String[letras.length * enes.length * 2][3];
		Utilidad.getCartesian(letras, enes, casosDePrueba, 0);
		Utilidad.getCartesian(letras, enes, casosDePrueba, 49);
		int index;
		for(index = 0; index < casosDePrueba.length / 2; index++)
		{
			casosDePrueba[index][2] = "true";
		}
		for(; index < casosDePrueba.length; index++)
		{
			casosDePrueba[index][2] = "false";
		}
		return casosDePrueba;
	}
	
	/*
	 * 	El siguiente conjunto de tests comprueba
	 * 	la funcionalidad del metodo addFirst
	 * 	mediante Robustness for worst case
	 */
	
	@DisplayName("Add First para los casos validos")
	@ParameterizedTest()
	@CsvSource({
	"Z,vacia",  
	"Z,unElemento",
	"Z,dosElementos",
	"Z,nElementos",
	"Y,vacia",  
	"Y,unElemento",
	"Y,dosElementos",
	"Y,nElementos",
	"M,vacia",  
	"M,unElemento",
	"M,dosElementos",
	"M,nElementos",
	"B,vacia",  
	"B,unElemento",
	"B,dosElementos",
	"B,nElementos",
	"A,vacia",  
	"A,unElemento",
	"A,dosElementos",
	"A,nElementos",
	})
	public void addFirstValidos(String str, String key) {
		SingleLinkedListImpl<String> lista = hmap.get(key);
		String solucion = lista.toString();
		solucion = "[" + str + ", " + solucion.substring(1);
		lista.addFirst(str);
		String prueba = lista.toString();
		assertEquals(solucion, prueba);
	}
	
	@DisplayName("Add First para los casos invalidos")
	@ParameterizedTest()
	@CsvSource({
	"@,vacia",        
	"[,vacia",
	"@,unElemento",        
	"[,unElemento",
	"@,dosElementos",        
	"[,dosElementos",
	"@,nElementos",        
	"[,nElementos"
	})
	public void addFirstInvalidos(String str, String key) {
		SingleLinkedListImpl<String> lista = hmap.get(key);
		String solucion = lista.toString();
		lista.addFirst(str);
		String prueba = lista.toString();
		assertEquals(solucion, prueba);
	}
	

	@ParameterizedTest()
	@CsvSource({
	"@",        
	"["
	})
    public void addLastInvalido(String s) {
        int sizeOri = lista3Elem.size();
		String solucion = lista3Elem.toString();
        lista3Elem.addLast(s);
        assertEquals(sizeOri, lista3Elem.size(), "An invalid element has been wrongfully added.");
        assertEquals(lista3Elem.toString(), solucion);
	}
	
    @Test
    public void addLastInvalidoConNull() {
      int sizeOri = lista3Elem.size();
      assertThrows(NullPointerException.class, () -> {
        lista3Elem.addLast(null);
      });
      assertEquals(lista3Elem.size(), sizeOri, "A null element has been wrongfully added");
    }
	
	@ParameterizedTest()
	@CsvSource({
	"A",        
	"B",
	"M",
	"Y",
	"Z"
	})
    public void addLastValido(String s) {
        int sizeOri = lista3Elem.size();
        lista3Elem.addLast(s);
		String solucion = "[C, D, E, " + s + "]";
        assertEquals(sizeOri + 1, lista3Elem.size());
        assertEquals(lista3Elem.toString(), solucion);
	}
	
    @Test
    public void addLastValidoListaVacia() {
      int sizeOri = vacia.size();
      String solucion = "[M]";
      vacia.addLast("M");
      assertEquals(sizeOri + 1, vacia.size());
      assertEquals(vacia.toString(), solucion);
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
