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
public class Location {
    private String name = "";

    private ArrayList<EnvironmentObj> contains = new ArrayList<>(); // Objs in this location

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<EnvironmentObj> getContains() {
        return contains;
    }

    public void add(EnvironmentObj obj) {
        contains.add(obj);
    }

    public void remove(EnvironmentObj obj) {
        contains.remove(obj);
    }

    public boolean contains(EnvironmentObj other) {
        for (EnvironmentObj obj : contains)
            if (obj == other)
                return true;
        return false;
    }

    public void clean() {
        Iterator<EnvironmentObj> iter = contains.iterator();
        while (iter.hasNext()) {
            EnvironmentObj obj = iter.next();
                if (obj instanceof Dirty)
                    iter.remove();
        }
    }

  
    public void dropPoo() {
        contains.add(new Dirty());      
    }

    public boolean isDirty() {
        for (EnvironmentObj obj : contains)
            if (obj instanceof Dirty)
                return true;

        return false;
    } 
}
