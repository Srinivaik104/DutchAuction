
package id2209.hw2;

import jade.wrapper.AgentContainer;
import jade.core.ProfileImpl;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {    
    static jade.core.Runtime rt;
    static AgentContainer c1;
    static AgentContainer c2;
    
    public static void main(String[] args) throws StaleProxyException {
    rt = jade.core.Runtime.instance();
    rt.setCloseVM(true);
    rt.createMainContainer(new ProfileImpl()).createNewAgent("rma", "jade.tools.rma.rma", new Object[]{}).start();
    
    c1 =  rt.createAgentContainer(new ProfileImpl());
    c2 =  rt.createAgentContainer(new ProfileImpl());
    c1.createNewAgent("ArtistManager", "id2209.hw2.ManagerAgent", new Object[]{}).start();
    for(int i = 0; i<6; i++)
        c2.createNewAgent("CuratorAgent", "id2209.hw2.CuratorAgent", new Object[]{}).start();
}
public static void quit(){
    try{
        c1.kill();
        c2.kill();
    }catch(StaleProxyException ex){
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
