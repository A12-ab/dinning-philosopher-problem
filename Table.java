import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {
    private final int tableId;
    private final Lock tableLock = new ReentrantLock();
    //private int philosophersAtTable = 5;

    public Table(int tableId) {
        this.tableId = tableId;
    }

    public boolean tryToEat(Philosopher philosopher, Lock leftFork, Lock rightFork) throws InterruptedException {
        tableLock.lock();
        try {
            if (leftFork.tryLock()) {
                try {
                    Thread.sleep(1000);
                    if (rightFork.tryLock()) {
                        return true;
                    } else {
                        leftFork.unlock(); 
                    }
                } finally {
                    // unlock 
                    if (!rightFork.tryLock()) {
                        leftFork.unlock();
                    }
                }
            }
        } finally {
            tableLock.unlock();
        }
        return false;
    }

    public void doneEating(Philosopher philosopher, Lock leftFork, Lock rightFork) {
        leftFork.unlock();
        rightFork.unlock();
        System.out.println("Philosopher " + philosopher.getLabel() + " is done eating.");
    }

}
