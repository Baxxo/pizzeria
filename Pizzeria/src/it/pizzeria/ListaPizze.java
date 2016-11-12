package it.pizzeria;

import java.util.ArrayList;

public class ListaPizze {
	
	Pizzeria p;
	int n;

	// elenco pizze da fare
	ArrayList<String> pizzedaFare = new ArrayList<String>();
	// elenco pizze pronte
	ArrayList<String> pizzePronte = new ArrayList<String>();

	public String getPizza(int n) {
		return "";// pizze[n];
	}

	public synchronized void addPzzafare(String pizza) {
		pizzedaFare.add(pizza);
		System.out.println("pizza: " + pizza);
		notifyAll();

	}

	public synchronized void faiPizza() {
		System.out.println("faccio pizza");
		System.out.println(pizzedaFare.get(0));

		switch (pizzedaFare.get(0)) {
		case "Margherita":
			n = 1000;
			break;
		case "Capricciosa":
			n = 2500;
			break;
		case "4 Formaggi":
			n = 2000;
			break;
		case "Wurstel":
			n = 1500;
			break;
		}

		// fa la pizza ( aspetta tot secondi)
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// avvisa la lsita pizze che la pizza è pronta
		System.out.println("ho finito 1 una pizza");
		p.list_2.add(pizzePronte.toString());
		
		pizzePronte.add(pizzedaFare.get(0));
		pizzedaFare.remove(0);
		
		notifyAll();
	}
}
