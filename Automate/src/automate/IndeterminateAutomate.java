package automate;

import java.util.ArrayList;

public class IndeterminateAutomate extends Automate {

    ArrayList<State> endingState = new ArrayList<>();

    @Override
    protected String getAutomataCorrectMessage() {
        return "Congrats";
    }

    @Override
    public boolean checkResult() {
        endingState.add(new State("q0"));
        for(State state : endingState)
            if(getCurrentStates().contains(state)) {
                return true;
            }
        return false;
    }
}
