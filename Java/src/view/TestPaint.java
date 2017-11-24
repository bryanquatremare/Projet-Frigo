package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestPaint {

	public static void main(String[] args) {
		new TestPaint();
	}

	public TestPaint() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}

				JFrame frame = new JFrame("Testing");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(new TestPane());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class TestPane extends JPanel {

		private static final long serialVersionUID = 1L;
		private final int ARR_SIZE = 8;

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
			int[] inttemps = new int[25];
			int[] exttemps = new int[25];
			for (int iterator = 0; iterator < 25; iterator++) {
				inttemps[iterator] = 15 + (int) (Math.random() * ((25 - 15) + 1));
				exttemps[iterator] = 15 + (int) (Math.random() * ((25 - 15) + 1));
			}
			for (int iterator = 0; iterator < 25; iterator++) {
				if (iterator == 1) {
					g.setColor(Color.BLUE);
					g.drawOval(80, 540 - (inttemps[1] - 10) * 20, 3, 3);
					g.setColor(Color.RED);
					g.drawOval(80, 540 - (exttemps[1] - 10) * 20, 3, 3);

				} else if (iterator <= 22) {
					for (int i = 1; i < iterator; i++) {
						g.setColor(Color.BLUE);
						g.drawOval(60 + i * 20, 550 - (inttemps[i] - 10) * 20, 3, 3);
						g.setColor(Color.RED);
						g.drawOval(60 + i * 20, 550 - (exttemps[i] - 10) * 20, 3, 3);
					}
					g.setColor(Color.BLUE);
					g.drawOval(60 + iterator * 20, 550 - (inttemps[iterator] - 10) * 20, 3, 3);
					g.setColor(Color.RED);
					g.drawOval(60 + iterator * 20, 550 - (exttemps[iterator] - 10) * 20, 3, 3);

				} else {
					float[] hsb = Color.RGBtoHSB(240, 240, 240, new float[3]);
					g.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
					g.fillPolygon(new Polygon(new int[] { 60, 60, 620, 620 }, new int[] { 60, 520, 520, 60 }, 4));
					for (int i = 1; i < 22; i++) {
						g.setColor(Color.BLUE);
						g.drawOval(60 + (i - 1) * 20, 550 - (inttemps[iterator - 23 + i] - 10) * 20, 3, 3);
						g.setColor(Color.RED);
						g.drawOval(60 + (i - 1) * 20, 550 - (exttemps[iterator - 23 + i] - 10) * 20, 3, 3);
					}
					g.setColor(Color.BLUE);
					g.drawOval(60 + 22 * 20, 550 - (inttemps[iterator] - 10) * 20, 3, 3);
					g.setColor(Color.RED);
					g.drawOval(60 + 22 * 20, 550 - (exttemps[iterator] - 10) * 20, 3, 3);
				}
			}

		}
	}
}
