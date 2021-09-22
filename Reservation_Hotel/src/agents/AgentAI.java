package agents;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Gui.AIGUI;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgentAI extends GuiAgent{
	 private AIGUI ai;
	 protected void setup() {
			ai =new AIGUI();
			ai.setAgentAI(this);
			System.out.println("demarage de l'agent Interface");
			addBehaviour(new CyclicBehaviour() {

				@Override
				public void action() {
					ACLMessage msg =receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
					if (msg!=null) {
						String nom=msg.getContent();
						System.out.println(nom);
						ai.showmsg(nom, true);
				}}
		});
			}
	 
	 
	 
	@Override
	public void onGuiEvent(GuiEvent gv) {
		switch(gv.getType()) {
		case 1:
			Map<String,Object> res=(Map<String,Object>)gv.getParameter(0);
			 String Pays1=(String)res.get("Pays");
			 String Ville1=(String)res.get("Ville");
			 String Chambre1=(String)res.get("Chambre");
			 int NombreP=(int)res.get("NombreP");
			 String Dated1=(String)res.get("Dated");
			 String Datef1=(String)res.get("Datef");
			 Object [] resrv= {Pays1,Ville1,Chambre1,NombreP,Dated1,Datef1};
			ACLMessage aclmessage=new ACLMessage(ACLMessage.REQUEST);
			aclmessage.addReceiver(new AID("AgentAR",AID.ISLOCALNAME));
			try {
				aclmessage.setContentObject(resrv);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			send(aclmessage);
			System.out.println("L'agent AI envoyer les information a l'agent AR "+Pays1);
			
			break;
			
		default :
			break;
		}	
	}
}
