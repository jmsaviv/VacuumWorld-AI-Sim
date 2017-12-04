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
public class DogProgram implements AgentProgram {
    public DogProgram() {}

    @Override
    public Action execute(Percept percept) {
        Location location = percept.getLocation();
    
        int act = VacuumEnvironment.getRandom().nextInt(10);
        Action action;
        switch (act) {
            case 1:
            case 2:
                action = VacuumEnvironment.getAction("DoAPoo"); // Nature calls
                break;
            case 3:
            case 4:
            case 5: // Look for owner
                action = VacuumEnvironment.getAction(location.getName().equals("B") ? "Left" : "Right");
                break;
            default: 
                action = VacuumEnvironment.getAction("NoOp"); // Take a nap
                break;
        }
        return action;
    }    
}
