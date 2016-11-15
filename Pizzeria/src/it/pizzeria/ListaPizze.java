package it.pizzeria;

import java.util.ArrayList;

public class ListaPizze {

	Pizzeria p;
	int n;
	int index;
	
	// elenco pizze da fare
	ArrayList<String> pizzedaFare = new ArrayList<String>();
	// elenco pizze pronte
	ArrayList<String> pizzePronte = new ArrayList<String>();

	public ListaPizze(Pizzeria p) {
		this.p = p;
	}

	public ListaPizze() {

	}

	public synchronized void addPizzafare(String pizza) {
		pizzedaFare.add(pizza);
		System.out.println("pizza: " + pizza);
		notifyAll();

	}

	public synchronized void faiPizza() {
		index = pizzePronte.size();
		if(pizzedaFare.size() == 0)
			return;
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

		System.out.println("ho finito 1 una pizza");
		pizzePronte.add(pizzedaFare.get(0));
		pizzedaFare.remove(0);

		p.addList(pizzePronte.get(index));

		notifyAll();
	}
}
