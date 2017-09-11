package automate;

public class DeterminateAutomate extends Automate {

    @Override
    protected String getMessage() {
        return "Congrats";
    }

    @Override
    public boolean checkResult() {
        return false;
    }
}
