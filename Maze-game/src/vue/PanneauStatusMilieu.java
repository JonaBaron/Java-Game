package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import equipements.AbstractEquipement;
import equipements.Arme;
import equipements.Armure;
import equipements.Casque;
import equipements.Potion;
import modele.PlanDeJeu;

public class PanneauStatusMilieu extends JPanel implements ItemListener{

	protected JPanel panHero = new JPanel();
	protected BufferedImage image;
	protected JPanel panEquipement;
	protected JLabel defence;
	protected JLabel casqueTexte;
	protected JComboBox<Casque> casqueBox;
	protected JLabel armureTexte;
	protected JComboBox<Armure> armureBox;
	protected JLabel attaque;
	protected JLabel armeTexte;
	protected JComboBox<Arme> armeBox ;
	protected JLabel nbPotion;
	protected JButton bouton;
	protected int nbPotions;

	public PanneauStatusMilieu(PlanDeJeu plan) {

		Border blackline = BorderFactory.createLineBorder(Color.black);

		setBorder(blackline);

		this.setLayout(new GridLayout(1, 2));

		this.add(panneauHero());

		this.add(panneauEquipement(plan));


		this.setVisible(true);
	}

	public JPanel panneauHero() {

		try {
			image = ImageIO.read(new File("images/hero.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panHero.add(new JLabel(new ImageIcon(image)));

		return panHero;

	}

	public JPanel panneauEquipement(PlanDeJeu plan) {

		panEquipement = new JPanel();

		panEquipement.setLayout(new GridLayout(10, 1));

		defence = new JLabel();
		defence.setText("Defence total = ");
		panEquipement.add(defence);

		casqueTexte = new JLabel("Casque:");
		panEquipement.add(casqueTexte);

		casqueBox = new JComboBox<>();
		panEquipement.add(casqueBox);

		armureTexte = new JLabel("Armure:");
		panEquipement.add(armureTexte);

		armureBox = new JComboBox<>();
		panEquipement.add(armureBox);

		attaque = new JLabel();
		attaque.setText("Attaque total = ");
		panEquipement.add(attaque);

		armeTexte = new JLabel("Arme:");
		panEquipement.add(armeTexte);

		armeBox = new JComboBox<>();
		panEquipement.add(armeBox);

		nbPotion = new JLabel();
		nbPotion.setText("Nb Potion = 0");
		panEquipement.add(nbPotion);

		bouton = new JButton("Utiliser Potion");
		bouton.setEnabled(false);
		bouton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				plan.getJoueur().utiliserPotion();
				System.out.println("Potion utiliser");
			}
		});
		panEquipement.add(bouton);

		return panEquipement;

	}

	public void mettreAJoursInfo(PlanDeJeu plan) {

		attaque.setText("Attaque total = " + plan.getJoueur().getForce());
		defence.setText("Defence total = " + plan.getJoueur().getArmure());

		casqueBox.removeAllItems();
		armureBox.removeAllItems();
		armeBox.removeAllItems();

		nbPotions = 0;

		ListIterator<AbstractEquipement> iterator = plan.getJoueur().getEquipements().listIterator();

		while(iterator.hasNext()) {
			AbstractEquipement trouver = iterator.next();

			if(trouver instanceof Casque) {
				casqueBox.addItem((Casque) trouver);
				plan.getJoueur().equiper((Casque) trouver);
			}

			if(trouver instanceof Armure) {
				armureBox.addItem((Armure) trouver);
				plan.getJoueur().equiper((Armure) trouver);
			}

			if(trouver instanceof Arme) {
				armeBox.addItem((Arme) trouver);
				plan.getJoueur().equiper((Arme) trouver);
			}

			if(trouver instanceof Potion) {
				nbPotions++;

			}

		}
		nbPotion.setText("Nb Potion = " + nbPotions);

		if(nbPotions > 0)
			bouton.setEnabled(true);
		else {
			bouton.setEnabled(false);
		}


	}

	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

		PlanDeJeu plan =  PlanDeJeu.getInstance();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Object item = e.getItem();
			plan.getJoueur().equiper((AbstractEquipement) item);

		}
	}

}
