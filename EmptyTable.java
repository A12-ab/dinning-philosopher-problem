import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class EmptyTable {
    private final Lock[] chopsticks = new Lock[5];

    public EmptyTable() {
        
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new ReentrantLock();
        }
    }

    private void checkDeadlock() {
        if (philosophers.size() == 5) {
            deadlocked = true;
            System.out.println("The empty table has deadlocked.");
            System.out.println("Last philosopher to move to the empty table: " + lastPhilosopherToMove);
        }
    }

    public boolean isDeadlocked() {
        return deadlocked;
    }

   
}