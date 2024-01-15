package fr.ensma.a3.ia;

public class App 
{
    public static void main( String[] args ) {
    	TableauNoir tableauNoir = new TableauNoir();
    	
    	TH1 th1 = new TH1(tableauNoir);
    	TH2 th2 = new TH2(tableauNoir);
    	
    	Thread t1 = new Thread(th1, "TH1");
    	Thread t2 = new Thread(th2, "TH2");
    	
    	t1.start();
    	t2.start();
    }
}
