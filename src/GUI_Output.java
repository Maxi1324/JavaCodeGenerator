import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class GUI_Output {

	private Gui_main gui_main;
	private JTextArea output_textfield = new JTextArea();
	private JScrollPane output_scollpane = new JScrollPane();
	private boolean Fehler;

	public GUI_Output(Gui_main gui_main) {
		this.gui_main = gui_main;
		gui_erstellen();
	}

	private void gui_erstellen() {
		Gui_main.text_area_und_scrollpane_einstellen1(output_textfield, output_scollpane, gui_main, 4.6f);
		output_textfield.setFont(gui_main.setFontmulti(output_textfield.getFont(), 2));
	}

	public void generieren(Atribut[] attribut, int attributlange, Methode[] methoden, int methoden_lange,
			Konstruktor[] konstruktoren, int kondtruktor_count, String class_name1) {

		if (Fehler == false) {

			String class_name = (class_name1.length() > 2 && class_name1 != null) ? class_name1 : "Class_Name";
			output_textfield.setText("");

			hinzufugen(output_textfield, "import javax.swing.*;\nimport java.awt.*;\n\npublic class " + class_name + " {\n\n");
					
			if (attributlange != 0) {
				for (int x = 0; attributlange >= x;) {
					if (attribut[x] != null) {
						hinzufugen(output_textfield, attribut[x].code_var());
					}

					x++;

				}
			}

			if (kondtruktor_count != 0) {
				for (int x = 0; x < kondtruktor_count;) {
					if (konstruktoren[x] != null) {
						hinzufugen(output_textfield, konstruktoren[x].code_konstruktor());
						x++;
					}
				}
			}

			if (methoden_lange != 0) {

				for (int x = 0; x < methoden_lange;) {
					if (methoden[0] != null)hinzufugen(output_textfield, methoden[x].code_Methode());

					x++;

				}
			}

			if (attributlange != 0) {
				for (int x = 0; attributlange >= x;) {
				
					if (attribut[x] != null && attribut[x].getIs_swing() == true) {
						hinzufugen(output_textfield, attribut[x].codeswingaction());

					}
					x++;
				}
			}

			if (attributlange != 0) {
				for (int x = 0; attributlange >= x;) {
					if (attribut[x] != null) {
						hinzufugen(output_textfield, attribut[x].code_get_var());
						hinzufugen(output_textfield, attribut[x].code_set_var());

					}
					x++;
				}
			}

			hinzufugen(output_textfield, "\n}");
		}

		if (gui_main.getGui_Error_Output().error == true) {

			System.out.println("gelöscht");
			output_textfield.setText("");
			gui_main.getGui_Error_Output().error = false;
			
		}
		gui_main.getGui_Error_Output().error = false;
	}

	public static void hinzufugen(JTextArea area, String add) {

		String stg = area.getText();
		area.setText(stg + add);
	}

	public boolean getFehler() {
		return this.Fehler;
	}

	public void setFehler(boolean Fehler) {
		this.Fehler = Fehler;
	}

}
