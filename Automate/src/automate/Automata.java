package automate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Automata {

    private ArrayList<Signal> signals;

    private ArrayList<State> currentStates;

    private ArrayList<State> endingStates;

    private ArrayList<State> initialStates;

    private Map<State, Map<Signal, ArrayList<State>>> T;

    private ArrayList<Signal> alphabet = new ArrayList<>();

    public Automata() {
    }

    public Automata(Map<State, Map<Signal, ArrayList<State>>> t, ArrayList<Signal> alphabet) {
        T = t;
        this.alphabet = alphabet;
    }

    public Automata(Map<State, Map<Signal, ArrayList<State>>> t) {
        T = t;
    }

    public void init(String filePath) {
        URL resource = getClass().getClassLoader().getResource(filePath);
        T = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getPath()))) {
            getInitialStates(bufferedReader);
            getEndingStates(bufferedReader);
            getEnterSignals(bufferedReader);
            getHeaderSignals(bufferedReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] states = line.split(" ");
                HashMap<Signal, ArrayList<State>> stateMap = new HashMap<>();
                int headerIndex = 0;
                for (int i = 1; i < states.length; i++) {
                    if (!states[i].isEmpty()) {
                        String[] stateMas = states[i].split(",");
                        ArrayList<State> statesInMap = new ArrayList<>();
                        for (int j = 0; j < stateMas.length; j++) {
                            statesInMap.add(new State(stateMas[j]));
                        }
                        stateMap.put(alphabet.get(headerIndex++), statesInMap);
                    }
                }
                T.put(new State(states[0]), stateMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getHeaderSignals(BufferedReader bufferedReader) throws IOException {
        String[] signalsHeader = bufferedReader.readLine().split(" ");
        for (int i = 0; i < signalsHeader.length; i++) {
            if (!signalsHeader[i].isEmpty())
                alphabet.add(new Signal(signalsHeader[i]));
        }
    }

    private void getInitialStates(BufferedReader bufferedReader) throws IOException {
        String[] initialStatesLine = bufferedReader.readLine().split(" ");
        initialStates = new ArrayList<>();
        for (int i = 0; i < initialStatesLine.length; i++) {
            if (!initialStatesLine[i].isEmpty())
                initialStates.add(new State(initialStatesLine[i]));
        }
    }

    private void getEndingStates(BufferedReader bufferedReader) throws IOException {
        String[] endingStatesLine = bufferedReader.readLine().split(" ");
        endingStates = new ArrayList<>();
        for (int i = 0; i < endingStatesLine.length; i++) {
            if (!endingStatesLine[i].isEmpty())
                endingStates.add(new State(endingStatesLine[i]));
        }
    }

    private void getEnterSignals(BufferedReader bufferedReader) throws IOException {
        String[] enterSignals = bufferedReader.readLine().split(" ");
        signals = new ArrayList<>();
        for (int i = 0; i < enterSignals.length; i++) {
            if (!enterSignals[i].isEmpty())
                signals.add(new Signal(enterSignals[i]));
        }
    }

    public void determinate() {
        if (!Signal.isCorrect(alphabet, signals)) {
            throw new RuntimeException() {
                @Override
                public String getMessage() {
                    return "Incorrect signal!";
                }
            };
        }
        currentStates = new ArrayList<>(initialStates);
        for (Signal signal : signals) {
            doMove(signal);
        }
        if (checkResult())
            System.out.println(getAutomataCorrectMessage());
        else
            System.out.println("Chain not correct of automate");
    }

    protected void doMove(Signal signal) {
        ArrayList<State> newQ = new ArrayList<>();
        for (State state : currentStates) {
            newQ.addAll(T.get(state).get(signal));
        }
        currentStates = newQ;
    }

    public ArrayList<State> getEndingStates() {
        return endingStates;
    }

    public void setEndingStates(ArrayList<State> endingStates) {
        this.endingStates = endingStates;
    }

    public ArrayList<State> getInitialStates() {
        return initialStates;
    }

    public void setInitialStates(ArrayList<State> initialStates) {
        this.initialStates = initialStates;
    }


    protected abstract String getAutomataCorrectMessage();

    public abstract boolean checkResult();

    public ArrayList<Signal> getSignals() {
        return signals;
    }

    public void setSignals(ArrayList<Signal> signals) {
        this.signals = signals;
    }

    public ArrayList<State> getCurrentStates() {
        return currentStates;
    }

    public void setCurrentStates(ArrayList<State> currentStates) {
        this.currentStates = currentStates;
    }

    public Map<State, Map<Signal, ArrayList<State>>> getT() {
        return T;
    }

    public void setT(Map<State, Map<Signal, ArrayList<State>>> t) {
        T = t;
    }

    public ArrayList<Signal> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(ArrayList<Signal> alphabet) {
        this.alphabet = alphabet;
    }
}
