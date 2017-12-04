/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacuumworld;

import java.util.*;
/**
 *
 * @author jamesstrater-smith
 */
public class VacuumEnvironment {
  private HashMap<String, Location> locations = new HashMap<>();
  private HashMap<String, Action> actions = new HashMap<>();

  private ArrayList<Agent> agents = new ArrayList<>();
  private Random r = new Random();
  
  private static VacuumEnvironment instance;

  public static Action getAction(String name) {
    return getInstance().actions.get(name);
  }

  public static Collection<Action> getActions() {
   return getInstance().actions.values();
  }

  public static Random getRandom() { return getInstance().r; }
  
  public static VacuumEnvironment getInstance() { return instance; }

  public static VacuumEnvironment createNew() {
    instance = new VacuumEnvironment();
    return instance;
  }
  
  private VacuumEnvironment() {
    System.out.println("Setting up the initial vacuum environment");
    locations.put("A", new Location("A"));
    locations.put("B", new Location("B"));

    for (Location loc: locations.values()) {
        if (r.nextInt(2) == 0) { // Randomly decide if this location is dirty
            loc.add(new Dirty());
            System.out.println("Adding dirt at " + loc.getName());
        }
    }
    
    Agent dog = new Agent("Lilly", new DogProgram());
    agents.add(dog);
    String dogLoc = r.nextInt(2) == 0 ? "A" : "B";
    locations.get(dogLoc).add(dog);
    System.out.println("Inserting " + dog.getName() + " at location " + dogLoc);

    // Make the SuperVac the 2nd agent so it has the chance to respond to Lilly's actions from this iteration
    Agent vac = new Agent("SuperVac", new VacuumProgram());
    agents.add(vac);
    String vacLoc = r.nextInt(2) == 0 ? "A" : "B";
    locations.get(vacLoc).add(vac);
    System.out.println("Inserting " + vac.getName() + " at location " + vacLoc);

    actions.put("NoOp", new Action("NoOp"));
    actions.put("Left", new Action("Left"));
    actions.put("Right", new Action("Right"));
    actions.put("Vacuum", new Action("Vacuum"));
    actions.put("DoAPoo", new Action("DoAPoo"));
  }
 
  public Percept getPerceptSeenBy(Agent agt) {
    return new Percept(agentLocation(agt)); // Fully observable
  }

  public void execute(Agent agt, Action act) {
    String action = act.getName();
    Location location = agentLocation(agt);
    
    System.out.println(agt.getName() + " performed action " + action + " at location " + location.getName());
    if (!action.equals("NoOp")) {

      if (action.equals("Vacuum")) {
        location.clean();
      } else if (action.equals("DoAPoo")) {
        location.dropPoo();
      } else if (action.equals("Left") || action.equals("Right")) {
        location.remove(agt);

        if (location.getName().equals("A"))
          locations.get("B").add(agt);
        else
          locations.get("A").add(agt);
      }
    }
  }

  public ArrayList<EnvironmentObj> contains() { // Derived asscociation
    ArrayList<EnvironmentObj> objs = new ArrayList<>();

    for (Location location : locations.values())
      objs.addAll(location.getContains());

    return objs;
  }

  public ArrayList<Agent> agents() {
    return agents;
  }

  private Location agentLocation(Agent agt) {    // Utility returning the Agent's current location
    for (Location location : locations.values()) 
      if (location.contains(agt))
        return location;

    return null;
  }
 
  public int getCleanLocationCount() { // Return the total number of clean locations for scoring Rationality
      int cleanCnt = 0;
      for (Location location: locations.values()) {
          if (!location.isDirty())
              cleanCnt += 1;
      }
      return cleanCnt;
  }
}

