import java.util.concurrent.locks.Lock;

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
            while (true) {
                think();
                
                if (!atEmptyTable) {
                    // khaoar try kortese ekhane
                    if (table.tryToEat(this, leftFork, rightFork)) {
                        eat();
                        table.doneEating(this, leftFork, rightFork);
                    } else {
                        // deadlock so faka table e movie korbe
                        System.out.println("Philosopher " + label + " moving to the empty table.");
                        emptyTable.movePhilosopherToEmptyTable(this);
                        atEmptyTable = true;
                    }
                } else {
                    // Philosopher faka table e chole gese
                    emptyTable.tryToEat(this, leftFork, rightFork);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + label + " is thinking.");
        Thread.sleep((long) (Math.random() * 10000)); // Think for 0-10 seconds
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + label + " is eating.");
        Thread.sleep((long) (Math.random() * 5000)); // Eat for 0-5 seconds
    }

    public char getLabel() {
        return label;
    }
}
