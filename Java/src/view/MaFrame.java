package view;
import java.nio.charset.StandardCharsets;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import model.Consigne;
import model.SerialTest;


@SuppressWarnings("serial")
public class MaFrame extends JFrame {

	// dï¿½claration des variables
	private String inputarduino;
	@SuppressWarnings("unused")
	private String inputarduinodeux;
	@SuppressWarnings("unused")
	private String inputarduinotrois;
	@SuppressWarnings("unused")
	private Boolean inputarduinoquatre;
	@SuppressWarnings("unused")
	private String inputconsign;
	public int i;
	// dï¿½claration des ï¿½lï¿½ments de l'interface + l'interface
	static JDialog jdialog; // boite de dilogue (gï¿½nï¿½rale)
	static JDialog dialoguedeux; // boite de dialogue
	@SuppressWarnings("unused")
	private JLabel temperature, temperatureext, pointrosee, alarme; // Label
																	// (zone de
																	// texte
																	// simple)
	private JLabel consigne; // Label (consigne)
	static Button more, less, valider;
	static TextArea text;
	private Consigne consign = new Consigne();

	public int[] inttemps;
	public int[] exttemps;

	public MaFrame(){
		StandardCharsets.UTF_8.name();
		this.i = 0;
		this.inttemps = new int[23];
		this.exttemps = new int[23];
	}

	public void affichelaJFrame(SerialTest serialtest) { // affiche la frame

		// instancie les ï¿½lï¿½ments
		
		this.temperature = new JLabel("Voici donc la tempï¿½rature actuelle");
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
		// if (this.inputarduinoquatre == true) {
		// pane.add(alarme);
		// }

		// Mettre les bouttons et la zone de texte sur la fenï¿½tre
		pane.add(more);
		pane.add(less);
		pane.add(text);
		pane.add(consigne);
		pane.add(valider);
		main.add(new Canva(this.i, this.exttemps, this.inttemps));

		main.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				SerialTest serial = new SerialTest();
				serial.close();
			}
		});

		// set up de la fenêtre puis affichage
		main.setPreferredSize(new Dimension(1000, 800));
		main.pack();
		main.setLocationRelativeTo(null);
		main.setVisible(true);

		// crï¿½ation des ï¿½vï¿½nements des boutons +/-
		more.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consign.addOneConsign();
					serialtest.writeData(consign.getConsign());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		less.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consign.removeOneConsign();
					serialtest.writeData(consign.getConsign());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consign.alterConsign(text.getText());
					serialtest.writeData(consign.getConsign());

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// if (inputarduinoquatre == true) {
		// pane.add(alarme);
		// }
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

	public class Canva extends JPanel {

		private final int ARR_SIZE = 8;
		private int iterator;
		private int[] inttemps;
		private int[] exttemps;

		public Canva(int iterator, int[] inttemps, int[] exttemps) {
			this.iterator = iterator;
			this.inttemps = inttemps;
			this.exttemps = exttemps;
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(640, 640);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);

			g.drawLine(50, 540, 540, 540);
			g.fillPolygon(new int[] { 540, 540 - ARR_SIZE, 540 - ARR_SIZE },
					new int[] { 540, 540 - ARR_SIZE, 540 + ARR_SIZE }, 3);
			g.drawLine(50, 540, 50, 50);
			g.fillPolygon(new int[] { 50, 50 - ARR_SIZE, 50 + ARR_SIZE },
					new int[] { 50, 50 + ARR_SIZE, 50 + ARR_SIZE }, 3);

			for (int i = 60; i < 540; i += 20) {
				g.drawString("" + ((i - 50) / 20 + 10), 25, 530 - (i - 65));
				g.drawLine(i, 536, i, 544);
				g.drawLine(48, i + 10, 52, i + 10);
			}
			g.setFont(new Font("Impact", Font.PLAIN, 15));
			g.drawString("Tempï¿½rature (en ï¿½C)", 10, 40);
			g.drawString("Temps", 270, 600);
			if (this.iterator == 1) {
				g.setColor(Color.BLUE);
				g.drawOval(70, 540 - this.inttemps[1] * 20, 3, 3);
				g.setColor(Color.RED);
				g.drawOval(70, 540 - this.exttemps[1] * 20, 3, 3);

			} else if (this.iterator <= 22) {
				for (int i = 1; i < iterator; i++) {
					g.setColor(Color.BLUE);
					g.drawOval(50 + i * 20, 540 - this.inttemps[i] * 20, 3, 3);
					g.setColor(Color.RED);
					g.drawOval(50 + i * 20, 540 - this.exttemps[i] * 20, 3, 3);
				}
				g.setColor(Color.BLUE);
				g.drawOval(50 + this.iterator * 20, 540 - this.inttemps[this.iterator] * 20, 3, 3);
				g.setColor(Color.RED);
				g.drawOval(50 + this.iterator * 20, 540 - this.exttemps[this.iterator] * 20, 3, 3);

			} else {
				for (int i = 1; i < 22; i++) {
					g.setColor(Color.BLUE);
					g.drawOval(50 + (i - 1) * 20, 540 - this.inttemps[this.iterator - 23 + i] * 20, 3, 3);
					g.setColor(Color.RED);
					g.drawOval(50 + (i - 1) * 20, 540 - this.exttemps[this.iterator - 23 + i] * 20, 3, 3);
				}
				g.setColor(Color.BLUE);
				g.drawOval(50 + 22 * 20, 540 - this.inttemps[this.iterator] * 20, 3, 3);
				g.setColor(Color.RED);
				g.drawOval(50 + 22 * 20, 540 - this.exttemps[this.iterator] * 20, 3, 3);
			}
		}
	}
}
