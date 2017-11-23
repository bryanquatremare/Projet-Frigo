package model;

import java.io.IOException;

public class Consigne {
	private String reponse;

	private int consigne = 20;

	public Consigne() {

	}

	public int getConsign() {
		return this.consigne;
	}

	public String getReponse() {
		return this.reponse;
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
			this.setConsign(i);
			this.reponse = Integer.toString(i);
		} else {
			this.reponse = null;
		}
	}

	public void addOneConsign() throws IOException {
		if (this.getConsign() >= 30) {
			this.setConsign(this.getConsign() - 1);
		}
		this.reponse = "+";
		this.setConsign(this.getConsign() + 1);
	}

	public void removeOneConsign() throws IOException {
		if (this.getConsign() <= 15) {
			this.setConsign(this.getConsign() + 1);
		}
		this.reponse = "-";
		this.setConsign(this.getConsign()-1);
	}
}