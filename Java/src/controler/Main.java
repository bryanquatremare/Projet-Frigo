package controler;

import model.SerialTest;
import view.MaFrame;

public class Main {

	public static void main(String[] args) {
		MaFrame maframe = new MaFrame(); //initialise la frame
		maframe.run(new SerialTest()); //lance la frame avec un serialtest pour r�cup�rer les infos de l'arduino
	}
}
