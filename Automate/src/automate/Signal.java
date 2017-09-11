package automate;

import java.util.ArrayList;

public class Signal {

    private String value;

    public Signal(String value) {
        this.value = value;
    }

    public static boolean isCorrect(ArrayList<Signal> alphabet, ArrayList<Signal> signals) {
        for(Signal signal : signals) {
            if(!alphabet.contains(signal)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return value.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
