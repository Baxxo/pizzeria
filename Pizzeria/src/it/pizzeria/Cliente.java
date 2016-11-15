package it.pizzeria;

public class Cliente extends Thread {

	int n;
	String pizza;
	ListaPizze lp;

	public Cliente(ListaPizze lp) {
		this.lp = lp;
	}

	public void setPizza(String pizza) {
		this.pizza = pizza;

	}

	@Override
	public void run() {

		lp.addPizzafare(pizza);

		
	}

}
