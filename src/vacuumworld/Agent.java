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
public class Agent extends EnvironmentObj {
  protected AgentProgram program;
  private final String name;
  public Agent(String name, AgentProgram program) {
      this.name = name;
      this.program = program;
  }

  public String getName() {
      return this.name;      
  }
  
  public Action execute(Percept p) {
    if (null != program) {
      return program.execute(p);
    }
    return VacuumEnvironment.getAction("NoOp");
  }    
}
