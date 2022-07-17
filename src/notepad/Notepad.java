package notepad;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

/*
 * Here we are going to give the java ux and i/o concepts
 * 
 * */
public class Notepad extends JFrame implements ActionListener, WindowListener {

	JTextArea jta = new JTextArea();
	File fnameContainer;
	
	public Notepad() {
		Font fnt = new Font("Arial",Font.PLAIN,15);
		Container con = getContentPane();
		JMenuBar jmb = new JMenuBar();
		JMenu jmfile = new JMenu("File");
		JMenu jmedit = new JMenu("Edit");
		JMenu jmhelp = new JMenu("Help");
		
		con.setLayout(new BorderLayout());
		JScrollPane sbrText = new JScrollPane(jta);
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sbrText.setVisible(true);
		
		jta.setFont(fnt);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		
		con.add(sbrText);
		
		createMenuItem(jmfile,"New");
		createMenuItem(jmfile,"Open");
		createMenuItem(jmfile,"Save");
		jmfile.addSeparator();
		createMenuItem(jmfile,"Exit");	
		
		createMenuItem(jmedit,"Cut");
		createMenuItem(jmedit,"Copy");
		createMenuItem(jmfile,"Paste");
		
		createMenuItem(jmhelp,"About Notepad");
		
		jmb.add(jmfile);
		jmb.add(jmedit);
		jmb.add(jmhelp);
		
		setJMenuBar(jmb);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
		addWindowListener(this);
		setSize(500,500);
		setTitle("Untitled.txt - Notepad");
		setVisible(true);
	}

public void createMenuItem(JMenu jm, String txt) {
	// TODO Auto-generated method stub
	JMenuItem jmi = new JMenuItem(txt);
	jmi.addActionListener(this);
	jm.add(jmi);
}

public void actionPerformed(ActionEvent e) {
	JFileChooser jfc = new JFileChooser();
	if(e.getActionCommand().equals("New")) {
		this.setTitle("Untitled.txt");
		jta.setText("");
		fnameContainer = null;
	}
	else if(e.getActionCommand().equals("Open")) {
		int ret = jfc.showDialog(null, "Open");
		if(ret == JFileChooser.APPROVE_OPTION) {
			try {
				File fyl = jfc.getSelectedFile();
				OpenFile(fyl.getAbsolutePath());
				this.setTitle(fyl.getName()+"");
				fnameContainer = fyl;
				
			}catch(IOException E) {}
			
		}
		else if(e.getActionCommand().equals("Save")) {
			if(fnameContainer!=null) {
				
			}
			else {
				jfc.setSelectedFile(new File("untitled.txt"));
			}
			
			int rete = jfc.showSaveDialog(null);
			if(rete == JFileChooser.APPROVE_OPTION) {
				try {
					File fyl = jfc.getSelectedFile();
					SaveFile(fyl.getAbsolutePath());
					this.setTitle(fyl.getName()+ "");
					fnameContainer = fyl;
				}
				catch(Exception er) {
				}
				}
			}
			
		else if(e.getActionCommand().equals("Exit")) {
			Exiting();
			}
		else if(e.getActionCommand().equals("Copy")) {
			jta.copy();
			}
		else if(e.getActionCommand().equals("Paste")) {
			jta.paste();
			}
		else if(e.getActionCommand().equals("About")) {
			JOptionPane.showMessageDialog(this, "Created by: santhoshsivan");
			}
		else if(e.getActionCommand().equals("Cut")) {
			jta.cut();
			}}
	}
	
	public void OpenFile(String fname) throws IOException{
		try {
			BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
			String l;
			jta.setText("");
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			while((l=d.readLine())!=null) {
				jta.setText(jta.getText()+l+"\r\n");
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			d.close();
		}
		catch (Exception e) {
			
		}
	}
	
	public void SaveFile(String fname) throws IOException{
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		DataOutputStream o = new DataOutputStream(new FileOutputStream(fname));
		o.writeBytes(jta.getText());
		o.close();
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		Exiting();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void Exiting() {
		System.exit(0);
	}
	}

