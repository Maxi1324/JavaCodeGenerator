 import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
 
 
public class gui_dokumentation extends JFrame {

	JTextArea text = new JTextArea();
	JScrollPane scollpane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
	
	public gui_dokumentation(Gui_main main) {
	
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		add(text);
		setSize(new Dimension(500,800));
		setMinimumSize(new Dimension(300,500));
		text.setEditable(false);
		text.setRows((int) ((main.getGroesse().y / 9) * 4) / 19);
		text.setColumns((main.getGroesse().x - 20) / 11);
		scollpane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setLocationRelativeTo(main.getMain_fenster());
		add(scollpane);
		
		text.setText("Skeleton2	\r\n" + 
				"\r\n" + 
				"Inhaltsverzeichnis\r\n" + 
				"\r\n" + 
				"Inhalt Skeleton2	\r\n" + 
				"Generator Möglichkeiten	1\r\n" + 
				"Syntax		2\r\n" + 
				"Einstellungen:		3\r\n" + 
				"Fehler finden		4\r\n" + 
				"Speichern und laden	5\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				" \r\n" + 
				"Generator Möglichkeiten\r\n" + 
				"\r\n" + 
				"1.	Attribute\r\n" + 
				"2.	Methoden (mit Parametern)\r\n" + 
				"3.	Konstruktoren (mit Parameter)\r\n" + 
				"4.	Getter\r\n" + 
				"5.	Setter\r\n" + 
				"6.	Print Methode\r\n" + 
				"7.	toString Methode\r\n" + 
				"\r\n" + 
				" \r\n" + 
				"Syntax\r\n" + 
				"-----------------------------------------------------------------------------------------------\r\n" + 
				"\r\n" + 
				"Der Syntax von Skeleton ist sehr ähnlich von dem vom URL Diagramm (Klassen-diagramm).\r\n" + 
				"\r\n" + 
				"Syntax Attribute\r\n" + 
				"\r\n" + 
				"name : typ\r\n" + 
				"\r\n" + 
				"Syntax Method\r\n" + 
				"\r\n" + 
				"aufstehen():void\r\n" + 
				"\r\n" + 
				"Syntax Constructor\r\n" + 
				"\r\n" + 
				"Name der Klasse zb:\r\n" + 
				"\r\n" + 
				"Hund()\r\n" + 
				"\r\n" + 
				"Syntax Parameter (für Methode und Konstruktor)\r\n" + 
				"\r\n" + 
				"name : typ\r\n" + 
				"\r\n" + 
				"Ein Parameter kann bei einer Methode oder Konstruktor genutzt werden.\r\n" + 
				"Für den Fall, dass man mehrere Parameter in einer Methode/Konstruktor nutzen will kann man dies auch tun, allerdings muss man zwischen den Parametern einen Beistrich setzten.\r\n" + 
				"Syntax Klassen Name\r\n" + 
				"\r\n" + 
				"\r\n" + 
				" \r\n" + 
				"Einstellungen: \r\n" + 
				"\r\n" + 
				"Im Einstellungsfenster kann man Einstellungen einstellen.\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"Momentan kann man im Einstellungsfenster nur das Überprüfen von Variablen/Methoden Typen ausstelle.\r\n" + 
				"\r\n" + 
				"Fehler finden\r\n" + 
				"\r\n" + 
				"Skeleton 2 hat wahrscheinlich einige Fehler, wenn die einen solchen Fehler finden senden sie, wenn sie wollen eine mail an maxiprogramming@gmail.com\r\n" + 
				"\r\n" + 
				"Ein Bug, der leider sehr häufig auftritt ist es das nach dem eine Fehlermeldung dass das Programm keinen code generiert um ihn zu lösen sollte man das Programm neustarten (rotes X/Tools Restart)\r\n" + 
				"\r\n" + 
				"Speichern und laden\r\n" + 
				"\r\n" + 
				"Speichern:\r\n" + 
				"Tool > save File\r\n" + 
				"erstellt ein Dokument, in dem das Erstellte Klassendiagramm steht\r\n" + 
				"\r\n" + 
				"Laden > Load File\r\n" + 
				"liest ein Dokument aus und erzeugt den Java code zum Klassendagramm\r\n" + 
				"");
				
	
			addWindowListener(new WindowListener() {
				
				@Override
				public void windowOpened(WindowEvent e) {
					
					
				}
				
				@Override
				public void windowIconified(WindowEvent e) {
					
					
				}
				
				@Override
				public void windowDeiconified(WindowEvent e) {
					
					
				}
				
				@Override
				public void windowDeactivated(WindowEvent e) {
					
					
				}
				
				@Override
				public void windowClosing(WindowEvent e) {
					
					setVisible(false);
					
				}
				
				@Override
				public void windowClosed(WindowEvent e) {
					
					
				}
				
				@Override
				public void windowActivated(WindowEvent e) {
					
					
				}
			});
	
	
	}		
			
	
	
}
