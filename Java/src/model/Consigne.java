package model;

import java.io.IOException;

public class Consigne {
	private SerialTest serial;

	private int consigne = 20;
	
	public Consigne() {
		
	}
	public int getConsign() {
		return consigne;
	}
	public void setConsign(int rep) {
		this.consigne = rep;
	}
	public void alterConsign(String test) throws IOException {
		int i = Integer.parseInt(test);
		if (i >= 15 && i <= 30) {
			setConsign(i);
			serial.writeData(Integer.toString(getConsign()));
		}
	}
	public void addOneConsign() throws IOException {
		if (getConsign() < 30) {
			setConsign(getConsign()+1);
			serial.writeData("+");;
		}
	}
	public void removeOneConsign() throws IOException {
		if (getConsign() > 15) {
			setConsign(getConsign()-1);
			serial.writeData("-");
		}
	}
}
