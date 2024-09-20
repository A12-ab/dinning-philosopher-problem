import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {
    private final int tableId;
    private final Lock tableLock = new ReentrantLock();
    private final Lock[] chopsticks = new Lock[5]; 
    private int philosophersAtTable = 5; 

    public Table(int tableId) {
        this.tableId = tableId;
        // added lock to chopstick
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new ReentrantLock();
        }
    }

    public boolean tryToEat(Philosopher philosopher, Lock[] chopsticks) {
        tableLock.lock();
        try {
            int leftChopstick = philosopher.getId();
            int rightChopstick = (philosopher.getId() + 1) % 5;

            if (chopsticks[leftChopstick].tryLock()) {
                if (chopsticks[rightChopstick].tryLock()) {
                    return true; // Philosopher duita chopstick peye gese
                } else {
                    chopsticks[leftChopstick].unlock(); // jodi right chopstick na pai left er tao chere dicche
                }
            }
        } finally {
            tableLock.unlock();
        }
        return false; 
    }


  
}