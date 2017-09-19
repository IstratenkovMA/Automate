package automate;

import java.util.ArrayList;

public class IndeterminateAutomate extends Automata {

    @Override
    protected String getAutomataCorrectMessage() {
        return "Siquense is correct for Indeterminate Automata";
    }

    @Override
    public boolean checkResult() {
        ArrayList<State> endingStates = getEndingStates();
        for (State state : endingStates)
            if (getCurrentStates().contains(state)) {
                return true;
            }
        return false;
    }
}
