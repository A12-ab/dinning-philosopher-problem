import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EmptyTable {
    private final List<Philosopher> philosophers = new ArrayList<>();
    private final Lock[] forks = new Lock[5];
    private char lastPhilosopherToMove;
    private boolean deadlocked = false;

    public EmptyTable() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    public synchronized void movePhilosopherToEmptyTable(Philosopher philosopher) {
        philosophers.add(philosopher);
        lastPhilosopherToMove = philosopher.getLabel();
        System.out.println("Philosopher " + lastPhilosopherToMove + " moved to the empty table.");
        if (philosophers.size() == 5) {
            checkDeadlock();
        }
    }

    public boolean tryToEat(Philosopher philosopher, Lock leftFork, Lock rightFork) throws InterruptedException {
        // Philosopher tries to eat at the empty table
        return true;
    }

    private void checkDeadlock() {
        if (philosophers.size() == 5) {
            // Simple deadlock detection: if all 5 philosophers are at the empty table
            deadlocked = true;
            System.out.println("The empty table has deadlocked.");
            System.out.println("Last philosopher to move to the empty table: " + lastPhilosopherToMove);
        }
    }

    public boolean isDeadlocked() {
        return deadlocked;
    }
}
