package ru.evgen.adminpanel.entity;

import java.util.List;

public class ControlCmdResults {
    private int ActiveCmdsCount;
    private List<Item> ControlCmds;

    public int getActiveCmdsCount() {
        return ActiveCmdsCount;
    }

    public void setActiveCmdsCount(int activeCmdsCount) {
        ActiveCmdsCount = activeCmdsCount;
    }

    public List<Item> getControlCmds() {
        return ControlCmds;
    }

    public void setControlCmds(List<Item> controlCmds) {
        ControlCmds = controlCmds;
    }
}
