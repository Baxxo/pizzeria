package it.pizzeria;

import java.util.ArrayList;

public class ListaPizze {
	

	int n = 1;

	// elenco pizze da fare
	ArrayList<String> pizzedaFare = new ArrayList<String>();
	// elenco pizze pronte
	ArrayList<String> pizzePronte = new ArrayList<String>();

	public String getPizza(int n) {
		return "";// pizze[n];
	}
	
	public synchronized void addPzzafare(String pizza){
		pizzedaFare.add(pizza);
	}
}
