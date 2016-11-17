package it.pizzeria;

import java.util.ArrayList;

public class ListaPizze {

	Pizzeria p;
	int n;
	int index;
	Audio a = new Audio();

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
		//System.out.println("pizza: " + pizza);
		notifyAll();

	}

	public synchronized void faiPizza() {
		index = pizzePronte.size();
		if (pizzedaFare.size() == 0)
			return;
		//System.out.println("faccio pizza");
		//System.out.println(pizzedaFare.get(0));

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
		p.forno(pizzedaFare.get(0));
		a.setSound("Fire_Burning");
		a.suona();
		if (Thread.currentThread().getName().equals("primo")) {
			p.updateBar(n);
		}
		if (Thread.currentThread().getName().equals("sec")) {
			p.updateBar2(n);
		}

		// fa la pizza ( aspetta tot secondi)
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		a.ferma();
		a.setSound("chinese-gong-daniel_simon");
		a.suona();
		pizzePronte.add(pizzedaFare.get(0));
		pizzedaFare.remove(0);
		p.forno("");
		p.addList(pizzePronte.get(index));
		p.togliPizza();

		notifyAll();
	}
}
