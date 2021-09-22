package Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import agents.AgentAI;
import jade.gui.GuiEvent;

public class AIGUI extends JFrame {
	private JLabel pays=new JLabel ("Pays");
	private JTextField payst=new JTextField (12);
	private JLabel ville=new JLabel ("Ville");
	private JTextField villet=new JTextField (12);
	private JLabel chambre=new JLabel ("Chambre");
	private JTextField chambret=new JTextField (12);
	private JLabel nombre=new JLabel ("Nombre Personne");
	private JTextField nombret=new JTextField (12);
	private JLabel dated=new JLabel ("Date Début");
	private JTextField datedt=new JTextField (12);
	private JLabel datef=new JLabel ("Date Fin");
	private JTextField dateft=new JTextField (12);
	private JButton envoyer=new JButton ("envoyer");
	private JTextArea message=new JTextArea();
	private AgentAI agentAI;

	public AgentAI getAgentAI() {
		return agentAI;
	}
	public void setAgentAI(AgentAI agentAI) {
		this.agentAI = agentAI;
	}
   public void showmsg(String msg,boolean append) {
	   if(append==true) {
		   message.append(msg+"\n");
	   }
	   else {
		   message.setText(msg);
	   }
   }
	public AIGUI () {
		JPanel d=new JPanel();
		d.setLayout(new FlowLayout());
		d.add(pays);d.add(payst);d.add(ville);d.add(villet);
		d.add(chambre);d.add(chambret);d.add(nombre);
		d.add(nombret);d.add(dated);d.add(datedt);
		d.add(datef);d.add(dateft);d.add(envoyer);
		this.setLayout(new BorderLayout());
		this.add(d,BorderLayout.CENTER);
		this.add(new JScrollPane(message),BorderLayout.PAGE_END);
		this.setSize(600,400);
		this.setVisible(true);
		envoyer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Ville=villet.getText();
				String	 Pays=payst.getText();
				String Chambre=chambret.getText();
				String NombreP=nombret.getText();
				int Nombre=Integer.parseInt(NombreP);
				String Dated=datedt.getText();
				String Datef=dateft.getText();
				GuiEvent gev =new GuiEvent(this,1);
				Map<String,Object> res= new HashMap<>();
				res.put("Ville", Ville);
				res.put("Pays",Pays);
				res.put("Chambre", Chambre);
				res.put("NombreP", Nombre);
				res.put("Dated", Dated);
				res.put("Datef", Datef);
				gev.addParameter(res);
				 agentAI.onGuiEvent(gev);
			}});}}
