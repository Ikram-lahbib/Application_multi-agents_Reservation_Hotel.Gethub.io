package Container;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class AgentAHContainer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      try {
		Runtime rt=Runtime.instance();
		ProfileImpl pc=new ProfileImpl(false);
		pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");
		AgentContainer container1 =rt.createAgentContainer(pc);
		AgentController agentcontroller1=container1.createNewAgent("AgentAH1", "agents.AgentAH", new Object[]{});
		AgentController agentcontroller2=container1.createNewAgent("AgentAH2", "agents.AgentAH2", new Object[]{});
        agentcontroller1.start();
		agentcontroller2.start();
		    
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
