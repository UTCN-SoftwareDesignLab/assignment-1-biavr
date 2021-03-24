package model;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private final User employee;
    private List<Action> actions;

    public Report(User employee) {
        this.employee = employee;
        actions = new ArrayList<>();
    }

    public void addAction(Action action){
        actions.add(action);
    }

    public List<Action> getActions() {
        return actions;
    }
}
