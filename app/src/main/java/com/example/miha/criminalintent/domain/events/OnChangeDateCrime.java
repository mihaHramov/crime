package com.example.miha.criminalintent.domain.events;

public class OnChangeDateCrime {
    private String date;

    public OnChangeDateCrime(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
