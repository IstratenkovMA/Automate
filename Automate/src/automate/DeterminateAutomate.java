package automate;

public class DeterminateAutomate extends Automate {

    @Override
    protected String getAutomataCorrectMessage() {
        return "Congrats";
    }

    @Override
    public boolean checkResult() {
        return false;
    }
}
