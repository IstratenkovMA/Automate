import automate.IndeterminateAutomate;
import automate.Automate;
import automate.Signal;
import automate.State;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Automate automate = new IndeterminateAutomate();
        automate.setSignals(new ArrayList<Signal>() {
            {/*add(new Signal("+,-"));*/ add(new Signal("0...9"));}
        });
        automate.setCurrentStates(new ArrayList<State>() {
            {
                add(new State("q2"));
                add(new State("q1"));
            }
        });
        automate.init("resources/Test1.txt");
        automate.determinate();
    }
}
