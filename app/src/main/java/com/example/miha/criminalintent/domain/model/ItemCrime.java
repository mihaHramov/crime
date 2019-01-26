package com.example.miha.criminalintent.domain.model;

public class ItemCrime {
    private Crime crime;
    private Integer position;

    public Crime getCrime() {
        return crime;
    }

    public void setCrime(Crime crime) {
        this.crime = crime;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
