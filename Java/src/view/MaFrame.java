package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

	public class MaFrame extends Frame
	{    
	    
	    static JFrame frame;
	    static JDialog jdialog;
	    static JDialog dialoguedeux;
	    
	    public static void main(String[] args)
	    {
	      // schedule this for the event dispatch thread (edt)
	      SwingUtilities.invokeLater(new Runnable()
	      {
	          public void run()
	          {
	              affichelaJFrame();
	          }
	        });
	    }
	    
	    static void affichelaJFrame()
	      {        
	        frame = new JFrame("Interface de gestion du Frigo");
	        
	        // preparer les dialogues (listener)
	        jdialog = new JDialog(frame, "Bonjour", true);
	        dialoguedeux = new JDialog(frame, "La bonne journ�e",true);
	        
	        // ajouter un listeneur de feunaitre
	        jdialog.addWindowListener(new WindowAdapter()
	        {
	    public void windowClosed(WindowEvent e)
	          {
	                    System.out.println("JDialog fen�tre en cours de fermeture");
	          }

	    public void windowClosing(WindowEvent e)
	          {
	                    System.out.println("Dialogue num�ro un ferm�");
	          }
	        });
	        
	        dialoguedeux.addWindowListener(new WindowAdapter()
	        {
	    public void windowClosed(WindowEvent e)
	          {System.out.println("Fen�tre Ferm�e");
	          }
	          
	    public void windowClosing(WindowEvent e)
	          {
	                  System.out.println("Dialogue num�ro deux ferm�");
	          }
	        });

	        // ajouter un listeneur de composant
	        jdialog.addComponentListener(new ComponentListener()
	        {
	          public void componentHidden(ComponentEvent e)
	          {
	            System.out.println("Dialogue Ferm�");
	          }

	          public void componentMoved(ComponentEvent e)
	          {
	            System.out.println("Dialogue Boug�");
	          }

	          public void componentResized(ComponentEvent e)
	          {
	            System.out.println("Taille du dialogue modifi�");
	          }

	          public void componentShown(ComponentEvent e)
	          {
	            System.out.println("Dialogue Ouvert");
	          }
	        });
	        
	        dialoguedeux.addComponentListener(new ComponentListener()
	        {
	          public void componentHidden(ComponentEvent e)
	          {
	            System.out.println("Dialogue Ferm�");
	          }

	          public void componentMoved(ComponentEvent e)
	          {
	            System.out.println("Dialogue Boug�");
	          }

	          public void componentResized(ComponentEvent e)
	          {
	            System.out.println("Taille du dialogue modifi�");
	          }
	          public void componentShown(ComponentEvent e)
	          {
	            System.out.println("Dialogue Ouvert");
	          }
	        });

	        // Affichage du dialogue correspondant lors de l'appuiyage (LOL) d'un bouton
	        JButton showDialogButton = new JButton("Afficher temp�rature int�rieure");
	        showDialogButton.addActionListener(new ActionListener()
	        {
	          public void actionPerformed(ActionEvent e)
	          {
	            jdialog.setLocationRelativeTo(frame);
	            jdialog.setSize(300,200);
	            jdialog.setVisible(true);
	          }
	        });
	        
	        JButton montreledialoguePrevu = new JButton("Afficher Temp�rature ext�rieure");
	        montreledialoguePrevu.addActionListener(new ActionListener()
	        {
	  	 public void actionPerformed(ActionEvent e)
	          {
	            dialoguedeux.setLocationRelativeTo(frame);
	            dialoguedeux.setSize(300,200);
	            dialoguedeux.setVisible(true);            
	          }
	        });

	        // Mettre les bouttons sur la fen�tre
	        frame.getContentPane().setLayout(new FlowLayout());
	        frame.add(showDialogButton);
	        frame.add(montreledialoguePrevu);

	        // set up de la fen�tre puis affichage
	        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        frame.setPreferredSize(new Dimension(600, 400));
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	      }
	}
