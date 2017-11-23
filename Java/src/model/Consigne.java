package model;

import java.io.IOException;

public class Consigne {
	private String reponse;

	private int consigne = 20;

	public Consigne() {

	}

	public int getConsign() {
		return consigne;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String rep) {
		this.reponse = rep;
	}

	public void setConsign(int rep) {
		this.consigne = rep;
	}

	public void alterConsign(String test) throws IOException {
		int i = Integer.parseInt(test);
		if (i >= 15 && i <= 30) {
			setConsign(i);
			this.reponse = Integer.toString(i);
		} else {
			this.reponse = null;
		}
	}

	public void addOneConsign() throws IOException {
		if (getConsign() < 30) {
			setConsign(getConsign() + 1);
			this.reponse = "+";
		}
	}

	public void removeOneConsign() throws IOException {
		if (getConsign() > 15) {
			setConsign(getConsign() - 1);
			this.reponse = "-";
		}
	}
}