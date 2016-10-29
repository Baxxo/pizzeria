package it.pizzeria;

public class Cliente extends Thread {

	int n;
	ListaPizze lp;

	public Cliente(ListaPizze lp) {
		// TODO Auto-generated constructor stub
		this.lp = lp;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		n = (int) (Math.random() * 4 + 1);
		System.out.println("pizza: " + lp.pizze[n]);
		lp.pizzedaFare.add(lp.pizze[n]);
		/*try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
