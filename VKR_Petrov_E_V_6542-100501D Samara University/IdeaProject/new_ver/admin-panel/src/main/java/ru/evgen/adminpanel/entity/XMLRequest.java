package ru.evgen.adminpanel.entity;

import java.util.List;

public class XMLRequest {
    private long CID;
    private long SIDResp;
    private List<ControlCmd> controlCmds;

    public long getCID() {
        return CID;
    }

    public void setCID(long CID) {
        this.CID = CID;
    }

    public long getSIDResp() {
        return SIDResp;
    }

    public void setSIDResp(long SIDResp) {
        this.SIDResp = SIDResp;
    }

    public List<ControlCmd> getControlCmds() {
        return controlCmds;
    }

    public void setControlCmds(List<ControlCmd> controlCmds) {
        this.controlCmds = controlCmds;
    }
}
