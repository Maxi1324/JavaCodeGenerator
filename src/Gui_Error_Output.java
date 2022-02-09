import javax.swing.*;
import java.awt.*;

public class Gui_Error_Output {

	private Gui_main gui_main;
	private JTextArea error_textfield = new JTextArea();
	private JScrollPane error_scollpane = new JScrollPane();
	public boolean error;
	
	
	public Gui_Error_Output(Gui_main gui_main) {
	
		this.gui_main = gui_main;
		gui_erstellen();
		
	}
	
	private void gui_erstellen()
	{
		error_textfield.setFont(gui_main.setFontmulti(error_textfield.getFont(), 2));
		Gui_main.text_area_und_scrollpane_einstellen1(error_textfield, error_scollpane, gui_main ,1.5f);
		//error_textfield.setText("Hier werden Fehlerausgaben getätigt ");
		error_textfield.setForeground(Color.RED);
	
		
	}
	
	public void Fehlermeldung(String fehler)
	{
	//	error = true;
		gui_main.getGui_Output().hinzufugen(error_textfield,fehler+"\n");
		gui_main.getGui_Output().setFehler(true);
	}
	

	public Gui_main getGui_main() {
		return gui_main;
	}

	public void setGui_main(Gui_main gui_main) {
		this.gui_main = gui_main;
	}

	public JTextArea getError_textfield() {
		return error_textfield;
	}

	public void setError_textfield(JTextArea error_textfield) {
		this.error_textfield = error_textfield;
	}

	public JScrollPane getError_scollpane() {
		return error_scollpane;
	}

	public void setError_scollpane(JScrollPane error_scollpane) {
		this.error_scollpane = error_scollpane;
	}

	
}
