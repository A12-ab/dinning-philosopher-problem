import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class EmptyTable {
    private static final int NUM_PHILOSOPHERS = 5;
    private static final Logger logger = Logger.getLogger(EmptyTable.class.getName());

    private final List<Philosopher> philosophers = new CopyOnWriteArrayList<>();
    private final Lock[] forks = new Lock[NUM_PHILOSOPHERS];
    private char lastPhilosopherToMove;
    private boolean deadlocked = false;

    public EmptyTable() {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    public synchronized void movePhilosopherToEmptyTable(Philosopher philosopher) {
        philosophers.add(philosopher);
        lastPhilosopherToMove = philosopher.getLabel();
        logger.info("Philosopher " + lastPhilosopherToMove + " moved to the empty table.");
        if (philosophers.size() == NUM_PHILOSOPHERS) {
            checkDeadlock();
        }
    }

    public boolean tryToEat(Philosopher philosopher, Lock leftFork, Lock rightFork) {
        return true;
    }

    private synchronized void checkDeadlock() {
        if (philosophers.size() == NUM_PHILOSOPHERS) {
            deadlocked = true;
            logger.warning("The empty table has deadlocked.");
            logger.warning("Last philosopher to move to the empty table: " + lastPhilosopherToMove);
        }
    }

    public boolean isDeadlocked() {
        return deadlocked;
    }
}