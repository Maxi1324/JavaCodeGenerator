import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Collectors;

public class Gui_main {

	private JFrame main_fenster = new JFrame();
	private Gui_Input gui_Input;
	private Gui_Error_Output gui_Error_Output;
	private GUI_Output gui_Output;
	private Vector2 groesse = new Vector2(450, 486);
	private JMenuBar main_bar = new JMenuBar();
	private JMenu[] menu = new JMenu[8];
	private JMenuItem[] items = new JMenuItem[99];
	private gui_Options option_fenster = new gui_Options(this);
	private JPanel hintergrund = new JPanel();
	private gui_dokumentation doku;

	public Gui_main() {

		gui_Input = new Gui_Input(this);
		gui_Error_Output = new Gui_Error_Output(this);
		gui_Output = new GUI_Output(this);
		Main_Fenster_erstellen();

		doku = new gui_dokumentation(this);

	}

	private void Main_Fenster_erstellen() {
		Fenster(main_fenster, 770, 825, "CodeGenerator by Maximilian Fischer");
		main_fenster.add(hintergrund);
		hintergrund.setLayout(new FlowLayout());
		hintergrund.setBackground(Color.BLACK);
		System.out.println(hintergrund.getBackground());
		menu[0] = new JMenu("Options");
		menu[1] = new JMenu("Tools");
		menu[2] = new JMenu("HELP");

		items[0] = new JMenuItem("restart");
		items[1] = new JMenuItem("get source code");
		items[2] = new JMenuItem("dokumentation( web )");
		items[4] = new JMenuItem("dokumentation( text file )");
		items[5] = new JMenuItem("dokumentation( in programm )");
		items[3] = new JMenuItem("Options");
		items[6] = new JMenuItem("open file");
		items[7] = new JMenuItem("save file");
		items[8] = new JMenuItem("Video tutorial");

		menu[1].add(items[0]);
		menu[1].add(items[6]);
		menu[1].add(items[7]);
		menu[0].add(items[3]);
		menu[2].add(items[4]);
		menu[2].add(items[5]);
		menu[2].add(items[8]);

		main_bar.add(menu[0]);
		main_bar.add(menu[1]);
		main_bar.add(menu[2]);

		items[7].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				save_file();

			}
		});

		items[6].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				open_file();

			}
		});

		items[0].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				option_fenster.setVisible(false);
				main_fenster.setVisible(false);
				new Gui_main();

			}
		});

		items[3].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				option_fenster.setVisible(true);
				option_fenster.setLocationRelativeTo(main_fenster);

			}
		});

		items[8].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=7-IEmZvw8BI").toURI());
				} catch (MalformedURLException e1) {
					;
				} catch (IOException e1) {

				} catch (URISyntaxException e1) {

				}

			}
		});

		items[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(main_fenster);
				try {
					OutputStream output = new FileOutputStream(chooser.getSelectedFile().getAbsoluteFile());

					try {
						output.write(
								"Skeleton2	\n\nInhaltsverzeichnis\n\nInhalt Skeleton2	\nGenerator Möglichkeiten	1\nSyntax			2\nEinstellungen:		3\nFehler finden		4\nSpeichern und laden	5s\n\n\n\n\n \nGenerator Möglichkeiten\n\n1.	Attribute\n2.	Methoden (mit Parametern)\n3.	Konstruktoren (mit Parameter)\n4.	Getter\n5.	Setter\n6.	Print Methode\n7.	toString Methode\n\n \nSyntax\n-----------------------------------------------------------------------------------------------\n\nDer Syntax von Skeleton ist sehr ähnlich von dem vom URL Diagramm (Klassen-diagramm).\n\nSyntax Attribute\n\nname : typ\n\nSyntax Method\n\naufstehen():void\n\nSyntax Constructor\n\nName der Klasse zb:\n\nHund()\n\nSyntax Parameter (für Methode und Konstruktor)\n\nname : typ\n\nEin Parameter kann bei einer Methode oder Konstruktor genutzt werden.\nFür den Fall, dass man mehrere Parameter in einer Methode/Konstruktor nutzen will kann man dies auch tun, allerdings muss man zwischen den Parametern einen Beistrich setzten.\nSyntax Klassen Name\n\n\n \nEinstellungen: \n\nIm Einstellungsfenster kann man Einstellungen einstellen.\n\n\nMomentan kann man im Einstellungsfenster nur das Überprüfen von Variablen/Methoden Typen ausstelle.\n\nFehler finden\n\nSkeleton 2 hat wahrscheinlich einige Fehler, wenn die einen solchen Fehler finden senden sie, wenn sie wollen eine mail an maxiprogramming@gmail.com\n\nEin Bug, der leider sehr häufig auftritt ist es das nach dem eine Fehlermeldung dass das Programm keinen code generiert um ihn zu lösen sollte man das Programm neustarten (rotes X/Tools Restart)\n\nSpeichern und laden\n\nSpeichern:\nTool > save File\nerstellt ein Dokument, in dem das Erstellte Klassendiagramm steht\n\nLaden > Load File\nliest ein Dokument aus und erzeugt den Java code zum Klassendagramm\n"
										.getBytes());
					} catch (IOException e2) {

					}

					try {
						output.close();
					} catch (IOException e1) {

					}
				} catch (FileNotFoundException e1) {

				}

			}
		});

		main_fenster.setJMenuBar(main_bar);

		items[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doku.setVisible(true);
				doku.setLocationRelativeTo(main_fenster);

			}
		});
	}

	public void save_file() {

		gui_Input.input_lesen();
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("speicher ort wählen");
		chooser.showOpenDialog(main_fenster);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogType(JFileChooser.DIRECTORIES_ONLY);
		try {

			System.out.println(chooser.getSelectedFiles());
			OutputStream output = new FileOutputStream(chooser.getSelectedFile().getPath());
			output.write(gui_Input.getInput_texflield().getText().getBytes());

			output.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

		}

	}

	public void open_file() {

		JFileChooser chooser = new JFileChooser("open file");
		chooser.showOpenDialog(main_fenster);

		try {

			BufferedReader stream = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(chooser.getSelectedFile().getPath()))));

			gui_Input.getInput_texflield().setText("");
			boolean a = false;
			for (int i = 0; i < 30 && a == false;) {
				i++;
				String str = stream.readLine();
				if (!(str.equals("null"))) {
					gui_Input.getInput_texflield().setText(gui_Input.getInput_texflield().getText() + str + "\n");
				} else {
					a = true;
				}

			}
			stream.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	static public void Fenster(JFrame Fenster, int size_x, int size_y, String Titel) {
		Fenster.setVisible(true);
		Fenster.setSize(new Dimension(size_x, size_y));
		Fenster.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		Fenster.setTitle(Titel);
		Fenster.setResizable(false);
		Fenster.setLocationRelativeTo(null);

	}

	public static void text_area_und_scrollpane_einstellen(JTextArea textarea, JScrollPane scollpane,
			Gui_main gui_main) {
		textarea.setRows((int) ((gui_main.getGroesse().y / 9) * 4) / 19);
		textarea.setColumns((gui_main.getGroesse().x - 20) / 11);
		scollpane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gui_main.getHintergrund().add(scollpane);
	}

	public void text_area_und_scrollpane_einstellen2(JTextArea textarea, JScrollPane scollpane, Gui_main gui_main) {
		textarea.setRows((int) ((gui_main.getGroesse().y / 9) * 4) / 19);
		textarea.setColumns((gui_main.getGroesse().x - 20) / 11);
		scollpane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		doku.add(scollpane);
	}

	public static void text_area_und_scrollpane_einstellen(JTextArea textarea, JScrollPane scollpane, Gui_main gui_main,
			float y) {
		textarea = new JTextArea((int) ((gui_main.getGroesse().y / 9) * y) / 19, (gui_main.getGroesse().x - 20) / 11);
		scollpane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	public static void text_area_und_scrollpane_einstellen1(JTextArea textarea, JScrollPane scollpane,
			Gui_main gui_main, float y) {
		textarea.setRows((int) ((gui_main.getGroesse().y / 9) * y) / 19);
		textarea.setColumns((gui_main.getGroesse().x - 20) / 11);
		scollpane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		gui_main.getHintergrund().add(scollpane);
		textarea.setEditable(false);
	}

	public static Font fontgroesse(int x) {

		return new Font("Arial", new JButton().getFont().getStyle(), x);

	}

	public JFrame getMain_fenster() {
		return main_fenster;
	}

	public void setMain_fenster(JFrame main_fenster) {
		this.main_fenster = main_fenster;
	}

	public Gui_Input getGui_Input() {
		return gui_Input;
	}

	public void setGui_Input(Gui_Input gui_Input) {
		this.gui_Input = gui_Input;
	}

	public Gui_Error_Output getGui_Error_Output() {
		return gui_Error_Output;
	}

	public void setGui_Error_Output(Gui_Error_Output gui_Error_Output) {
		this.gui_Error_Output = gui_Error_Output;
	}

	public GUI_Output getGui_Output() {
		return gui_Output;
	}

	public void setGui_Output(GUI_Output gui_Output) {
		this.gui_Output = gui_Output;
	}

	public JMenuBar getMain_bar() {
		return main_bar;
	}

	public void setMain_bar(JMenuBar main_bar) {
		this.main_bar = main_bar;
	}

	public Vector2 getGroesse() {
		return groesse;
	}

	public void setGroesse(Vector2 groesse) {
		this.groesse = groesse;
	}

	public static Font setFontmulti(Font font, float factor) {
		return new Font(font.getName(), font.getStyle(), 20);
	}

	public JMenu[] getMenu() {
		return menu;
	}

	public void setMenu(JMenu[] menu) {
		this.menu = menu;
	}

	public JMenuItem[] getItems() {
		return items;
	}

	public void setItems(JMenuItem[] items) {
		this.items = items;
	}

	public gui_Options getOption_fenster() {
		return option_fenster;
	}

	public void setOption_fenster(gui_Options option_fenster) {
		this.option_fenster = option_fenster;
	}

	public JPanel getHintergrund() {
		return hintergrund;
	}

	public void setHintergrund(JPanel hintergrund) {
		this.hintergrund = hintergrund;
	}

	public gui_dokumentation getDoku() {
		return doku;
	}

	public void setDoku(gui_dokumentation doku) {
		this.doku = doku;
	}

}
