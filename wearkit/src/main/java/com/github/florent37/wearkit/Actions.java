package com.github.florent37.wearkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by florentchampigny on 17/04/15.
 */
public class Actions {

    private List<String> actionsNames = new ArrayList<>();
    private List<Integer> actionsTitleColor = new ArrayList<>();
    private boolean cancelButtonEnabled = false;

    public Actions(List<String> actionsNames, List<Integer> actionsTitleColor, boolean cancelButtonEnabled) {
        this.actionsNames = actionsNames;
        this.actionsTitleColor = actionsTitleColor;
        this.cancelButtonEnabled = cancelButtonEnabled;
    }

    public Actions(List<String> actionsNames, boolean cancelButtonEnabled) {
        this.actionsNames = actionsNames;
        this.cancelButtonEnabled = cancelButtonEnabled;
    }

    public Actions(String[] actionsNames, Integer[] actionsTitleColor, boolean cancelButtonEnabled) {
        this(Arrays.asList(actionsNames),Arrays.asList(actionsTitleColor),cancelButtonEnabled);
    }

    public Actions(String[] actionsNames, boolean cancelButtonEnabled) {
        this(Arrays.asList(actionsNames), cancelButtonEnabled);
    }

    public List<String> getActionsNames() {
        return actionsNames;
    }

    public void setActionsNames(List<String> actionsNames) {
        this.actionsNames = actionsNames;
    }

    public List<Integer> getActionsTitleColor() {
        return actionsTitleColor;
    }

    public void setActionsTitleColor(List<Integer> actionsTitleColor) {
        this.actionsTitleColor = actionsTitleColor;
    }

    public boolean isCancelButtonEnabled() {
        return cancelButtonEnabled;
    }

    public void setCancelButtonEnabled(boolean cancelButtonEnabled) {
        this.cancelButtonEnabled = cancelButtonEnabled;
    }
}
