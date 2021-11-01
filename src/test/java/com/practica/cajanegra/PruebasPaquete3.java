package com.practica.cajanegra;

import com.cajanegra.SingleLinkedListImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class PruebasPaquete3 {
	private static SingleLinkedListImpl<String> lista;
	private static SingleLinkedListImpl<String> vacia;
	
	@BeforeEach
	void init() {
		lista= new SingleLinkedListImpl<String>("C", "D", "E");
		vacia = new SingleLinkedListImpl<String>();
	}
	
	@ParameterizedTest()
	@CsvSource({
	"@",        
	"["
	})
	void addLastInvalido(String s) {
		String solucion = "[C, D, E]";
		lista.addLast(s);
		System.out.println(lista.toString());
		assertEquals(lista.toString(), solucion);
	}
	

	@ParameterizedTest()
	@CsvSource({
	"@",        
	"["
	})
	public void vaciaAddFirstInvalido(String str) {
		vacia.addFirst(str);
		String prueba = vacia.toString();
		String solucion = "[]";
	    assertEquals(prueba, solucion);
	}
	
	@ParameterizedTest()
	@CsvSource({
	"A",        
	"B",
	"M",
	"Y",
	"Z"
	})
	void addLastValido(String s) {
		lista.addLast(s);
		String solucion = "[C, D, E, " + s + "]";
		assertEquals(lista.toString(), solucion);
	}
	
}
