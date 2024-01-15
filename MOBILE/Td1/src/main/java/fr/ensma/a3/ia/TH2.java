package fr.ensma.a3.ia;

public class TH2 extends Thread {

	TableauNoir tabNoir;
	
	public TH2(TableauNoir tabNoir) {
		this.tabNoir = tabNoir;
	}
	
	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + " - " + tabNoir.getValeur() * 10);
		
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
