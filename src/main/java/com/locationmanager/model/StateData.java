package com.locationmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "state_data")
public class StateData {
    @Id
    @GeneratedValue
    private Long id;

   

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "state_acronym")
    private String stateAcronym;

    public StateData() {}
    public StateData(String name) {
        stateName = name.trim();

        stateAcronym = "";
        String[] list = stateName.split(" ");

        if (list.length > 1) {
            stateAcronym += Character.toString(list[0].charAt(0)) + Character.toString(list[1].charAt(0));
            return;
        }

        stateAcronym += Character.toString(stateName.charAt(0)) + Character.toString(stateName.charAt(2));
    }
    
    public Long getId() {
        return id;
    }

    public String getStateAcronym() {
        return stateAcronym;
    }

    public String getStateName() {
        return stateName;
    }

    @Override
    public String toString() {
        return stateName + " | " + stateAcronym.toUpperCase();
    }
}
