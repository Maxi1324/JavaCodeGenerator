public class Konstruktor {

	private String class_name;
	private Atribut[] Parameter;

	int Parameter_count;
	private Gui_Input input;

	public Konstruktor(String class_name, Atribut[] parameter, int parameter_count, Gui_Input input) {

		this.class_name = class_name;
		Parameter = parameter;
		Parameter_count = parameter_count;
		this.input = input;

	}

	public String code_konstruktor() {
		String string1 = "\n\n    public " + " " + class_name + "(" + Parameter_code() + ") {\n";


		for(int i = 0;i < input.getAttribut_count(); )
		{

			
			if(input.getAttribute()[i+1] != null && input.getAttribute()[i+1].getIs_swing() == true)
			{
				string1 += "        "+input.getAttribute()[i+1].getName() + "_action();\n";
			}
			
			i++;
		}
		
		
		if (Parameter[0] != null && Parameter[0].getTyp().equals("all")) {

			for (int x = 0; x <= input.getAttribut_count();) {

				if (input.getAttribute()[x] != null) {
					if (input.getAttribute()[x].is_Attribut() == true) {
						String setname = "    " + " set"
								+ String.valueOf(Gui_Input.del_leer_zeich(input.getAttribute()[x].getName()).charAt(0))
										.toUpperCase()
								+ Gui_Input.del_leer_zeich(input.getAttribute()[x].getName()).substring(1) + "(";
						string1 += "\n    " + setname + input.getAttribute()[x].getName() + ");";
					}
				}

				x++;

			}

		}

		else {
			for (int x = 0; x < Parameter_count;) {

				if (Parameter[x].is_Attribut() == true) {
					String setname = "    " + " set"
							+ String.valueOf(Gui_Input.del_leer_zeich(Parameter[x].getName()).charAt(0)).toUpperCase()
							+ Gui_Input.del_leer_zeich(Parameter[x].getName()).substring(1) + "(";
					string1 += "\n    " + setname + Parameter[x].getName() + ");";
				}
				x++;
			}
		}
		
		string1 += " \n    }\n";

		return string1;

	}

	public String Parameter_code() {

		String Para = "";

		if (input.getAttribut_count() > 0 && Parameter[0] != null && Parameter[0].getTyp().equals("all")) {

			for (int i = 0; i < input.getAttribut_count();) {

				if (input.getAttribute()[i + 1] != null) {
					if (i == 0) {
						Para = Para + input.getAttribute()[i + 1].getTyp() + " "
								+ input.getAttribute()[i + 1].getName();
						// System.out.println("d1a");
					}

					if (i < input.getAttribut_count() && i != 0) {
						Para = Para + "," + input.getAttribute()[i + 1].getTyp() + " "
								+ input.getAttribute()[i + 1].getName();
						//// //System.out.println("da");
						// System.out.println("d2a");
					}
				}
				i++;
			}

		}

		else {
			if (Parameter_count > 0) {

				for (int i = 0; i < Parameter_count;) {

					if (Parameter[i].is_Attribut() == true) {

						if (i == 0) {
							Para = Para + Parameter[i].getTyp() + " " + Parameter[i].getName();

						}

						if (i < Parameter_count && i != 0) {
							Para = Para + "," + Parameter[i].getTyp() + " " + Parameter[i].getName();
							//// //System.out.println("da");
						}
					}
					i++;
				}
			}
		}
		return Para;
	}

}
