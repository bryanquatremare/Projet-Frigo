package controler;

import model.SerialTest;
import view.MaFrame;

public class Main {

	public static void main(String[] args) {
		MaFrame maframe = new MaFrame(); // initialise la frame
		SerialTest main = new SerialTest();
		main.initialize();
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException ie) {
					System.err.println(ie.toString());
				}
			}
		};
		t.start();
		main.run(maframe); // lance la frame avec un serialtest pour récupérer
							// les infos de l'arduino
	}
}
