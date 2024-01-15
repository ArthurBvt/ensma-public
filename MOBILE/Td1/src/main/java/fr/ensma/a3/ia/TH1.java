package fr.ensma.a3.ia;

public class TH1 extends Thread {

	private TableauNoir tabNoir;
	
	public TH1(TableauNoir tabNoir) {
		this.tabNoir = tabNoir;
	}
	
	@Override
	public void run() {
		int c = 1;
		while (true) {
			tabNoir.setValeur(c);
			System.out.println(Thread.currentThread().getName() + " - " + tabNoir.getValeur());
			c++;
			
			try {
				Thread.sleep(350);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
