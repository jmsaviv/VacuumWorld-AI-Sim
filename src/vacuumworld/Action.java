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
public class Action {
    private String name;
    
    public Action(String name) {
        this.name = name;        
    }
    public String getName() {
        return this.name;
    }
    public boolean equals(String action) {
        return this.name.equalsIgnoreCase(action);
    }
    
    public boolean equals(Object that) {
        if (that == null) return false;
        else if (this == that) return true;
        else if (that instanceof Action) {
            return ((Action)that).getName().equalsIgnoreCase(this.name);
        }
        return false;
    }
}
