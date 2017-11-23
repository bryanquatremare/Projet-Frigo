package view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import model.Consigne;
import model.SerialTest;

@SuppressWarnings("serial")
public class MaFrame extends JFrame {

	// déclaration des variables
	private String inputarduino;
	private String inputarduinodeux;
	private String inputarduinotrois;
	private Boolean inputarduinoquatre;
	private String inputconsign;
	// déclaration des éléments de l'interface + l'interface
	static JDialog jdialog; // boite de dilogue (générale)
	static JDialog dialoguedeux; // boite de dialogue
	private JLabel temperature, temperatureext, pointrosee, alarme; //Label (zone de texte simple)
	private JLabel consigne; // Label (consigne)
	static Button more, less, valider;
	static TextArea text;
	private Consigne consign = new Consigne();

	public void affichelaJFrame(SerialTest serialtest) { // affiche la frame
		
		// instancie les éléments
		this.temperature = new JLabel("Voici donc la température actuelle");
		this.temperatureext = new JLabel("Voici donc la temperature exterieure");
		this.pointrosee = new JLabel("Voici le seuil limite en-dessous duquel de la condensation apparaitrait");
		this.alarme = new JLabel("ALERTE ALERTE ALERTE !!!! /n Risque de condensation, augmenter la consigne");
		this.consigne = new JLabel(Integer.toString(this.consign.getConsign()));
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
//		if (this.inputarduinoquatre == true) {
//			pane.add(alarme);
//		}

		// Mettre les bouttons et la zone de texte sur la fenêtre
		pane.add(more);
		pane.add(less);
		pane.add(text);
		pane.add(consigne);
		pane.add(valider);
		
		main.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				SerialTest serial = new SerialTest();
				serial.close();
			}
		});

		// set up de la fenêtre puis affichage
		main.setPreferredSize(new Dimension(600, 400));
		main.pack();
		main.setLocationRelativeTo(null);
		main.setVisible(true);
		
		//création des évênements des boutons +/-
		more.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					consign.addOneConsign();
					serialtest.writeData(consign.getReponse());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		less.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					consign.removeOneConsign();
					serialtest.writeData(consign.getReponse());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		valider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					consign.alterConsign(text.getText());
					serialtest.writeData(consign.getReponse());
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
//		if (inputarduinoquatre == true) {
//			pane.add(alarme);
//		}
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
	public void setInputConsign(String inputConsign) {
		this.inputconsign = inputConsign;
	}
}
