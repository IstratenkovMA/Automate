package automate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public abstract class Automate {

    private ArrayList<Signal> signals;
    private ArrayList<State> Q;
    private Map<State, Map<Signal, State>> T;
    private ArrayList<Signal> header = new ArrayList<>();

    public void init(String filePath) {
        T = new HashMap<>();
        URL resource = getClass().getClassLoader().getResource(filePath);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getPath()))) {
            String[] signalsHeader = bufferedReader.readLine().split(" ");
            for (int i = 0; i < signalsHeader.length; i++) {
                if (!signalsHeader[i].isEmpty())
                    header.add(new Signal(signalsHeader[i]));
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] states = line.split(" ");
                HashMap<Signal, State> stateMap = new HashMap<>();
                int headerIndex = 0;
                for (int i = 1; i < states.length; i++) {
                    if (!states[i].isEmpty()) {
                        stateMap.put(header.get(headerIndex++), new State(states[i]));
                    }
                }
                T.put(new State(states[0]), stateMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void determinate() {
        if (!Signal.isCorrect(header, signals)) {
            throw new RuntimeException() {
                @Override
                public String getMessage() {
                    return "Incorrect signal!";
                }
            };
        }
        for (Signal signal : signals) {
            doMove(signal);
        }
        if (checkResult())
            System.out.println(getMessage());
        else
            System.out.println("Chain not correct of automate");
    }

    protected void doMove(Signal signal) {
        ArrayList<State> newQ = new ArrayList<>();
        for (State state : Q) {
            newQ.add(T.get(state).get(signal));
        }
        Q = newQ;
    }


    protected abstract String getMessage();

    public abstract boolean checkResult();

    public ArrayList<Signal> getSignals() {
        return signals;
    }

    public void setSignals(ArrayList<Signal> signals) {
        this.signals = signals;
    }

    public ArrayList<State> getQ() {
        return Q;
    }

    public void setQ(ArrayList<State> q) {
        Q = q;
    }

    public Map<State, Map<Signal, State>> getT() {
        return T;
    }

    public void setT(Map<State, Map<Signal, State>> t) {
        T = t;
    }

    public ArrayList<Signal> getHeader() {
        return header;
    }

    public void setHeader(ArrayList<Signal> header) {
        this.header = header;
    }
}
