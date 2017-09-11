package automate;

import java.util.ArrayList;

public class AnotherAutomate extends Automate {

    ArrayList<State> endingState = new ArrayList<>();

    @Override
    protected String getMessage() {
        return "Congrats";
    }

    @Override
    public boolean checkResult() {
        endingState.add(new State("q0"));
        for(State state : endingState)
            if(getQ().contains(state)) {
                return true;
            }
        return false;
    }
}
