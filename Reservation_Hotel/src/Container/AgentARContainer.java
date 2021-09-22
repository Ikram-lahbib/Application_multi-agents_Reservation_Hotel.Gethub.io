package Container;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class AgentARContainer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Runtime rt=Runtime.instance();
			ProfileImpl pc=new ProfileImpl(false);
			pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");
		    AgentContainer container1 =rt.createAgentContainer(pc);
			AgentController agentcontroller1=container1.createNewAgent("AgentAR", "agents.AgentAR", new Object[]{});
			
		    agentcontroller1.start();
			
		    
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
