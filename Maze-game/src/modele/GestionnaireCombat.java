package modele;

/**
 * Gestionnaire des combats prenant place entre le hero et les
 * creatures du donjon.
 * 
 * Le gestionnaire lance un pop-up window qui affiche les détails du combat.
 * Le combat s'exécute dans le gestionnaire qui génère une liste de messages.
 * La fenêtre agit comme observer et affiche les messages générés.
 * 
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP3
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import creature.AbstractCreature;
import creature.Araigne;
import creature.Dragon;
import creature.Minotaure;
import equipements.Arme;
import equipements.Armure;
import equipements.Casque;
import equipements.Potion;
import joueur.Joueur;
import observer.MonObservable;
import personnage.AbstractPersonnage;
import vue.FenetreCombat;

public class GestionnaireCombat extends MonObservable implements Runnable{
	
	private final int TEMP_DELAI = 500;
	
	private int creatureTueOuPas= 0;


	private Thread combatFrameThread;
	private AbstractPersonnage hero, creature;
	private boolean combatEnCours = false;
	private ArrayList<String> messages = new ArrayList<String>();
	private PlanDeJeu plan;
	
	/**
	 * Méthode qui lance la fenetre pop-up, et la tâche qui exécute le combat
	 * @param hero, le hero du donjon
	 * @param creature, la creature avec qui il y a un combat
	 */
	public void executerCombat(Joueur hero, AbstractCreature creature){

		plan = PlanDeJeu.getInstance();
		
	
		
		// efface les messages
		messages.clear();
		
		// indique qu'un combat est en cours (suspend l'exécution du donjon)
		combatEnCours = true;
		
		// copie les références
		this.hero = hero;
		this.creature = creature;
		
		// lance le pop-up window
		// (à compléter)
		FenetreCombat fenetre = new FenetreCombat(hero, creature, plan.getGestionnaireCombat());
		plan.getGestionnaireCombat().attacherObserver(fenetre);
		
		// lance la tâche qui gère le combat
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * Tâche qui exécute le combat
	 * Boucle tant que les deux protagoniste sont vivant. Alterne les coups
	 * données entre le hero et la creature.
	 */
	public void run() {
		
		do{
			// hero donne le premier coup
			int forceCoupDonne = hero.getForce();
			creature.recoitCoup(forceCoupDonne);
			
			// ajoute les messages et mets à jours la fenetre
			messages.add("Joueur donne un coup: " + forceCoupDonne);
			messages.add("Point de vie creature: " + creature.getPointDeVie());
			this.avertirLesObservers();
					
			// attend un peu
			try {
				Thread.sleep(TEMP_DELAI);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// si la creature est toujours vivante
			if(creature.estVivant()){
				
				// creature donne un coup
				forceCoupDonne = creature.getForce();
				hero.recoitCoup(forceCoupDonne);
				
				// ajoute les messages
				messages.add("Creature donne un coup: " + forceCoupDonne);
				messages.add("Point de vie joueur: " + hero.getPointDeVie());
				this.avertirLesObservers();
			}else{
				
				// si la creature est morte, c'est la fin du combat
				messages.add("Creature vaincu");
				
				if (this.creature instanceof Araigne) {
					System.out.println("Vous avez tuer l'Araigne!!!");
				}
				
				if (this.creature instanceof Dragon) {
					
					System.out.println("Vous avez tuer le Dragon!!!");
				}

				if (this.creature instanceof Minotaure) {
					System.out.println("Vous avez tuer le Minotaure!!!");
				}
				creatureTueOuPas++;
				this.avertirLesObservers();
			}

			// attend un peu
			try {
				Thread.sleep(TEMP_DELAI);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// si le hero est mort
			if(!hero.estVivant()){
				
				// ajoute un message
				messages.add("Joueur vaincu");
				if (this.creature instanceof Araigne) {
					System.out.println("L'Araigne vous a tuer!!!");
				}
				
				if (this.creature instanceof Dragon) {
					
					System.out.println("Le Dragon vous a tuer!!!");
				}

				if (this.creature instanceof Minotaure) {
					System.out.println("Le Minotaure vous a tuer!!!");
				}
				setCreatureTueZero();
				this.avertirLesObservers();
			}	
			// boucle tant que le hero et la creature sont vivants
			}while(hero.estVivant() && creature.estVivant());

			combatEnCours = false;
		}


	/**
	 * informatrice pour savoir s'il y a un combat en cours.
	 * @return true/false, indiquant si un combat est en cours
	 */
	public boolean combatEstEnCours(){
		return combatEnCours;
	}
	
	/**
	 * mutatrice pour indiquer que le combat est termine
	 */
	public void combatTermine(){
		combatEnCours = false;
	}

	/**
	 * methode pour obtenir la liste des messages du combat
	 * @return chaine de caracteres contenant tous les messages avec des sauts de lignes
	 */
	public String getMsg(){

	    String str = "";
        for (int i = 0; i < messages.size(); ++i){
            str += messages.get(i) + "\n";
        }
        return str;
	}
	
	public int getCreatureTue() {
		return creatureTueOuPas;
	}

	public void setCreatureTueZero() {
		creatureTueOuPas = 0;
	}

}

