package ru.evgen.adminpanel.entity;

public class XMLResponse {
    private long CIDResp;
    private long SID;
    private boolean ControlCmdsResponse;
    private ControlCmdResults controlCmdResults;

    public long getCIDResp() {
        return CIDResp;
    }

    public void setCIDResp(long CIDResp) {
        this.CIDResp = CIDResp;
    }

    public long getSID() {
        return SID;
    }

    public void setSID(long SID) {
        this.SID = SID;
    }

    public boolean isControlCmdsResponse() {
        return ControlCmdsResponse;
    }

    public void setControlCmdsResponse(boolean controlCmdsResponse) {
        ControlCmdsResponse = controlCmdsResponse;
    }

    public ControlCmdResults getControlCmdResults() {
        return controlCmdResults;
    }

    public void setControlCmdResults(ControlCmdResults controlCmdResults) {
        this.controlCmdResults = controlCmdResults;
    }
}
