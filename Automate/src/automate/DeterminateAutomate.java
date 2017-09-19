package automate;

import java.util.ArrayList;

public class DeterminateAutomate extends Automata {

    @Override
    protected String getAutomataCorrectMessage() {
        return "Sequence is correct for Automata";
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
