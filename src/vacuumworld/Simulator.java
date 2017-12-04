/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacuumworld;

/**
 *
 * @author jamesstrater-smith
 */
public class Simulator {
    static class PerformanceMeasure {
        public int steps = 0;
        public int score = 0;        
        public boolean isAnotherStep() {
            return this.steps < 10;
        }
        public void update(Agent agent, Action action, Percept percept, VacuumEnvironment env) {
           /* Score 1 point for each location that is clean... not just the location where the vacuum is. 
              Performance is based on environment state only.
            */
            String agentName = agent.getName();
            Location location = percept.getLocation();
            int scoreAdded = env.getCleanLocationCount();
            this.score += scoreAdded;
            this.steps += 1;
            System.out.println(agentName + " has scored " + score + " after " + steps + " steps");
        }
    }
    
    public static void main(String[] args) {
        VacuumEnvironment env = VacuumEnvironment.createNew();
        PerformanceMeasure performanceMeasure = new PerformanceMeasure();
        
        while (performanceMeasure.isAnotherStep()) {
            for (int i = 0; i < env.agents().size(); i++) {
                Agent agent = env.agents().get(i);                
                Percept percept = env.getPerceptSeenBy(agent);
                Action action = agent.execute(percept);
                env.execute(agent, action);
                
                if (i == env.agents().size() - 1) {
                    /* Keep one performance metric for the environment, independent of number of (non-vacuum) agents */
                    performanceMeasure.update(agent, action, percept, env);                    
                }
            }
        }    
    }
            
}
