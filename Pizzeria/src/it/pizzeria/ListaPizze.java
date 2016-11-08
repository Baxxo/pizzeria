package it.pizzeria;

import java.util.ArrayList;

public class ListaPizze {
	public static String pizze[] = {"Margherita","Capricciosa","4 Formaggi" , "Wurstel"};
	
	// elenco pizze da fare
	ArrayList<String> pizzedaFare = new ArrayList<String>();
	// elenco pizze pronte
	ArrayList<String> pizzePronte = new ArrayList<String>();
	
	public String getPizza(int n){
		return pizze[n];
	}
}
