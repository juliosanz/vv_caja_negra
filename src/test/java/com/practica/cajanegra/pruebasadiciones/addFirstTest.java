package com.practica.cajanegra.pruebasadiciones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.cajanegra.SingleLinkedListImpl;

public class addFirstTest {
	
	private static SingleLinkedListImpl<String> vacia;
	private static SingleLinkedListImpl<String> unElemento;
	private static SingleLinkedListImpl<String> dosElementos;
	private static SingleLinkedListImpl<String> nElementos;
	private static HashMap<String,SingleLinkedListImpl<String>> hmap;
	
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
	
	@Test
	@BeforeEach
	public void init() {
		vacia = new SingleLinkedListImpl<String>();
		unElemento = new SingleLinkedListImpl<String>("A");
		dosElementos = new SingleLinkedListImpl<String>("A", "B");
		nElementos = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E", "F", "G");
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
	"[,nElementos",
	",vacia",
	",unElemento",
	",dosElementos"
	})
	public void addFirstInvalidos(String str, String key) {
		SingleLinkedListImpl<String> lista = hmap.get(key);
		String solucion = lista.toString();
		lista.addFirst(str);
		String prueba = lista.toString();
		assertEquals(solucion, prueba);
	}
}
