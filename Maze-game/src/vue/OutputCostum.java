package vue;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class OutputCostum extends OutputStream {
	private JTextArea textArea;

    public OutputCostum(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
    	
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.update(textArea.getGraphics());
        
    }
}
