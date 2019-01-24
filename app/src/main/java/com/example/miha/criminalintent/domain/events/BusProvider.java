package com.example.miha.criminalintent.domain.events;

import com.squareup.otto.Bus;


public class BusProvider {
    private static Bus bus;
    public static Bus getInstance() {
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }
}
