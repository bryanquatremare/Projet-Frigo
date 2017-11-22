package model;

public class Consigne {

	private float consigne;
	public float getConsign() {
		return consigne;
	}
	public void setConsign(float rep) {
		this.consigne = rep;
	}
	public void alterConsign(String test) {
		float i = Float.parseFloat(test);
		if (i >= 15 && i <= 30) {
			
			setConsign(i);
		}
	}
	public void addOneConsign(String test) {
		if (Float.parseFloat(test) < 30) {
			setConsign(getConsign()+1);
		}
	}
	public void removeOneConsign(String test) {
		if (Float.parseFloat(test) > 15) {
			setConsign(getConsign()-1);
		}
	}
}
