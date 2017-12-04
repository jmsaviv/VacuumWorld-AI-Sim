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
public class VacuumProgram implements AgentProgram {
    @Override
    public Action execute(Percept percept) {
        Location location = percept.getLocation();
        if (location.isDirty()) {
            return VacuumEnvironment.getAction("Vacuum");
        } else if (location.getName().equals("A")) {
            return VacuumEnvironment.getAction("Right");
        } else if (location.getName().equals("B")) {
            return VacuumEnvironment.getAction("Left");
        } else {
            return VacuumEnvironment.getAction("NoOp");
        }    
    }
}
