public class Methode {

	private String name;
	private String Typ;
	private Atribut[] Parameter;
	private int Parameter_count;
	private Gui_Input gui_input;
	public Methode(String name, String typ, int parametercount, Atribut[] Parameter,Gui_Input gui_input) {
		this.name = name;
		Typ = typ;
		this.Parameter = Parameter;
		this.Parameter_count = parametercount;
		////System.out.println(typ);
		this.gui_input = gui_input;
		
	}

	public String code_toString()
	{
		String code  = "\n\n    public " + "String" + " " + name + "(" + Parameter_code() + ") { \n"+"        return  \" -----------------------\\n \"";
	
		for(int x = 0;x < gui_input.getAttribut_count();)
		{
			gui_input.toString_da = true;
			
			if(gui_input.getAttribute()[x] != null) code += " + "+"\""+ gui_input.getAttribute()[x].getName() + " : \" + " + "get"+ String.valueOf( gui_input.getAttribute()[x].getName().charAt(0)).toUpperCase()+ gui_input.getAttribute()[x].getName().substring(1)+"() + \" \\n \"";
			
			x++;
		}
		
		code +=";\n    }";
		return code;
	}
	
	public String code_print() {
		String string2 = "";
		if(gui_input.toString_da == false)
		{
			String code  = "   \" -----------------------\\n \"";
			
			for(int x = 0;x < gui_input.getAttribut_count();)
			{
				gui_input.toString_da = true;
				
				if(gui_input.getAttribute()[x] != null) code += " + "+"\""+ gui_input.getAttribute()[x].getName() + " : \" + " + "get"+ String.valueOf( gui_input.getAttribute()[x].getName().charAt(0)).toUpperCase()+ gui_input.getAttribute()[x].getName().substring(1)+"() + \" \\n \"";
				
				x++;
			}
			
			string2 = "        System.out.println("+code + ");";
		}
		else 
		{
			string2 = "        System.out.println(toString());";
		}
		
		return code_methode_standart(string2);
	
	}
	
	private String code_methode_standart(String hinzufügen)
	{
		String string1 = "\n\n    public " + Typ + " " + name + "(" + Parameter_code() + ") { \n"+hinzufügen+ "\n    }\n";
		return string1;
	}
	
	public static String code_methode_standart(String hinzufügen,String Typ , String name)
	{
		String string1 = "\n\n    public " + Typ + " " + name + "(" +  ") { \n"+hinzufügen+ "\n    }\n";
		return string1;
	}
	
	public String code_Methode() {
		////System.out.println(gui_input.toString_da);
		// ////System.out.println("Parameter " + Parameter_count);
		String string1 = "\n\n    public " + Typ + " " + name + "(" + Parameter_code() + ") { \n \n   }\n";
		switch (name) {
		case "toString":
			if (Typ.equals("String")) {
				string1 = code_toString();
			}
			break;
			
		case "print":
			if(Typ.equals("void")) string1 = code_print();
			break;
		}
		return string1;

	}

	public String Parameter_code() {
		
		String Para = "";
		if (Parameter_count > 0) {
			
			for (int i = 0; i < Parameter_count;) {

				if (i == 0 ) {
					Para = Para + Parameter[i].getTyp() + " " + Parameter[i].getName();
				
				}

				if (i < Parameter_count && i != 0) {
					Para = Para + ","+ Parameter[i].getTyp() + " " + Parameter[i].getName();
					//////System.out.println("da");
				}

				i++;
			}
		}
		return Para;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTyp() {
		return Typ;
	}

	public void setTyp(String typ) {
		Typ = typ;
	}

	public Atribut[] getParameter() {
		return Parameter;
	}

	public void setParameter(Atribut[] parameter) {
		Parameter = parameter;
	}

}
