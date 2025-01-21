package vue;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import creature.AbstractCreature;
import creature.Araigne;
import creature.Dragon;
import creature.Minotaure;
import joueur.Joueur;
import modele.GestionnaireCombat;
import observer.MonObserver;


public class FenetreCombat extends JFrame implements MonObserver, WindowListener {

	protected Joueur hero;
	protected AbstractCreature creature;
	protected GestionnaireCombat gestionCombat;
	protected JTextArea text;
	protected JScrollPane paneauDefilant;
	protected JPanel panneauPrincipale;
	
	public FenetreCombat(Joueur hero, AbstractCreature creature, GestionnaireCombat gestionCombat ){
		this.hero = hero;
		this.creature = creature;
		this.gestionCombat = gestionCombat;
		this.configurerFenetrePrincipale();
		this.imageHero();
		this.boiteMessage();
		this.imageCreature();
		this.requestFocus();
		this.setVisible(true);
	}
	
	private void configurerFenetrePrincipale() {
		
		this.panneauPrincipale = (JPanel) this.getContentPane();
		this.setLocation(600, 300);
		this.setSize(800, 400);
		this.setLayout(new GridLayout(0, 3));

	}

	@Override
	public void avertir() {
		// TODO Auto-generated method stub
		this.text.setText(this.gestionCombat.getMsg());
		
	}
	
	private void imageHero() {
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("images/hero.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(new JLabel(new ImageIcon(image)));
	}
	
	private void boiteMessage() {
		
		this.text = new JTextArea(16, 20);
		this.text.setEditable(false);
		
		
		this.paneauDefilant = new JScrollPane(this.text);
		this.paneauDefilant.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.panneauPrincipale.add(this.paneauDefilant);
		
	}
	
	private void imageCreature() {

		BufferedImage image = null;
		

		
		if (this.creature instanceof Araigne) {
			try {
				image = ImageIO.read(new File("images/spider.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			add(new JLabel(new ImageIcon(image)));
			System.out.println("Combat contre l'Araigne!!!");

		}
		
		if (this.creature instanceof Dragon) {
			try {
				image = ImageIO.read(new File("images/dragon.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			add(new JLabel(new ImageIcon(image)));
			System.out.println("Combat contre le Dragon!!!");
		}

		if (this.creature instanceof Minotaure) {
			try {
				image = ImageIO.read(new File("images/minotaur.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			add(new JLabel(new ImageIcon(image)));
			System.out.println("Combat contre le Minotaure!!!");
		}



	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		gestionCombat.combatTermine();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	

}
