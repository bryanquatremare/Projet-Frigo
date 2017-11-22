package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.SerialTest;

@SuppressWarnings("serial")
public class MaFrame extends JFrame {

	private String inputarduino;
	private String inputarduinodeux;
	private String inputarduinotrois;
	private Boolean inputarduinoquatre;
	static JDialog jdialog;
	static JDialog dialoguedeux;
	private JLabel temperature, temperatureext, pointrosee, alarme;
	static Button more, less, valider;
	static TextArea text;

	public void affichelaJFrame(SerialTest serialtest) { // affiche la frame

		this.temperature = new JLabel("Voici donc la temp�rature actuelle");
		this.temperatureext = new JLabel("Voici donc la temperature exterieure");
		this.pointrosee = new JLabel("Voici le seuil limite en-dessous duquel de la condensation apparaitrait");
		this.alarme = new JLabel("ALERTE ALERTE ALERTE !!!!");
		JFrame main = new JFrame("Interface de gestion du Frigo");
		more = new Button("+");
		less = new Button("-");
		text = new TextArea(1, 4);
		valider = new Button("Valider");
		JPanel pane = new JPanel(new GridLayout(0, 1));
		main.setLayout(new GridLayout(0, 1));
		main.add(pane);
		pane.add(temperature);
		pane.add(temperatureext);
		pane.add(pointrosee);

		// Mettre les bouttons et la zone de texte sur la fen�tre
		pane.add(more);
		pane.add(less);
		pane.add(text);
		pane.add(valider);

		// set up de la fen�tre puis affichage
		main.setPreferredSize(new Dimension(600, 400));
		main.pack();
		main.setLocationRelativeTo(null);
		main.setVisible(true);
	}

	public void setTextLabel(String text) {
		this.temperature.setText(text);
	}

	public void setTextLabeldeux(String text) {
		this.temperatureext.setText(text);
	}

	public void setTextLabelTrois(String text) {
		this.pointrosee.setText(text);
	}

	public void setInputArduino(String text) {
		this.inputarduino = text;
	}

	public void setInputArduinodeux(String text) {
		this.inputarduinodeux = text;
	}

	public void setInputArduinotrois(String text) {
		this.inputarduinotrois = text;
	}
	
	public void setInputArduinoquatre(Boolean bool) {
		this.inputarduinoquatre = bool;
	}

	public String getInputArduino() {
		return this.inputarduino;
	}
}
