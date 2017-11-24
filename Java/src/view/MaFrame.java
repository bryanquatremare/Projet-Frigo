package view;

import java.nio.charset.StandardCharsets;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import model.Consigne;
import model.SerialTest;

public class MaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// déclaration des variables
	private String inputarduino = null;
	private String inputarduinodeux = null;
	private String inputarduinotrois = null;
	private Boolean inputarduinoquatre = false;
	@SuppressWarnings("unused")
	private String inputconsign = null;
	public int i;

	// déclaration des éléments de l'interface + l'interface
	static JDialog jdialog; // boite de dilogue (générale)
	static JDialog dialoguedeux; // boite de dialogue
	private JLabel temperature, temperatureext, pointrosee, alarme; // Label
																	// (zone de
																	// texte
																	// simple)
	private JLabel consigne; // Label (consigne)
	static JButton more, less, valider;
	static TextArea text;
	private Consigne consign = new Consigne();

	public int[] inttemps;
	public int[] exttemps;

	public MaFrame() {
		StandardCharsets.UTF_8.name();
		this.i = 0;
		this.inttemps = new int[23];
		this.exttemps = new int[23];
	}

	public void affichelaJFrame(SerialTest serialtest) { // affiche la frame
		// instancie les éléments
		boolean bool = this.inputarduinoquatre;
		this.temperature = new JLabel(this.inputarduino);
		this.temperatureext = new JLabel(this.inputarduinodeux);
		this.pointrosee = new JLabel(this.inputarduinotrois);
		this.alarme = new JLabel("ALERTE ALERTE ALERTE !!!! \n Risque de condensation, augmenter la consigne");
		this.consigne = new JLabel(Integer.toString(this.consign.getConsign()));
		JFrame main = new JFrame("Interface de gestion du Frigo");
		more = new JButton("+");
		less = new JButton("-");
		text = new TextArea(1, 4);
		valider = new JButton("Valider");
		JPanel pane = new JPanel(new GridLayout(0, 1));
		main.setLayout(new GridLayout(0, 1));
		main.add(pane);
		pane.add(temperature);
		pane.add(temperatureext);
		pane.add(pointrosee);

		if (bool == true) {
			pane.add(alarme);
		}

		// Mettre les bouttons et la zone de texte sur la fenêtre
		pane.add(more);
		pane.add(less);
		pane.add(text);
		pane.add(consigne);
		pane.add(valider);
		main.add(new Canva(this.i, this.exttemps, this.inttemps));

		// set up de la fenêtre puis affichage
		main.setPreferredSize(new Dimension(1850, 1080));
		main.pack();
		main.setLocation(70,0);
		main.setVisible(true);

		// création des évenements des boutons +/-
		more.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consign.addOneConsign();
					serialtest.writeData(consign.getConsign());
					consigne.setText(Integer.toString(consign.getConsign()));
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
					consigne.setText(Integer.toString(consign.getConsign()));
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
					consigne.setText(Integer.toString(consign.getConsign()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
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

	@SuppressWarnings("serial")
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
			g.drawString("Température (en °C)", 10, 40);
			g.drawString("Temps", 270, 600);
			if (this.iterator == 1) {
				g.setColor(Color.BLUE);
				g.drawOval(80, 540 - (this.inttemps[1] - 10) * 20, 3, 3);
				g.setColor(Color.RED);
				g.drawOval(80, 540 - (this.exttemps[1] - 10) * 20, 3, 3);

			} else if (this.iterator <= 22) {
				for (int i = 1; i < this.iterator; i++) {
					g.setColor(Color.BLUE);
					g.drawOval(60 + i * 20, 550 - (this.inttemps[i] - 10) * 20, 3, 3);
					g.setColor(Color.RED);
					g.drawOval(60 + i * 20, 550 - (this.exttemps[i] - 10) * 20, 3, 3);
				}
				g.setColor(Color.BLUE);
				g.drawOval(60 + this.iterator * 20, 550 - (this.inttemps[this.iterator] - 10) * 20, 3, 3);
				g.setColor(Color.RED);
				g.drawOval(60 + this.iterator * 20, 550 - (this.exttemps[this.iterator] - 10) * 20, 3, 3);

			} else {
				float[] hsb = Color.RGBtoHSB(240, 240, 240, new float[3]);
				g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
				g.fillPolygon(new Polygon(new int[] { 60, 60, 620, 620 }, new int[] { 60, 520, 520, 60 }, 4));
				for (int i = 1; i < 22; i++) {
					g.setColor(Color.BLUE);
					g.drawOval(60 + (i - 1) * 20, 550 - (this.inttemps[this.iterator - 23 + i] - 10) * 20, 3, 3);
					g.setColor(Color.RED);
					g.drawOval(60 + (i - 1) * 20, 550 - (this.exttemps[this.iterator - 23 + i] - 10) * 20, 3, 3);
				}
				g.setColor(Color.BLUE);
				g.drawOval(60 + 22 * 20, 550 - (this.inttemps[this.iterator] - 10) * 20, 3, 3);
				g.setColor(Color.RED);
				g.drawOval(60 + 22 * 20, 550 - (this.exttemps[this.iterator] - 10) * 20, 3, 3);
			}
		}
	}
}
