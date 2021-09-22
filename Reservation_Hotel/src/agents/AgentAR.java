package agents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jade.core.AID;
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

public class AgentAR extends Agent {
	private Object [] resrv ;
    private AID[] Agenthotel;
    private Map<String,Object> offer=new HashMap<>();
    int index;
    int mprix,prix;
    AID  magent,agent;
    
	protected void setup() {
		System.out.println("Démarrage de l'agent:"+this.getAID().getName());
		try {
			DFAgentDescription Afd = new DFAgentDescription();
			Afd.setName(getAID());
			ServiceDescription sd = new ServiceDescription();
			sd.setType("Resrvation Hotel");
			sd.setName("Reservation Hotel");
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
					System.out.println(getLocalName()+" je recherche des Agent hotel");
					DFAgentDescription participants = new DFAgentDescription();
					ServiceDescription sd = new ServiceDescription();
					sd.setType("Hotel");
					sd.setName("Hotel Reservation");
					participants.addServices(sd);
					DFAgentDescription[] result = null;
					try {
						result = DFService.search(myAgent,participants);
						Agenthotel = new AID[result.length];
						for (int i=0; i<result.length; ++i) {
						Agenthotel[i] = result[i].getName();
						System.out.println("Hotel --:"+result[i].getName());
						}
						
					  }catch (FIPAException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
						
						}
			 });
	
parallelBehaviour.addSubBehaviour(new TickerBehaviour(this, 30000) {
		@Override
   protected void onTick() {
			
	  MessageTemplate mt=MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
			  MessageTemplate.MatchPerformative(ACLMessage.PROPOSE));
	  
			ACLMessage msg=receive(mt);
			if (msg!=null) {
				switch(msg.getPerformative()) {
				case ACLMessage.REQUEST:
					 try {
							resrv=(Object[]) msg.getContentObject();
							for(int i =0;i<resrv.length;i++) {
								System.out.println(resrv[i]);	
							     }
							ACLMessage message = new ACLMessage(ACLMessage.CFP);
							message.setContentObject(resrv);
							for( AID aid:Agenthotel) {
							message.addReceiver(aid);
							send(message);
							System.out.println("msg envoyer a l'agent hotel"+aid);
							}
						     } catch (UnreadableException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						     } catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				break;
					
                case ACLMessage.PROPOSE:
					try {
						prix = (int)msg.getContentObject();
						agent=msg.getSender();
						System.out.println("l'agent "+msg.getSender().getName()+" a envoyer cette prix :"+prix);
						if(index==0) {
							mprix=prix;
							magent=msg.getSender();
						}else {
							if(prix<mprix) {
								mprix=prix;
								magent=msg.getSender();}   }
						++index;
						
						if(index==Agenthotel.length) {
							index=0;
							ACLMessage msga=new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
							ACLMessage msgr=new ACLMessage(ACLMessage.REFUSE);
							msga.addReceiver(magent);
							msga.setContent("Prix Accepté");
							System.out.println("j'ai envoyé un message d'accepte a la proposition de l'agent "+magent.getName());
							for(AID aid:Agenthotel) {
								if(!aid.getName().toString().equals(magent.getName().toString())) {
									msgr.addReceiver(aid);
									msgr.setContent("Prix Refusé");
								System.out.println("j'ai envoyé un message de refuse a la proposition de l'agent "+aid.getName());
								}}
							myAgent.send(msga);
							myAgent.send(msgr);   
							
						}
						
					} catch (UnreadableException e) {
						e.printStackTrace();
					}
				break;	
				}        
				}else {  System.out.println("msg vide");  }       }       });



parallelBehaviour.addSubBehaviour(new TickerBehaviour(this, 30000) {
		
		@Override
		protected void onTick() {
			ACLMessage msg=receive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
			if(msg!=null) {
			String message =(String)msg.getContent();
			System.out.println(message);
			ACLMessage msga=new ACLMessage(ACLMessage.INFORM);
			msga.addReceiver(new AID("AgentAI",AID.ISLOCALNAME));
			msga.setContent(message);
			myAgent.send(msga);
			System.out.println("Agent AR envoyer le nom d'hotel a Agent AI");
			}
			else {
				System.out.println("Agent AR n'envoyer pas le nom d'hotel a Agent AI");
			}
			
		}
		});

}
}
								
							
							
						 
							 
						 
						 
						/* System.out.println(getLocalName()+" je recherche des Agent Hotel");
							DFAgentDescription participants = new DFAgentDescription();
							ServiceDescription sde = new ServiceDescription();
							sde.setType("Hotel");
							sde.setName("Hotel");
							participants.addServices(sde);
							DFAgentDescription[] result = null;
							try {
								result = DFService.search(myAgent,participants);
								} catch (FIPAException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								}
								AID[] Agenthotel = new AID[result.length];
								for (int i = 0; i < result.length; ++i) {
								Agenthotel[i] = result[i].getName();
								System.out.println("Agent AH --:"+result[i].getName());
								}
								ACLMessage message = new ACLMessage(ACLMessage.CFP);
								for (int i = 0; i < Agenthotel.length; ++i) {
								message.addReceiver(Agenthotel[i]);
								}
								message.setContent(resrv);
								myAgent.send(message);
								parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {

									@Override
									public void action() {
										try {
											ACLMessage msgh =receive(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE));
											if (msgh != null) {
													String prix=msgh.getContent();
													System.out.println(prix);
													ACLMessage reply = msgh.createReply();
													reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
													reply.setContent("Prix Accepté");
													send(reply);	
												}	
											else {
												System.out.println("msgh est vide");
												}
									parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {

										@Override
										public void action() {
											ACLMessage msghc =receive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
											if (msghc!=null) {
												String message =(String)msghc.getContent();
												System.out.println(message);
												ACLMessage msga=new ACLMessage(ACLMessage.INFORM);
												msga.addReceiver(new AID("AgentAI",AID.ISLOCALNAME));
												msga.setContent(message);
												myAgent.send(msga);
												System.out.println("Agent AR envoyer le nom d'hotel a Agent AI");
											}
											
										}
										
									});
									
									}catch (NumberFormatException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									}	
								});*/
								
						
				
	
