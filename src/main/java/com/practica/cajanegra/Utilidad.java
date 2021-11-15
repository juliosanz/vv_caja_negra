package com.practica.cajanegra;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cajanegra.SingleLinkedListImpl;

public class Utilidad {
	

	/**
	 * Devuelve la lista que se va a pasar al metodo isSublist en
	 * las pruebas. Sera una lista cuyos elementos coincidan con
	 * los de la lista desde la que se llame al metodo a partir
	 * de la posicion p; si el tamanio de la sublista supera al
	 * de la lista, se rellena con "X".
	 * @param t Tamanio de la sublista.
	 * @param p Posicion a partir de la cual se copian los elementos
	 * de la lista padre.
	 * @return La sublista con la que se probara el metodo.
	 */
	public static SingleLinkedListImpl<String> getLista(int t, int p, SingleLinkedListImpl<String> listaActual)
	{
		System.out.println();
		System.out.println("Tamanio: " + t + "; Posicion: " + p);
		String[] nElementosArray = getLetras(listaActual);
		String[] solucion;
		if(t == 0)
		{
			return new SingleLinkedListImpl<String>();
		}
		else if(t == -1)
		{
			return null;
		}
		else
		{
			solucion = new String[t];
			int counter = 0;
			if(p < nElementosArray.length)
			{
				int i = p;
				while(i < nElementosArray.length && t != 0)
				{
					solucion[counter] = nElementosArray[i];
					System.out.print(solucion[counter]);
					counter++;
					t--;
					i++;
				}
			}
			if(t != 0)
			{
				for(; t > 0; t--)
				{
					solucion[counter] = "X";
					System.out.print(solucion[counter]);
					counter++;
				}
			}
			System.out.println();
			SingleLinkedListImpl<String> solucionLista = new SingleLinkedListImpl<String>(solucion);
			return solucionLista;
		}
	}
	
	
	/**
	 * Copia el producto cartesiano de dos arrays de strings en una matriz
	 * de strings a partir de la fila "start" de la matriz.
	 * @param params1 El primer string del producto cartesiano.
	 * @param params2 El segundo string del producto cartesiano.
	 * @param cartesianMatrix La matriz donde se copiara el producto cartesiano.
	 * @param start A partir de que fila de la matriz copiar el producto cartesiano.
	 */
	public static void getCartesian(String[] params1, String[] params2, String[][] cartesianMatrix, int start)
	{
		int k = start;
		for(int i = 0; i < params1.length; i++)
		{
			for(int j = 0; j < params2.length; j++)
			{
				cartesianMatrix[k][0] = params1[i];
				cartesianMatrix[k][1] = params2[j];
				k++;
			}
		}
	}
	
	/**
	 * Devuelve los elementos de la lista como array de strings para que
	 * sea mas facil operar con ellos.
	 * @param lista.
	 * @return array de strings donde cada elemento del array
	 * es un elemento de la lista.
	 */
	public static String[] getLetras(SingleLinkedListImpl<String> lista)
	{
		String nElementosString = lista.toString();
		
		ArrayList<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile("[A-Z]").matcher(nElementosString);
		while (m.find()) {
		   allMatches.add(m.group());
		}
		String[] arrayLista = new String[allMatches.size()];
		allMatches.toArray(arrayLista);
		
		return arrayLista;
	}
	
	/*
     * convierte una lista separada por comas en un objeto SingleLinkedListImpl y lo
     * asigna al atributo mylist de la clase 
     * 
     * */
    public static SingleLinkedListImpl<String> crearLista(String list) {
    	SingleLinkedListImpl<String> mylist = new SingleLinkedListImpl<String>();
        String[ ] elements = list.split(",");
         mylist = new SingleLinkedListImpl();
        for (String letra: elements)
            mylist.addLast(letra);
        return mylist;
    }
}
