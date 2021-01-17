package ru.evgen.adminpanel.entity;

public class ControlCmd {
    private long DevId;
    private long DevType;
    private long Action;
    private long ID;

    public long getDevId() {
        return DevId;
    }

    public void setDevId(long devId) {
        DevId = devId;
    }

    public long getDevType() {
        return DevType;
    }

    public void setDevType(long devType) {
        DevType = devType;
    }

    public long getAction() {
        return Action;
    }

    public void setAction(long action) {
        Action = action;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
