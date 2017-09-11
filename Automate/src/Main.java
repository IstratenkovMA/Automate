import automate.AnotherAutomate;
import automate.Automate;
import automate.Signal;
import automate.State;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Automate automate = new AnotherAutomate();
        automate.setSignals(new ArrayList<Signal>() {
            {/*add(new Signal("+,-"));*/ add(new Signal("0...9"));}
        });
        automate.setQ(new ArrayList<State>() {
            {add(new State("q2")); add(new State("q1"));}
        });
        automate.init("Test1.txt");
        automate.determinate();

    }
}
