import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Atribut {

	private String name;
	private String typ;
	private String getMethode_name = "nichtgesetzt";
	private String setMethode_name = "nicht gesetzt";
	private Gui_main gui_main;
	private boolean is_swing;

	public Atribut(String name, String typ, Gui_main gui_main) {
		setName(Gui_Input.del_leer_zeich(name));
		setTyp(Gui_Input.del_leer_zeich(typ));
		
		setGui_main(gui_main);
		typ();

	}

	public boolean is_Attribut() {

		for (int x = 0; x < gui_main.getGui_Input().getAttribut_count();) {
			if (gui_main.getGui_Input().getAttribute()[x + 1].name.equals(Gui_Input.del_leer_zeich(getName()))) {
				return true;
			}
			x++;
		}

		gui_main.getGui_Error_Output()
				.Fehlermeldung("Fehler : Konstruktor Parameter ist kein Attribut (" + name + "," + typ + ") #0004");

		return false;

	}

	public boolean typ() {

		if (gui_main.getOption_fenster().isTyp_prufen() == true) {
			String substring1 = getTyp();
			if (Gui_Input.del_leer_zeich(name).length() == 0) {
				gui_main.getGui_Error_Output()
						.Fehlermeldung("Fehler : Varieblenname ungültig (" + name + "," + typ + ") #0001");
			}

			if (Gui_Input.del_leer_zeich(substring1).equals("int")) {

				return true;

			}

			if (Gui_Input.del_leer_zeich(substring1).equals("all")) {

				return true;

			}
			if (Gui_Input.del_leer_zeich(substring1).equals("long")) {

				return true;
			}

			if (Gui_Input.del_leer_zeich(substring1).equals("String")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("float")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("double")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("short")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("byte")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("boolean")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("char")) {

				return true;
			}

			if (Gui_Input.del_leer_zeich(substring1).equals("int[]")) {

				return true;

			}
			if (Gui_Input.del_leer_zeich(substring1).equals("long[]")) {

				return true;
			}

			if (Gui_Input.del_leer_zeich(substring1).equals("String[]")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("float[]")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("double[]")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("short[]")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("byte[]")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("boolean[]")) {

				return true;
			}
			if (Gui_Input.del_leer_zeich(substring1).equals("char[]")) {

				return true;
			}

			if (Gui_Input.del_leer_zeich(substring1).equals("JButton")) {

				is_swing = true;
				return true;
			}

			fehler();

			return false;
		}
		
		else {
			return true;
		}
	}

	public String code_var() {

		String code;
		code = "    private " + typ + " " + Gui_Input.del_leer_zeich(name) + ";" + "\n";
		return code;
	}

	public String code_get_var() {
		String code;
		code = " \n    public " + Gui_Input.del_leer_zeich(typ) + " get"
				+ String.valueOf(Gui_Input.del_leer_zeich(name).charAt(0)).toUpperCase()
				+ Gui_Input.del_leer_zeich(name).substring(1) + "()" + " " + "{" + "\n" + "" + "        return "
				+ "this." + Gui_Input.del_leer_zeich(name) + ";" + "\n" + "    }" + "\n";

		return code;
	}

	public String codeswingaction() {
		String str = "";
		str += "\n        " + Gui_Input.del_leer_zeich(name) + ".addActionListener(new ActionListener() {\n\n"
				+ "            public void actionPerformed(ActionEvent e) {\n\n" + "            }\n\n" + "        });";

		
		return Methode.code_methode_standart(str, "void", name + "_action");
	}

	public String code_set_var() {

		String code = "\n    public void " + "set" + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1)
				+ "(" + typ + " " + name + ") {";
		;
		code += "\n        this." + name + " = " + name + ";";
		code += "\n    }" + "\n";

		return code;

	}

	public void fehler() {

		if (gui_main != null)
			gui_main.getGui_Error_Output()
					.Fehlermeldung("Fehler : Variablentyp ungültig (" + typ + "," + name + ") 0002");
		else {
			System.out.println("Fehler : Variablentyp ungültig (" + name + "," + typ + ") 0002");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public Gui_main getGui_main() {
		return gui_main;
	}

	public void setGui_main(Gui_main gui_main) {
		this.gui_main = gui_main;
	}

	public String getGetMethode_name() {
		return this.getMethode_name;
	}

	public void setGetMethode_name(String getMethode_name) {
		this.getMethode_name = getMethode_name;
	}

	public String getSetMethode_name() {
		return this.setMethode_name;
	}

	public void setSetMethode_name(String setMethode_name) {
		this.setMethode_name = setMethode_name;
	}

	public boolean getIs_swing() {
		return is_swing;
	}

	public void setIs_swing(boolean is_swing) {
		this.is_swing = is_swing;
	}

}
