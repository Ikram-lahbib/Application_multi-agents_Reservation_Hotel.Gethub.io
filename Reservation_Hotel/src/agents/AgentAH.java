package agents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class AgentAH extends Agent{
	private Map<String,Object> hotel=new HashMap<>();
	   private  Object[] rv;
	protected void setup() {
		 hotel.put("Pays","Maroc");
	     hotel.put("Ville","Casa");
	     hotel.put("Nom","hotel1");
	     hotel.put("Prix",500);
	     hotel.put("Nombre",4);
		try {
		  System.out.println("Démarrage de l'agent:"+this.getAID().getName());
		  DFAgentDescription Afd = new DFAgentDescription();
		  Afd.setName(getAID());
		  ServiceDescription sd = new ServiceDescription();
		  sd.setType("Hotel");
		  sd.setName("Hotel Reservation");
		  Afd.addServices(sd);
		  DFService.register(this, Afd);
		  System.out.println(getLocalName()+" Enregistrement dans l'annuaire DF");
		} catch (FIPAException e) {
				e.printStackTrace();
		}
		
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		 addBehaviour(parallelBehaviour);
		 parallelBehaviour.addSubBehaviour(new TickerBehaviour(this,30000) {

			@Override
			protected void onTick() {
				MessageTemplate mt=MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.CFP)
						,MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL));
				ACLMessage aclmsg=receive(mt);
				if(aclmsg!=null){
					switch(aclmsg.getPerformative()) {
					case ACLMessage.CFP:
						try {
							rv=(Object[]) aclmsg.getContentObject();
							String pays=(String) rv[0];
							String ville=(String) rv[1];
							int nbp=(int) rv[3];
						if(pays.equals(hotel.get("Pays")) && ville.equals(hotel.get("Ville")) && nbp==(int)hotel.get("Nombre")){
								ACLMessage reply=aclmsg.createReply();
								reply.setPerformative(ACLMessage.PROPOSE);
								reply.setContentObject((int)hotel.get("Prix"));
								send(reply);
			                   System.out.println(myAgent.getName()+" Prix de Chambre dans notre Hotel est : "
								+hotel.get("Prix")+ " et le nombre de chambre disponible est :"+hotel.get("Nombre"));	   
			                   }
							else {
								System.out.println("Données différentes!!!!!!");
							}
							} catch (UnreadableException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();  }
						break;
			
                   case ACLMessage.ACCEPT_PROPOSAL:
                	   String message=aclmsg.getContent();
						System.out.println(message);
						ACLMessage reply2=aclmsg.createReply();
						reply2.setPerformative(ACLMessage.CONFIRM);
						reply2.setContent("le nom de l'hotel est : "+hotel.get("Nom")+"et son prix est "+hotel.get("Prix"));
						send(reply2);
						
						break;
					}
				}
				
			}
		 
		 });		
}
	
	
	
	
	
	
	
	
	
	
	
   
	
	/*parallelBehaviour.addSubBehaviour(new TickerBehaviour(this,30000) {

		protected void onTick() {
			try {
				MessageTemplate mt=MessageTemplate.MatchPerformative(ACLMessage.CFP);
				ACLMessage aclmsg=receive(mt);
				if(aclmsg!=null){
						 rv=aclmsg.getContent();
						/* String pays=(String) rv[0];
						 String ville=(String) rv[1];
						 String nb=(String)rv[3];
						 if(rv==hotel.get("Pays")) {
							   String prix=hotel.get("Prix");
								ACLMessage reply=aclmsg.createReply();
								reply.setPerformative(ACLMessage.PROPOSE);
								reply.setContent(prix.toString());
								System.out.println("Prix envoyer par "+hotel.get("Prix"));
								send(reply);	
						 }  
					}
				
				
				else{ 
					System.out.println("aclmsg vide"); 
					}
				
				parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {

					@Override
					public void action() {
						MessageTemplate temp=MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
						ACLMessage msgr=receive(temp);
						if(msgr!=null) {
						String message=msgr.getContent();
						System.out.println(message);
						ACLMessage reply2=msgr.createReply();
						reply2.setPerformative(ACLMessage.CONFIRM);
						reply2.setContent("le nom de l'hotel est"+hotel.get("Nom")+"et le prix est"+hotel.get("prix"));
						send(reply2);
					}
			}	
					
				});
			
		}catch (Exception e) 
	        { e.printStackTrace();}
	}
		
	});*/
	

}

