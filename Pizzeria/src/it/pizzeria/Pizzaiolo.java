package it.pizzeria;

public class Pizzaiolo extends Thread{

	ListaPizze lp;
	String pizza;
	int n;

	public Pizzaiolo(ListaPizze lp) {
		this.lp = lp;
	}

	public void run(String nome) {
		// TODO Auto-generated method stub
		// verifica se c'è una pzza da fare
		if (lp.pizzedaFare.isEmpty() == false) {
			pizza = lp.pizzedaFare.get(0);
		}
		
		pizza = nome;
		
		System.out.println(pizza);
		
		switch (pizza) {
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
		
		notifyAll();
		
	}

}
