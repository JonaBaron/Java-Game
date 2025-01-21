package joueur;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Définition du Joueur.
 * 
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */

import java.util.Vector;

import javax.management.loading.PrivateClassLoader;
import javax.sound.midi.VoiceStatus;

/**
 * Cette classe représente le joueur humain. Elle surcharge le
 * personnage abstrait pour le déplacement et propose une méthode
 * pour mettre à jours la visibilité des cases en fonction de la vision.
 * 
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */


import dongon.Case;
import equipements.AbstractEquipement;
import equipements.Arme;
import equipements.Armure;
import equipements.Casque;
import equipements.Potion;
import personnage.AbstractPersonnage;
import physique.Direction;
import physique.Position;

public class Joueur extends AbstractPersonnage {
	
	private final int PROFONDEUR_VISION = 2;
	private boolean mouvement = true;
	private ArrayList<AbstractEquipement> equipementRamasser = new ArrayList<>();
	private Casque casqueEquiper =null;
	private Armure armureEquiper =null;
	private Arme armeEquiper=null; 
	/**
	 * Construceur par paramètre
	 * @param pos, position du joueur
	 */
	public Joueur() {
		pointDeVie=100;
		pointDeVieMax=100;
	}

	/**
	 * surcharge de la méthode pour déplacer le joueur dans la direction donnée
	 * @param direction(int), direction du mouvement
	 */
	public void seDeplacer(int direction){
		
		if(mouvement) {
			// se déplacer
			super.seDeplacer(direction);
			
			// mise à jour de la vision
			mettreAJourVision();
		}
	}
	

	/**
	 * surcharge de la méthode pour placer le joueur à sa case de départ
	 * @param caseCourante(Case), case courante
	 */
	public void setCase(Case caseCourante){
		
		// assigne la case
		super.setCase(caseCourante);

		// mise à jour de la vision
		mettreAJourVision();
	}
	
	/**
	 * méthode qui mets à jour la vision
	 */
	private void mettreAJourVision(){
		
		// rend visible la case courante
		super.caseCourante.setDecouverte(true);
		
		// dans toutes les directions
		for(int i=0;i<Direction.NB_DIRECTIONS;i++){
			
			// dévoile les voisins jusqu'à la profondeur de la vision
			Case voisin = super.caseCourante.getVoisin(i);
			for(int j=0;j<PROFONDEUR_VISION;j++){
				if(voisin!=null){
					voisin.setDecouverte(true);
					voisin = voisin.getVoisin(i);
				}
			}
		}
	}

	public void setMouvement(boolean etat){
		this.mouvement = etat;
	}
	

	/**
	 * Remise à zéro du joueur
	 * - remet les points de vie à max
	 * - vide équipement
	 */
	public void remiseAZero(){
		this.pointDeVie = this.pointDeVieMax;
		this.equipementRamasser.clear();
	}

	public void ajoutEquipement (AbstractEquipement equipement) {
		
		equipementRamasser.add(equipement);
		equipement.setAuSol(false);
		equipement.setPos(null);
	}

	public ArrayList<AbstractEquipement> getEquipements() {
		return this.equipementRamasser;
	}

	public Casque getCasqueEquipe() {
		if (casqueEquiper != null)
			return casqueEquiper;
		return null;
	}
	
	public Armure getArmureEquipe() {
		if (armureEquiper != null)
			return armureEquiper;
		return null;
	}
	
	public Arme getArmeEquipe() {
		if (armeEquiper != null)
			return armeEquiper;
		return null;
	}
	
	
	public void equiper(AbstractEquipement equipement) {
		
		if(equipement instanceof Casque ) {
			this.casqueEquiper = (Casque) equipement;
		}

		if( equipement instanceof Arme ) {

			this.armeEquiper = (Arme) equipement;
		}

		if( equipement instanceof Armure) {
			this.armureEquiper = (Armure) equipement;
		}

		this.armure =0;
		
		this.bonusAttaque =0;
		
		 if (casqueEquiper != null && armureEquiper != null) {
	            armure = casqueEquiper.getValeur()+armureEquiper.getValeur();
	        }

	   
	        else if (armureEquiper != null) {
	            armure = armureEquiper.getValeur();
	        }
	        
	    
	        else if (casqueEquiper != null ) {
	            armure = casqueEquiper.getValeur();
	        }
	        
	    
	        if (armeEquiper != null) {
	            bonusAttaque = armeEquiper.getValeur();
	        }
	}
	
	public void utiliserPotion() {
		ListIterator<AbstractEquipement> list = equipementRamasser.listIterator();
		
		while(list.hasNext()) {
			AbstractEquipement equipement = list.next();
			if(equipement instanceof Potion) {
				super.pointDeVie = equipement.getValeur();
				equipementRamasser.remove(equipement);
			}
		}
	}
	
	
	public void remiserAZero() {
		
		this.armeEquiper = null;
		this.armureEquiper = null;
		this.casqueEquiper = null;
		
		equiper(null);
		
	}
	
	
}
