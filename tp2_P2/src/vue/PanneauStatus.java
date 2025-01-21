package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observer;

import javax.swing.JPanel;

import modele.PlanDeJeu;
import observer.MonObserver;

public class PanneauStatus extends JPanel implements MonObserver{
	
	protected PanneauStatusHaut statusHaut;
	protected PanneauStatusMilieu statusMilieu;
	protected PanneauStatusBas statusBas;
	private PlanDeJeu plan = PlanDeJeu.getInstance();
	
	public PanneauStatus(Dimension tailleEcran) {
		
		initaliserFenetre(tailleEcran);
		initaliserComposant();

	}
	
	public void initaliserFenetre(Dimension tailleEcran) {
		setSize(tailleEcran.width/3, tailleEcran.height);
		setLayout(new GridLayout(3, 1));
		setVisible(true);
	}

	public void initaliserComposant() {
		
		plan.attacherObserver(this);
		
		statusHaut = new PanneauStatusHaut();
		statusMilieu = new PanneauStatusMilieu(plan);
		statusBas =new PanneauStatusBas();
		
		this.add(statusHaut);
		this.add(statusMilieu);
		this.add(statusBas);

	}
	
	@Override
	public void avertir() {
		// TODO Auto-generated method stub
		statusHaut.mettreAJoursInfo(plan);
		statusMilieu.mettreAJoursInfo(plan);		
	}

}
