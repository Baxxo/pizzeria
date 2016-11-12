package it.pizzeria;

public class Cliente extends Thread {

	int n;
	String pizza;
	ListaPizze lp;

	public Cliente(ListaPizze lp) {
		this.lp = lp;
	}

	public void setPizza(String pizza, int n) {
		this.pizza = pizza;
		this.n = n;

	}

	@Override
	public synchronized void run() {
		System.out.println("pizza: " + lp.pizze[n]);
		lp.pizzedaFare.add(lp.pizze[n]);
		/*
		 * try { wait(); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

}
