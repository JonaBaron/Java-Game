package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

import modele.PlanDeJeu;

public class PanneauStatusHaut extends JPanel{

	protected JLabel nom;
	protected JProgressBar vieBar;
	protected JLabel niveauJLabel; 
	protected JLabel tue;
	protected JLabel temps;


	public PanneauStatusHaut() {
		setVisible(true);
		setLayout(new GridLayout(5, 1));

		Border blackline = BorderFactory.createLineBorder(Color.black);

		setBorder(blackline);

		nom = new JLabel("Leeroy Jenkins");
		nom.setFont(new Font(Font.SERIF, Font.ITALIC | Font.BOLD,24));
		nom.setHorizontalAlignment(JLabel.CENTER);
		add(nom);

		vieBar = new JProgressBar();
		vieBar.setValue(100);
		vieBar.setForeground(Color.green);
		vieBar.setBackground(Color.red);
		add(vieBar);

		niveauJLabel = new JLabel();
		niveauJLabel.setText("Niveau : 0");
		niveauJLabel.setHorizontalAlignment(JLabel.CENTER);
		add(niveauJLabel);

		tue = new JLabel();
		tue.setText("Nb Enemis Tues : 0");
		tue.setHorizontalAlignment(JLabel.CENTER);
		add(tue);

		temps = new JLabel();
		temps.setText("Temps de jeu : 0 secondes");
		temps.setHorizontalAlignment(JLabel.CENTER);
		add(temps);

	}

	public void mettreAJoursInfo(PlanDeJeu plan) {

		tue.setText("Nb Enemis Tues : " + plan.getGestionnaireCombat().getCreatureTue());
		niveauJLabel.setText("Niveau : " + plan.getNiveau());

		Long tempsPris = plan.getEnd() - plan.getStart();
		tempsPris = tempsPris / 1000000000;
		tempsPris = tempsPris / 2;

		String tempsString = String.valueOf(tempsPris);
		this.temps.setText("Temps de jeu : " + tempsString + "secondes");

		vieBar.setValue(plan.getJoueur().getPointDeVie()*100 / plan.getJoueur().getPointDeVieMax());


	}

}
