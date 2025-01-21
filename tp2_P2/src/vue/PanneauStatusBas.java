package vue;

import java.awt.BorderLayout;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import modele.PlanDeJeu;

public class PanneauStatusBas extends JPanel{
	
	protected JTextArea text;
	protected JScrollPane paneauDefilant;
	
	public PanneauStatusBas() {
		initialiserComposant();
		this.setVisible(true);
	}

	public void initialiserComposant(){
		
		this.setLayout(new BorderLayout());
		
		text = new JTextArea();
		text.setEditable(false);
		
		
        PrintStream printStream = new PrintStream(new OutputCostum(text));
        
        PrintStream standardOut = System.out;
        System.setOut(printStream);
 
    	System.out.println("On commence!!!!");
		paneauDefilant = new JScrollPane(text);
		paneauDefilant.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(paneauDefilant, BorderLayout.CENTER);
		
	}
	
}