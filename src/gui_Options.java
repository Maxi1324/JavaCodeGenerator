import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class gui_Options extends JFrame{

	
	private JPanel spalte_1= new JPanel();
	private JPanel spalte_2[] = new JPanel[1];
	private JButton[] uberschriften = new JButton[2];
	private boolean typ_prufen ;
	private JButton toogle;
	public gui_Options(Gui_main main) {
		
		fenster(main);
		algemein();
		
		einstellungen_auslesen();
		
		
		
		toogle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean bool1 = false;
				if(typ_prufen == true) {
					
					andern(false);
					System.out.println("false");
					bool1 = false;
				}
				else {
					andern(true);
					System.out.println("true");
					bool1 = true;
				}
				
				try {
					OutputStream output = new FileOutputStream(new File("save.maxi"));
					try {
						output.write(String.valueOf(bool1).getBytes());
					} catch (IOException e1) {
						
					}
					try {
						output.close();
					} catch (IOException e1) {
						
					}
				} catch (FileNotFoundException e1) {
				
				}
				
			}
		});
		
	}
	
	public void fenster(Gui_main main)
	{
		setSize(new Dimension(600,400));
		setLocationRelativeTo(main.getMain_bar());
		setAlwaysOnTop(true);
		setTitle("Options");
		add(spalte_1);
		
		spalte_1.setPreferredSize(new Dimension(200-10,400-20));
		setLayout(new FlowLayout());
		spalte_1.setBackground(Color.GRAY);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		spalte_2[0] = new JPanel();
		for (int i = 0;spalte_2.length > i;) {
			
			spalte_2[i] = new JPanel();
		
			spalte_2[i].setPreferredSize(new Dimension(400-20,400-20));
			spalte_2[i].setBackground(Color.LIGHT_GRAY);
			spalte_2[i].setVisible(true);
			add(spalte_2[i]);
			System.out.println(spalte_2[0].isVisible()==true);
			i++;
		}
		spalte_2[0].setVisible(true);
		
		addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		     setVisible(false);
		    }
		});
	}
	
	public void algemein()
	{
		GridLayout layout = new GridLayout();
		layout.setColumns(0);
		layout.setRows(10);
		spalte_1.setLayout(layout);
		algmein_dings();
		
		
	}
	
	public void einstellungen_auslesen() {
		
		File datei = new File("save.maxi");
		BufferedReader stream = null;
		try {
			stream = new BufferedReader(new InputStreamReader(new FileInputStream(datei)));
		} catch (FileNotFoundException e1) {
			
		}
		if(datei.exists()) {
			try {
				andern((stream.readLine().equals("true"))?true:false);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		else {
			andern(true);
			
		}
	}
	
	public void algmein_dings() {
		
		JPanel optionen[] = new JPanel[1];
		
		uberschriften[0] = new JButton("Algemein");
		uberschriften[0].setBackground(Color.WHITE);
		
		spalte_1.add(uberschriften[0]);
		uberschriften[0].setFont(Gui_main.fontgroesse(20));
		optionen[0] = new JPanel();
		uberschriften[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				change_spalte2(0);
				
			}
		});
		
		spalte_2[0].add(optionen[0]);
		
		optionen[0].setBackground(Color.WHITE);
		optionen[0].setPreferredSize(new Dimension(380,50));
		optionen[0].setLayout(new FlowLayout());
		JLabel text = new JLabel("Typ überprüfen");
		text.setPreferredSize(new Dimension(300,40));
		 toogle= new JButton();
		optionen[0].add(text);
		text.setFont(Gui_main.fontgroesse(20));
		optionen[0].add(toogle);
		toogle.setPreferredSize(new Dimension(50,40));
		
	}
	
	
	
	public void change_spalte2(int was_an) {
		
		for(int i = 0;i < spalte_2.length;)
		{
			spalte_2[i].setVisible(false);
			i++;
		}
		spalte_2[was_an].setVisible(true);
		
	}
	
	public void andern(boolean bool)
	{
		if(bool == true) {
			typ_prufen = true;
			toogle.setBackground(Color.BLACK);
			
		}
		else {
			typ_prufen = false;
			toogle.setBackground(Color.WHITE);
		}
	}
	
	public JPanel getSpalte_1() {
		return spalte_1;
	}

	public void setSpalte_1(JPanel spalte_1) {
		this.spalte_1 = spalte_1;
	}

	public JPanel[] getSpalte_2() {
		return spalte_2;
	}

	public void setSpalte_2(JPanel[] spalte_2) {
		this.spalte_2 = spalte_2;
	}

	public JButton[] getUberschriften() {
		return uberschriften;
	}

	public void setUberschriften(JButton[] uberschriften) {
		this.uberschriften = uberschriften;
	}

	public boolean isTyp_prufen() {
		return typ_prufen;
	}

	public void setTyp_prufen(boolean typ_prufen) {
		this.typ_prufen = typ_prufen;
	}

	public JButton getToogle() {
		return toogle;
	}

	public void setToogle(JButton toogle) {
		this.toogle = toogle;
	}

	
}
