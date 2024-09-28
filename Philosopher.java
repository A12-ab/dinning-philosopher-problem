import java.util.concurrent.locks.Lock;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {
    private final char label;
    private final Table table;
    private final Lock leftFork, rightFork;
    private final EmptyTable emptyTable;
    private final long startTime;
    private boolean atEmptyTable = false;

    public Philosopher(char label, Table table, Lock leftFork, Lock rightFork, EmptyTable emptyTable, long startTime) {
        this.label = label;
        this.table = table;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.emptyTable = emptyTable;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                think();
                
                if (!atEmptyTable) {
                    // Try to eat at the main table
                    if (table.tryToEat(this, leftFork, rightFork)) {
                        eat();
                        table.doneEating(this, leftFork, rightFork);
                    } else {
                        // Deadlock detected, move to the empty table
                        System.out.println("Philosopher " + label + " moving to the empty table.");
                        emptyTable.movePhilosopherToEmptyTable(this);
                        atEmptyTable = true;
                    }
                } else {
                    // Philosopher is at the empty table
                    emptyTable.tryToEat(this, leftFork, rightFork);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + label + " is thinking.");
        Thread.sleep(ThreadLocalRandom.current().nextLong(0, 10000)); // Think for 0-10 seconds
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + label + " is eating.");
        Thread.sleep(ThreadLocalRandom.current().nextLong(0, 5000)); // Eat for 0-5 seconds
    }

    public char getLabel() {
        return label;
    }
}