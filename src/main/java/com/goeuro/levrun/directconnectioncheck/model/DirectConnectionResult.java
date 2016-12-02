package com.goeuro.levrun.directconnectioncheck.model;

public class DirectConnectionResult {

    private int departureId;
    private int arrivalId;
    private boolean directConnectionExist;

    public DirectConnectionResult(Integer departureId, Integer arrivalId, Boolean directConnectionExist) {
        this.departureId = departureId;
        this.arrivalId = arrivalId;
        this.directConnectionExist = directConnectionExist;
    }

    public int getDepartureId() {
        return departureId;
    }

    public void setDepartureId(int departureId) {
        this.departureId = departureId;
    }

    public int getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(int arrivalId) {
        this.arrivalId = arrivalId;
    }

    public boolean isDirectConnectionExist() {
        return directConnectionExist;
    }

    public void setDirectConnectionExist(boolean directConnectionExist) {
        this.directConnectionExist = directConnectionExist;
    }

    @Override
    public String toString() {
        return "DirectConnectionResult{" +
                "departureId=" + departureId +
                ", arrivalId=" + arrivalId +
                ", directConnectionExist=" + directConnectionExist +
                '}';
    }
}
