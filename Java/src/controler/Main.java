package controler;

import model.SerialTest;
import view.MaFrame;

public class Main {

	public static void main(String[] args) {
		// schedule this for the event dispatch thread (edt)
		MaFrame maframe = new MaFrame();
		SerialTest serialTest = new SerialTest();
		maframe.run(serialTest);
	}
}
