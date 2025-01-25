package Application.modelClass.processScheduling;

import javafx.beans.property.SimpleIntegerProperty;

public class FCFSModel {
    SimpleIntegerProperty process;
    SimpleIntegerProperty arrival;
    SimpleIntegerProperty burst;
    SimpleIntegerProperty completion;
    SimpleIntegerProperty turn;
    SimpleIntegerProperty waiting;


    public FCFSModel(int process, int arrival, int burst, int completion, int turn, int waiting) {
        this.process = new SimpleIntegerProperty(process);
        this.arrival = new SimpleIntegerProperty(arrival);
        this.burst = new SimpleIntegerProperty(burst);
        this.completion = new SimpleIntegerProperty(completion);
        this.turn = new SimpleIntegerProperty(turn);
        this.waiting = new SimpleIntegerProperty(waiting);
    }

    public int getProcess() {
        return process.get();
    }
    public void setProcess(int process) {
        this.process.set(process);
    }

    public int getArrival() {
        return arrival.get();
    }
    public void setArrival(int arrival) {
        this.arrival.set(arrival);
    }

    public int getBurst() {
        return burst.get();
    }
    public void setBurst(int burst) {
        this.burst.set(burst);
    }

    public int getCompletion() {
        return completion.get();
    }
    public void setCompletion(int completion) {
        this.completion.set(completion);
    }

    public int getTurn() {
        return turn.get();
    }
    public void setTurn(int turn) {
        this.turn.set(turn);
    }

    public int getWaiting() {
        return waiting.get();
    }
    public void setWaiting(int waiting) {
        this.waiting.set(waiting);
    }
}
