package com.practica.cajanegra.pruebasconsultas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.cajanegra.SingleLinkedListImpl;
import com.practica.cajanegra.Utilidad;

public class isSublistTest {
	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> nElementos;
	private static HashMap<String,SingleLinkedListImpl<String>> hmap;
	
	/*
	 * En el beforeAll asignamos valores al hashMap que usaremos
	 * en algunos de los metodos, por lo que tambien instanciamos
	 * cada una de las listas.
	 */
	
	@Test
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
	
	/*
	 * En el beforeEach reinstanciamos las variables que referencian cada 
	 * lista utilizada para asegurarnos de la atomicidad en cada metodo.
	 * 
	 */
	@Test
	@BeforeEach
	public void init() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
	}
	
	/**
	 * 
	 * @param t referencia el tamanio de la lista que vamos a buscar
	 * @param p referencia la posicion a partir de la que se encuentra la subLista
	 */
	@ParameterizedTest()
	@MethodSource("getIsSublistParameters")
	public void isSublistTests(int t, int p, boolean pruebaVacia)
	{
		SingleLinkedListImpl<String> listaPrueba;
		if(pruebaVacia)
		{
			listaPrueba = vacia;
		}
		else
		{
			listaPrueba = nElementos;
		}
		SingleLinkedListImpl<String> sublista = Utilidad.getLista(t, p - 1, listaPrueba);
		int parametroSalida = listaPrueba.isSubList(sublista);
		if(t == 0 || t == -1)
		{
			assertEquals(0, parametroSalida);
		}
		else if(listaPrueba.size() - (p - 1) - t < 0)
		{
			assertEquals(-1, parametroSalida);
		}
		else
		{
			assertEquals(p, parametroSalida);
		}
	}

	/**
	 * Devuelve los parametros de las pruebas del metodo isSublist.
	 * @return Matriz de strings donde cada fila es un caso de prueba.
	 */
	public static String[][] getIsSublistParameters()
	{
		String[] tamanos = { "-1", "0", "1", "2", "4", "5", "6" };
		String[] posiciones = { "1", "2", "4", "6", "7", "8" };
		String[][] casosDePrueba = new String[tamanos.length * posiciones.length * 2][3];
		Utilidad.getCartesian(tamanos, posiciones, casosDePrueba, 0);
		Utilidad.getCartesian(tamanos, posiciones, casosDePrueba, 42);
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
}
