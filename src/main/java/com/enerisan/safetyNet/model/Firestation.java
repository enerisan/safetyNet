package com.enerisan.safetyNet.model;

public class Firestation {
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;
    private String station;

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }


    public Firestation updateFirestation(Firestation firestation, String station) {
        return null;
    }
}


