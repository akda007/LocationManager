package com.locationmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city_data")
public class CityData {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "city_name")
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateData cityState;
    
    public CityData() {}
    
    public CityData(String name, StateData state) {
        cityName = name;
        cityState = state;
    }

    public long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public StateData getCityState() {
        return cityState;
    }
}
