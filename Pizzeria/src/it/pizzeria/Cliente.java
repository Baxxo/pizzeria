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

		lp.addPzzafare(pizza);

		// System.out.println("pizza: " + lp.pizze[n]);
		// lp.pizzedaFare.add(lp.pizze[n]);
		/*
		 * try { wait(); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

}
