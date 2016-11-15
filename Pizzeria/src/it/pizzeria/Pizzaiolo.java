package it.pizzeria;

public class Pizzaiolo extends Thread {

	ListaPizze lp;
	String pizza;
	int n;

	public Pizzaiolo(ListaPizze lp) {
		this.lp = lp;
	}

	public void setPizza(String nome) {
		pizza = nome;
	}

	public void run() {

		// verifica se c'è pizza da fare
		while (true) {
			if (lp.pizzedaFare.isEmpty() == false) {
				lp.faiPizza();
			} else {
				// System.out.println("sto aspettando");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

	}

}
