import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.AbstractDocument.AttributeContext;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.concurrent.Delayed;

public class Gui_Input {
	private Gui_main gui_main;
	private JTextArea input_texflield = new JTextArea();
	private JScrollPane input_scrollpane = new JScrollPane(input_texflield, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private int attribut_count;
	private Atribut[] attribute = new Atribut[9999];
	private Methode[] Methoden1 = new Methode[9999];
	private int methoden_count = 0;
	private String class_name1 = "";
	public boolean toString_da = false;
	private Konstruktor[] konstruktoren = new Konstruktor[9999];
	private int Konstruktor_count = 0;
	private String class_name;
	private int kon_count = 0;

	public Gui_Input(Gui_main gui_main1) {

		gui_main = gui_main1;

		input_Gui_erstellen();

	}

	private void input_Gui_erstellen() {

		Gui_main.text_area_und_scrollpane_einstellen(input_texflield, input_scrollpane, gui_main);
		input_lesen();
		input_texflield.setSelectedTextColor(Color.BLUE);
		input_texflield.setFont(gui_main.setFontmulti(input_texflield.getFont(), 2));
		
		input_texflield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				input_lesen();

			}

			@Override
			public void focusGained(FocusEvent arg0) {

				gui_main.getGui_Error_Output().getError_textfield().setText("");
			}

		});

	}

	public void input_lesen() {
		class_name = "Class_Name";
		toString_da = false;
		if (gui_main.getGui_Error_Output() != null) {
			gui_main.getGui_Error_Output().error = false;
			gui_main.getGui_Output().setFehler(false);

		}
		methoden_count = 0;
		attribut_count = 0;
		Konstruktor_count = 0;
		Methoden1 = new Methode[9999];
		attribute = new Atribut[9999];
		String diagramm = input_texflield.getText();
		String[] anweisungen = input_texflield.getText().split("\n");
		anweisungen = del_leerzeilen(anweisungen);
		int anweisungs_count = anweisungen.length;
		if (anweisungs_count >= 1) {
			for (int x = 0; x < anweisungs_count;) {
				int pospunkt1 = 0;
				int eins = anweisungen[x].indexOf(")");
				if (eins != -1) {
					pospunkt1 = anweisungen[x].indexOf(":", eins);
				} else {
					pospunkt1 = anweisungen[x].indexOf(":");
				}

				if (pospunkt1 != -1) {
					Attribut(anweisungen[x], x);

				}
				if (pospunkt1 == -1) {
					class_name(anweisungen[x]);

				}
				x++;
			}

			gui_main.getGui_Output().generieren(attribute, attribut_count, Methoden1, methoden_count, konstruktoren,
					Konstruktor_count, class_name);
		}

	}

	public void Attribut(String prufen, int x) {
		int pos = prufen.indexOf(":");

		if (prufen.indexOf(":") != -1) {
			if (Methode(prufen) == false) {

				String substring1 = prufen.substring(pos + 1);
				String substring2 = prufen.substring(0, pos);
				/*
				 * if(substring2.length() == 0 ) {
				 * gui_main.getGui_Error_Output().Fehlermeldung("Fehler : Name zu kurz "+"("+
				 * substring2 +","+substring1+") #0001"); }
				 * 
				 * if (del_leer_zeich(substring1).equals("int")) { attribute[x] = new
				 * Atribut(substring2, substring1,gui_main); attribut_count++; return; }
				 * 
				 * if (del_leer_zeich(substring1).equals("long")) {
				 * 
				 * attribut_count++; return; }
				 * 
				 * if (del_leer_zeich(substring1).equals("String")) { attribute[x] = new
				 * Atribut(substring2, substring1,gui_main); attribut_count++; return; } if
				 * (del_leer_zeich(substring1).equals("float")) { attribute[x] = new
				 * Atribut(substring2, substring1,gui_main); attribut_count++; return; } if
				 * (del_leer_zeich(substring1).equals("double")) { attribute[x] = new
				 * Atribut(substring2, substring1,gui_main); attribut_count++; return; } if
				 * (del_leer_zeich(substring1).equals("short")) { attribute[x] = new
				 * Atribut(substring2, substring1,gui_main); attribut_count++; return; } if
				 * (del_leer_zeich(substring1).equals("byte")) { attribute[x] = new
				 * Atribut(substring2, substring1,gui_main); attribut_count++; return; } if
				 * (del_leer_zeich(substring1).equals("boolean")) { attribute[x] = new
				 * Atribut(substring2, substring1,gui_main); attribut_count++; return; } if
				 * (del_leer_zeich(substring1).equals("char")) { attribute[x] = new
				 * Atribut(substring2, substring1,gui_main); attribut_count++; return; }
				 */
				attribute[x] = new Atribut(substring2, substring1, gui_main);
				attribut_count++;

			}

		}
		;

	}

	public boolean Methode(String prufen) {

		int klamma2 = prufen.indexOf(")");
		int klamma1 = prufen.indexOf("(");

		if (klamma1 != -1 && klamma2 != -1) {

			Atribut[] Parameter = new Atribut[9999];
			int Parameter_count = 0;
			int pos1 = prufen.indexOf(":", klamma2);
			String substring = del_leer_zeich(prufen.substring(pos1));
			String typ = del_leer_zeich(prufen.substring(prufen.indexOf(":", klamma2) + 1));
			String name = del_leer_zeich(prufen.substring(0, klamma1));
			String sub2 = prufen.substring(klamma1 + 1, klamma2);
			int strich_count = char_count(sub2, ",");
			int punkt_count = char_count(sub2, ":");
			int vorlast;
			int last = 0;
			int[] positionenstrich = char_count1(sub2, ",", punkt_count);

			for (int x = 0; x <= punkt_count;) {
				if (punkt_count == 1) {
					String sub3 = del_leer_zeich(sub2.substring(0, sub2.indexOf(":")));
					String sub4 = del_leer_zeich(sub2.substring(sub2.indexOf(":") + 1));

					Parameter[x] = new Atribut(sub3, sub4, gui_main);
					Parameter_count++;
					x++;
				}

				if (punkt_count > 1) {
					if (x == 0) {
						int pos3 = sub2.indexOf(",", last);
						vorlast = last;
						last = pos3;

						String teil = del_leer_zeich(sub2.substring(0, last));
						String sub3 = del_leer_zeich(teil.substring(0, teil.indexOf(":")));
						String sub4 = del_leer_zeich(teil.substring(teil.indexOf(":") + 1));
						Parameter[x] = new Atribut(sub3, sub4, gui_main);
						Parameter_count++;
						if (punkt_count == 3) {

						}

					}

					if (x > 0 && x < punkt_count - 1) {

						int pos3 = sub2.indexOf(",", last + 1);
						vorlast = last;
						last = pos3;

						String teil = del_leer_zeich(sub2.substring(vorlast + 1, last));

						String sub3 = del_leer_zeich(teil.substring(0, teil.indexOf(":")));
						String sub4 = del_leer_zeich(teil.substring(teil.indexOf(":") + 1));
						Parameter[x] = new Atribut(sub3, sub4, gui_main);
						Parameter_count++;
					}

					if (x == punkt_count - 1 && x != 0) {
						int pos3 = sub2.indexOf(",", last);
						vorlast = last;
						last = pos3;

						String teil = del_leer_zeich(sub2.substring(vorlast + 1));
						String sub3 = del_leer_zeich(teil.substring(0, teil.indexOf(":")));
						String sub4 = del_leer_zeich(teil.substring(teil.indexOf(":") + 1));
						Parameter[x] = new Atribut(sub3, sub4, gui_main);
						Parameter_count++;
					}

				}
				x++;
			}

			for (int x = 0; x < methoden_count;) {
				x++;
			}

			if (gui_main.getOption_fenster().isTyp_prufen()) {
				if (typ.equals("void")) {

					Methoden1[methoden_count] = new Methode(name, "void", Parameter_count, Parameter, this);
					methoden_count++;
					return true;

				}
				if (typ.equals("String")) {

					Methoden1[methoden_count] = new Methode(name, "String", Parameter_count, Parameter, this);
					methoden_count++;
					return true;

				}

				if (typ.equals("int")) {

					Methoden1[methoden_count] = new Methode(name, "int", Parameter_count, Parameter, this);
					methoden_count++;
					return true;

				}
				if (typ.equals("float")) {

					Methoden1[methoden_count] = new Methode(name, "foat", Parameter_count, Parameter, this);
					methoden_count++;
					return true;

				}
				if (typ.equals("double")) {

					Methoden1[methoden_count] = new Methode(name, "double", Parameter_count, Parameter, this);
					methoden_count++;
					return true;

				}
				if (typ.equals("long")) {

					Methoden1[methoden_count] = new Methode(name, "long", Parameter_count, Parameter, this);
					methoden_count++;
					return true;

				}
				if (typ.equals("short")) {

					Methoden1[methoden_count] = new Methode(name, "void", Parameter_count, Parameter, this);
					methoden_count++;
					return true;

				}

				if (typ.equals("short")) {

					Methoden1[methoden_count] = new Methode(name, typ, Parameter_count, Parameter, this);
					methoden_count++;
					return true;

				}

				if (typ.equals("boolean")) {

					Methoden1[methoden_count] = new Methode(name, typ, Parameter_count, Parameter, this);
					methoden_count++;
					return true;

				}

				gui_main.getGui_Error_Output()
						.Fehlermeldung("Fehler : Methodentyp ungültig " + "(" + name + "," + typ + ") #0003");
			}

			else {
				Methoden1[methoden_count] = new Methode(name, typ, Parameter_count, Parameter, this);
				methoden_count++;
				return true;
			}
		}
		if (klamma1 == -1 && klamma2 == -1)
			return false;
		if (klamma1 != -1 && klamma2 != -1)
			return true;
		return false;

	}

	private boolean Konstruktor(String prufen) {

		int klamma2 = prufen.indexOf(")");
		int klamma1 = prufen.indexOf("(");

		if (klamma1 != -1 && klamma2 != -1) {

			Atribut[] Parameter = new Atribut[9999];
			int Parameter_count = 0;
			int pos1 = prufen.indexOf(":", klamma2);
			String substring;
			if (pos1 != -1)
				substring = del_leer_zeich(prufen.substring(pos1));
			String typ = del_leer_zeich(prufen.substring(prufen.indexOf(":", klamma2) + 1));
			String name = del_leer_zeich(prufen.substring(0, klamma1));
			String sub2 = prufen.substring(klamma1 + 1, klamma2);
			int strich_count = char_count(sub2, ",");
			int punkt_count = char_count(sub2, ":");
			int vorlast;
			int last = 0;
			int[] positionenstrich = char_count1(sub2, ",", punkt_count);

			for (int x = 0; x <= punkt_count;) {
				if (punkt_count == 1) {
					String sub3 = del_leer_zeich(sub2.substring(0, sub2.indexOf(":")));
					String sub4 = del_leer_zeich(sub2.substring(sub2.indexOf(":") + 1));

					Parameter[x] = new Atribut(sub3, sub4, gui_main);
					Parameter_count++;
					x++;
				}

				if (punkt_count > 1) {
					if (x == 0) {

						int pos3 = sub2.indexOf(",", last);
						vorlast = last;
						last = pos3;

						String teil = del_leer_zeich(sub2.substring(0, last));
						String sub3 = del_leer_zeich(teil.substring(0, teil.indexOf(":")));
						String sub4 = del_leer_zeich(teil.substring(teil.indexOf(":") + 1));
						Parameter[x] = new Atribut(sub3, sub4, this.gui_main);
						Parameter_count++;
						if (punkt_count == 3) {

						}

					}

					if (x > 0 && x < punkt_count - 1) {
						int pos3 = sub2.indexOf(",", last + 1);
						vorlast = last;
						last = pos3;

						String teil = del_leer_zeich(sub2.substring(vorlast + 1, last));

						String sub3 = del_leer_zeich(teil.substring(0, teil.indexOf(":")));
						String sub4 = del_leer_zeich(teil.substring(teil.indexOf(":") + 1));
						Parameter[x] = new Atribut(sub3, sub4, this.gui_main);
						Parameter_count++;
					}

					if (x == punkt_count - 1 && x != 0) {
						int pos3 = sub2.indexOf(",", last);
						vorlast = last;
						last = pos3;

						String teil = del_leer_zeich(sub2.substring(vorlast + 1));
						String sub3 = del_leer_zeich(teil.substring(0, teil.indexOf(":")));
						String sub4 = del_leer_zeich(teil.substring(teil.indexOf(":") + 1));

						Parameter[x] = new Atribut(sub3, sub4, this.gui_main);
						Parameter_count++;
					}

				}
				x++;

			}

			konstruktoren[Konstruktor_count] = new Konstruktor(class_name, Parameter, Parameter_count, this);
			Konstruktor_count++;

			return true;

		}
		return false;

	}

	public void class_name(String prufen) {

		if (prufen.indexOf("(") != -1 && prufen.indexOf(")") != -1) {
			String name = del_leer_zeich(prufen.substring(0, prufen.indexOf("(")));
			if (name.equals(class_name)) {
				Konstruktor(prufen);
			} else {
				Methode(prufen + ":void");
			}
		}

		else {
			class_name = del_leer_zeich(prufen);
		}

	}

	public String[] anweisungen_lesen(String zu_lesen, int anweisungs_count, int[] simi_pos) {
		String[] substrings = new String[anweisungs_count];
		for (int x = 0; x < anweisungs_count;) {
			if (x == 0) {
				substrings[x] = zu_lesen.substring(0, simi_pos[x]);
			} else {
				substrings[x] = zu_lesen.substring(simi_pos[x - 1] + 1, simi_pos[x]);
			}
			substrings[x] = zeichen_entfernen(substrings[x], " ");
			x++;
		}

		return substrings;
	}

	static public String del_leer_zeich(String String1) {
		return String1.replace(" ", "").replace("\n", "");
	}

	static public String zeichen_entfernen(String String_zu_filtern, String Stringzulöschen) {
		String neuer_String = String_zu_filtern;
		neuer_String.replace(Stringzulöschen, "");
		return neuer_String;
	}

	static public int char_count(String suchenin, String suchennach) {
		int last_index = 0;
		int wieviele = 0;
		boolean eins = false;
		for (boolean was_da = false; was_da == false;) {
			if (((eins == false) ? suchenin.indexOf(suchennach, last_index + 1)
					: suchenin.indexOf(suchennach, last_index + 1)) != -1) {
				eins = true;
				last_index = suchenin.indexOf(suchennach, last_index + 1);
				wieviele++;
				was_da = false;
			} else {
				was_da = true;
			}

		}
		return wieviele;
	}

	static public int[] char_count1(String suchenin, String suchennach, int wieviele1) {
		int[] pos_speicher = new int[wieviele1];
		int x = 0;
		int last_index = 0;
		int wieviele = 0;
		boolean eins = false;
		for (boolean was_da = false; was_da == false;) {
			if (((eins == false) ? suchenin.indexOf(suchennach, last_index + 1)
					: suchenin.indexOf(suchennach, last_index + 1)) != -1) {
				eins = true;
				last_index = suchenin.indexOf(suchennach, last_index + 1);
				wieviele++;
				was_da = false;
				pos_speicher[x] = last_index;
				x++;
			} else {
				was_da = true;
			}

		}
		return pos_speicher;
	}

	public String[] del_leerzeilen(String[] string) {
		String[] zwischenspeicher = new String[9999];
		int length = 0;
		for (int i = 0; string.length > i && string.length > 0;) {
			if (!string[i].equals("")) {
				zwischenspeicher[length] = string[i];
				length = length + 1;
			}
			i++;

		}

		String[] end = new String[length];

		for (int x = 0; x < length;) {
			end[x] = zwischenspeicher[x];
			x++;
		}
		return end;
	}

	public Gui_main getGui_main() {
		return gui_main;
	}

	public void setGui_main(Gui_main gui_main) {
		this.gui_main = gui_main;
	}

	public JTextArea getInput_texflield() {
		return input_texflield;
	}

	public void setInput_texflield(JTextArea input_texflield) {
		this.input_texflield = input_texflield;
	}

	public JScrollPane getInput_scrollpane() {
		return input_scrollpane;
	}

	public void setInput_scrollpane(JScrollPane input_scrollpane) {
		this.input_scrollpane = input_scrollpane;
	}

	public int getAttribut_count() {
		return attribut_count;
	}

	public void setAttribut_count(int attribut_count) {
		this.attribut_count = attribut_count;
	}

	public Atribut[] getAttribute() {
		return attribute;
	}

	public void setAttribute(Atribut[] attribute) {
		this.attribute = attribute;
	}

	public Methode[] getMethoden1() {
		return Methoden1;
	}

	public void setMethoden1(Methode[] methoden1) {
		Methoden1 = methoden1;
	}

	public int getMethoden_count() {
		return methoden_count;
	}

	public void setMethoden_count(int methoden_count) {
		this.methoden_count = methoden_count;
	}

	public String getClass_name1() {
		return class_name1;
	}

	public void setClass_name1(String class_name1) {
		this.class_name1 = class_name1;
	}

	public boolean isToString_da() {
		return toString_da;
	}

	public void setToString_da(boolean toString_da) {
		this.toString_da = toString_da;
	}

	public Konstruktor[] getKonstruktoren() {
		return konstruktoren;
	}

	public void setKonstruktoren(Konstruktor[] konstruktoren) {
		this.konstruktoren = konstruktoren;
	}

	public int getKonstruktor_count() {
		return Konstruktor_count;
	}

	public void setKonstruktor_count(int konstruktor_count) {
		Konstruktor_count = konstruktor_count;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public int getKon_count() {
		return kon_count;
	}

	public void setKon_count(int kon_count) {
		this.kon_count = kon_count;
	}

}